package retrofit2;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

class j {
    private static final j a = c();

    static class a extends j {

        static class a implements Executor {
            private final Handler a = new Handler(Looper.getMainLooper());

            a() {
            }

            public void execute(Runnable runnable) {
                this.a.post(runnable);
            }
        }

        a() {
        }

        public Executor b() {
            return new a();
        }

        retrofit2.c.a a(Executor executor) {
            return new g(executor);
        }
    }

    @IgnoreJRERequirement
    static class b extends j {
        b() {
        }

        boolean a(Method method) {
            return method.isDefault();
        }

        Object a(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
            Constructor declaredConstructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            return ((Lookup) declaredConstructor.newInstance(new Object[]{cls, Integer.valueOf(-1)})).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }
    }

    j() {
    }

    static j a() {
        return a;
    }

    private static j c() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new a();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new b();
        } catch (ClassNotFoundException e2) {
            return new j();
        }
    }

    Executor b() {
        return null;
    }

    retrofit2.c.a a(Executor executor) {
        if (executor != null) {
            return new g(executor);
        }
        return f.a;
    }

    boolean a(Method method) {
        return false;
    }

    Object a(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }
}
