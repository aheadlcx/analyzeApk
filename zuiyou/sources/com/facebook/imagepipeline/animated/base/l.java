package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import com.facebook.common.references.a;
import java.util.List;

public class l {
    private final i a;
    private a<Bitmap> b;
    private List<a<Bitmap>> c;
    private int d;

    l(i iVar) {
        this.a = iVar;
    }

    public i a() {
        return this.a;
    }

    public a<Bitmap> b() {
        return a.b(this.b);
    }

    public l a(a<Bitmap> aVar) {
        this.b = a.b(aVar);
        return this;
    }

    public int c() {
        return this.d;
    }

    public l a(int i) {
        this.d = i;
        return this;
    }

    public List<a<Bitmap>> d() {
        return a.a(this.c);
    }

    public l a(List<a<Bitmap>> list) {
        this.c = a.a(list);
        return this;
    }

    public k e() {
        try {
            k kVar = new k(this);
            return kVar;
        } finally {
            a.c(this.b);
            this.b = null;
            a.a(this.c);
            this.c = null;
        }
    }
}
