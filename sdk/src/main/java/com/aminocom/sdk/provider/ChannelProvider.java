package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Flowable;

public interface ChannelProvider {
    Flowable<List<Channel>> getChannels();
}