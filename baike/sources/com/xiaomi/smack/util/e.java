package com.xiaomi.smack.util;

import com.xiaomi.channel.commonutils.misc.h;
import com.xiaomi.channel.commonutils.misc.h.b;

public class e {
    private static h a = new h(true, 20);

    public static void a(b bVar) {
        a.a(bVar);
    }

    public static void a(b bVar, long j) {
        a.a(bVar, j);
    }

    public static void a(Runnable runnable) {
        a.a(new f(runnable));
    }
}
