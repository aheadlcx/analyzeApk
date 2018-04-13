package rx.internal.util;

import rx.b.g;

public final class UtilityFunctions {

    enum AlwaysTrue implements g<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object obj) {
            return Boolean.valueOf(true);
        }
    }

    enum Identity implements g<Object, Object> {
        INSTANCE;

        public Object call(Object obj) {
            return obj;
        }
    }

    public static <T> g<? super T, Boolean> a() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> g<T, T> b() {
        return Identity.INSTANCE;
    }
}
