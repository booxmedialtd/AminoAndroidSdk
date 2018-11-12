package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Observable;

public interface ChannelProvider {
    Observable<List<Channel>> getChannels();
}