package com.facebook.datasource;

import com.facebook.common.internal.Supplier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class DataSources {

    /* renamed from: com.facebook.datasource.DataSources$1 */
    final class AnonymousClass1 implements Supplier<DataSource<T>> {
        final /* synthetic */ Throwable val$failure;

        AnonymousClass1(Throwable th) {
            this.val$failure = th;
        }

        public DataSource<T> get() {
            return DataSources.immediateFailedDataSource(this.val$failure);
        }
    }

    /* renamed from: com.facebook.datasource.DataSources$2 */
    final class AnonymousClass2 implements DataSubscriber<T> {
        final /* synthetic */ CountDownLatch val$latch;
        final /* synthetic */ ValueHolder val$pendingException;
        final /* synthetic */ ValueHolder val$resultHolder;

        AnonymousClass2(ValueHolder valueHolder, CountDownLatch countDownLatch, ValueHolder valueHolder2) {
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

    private static class ValueHolder<T> {
        @Nullable
        public T value;

        private ValueHolder() {
            this.value = null;
        }
    }

    private DataSources() {
    }

    public static <T> DataSource<T> immediateFailedDataSource(Throwable th) {
        DataSource create = SimpleDataSource.create();
        create.setFailure(th);
        return create;
    }

    public static <T> DataSource<T> immediateDataSource(T t) {
        DataSource create = SimpleDataSource.create();
        create.setResult(t);
        return create;
    }

    public static <T> Supplier<DataSource<T>> getFailedDataSourceSupplier(Throwable th) {
        return new AnonymousClass1(th);
    }

    @Nullable
    public static <T> T waitForFinalResult(DataSource<T> dataSource) throws Throwable {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ValueHolder valueHolder = new ValueHolder();
        ValueHolder valueHolder2 = new ValueHolder();
        dataSource.subscribe(new AnonymousClass2(valueHolder, countDownLatch, valueHolder2), new Executor() {
            public void execute(Runnable runnable) {
                runnable.run();
            }
        });
        countDownLatch.await();
        if (valueHolder2.value == null) {
            return valueHolder.value;
        }
        throw ((Throwable) valueHolder2.value);
    }
}
