package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class IncreasingQualityDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    private final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    @ThreadSafe
    private class IncreasingQualityDataSource extends AbstractDataSource<T> {
        @GuardedBy("IncreasingQualityDataSource.this")
        @Nullable
        private ArrayList<DataSource<T>> mDataSources;
        @Nullable
        private Throwable mDelayedError;
        private final AtomicInteger mFinishedDataSources = new AtomicInteger(0);
        @GuardedBy("IncreasingQualityDataSource.this")
        private int mIndexOfDataSourceWithResult;
        private final int mNumberOfDataSources;

        private class InternalDataSubscriber implements DataSubscriber<T> {
            private int mIndex;

            public InternalDataSubscriber(int i) {
                this.mIndex = i;
            }

            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    IncreasingQualityDataSource.this.onDataSourceNewResult(this.mIndex, dataSource);
                } else if (dataSource.isFinished()) {
                    IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
                }
            }

            public void onFailure(DataSource<T> dataSource) {
                IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
            }

            public void onCancellation(DataSource<T> dataSource) {
            }

            public void onProgressUpdate(DataSource<T> dataSource) {
                if (this.mIndex == 0) {
                    IncreasingQualityDataSource.this.setProgress(dataSource.getProgress());
                }
            }
        }

        public IncreasingQualityDataSource() {
            int size = IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.size();
            this.mNumberOfDataSources = size;
            this.mIndexOfDataSourceWithResult = size;
            this.mDataSources = new ArrayList(size);
            int i = 0;
            while (i < size) {
                DataSource dataSource = (DataSource) ((Supplier) IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.get(i)).get();
                this.mDataSources.add(dataSource);
                dataSource.subscribe(new InternalDataSubscriber(i), CallerThreadExecutor.getInstance());
                if (!dataSource.hasResult()) {
                    i++;
                } else {
                    return;
                }
            }
        }

        @Nullable
        private synchronized DataSource<T> getDataSource(int i) {
            DataSource<T> dataSource;
            dataSource = (this.mDataSources == null || i >= this.mDataSources.size()) ? null : (DataSource) this.mDataSources.get(i);
            return dataSource;
        }

        @Nullable
        private synchronized DataSource<T> getAndClearDataSource(int i) {
            DataSource<T> dataSource = null;
            synchronized (this) {
                if (this.mDataSources != null && i < this.mDataSources.size()) {
                    dataSource = (DataSource) this.mDataSources.set(i, null);
                }
            }
            return dataSource;
        }

        @Nullable
        private synchronized DataSource<T> getDataSourceWithResult() {
            return getDataSource(this.mIndexOfDataSourceWithResult);
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

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean close() {
            /*
            r3 = this;
            r0 = 0;
            monitor-enter(r3);
            r1 = super.close();	 Catch:{ all -> 0x0026 }
            if (r1 != 0) goto L_0x000a;
        L_0x0008:
            monitor-exit(r3);	 Catch:{ all -> 0x0026 }
        L_0x0009:
            return r0;
        L_0x000a:
            r2 = r3.mDataSources;	 Catch:{ all -> 0x0026 }
            r1 = 0;
            r3.mDataSources = r1;	 Catch:{ all -> 0x0026 }
            monitor-exit(r3);	 Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x0029;
        L_0x0012:
            r1 = r0;
        L_0x0013:
            r0 = r2.size();
            if (r1 >= r0) goto L_0x0029;
        L_0x0019:
            r0 = r2.get(r1);
            r0 = (com.facebook.datasource.DataSource) r0;
            r3.closeSafely(r0);
            r0 = r1 + 1;
            r1 = r0;
            goto L_0x0013;
        L_0x0026:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0026 }
            throw r0;
        L_0x0029:
            r0 = 1;
            goto L_0x0009;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.close():boolean");
        }

        private void onDataSourceNewResult(int i, DataSource<T> dataSource) {
            maybeSetIndexOfDataSourceWithResult(i, dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                boolean z = i == 0 && dataSource.isFinished();
                setResult(null, z);
            }
            maybeSetFailure();
        }

        private void onDataSourceFailed(int i, DataSource<T> dataSource) {
            closeSafely(tryGetAndClearDataSource(i, dataSource));
            if (i == 0) {
                this.mDelayedError = dataSource.getFailureCause();
            }
            maybeSetFailure();
        }

        private void maybeSetFailure() {
            if (this.mFinishedDataSources.incrementAndGet() == this.mNumberOfDataSources && this.mDelayedError != null) {
                setFailure(this.mDelayedError);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void maybeSetIndexOfDataSourceWithResult(int r4, com.facebook.datasource.DataSource<T> r5, boolean r6) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002c }
            r1 = r3.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002c }
            r2 = r3.getDataSource(r4);	 Catch:{ all -> 0x002c }
            if (r5 != r2) goto L_0x000f;
        L_0x000b:
            r2 = r3.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002c }
            if (r4 != r2) goto L_0x0011;
        L_0x000f:
            monitor-exit(r3);	 Catch:{ all -> 0x002c }
        L_0x0010:
            return;
        L_0x0011:
            r2 = r3.getDataSourceWithResult();	 Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x001d;
        L_0x0017:
            if (r6 == 0) goto L_0x002f;
        L_0x0019:
            r2 = r3.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002c }
            if (r4 >= r2) goto L_0x002f;
        L_0x001d:
            r3.mIndexOfDataSourceWithResult = r4;	 Catch:{ all -> 0x002c }
        L_0x001f:
            monitor-exit(r3);	 Catch:{ all -> 0x002c }
        L_0x0020:
            if (r0 <= r4) goto L_0x0010;
        L_0x0022:
            r1 = r3.getAndClearDataSource(r0);
            r3.closeSafely(r1);
            r0 = r0 + -1;
            goto L_0x0020;
        L_0x002c:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x002c }
            throw r0;
        L_0x002f:
            r4 = r1;
            goto L_0x001f;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.maybeSetIndexOfDataSourceWithResult(int, com.facebook.datasource.DataSource, boolean):void");
        }

        @Nullable
        private synchronized DataSource<T> tryGetAndClearDataSource(int i, DataSource<T> dataSource) {
            if (dataSource == getDataSourceWithResult()) {
                dataSource = null;
            } else if (dataSource == getDataSource(i)) {
                dataSource = getAndClearDataSource(i);
            }
            return dataSource;
        }

        private void closeSafely(DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }

    private IncreasingQualityDataSourceSupplier(List<Supplier<DataSource<T>>> list) {
        Preconditions.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
    }

    public static <T> IncreasingQualityDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> list) {
        return new IncreasingQualityDataSourceSupplier(list);
    }

    public DataSource<T> get() {
        return new IncreasingQualityDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IncreasingQualityDataSourceSupplier)) {
            return false;
        }
        return Objects.equal(this.mDataSourceSuppliers, ((IncreasingQualityDataSourceSupplier) obj).mDataSourceSuppliers);
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("list", this.mDataSourceSuppliers).toString();
    }
}
