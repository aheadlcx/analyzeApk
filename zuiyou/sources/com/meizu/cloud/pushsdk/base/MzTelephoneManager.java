package com.meizu.cloud.pushsdk.base;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.meizu.cloud.pushsdk.base.reflect.ReflectClass;
import com.meizu.cloud.pushsdk.base.reflect.ReflectResult;

public class MzTelephoneManager {
    private static final String CLASS_NAME = "android.telephony.MzTelephonyManager";
    private static final String METHOD_NAME = "getDeviceId";

    public static String getDeviceId(Context context) {
        ReflectResult invokeStatic = ReflectClass.forName(CLASS_NAME).method(METHOD_NAME, new Class[0]).invokeStatic(new Object[0]);
        if (invokeStatic.ok) {
            return (String) invokeStatic.value;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }
}
