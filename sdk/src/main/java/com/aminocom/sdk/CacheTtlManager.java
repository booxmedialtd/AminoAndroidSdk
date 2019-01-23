package com.aminocom.sdk;

public interface CacheTtlManager {
    long getRecordingTtl();

    long gerChannelTtl();

    long getCategoryTtl();

    long getEpgTtl();
}