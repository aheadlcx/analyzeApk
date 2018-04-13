package com.jakewharton.a.a;

import rx.b.f;
import rx.b.g;

public final class a {
    public static final f<Boolean> a = c;
    public static final g<Object, Boolean> b = c;
    private static final a<Boolean> c = new a(Boolean.valueOf(true));

    private static final class a<T> implements f<T>, g<Object, T> {
        private final T a;

        a(T t) {
            this.a = t;
        }

        public T call(Object obj) {
            return this.a;
        }

        public T call() {
            return this.a;
        }
    }
}
