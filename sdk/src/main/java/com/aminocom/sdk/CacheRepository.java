package com.aminocom.sdk;

import com.aminocom.sdk.model.client.channel.Channel;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.Program;

import java.util.List;

import io.reactivex.Observable;

class CacheRepository implements LocalRepository {
    private ObservableList<Channel> channels = new ObservableList<>();
    private ObservableList<Program> programs = new ObservableList<>();
    private ObservableList<Epg> epgList = new ObservableList<>();
    private ObservableList<Group> groups = new ObservableList<>();

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
        this.programs.setItems(programs);
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
}