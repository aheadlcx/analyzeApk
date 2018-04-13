package com.tencent.smtt.export.external;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class libwebp {
    private static final int BITMAP_ALPHA_8 = 1;
    private static final int BITMAP_ARGB_4444 = 3;
    private static final int BITMAP_ARGB_8888 = 4;
    private static final int BITMAP_RGB_565 = 2;
    private static final String LOGTAG = "[image]";
    private static boolean isMultiCore = false;
    private static libwebp mInstance = null;
    private static boolean mIsLoadLibSuccess = false;
    private int mBitmapType = 4;

    /* renamed from: com.tencent.smtt.export.external.libwebp$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Config.ARGB_4444.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static int checkIsHuaModel() {
        String toLowerCase = Build.BRAND.trim().toLowerCase();
        String toLowerCase2 = Build.MODEL.trim().toLowerCase();
        int i = 0;
        if (toLowerCase != null && toLowerCase.length() > 0 && toLowerCase.contains("huawei")) {
            i = 1;
        }
        return (toLowerCase2 == null || toLowerCase2.length() <= 0 || !toLowerCase2.contains("huawei")) ? i : 1;
    }

    private String getCPUinfo() {
        String str;
        IOException e;
        String str2 = "";
        try {
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/proc/cpuinfo"}).start().getInputStream();
            byte[] bArr = new byte[1024];
            str = str2;
            while (inputStream.read(bArr) != -1) {
                try {
                    str = str + new String(bArr);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            inputStream.close();
        } catch (IOException e3) {
            IOException iOException = e3;
            str = str2;
            e = iOException;
            e.printStackTrace();
            return str;
        }
        return str;
    }

    public static libwebp getInstance(Context context) {
        if (mInstance == null) {
            loadWepLibraryIfNeed(context);
            mInstance = new libwebp();
        }
        return mInstance;
    }

    private boolean isMultiCore() {
        return getCPUinfo().contains("processor");
    }

    public static void loadWepLibraryIfNeed(Context context) {
        if (!mIsLoadLibSuccess) {
            try {
                LibraryLoader.loadLibrary(context, "webp_base");
                mIsLoadLibSuccess = true;
            } catch (UnsatisfiedLinkError e) {
                Log.e(LOGTAG, "Load WebP Library Error...: libwebp.java - loadWepLibraryIfNeed()");
            }
        }
    }

    public static void loadWepLibraryIfNeed(Context context, String str) {
        if (!mIsLoadLibSuccess) {
            try {
                System.load(str + File.separator + "libwebp_base.so");
                mIsLoadLibSuccess = true;
            } catch (UnsatisfiedLinkError e) {
                Log.e(LOGTAG, "Load WebP Library Error...: libwebp.java - loadWepLibraryIfNeed()");
            }
        }
    }

    public int[] decodeBase(byte[] bArr, int[] iArr, int[] iArr2) {
        if (mIsLoadLibSuccess) {
            return nativeDecode(bArr, isMultiCore, iArr, iArr2);
        }
        Log.e(LOGTAG, "Load WebP Library Error...");
        return null;
    }

    public int[] decodeBase_16bit(byte[] bArr, Config config) {
        if (mIsLoadLibSuccess) {
            switch (AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()]) {
                case 1:
                    this.mBitmapType = 3;
                    break;
                case 2:
                    this.mBitmapType = 2;
                    break;
                default:
                    this.mBitmapType = 2;
                    break;
            }
            return nativeDecode_16bit(bArr, isMultiCore, this.mBitmapType);
        }
        Log.e(LOGTAG, "Load WebP Library Error...");
        return null;
    }

    public int[] decodeInto(byte[] bArr, int[] iArr, int[] iArr2) {
        if (mIsLoadLibSuccess) {
            return nativeDecodeInto(bArr, isMultiCore, iArr, iArr2);
        }
        Log.e(LOGTAG, "Load WebP Library Error...");
        return null;
    }

    public int getInfo(byte[] bArr, int[] iArr, int[] iArr2) {
        return !mIsLoadLibSuccess ? 0 : nativeGetInfo(bArr, iArr, iArr2);
    }

    public int[] incDecode(byte[] bArr, int[] iArr, int[] iArr2) {
        if (mIsLoadLibSuccess) {
            return nativeIDecode(bArr, isMultiCore, iArr, iArr2);
        }
        Log.e(LOGTAG, "Load WebP Library Error...");
        return null;
    }

    public native int[] nativeDecode(byte[] bArr, boolean z, int[] iArr, int[] iArr2);

    public native int[] nativeDecodeInto(byte[] bArr, boolean z, int[] iArr, int[] iArr2);

    public native int[] nativeDecode_16bit(byte[] bArr, boolean z, int i);

    public native int nativeGetInfo(byte[] bArr, int[] iArr, int[] iArr2);

    public native int[] nativeIDecode(byte[] bArr, boolean z, int[] iArr, int[] iArr2);
}
