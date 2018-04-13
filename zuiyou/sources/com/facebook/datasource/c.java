package com.facebook.datasource;

import com.facebook.common.internal.i;

public class c {
    public static <T> b<T> a(Throwable th) {
        b j = g.j();
        j.a(th);
        return j;
    }

    public static <T> i<b<T>> b(final Throwable th) {
        return new i<b<T>>() {
            public /* synthetic */ Object b() {
                return a();
            }

            public b<T> a() {
                return c.a(th);
            }
        };
    }
}
