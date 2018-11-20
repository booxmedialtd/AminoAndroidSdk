package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.CacheRepository;
import com.aminocom.sdk.CookieManager;
import com.aminocom.sdk.CustomDigestAuthenticator;
import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.provider.CategoryProvider;
import com.aminocom.sdk.provider.ChannelProvider;
import com.aminocom.sdk.provider.EpgProvider;
import com.aminocom.sdk.provider.Providers;
import com.aminocom.sdk.provider.UserProvider;

public class AminoProviders implements Providers {

    private UserProvider userProvider;
    private ChannelProvider channelProvider;
    private CategoryProvider categoryProvider;
    private EpgProvider epgProvider;

    public AminoProviders(ServerApi api, CustomDigestAuthenticator authenticator, String service, CookieManager cookieManager, LocalRepository dbRepository) {
        LocalRepository localRepository = new CacheRepository();

        userProvider = UserProviderImpl.newInstance(api, authenticator, service);
        channelProvider = ChannelProviderImpl.newInstance(api, dbRepository, service, cookieManager);
        categoryProvider = CategoryProviderImpl.newInstance(api, localRepository, service);
        epgProvider = EpgProviderImpl.newInstance(api, dbRepository, service);
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
}