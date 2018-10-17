package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Channel;

import java.util.List;

import io.reactivex.Observable;

public interface LocalRepository {
    Observable<List<Channel>> getChannels();

    void cacheChannels(List<Channel> channels);
}