package com.alibaba.mtl.log.d;

import com.alibaba.mtl.log.e.b;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.r;
import java.util.Random;

public class a {
    static a a = new a();
    private int A;
    private boolean E = false;
    protected long z = com.alibaba.mtl.log.a.a.a();

    public static a a() {
        return a;
    }

    public synchronized void start() {
        this.E = true;
        if (r.a().b(2)) {
            r.a().f(2);
        }
        c();
        Random random = new Random();
        if (!b.isRunning()) {
            r.a().a(2, new b(this) {
                final /* synthetic */ a b;

                {
                    this.b = r1;
                }

                public void K() {
                    if (this.b.c()) {
                        com.alibaba.mtl.log.b.a.E();
                        this.b.c();
                        i.a("UploadTask", "mPeriod:", Long.valueOf(this.b.z));
                        if (r.a().b(2)) {
                            r.a().f(2);
                        }
                        if (!b.isRunning()) {
                            r.a().a(2, this, this.b.z);
                        }
                    }
                }

                public void L() {
                    this.b.J();
                }
            }, (long) random.nextInt((int) this.z));
        }
    }

    public void J() {
        if (this.A == 0) {
            this.A = 7000;
        } else {
            this.A = 0;
        }
    }

    public synchronized void stop() {
        this.E = false;
        r.a().f(2);
    }

    private long c() {
        long b;
        boolean z = true;
        i.a("UploadEngine", "UTDC.bBackground:", Boolean.valueOf(com.alibaba.mtl.log.a.o), "AppInfoUtil.isForeground(UTDC.getContext()) ", Boolean.valueOf(b.b(com.alibaba.mtl.log.a.getContext())));
        if (b.b(com.alibaba.mtl.log.a.getContext())) {
            z = false;
        }
        com.alibaba.mtl.log.a.o = z;
        z = com.alibaba.mtl.log.a.o;
        com.alibaba.mtl.log.a.a.a();
        if (z) {
            b = com.alibaba.mtl.log.a.a.b() + ((long) this.A);
        } else {
            b = com.alibaba.mtl.log.a.a.a() + ((long) this.A);
        }
        this.z = b;
        if (com.alibaba.mtl.log.a.a.g()) {
            this.z = 3000;
        }
        return this.z;
    }
}
