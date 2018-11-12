package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.network.UserResponse;

import io.reactivex.Single;

public interface UserProvider {
    Single<UserResponse> login(String login, String password);
}