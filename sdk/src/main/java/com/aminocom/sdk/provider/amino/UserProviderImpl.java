package com.aminocom.sdk.provider.amino;

import com.aminocom.sdk.CustomDigestAuthenticator;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.provider.UserProvider;
import com.aminocom.sdk.util.AccountUtil;
import com.burgstaller.okhttp.digest.Credentials;

import io.reactivex.Single;

public class UserProviderImpl implements UserProvider {
    private ServerApi api;
    private CustomDigestAuthenticator authenticator;
    private String service;

    public static UserProvider newInstance(ServerApi api, CustomDigestAuthenticator authenticator, String service) {
        return new UserProviderImpl(api, authenticator, service);
    }

    private UserProviderImpl(ServerApi api, CustomDigestAuthenticator authenticator, String service) {
        this.api = api;
        this.authenticator = authenticator;
        this.service = service;
    }

    // TODO: add correct parameters when SDK Builder will be ready
    @Override
    public Single<UserResponse> login(String login, String password) {
        authenticator.setUserCredentials(new Credentials(login, password));

        return api.login(
                login,
                "0.0.1",
                AccountUtil.generateUuid(),
                "Android",
                "8.0.1",
                service
        );
    }
}
