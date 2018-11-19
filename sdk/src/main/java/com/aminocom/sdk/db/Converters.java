package com.aminocom.sdk.db;

import android.arch.persistence.room.TypeConverter;

import com.aminocom.sdk.model.client.ExternalInformation;
import com.aminocom.sdk.model.client.ServiceState;
import com.aminocom.sdk.model.client.Thumbnail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public static String fromStringList(List<String> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static List<String> toStringList(String value) {
        Type type = new TypeToken<List<String>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromThumbnail(List<Thumbnail> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static List<Thumbnail> toThumbnail(String value) {
        Type type = new TypeToken<List<Thumbnail>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromServiceState(ServiceState value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static ServiceState toServiceState(String value) {
        return new Gson().fromJson(value, ServiceState.class);
    }

    @TypeConverter
    public static String fromExternalInformation(List<ExternalInformation> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static List<ExternalInformation> toExternalInformation(String value) {
        Type type = new TypeToken<List<ExternalInformation>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }
}