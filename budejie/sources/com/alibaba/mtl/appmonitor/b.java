package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.a.e;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.r;

class b implements Runnable {
    private static long a = 300000;
    /* renamed from: a */
    private static b f21a;
    private static boolean j = false;

    private b() {
    }

    static void init() {
        if (!j) {
            i.a("CleanTask", "init TimeoutEventManager");
            f21a = new b();
            r.a().a(5, f21a, a);
            j = true;
        }
    }

    static void destroy() {
        r.a().f(5);
        j = false;
        f21a = null;
    }

    public void run() {
        i.a("CleanTask", "clean TimeoutEvent");
        e.a().h();
        r.a().a(5, f21a, a);
    }
}
