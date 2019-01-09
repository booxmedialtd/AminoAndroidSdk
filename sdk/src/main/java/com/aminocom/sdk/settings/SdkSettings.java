package com.aminocom.sdk.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SdkSettings implements Settings {

    private static final String SETTINGS_FILE_NAME = "com.aminocom.sdk";

    private static final String PREF_USER_NAME = "user_name";
    private static final String PREF_LAST_LOADED_EPG = "last_loaded_epg";

    private final SharedPreferences preferences;

    public SdkSettings(Context context) {
        preferences = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
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
    public long getLastLoadedEpgDay() {
        return preferences.getLong(PREF_LAST_LOADED_EPG, 0);
    }

    @Override
    public void setLastLoadedEpgDay(long time) {
        preferences.edit().putLong(PREF_USER_NAME, time).apply();
    }
}
