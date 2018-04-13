package io.agora.rtc.video;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public abstract class VideoCapture {
    private static final int kVideoI420 = 0;
    private static final int kVideoNV12 = 11;
    private static final int kVideoNV21 = 12;
    private static final int kVideoUnknown = 99;
    private static final int kVideoYUY2 = 2;
    private static final int kVideoYV12 = 1;
    protected int mCameraNativeOrientation;
    protected final Context mContext;
    protected final int mId;
    protected long mNativeVideoCaptureDeviceAndroid;

    public native void ProvideCameraFrame(byte[] bArr, int i, long j);

    public native void ProvideCameraTexture(byte[] bArr, int i, long j);

    public abstract int allocate();

    public abstract void deallocate();

    public native void onCameraError(long j, String str);

    public abstract int setCaptureFormat(int i);

    public abstract int startCapture(int i, int i2, int i3);

    public abstract int stopCapture();

    VideoCapture(Context context, int i, long j) {
        this.mContext = context;
        this.mId = i;
        this.mNativeVideoCaptureDeviceAndroid = j;
    }

    public static String fetchCapability(int i, Context context) {
        return context.getSharedPreferences("CamCaps", 0).getString("Cam_" + i, null);
    }

    public static void cacheCapability(int i, Context context, String str) {
        Editor edit = context.getSharedPreferences("CamCaps", 0).edit();
        edit.putString("Cam_" + i, str);
        edit.commit();
    }

    public static int translateToEngineFormat(int i) {
        switch (i) {
            case 17:
                return 12;
            case 20:
                return 2;
            case 35:
                return 0;
            case 842094169:
                return 1;
            default:
                return 99;
        }
    }

    public static int translateToAndroidFormat(int i) {
        switch (i) {
            case 0:
                return 35;
            case 1:
                return 842094169;
            case 2:
                return 20;
            case 12:
                return 17;
            default:
                return 0;
        }
    }
}
