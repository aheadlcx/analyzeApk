package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@NotThreadSafe
class ab implements InvocationHandler {
    private static final Method a;
    private final HttpResponse b;

    static {
        try {
            a = Closeable.class.getMethod("close", new Class[0]);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }

    ab(HttpResponse httpResponse) {
        this.b = httpResponse;
    }

    public void close() throws IOException {
        s.a(this.b.getEntity());
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (method.equals(a)) {
            close();
            return null;
        }
        try {
            return method.invoke(this.b, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                throw cause;
            }
            throw e;
        }
    }
}
