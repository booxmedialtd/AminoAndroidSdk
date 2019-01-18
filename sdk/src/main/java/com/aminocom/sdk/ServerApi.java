package com.aminocom.sdk;

import com.aminocom.sdk.model.network.UserResponse;
import com.aminocom.sdk.model.network.category.CategoryListResponse;
import com.aminocom.sdk.model.network.category.CategoryResponse;
import com.aminocom.sdk.model.network.channel.ChannelResponse;
import com.aminocom.sdk.model.network.epg.EpgResponse;
import com.aminocom.sdk.model.network.recording.group.RecordingGroupResponse;
import com.aminocom.sdk.model.network.recording.RecordingResponse;
import com.aminocom.sdk.model.network.stream.StreamResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
                               @Query("st") long startTime,
                               @Query("et") long endTime,
                               @Query("pg") int page);

    @GET("api/user/{user}/recording/search?sort=start_date__desc")
    Single<RecordingResponse> getRecording(@Path("user") String user,
                                           @Query("service") String service,
                                           @Query("st") Long startTime,
                                           @Query("et") Long endTime,
                                           @Query("pg") int page);

    @GET("api/user/{user}/recording/favorite?sort=start_date__desc")
    Single<RecordingResponse> getFavoriteRecording(@Path("user") String user,
                                                   @Query("service") String service,
                                                   @Query("pg") int page);

    @FormUrlEncoded
    @PUT("api/user/{user}/recording/favorite")
    Completable addFavoriteRecording(@Path("user") String user,
                                     @Field("add") String programUid,
                                     @Query("service") String service);

    @FormUrlEncoded
    @PUT("api/user/{user}/recording/favorite")
    Completable removeFavoriteRecording(@Path("user") String user,
                                        @Field("remove") String programUid,
                                        @Query("service") String service);

    @GET("api/v1/channels/{channelId}/relationships/streams")
    Single<StreamResponse> getChannelStreams(@Path("channelId") String channelId, @Query("service") String service);

    @GET("api/v1/recordings/{recordingId}/relationships/streams")
    Single<StreamResponse> getRecordingStreams(@Path("recordingId") String recordingId, @Query("service") String service);

    @GET("api/v2/user/{user}/recording/group?sort=start_date__asc")
    Single<RecordingGroupResponse> getRecordingGroups(@Path("user") String user,
                                                      @Query("st") long startTime,
                                                      @Query("favorite") boolean favorite,
                                                      @Query("service") String service);

    @GET("api/v2/user/{user}/recording/group/{groupId}?sort=start_date__asc")
    Single<RecordingGroupResponse> getGroupRecordings(@Path("user") String user,
                                                         @Path("groupId") String groupId,
                                                         @Query("service") String service);
}