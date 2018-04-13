package com.spriteapp.booklibrary.util;

public class TimeUtil {
    private static final String TAG = "TimeUtil";

    public static int getTimeInterval(long j) {
        return (int) (((System.currentTimeMillis() / 1000) - j) / 3600);
    }
}
