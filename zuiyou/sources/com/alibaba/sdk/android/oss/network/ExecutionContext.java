package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import okhttp3.w;

public class ExecutionContext<T extends OSSRequest> {
    private CancellationHandler cancellationHandler = new CancellationHandler();
    private w client;
    private OSSCompletedCallback completedCallback;
    private OSSProgressCallback progressCallback;
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

    public OSSCompletedCallback getCompletedCallback() {
        return this.completedCallback;
    }

    public void setCompletedCallback(OSSCompletedCallback oSSCompletedCallback) {
        this.completedCallback = oSSCompletedCallback;
    }

    public OSSProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(OSSProgressCallback oSSProgressCallback) {
        this.progressCallback = oSSProgressCallback;
    }
}
