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

@SuppressWarnings("WeakerAccess")
// Suppressed because Room requires public access to converter's methods
class Converters {
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
    public static String fromThumbnailList(List<Thumbnail> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static List<Thumbnail> toThumbnailList(String value) {
        Type type = new TypeToken<List<Thumbnail>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromThumbnail(Thumbnail value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static Thumbnail toThumbnail(String value) {
        return new Gson().fromJson(value, Thumbnail.class);
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

    @TypeConverter
    public static String fromFastForwardState(FastForwardState value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static FastForwardState toFastForwardState(String value) {
        return new Gson().fromJson(value, FastForwardState.class);
    }

    @TypeConverter
    public static String fromDvb(List<Dvb> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static List<Dvb> toDvb(String value) {
        Type type = new TypeToken<List<Dvb>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromOtt(Ott value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static Ott toOtt(String value) {
        return new Gson().fromJson(value, Ott.class);
    }

    @TypeConverter
    public static String fromProduct(List<Product> value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static List<Product> toProduct(String value) {
        Type type = new TypeToken<List<Product>>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromProgramStatus(ProgramStatus value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static ProgramStatus toProgramStatus(String value) {
        return new Gson().fromJson(value, ProgramStatus.class);
    }
}