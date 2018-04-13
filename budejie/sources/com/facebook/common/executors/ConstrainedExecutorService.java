package com.facebook.common.executors;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConstrainedExecutorService extends AbstractExecutorService {
    private static final Class<?> TAG = ConstrainedExecutorService.class;
    private final Executor mExecutor;
    private volatile int mMaxConcurrency;
    private final AtomicInteger mMaxQueueSize;
    private final String mName;
    private final AtomicInteger mPendingWorkers;
    private final Worker mTaskRunner;
    private final BlockingQueue<Runnable> mWorkQueue;

    private class Worker implements Runnable {
        private Worker() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r5 = this;
            r0 = com.facebook.common.executors.ConstrainedExecutorService.this;	 Catch:{ all -> 0x003d }
            r0 = r0.mWorkQueue;	 Catch:{ all -> 0x003d }
            r0 = r0.poll();	 Catch:{ all -> 0x003d }
            r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x002d;
        L_0x000e:
            r0.run();	 Catch:{ all -> 0x003d }
        L_0x0011:
            r0 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r0 = r0.mPendingWorkers;
            r0 = r0.decrementAndGet();
            r1 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r1 = r1.mWorkQueue;
            r1 = r1.isEmpty();
            if (r1 != 0) goto L_0x005a;
        L_0x0027:
            r0 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r0.startWorkerIfNeeded();
        L_0x002c:
            return;
        L_0x002d:
            r0 = com.facebook.common.executors.ConstrainedExecutorService.TAG;	 Catch:{ all -> 0x003d }
            r1 = "%s: Worker has nothing to run";
            r2 = com.facebook.common.executors.ConstrainedExecutorService.this;	 Catch:{ all -> 0x003d }
            r2 = r2.mName;	 Catch:{ all -> 0x003d }
            com.facebook.common.logging.FLog.v(r0, r1, r2);	 Catch:{ all -> 0x003d }
            goto L_0x0011;
        L_0x003d:
            r0 = move-exception;
            r1 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r1 = r1.mPendingWorkers;
            r1 = r1.decrementAndGet();
            r2 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r2 = r2.mWorkQueue;
            r2 = r2.isEmpty();
            if (r2 != 0) goto L_0x006e;
        L_0x0054:
            r1 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r1.startWorkerIfNeeded();
        L_0x0059:
            throw r0;
        L_0x005a:
            r1 = com.facebook.common.executors.ConstrainedExecutorService.TAG;
            r2 = "%s: worker finished; %d workers left";
            r3 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r3 = r3.mName;
            r0 = java.lang.Integer.valueOf(r0);
            com.facebook.common.logging.FLog.v(r1, r2, r3, r0);
            goto L_0x002c;
        L_0x006e:
            r2 = com.facebook.common.executors.ConstrainedExecutorService.TAG;
            r3 = "%s: worker finished; %d workers left";
            r4 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r4 = r4.mName;
            r1 = java.lang.Integer.valueOf(r1);
            com.facebook.common.logging.FLog.v(r2, r3, r4, r1);
            goto L_0x0059;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.executors.ConstrainedExecutorService.Worker.run():void");
        }
    }

    public ConstrainedExecutorService(String str, int i, Executor executor, BlockingQueue<Runnable> blockingQueue) {
        if (i <= 0) {
            throw new IllegalArgumentException("max concurrency must be > 0");
        }
        this.mName = str;
        this.mExecutor = executor;
        this.mMaxConcurrency = i;
        this.mWorkQueue = blockingQueue;
        this.mTaskRunner = new Worker();
        this.mPendingWorkers = new AtomicInteger(0);
        this.mMaxQueueSize = new AtomicInteger(0);
    }

    public static ConstrainedExecutorService newConstrainedExecutor(String str, int i, int i2, Executor executor) {
        return new ConstrainedExecutorService(str, i, executor, new LinkedBlockingQueue(i2));
    }

    public boolean isIdle() {
        return this.mWorkQueue.isEmpty() && this.mPendingWorkers.get() == 0;
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable parameter is null");
        } else if (this.mWorkQueue.offer(runnable)) {
            int size = this.mWorkQueue.size();
            int i = this.mMaxQueueSize.get();
            if (size > i && this.mMaxQueueSize.compareAndSet(i, size)) {
                FLog.v(TAG, "%s: max pending work in queue = %d", this.mName, Integer.valueOf(size));
            }
            startWorkerIfNeeded();
        } else {
            throw new RejectedExecutionException(this.mName + " queue is full, size=" + this.mWorkQueue.size());
        }
    }

    private void startWorkerIfNeeded() {
        int i = this.mPendingWorkers.get();
        while (i < this.mMaxConcurrency) {
            int i2 = i + 1;
            if (this.mPendingWorkers.compareAndSet(i, i2)) {
                FLog.v(TAG, "%s: starting worker %d of %d", this.mName, Integer.valueOf(i2), Integer.valueOf(this.mMaxConcurrency));
                this.mExecutor.execute(this.mTaskRunner);
                return;
            }
            FLog.v(TAG, "%s: race in startWorkerIfNeeded; retrying", this.mName);
            i = this.mPendingWorkers.get();
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
