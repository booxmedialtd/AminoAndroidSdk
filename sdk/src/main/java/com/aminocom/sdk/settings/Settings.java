package com.aminocom.sdk.settings;

import com.aminocom.sdk.CacheTtlManager;

public interface Settings {

    void clear();

    String getUserName();

    void setUserName(String username);

    long getLastLoadedEpgDay();

    void setLastLoadedEpgDay(long time);

    CacheTtlManager getCacheTtlManager();
}