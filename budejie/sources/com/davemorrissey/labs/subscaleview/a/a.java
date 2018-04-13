package com.davemorrissey.labs.subscaleview.a;

import android.support.annotation.NonNull;

public class a<T> implements b<T> {
    private Class<? extends T> a;

    public a(@NonNull Class<? extends T> cls) {
        this.a = cls;
    }

    public T a() throws IllegalAccessException, InstantiationException {
        return this.a.newInstance();
    }
}
