package com.facebook.datasource;

import com.facebook.common.internal.Supplier;

class DataSources$1 implements Supplier<DataSource<T>> {
    final /* synthetic */ Throwable val$failure;

    DataSources$1(Throwable th) {
        this.val$failure = th;
    }

    public DataSource<T> get() {
        return DataSources.immediateFailedDataSource(this.val$failure);
    }
}
