package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.RecordingGroup;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface LocalRepository {
    Flowable<List<Channel>> getChannels();

    void cacheChannels(List<Channel> channels);

    void clearChannels();

    Flowable<List<Program>> getPrograms();

    Flowable<List<Program>> getPrograms(long startDate, long endDate);

    Flowable<List<Program>> getProgramsByGroup(String groupId);

    Single<List<Program>> getUpcomingPrograms(String channelId, long currentTime, int limit);

    void cachePrograms(List<Program> programs);

    void updateOrInsertPrograms(List<Program> programs);

    void clearPrograms();

    Flowable<List<Program>> getEpgPrograms(String channelId, long startDate, long endDate);

    void cacheEpg(List<Epg> epgList);

    void clearEpg();

    Flowable<List<RecordingGroup>> getRecordingGroups();

    void cacheGroups(List<RecordingGroup> groups);

    void clearGroups();

    Flowable<List<Category>> getCategories();

    void cacheCategories(List<Category> categories);

    void clearCategories();
}