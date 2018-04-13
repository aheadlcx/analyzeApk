package com.c.a;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class g {
    private static Handler a = new Handler(Looper.getMainLooper());

    public interface a {
        void a();
    }

    static class b implements InvocationHandler {
        private WeakReference<a> a;

        public b(WeakReference<a> weakReference) {
            this.a = weakReference;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if (((Boolean) objArr[0]).booleanValue() && this.a.get() != null) {
                    ((a) this.a.get()).a();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static boolean a(Activity activity) {
        try {
            Method declaredMethod = Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activity, new Object[0]);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void a(Activity activity, a aVar) {
        if (VERSION.SDK_INT < 21) {
            b(activity, aVar);
        } else {
            c(activity, aVar);
        }
    }

    private static void b(Activity activity, a aVar) {
        Class cls = null;
        try {
            Class[] declaredClasses = Activity.class.getDeclaredClasses();
            int length = declaredClasses.length;
            int i = 0;
            while (i < length) {
                Class cls2 = declaredClasses[i];
                if (!cls2.getSimpleName().contains("TranslucentConversionListener")) {
                    cls2 = cls;
                }
                i++;
                cls = cls2;
            }
            Method declaredMethod = Activity.class.getDeclaredMethod("convertToTranslucent", new Class[]{cls});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activity, new Object[]{null});
            InvocationHandler bVar = new b(new WeakReference(aVar));
            Object newProxyInstance = Proxy.newProxyInstance(Activity.class.getClassLoader(), new Class[]{cls}, bVar);
            Method declaredMethod2 = Activity.class.getDeclaredMethod("convertToTranslucent", new Class[]{cls});
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(activity, new Object[]{newProxyInstance});
        } catch (Throwable th) {
        }
    }

    @RequiresApi(api = 16)
    private static void c(Activity activity, a aVar) {
        try {
            Method declaredMethod = Activity.class.getDeclaredMethod("getActivityOptions", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(activity, new Object[0]);
            Class[] declaredClasses = Activity.class.getDeclaredClasses();
            Class cls = null;
            int length = declaredClasses.length;
            int i = 0;
            while (i < length) {
                Class cls2 = declaredClasses[i];
                if (!cls2.getSimpleName().contains("TranslucentConversionListener")) {
                    cls2 = cls;
                }
                i++;
                cls = cls2;
            }
            InvocationHandler bVar = new b(new WeakReference(aVar));
            Object newProxyInstance = Proxy.newProxyInstance(Activity.class.getClassLoader(), new Class[]{cls}, bVar);
            declaredMethod = Activity.class.getDeclaredMethod("convertToTranslucent", new Class[]{cls, ActivityOptions.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activity, new Object[]{newProxyInstance, invoke});
        } catch (Throwable th) {
        }
    }

    static void a(Runnable runnable) {
        if (runnable != null) {
            a.post(runnable);
        }
    }
}
