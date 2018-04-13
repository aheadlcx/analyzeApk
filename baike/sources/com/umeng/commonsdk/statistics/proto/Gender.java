package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.p;

public enum Gender implements p {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);
    
    private final int a;

    private Gender(int i) {
        this.a = i;
    }

    public int getValue() {
        return this.a;
    }

    public static Gender findByValue(int i) {
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
