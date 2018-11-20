package com.aminocom.sdk.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.client.channel.Channel;

@Database(entities = {Program.class, Channel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SdkDatabase extends RoomDatabase {
    public abstract ProgramDao programDao();

    public abstract ChannelDao channelDao();
}