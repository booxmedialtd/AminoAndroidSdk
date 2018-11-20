package com.aminocom.sdk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aminocom.sdk.model.client.Program;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ProgramDao {
    @Query("SELECT * FROM programs")
    Flowable<List<Program>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Program> programs);

    @Query("DELETE FROM programs")
    void clear();
}