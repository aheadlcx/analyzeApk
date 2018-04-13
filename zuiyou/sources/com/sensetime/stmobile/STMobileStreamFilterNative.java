package com.sensetime.stmobile;

public class STMobileStreamFilterNative {
    private long nativeHandle;

    public native int createInstance();

    public native void destroyInstance();

    public native int processBuffer(byte[] bArr, int i, int i2, int i3, byte[] bArr2, int i4);

    public native int processTexture(int i, int i2, int i3, int i4);

    public native int processTextureAndOutputBuffer(int i, int i2, int i3, int i4, byte[] bArr, int i5);

    public native int setParam(int i, float f);

    public native int setStyle(String str);
}
