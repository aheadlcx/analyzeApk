package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.misc.j.b;
import com.xiaomi.network.e;
import com.xiaomi.push.protobuf.a.a;

class ba extends b {
    boolean a = false;
    final /* synthetic */ az b;

    ba(az azVar) {
        this.b = azVar;
    }

    public void b() {
        try {
            a b = a.b(Base64.decode(e.a(h.a(), "http://resolver.msg.xiaomi.net/psc/?t=a", null), 10));
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
            for (az.a a : (az.a[]) this.b.b.toArray(new az.a[this.b.b.size()])) {
                a.a(this.b.c);
            }
        }
    }
}
