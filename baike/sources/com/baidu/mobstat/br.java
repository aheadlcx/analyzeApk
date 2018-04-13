package com.baidu.mobstat;

import android.content.Context;
import java.util.Map;

class br implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ ExtraInfo e;
    final /* synthetic */ Map f;
    final /* synthetic */ bm g;

    br(bm bmVar, long j, Context context, String str, String str2, ExtraInfo extraInfo, Map map) {
        this.g = bmVar;
        this.a = j;
        this.b = context;
        this.c = str;
        this.d = str2;
        this.e = extraInfo;
        this.f = map;
    }

    public void run() {
        bv.a().d();
        if (this.a <= 0) {
            db.a("EventStat: Wrong Case, Duration must be positive");
        } else {
            this.g.a(this.b, this.c, this.d, 1, System.currentTimeMillis(), this.a, this.e, this.f);
        }
    }
}
