package com.facebook.imagepipeline.nativecode;

import android.os.Build.VERSION;
import com.facebook.common.e.a;

public class b {
    private static boolean a;

    public static synchronized void a() {
        synchronized (b.class) {
            if (!a) {
                if (VERSION.SDK_INT <= 16) {
                    try {
                        a.a("fb_jpegturbo");
                    } catch (UnsatisfiedLinkError e) {
                    }
                }
                a.a("static-webp");
                a = true;
            }
        }
    }
}
