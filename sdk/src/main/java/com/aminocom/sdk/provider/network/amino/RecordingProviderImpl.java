package com.aminocom.sdk.provider.network.amino;

import com.aminocom.sdk.provider.local.LocalProvider;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.ProgramMapper;
import com.aminocom.sdk.mapper.RecordingGroupMapper;
import com.aminocom.sdk.model.client.RecordingGroup;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.provider.network.RecordingProvider;
import com.aminocom.sdk.settings.Settings;
import com.aminocom.sdk.util.DateUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class RecordingProviderImpl implements RecordingProvider {

    private static final int INITIAL_RECORDING_PAGE = 0;
    private static final int MAX_RECORDING_PAGE = 20;

    private ServerApi api;
    private LocalProvider localRepository;
    private String service;
    private Settings settings;

    private long recordingCacheTime = 0;

    public static RecordingProvider newInstance(ServerApi api, LocalProvider localRepository, String service, Settings settings) {
        return new RecordingProviderImpl(api, localRepository, service, settings);
    }

    private RecordingProviderImpl(ServerApi api, LocalProvider localRepository, String service, Settings settings) {
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
        if (System.currentTimeMillis() - settings.getRecordingLoadedTime() > settings.getCacheTtlManager().getRecordingTtl()) {
            return Flowable.range(INITIAL_RECORDING_PAGE, MAX_RECORDING_PAGE)
                    .flatMapSingle(page -> api.getRecording(settings.getUserName(), service, DateUtil.getTimeInSeconds(startTime), DateUtil.getTimeInSeconds(endTime), page))
                    .takeUntil(response -> response.recordedContent.resultSet.currentPage == response.recordedContent.resultSet.totalPages - 1)
                    .map(response -> ProgramMapper.from(response.recordedContent.programList.programs))
                    .doOnNext(programs -> {
                        localRepository.updateOrInsertPrograms(programs);
                        recordingCacheTime = System.currentTimeMillis();
                    })
                    .flatMap(programs -> localRepository.getPrograms(startTime, endTime));
        } else {
            return localRepository.getPrograms(startTime, endTime);
        }
    }

    // FIXME: Fix returning of favorites only
    @Override
    public Flowable<List<Program>> getFavoriteRecordings(Long startTime, Long endTime) {
        if (System.currentTimeMillis() - settings.getFavoriteRecordingLoadedTime() > settings.getCacheTtlManager().getRecordingTtl()) {
            return Flowable.range(INITIAL_RECORDING_PAGE, MAX_RECORDING_PAGE)
                    .flatMapSingle(page -> api.getFavoriteRecording(settings.getUserName(), service, page))
                    .takeUntil(response -> response.recordedContent.resultSet.currentPage == response.recordedContent.resultSet.totalPages - 1)
                    .map(response -> ProgramMapper.from(response.recordedContent.programList.programs))
                    .doOnNext(programs -> {
                        localRepository.updateOrInsertPrograms(programs);
                        recordingCacheTime = System.currentTimeMillis();
                    })
                    .flatMap(programs -> localRepository.getPrograms(startTime, endTime));
        } else {
            return localRepository.getPrograms(startTime, endTime);
        }
    }

    @Override
    public Completable setFavorite(String programUid, boolean isFavorite) {
        return isFavorite ? api.addFavoriteRecording(settings.getUserName(), programUid, service) :
                api.removeFavoriteRecording(settings.getUserName(), programUid, service);
    }

    // Add group DAO
    @Override
    public Flowable<List<RecordingGroup>> getGroups(Long startTime) {
        return Flowable.range(INITIAL_RECORDING_PAGE, MAX_RECORDING_PAGE)
                .flatMapSingle(page -> api.getRecordingGroups(settings.getUserName(), startTime, false, service, page))
                .takeUntil(response -> response.resultSet.currentPage == response.resultSet.totalPages - 1)
                .map(response -> RecordingGroupMapper.from(response.recordingGroups))
                .doOnNext(programs -> {
                    localRepository.cacheGroups(programs);
                    recordingCacheTime = System.currentTimeMillis();
                })
                .flatMap(programs -> localRepository.getRecordingGroups());
    }

    @Override
    public Flowable<List<Program>> getGroupRecordings(String groupId) {
        return Flowable.range(INITIAL_RECORDING_PAGE, MAX_RECORDING_PAGE)
                .flatMapSingle(page -> api.getGroupRecordings(settings.getUserName(), groupId, service))
                .takeUntil(response -> response.resultSet.currentPage == response.resultSet.totalPages - 1)
                .map(response -> ProgramMapper.from(response.recordingGroup.programs, groupId))
                .doOnNext(programs -> {
                    localRepository.updateOrInsertPrograms(programs);
                    recordingCacheTime = System.currentTimeMillis();
                })
                .flatMap(programs -> localRepository.getProgramsByGroup(groupId));
    }
}