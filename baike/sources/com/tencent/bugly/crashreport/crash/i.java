package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.x;

final class i implements Runnable {
    private /* synthetic */ boolean a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ String d;
    private /* synthetic */ byte[] e;
    private /* synthetic */ boolean f;
    private /* synthetic */ c g;

    i(c cVar, boolean z, Thread thread, Throwable th, String str, byte[] bArr, boolean z2) {
        this.g = cVar;
        this.a = z;
        this.b = thread;
        this.c = th;
        this.d = str;
        this.e = bArr;
        this.f = z2;
    }

    public final void run() {
        try {
            x.c("post a throwable %b", Boolean.valueOf(this.a));
            this.g.r.a(this.b, this.c, false, this.d, this.e);
            if (this.f) {
                x.a("clear user datas", new Object[0]);
                a.a(this.g.p).A();
            }
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            x.e("java catch error: %s", this.c.toString());
        }
    }
}
