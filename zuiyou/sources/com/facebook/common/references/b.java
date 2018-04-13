package com.facebook.common.references;

import java.lang.ref.SoftReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class b<T> {
    SoftReference<T> a = null;
    SoftReference<T> b = null;
    SoftReference<T> c = null;

    public void a(@Nonnull T t) {
        this.a = new SoftReference(t);
        this.b = new SoftReference(t);
        this.c = new SoftReference(t);
    }

    @Nullable
    public T a() {
        return this.a == null ? null : this.a.get();
    }

    public void b() {
        if (this.a != null) {
            this.a.clear();
            this.a = null;
        }
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
        if (this.c != null) {
            this.c.clear();
            this.c = null;
        }
    }
}
