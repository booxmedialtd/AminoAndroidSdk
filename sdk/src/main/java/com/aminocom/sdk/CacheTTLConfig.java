package com.aminocom.sdk;

import java.util.concurrent.TimeUnit;

public class CacheTTLConfig {
    public static final long CHANNEL_TTL = TimeUnit.MINUTES.toMillis(15);
    public static final long RECORDINGS_TTL = TimeUnit.MINUTES.toMillis(10);
    public static final long EPG_TTL = TimeUnit.DAYS.toMillis(1);
    public static final long CATEGORY_TTL = TimeUnit.MINUTES.toMillis(15);
}