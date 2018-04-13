package com.baidu.mobstat;

import android.content.Context;
import java.util.Map;

class bo implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ long e;
    final /* synthetic */ ExtraInfo f;
    final /* synthetic */ Map g;
    final /* synthetic */ bm h;

    bo(bm bmVar, Context context, String str, String str2, int i, long j, ExtraInfo extraInfo, Map map) {
        this.h = bmVar;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = j;
        this.f = extraInfo;
        this.g = map;
    }

    public void run() {
        bv.a().d();
        this.h.a(this.a, this.b, this.c, this.d, this.e, 0, this.f, this.g);
    }
}
