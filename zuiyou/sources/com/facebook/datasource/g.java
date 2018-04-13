package com.facebook.datasource;

public class g<T> extends AbstractDataSource<T> {
    private g() {
    }

    public static <T> g<T> j() {
        return new g();
    }

    public boolean a(T t, boolean z) {
        return super.a(com.facebook.common.internal.g.a((Object) t), z);
    }

    public boolean a(Throwable th) {
        return super.a((Throwable) com.facebook.common.internal.g.a((Object) th));
    }

    public boolean a(float f) {
        return super.a(f);
    }
}
