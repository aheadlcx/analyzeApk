package com.tencent.bugly.proguard;

public class aw {
    public static ax a(int i) {
        if (i == 1) {
            return a();
        }
        if (i == 3) {
            return b();
        }
        return null;
    }

    private static av a() {
        return new av();
    }

    private static au b() {
        return new au();
    }
}
