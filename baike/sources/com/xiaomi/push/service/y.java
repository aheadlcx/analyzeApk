package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.channel.commonutils.misc.h.b;
import com.xiaomi.network.HttpUtils;
import com.xiaomi.push.protobuf.a.a;

class y extends b {
    boolean a = false;
    final /* synthetic */ at b;

    y(at atVar) {
        this.b = atVar;
    }

    public void b() {
        try {
            a b = a.b(Base64.decode(HttpUtils.a(j.a(), "http://resolver.msg.xiaomi.net/psc/?t=a", null), 10));
            if (b != null) {
                this.b.c = b;
                this.a = true;
                this.b.i();
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("fetch config failure: " + e.getMessage());
        }
    }

    public void c() {
        this.b.d = null;
        if (this.a) {
            synchronized (this.b) {
            }
            for (at.a a : (at.a[]) this.b.b.toArray(new at.a[this.b.b.size()])) {
                a.a(this.b.c);
            }
        }
    }
}
