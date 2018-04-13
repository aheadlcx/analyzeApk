package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import javax.annotation.concurrent.GuardedBy;

class ListDataSource$InternalDataSubscriber implements DataSubscriber<CloseableReference<T>> {
    @GuardedBy("InternalDataSubscriber.this")
    boolean mFinished;
    final /* synthetic */ ListDataSource this$0;

    private ListDataSource$InternalDataSubscriber(ListDataSource listDataSource) {
        this.this$0 = listDataSource;
        this.mFinished = false;
    }

    private synchronized boolean tryFinish() {
        boolean z = true;
        synchronized (this) {
            if (this.mFinished) {
                z = false;
            } else {
                this.mFinished = true;
            }
        }
        return z;
    }

    public void onFailure(DataSource<CloseableReference<T>> dataSource) {
        ListDataSource.access$100(this.this$0, dataSource);
    }

    public void onCancellation(DataSource<CloseableReference<T>> dataSource) {
        ListDataSource.access$200(this.this$0);
    }

    public void onNewResult(DataSource<CloseableReference<T>> dataSource) {
        if (dataSource.isFinished() && tryFinish()) {
            ListDataSource.access$300(this.this$0);
        }
    }

    public void onProgressUpdate(DataSource<CloseableReference<T>> dataSource) {
        ListDataSource.access$400(this.this$0);
    }
}
