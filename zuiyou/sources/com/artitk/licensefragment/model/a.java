package com.artitk.licensefragment.model;

import android.content.Context;

public class a {
    private final Context a;
    private String b;
    private LicenseType c;
    private String d;
    private String e;

    public a(Context context, String str, LicenseType licenseType, String str2, String str3) {
        this.a = context;
        this.b = str;
        this.c = licenseType;
        this.d = str2;
        this.e = str3;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return String.format(b.a().a(this.a, this.c), new Object[]{this.d, this.e});
    }
}
