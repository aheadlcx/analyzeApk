package com.facebook.imagepipeline.request;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import com.facebook.cache.common.b;
import com.facebook.imagepipeline.b.f;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import javax.annotation.Nullable;

public abstract class a implements c {
    public static final Config a = Config.ARGB_8888;

    public String b() {
        return "Unknown postprocessor";
    }

    public com.facebook.common.references.a<Bitmap> a(Bitmap bitmap, f fVar) {
        Config config = bitmap.getConfig();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (config == null) {
            config = a;
        }
        com.facebook.common.references.a a = fVar.a(width, height, config);
        try {
            a((Bitmap) a.a(), bitmap);
            com.facebook.common.references.a<Bitmap> b = com.facebook.common.references.a.b(a);
            return b;
        } finally {
            com.facebook.common.references.a.c(a);
        }
    }

    public void a(Bitmap bitmap, Bitmap bitmap2) {
        b(bitmap, bitmap2);
        a(bitmap);
    }

    public void a(Bitmap bitmap) {
    }

    @Nullable
    public b a() {
        return null;
    }

    private static void b(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap.getConfig() == bitmap2.getConfig()) {
            Bitmaps.a(bitmap, bitmap2);
        } else {
            new Canvas(bitmap).drawBitmap(bitmap2, 0.0f, 0.0f, null);
        }
    }
}
