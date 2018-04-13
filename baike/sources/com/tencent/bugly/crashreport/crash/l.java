package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.proguard.x;
import java.util.Map;

final class l implements Runnable {
    private /* synthetic */ Thread a;
    private /* synthetic */ int b;
    private /* synthetic */ String c;
    private /* synthetic */ String d;
    private /* synthetic */ String e;
    private /* synthetic */ Map f;

    l(Thread thread, int i, String str, String str2, String str3, Map map) {
        this.a = thread;
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = map;
    }

    public final void run() {
        try {
            if (d.a == null) {
                x.e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
            } else {
                d.a(d.a, this.a, this.b, this.c, this.d, this.e, this.f);
            }
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            x.e("[ExtraCrashManager] Crash error %s %s %s", this.c, this.d, this.e);
        }
    }
}
