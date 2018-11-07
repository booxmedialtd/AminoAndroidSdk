package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.CategoryProgram;

import java.util.List;

import io.reactivex.Observable;

public interface LocalRepository {
    Observable<List<Channel>> getChannels();

    void cacheChannels(List<Channel> channels);

    Observable<List<CategoryProgram>> getPrograms();

    void cachePrograms(List<CategoryProgram> programs);

    Observable<List<Epg>> getEpg();

    void cacheEpg(List<Epg> epgList);

    Observable<List<Group>> getGroup();

    void cacheGroups(List<Group> groups);

    Observable<List<Category>> getCategories();

    void cacheCategories(List<Category> categories);
}