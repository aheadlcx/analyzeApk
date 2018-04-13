package com.tencent.bugly.proguard;

public class aq {
    private static as a = null;
    private static at b = null;

    public static ar a(int i) {
        if (i == 1) {
            return b();
        }
        if (i == 2) {
            return a();
        }
        return null;
    }

    public static ar a() {
        return new as();
    }

    public static ar b() {
        return new at();
    }
}
