package com.facebook.common.b;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class b extends AbstractExecutorService {
    private static final Class<?> a = b.class;
    private final String b;
    private final Executor c;
    private volatile int d;
    private final BlockingQueue<Runnable> e;
    private final a f;
    private final AtomicInteger g;
    private final AtomicInteger h;

    private class a implements Runnable {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r5 = this;
            r0 = r5.a;	 Catch:{ all -> 0x003e }
            r0 = r0.e;	 Catch:{ all -> 0x003e }
            r0 = r0.poll();	 Catch:{ all -> 0x003e }
            r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x002d;
        L_0x000e:
            r0.run();	 Catch:{ all -> 0x003e }
        L_0x0011:
            r0 = r5.a;
            r0 = r0.g;
            r0 = r0.decrementAndGet();
            r1 = r5.a;
            r1 = r1.e;
            r1 = r1.isEmpty();
            if (r1 != 0) goto L_0x005b;
        L_0x0027:
            r0 = r5.a;
            r0.b();
        L_0x002c:
            return;
        L_0x002d:
            r0 = com.facebook.common.b.b.a;	 Catch:{ all -> 0x003e }
            r1 = "%s: Worker has nothing to run";
            r2 = r5.a;	 Catch:{ all -> 0x003e }
            r2 = r2.b;	 Catch:{ all -> 0x003e }
            com.facebook.common.c.a.a(r0, r1, r2);	 Catch:{ all -> 0x003e }
            goto L_0x0011;
        L_0x003e:
            r0 = move-exception;
            r1 = r5.a;
            r1 = r1.g;
            r1 = r1.decrementAndGet();
            r2 = r5.a;
            r2 = r2.e;
            r2 = r2.isEmpty();
            if (r2 != 0) goto L_0x0070;
        L_0x0055:
            r1 = r5.a;
            r1.b();
        L_0x005a:
            throw r0;
        L_0x005b:
            r1 = com.facebook.common.b.b.a;
            r2 = "%s: worker finished; %d workers left";
            r3 = r5.a;
            r3 = r3.b;
            r0 = java.lang.Integer.valueOf(r0);
            com.facebook.common.c.a.a(r1, r2, r3, r0);
            goto L_0x002c;
        L_0x0070:
            r2 = com.facebook.common.b.b.a;
            r3 = "%s: worker finished; %d workers left";
            r4 = r5.a;
            r4 = r4.b;
            r1 = java.lang.Integer.valueOf(r1);
            com.facebook.common.c.a.a(r2, r3, r4, r1);
            goto L_0x005a;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.b.b.a.run():void");
        }
    }

    public b(String str, int i, Executor executor, BlockingQueue<Runnable> blockingQueue) {
        if (i <= 0) {
            throw new IllegalArgumentException("max concurrency must be > 0");
        }
        this.b = str;
        this.c = executor;
        this.d = i;
        this.e = blockingQueue;
        this.f = new a();
        this.g = new AtomicInteger(0);
        this.h = new AtomicInteger(0);
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable parameter is null");
        } else if (this.e.offer(runnable)) {
            int size = this.e.size();
            int i = this.h.get();
            if (size > i && this.h.compareAndSet(i, size)) {
                com.facebook.common.c.a.a(a, "%s: max pending work in queue = %d", this.b, Integer.valueOf(size));
            }
            b();
        } else {
            throw new RejectedExecutionException(this.b + " queue is full, size=" + this.e.size());
        }
    }

    private void b() {
        int i = this.g.get();
        while (i < this.d) {
            int i2 = i + 1;
            if (this.g.compareAndSet(i, i2)) {
                com.facebook.common.c.a.a(a, "%s: starting worker %d of %d", this.b, Integer.valueOf(i2), Integer.valueOf(this.d));
                this.c.execute(this.f);
                return;
            }
            com.facebook.common.c.a.a(a, "%s: race in startWorkerIfNeeded; retrying", this.b);
            i = this.g.get();
        }
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }
}
