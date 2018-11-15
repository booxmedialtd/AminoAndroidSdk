package com.aminocom.sdk;

import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.model.network.category.CategoryListResponse;
import com.aminocom.sdk.model.network.category.CategoryResponse;
import com.aminocom.sdk.model.network.channel.ChannelResponse;
import com.aminocom.sdk.model.network.epg.EpgResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

// TODO: Add service parameter to interceptor
public interface ServerApi {

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

    @GET("api/v3/user/{user}/channel")
    Single<ChannelResponse> getChannels(@Path("user") String user, @Query("service") String service);

    @GET("api/category")
    Single<CategoryListResponse> getCategoryList(@Query("service") String service);

    @GET
    Single<CategoryResponse> getCategory(@Url String url);

    @GET("api/v3/epg")
    Single<EpgResponse> getEpg(@Query("service") String service,
                               @Query("st") String startTime,
                               @Query("et") String endTime,
                               @Query("pg") int page);
}