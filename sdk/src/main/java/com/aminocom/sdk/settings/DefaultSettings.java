package com.aminocom.sdk.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.aminocom.sdk.CacheTtlManager;

public class DefaultSettings implements Settings {

    private static final String SETTINGS_FILE_NAME = "com.aminocom.sdk";

    private static final String PREF_USER_NAME = "user_name";
    private static final String PREF_EPG_LOADED_TIME = "epg_loaded_time";
    private static final String PREF_CHANNEL_LOADED_TIME = "channel_loaded_time";
    private static final String PREF_CATEGORY_LOADED_TIME = "category_loaded_time";
    private static final String PREF_RECORDING_LOADED_TIME = "recording_loaded_time";
    private static final String PREF_FAVORITE_RECORDING_LOADED_TIME = "favorite_recording_loaded_time";

    private final CacheTtlManager cacheTtlManager;

    private final SharedPreferences preferences;

    public DefaultSettings(Context context, CacheTtlManager cacheTtlManager) {
        preferences = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        this.cacheTtlManager = cacheTtlManager;
    }

    @Override
    public void clear() {
        preferences.edit().clear().apply();
    }

    @Override
    public String getUserName() {
        return preferences.getString(PREF_USER_NAME, "");
    }

    @Override
    public void setUserName(String username) {
        preferences.edit().putString(PREF_USER_NAME, username).apply();
    }

    @Override
    public long getEpgLoadedTime() {
        return preferences.getLong(PREF_EPG_LOADED_TIME, 0);
    }

    @Override
    public void setEpgLoadedTime(long time) {
        preferences.edit().putLong(PREF_EPG_LOADED_TIME, time).apply();
    }

    @Override
    public long getCategoryLoadedTime() {
        return preferences.getLong(PREF_CATEGORY_LOADED_TIME, 0);
    }

    @Override
    public void setCategoryLoadedTime(long time) {
        preferences.edit().putLong(PREF_CATEGORY_LOADED_TIME, time).apply();
    }

    @Override
    public long getChannelLoadedTime() {
        return preferences.getLong(PREF_CHANNEL_LOADED_TIME, 0);
    }

    @Override
    public void setChannelLoadedTime(long time) {
        preferences.edit().putLong(PREF_CHANNEL_LOADED_TIME, time).apply();
    }

    @Override
    public long getRecordingLoadedTime() {
        return preferences.getLong(PREF_RECORDING_LOADED_TIME, 0);
    }

    @Override
    public void setRecordingLoadedTime(long time) {
        preferences.edit().putLong(PREF_RECORDING_LOADED_TIME, time).apply();
    }

    @Override
    public long getFavoriteRecordingLoadedTime() {
        return preferences.getLong(PREF_FAVORITE_RECORDING_LOADED_TIME, 0);
    }

    @Override
    public void setFavoriteRecordingLoadedTime(long time) {
        preferences.edit().putLong(PREF_FAVORITE_RECORDING_LOADED_TIME, time).apply();
    }

    @Override
    public CacheTtlManager getCacheTtlManager() {
        return cacheTtlManager;
    }
}
