package com.aminocom.sdk.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.aminocom.sdk.model.client.Program;

@Database(entities = {Program.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SdkDatabase extends RoomDatabase {
    public abstract ProgramDao programDao();
}