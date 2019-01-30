package com.aminocom.sdk.provider.network.amino;

import com.aminocom.sdk.CustomDigestAuthenticator;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.provider.network.UserProvider;
import com.aminocom.sdk.settings.Settings;
import com.aminocom.sdk.util.AccountUtil;
import com.burgstaller.okhttp.digest.Credentials;

import io.reactivex.Single;

public class UserProviderImpl implements UserProvider {
    private ServerApi api;
    private CustomDigestAuthenticator authenticator;
    private String service;
    private Settings settings;

    public static UserProvider newInstance(ServerApi api, CustomDigestAuthenticator authenticator, String service, Settings settings) {
        return new UserProviderImpl(api, authenticator, service, settings);
    }

    private UserProviderImpl(ServerApi api, CustomDigestAuthenticator authenticator, String service, Settings settings) {
        this.api = api;
        this.authenticator = authenticator;
        this.service = service;
        this.settings = settings;
    }

    // TODO: add correct parameters when SDK Builder will be ready
    @Override
    public Single<UserResponse> login(String username, String password) {
        authenticator.setUserCredentials(new Credentials(username, password));

        return api.login(
                username,
                "0.0.1",
                AccountUtil.generateUuid(),
                "Android",
                "8.0.1",
                service
        ).doOnSuccess(response -> settings.setUserName(username));
    }
}
