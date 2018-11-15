package com.aminocom.sdk.util;

import java.util.Calendar;

public class DateUtil {
    public static long getTimeInSeconds(long time) {
        return time / 1000;
    }

    public static long getTvDayStartTime(long time) {
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(time);

        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);

        return result.getTimeInMillis();
    }

    public static long getTvDayEndTime(long time) {
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(time);

        result.set(Calendar.HOUR_OF_DAY, 23);
        result.set(Calendar.MINUTE, 59);
        result.set(Calendar.SECOND, 59);

        return result.getTimeInMillis();
    }
}