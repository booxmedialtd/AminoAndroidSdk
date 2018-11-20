package com.aminocom.aminoandroidsdk;

import android.app.Application;

import com.aminocom.sdk.AndroidCookieManager;
import com.aminocom.sdk.DbRepository;
import com.aminocom.sdk.Sdk;
import com.aminocom.sdk.provider.ProviderType;

public class App extends Application {
    public Sdk sdk;

    @Override
    public void onCreate() {
        super.onCreate();

        sdk = new Sdk(
                "https://tv.dna.fi/",
                "dnaclient",
                "dn4c1!3nt",
                ProviderType.AMINO,
                new AndroidCookieManager(),
                new DbRepository(this)
        );
    }
}
