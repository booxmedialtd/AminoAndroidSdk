package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Observable;

public interface LocalRepository {
    Observable<List<Channel>> getChannels();

    void cacheChannels(List<Channel> channels);

    Observable<List<Program>> getPrograms();

    void cachePrograms(List<Program> programs);

    void clearPrograms();

    Observable<List<Epg>> getEpg();

    void cacheEpg(List<Epg> epgList);

    Observable<List<Group>> getGroup();

    void cacheGroups(List<Group> groups);

    Observable<List<Category>> getCategories();

    void cacheCategories(List<Category> categories);
}