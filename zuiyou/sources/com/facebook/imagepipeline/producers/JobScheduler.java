package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.imagepipeline.g.e;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class JobScheduler {
    @GuardedBy
    e a = null;
    @GuardedBy
    boolean b = false;
    @GuardedBy
    JobState c = JobState.IDLE;
    @GuardedBy
    long d = 0;
    @GuardedBy
    long e = 0;
    private final Executor f;
    private final a g;
    private final Runnable h = new Runnable(this) {
        final /* synthetic */ JobScheduler a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.e();
        }
    };
    private final Runnable i = new Runnable(this) {
        final /* synthetic */ JobScheduler a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d();
        }
    };
    private final int j;

    enum JobState {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    public interface a {
        void a(e eVar, boolean z);
    }

    static class b {
        private static ScheduledExecutorService a;

        static ScheduledExecutorService a() {
            if (a == null) {
                a = Executors.newSingleThreadScheduledExecutor();
            }
            return a;
        }
    }

    public JobScheduler(Executor executor, a aVar, int i) {
        this.f = executor;
        this.g = aVar;
        this.j = i;
    }

    public void a() {
        e eVar;
        synchronized (this) {
            eVar = this.a;
            this.a = null;
            this.b = false;
        }
        e.d(eVar);
    }

    public boolean a(e eVar, boolean z) {
        if (!b(eVar, z)) {
            return false;
        }
        e eVar2;
        synchronized (this) {
            eVar2 = this.a;
            this.a = e.a(eVar);
            this.b = z;
        }
        e.d(eVar2);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() {
        /*
        r8 = this;
        r1 = 1;
        r0 = 0;
        r4 = android.os.SystemClock.uptimeMillis();
        r2 = 0;
        monitor-enter(r8);
        r6 = r8.a;	 Catch:{ all -> 0x0042 }
        r7 = r8.b;	 Catch:{ all -> 0x0042 }
        r6 = b(r6, r7);	 Catch:{ all -> 0x0042 }
        if (r6 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r8);	 Catch:{ all -> 0x0042 }
    L_0x0014:
        return r0;
    L_0x0015:
        r6 = com.facebook.imagepipeline.producers.JobScheduler.AnonymousClass3.a;	 Catch:{ all -> 0x0042 }
        r7 = r8.c;	 Catch:{ all -> 0x0042 }
        r7 = r7.ordinal();	 Catch:{ all -> 0x0042 }
        r6 = r6[r7];	 Catch:{ all -> 0x0042 }
        switch(r6) {
            case 1: goto L_0x002b;
            case 2: goto L_0x0022;
            case 3: goto L_0x003d;
            default: goto L_0x0022;
        };	 Catch:{ all -> 0x0042 }
    L_0x0022:
        monitor-exit(r8);	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x0029;
    L_0x0025:
        r2 = r2 - r4;
        r8.a(r2);
    L_0x0029:
        r0 = r1;
        goto L_0x0014;
    L_0x002b:
        r2 = r8.e;	 Catch:{ all -> 0x0042 }
        r0 = r8.j;	 Catch:{ all -> 0x0042 }
        r6 = (long) r0;	 Catch:{ all -> 0x0042 }
        r2 = r2 + r6;
        r2 = java.lang.Math.max(r2, r4);	 Catch:{ all -> 0x0042 }
        r8.d = r4;	 Catch:{ all -> 0x0042 }
        r0 = com.facebook.imagepipeline.producers.JobScheduler.JobState.QUEUED;	 Catch:{ all -> 0x0042 }
        r8.c = r0;	 Catch:{ all -> 0x0042 }
        r0 = r1;
        goto L_0x0022;
    L_0x003d:
        r6 = com.facebook.imagepipeline.producers.JobScheduler.JobState.RUNNING_AND_PENDING;	 Catch:{ all -> 0x0042 }
        r8.c = r6;	 Catch:{ all -> 0x0042 }
        goto L_0x0022;
    L_0x0042:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0042 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.JobScheduler.b():boolean");
    }

    private void a(long j) {
        if (j > 0) {
            b.a().schedule(this.i, j, TimeUnit.MILLISECONDS);
        } else {
            this.i.run();
        }
    }

    private void d() {
        this.f.execute(this.h);
    }

    private void e() {
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            e eVar = this.a;
            boolean z = this.b;
            this.a = null;
            this.b = false;
            this.c = JobState.RUNNING;
            this.e = uptimeMillis;
        }
        try {
            if (b(eVar, z)) {
                this.g.a(eVar, z);
            }
            e.d(eVar);
            f();
        } catch (Throwable th) {
            e.d(eVar);
            f();
        }
    }

    private void f() {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = 0;
        Object obj = null;
        synchronized (this) {
            if (this.c == JobState.RUNNING_AND_PENDING) {
                j = Math.max(this.e + ((long) this.j), uptimeMillis);
                obj = 1;
                this.d = uptimeMillis;
                this.c = JobState.QUEUED;
            } else {
                this.c = JobState.IDLE;
            }
        }
        if (obj != null) {
            a(j - uptimeMillis);
        }
    }

    private static boolean b(e eVar, boolean z) {
        return z || e.e(eVar);
    }

    public synchronized long c() {
        return this.e - this.d;
    }
}
