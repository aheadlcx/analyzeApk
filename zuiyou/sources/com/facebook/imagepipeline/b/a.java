package com.facebook.imagepipeline.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public class a extends f {
    private final com.facebook.imagepipeline.memory.a a;

    public a(com.facebook.imagepipeline.memory.a aVar) {
        this.a = aVar;
    }

    public com.facebook.common.references.a<Bitmap> a(int i, int i2, Config config) {
        Bitmap bitmap = (Bitmap) this.a.get(com.facebook.d.a.a(i, i2, config));
        Bitmaps.a(bitmap, i, i2, config);
        return com.facebook.common.references.a.a(bitmap, this.a);
    }
}
