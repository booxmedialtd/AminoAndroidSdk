package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.ProgramMapper;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.provider.RecordingProvider;
import com.aminocom.sdk.settings.Settings;
import com.aminocom.sdk.util.DateUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class RecordingProviderImpl implements RecordingProvider {

    private static final int INITIAL_RECORDING_PAGE = 0;
    private static final int MAX_RECORDING_PAGE = 20;

    private ServerApi api;
    private LocalRepository localRepository;
    private String service;
    private Settings settings;

    private long recordingCacheTime = 0;

    public static RecordingProvider newInstance(ServerApi api, LocalRepository localRepository, String service, Settings settings) {
        return new RecordingProviderImpl(api, localRepository, service, settings);
    }

    private RecordingProviderImpl(ServerApi api, LocalRepository localRepository, String service, Settings settings) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
        this.settings = settings;
    }

    @Override
    public Flowable<List<Program>> getPastRecordings() {
        return getRecordings(null, System.currentTimeMillis());
    }

    @Override
    public Flowable<List<Program>> getUpcomingRecordings() {
        return getRecordings(System.currentTimeMillis(), null);
    }

    @Override
    public Flowable<List<Program>> getRecordings(Long startTime, Long endTime) {
        return Flowable.range(INITIAL_RECORDING_PAGE, MAX_RECORDING_PAGE)
                .flatMapSingle(page -> api.getRecording(settings.getUserName(), service, DateUtil.getTimeInSeconds(startTime), DateUtil.getTimeInSeconds(endTime), page))
                .takeUntil(response -> response.recordedContent.resultSet.currentPage == response.recordedContent.resultSet.totalPages - 1)
                .map(response -> ProgramMapper.from(response.recordedContent.programList.programs))
                .doOnNext(programs -> {
                    localRepository.updateOrInsertPrograms(programs);
                    recordingCacheTime = System.currentTimeMillis();
                })
                .flatMap(programs -> localRepository.getPrograms(startTime, endTime));
    }

    @Override
    public Flowable<List<Program>> getFavoriteRecordings(Long startTime, Long endTime) {
        return Flowable.range(INITIAL_RECORDING_PAGE, MAX_RECORDING_PAGE)
                .flatMapSingle(page -> api.getFavoriteRecording(settings.getUserName(), service, page))
                .takeUntil(response -> response.recordedContent.resultSet.currentPage == response.recordedContent.resultSet.totalPages - 1)
                .map(response -> ProgramMapper.from(response.recordedContent.programList.programs))
                .doOnNext(programs -> {
                    localRepository.updateOrInsertPrograms(programs);
                    recordingCacheTime = System.currentTimeMillis();
                })
                .flatMap(programs -> localRepository.getPrograms(startTime, endTime));
    }

    @Override
    public Completable setFavorite(String programUid, boolean isFavorite) {
        return isFavorite ? api.addFavoriteRecording(settings.getUserName(), programUid, service) :
                api.removeFavoriteRecording(settings.getUserName(), programUid, service);
    }

    @Override
    public Flowable<List<Group>> getGroups() {
        return null;
    }

    @Override
    public Flowable<List<Group>> getGroupRecordings(String groupId) {
        return null;
    }
}