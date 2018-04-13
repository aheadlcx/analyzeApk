package com.davemorrissey.labs.subscaleview.decoder;

import android.graphics.Bitmap.Config;
import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

public class a<T> implements b<T> {
    private final Class<? extends T> a;
    private final Config b;

    public a(@NonNull Class<? extends T> cls) {
        this(cls, null);
    }

    public a(@NonNull Class<? extends T> cls, Config config) {
        this.a = cls;
        this.b = config;
    }

    public T a() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (this.b == null) {
            return this.a.newInstance();
        }
        return this.a.getConstructor(new Class[]{Config.class}).newInstance(new Object[]{this.b});
    }
}
