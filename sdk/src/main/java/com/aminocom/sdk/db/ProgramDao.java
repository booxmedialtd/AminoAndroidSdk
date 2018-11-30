package com.aminocom.sdk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aminocom.sdk.model.client.Program;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ProgramDao {
    @Query("SELECT * FROM programs WHERE programUId = :programUid")
    Program get(String programUid);

    @Query("SELECT * FROM programs")
    Flowable<List<Program>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Program> programs);

    @Insert
    void insert(Program program);

    @Update
    void update(Program program);

    @Query("DELETE FROM programs")
    void clear();

    @Query("SELECT * FROM programs WHERE channelId = :channelId AND endTime > :currentTime ORDER BY startTime ASC LIMIT :limit")
    Single<List<Program>> getPendingPrograms(String channelId, long currentTime, int limit);
}