package com.facebook.imagepipeline.nativecode;

import android.os.Build.VERSION;
import com.facebook.common.soloader.SoLoaderShim;

public class StaticWebpNativeLoader {
    private static boolean sInitialized;

    public static synchronized void ensure() {
        synchronized (StaticWebpNativeLoader.class) {
            if (!sInitialized) {
                if (VERSION.SDK_INT <= 16) {
                    try {
                        SoLoaderShim.loadLibrary("fb_jpegturbo");
                    } catch (UnsatisfiedLinkError e) {
                    }
                }
                SoLoaderShim.loadLibrary("static-webp");
                sInitialized = true;
            }
        }
    }
}
