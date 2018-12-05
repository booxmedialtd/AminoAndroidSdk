package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Epg;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface EpgProvider {
    Flowable<List<Epg>> getTodayEpg();

    Flowable<List<Epg>> getEpg(long dateInMillis);

    Single<Boolean> loadEpg(long startDateInMillis, long endDateInMillis);
}