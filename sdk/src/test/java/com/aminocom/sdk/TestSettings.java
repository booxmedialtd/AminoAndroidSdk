package com.aminocom.sdk;

import com.aminocom.sdk.settings.Settings;

public class TestSettings implements Settings {

    private String username;
    private long lastLoadedEpgDay;

    @Override
    public void clear() {

    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public void setUserName(String username) {
        this.username = username;
    }

    @Override
    public long getLastLoadedEpgDay() {
        return lastLoadedEpgDay;
    }

    @Override
    public void setLastLoadedEpgDay(long time) {
        this.lastLoadedEpgDay = time;
    }
}
