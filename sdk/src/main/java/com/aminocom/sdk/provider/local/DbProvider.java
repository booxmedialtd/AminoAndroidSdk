package com.aminocom.sdk.provider.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.aminocom.sdk.db.SdkDatabase;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.RecordingGroup;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class DbProvider implements LocalProvider {

    private SdkDatabase db;

    public DbProvider(Context appContext) {
        this.db = Room.databaseBuilder(appContext, SdkDatabase.class, "sdk-database").build();
    }

    @Override
    public Flowable<List<Channel>> getChannels() {
        return db.channelDao().getAll();
    }

    @Override
    public void cacheChannels(List<Channel> channels) {
        db.channelDao().insertAll(channels);
    }

    @Override
    public void clearChannels() {
        throw new UnsupportedOperationException("To be implemented");
    }

    @Override
    public Single<List<Program>> getUpcomingPrograms(String channelId, long currentTime, int limit) {
        return db.programDao().getPendingPrograms(channelId, currentTime, limit);
    }

    @Override
    public Flowable<List<Program>> getPrograms() {
        return db.programDao().getAll();
    }

    @Override
    public Flowable<List<Program>> getPrograms(long startDate, long endDate) {
        return null;
    }

    @Override
    public Flowable<List<Program>> getProgramsByGroup(String groupId) {
        return db.programDao().getProgramsByGroup(groupId);
    }

    @Override
    public void cachePrograms(List<Program> programs) {
        db.programDao().insertAll(programs);
    }

    @Override
    public void updateOrInsertPrograms(List<Program> programs) {
        for (Program program : programs) {
            Program dbProgram = db.programDao().get(program.getProgramUId());

            if (dbProgram != null) {
                dbProgram.setFavorite(program.isFavorite());
                dbProgram.setContinuous(program.isContinuous());
                dbProgram.setAutoDelete(program.isAutoDelete());
                dbProgram.setStatus(program.getStatus());
                dbProgram.setPlaybackDuration(program.getPlaybackDuration());

                db.programDao().update(dbProgram);
            }
        }
    }

    @Override
    public void clearPrograms() {
        db.programDao().clear();
    }

    @Override
    public Flowable<List<Program>> getEpgPrograms(String channelId, long startDate, long endDate) {
        return Flowable.empty();
    }

    @Override
    public void cacheEpg(List<Epg> epgList) {
    }

    @Override
    public void clearEpg() {
        throw new UnsupportedOperationException("To be implemented");
    }

    @Override
    public Flowable<List<RecordingGroup>> getRecordingGroups() {
        return Flowable.empty();
    }

    @Override
    public void cacheGroups(List<RecordingGroup> groups) {
    }

    @Override
    public void clearGroups() {
        throw new UnsupportedOperationException("To be implemented");
    }

    @Override
    public Flowable<List<Category>> getCategories() {
        return Flowable.empty();
    }

    @Override
    public void cacheCategories(List<Category> categories) {
    }

    @Override
    public void clearCategories() {
        throw new UnsupportedOperationException("To be implemented");
    }
}