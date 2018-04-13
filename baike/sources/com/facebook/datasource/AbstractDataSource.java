package com.facebook.datasource;

import android.util.Pair;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public abstract class AbstractDataSource<T> implements DataSource<T> {
    @GuardedBy("this")
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    @GuardedBy("this")
    private Throwable mFailureThrowable = null;
    @GuardedBy("this")
    private boolean mIsClosed = false;
    @GuardedBy("this")
    private float mProgress = 0.0f;
    @GuardedBy("this")
    @Nullable
    private T mResult = null;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue();

    private enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    protected AbstractDataSource() {
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    public synchronized float getProgress() {
        return this.mProgress;
    }

    public boolean close() {
        boolean z = true;
        synchronized (this) {
            if (this.mIsClosed) {
                z = false;
            } else {
                this.mIsClosed = true;
                Object obj = this.mResult;
                this.mResult = null;
                if (obj != null) {
                    closeResult(obj);
                }
                if (!isFinished()) {
                    notifyDataSubscribers();
                }
                synchronized (this) {
                    this.mSubscribers.clear();
                }
            }
        }
        return z;
    }

    protected void closeResult(@Nullable T t) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribe(com.facebook.datasource.DataSubscriber<T> r3, java.util.concurrent.Executor r4) {
        /*
        r2 = this;
        com.facebook.common.internal.Preconditions.checkNotNull(r3);
        com.facebook.common.internal.Preconditions.checkNotNull(r4);
        monitor-enter(r2);
        r0 = r2.mIsClosed;	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.mDataSourceStatus;	 Catch:{ all -> 0x0040 }
        r1 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS;	 Catch:{ all -> 0x0040 }
        if (r0 != r1) goto L_0x001c;
    L_0x0013:
        r0 = r2.mSubscribers;	 Catch:{ all -> 0x0040 }
        r1 = android.util.Pair.create(r3, r4);	 Catch:{ all -> 0x0040 }
        r0.add(r1);	 Catch:{ all -> 0x0040 }
    L_0x001c:
        r0 = r2.hasResult();	 Catch:{ all -> 0x0040 }
        if (r0 != 0) goto L_0x002e;
    L_0x0022:
        r0 = r2.isFinished();	 Catch:{ all -> 0x0040 }
        if (r0 != 0) goto L_0x002e;
    L_0x0028:
        r0 = r2.wasCancelled();	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x003e;
    L_0x002e:
        r0 = 1;
    L_0x002f:
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x000c;
    L_0x0032:
        r0 = r2.hasFailed();
        r1 = r2.wasCancelled();
        r2.notifyDataSubscriber(r3, r4, r0, r1);
        goto L_0x000c;
    L_0x003e:
        r0 = 0;
        goto L_0x002f;
    L_0x0040:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.subscribe(com.facebook.datasource.DataSubscriber, java.util.concurrent.Executor):void");
    }

    private void notifyDataSubscribers() {
        boolean hasFailed = hasFailed();
        boolean wasCancelled = wasCancelled();
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            notifyDataSubscriber((DataSubscriber) pair.first, (Executor) pair.second, hasFailed, wasCancelled);
        }
    }

    private void notifyDataSubscriber(final DataSubscriber<T> dataSubscriber, Executor executor, final boolean z, final boolean z2) {
        executor.execute(new Runnable() {
            public void run() {
                if (z) {
                    dataSubscriber.onFailure(AbstractDataSource.this);
                } else if (z2) {
                    dataSubscriber.onCancellation(AbstractDataSource.this);
                } else {
                    dataSubscriber.onNewResult(AbstractDataSource.this);
                }
            }
        });
    }

    private synchronized boolean wasCancelled() {
        boolean z;
        z = isClosed() && !isFinished();
        return z;
    }

    protected boolean setResult(@Nullable T t, boolean z) {
        boolean resultInternal = setResultInternal(t, z);
        if (resultInternal) {
            notifyDataSubscribers();
        }
        return resultInternal;
    }

    protected boolean setFailure(Throwable th) {
        boolean failureInternal = setFailureInternal(th);
        if (failureInternal) {
            notifyDataSubscribers();
        }
        return failureInternal;
    }

    protected boolean setProgress(float f) {
        boolean progressInternal = setProgressInternal(f);
        if (progressInternal) {
            notifyProgressUpdate();
        }
        return progressInternal;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean setResultInternal(@javax.annotation.Nullable T r4, boolean r5) {
        /*
        r3 = this;
        r1 = 0;
        monitor-enter(r3);	 Catch:{ all -> 0x003a }
        r0 = r3.mIsClosed;	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x000c;
    L_0x0006:
        r0 = r3.mDataSourceStatus;	 Catch:{ all -> 0x002f }
        r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS;	 Catch:{ all -> 0x002f }
        if (r0 == r2) goto L_0x0014;
    L_0x000c:
        r0 = 0;
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        if (r4 == 0) goto L_0x0013;
    L_0x0010:
        r3.closeResult(r4);
    L_0x0013:
        return r0;
    L_0x0014:
        if (r5 == 0) goto L_0x001e;
    L_0x0016:
        r0 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.SUCCESS;	 Catch:{ all -> 0x002f }
        r3.mDataSourceStatus = r0;	 Catch:{ all -> 0x002f }
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3.mProgress = r0;	 Catch:{ all -> 0x002f }
    L_0x001e:
        r0 = r3.mResult;	 Catch:{ all -> 0x002f }
        if (r0 == r4) goto L_0x0042;
    L_0x0022:
        r1 = r3.mResult;	 Catch:{ all -> 0x002f }
        r3.mResult = r4;	 Catch:{ all -> 0x003f }
        r4 = r1;
    L_0x0027:
        r0 = 1;
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        if (r4 == 0) goto L_0x0013;
    L_0x002b:
        r3.closeResult(r4);
        goto L_0x0013;
    L_0x002f:
        r0 = move-exception;
        r4 = r1;
    L_0x0031:
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        throw r0;	 Catch:{ all -> 0x0033 }
    L_0x0033:
        r0 = move-exception;
    L_0x0034:
        if (r4 == 0) goto L_0x0039;
    L_0x0036:
        r3.closeResult(r4);
    L_0x0039:
        throw r0;
    L_0x003a:
        r0 = move-exception;
        r4 = r1;
        goto L_0x0034;
    L_0x003d:
        r0 = move-exception;
        goto L_0x0031;
    L_0x003f:
        r0 = move-exception;
        r4 = r1;
        goto L_0x0031;
    L_0x0042:
        r4 = r1;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setResultInternal(java.lang.Object, boolean):boolean");
    }

    private synchronized boolean setFailureInternal(Throwable th) {
        boolean z;
        if (this.mIsClosed || this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS) {
            z = false;
        } else {
            this.mDataSourceStatus = DataSourceStatus.FAILURE;
            this.mFailureThrowable = th;
            z = true;
        }
        return z;
    }

    private synchronized boolean setProgressInternal(float f) {
        boolean z = false;
        synchronized (this) {
            if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                if (f >= this.mProgress) {
                    this.mProgress = f;
                    z = true;
                }
            }
        }
        return z;
    }

    protected void notifyProgressUpdate() {
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            final DataSubscriber dataSubscriber = (DataSubscriber) pair.first;
            ((Executor) pair.second).execute(new Runnable() {
                public void run() {
                    dataSubscriber.onProgressUpdate(AbstractDataSource.this);
                }
            });
        }
    }
}
