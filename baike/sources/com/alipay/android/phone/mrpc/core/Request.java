package com.alipay.android.phone.mrpc.core;

public abstract class Request {
    protected TransportCallback a;
    private boolean b = false;

    public void cancel() {
        this.b = true;
    }

    public TransportCallback getCallback() {
        return this.a;
    }

    public boolean isCanceled() {
        return this.b;
    }

    public void setTransportCallback(TransportCallback transportCallback) {
        this.a = transportCallback;
    }
}
