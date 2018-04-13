package com.budejie.www.goddubbing.c;

public class c {
    private static long a = 0;

    public static boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - a <= ((long) 500);
        a = currentTimeMillis;
        return z;
    }
}
