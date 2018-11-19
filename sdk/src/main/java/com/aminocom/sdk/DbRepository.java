package com.aminocom.sdk;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.aminocom.sdk.db.SdkDatabase;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Observable;

public class DbRepository implements LocalRepository {

    private SdkDatabase db;

    public DbRepository(Context appContext) {
        this.db = Room.databaseBuilder(appContext, SdkDatabase.class, "sdk-database").build();
    }

    private ObservableList<Channel> channels = new ObservableList<>();
    private ObservableList<Program> programs = new ObservableList<>();
    private ObservableList<Epg> epgList = new ObservableList<>();
    private ObservableList<Group> groups = new ObservableList<>();
    private ObservableList<Category> categories = new ObservableList<>();

    @Override
    public Observable<List<Channel>> getChannels() {
        return channels.getObservable();
    }

    @Override
    public void cacheChannels(List<Channel> channels) {
        this.channels.setItems(channels);
    }

    @Override
    public Observable<List<Program>> getPrograms() {
        return programs.getObservable();
    }

    @Override
    public void cachePrograms(List<Program> programs) {
        db.programDao().insertAll(programs);
    }

    @Override
    public void clearPrograms() {
        db.programDao().clear();
    }

    @Override
    public Observable<List<Epg>> getEpg() {
        return epgList.getObservable();
    }

    @Override
    public void cacheEpg(List<Epg> epgList) {
        this.epgList.setItems(epgList);
    }

    @Override
    public Observable<List<Group>> getGroup() {
        return groups.getObservable();
    }

    @Override
    public void cacheGroups(List<Group> groups) {
        this.groups.setItems(groups);
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return categories.getObservable();
    }

    @Override
    public void cacheCategories(List<Category> categories) {
        this.categories.setItems(categories);
    }
}