package com.alibaba.sdk.android.httpdns;

import org.json.JSONObject;

class d {
    private int b;
    private String d;

    d(int i, String str) {
        this.b = i;
        this.d = new JSONObject(str).getString("code");
    }

    public String a() {
        return this.d;
    }
}
