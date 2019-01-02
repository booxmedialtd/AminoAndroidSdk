package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Program;

import io.reactivex.Flowable;

public interface RecordingProvider {
    Flowable<Program> getPastRecordings();

    Flowable<Program> getFutureRecordings();

    Flowable<Program> getRecordingsByTime(long startTime, long endTime);

    Flowable<Program> getFavoriteRecordings(long startTime, long endTime);
}