package com.sensetime.stmobile;

import android.content.Context;

public class STMobileAuthentificationNative {
    public static native int checkActiveCode(Context context, String str, String str2, int i);

    public static native int checkActiveCodeFromBuffer(Context context, String str, int i, String str2, int i2);

    public static native String generateActiveCode(Context context, String str);

    public static native String generateActiveCodeFromBuffer(Context context, String str, int i);

    static {
        System.loadLibrary("st_mobile");
        System.loadLibrary("stmobile_jni");
    }
}
