package com.baidu.mobstat;

import android.content.Context;
import java.util.Map;

class bq implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ Context d;
    final /* synthetic */ ExtraInfo e;
    final /* synthetic */ Map f;
    final /* synthetic */ bm g;

    bq(bm bmVar, String str, String str2, long j, Context context, ExtraInfo extraInfo, Map map) {
        this.g = bmVar;
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = context;
        this.e = extraInfo;
        this.f = map;
    }

    public void run() {
        bv.a().d();
        String a = this.g.a(this.a, this.b);
        bs bsVar = (bs) this.g.a.get(a);
        if (bsVar == null) {
            db.b("EventStat: event_id[" + this.a + "] with label[" + this.b + "] is not started or alread done.");
        } else if (this.a.equals(bsVar.a) && this.b.equals(bsVar.b)) {
            this.g.a.remove(a);
            long j = this.c - bsVar.c;
            if (j <= 0) {
                db.a("EventStat: Wrong Case, Duration must be positive");
            } else {
                this.g.a(this.d, this.a, this.b, 1, bsVar.c, j, this.e, this.f);
            }
        } else {
            db.a("EventStat: Wrong Case, eventId/label pair not match");
        }
    }
}
