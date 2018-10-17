package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Channel;

import java.util.List;

import io.reactivex.Observable;

class CacheRepository implements LocalRepository {
    private ObservableList<Channel> channels = new ObservableList<>();

    @Override
    public Observable<List<Channel>> getChannels() {
        return channels.getObservable();
    }

    @Override
    public void cacheChannels(List<Channel> channels) {
        this.channels.setItems(channels);
    }
}