package com.crashlytics.android.internal;

public enum ai {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    
    private final int e;

    private ai(int i) {
        this.e = i;
    }

    public final int a() {
        return this.e;
    }

    public final String toString() {
        return Integer.toString(this.e);
    }

    public static ai a(String str) {
        if ((str != null ? 1 : null) != null) {
            return APP_STORE;
        }
        return DEVELOPER;
    }
}
