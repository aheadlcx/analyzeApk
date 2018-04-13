package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class OSSAsyncTask<T extends OSSResult> {
    private volatile boolean canceled;
    private ExecutionContext context;
    private Future<T> future;

    public void cancel() {
        this.canceled = true;
        if (this.context != null) {
            this.context.getCancellationHandler().cancel();
        }
    }

    public boolean isCompleted() {
        return this.future.isDone();
    }

    public T getResult() throws ClientException, ServiceException {
        Throwable e;
        try {
            return (OSSResult) this.future.get();
        } catch (Throwable e2) {
            throw new ClientException(e2.getMessage(), e2);
        } catch (ExecutionException e3) {
            e2 = e3.getCause();
            if (e2 instanceof ClientException) {
                throw ((ClientException) e2);
            } else if (e2 instanceof ServiceException) {
                throw ((ServiceException) e2);
            } else {
                e2.printStackTrace();
                throw new ClientException("Unexpected exception!" + e2.getMessage());
            }
        }
    }

    public static OSSAsyncTask wrapRequestTask(Future future, ExecutionContext executionContext) {
        OSSAsyncTask oSSAsyncTask = new OSSAsyncTask();
        oSSAsyncTask.future = future;
        oSSAsyncTask.context = executionContext;
        return oSSAsyncTask;
    }

    public void waitUntilFinished() {
        try {
            this.future.get();
        } catch (Exception e) {
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }
}
