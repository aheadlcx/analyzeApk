package com.facebook.common.internal;

public class Suppliers {
    public static final Supplier<Boolean> BOOLEAN_FALSE = new Supplier<Boolean>() {
        public Boolean get() {
            return Boolean.valueOf(false);
        }
    };
    public static final Supplier<Boolean> BOOLEAN_TRUE = new Supplier<Boolean>() {
        public Boolean get() {
            return Boolean.valueOf(true);
        }
    };

    /* renamed from: com.facebook.common.internal.Suppliers$1 */
    final class AnonymousClass1 implements Supplier<T> {
        final /* synthetic */ Object val$instance;

        AnonymousClass1(Object obj) {
            this.val$instance = obj;
        }

        public T get() {
            return this.val$instance;
        }
    }

    public static <T> Supplier<T> of(T t) {
        return new AnonymousClass1(t);
    }
}
