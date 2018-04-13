package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.d;
import com.facebook.common.internal.g;

@d
public class NativeBlurFilter {
    @d
    private static native void nativeIterativeBoxBlur(Bitmap bitmap, int i, int i2);

    static {
        a.a();
    }

    public static void a(Bitmap bitmap, int i, int i2) {
        boolean z;
        boolean z2 = true;
        g.a((Object) bitmap);
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i2 <= 0) {
            z2 = false;
        }
        g.a(z2);
        nativeIterativeBoxBlur(bitmap, i, i2);
    }
}
