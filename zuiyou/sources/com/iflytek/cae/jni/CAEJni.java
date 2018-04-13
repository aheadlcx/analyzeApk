package com.iflytek.cae.jni;

import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.as;

public final class CAEJni {
    private static boolean a;

    public static class a {
    }

    public static native int CAEAudioWrite(int i, byte[] bArr, int i2);

    public static native int CAEDestroy(int i);

    public static native int CAEGetChannel();

    public static native int CAENew(String str, String str2, String str3, String str4, Object obj);

    public static native int CAERead16kAudio(byte[] bArr);

    public static native int CAEReset(int i);

    public static native int CAESendMsg(int i, int i2, byte[] bArr, byte[] bArr2);

    public static native int CAESetRealBeam(int i, int i2);

    public static native int CAESetWParam(int i, byte[] bArr, byte[] bArr2);

    public static native void DebugLog(boolean z);

    public static void a(String str) {
        if (!a) {
            if (TextUtils.isEmpty(str)) {
                str = "cae";
            }
            try {
                System.loadLibrary(str);
                a = true;
                DebugLog(as.a());
            } catch (Exception e) {
                a = false;
            }
        }
    }

    public static boolean a() {
        return a;
    }
}
