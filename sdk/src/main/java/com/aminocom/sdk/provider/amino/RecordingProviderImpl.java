package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.ProgramMapper;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.provider.RecordingProvider;
import com.aminocom.sdk.util.DateUtil;

import java.util.List;

import io.reactivex.Flowable;

public class RecordingProviderImpl implements RecordingProvider {

    private static final int INITIAL_RECORDING_PAGE = 0;
    private static final int MAX_RECORDING_PAGE = 20;

    private ServerApi api;
    private LocalRepository localRepository;
    private String service;

    private long recordingCacheTime = 0;

    public static RecordingProvider newInstance(ServerApi api, LocalRepository localRepository, String service) {
        return new RecordingProviderImpl(api, localRepository, service);
    }

    private RecordingProviderImpl(ServerApi api, LocalRepository localRepository, String service) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
    }

    @Override
    public Flowable<List<Program>> getPastRecordings() {
        return getRecordings(null, System.currentTimeMillis());
    }

    @Override
    public Flowable<List<Program>> getUpcomingRecordings() {
        return getRecordings(System.currentTimeMillis(), null);
    }

    // TODO: change username when username will be stored in SDK
    @Override
    public Flowable<List<Program>> getRecordings(Long startTime, Long endTime) {
        return Flowable.range(INITIAL_RECORDING_PAGE, MAX_RECORDING_PAGE)
                .flatMapSingle(page -> api.getRecording("btcv3@dna.fi", service, DateUtil.getTimeInSeconds(startTime), DateUtil.getTimeInSeconds(endTime), page))
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
                .flatMapSingle(page -> api.getFavoriteRecording("btcv3@dna.fi", service, DateUtil.getTimeInSeconds(startTime), DateUtil.getTimeInSeconds(endTime), page))
                .takeUntil(response -> response.recordedContent.resultSet.currentPage == response.recordedContent.resultSet.totalPages - 1)
                .map(response -> ProgramMapper.from(response.recordedContent.programList.programs))
                .doOnNext(programs -> {
                    localRepository.updateOrInsertPrograms(programs);
                    recordingCacheTime = System.currentTimeMillis();
                })
                .flatMap(programs -> localRepository.getPrograms(startTime, endTime));
    }
}