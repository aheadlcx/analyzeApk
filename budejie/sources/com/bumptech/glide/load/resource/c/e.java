package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.f;

public class e implements f<b> {
    private final f<Bitmap> a;
    private final c b;

    public e(f<Bitmap> fVar, c cVar) {
        this.a = fVar;
        this.b = cVar;
    }

    public j<b> a(j<b> jVar, int i, int i2) {
        b bVar = (b) jVar.b();
        Bitmap b = ((b) jVar.b()).b();
        Bitmap bitmap = (Bitmap) this.a.a(new com.bumptech.glide.load.resource.bitmap.c(b, this.b), i, i2).b();
        if (bitmap.equals(b)) {
            return jVar;
        }
        return new d(new b(bVar, bitmap, this.a));
    }

    public String a() {
        return this.a.a();
    }
}
