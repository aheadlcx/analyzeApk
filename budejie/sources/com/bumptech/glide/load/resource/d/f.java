package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.c.b;
import com.bumptech.glide.load.resource.c.e;

public class f implements com.bumptech.glide.load.f<a> {
    private final com.bumptech.glide.load.f<Bitmap> a;
    private final com.bumptech.glide.load.f<b> b;

    public f(c cVar, com.bumptech.glide.load.f<Bitmap> fVar) {
        this((com.bumptech.glide.load.f) fVar, new e(fVar, cVar));
    }

    f(com.bumptech.glide.load.f<Bitmap> fVar, com.bumptech.glide.load.f<b> fVar2) {
        this.a = fVar;
        this.b = fVar2;
    }

    public j<a> a(j<a> jVar, int i, int i2) {
        j b = ((a) jVar.b()).b();
        j c = ((a) jVar.b()).c();
        if (b != null && this.a != null) {
            j a = this.a.a(b, i, i2);
            if (b.equals(a)) {
                return jVar;
            }
            return new b(new a(a, ((a) jVar.b()).c()));
        } else if (c == null || this.b == null) {
            return jVar;
        } else {
            b = this.b.a(c, i, i2);
            if (c.equals(b)) {
                return jVar;
            }
            return new b(new a(((a) jVar.b()).b(), b));
        }
    }

    public String a() {
        return this.a.a();
    }
}
