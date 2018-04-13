package com.xiaomi.channel.commonutils.misc;

import com.xiaomi.channel.commonutils.misc.f.a;

class g extends b {
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    g(f fVar, a aVar, String str) {
        this.c = fVar;
        this.b = str;
        super(aVar);
    }

    void a() {
        super.a();
    }

    void b() {
        this.c.e.edit().putLong(this.b, System.currentTimeMillis()).commit();
    }
}
