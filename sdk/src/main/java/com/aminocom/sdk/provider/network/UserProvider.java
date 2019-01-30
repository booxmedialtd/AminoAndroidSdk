package com.aminocom.sdk.provider.network;

import com.aminocom.sdk.model.network.UserResponse;

import io.reactivex.Single;

public interface UserProvider {

    String USER_GUEST = "guest";

    Single<UserResponse> login(String username, String password);
}