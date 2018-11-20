package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.CacheTTLConfig;
import com.aminocom.sdk.CookieManager;
import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.ChannelMapper;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.provider.ChannelProvider;

import java.util.List;

import io.reactivex.Flowable;

public class ChannelProviderImpl implements ChannelProvider {

    private ServerApi api;
    private LocalRepository localRepository;
    private String service;
    private CookieManager cookieManager;

    private long channelsCacheTime = 0;

    public static ChannelProvider newInstance(ServerApi api, LocalRepository localRepository, String service, CookieManager cookieManager) {
        return new ChannelProviderImpl(api, localRepository, service, cookieManager);
    }

    private ChannelProviderImpl(ServerApi api, LocalRepository localRepository, String service, CookieManager cookieManager) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
        this.cookieManager = cookieManager;
    }

    // TODO: change username when username will be stored in SDK
    @Override
    public Flowable<List<Channel>> getChannels() {
        String userName = cookieManager.isCookieExists() ? "bt1@dna.fi" : "guest";

        if (System.currentTimeMillis() - channelsCacheTime > CacheTTLConfig.CHANNEL_TTL) {
            return api.getChannels(userName, service)
                    .toObservable()
                    .flatMapIterable(response -> response.data.channels)
                    .map(ChannelMapper::from)
                    .toList()
                    .doOnSuccess(channels -> {
                        localRepository.cacheChannels(channels);
                        channelsCacheTime = System.currentTimeMillis();
                    })
                    .flatMapPublisher(list -> localRepository.getChannels());
        } else {
            return localRepository.getChannels();
        }
    }
}
