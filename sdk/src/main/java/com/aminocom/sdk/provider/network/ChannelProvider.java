package com.aminocom.sdk.provider.network;

import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.client.channel.LiveChannel;

import java.util.List;

import io.reactivex.Flowable;

public interface ChannelProvider {
    Flowable<List<Channel>> getChannels();

    Flowable<List<LiveChannel>> getLiveChannels();
}