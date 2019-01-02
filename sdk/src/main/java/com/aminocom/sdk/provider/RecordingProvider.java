package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Program;

import io.reactivex.Flowable;

public interface RecordingProvider {
    Flowable<Program> getPastRecordings();

    Flowable<Program> getFutureRecordings();

    Flowable<Program> getRecordings(Long startTime, Long endTime);

    Flowable<Program> getFavoriteRecordings(Long startTime, Long endTime);
}