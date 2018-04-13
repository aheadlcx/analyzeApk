package com.google.tagmanager;

import com.google.tagmanager.LoadCallback.Failure;

class TypeOrFailure<T> {
    private Failure mFailure;
    private T mType;

    public TypeOrFailure(T t) {
        this.mType = t;
    }

    public TypeOrFailure(Failure failure) {
        this.mFailure = failure;
    }

    public T getType() {
        return this.mType;
    }

    public Failure getFailure() {
        return this.mFailure;
    }
}
