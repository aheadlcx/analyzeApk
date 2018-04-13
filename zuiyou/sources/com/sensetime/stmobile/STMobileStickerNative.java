package com.sensetime.stmobile;

import android.content.Context;

public class STMobileStickerNative {
    public static final int ST_MOBILE_BROW_JUMP = 32;
    public static final int ST_MOBILE_EYE_BLINK = 2;
    public static final int ST_MOBILE_HEAD_PITCH = 16;
    public static final int ST_MOBILE_HEAD_YAW = 8;
    public static final int ST_MOBILE_MOUTH_AH = 4;
    private static final String TAG = STMobileStickerNative.class.getSimpleName();
    private static ItemCallback mCallback;
    private STSoundPlay mSoundPlay;
    private long nativeStickerHandle;

    public interface ItemCallback {
        void processTextureCallback(String str, RenderStatus renderStatus);
    }

    enum RenderStatus {
        ST_MATERIAL_BEGIN_RENDER(0),
        ST_MATERIAL_RENDERING(1),
        ST_MATERIAL_NO_RENDERING(2);
        
        private final int status;

        private RenderStatus(int i) {
            this.status = i;
        }

        public int getStatus() {
            return this.status;
        }

        public static RenderStatus fromStatus(int i) {
            for (RenderStatus renderStatus : values()) {
                if (renderStatus.getStatus() == i) {
                    return renderStatus;
                }
            }
            return null;
        }
    }

    private native int createInstanceNative(String str);

    private native void destroyInstanceNative();

    public native int changeSticker(String str);

    public native long getTriggerAction();

    public native int processTexture(int i, STHumanAction sTHumanAction, int i2, int i3, int i4, boolean z, int i5);

    public native int processTextureAndOutputBuffer(int i, STHumanAction sTHumanAction, int i2, int i3, int i4, boolean z, int i5, int i6, byte[] bArr);

    public native int setMaxMemory(int i);

    public native int setSoundPlayDone(String str);

    public native int setWaitingMaterialLoaded(boolean z);

    static {
        System.loadLibrary("st_mobile");
        System.loadLibrary("stmobile_jni");
    }

    public static void setCallback(ItemCallback itemCallback) {
        mCallback = itemCallback;
    }

    public static void item_callback(String str, int i) {
        if (mCallback != null) {
            mCallback.processTextureCallback(str, RenderStatus.fromStatus(i));
        }
    }

    public int createInstance(Context context, String str) {
        if (context != null) {
            this.mSoundPlay = new STSoundPlay(context);
        }
        int createInstanceNative = createInstanceNative(str);
        if (createInstanceNative == 0 && this.mSoundPlay != null) {
            this.mSoundPlay.setStickHandle(this);
        }
        return createInstanceNative;
    }

    public void destroyInstance() {
        destroyInstanceNative();
        if (this.mSoundPlay != null) {
            this.mSoundPlay.release();
            this.mSoundPlay = null;
        }
    }
}
