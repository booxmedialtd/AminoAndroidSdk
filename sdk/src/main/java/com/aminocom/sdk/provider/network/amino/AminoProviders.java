package com.aminocom.sdk.provider.network.amino;

import com.aminocom.sdk.CookieManager;
import com.aminocom.sdk.CustomDigestAuthenticator;
import com.aminocom.sdk.provider.local.LocalProvider;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.provider.network.CategoryProvider;
import com.aminocom.sdk.provider.network.ChannelProvider;
import com.aminocom.sdk.provider.network.EpgProvider;
import com.aminocom.sdk.provider.network.Providers;
import com.aminocom.sdk.provider.network.RecordingProvider;
import com.aminocom.sdk.provider.network.StreamProvider;
import com.aminocom.sdk.provider.network.UserProvider;
import com.aminocom.sdk.settings.Settings;

public class AminoProviders implements Providers {

    private UserProvider userProvider;
    private ChannelProvider channelProvider;
    private CategoryProvider categoryProvider;
    private EpgProvider epgProvider;
    private RecordingProvider recordingProvider;
    private StreamProvider streamProvider;

    public AminoProviders(ServerApi api, CustomDigestAuthenticator authenticator, String service, CookieManager cookieManager, LocalProvider dbProvider, Settings settings) {
        userProvider = UserProviderImpl.newInstance(api, authenticator, service, settings);
        channelProvider = ChannelProviderImpl.newInstance(api, dbProvider, service, cookieManager, settings);
        categoryProvider = CategoryProviderImpl.newInstance(api, dbProvider, service, settings);
        epgProvider = EpgProviderImpl.newInstance(api, dbProvider, service, settings);
        recordingProvider = RecordingProviderImpl.newInstance(api, dbProvider, service, settings);
        streamProvider = StreamProviderImpl.newInstance(api, service);
    }

    @Override
    public UserProvider user() {
        return userProvider;
    }

    @Override
    public ChannelProvider channels() {
        return channelProvider;
    }

    @Override
    public CategoryProvider categories() {
        return categoryProvider;
    }

    @Override
    public EpgProvider epg() {
        return epgProvider;
    }

    @Override
    public RecordingProvider recording() {
        return recordingProvider;
    }

    @Override
    public StreamProvider stream() {
        return streamProvider;
    }
}