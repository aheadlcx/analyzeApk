package qsbk.app.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtils {
    static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static final Object get(Object obj, String str) {
        if (obj == null || a(str)) {
            return null;
        }
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            declaredField.setAccessible(false);
            return obj2;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final Method getMethod(Class cls, String str, Class<?>... clsArr) {
        Method method = null;
        if (!(cls == null || a(str))) {
            while (method == null && cls != null) {
                try {
                    method = cls.getDeclaredMethod(str, clsArr);
                    if (method != null) {
                        method.setAccessible(true);
                    }
                } catch (NoSuchMethodException e) {
                }
                if (method == null) {
                    cls = cls.getSuperclass();
                }
            }
        }
        return method;
    }

    public static final Method getMethod(Object obj, String str, Class<?>... clsArr) {
        if (obj == null) {
            return null;
        }
        return getMethod(obj.getClass(), str, (Class[]) clsArr);
    }

    public static final Object invokeMethod(Object obj, Method method, Object... objArr) {
        if (method == null || obj == null) {
            return null;
        }
        try {
            method.setAccessible(true);
            Object invoke = method.invoke(obj, objArr);
            method.setAccessible(false);
            return invoke;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
