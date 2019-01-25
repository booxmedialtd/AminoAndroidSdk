package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.CookieManager;
import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.ChannelMapper;
import com.aminocom.sdk.mapper.LiveChannelMapper;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.client.channel.LiveChannel;
import com.aminocom.sdk.provider.ChannelProvider;
import com.aminocom.sdk.provider.UserProvider;
import com.aminocom.sdk.settings.Settings;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class ChannelProviderImpl implements ChannelProvider {

    private ServerApi api;
    private LocalRepository localRepository;
    private String service;
    private CookieManager cookieManager;
    private Settings settings;

    static ChannelProvider newInstance(ServerApi api, LocalRepository localRepository, String service, CookieManager cookieManager, Settings settings) {
        return new ChannelProviderImpl(api, localRepository, service, cookieManager, settings);
    }

    private ChannelProviderImpl(ServerApi api, LocalRepository localRepository, String service, CookieManager cookieManager, Settings settings) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
        this.cookieManager = cookieManager;
        this.settings = settings;
    }

    @Override
    public Flowable<List<Channel>> getChannels() {
        String userName = cookieManager.isCookieExists() ? settings.getUserName() : UserProvider.USER_GUEST;

        if (System.currentTimeMillis() - settings.getChannelLoadedTime() > settings.getCacheTtlManager().getChannelTtl()) {
            return api.getChannels(userName, service)
                    .toObservable()
                    .flatMapIterable(response -> response.data.channels)
                    .map(ChannelMapper::from)
                    .toList()
                    .doOnSuccess(channels -> {
                        localRepository.cacheChannels(channels);
                        settings.setChannelLoadedTime(System.currentTimeMillis());
                    })
                    .flatMapPublisher(list -> localRepository.getChannels());
        } else {
            return localRepository.getChannels();
        }
    }

    @Override
    public Flowable<List<LiveChannel>> getLiveChannels() {
        return getChannels()
                .flatMapSingle(channels -> Flowable.fromIterable(channels)
                        .flatMapSingle(this::getLiveChannel)
                        .toList()
                );
    }

    private Single<LiveChannel> getLiveChannel(Channel channel) {
        return localRepository
                .getUpcomingPrograms(channel.getId(), System.currentTimeMillis(), 3)
                .map(programs -> LiveChannelMapper.from(channel, programs));
    }
}
