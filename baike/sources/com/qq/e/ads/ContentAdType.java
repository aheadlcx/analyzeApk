package com.qq.e.ads;

import com.umeng.commonsdk.proguard.g;

public enum ContentAdType {
    AD,
    INFORMATION;

    public static ContentAdType fromString(String str) {
        return g.an.equals(str) ? AD : "information".equals(str) ? INFORMATION : null;
    }
}
