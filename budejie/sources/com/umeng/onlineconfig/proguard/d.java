package com.umeng.onlineconfig.proguard;

import org.json.JSONObject;

public abstract class d {
    protected static String b = "POST";
    protected static String c = "GET";
    protected String d;

    public abstract JSONObject a();

    public abstract String b();

    protected String c() {
        return b;
    }

    public d(String str) {
        this.d = str;
    }

    public void a(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }
}
