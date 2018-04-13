package com.facebook.datasource;

import com.facebook.common.internal.Supplier;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

public class DataSources {

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
        return new DataSources$1(th);
    }

    @Nullable
    public static <T> T waitForFinalResult(DataSource<T> dataSource) throws Throwable {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ValueHolder valueHolder = new ValueHolder();
        ValueHolder valueHolder2 = new ValueHolder();
        dataSource.subscribe(new DataSources$2(valueHolder, countDownLatch, valueHolder2), new DataSources$3());
        countDownLatch.await();
        if (valueHolder2.value == null) {
            return valueHolder.value;
        }
        throw ((Throwable) valueHolder2.value);
    }
}
