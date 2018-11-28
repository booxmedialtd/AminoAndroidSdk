package com.aminocom.sdk.db;

import android.arch.persistence.room.TypeConverter;

import com.aminocom.sdk.model.client.ExternalInformation;
import com.aminocom.sdk.model.client.Product;
import com.aminocom.sdk.model.client.ServiceState;
import com.aminocom.sdk.model.client.Thumbnail;
import com.aminocom.sdk.model.client.channel.Dvb;
import com.aminocom.sdk.model.client.channel.FastForwardState;
import com.aminocom.sdk.model.client.channel.Ott;
import com.aminocom.sdk.model.network.ProgramStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

class Converters {
    @TypeConverter
    static String fromStringList(List<String> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static List<String> toStringList(String value) {
        Type type = new TypeToken<List<String>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    static String fromThumbnailList(List<Thumbnail> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static List<Thumbnail> toThumbnailList(String value) {
        Type type = new TypeToken<List<Thumbnail>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    static String fromThumbnail(Thumbnail value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static Thumbnail toThumbnail(String value) {
        return new Gson().fromJson(value, Thumbnail.class);
    }

    @TypeConverter
    static String fromServiceState(ServiceState value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static ServiceState toServiceState(String value) {
        return new Gson().fromJson(value, ServiceState.class);
    }

    @TypeConverter
    static String fromExternalInformation(List<ExternalInformation> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static List<ExternalInformation> toExternalInformation(String value) {
        Type type = new TypeToken<List<ExternalInformation>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    static String fromFastForwardState(FastForwardState value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static FastForwardState toFastForwardState(String value) {
        return new Gson().fromJson(value, FastForwardState.class);
    }

    @TypeConverter
    static String fromDvb(List<Dvb> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static List<Dvb> toDvb(String value) {
        Type type = new TypeToken<List<Dvb>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    static String fromOtt(Ott value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static Ott toOtt(String value) {
        return new Gson().fromJson(value, Ott.class);
    }

    @TypeConverter
    static String fromProduct(List<Product> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static List<Product> toProduct(String value) {
        Type type = new TypeToken<List<Product>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    static String fromProgramStatus(ProgramStatus value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    static ProgramStatus toProgramStatus(String value) {
        return new Gson().fromJson(value, ProgramStatus.class);
    }
}