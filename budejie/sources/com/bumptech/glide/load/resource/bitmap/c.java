package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.i.h;
import com.bumptech.glide.load.engine.j;

public class c implements j<Bitmap> {
    private final Bitmap a;
    private final com.bumptech.glide.load.engine.a.c b;

    public /* synthetic */ Object b() {
        return a();
    }

    public static c a(Bitmap bitmap, com.bumptech.glide.load.engine.a.c cVar) {
        if (bitmap == null) {
            return null;
        }
        return new c(bitmap, cVar);
    }

    public c(Bitmap bitmap, com.bumptech.glide.load.engine.a.c cVar) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (cVar == null) {
            throw new NullPointerException("BitmapPool must not be null");
        } else {
            this.a = bitmap;
            this.b = cVar;
        }
    }

    public Bitmap a() {
        return this.a;
    }

    public int c() {
        return h.a(this.a);
    }

    public void d() {
        if (!this.b.a(this.a)) {
            this.a.recycle();
        }
    }
}
