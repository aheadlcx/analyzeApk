package com.umeng.analytics.pro;

public enum bj implements ck {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);
    
    private final int d;

    private bj(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public static bj a(int i) {
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
