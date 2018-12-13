package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class TestLocalRepository implements LocalRepository {
    private List<Channel> channels = new ArrayList<>();

    @Override
    public Flowable<List<Channel>> getChannels() {
        return Flowable.just(channels);
    }

    @Override
    public void cacheChannels(List<Channel> channels) {
        this.channels.addAll(channels);
    }

    @Override
    public Flowable<List<Program>> getPrograms() {
        return null;
    }

    @Override
    public Single<List<Program>> getPendingPrograms(String channelId, long currentTime, int limit) {
        return null;
    }

    @Override
    public void cachePrograms(List<Program> programs) {
    }

    @Override
    public void updateOrInsertPrograms(List<Program> programs) {
    }

    @Override
    public void clearPrograms() {
    }

    @Override
    public Flowable<List<Program>> getEpgPrograms(String channelId, long startDate, long endDate) {
        return null;
    }

    @Override
    public void cacheEpg(List<Epg> epgList) {
    }

    @Override
    public Flowable<List<Group>> getGroup() {
        return null;
    }

    @Override
    public void cacheGroups(List<Group> groups) {
    }

    @Override
    public Flowable<List<Category>> getCategories() {
        return null;
    }

    @Override
    public void cacheCategories(List<Category> categories) {
    }
}