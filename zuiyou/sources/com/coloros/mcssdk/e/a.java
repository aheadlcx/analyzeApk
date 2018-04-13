package com.coloros.mcssdk.e;

import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;

public class a extends c {
    String a;
    String b;
    long c;
    long d;
    int e;
    String f = "08:00-22:00";
    String g;
    int h = 0;
    int i = 0;

    public int a() {
        return InputDeviceCompat.SOURCE_TOUCHSCREEN;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(int i) {
        this.h = i;
    }

    public void b(long j) {
        this.d = j;
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
        }
    }

    public void c(int i) {
        this.i = i;
    }

    public void c(String str) {
        this.a = str;
    }

    public void d(String str) {
        this.g = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("messageID:" + this.j);
        stringBuilder.append(",taskID:" + this.l);
        stringBuilder.append(",appPackage:" + this.k);
        stringBuilder.append(",title:" + this.a);
        stringBuilder.append(",balanceTime:" + this.e);
        stringBuilder.append(",startTime:" + this.c);
        stringBuilder.append(",endTime:" + this.d);
        stringBuilder.append(",balanceTime:" + this.e);
        stringBuilder.append(",timeRanges:" + this.f);
        stringBuilder.append(",forcedDelivery:" + this.h);
        stringBuilder.append(",distinctBycontent:" + this.i);
        return stringBuilder.toString();
    }
}
