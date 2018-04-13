package com.google.tagmanager;

class ObjectAndStatic<T> {
    private final boolean mIsStatic;
    private final T mObject;

    ObjectAndStatic(T t, boolean z) {
        this.mObject = t;
        this.mIsStatic = z;
    }

    public T getObject() {
        return this.mObject;
    }

    public boolean isStatic() {
        return this.mIsStatic;
    }
}
