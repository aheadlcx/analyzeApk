package com.facebook.datasource;

import com.facebook.datasource.DataSources.ValueHolder;
import java.util.concurrent.CountDownLatch;

class DataSources$2 implements DataSubscriber<T> {
    final /* synthetic */ CountDownLatch val$latch;
    final /* synthetic */ ValueHolder val$pendingException;
    final /* synthetic */ ValueHolder val$resultHolder;

    DataSources$2(ValueHolder valueHolder, CountDownLatch countDownLatch, ValueHolder valueHolder2) {
        this.val$resultHolder = valueHolder;
        this.val$latch = countDownLatch;
        this.val$pendingException = valueHolder2;
    }

    public void onNewResult(DataSource<T> dataSource) {
        if (dataSource.isFinished()) {
            try {
                this.val$resultHolder.value = dataSource.getResult();
            } finally {
                this.val$latch.countDown();
            }
        }
    }

    public void onFailure(DataSource<T> dataSource) {
        try {
            this.val$pendingException.value = dataSource.getFailureCause();
        } finally {
            this.val$latch.countDown();
        }
    }

    public void onCancellation(DataSource<T> dataSource) {
        this.val$latch.countDown();
    }

    public void onProgressUpdate(DataSource<T> dataSource) {
    }
}
