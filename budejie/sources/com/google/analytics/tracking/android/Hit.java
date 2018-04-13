package com.google.analytics.tracking.android;

import android.text.TextUtils;

class Hit {
    private final long mHitId;
    private String mHitString;
    private final long mHitTime;
    private String mHitUrlScheme = "https:";

    String getHitParams() {
        return this.mHitString;
    }

    void setHitString(String str) {
        this.mHitString = str;
    }

    long getHitId() {
        return this.mHitId;
    }

    long getHitTime() {
        return this.mHitTime;
    }

    Hit(String str, long j, long j2) {
        this.mHitString = str;
        this.mHitId = j;
        this.mHitTime = j2;
    }

    String getHitUrlScheme() {
        return this.mHitUrlScheme;
    }

    void setHitUrl(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim()) && str.toLowerCase().startsWith("http:")) {
            this.mHitUrlScheme = "http:";
        }
    }
}
