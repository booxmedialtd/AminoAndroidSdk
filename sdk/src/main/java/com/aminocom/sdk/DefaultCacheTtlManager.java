package com.aminocom.sdk;

import java.util.concurrent.TimeUnit;

public class DefaultCacheTtlManager implements CacheTtlManager {

    private static final long RECORDINGS_TTL = TimeUnit.MINUTES.toMillis(20);
    private static final long CHANNEL_TTL = TimeUnit.MINUTES.toMillis(15);
    private static final long CATEGORY_TTL = TimeUnit.MINUTES.toMillis(15);
    private static final long EPG_TTL = TimeUnit.DAYS.toMillis(1);

    @Override
    public long getRecordingTtl() {
        return RECORDINGS_TTL;
    }

    @Override
    public long getChannelTtl() {
        return CHANNEL_TTL;
    }

    @Override
    public long getCategoryTtl() {
        return CATEGORY_TTL;
    }

    @Override
    public long getEpgTtl() {
        return EPG_TTL;
    }
}
