package com.aminocom.sdk.provider.network;

import com.aminocom.sdk.model.network.Storage;
import com.aminocom.sdk.model.network.UserResponse;

import io.reactivex.Single;

public interface UserProvider {

    String USER_GUEST = "guest";

    Single<UserResponse> login(String username, String password);

    Single<Boolean> logout();

    Single<UserResponse> register(String username, String password);

    Single<Storage> getUserStorage();
}