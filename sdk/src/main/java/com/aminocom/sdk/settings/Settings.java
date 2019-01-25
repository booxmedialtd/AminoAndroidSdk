package com.aminocom.sdk.settings;

import com.aminocom.sdk.CacheTtlManager;

public interface Settings {

    void clear();

    String getUserName();

    void setUserName(String username);

    long getEpgLoadedTime();

    void setEpgLoadedTime(long time);

    long getCategoryLoadedTime();

    void setCategoryLoadedTime(long time);

    long getChannelLoadedTime();

    void setChannelLoadedTime(long time);

    long getRecordingLoadedTime();

    void setRecordingLoadedTime(long time);

    long getFavoriteRecordingLoadedTime();

    void setFavoriteRecordingLoadedTime(long time);

    CacheTtlManager getCacheTtlManager();
}