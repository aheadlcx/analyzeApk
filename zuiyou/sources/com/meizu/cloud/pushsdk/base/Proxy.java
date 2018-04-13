package com.meizu.cloud.pushsdk.base;

public class Proxy<T> {
    protected T mInnerImpl;
    protected T mOuterImpl;

    protected Proxy(T t) {
        if (t == null) {
            throw new RuntimeException("proxy must be has a default implementation");
        }
        this.mInnerImpl = t;
    }

    public void setImpl(T t) {
        this.mOuterImpl = t;
    }

    protected T getImpl() {
        if (this.mOuterImpl != null) {
            return this.mOuterImpl;
        }
        return this.mInnerImpl;
    }
}
