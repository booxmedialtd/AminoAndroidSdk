package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.RecordingGroup;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.provider.local.LocalProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class TestLocalRepository implements LocalProvider {
    private List<Channel> channels = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    @Override
    public Flowable<List<Channel>> getChannels() {
        return Flowable.just(channels);
    }

    @Override
    public void cacheChannels(List<Channel> channels) {
        this.channels.addAll(channels);
    }

    @Override
    public void clearChannels() {
        channels.clear();
    }

    @Override
    public Flowable<List<Program>> getPrograms() {
        return null;
    }

    @Override
    public Flowable<List<Program>> getPrograms(long startDate, long endDate) {
        return null;
    }

    @Override
    public Flowable<List<Program>> getProgramsByGroup(String groupId) {
        return null;
    }

    @Override
    public Single<List<Program>> getUpcomingPrograms(String channelId, long currentTime, int limit) {
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
    public void clearEpg() {

    }

    @Override
    public Flowable<List<RecordingGroup>> getRecordingGroups() {
        return null;
    }

    @Override
    public void cacheGroups(List<RecordingGroup> groups) {
    }

    @Override
    public void clearGroups() {

    }

    @Override
    public Flowable<List<Category>> getCategories() {
        return Flowable.just(categories);
    }

    @Override
    public void cacheCategories(List<Category> categories) {
        this.categories.addAll(categories);
    }

    @Override
    public void clearCategories() {
        categories.clear();
    }
}