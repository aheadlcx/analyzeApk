package com.loc;

public class bc$a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f = "copy";

    public bc$a(String str, String str2, String str3, String str4, String str5) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    public final bc$a a(String str) {
        this.f = str;
        return this;
    }

    public final bc a() {
        return new bc(this);
    }
}
