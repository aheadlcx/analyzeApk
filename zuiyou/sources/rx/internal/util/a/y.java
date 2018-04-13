package rx.internal.util.a;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public final class y {
    public static final Unsafe a;
    private static final boolean b;

    static {
        Unsafe unsafe;
        boolean z = true;
        if (System.getProperty("rx.unsafe-disable") == null) {
            z = false;
        }
        b = z;
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (Throwable th) {
            unsafe = null;
        }
        a = unsafe;
    }

    public static boolean a() {
        return (a == null || b) ? false : true;
    }

    public static long a(Class<?> cls, String str) {
        try {
            return a.objectFieldOffset(cls.getDeclaredField(str));
        } catch (Throwable e) {
            InternalError internalError = new InternalError();
            internalError.initCause(e);
            throw internalError;
        }
    }
}
