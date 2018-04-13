package com.tencent.bugly.proguard;

import com.tencent.bugly.b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class am {
    static am a;
    private ScheduledExecutorService b;

    protected am() {
        this.b = null;
        this.b = Executors.newScheduledThreadPool(3, new am$1(this));
        if (this.b == null || this.b.isShutdown()) {
            an.d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    public static synchronized am a() {
        am amVar;
        synchronized (am.class) {
            if (a == null) {
                a = new am();
            }
            amVar = a;
        }
        return amVar;
    }

    public synchronized boolean a(Runnable runnable, long j) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                an.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                an.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                if (j <= 0) {
                    j = 0;
                }
                an.c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
                try {
                    this.b.schedule(runnable, j, TimeUnit.MILLISECONDS);
                    z = true;
                } catch (Throwable th) {
                    if (b.c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public synchronized boolean a(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                an.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                an.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                an.c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
                try {
                    this.b.execute(runnable);
                    z = true;
                } catch (Throwable th) {
                    if (b.c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public synchronized void b() {
        if (!(this.b == null || this.b.isShutdown())) {
            an.c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.b.shutdownNow();
        }
    }

    public synchronized boolean c() {
        boolean z;
        z = (this.b == null || this.b.isShutdown()) ? false : true;
        return z;
    }
}
