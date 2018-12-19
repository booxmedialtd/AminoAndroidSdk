package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.StreamMapper;
import com.aminocom.sdk.model.client.Stream;
import com.aminocom.sdk.provider.StreamProvider;

import java.util.List;

import io.reactivex.Single;

public class StreamProviderImpl implements StreamProvider {
    private ServerApi api;
    private String service;

    public static StreamProvider newInstance(ServerApi api, String service) {
        return new StreamProviderImpl(api, service);
    }

    private StreamProviderImpl(ServerApi api, String service) {
        this.api = api;
        this.service = service;
    }

    @Override
    public Single<List<Stream>> getLiveStreams(String channelId) {
        return api.getChannelStreams(channelId, service)
                .map(response -> StreamMapper.from(channelId, response.data));
    }

    @Override
    public Single<List<Stream>> getRecordingStreams(String recordingId) {
        return api.getRecordingStreams(recordingId, service)
                .map(response -> StreamMapper.from(recordingId, response.data));
    }
}