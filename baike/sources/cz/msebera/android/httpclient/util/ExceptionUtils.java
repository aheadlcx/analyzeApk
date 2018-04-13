package cz.msebera.android.httpclient.util;

import java.lang.reflect.Method;

@Deprecated
public final class ExceptionUtils {
    private static final Method a = a();

    private static Method a() {
        try {
            return Throwable.class.getMethod("initCause", new Class[]{Throwable.class});
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static void initCause(Throwable th, Throwable th2) {
        if (a != null) {
            try {
                a.invoke(th, new Object[]{th2});
            } catch (Exception e) {
            }
        }
    }

    private ExceptionUtils() {
    }
}
