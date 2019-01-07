package com.aminocom.sdk.provider;

import com.aminocom.sdk.CookieManager;
import com.aminocom.sdk.CustomDigestAuthenticator;
import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.provider.amino.AminoProviders;
import com.aminocom.sdk.settings.Settings;

public class ProviderFactory {

    public static Providers getProvider(ProviderType type, ServerApi api, CustomDigestAuthenticator authenticator, String service, CookieManager cookieManager, LocalRepository dbRepository, Settings settings) {
        switch (type) {
            case AMINO:
                return new AminoProviders(api, authenticator, service, cookieManager, dbRepository, settings);

            default:
                return new AminoProviders(api, authenticator, service, cookieManager, dbRepository, settings);
        }
    }
}