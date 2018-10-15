package com.aminocom.sdk;

import com.aminocom.sdk.model.network.ChannelResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ServerApi {
    // TODO: Add service parameter to interceptor
    @GET("api/user/{user}/recording/search?sort=start_date__desc")
    Single<ChannelResponse> getChannels(@Path("user") String user,
                                        @Query("service") String service);
}