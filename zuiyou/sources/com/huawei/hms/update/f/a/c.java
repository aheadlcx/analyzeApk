package com.huawei.hms.update.f.a;

import com.huawei.hms.support.log.a;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class c extends a {
    c() {
    }

    public int b() {
        try {
            return e();
        } catch (ClassNotFoundException e) {
            a.c("MultiCardMTKImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (NoSuchMethodException e2) {
            a.c("MultiCardMTKImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (IllegalAccessException e3) {
            a.c("MultiCardMTKImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (IllegalArgumentException e4) {
            a.c("MultiCardMTKImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (InvocationTargetException e5) {
            a.c("MultiCardMTKImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        } catch (ClassCastException e6) {
            a.c("MultiCardMTKImpl", "Failed to invoke [TelephonyManager].getDefaultSubscription()");
            return -1;
        }
    }

    private int e() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class cls = Class.forName("android.telephony.TelephonyManager");
        Object invoke = cls.getDeclaredMethod("getDefault", (Class[]) null).invoke(null, (Object[]) null);
        Method declaredMethod = cls.getDeclaredMethod("getDefaultSim", (Class[]) null);
        declaredMethod.setAccessible(true);
        return ((Integer) declaredMethod.invoke(invoke, (Object[]) null)).intValue();
    }

    Object c() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class cls = Class.forName("com.mediatek.telephony.TelephonyManagerEx");
        return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
    }

    public boolean d() {
        try {
            return f();
        } catch (ClassNotFoundException e) {
            a.c("MultiCardMTKImpl", "Failed to invoke FeatureOption.MTK_GEMINI_SUPPORT");
            return false;
        } catch (NoSuchFieldException e2) {
            a.c("MultiCardMTKImpl", "Failed to invoke FeatureOption.MTK_GEMINI_SUPPORT");
            return false;
        } catch (IllegalAccessException e3) {
            a.c("MultiCardMTKImpl", "Failed to invoke FeatureOption.MTK_GEMINI_SUPPORT");
            return false;
        } catch (IllegalArgumentException e4) {
            a.c("MultiCardMTKImpl", "Failed to invoke FeatureOption.MTK_GEMINI_SUPPORT");
            return false;
        } catch (ClassCastException e5) {
            a.c("MultiCardMTKImpl", "Failed to invoke FeatureOption.MTK_GEMINI_SUPPORT");
            return false;
        }
    }

    private boolean f() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field declaredField = Class.forName("com.mediatek.common.featureoption.FeatureOption").getDeclaredField("MTK_GEMINI_SUPPORT");
        declaredField.setAccessible(true);
        return declaredField.getBoolean(null);
    }
}
