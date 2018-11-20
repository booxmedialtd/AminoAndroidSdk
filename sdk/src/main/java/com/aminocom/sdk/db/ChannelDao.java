package com.aminocom.sdk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aminocom.sdk.model.client.channel.Channel;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ChannelDao {
    @Query("SELECT * FROM channels")
    Flowable<List<Channel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Channel> channels);

    @Query("DELETE FROM channels")
    void clear();
}