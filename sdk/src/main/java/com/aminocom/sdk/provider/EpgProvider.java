package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Epg;

import java.util.List;

import io.reactivex.Observable;

public interface EpgProvider {
    Observable<List<Epg>> getTodayEpg();

    Observable<List<Epg>> getEpg(long dateInMillis);
}