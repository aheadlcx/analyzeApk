package io.agora.videoprp;

import android.content.Context;

public class AgoraYuvEnhancer {
    private native void SetConfigureFile(String str);

    public native int SetColorTemperature(float f);

    public native int SetGammaFactor(float f);

    public native int SetLighteningFactor(float f);

    public native int SetSmoothnessFactor(float f);

    public native void SetType(int i);

    public native int StartPreProcess();

    public native int StopPreProcess();

    static {
        SoHolder.load();
    }

    public AgoraYuvEnhancer(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        if (absolutePath == null) {
            absolutePath = context.getExternalFilesDir(null).getAbsolutePath();
            if (absolutePath == null) {
                absolutePath = "/sdcard";
            }
        }
        SetConfigureFile(absolutePath + "/agora_meiyan.cfg");
    }
}
