package com.sensetime.stmobile;

public class STMobileFilterNative {
    private long nativeHandle;

    public native int createInstance();

    public native void destroyInstance();

    public native int process(byte[] bArr, int i, int i2, int i3, byte[] bArr2, int i4);

    public native int setParam(int i, float f);

    public native int setStyle(String str);
}
