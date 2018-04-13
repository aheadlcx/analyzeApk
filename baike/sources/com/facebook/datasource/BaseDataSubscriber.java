package com.facebook.datasource;

public abstract class BaseDataSubscriber<T> implements DataSubscriber<T> {
    protected abstract void onFailureImpl(DataSource<T> dataSource);

    protected abstract void onNewResultImpl(DataSource<T> dataSource);

    public void onNewResult(DataSource<T> dataSource) {
        boolean isFinished = dataSource.isFinished();
        try {
            onNewResultImpl(dataSource);
        } finally {
            if (isFinished) {
                dataSource.close();
            }
        }
    }

    public void onFailure(DataSource<T> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }

    public void onCancellation(DataSource<T> dataSource) {
    }

    public void onProgressUpdate(DataSource<T> dataSource) {
    }
}
