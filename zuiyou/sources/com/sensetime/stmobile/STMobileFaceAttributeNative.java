package com.sensetime.stmobile;

import com.sensetime.stmobile.model.STMobile106;

public class STMobileFaceAttributeNative {
    private long nativeHandle;

    public native int createInstance(String str);

    public native void destroyInstance();

    public native int detect(byte[] bArr, int i, int i2, int i3, STMobile106[] sTMobile106Arr, STFaceAttribute[] sTFaceAttributeArr);

    static {
        System.loadLibrary("st_mobile");
        System.loadLibrary("stmobile_jni");
    }
}
