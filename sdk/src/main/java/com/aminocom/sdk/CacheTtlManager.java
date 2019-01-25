package com.aminocom.sdk;

public interface CacheTtlManager {
    long getRecordingTtl();

    long getChannelTtl();

    long getCategoryTtl();

    long getEpgTtl();
}