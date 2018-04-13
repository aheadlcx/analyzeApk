package com.baidu.mobstat;

import android.content.Context;

class bn implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ long e;
    final /* synthetic */ String f;
    final /* synthetic */ String g;
    final /* synthetic */ int h;
    final /* synthetic */ boolean i;
    final /* synthetic */ bm j;

    bn(bm bmVar, Context context, String str, String str2, int i, long j, String str3, String str4, int i2, boolean z) {
        this.j = bmVar;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = j;
        this.f = str3;
        this.g = str4;
        this.h = i2;
        this.i = z;
    }

    public void run() {
        bv.a().d();
        this.j.a(this.a, this.b, this.c, this.d, this.e, 0, this.f, this.g, this.h, this.i);
    }
}
