package com.aminocom.sdk.settings;

public interface Settings {

    void clear();

    String getUserName();

    void setUserName(String username);

    long getLastLoadedEpgDay();

    void setLastLoadedEpgDay(long time);
}