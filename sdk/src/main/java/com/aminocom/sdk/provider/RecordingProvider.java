package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Program;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface RecordingProvider {
    Flowable<List<Program>> getPastRecordings();

    Flowable<List<Program>> getUpcomingRecordings();

    Flowable<List<Program>> getRecordings(Long startTime, Long endTime);

    Flowable<List<Program>> getFavoriteRecordings(Long startTime, Long endTime);

    Completable setFavorite(String programUid, boolean isFavorite);
}