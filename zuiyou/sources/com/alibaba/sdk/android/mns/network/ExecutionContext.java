package com.alibaba.sdk.android.mns.network;

import com.alibaba.sdk.android.common.CancellationHandler;
import com.alibaba.sdk.android.mns.callback.MNSCompletedCallback;
import com.alibaba.sdk.android.mns.callback.MNSProgressCallback;
import com.alibaba.sdk.android.mns.model.MNSRequest;
import okhttp3.w;

public class ExecutionContext<T extends MNSRequest> {
    private CancellationHandler cancellationHandler = new CancellationHandler();
    private w client;
    private MNSCompletedCallback completedCallback;
    private MNSProgressCallback progressCallback;
    private T request;

    public ExecutionContext(w wVar, T t) {
        this.client = wVar;
        this.request = t;
    }

    public T getRequest() {
        return this.request;
    }

    public void setRequest(T t) {
        this.request = t;
    }

    public w getClient() {
        return this.client;
    }

    public void setClient(w wVar) {
        this.client = wVar;
    }

    public CancellationHandler getCancellationHandler() {
        return this.cancellationHandler;
    }

    public void setCancellationHandler(CancellationHandler cancellationHandler) {
        this.cancellationHandler = cancellationHandler;
    }

    public MNSCompletedCallback getCompletedCallback() {
        return this.completedCallback;
    }

    public void setCompletedCallback(MNSCompletedCallback mNSCompletedCallback) {
        this.completedCallback = mNSCompletedCallback;
    }

    public MNSProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(MNSProgressCallback mNSProgressCallback) {
        this.progressCallback = mNSProgressCallback;
    }
}
