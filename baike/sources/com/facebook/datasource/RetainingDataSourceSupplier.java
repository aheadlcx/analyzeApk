package com.facebook.datasource;

import com.facebook.common.internal.Supplier;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class RetainingDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    @Nullable
    private Supplier<DataSource<T>> mCurrentDataSourceSupplier = null;
    private final Set<RetainingDataSource> mDataSources = Collections.newSetFromMap(new WeakHashMap());

    private static class RetainingDataSource<T> extends AbstractDataSource<T> {
        @GuardedBy("RetainingDataSource.this")
        @Nullable
        private DataSource<T> mDataSource;

        private class InternalDataSubscriber implements DataSubscriber<T> {
            private InternalDataSubscriber() {
            }

            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    RetainingDataSource.this.onDataSourceNewResult(dataSource);
                } else if (dataSource.isFinished()) {
                    RetainingDataSource.this.onDataSourceFailed(dataSource);
                }
            }

            public void onFailure(DataSource<T> dataSource) {
                RetainingDataSource.this.onDataSourceFailed(dataSource);
            }

            public void onCancellation(DataSource<T> dataSource) {
            }

            public void onProgressUpdate(DataSource<T> dataSource) {
                RetainingDataSource.this.onDatasourceProgress(dataSource);
            }
        }

        private RetainingDataSource() {
            this.mDataSource = null;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setSupplier(@javax.annotation.Nullable com.facebook.common.internal.Supplier<com.facebook.datasource.DataSource<T>> r5) {
            /*
            r4 = this;
            r1 = 0;
            r0 = r4.isClosed();
            if (r0 == 0) goto L_0x0008;
        L_0x0007:
            return;
        L_0x0008:
            if (r5 == 0) goto L_0x001f;
        L_0x000a:
            r0 = r5.get();
            r0 = (com.facebook.datasource.DataSource) r0;
        L_0x0010:
            monitor-enter(r4);
            r2 = r4.isClosed();	 Catch:{ all -> 0x001c }
            if (r2 == 0) goto L_0x0021;
        L_0x0017:
            closeSafely(r0);	 Catch:{ all -> 0x001c }
            monitor-exit(r4);	 Catch:{ all -> 0x001c }
            goto L_0x0007;
        L_0x001c:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x001c }
            throw r0;
        L_0x001f:
            r0 = r1;
            goto L_0x0010;
        L_0x0021:
            r2 = r4.mDataSource;	 Catch:{ all -> 0x001c }
            r4.mDataSource = r0;	 Catch:{ all -> 0x001c }
            monitor-exit(r4);	 Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0034;
        L_0x0028:
            r3 = new com.facebook.datasource.RetainingDataSourceSupplier$RetainingDataSource$InternalDataSubscriber;
            r3.<init>();
            r1 = com.facebook.common.executors.CallerThreadExecutor.getInstance();
            r0.subscribe(r3, r1);
        L_0x0034:
            closeSafely(r2);
            goto L_0x0007;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.RetainingDataSourceSupplier.RetainingDataSource.setSupplier(com.facebook.common.internal.Supplier):void");
        }

        @Nullable
        public synchronized T getResult() {
            return this.mDataSource != null ? this.mDataSource.getResult() : null;
        }

        public synchronized boolean hasResult() {
            boolean z;
            z = this.mDataSource != null && this.mDataSource.hasResult();
            return z;
        }

        public boolean close() {
            synchronized (this) {
                if (super.close()) {
                    DataSource dataSource = this.mDataSource;
                    this.mDataSource = null;
                    closeSafely(dataSource);
                    return true;
                }
                return false;
            }
        }

        private void onDataSourceNewResult(DataSource<T> dataSource) {
            if (dataSource == this.mDataSource) {
                setResult(null, false);
            }
        }

        private void onDataSourceFailed(DataSource<T> dataSource) {
        }

        private void onDatasourceProgress(DataSource<T> dataSource) {
            if (dataSource == this.mDataSource) {
                setProgress(dataSource.getProgress());
            }
        }

        private static <T> void closeSafely(DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }

    public DataSource<T> get() {
        DataSource retainingDataSource = new RetainingDataSource();
        retainingDataSource.setSupplier(this.mCurrentDataSourceSupplier);
        this.mDataSources.add(retainingDataSource);
        return retainingDataSource;
    }

    public void replaceSupplier(Supplier<DataSource<T>> supplier) {
        this.mCurrentDataSourceSupplier = supplier;
        for (RetainingDataSource retainingDataSource : this.mDataSources) {
            if (!retainingDataSource.isClosed()) {
                retainingDataSource.setSupplier(supplier);
            }
        }
    }
}
