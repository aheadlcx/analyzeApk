package com.huawei.hms.update.f.a;

import java.lang.reflect.InvocationTargetException;

public abstract class a {
    public abstract int b();

    abstract Object c() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    public abstract boolean d();

    public static a a() {
        a bVar = new b();
        if (bVar.d()) {
            com.huawei.hms.support.log.a.b("MultiCard", "Return HW instance.");
            return bVar;
        }
        bVar = new c();
        if (!bVar.d()) {
            return null;
        }
        com.huawei.hms.support.log.a.b("MultiCard", "Return MTK instance.");
        return bVar;
    }

    public String a(int i) {
        return "";
    }

    public int b(int i) {
        try {
            Object c = c();
            return ((Integer) c.getClass().getDeclaredMethod("getSimState", new Class[]{Integer.TYPE}).invoke(c, new Object[]{Integer.valueOf(i)})).intValue();
        } catch (ClassNotFoundException e) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSimState()");
            return 0;
        } catch (NoSuchMethodException e2) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSimState()");
            return 0;
        } catch (IllegalAccessException e3) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSimState()");
            return 0;
        } catch (IllegalArgumentException e4) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSimState()");
            return 0;
        } catch (InvocationTargetException e5) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSimState()");
            return 0;
        } catch (ClassCastException e6) {
            com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSimState()");
            return 0;
        }
    }

    public String c(int i) {
        try {
            Object c = c();
            return (String) c.getClass().getMethod("getSubscriberId", new Class[]{Integer.TYPE}).invoke(c, new Object[]{Integer.valueOf(i)});
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        } catch (IllegalAccessException e3) {
        } catch (IllegalArgumentException e4) {
        } catch (InvocationTargetException e5) {
        } catch (ClassCastException e6) {
        }
        com.huawei.hms.support.log.a.c("MultiCard", "Failed to call [TelephonyManager].getSubscriberId()");
        return "";
    }
}
