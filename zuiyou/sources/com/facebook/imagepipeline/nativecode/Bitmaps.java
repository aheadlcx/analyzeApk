package com.facebook.imagepipeline.nativecode;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.d;
import com.facebook.common.internal.g;
import com.facebook.d.a;
import java.nio.ByteBuffer;

@d
public class Bitmaps {
    @d
    private static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);

    @d
    private static native ByteBuffer nativeGetByteBuffer(Bitmap bitmap, long j, long j2);

    @d
    private static native void nativePinBitmap(Bitmap bitmap);

    @d
    private static native void nativeReleaseByteBuffer(Bitmap bitmap);

    static {
        a.a();
    }

    public static void a(Bitmap bitmap) {
        g.a((Object) bitmap);
        nativePinBitmap(bitmap);
    }

    public static void a(Bitmap bitmap, Bitmap bitmap2) {
        boolean z;
        boolean z2 = true;
        g.a(bitmap2.getConfig() == bitmap.getConfig());
        g.a(bitmap.isMutable());
        if (bitmap.getWidth() == bitmap2.getWidth()) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (bitmap.getHeight() != bitmap2.getHeight()) {
            z2 = false;
        }
        g.a(z2);
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }

    @TargetApi(19)
    public static void a(Bitmap bitmap, int i, int i2, Config config) {
        g.a(bitmap.getAllocationByteCount() >= (i * i2) * a.a(config));
        bitmap.reconfigure(i, i2, config);
    }
}
