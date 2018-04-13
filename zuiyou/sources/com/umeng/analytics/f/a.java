package com.umeng.analytics.f;

public enum a {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);
    
    private final int d;

    private a(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public static a a(int i) {
        switch (i) {
            case 0:
                return MALE;
            case 1:
                return FEMALE;
            case 2:
                return UNKNOWN;
            default:
                return null;
        }
    }
}
