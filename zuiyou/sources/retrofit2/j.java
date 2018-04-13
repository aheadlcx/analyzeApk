package retrofit2;

import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

class j {
    private static final j a = c();

    j() {
    }

    static j a() {
        return a;
    }

    private static j c() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new j$a();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new j$b();
        } catch (ClassNotFoundException e2) {
            return new j();
        }
    }

    @Nullable
    Executor b() {
        return null;
    }

    c$a a(@Nullable Executor executor) {
        if (executor != null) {
            return new g(executor);
        }
        return f.a;
    }

    boolean a(Method method) {
        return false;
    }

    @Nullable
    Object a(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }
}
