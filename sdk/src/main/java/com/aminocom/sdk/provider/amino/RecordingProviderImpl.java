package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.provider.RecordingProvider;

import io.reactivex.Flowable;

public class RecordingProviderImpl implements RecordingProvider {

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
    public Flowable<Program> getPastRecordings() {
        return getRecordings(null, System.currentTimeMillis());
    }

    @Override
    public Flowable<Program> getFutureRecordings() {
        return getRecordings(System.currentTimeMillis(), null);
    }

    @Override
    public Flowable<Program> getRecordings(Long startTime, Long endTime) {
        return Flowable.empty();
    }

    @Override
    public Flowable<Program> getFavoriteRecordings(Long startTime, Long endTime) {
        return Flowable.empty();
    }
}