package com.bumptech.glide.load.resource.e;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.k;

public class b implements c<Bitmap, j> {
    private final Resources a;
    private final c b;

    public b(Resources resources, c cVar) {
        this.a = resources;
        this.b = cVar;
    }

    public com.bumptech.glide.load.engine.j<j> a(com.bumptech.glide.load.engine.j<Bitmap> jVar) {
        return new k(new j(this.a, (Bitmap) jVar.b()), this.b);
    }

    public String a() {
        return "GlideBitmapDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
