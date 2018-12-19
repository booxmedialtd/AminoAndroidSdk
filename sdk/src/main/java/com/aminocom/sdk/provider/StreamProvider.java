package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Stream;

import java.util.List;

import io.reactivex.Single;

public interface StreamProvider {
    Single<List<Stream>> getLiveStreams(String channelId);

    Single<List<Stream>> getRecordingStreams(String recordingId);
}