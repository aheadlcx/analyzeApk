package com.xiaomi.smack.util;

import android.content.Context;
import com.xiaomi.channel.commonutils.misc.h.b;
import java.util.ArrayList;
import java.util.List;

final class h extends b {
    final /* synthetic */ Context a;

    h(Context context) {
        this.a = context;
    }

    public void b() {
        List arrayList;
        synchronized (g.c) {
            arrayList = new ArrayList(g.d);
            g.d.clear();
        }
        g.b(this.a, arrayList);
    }
}
