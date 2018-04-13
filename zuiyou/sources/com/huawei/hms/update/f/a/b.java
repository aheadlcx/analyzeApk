package com.huawei.hms.update.f.a;

import com.huawei.hms.support.log.a;
import java.lang.reflect.InvocationTargetException;

class b extends a {
    b() {
    }

    public int b() {
        try {
            Object c = c();
            return ((Integer) c.getClass().getMethod("getDefaultSubscription", new Class[0]).invoke(c, new Object[0])).intValue();
        } catch (ClassNotFoundException e) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (NoSuchMethodException e2) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (IllegalAccessException e3) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (IllegalArgumentException e4) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (InvocationTargetException e5) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (ClassCastException e6) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        }
    }

    public String a(int i) {
        try {
            Object c = c();
            return (String) c.getClass().getMethod("getSimOperator", new Class[]{Integer.TYPE}).invoke(c, new Object[]{Integer.valueOf(i)});
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        } catch (IllegalAccessException e3) {
        } catch (IllegalArgumentException e4) {
        } catch (InvocationTargetException e5) {
        } catch (ClassCastException e6) {
        }
        a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].getSimOperator()");
        return "";
    }

    Object c() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class cls = Class.forName("android.telephony.MSimTelephonyManager");
        return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
    }

    public boolean d() {
        try {
            Object c = c();
            return ((Boolean) c.getClass().getMethod("isMultiSimEnabled", new Class[0]).invoke(c, new Object[0])).booleanValue();
        } catch (ClassNotFoundException e) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].isMultiSimEnabled()");
            return false;
        } catch (NoSuchMethodException e2) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].isMultiSimEnabled()");
            return false;
        } catch (IllegalAccessException e3) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].isMultiSimEnabled()");
            return false;
        } catch (IllegalArgumentException e4) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].isMultiSimEnabled()");
            return false;
        } catch (InvocationTargetException e5) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].isMultiSimEnabled()");
            return false;
        } catch (ClassCastException e6) {
            a.c("MultiCardHwImpl", "Failed to invoke [TelephonyManager].isMultiSimEnabled()");
            return false;
        }
    }
}
