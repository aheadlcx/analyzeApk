package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Supplier;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class FirstAvailableDataSourceSupplier$FirstAvailableDataSource extends AbstractDataSource<T> {
    private DataSource<T> mCurrentDataSource = null;
    private DataSource<T> mDataSourceWithResult = null;
    private int mIndex = 0;
    final /* synthetic */ FirstAvailableDataSourceSupplier this$0;

    public FirstAvailableDataSourceSupplier$FirstAvailableDataSource(FirstAvailableDataSourceSupplier firstAvailableDataSourceSupplier) {
        this.this$0 = firstAvailableDataSourceSupplier;
        if (!startNextDataSource()) {
            setFailure(new RuntimeException("No data source supplier or supplier returned null."));
        }
    }

    @Nullable
    public synchronized T getResult() {
        DataSource dataSourceWithResult;
        dataSourceWithResult = getDataSourceWithResult();
        return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
    }

    public synchronized boolean hasResult() {
        boolean z;
        DataSource dataSourceWithResult = getDataSourceWithResult();
        z = dataSourceWithResult != null && dataSourceWithResult.hasResult();
        return z;
    }

    public boolean close() {
        synchronized (this) {
            if (super.close()) {
                DataSource dataSource = this.mCurrentDataSource;
                this.mCurrentDataSource = null;
                DataSource dataSource2 = this.mDataSourceWithResult;
                this.mDataSourceWithResult = null;
                closeSafely(dataSource2);
                closeSafely(dataSource);
                return true;
            }
            return false;
        }
    }

    private boolean startNextDataSource() {
        Supplier nextSupplier = getNextSupplier();
        DataSource dataSource = nextSupplier != null ? (DataSource) nextSupplier.get() : null;
        if (!setCurrentDataSource(dataSource) || dataSource == null) {
            closeSafely(dataSource);
            return false;
        }
        dataSource.subscribe(new FirstAvailableDataSourceSupplier$FirstAvailableDataSource$InternalDataSubscriber(this, null), CallerThreadExecutor.getInstance());
        return true;
    }

    @Nullable
    private synchronized Supplier<DataSource<T>> getNextSupplier() {
        Supplier<DataSource<T>> supplier;
        if (isClosed() || this.mIndex >= FirstAvailableDataSourceSupplier.access$100(this.this$0).size()) {
            supplier = null;
        } else {
            List access$100 = FirstAvailableDataSourceSupplier.access$100(this.this$0);
            int i = this.mIndex;
            this.mIndex = i + 1;
            supplier = (Supplier) access$100.get(i);
        }
        return supplier;
    }

    private synchronized boolean setCurrentDataSource(DataSource<T> dataSource) {
        boolean z;
        if (isClosed()) {
            z = false;
        } else {
            this.mCurrentDataSource = dataSource;
            z = true;
        }
        return z;
    }

    private synchronized boolean clearCurrentDataSource(DataSource<T> dataSource) {
        boolean z;
        if (isClosed() || dataSource != this.mCurrentDataSource) {
            z = false;
        } else {
            this.mCurrentDataSource = null;
            z = true;
        }
        return z;
    }

    @Nullable
    private synchronized DataSource<T> getDataSourceWithResult() {
        return this.mDataSourceWithResult;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeSetDataSourceWithResult(com.facebook.datasource.DataSource<T> r3, boolean r4) {
        /*
        r2 = this;
        r0 = 0;
        monitor-enter(r2);
        r1 = r2.mCurrentDataSource;	 Catch:{ all -> 0x001b }
        if (r3 != r1) goto L_0x000a;
    L_0x0006:
        r1 = r2.mDataSourceWithResult;	 Catch:{ all -> 0x001b }
        if (r3 != r1) goto L_0x000c;
    L_0x000a:
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
    L_0x000b:
        return;
    L_0x000c:
        r1 = r2.mDataSourceWithResult;	 Catch:{ all -> 0x001b }
        if (r1 == 0) goto L_0x0012;
    L_0x0010:
        if (r4 == 0) goto L_0x0016;
    L_0x0012:
        r0 = r2.mDataSourceWithResult;	 Catch:{ all -> 0x001b }
        r2.mDataSourceWithResult = r3;	 Catch:{ all -> 0x001b }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
        r2.closeSafely(r0);
        goto L_0x000b;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.FirstAvailableDataSourceSupplier$FirstAvailableDataSource.maybeSetDataSourceWithResult(com.facebook.datasource.DataSource, boolean):void");
    }

    private void onDataSourceFailed(DataSource<T> dataSource) {
        if (clearCurrentDataSource(dataSource)) {
            if (dataSource != getDataSourceWithResult()) {
                closeSafely(dataSource);
            }
            if (!startNextDataSource()) {
                setFailure(dataSource.getFailureCause());
            }
        }
    }

    private void onDataSourceNewResult(DataSource<T> dataSource) {
        maybeSetDataSourceWithResult(dataSource, dataSource.isFinished());
        if (dataSource == getDataSourceWithResult()) {
            setResult(null, dataSource.isFinished());
        }
    }

    private void closeSafely(DataSource<T> dataSource) {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
