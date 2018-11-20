package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Flowable;

public class CacheRepository implements LocalRepository {
    private ObservableList<Channel> channels = new ObservableList<>();
    private ObservableList<Program> programs = new ObservableList<>();
    private ObservableList<Epg> epgList = new ObservableList<>();
    private ObservableList<Group> groups = new ObservableList<>();
    private ObservableList<Category> categories = new ObservableList<>();

    @Override
    public Flowable<List<Channel>> getChannels() {
        return Flowable.empty();//channels.getObservable();
    }

    @Override
    public void cacheChannels(List<Channel> channels) {
        this.channels.setItems(channels);
    }

    @Override
    public Flowable<List<Program>> getPrograms() {
        return Flowable.empty();//programs.getObservable();
    }

    @Override
    public void cachePrograms(List<Program> programs) {
        this.programs.setItems(programs);
    }

    @Override
    public void clearPrograms() {
        this.programs = new ObservableList<>();
    }

    @Override
    public Flowable<List<Epg>> getEpg() {
        return Flowable.empty();//epgList.getObservable();
    }

    @Override
    public void cacheEpg(List<Epg> epgList) {
        this.epgList.setItems(epgList);
    }

    @Override
    public Flowable<List<Group>> getGroup() {
        return Flowable.empty();//groups.getObservable();
    }

    @Override
    public void cacheGroups(List<Group> groups) {
        this.groups.setItems(groups);
    }

    @Override
    public Flowable<List<Category>> getCategories() {
        return Flowable.empty();//categories.getObservable();
    }

    @Override
    public void cacheCategories(List<Category> categories) {
        this.categories.setItems(categories);
    }
}