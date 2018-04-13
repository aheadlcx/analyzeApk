package com.meizu.a.a;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class b {
    protected static Method a(Method method, Class<?> cls, String str, Class<?>... clsArr) {
        if (method == null) {
            try {
                method = cls.getMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    protected static boolean a(Method method, Object obj, Object... objArr) {
        if (method != null) {
            try {
                method.invoke(obj, objArr);
                return true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }
}
