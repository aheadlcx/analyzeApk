package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.load.engine.a.c;

public class e extends d {
    public e(c cVar) {
        super(cVar);
    }

    protected Bitmap a(c cVar, Bitmap bitmap, int i, int i2) {
        Bitmap a = cVar.a(i, i2, bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888);
        Bitmap a2 = p.a(a, bitmap, i, i2);
        if (!(a == null || a == a2 || cVar.a(a))) {
            a.recycle();
        }
        return a2;
    }

    public String a() {
        return "CenterCrop.com.bumptech.glide.load.resource.bitmap";
    }
}
