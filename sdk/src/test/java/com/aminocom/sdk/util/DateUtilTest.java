package com.aminocom.sdk.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {
    @Test
    public void getTimeInSeconds() {
        long time = 1542275349000L; // November 15, 2018 11:49:09
        long expected = 1542275349L; // November 15, 2018 11:49:09

        long timeInSeconds = DateUtil.getTimeInSeconds(time);

        assertEquals(expected, timeInSeconds);
    }

    @Test
    public void getTvDayStartTime() {
        long time = 1542275349000L; // November 15, 2018 11:49:09
        long expected = 1542232800000L; // November 15, 2018 00:00:00

        long startTime = DateUtil.getTvDayStartTime(time);

        assertEquals(expected, startTime);
    }

    @Test
    public void getTvDayStartTime_Midnight() {
        long time = 1542232800000L; // November 15, 2018 00:00:00
        long expected = 1542232800000L; // November 15, 2018 00:00:00

        long startTime = DateUtil.getTvDayStartTime(time);

        assertEquals(expected, startTime);
    }

    @Test
    public void getTvDayStartTime_AlmostNextDay() {
        long time = 1542319199000L; // November 15, 2018 23:59:59
        long expected = 1542232800000L; // November 15, 2018 00:00:00

        long startTime = DateUtil.getTvDayStartTime(time);

        assertEquals(expected, startTime);
    }

    @Test
    public void getTvDayEndTime() {
        long time = 1542275349000L; // November 15, 2018 11:49:09
        long expected = 1542319199000L; // November 15, 2018 23:59:59

        long startTime = DateUtil.getTvDayEndTime(time);

        assertEquals(expected, startTime);
    }

    @Test
    public void getTvDayEndTime_Midnight() {
        long time = 1542232800000L; // November 15, 2018 00:00:00
        long expected = 1542319199000L; // November 15, 2018 23:59:59

        long startTime = DateUtil.getTvDayEndTime(time);

        assertEquals(expected, startTime);
    }

    @Test
    public void getTvDayEndTime_AlmostNextDay() {
        long time = 1542319199000L; // November 15, 2018 23:59:59
        long expected = 1542319199000L; // November 15, 2018 23:59:59

        long startTime = DateUtil.getTvDayEndTime(time);

        assertEquals(expected, startTime);
    }
}