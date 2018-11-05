package com.aminocom.sdk;

import com.aminocom.sdk.model.network.ChannelResponse;
import com.aminocom.sdk.model.network.UserResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

// TODO: Add service parameter to interceptor
interface ServerApi {

    @POST("api/user/{user}/login")
    @FormUrlEncoded
    Single<UserResponse> login(
            @Path("user") String user,
            @Field("ver") String version,
            @Field("device_id") String deviceId,
            @Field("platform") String platform,
            @Field("os_ver") String osVersion,
            @Field("service") String service
    );

    @GET("api/v3/user/{user}/channel/")
    Single<ChannelResponse> getChannels(@Path("user") String user, @Query("service") String service);
}