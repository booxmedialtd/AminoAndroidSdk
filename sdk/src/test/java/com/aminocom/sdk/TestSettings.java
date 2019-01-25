package com.aminocom.sdk;

import com.aminocom.sdk.settings.Settings;

public class TestSettings implements Settings {

    private String username = "";
    private long epgLoadedTime;

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
    public long getEpgLoadedTime() {
        return epgLoadedTime;
    }

    @Override
    public void setEpgLoadedTime(long time) {
        this.epgLoadedTime = time;
    }

    @Override
    public long getCategoryLoadedTime() {
        return 0;
    }

    @Override
    public void setCategoryLoadedTime(long time) {

    }

    @Override
    public long getChannelLoadedTime() {
        return 0;
    }

    @Override
    public void setChannelLoadedTime(long time) {

    }

    @Override
    public long getRecordingLoadedTime() {
        return 0;
    }

    @Override
    public void setRecordingLoadedTime(long time) {

    }

    @Override
    public long getFavoriteRecordingLoadedTime() {
        return 0;
    }

    @Override
    public void setFavoriteRecordingLoadedTime(long time) {

    }

    @Override
    public CacheTtlManager getCacheTtlManager() {
        return null;
    }
}
