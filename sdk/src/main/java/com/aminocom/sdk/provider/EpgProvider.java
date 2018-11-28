package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Program;

import java.util.List;

import io.reactivex.Flowable;

public interface EpgProvider {
    Flowable<List<Epg>> getTodayEpg();

    Flowable<List<Epg>> getEpg(long dateInMillis);

    Flowable<List<Program>> loadEpg(long dateInMillis);
}