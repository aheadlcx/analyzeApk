package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class c implements d<g, a> {
    private static final b a = new b();
    private static final a b = new a();
    private final d<g, Bitmap> c;
    private final d<InputStream, com.bumptech.glide.load.resource.c.b> d;
    private final com.bumptech.glide.load.engine.a.c e;
    private final b f;
    private final a g;
    private String h;

    static class a {
        a() {
        }

        public InputStream a(InputStream inputStream, byte[] bArr) {
            return new RecyclableBufferedInputStream(inputStream, bArr);
        }
    }

    static class b {
        b() {
        }

        public ImageType a(InputStream inputStream) throws IOException {
            return new ImageHeaderParser(inputStream).b();
        }
    }

    public c(d<g, Bitmap> dVar, d<InputStream, com.bumptech.glide.load.resource.c.b> dVar2, com.bumptech.glide.load.engine.a.c cVar) {
        this(dVar, dVar2, cVar, a, b);
    }

    c(d<g, Bitmap> dVar, d<InputStream, com.bumptech.glide.load.resource.c.b> dVar2, com.bumptech.glide.load.engine.a.c cVar, b bVar, a aVar) {
        this.c = dVar;
        this.d = dVar2;
        this.e = cVar;
        this.f = bVar;
        this.g = aVar;
    }

    public j<a> a(g gVar, int i, int i2) throws IOException {
        com.bumptech.glide.i.a a = com.bumptech.glide.i.a.a();
        byte[] b = a.b();
        try {
            a a2 = a(gVar, i, i2, b);
            return a2 != null ? new b(a2) : null;
        } finally {
            a.a(b);
        }
    }

    private a a(g gVar, int i, int i2, byte[] bArr) throws IOException {
        if (gVar.a() != null) {
            return b(gVar, i, i2, bArr);
        }
        return b(gVar, i, i2);
    }

    private a b(g gVar, int i, int i2, byte[] bArr) throws IOException {
        InputStream a = this.g.a(gVar.a(), bArr);
        a.mark(2048);
        ImageType a2 = this.f.a(a);
        a.reset();
        a aVar = null;
        if (a2 == ImageType.GIF) {
            aVar = a(a, i, i2);
        }
        if (aVar == null) {
            return b(new g(a, gVar.b()), i, i2);
        }
        return aVar;
    }

    private a a(InputStream inputStream, int i, int i2) throws IOException {
        j a = this.d.a(inputStream, i, i2);
        if (a == null) {
            return null;
        }
        com.bumptech.glide.load.resource.c.b bVar = (com.bumptech.glide.load.resource.c.b) a.b();
        if (bVar.f() > 1) {
            return new a(null, a);
        }
        return new a(new com.bumptech.glide.load.resource.bitmap.c(bVar.b(), this.e), null);
    }

    private a b(g gVar, int i, int i2) throws IOException {
        j a = this.c.a(gVar, i, i2);
        if (a != null) {
            return new a(a, null);
        }
        return null;
    }

    public String a() {
        if (this.h == null) {
            this.h = this.d.a() + this.c.a();
        }
        return this.h;
    }
}
