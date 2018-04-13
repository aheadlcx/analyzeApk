package com.bumptech.glide.load.resource.e;

import android.graphics.Bitmap;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.load.resource.bitmap.j;

public class a implements c<com.bumptech.glide.load.resource.d.a, b> {
    private final c<Bitmap, j> a;

    public a(c<Bitmap, j> cVar) {
        this.a = cVar;
    }

    public com.bumptech.glide.load.engine.j<b> a(com.bumptech.glide.load.engine.j<com.bumptech.glide.load.resource.d.a> jVar) {
        com.bumptech.glide.load.resource.d.a aVar = (com.bumptech.glide.load.resource.d.a) jVar.b();
        com.bumptech.glide.load.engine.j b = aVar.b();
        if (b != null) {
            return this.a.a(b);
        }
        return aVar.c();
    }

    public String a() {
        return "GifBitmapWrapperDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
