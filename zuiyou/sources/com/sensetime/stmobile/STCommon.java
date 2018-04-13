package com.sensetime.stmobile;

public class STCommon {
    public static final int ST_MOBILE_HUMAN_ACTION_MAX_FACE_COUNT = 10;
    public static final int ST_MOBILE_TRACKING_ENABLE_DEBOUNCE = 16;
    public static final int ST_MOBILE_TRACKING_ENABLE_FACE_ACTION = 32;
    public static final int ST_MOBILE_TRACKING_MULTI_THREAD = 0;
    public static final int ST_MOBILE_TRACKING_SINGLE_THREAD = 65536;
    public static final int ST_PIX_FMT_BGR888 = 5;
    public static final int ST_PIX_FMT_BGRA8888 = 4;
    public static final int ST_PIX_FMT_GRAY8 = 0;
    public static final int ST_PIX_FMT_NV12 = 2;
    public static final int ST_PIX_FMT_NV21 = 3;
    public static final int ST_PIX_FMT_RGBA8888 = 6;
    public static final int ST_PIX_FMT_YUV420P = 1;

    enum ResultCode {
        ST_OK(0),
        ST_E_INVALIDARG(-1),
        ST_E_HANDLE(-2),
        ST_E_OUTOFMEMORY(-3),
        ST_E_FAIL(-4),
        ST_E_DELNOTFOUND(-5),
        ST_E_INVALID_PIXEL_FORMAT(-6),
        ST_E_FILE_NOT_FOUND(-7),
        ST_E_INVALID_FILE_FORMAT(-8),
        ST_E_FILE_EXPIRE(-9),
        ST_E_INVALID_AUTH(-13),
        ST_E_INVALID_APPID(-14),
        ST_E_AUTH_EXPIRE(-15),
        ST_E_UUID_MISMATCH(-16),
        ST_E_ONLINE_AUTH_CONNECT_FAIL(-17),
        ST_E_ONLINE_AUTH_TIMEOUT(-18),
        ST_E_ONLINE_AUTH_INVALID(-19),
        ST_E_LICENSE_IS_NOT_ACTIVABLE(-20),
        ST_E_ACTIVE_FAIL(-21),
        ST_E_ACTIVE_CODE_INVALID(-22),
        ST_E_NO_CAPABILITY(-23),
        ST_E_GET_UDID_FAIL(-24),
        ST_E_SUBMODEL_NOT_EXIST(-26),
        ST_E_ONLINE_ACTIVATE_NO_NEED(-27),
        ST_E_ONLINE_ACTIVATE_FAIL(-28),
        ST_E_ONLINE_ACTIVATE_CODE_INVALID(-29),
        ST_E_ONLINE_ACTIVATE_CONNECT_FAIL(-30);
        
        private final int resultCode;

        private ResultCode(int i) {
            this.resultCode = i;
        }

        public int getResultCode() {
            return this.resultCode;
        }
    }

    public static native int stColorConvert(byte[] bArr, byte[] bArr2, int i, int i2, int i3);

    public static native int stImageRotate(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4);

    public native void setBrowjumpThreshold(float f);

    public native void setEyeblinkThreshold(float f);

    public native void setHeadpitchThreshold(float f);

    public native void setHeadposeThreshold(float f);

    public native void setHeadyawThreshold(float f);

    public native void setMouthahThreshold(float f);

    public native void setSmoothThreshold(float f);

    static {
        System.loadLibrary("st_mobile");
        System.loadLibrary("stmobile_jni");
    }
}
