package com.sensetime.stmobile;

import android.content.res.AssetManager;

public class STMobileHumanActionNative {
    public static final long ST_MOBILE_BROW_JUMP = 32;
    public static final long ST_MOBILE_DETECT_EXTRA_FACE_POINTS = 16777216;
    public static final long ST_MOBILE_DETECT_EYEBALL_CENTER = 33554432;
    public static final long ST_MOBILE_DETECT_EYEBALL_CONTOUR = 67108864;
    public static final int ST_MOBILE_DETECT_MODE_IMAGE = 262144;
    public static final int ST_MOBILE_DETECT_MODE_VIDEO = 131072;
    public static final int ST_MOBILE_ENABLE_EYEBALL_CENTER_DETECT = 1024;
    public static final int ST_MOBILE_ENABLE_EYEBALL_CONTOUR_DETECT = 2048;
    public static final int ST_MOBILE_ENABLE_FACE_DETECT = 64;
    public static final int ST_MOBILE_ENABLE_FACE_EXTRA_DETECT = 512;
    public static final int ST_MOBILE_ENABLE_HAND_DETECT = 128;
    public static final int ST_MOBILE_ENABLE_SEGMENT_DETECT = 256;
    public static final long ST_MOBILE_EYE_BLINK = 2;
    public static final long ST_MOBILE_FACE_DETECT = 1;
    public static final long ST_MOBILE_HAND_CONGRATULATE = 131072;
    public static final long ST_MOBILE_HAND_FINGER_HEART = 262144;
    public static final long ST_MOBILE_HAND_FINGER_INDEX = 1048576;
    public static final long ST_MOBILE_HAND_GOOD = 2048;
    public static final long ST_MOBILE_HAND_HOLDUP = 32768;
    public static final long ST_MOBILE_HAND_LOVE = 16384;
    public static final long ST_MOBILE_HAND_OK = 512;
    public static final long ST_MOBILE_HAND_PALM = 4096;
    public static final long ST_MOBILE_HAND_PISTOL = 8192;
    public static final long ST_MOBILE_HAND_SCISSOR = 1024;
    public static final long ST_MOBILE_HAND_TWO_INDEX_FINGER = 524288;
    public static final long ST_MOBILE_HEAD_PITCH = 16;
    public static final long ST_MOBILE_HEAD_YAW = 8;
    public static final long ST_MOBILE_HUMAN_ACTION_DEFAULT_CONFIG_DETECT = 101177407;
    public static final int ST_MOBILE_HUMAN_ACTION_DEFAULT_CONFIG_IMAGE = 328128;
    public static final int ST_MOBILE_HUMAN_ACTION_DEFAULT_CONFIG_VIDEO = 131568;
    public static final long ST_MOBILE_MOUTH_AH = 4;
    public static final long ST_MOBILE_SEG_BACKGROUND = 65536;
    private static final String TAG = STMobileHumanActionNative.class.getSimpleName();
    private long nativeHumanActionHandle;

    public native int addSubModel(String str);

    public native int addSubModelFromAssetFile(String str, AssetManager assetManager);

    public native int createInstance(String str, int i);

    public native int createInstanceFromAssetFile(String str, int i, AssetManager assetManager);

    public native int createInstanceWithSubModels(String[] strArr, int i, int i2);

    public native void destroyInstance();

    public native STHumanAction humanActionDetect(byte[] bArr, int i, long j, int i2, int i3, int i4);

    public native STHumanAction humanActionMirror(int i, STHumanAction sTHumanAction);

    public native void reset();

    public native int setParam(int i, float f);

    static {
        System.loadLibrary("st_mobile");
        System.loadLibrary("stmobile_jni");
    }
}
