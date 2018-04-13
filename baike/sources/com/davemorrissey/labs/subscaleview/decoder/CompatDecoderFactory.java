package com.davemorrissey.labs.subscaleview.decoder;

import android.support.annotation.NonNull;

public class CompatDecoderFactory<T> implements DecoderFactory<T> {
    private Class<? extends T> a;

    public CompatDecoderFactory(@NonNull Class<? extends T> cls) {
        this.a = cls;
    }

    public T make() throws IllegalAccessException, InstantiationException {
        return this.a.newInstance();
    }
}
