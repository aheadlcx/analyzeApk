package com.alibaba.wireless.security.framework;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class h {
    public static String a(ClassLoader classLoader, String str) {
        if (classLoader == null || str == null || "".equals(str)) {
            return null;
        }
        String a = a(classLoader, str, true);
        return a == null ? a(classLoader, str, false) : a;
    }

    private static String a(ClassLoader classLoader, String str, boolean z) {
        if (classLoader == null) {
            return null;
        }
        String str2;
        if (z) {
            try {
                Method method = classLoader.getClass().getMethod("findLibrary", new Class[]{String.class});
            } catch (NoSuchMethodException e) {
                return null;
            } catch (IllegalAccessException e2) {
                return null;
            } catch (IllegalArgumentException e3) {
                return null;
            } catch (InvocationTargetException e4) {
                return null;
            } catch (Exception e5) {
                return null;
            }
        }
        method = classLoader.getClass().getDeclaredMethod("findLibrary", new Class[]{String.class});
        if (method == null) {
            str2 = null;
        } else {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            Object invoke = method.invoke(classLoader, new Object[]{str});
            str2 = invoke == null ? null : invoke instanceof String ? (String) invoke : null;
        }
        return str2;
    }

    public static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return (packageInfo == null || (packageInfo.applicationInfo.flags & 1) == 0 || (packageInfo.applicationInfo.flags & 128) != 0) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }
}
