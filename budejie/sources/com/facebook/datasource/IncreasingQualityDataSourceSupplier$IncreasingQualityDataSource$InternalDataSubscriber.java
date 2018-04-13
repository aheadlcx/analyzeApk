package com.facebook.datasource;

class IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource$InternalDataSubscriber implements DataSubscriber<T> {
    private int mIndex;
    final /* synthetic */ IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource this$1;

    public IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource$InternalDataSubscriber(IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource increasingQualityDataSourceSupplier$IncreasingQualityDataSource, int i) {
        this.this$1 = increasingQualityDataSourceSupplier$IncreasingQualityDataSource;
        this.mIndex = i;
    }

    public void onNewResult(DataSource<T> dataSource) {
        if (dataSource.hasResult()) {
            IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource.access$100(this.this$1, this.mIndex, dataSource);
        } else if (dataSource.isFinished()) {
            IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource.access$200(this.this$1, this.mIndex, dataSource);
        }
    }

    public void onFailure(DataSource<T> dataSource) {
        IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource.access$200(this.this$1, this.mIndex, dataSource);
    }

    public void onCancellation(DataSource<T> dataSource) {
    }

    public void onProgressUpdate(DataSource<T> dataSource) {
        if (this.mIndex == 0) {
            this.this$1.setProgress(dataSource.getProgress());
        }
    }
}
