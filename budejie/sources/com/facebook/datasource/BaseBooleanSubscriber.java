package com.facebook.datasource;

public abstract class BaseBooleanSubscriber implements DataSubscriber<Boolean> {
    protected abstract void onFailureImpl(DataSource<Boolean> dataSource);

    protected abstract void onNewResultImpl(boolean z);

    public void onNewResult(DataSource<Boolean> dataSource) {
        try {
            onNewResultImpl(((Boolean) dataSource.getResult()).booleanValue());
        } finally {
            dataSource.close();
        }
    }

    public void onFailure(DataSource<Boolean> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }

    public void onCancellation(DataSource<Boolean> dataSource) {
    }

    public void onProgressUpdate(DataSource<Boolean> dataSource) {
    }
}
