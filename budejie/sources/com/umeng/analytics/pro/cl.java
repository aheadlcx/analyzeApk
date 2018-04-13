package com.umeng.analytics.pro;

import java.lang.reflect.InvocationTargetException;

public class cl {
    public static ck a(Class<? extends ck> cls, int i) {
        try {
            return (ck) cls.getMethod("findByValue", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(i)});
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        } catch (InvocationTargetException e3) {
            return null;
        }
    }
}
