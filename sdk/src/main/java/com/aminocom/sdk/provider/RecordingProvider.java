package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Program;

import io.reactivex.Flowable;

public interface RecordingProvider {
    Flowable<Program> getRecordings();

    Flowable<Program> getRecordingsByTime(long startTime, long endTime);
}