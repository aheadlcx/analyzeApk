package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.annotation.Immutable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Immutable
@Deprecated
public class JdkIdn implements Idn {
    private final Method a;

    public JdkIdn() throws ClassNotFoundException {
        try {
            this.a = Class.forName("java.net.IDN").getMethod("toUnicode", new Class[]{String.class});
        } catch (Throwable e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (Throwable e2) {
            throw new IllegalStateException(e2.getMessage(), e2);
        }
    }

    public String toUnicode(String str) {
        Throwable e;
        try {
            return (String) this.a.invoke(null, new Object[]{str});
        } catch (Throwable e2) {
            throw new IllegalStateException(e2.getMessage(), e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }
}
