package com.sina.weibo.sdk.auth;

import android.text.TextUtils;
import com.sina.weibo.BuildConfig;

public class WbAppInfo {
    private String a = BuildConfig.APPLICATION_ID;
    private String b = "com.sina.weibo.SSOActivity";
    private int c;

    public String getPackageName() {
        return this.a;
    }

    public String getAuthActivityName() {
        return this.b;
    }

    public int getSupportVersion() {
        return this.c;
    }

    public void setPackageName(String str) {
        this.a = str;
    }

    public void setAuthActivityName(String str) {
        this.b = str;
    }

    public void setSupportVersion(int i) {
        this.c = i;
    }

    public boolean isLegal() {
        if (TextUtils.isEmpty(this.a) || this.c <= 0) {
            return false;
        }
        return true;
    }
}
