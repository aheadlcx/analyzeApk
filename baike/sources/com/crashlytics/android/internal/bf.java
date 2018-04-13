package com.crashlytics.android.internal;

import java.util.Map;

final class bf {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final long i;
    public final bg j;
    public final Map<String, String> k;
    private String l;

    public static final bf a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, bg bgVar, Map<String, String> map) {
        return new bf(str, str2, str3, str4, str5, str6, str7, str8, System.currentTimeMillis(), bgVar, map);
    }

    private bf(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, long j, bg bgVar, Map<String, String> map) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = j;
        this.j = bgVar;
        this.k = map;
    }

    public final String toString() {
        if (this.l == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(getClass().getSimpleName());
            stringBuilder.append(": appBundleId=");
            stringBuilder.append(this.a);
            stringBuilder.append(", executionId=");
            stringBuilder.append(this.b);
            stringBuilder.append(", installationId=");
            stringBuilder.append(this.c);
            stringBuilder.append(", androidId=");
            stringBuilder.append(this.d);
            stringBuilder.append(", osVersion=");
            stringBuilder.append(this.e);
            stringBuilder.append(", deviceModel=");
            stringBuilder.append(this.f);
            stringBuilder.append(", appVersionCode=");
            stringBuilder.append(this.g);
            stringBuilder.append(", appVersionName=");
            stringBuilder.append(this.h);
            stringBuilder.append(", timestamp=");
            stringBuilder.append(this.i);
            stringBuilder.append(", type=");
            stringBuilder.append(this.j);
            stringBuilder.append(", details=");
            stringBuilder.append(this.k.toString());
            stringBuilder.append("]");
            this.l = stringBuilder.toString();
        }
        return this.l;
    }
}
