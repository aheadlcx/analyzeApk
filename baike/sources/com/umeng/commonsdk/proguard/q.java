package com.umeng.commonsdk.proguard;

import java.lang.reflect.InvocationTargetException;

public class q {
    public static p a(Class<? extends p> cls, int i) {
        try {
            return (p) cls.getMethod("findByValue", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(i)});
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        } catch (InvocationTargetException e3) {
            return null;
        }
    }
}
