package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.c.b;

public class a {
    private final j<b> a;
    private final j<Bitmap> b;

    public a(j<Bitmap> jVar, j<b> jVar2) {
        if (jVar != null && jVar2 != null) {
            throw new IllegalArgumentException("Can only contain either a bitmap resource or a gif resource, not both");
        } else if (jVar == null && jVar2 == null) {
            throw new IllegalArgumentException("Must contain either a bitmap resource or a gif resource");
        } else {
            this.b = jVar;
            this.a = jVar2;
        }
    }

    public int a() {
        if (this.b != null) {
            return this.b.c();
        }
        return this.a.c();
    }

    public j<Bitmap> b() {
        return this.b;
    }

    public j<b> c() {
        return this.a;
    }
}
