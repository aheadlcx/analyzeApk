package com.facebook.datasource;

class FirstAvailableDataSourceSupplier$FirstAvailableDataSource$InternalDataSubscriber implements DataSubscriber<T> {
    final /* synthetic */ FirstAvailableDataSourceSupplier$FirstAvailableDataSource this$1;

    private FirstAvailableDataSourceSupplier$FirstAvailableDataSource$InternalDataSubscriber(FirstAvailableDataSourceSupplier$FirstAvailableDataSource firstAvailableDataSourceSupplier$FirstAvailableDataSource) {
        this.this$1 = firstAvailableDataSourceSupplier$FirstAvailableDataSource;
    }

    public void onFailure(DataSource<T> dataSource) {
        FirstAvailableDataSourceSupplier$FirstAvailableDataSource.access$200(this.this$1, dataSource);
    }

    public void onCancellation(DataSource<T> dataSource) {
    }

    public void onNewResult(DataSource<T> dataSource) {
        if (dataSource.hasResult()) {
            FirstAvailableDataSourceSupplier$FirstAvailableDataSource.access$300(this.this$1, dataSource);
        } else if (dataSource.isFinished()) {
            FirstAvailableDataSourceSupplier$FirstAvailableDataSource.access$200(this.this$1, dataSource);
        }
    }

    public void onProgressUpdate(DataSource<T> dataSource) {
        this.this$1.setProgress(Math.max(this.this$1.getProgress(), dataSource.getProgress()));
    }
}
