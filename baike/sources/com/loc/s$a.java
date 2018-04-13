package com.loc;

import cz.msebera.android.httpclient.client.config.CookieSpecs;

public class s$a {
    private String a;
    private String b;
    private String c;
    private String d;
    private boolean e = true;
    private String f = CookieSpecs.STANDARD;
    private String[] g = null;

    public s$a(String str, String str2, String str3) {
        this.a = str2;
        this.b = str2;
        this.d = str3;
        this.c = str;
    }

    public final s$a a(String str) {
        this.b = str;
        return this;
    }

    public final s$a a(String[] strArr) {
        this.g = (String[]) strArr.clone();
        return this;
    }

    public final s a() throws j {
        if (this.g != null) {
            return new s(this, (byte) 0);
        }
        throw new j("sdk packages is null");
    }
}
