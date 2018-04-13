package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.b.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.f;
import java.io.OutputStream;

public class j implements e<b> {
    private static final a a = new a();
    private final com.bumptech.glide.b.a.a b;
    private final c c;
    private final a d;

    static class a {
        a() {
        }

        public com.bumptech.glide.b.a a(com.bumptech.glide.b.a.a aVar) {
            return new com.bumptech.glide.b.a(aVar);
        }

        public d a() {
            return new d();
        }

        public com.bumptech.glide.c.a b() {
            return new com.bumptech.glide.c.a();
        }

        public com.bumptech.glide.load.engine.j<Bitmap> a(Bitmap bitmap, c cVar) {
            return new com.bumptech.glide.load.resource.bitmap.c(bitmap, cVar);
        }
    }

    public j(c cVar) {
        this(cVar, a);
    }

    j(c cVar, a aVar) {
        this.c = cVar;
        this.b = new a(cVar);
        this.d = aVar;
    }

    public boolean a(com.bumptech.glide.load.engine.j<b> jVar, OutputStream outputStream) {
        long a = com.bumptech.glide.i.d.a();
        b bVar = (b) jVar.b();
        f d = bVar.d();
        if (d instanceof com.bumptech.glide.load.resource.d) {
            return a(bVar.e(), outputStream);
        }
        com.bumptech.glide.b.a a2 = a(bVar.e());
        com.bumptech.glide.c.a b = this.d.b();
        if (!b.a(outputStream)) {
            return false;
        }
        int i = 0;
        while (i < a2.e()) {
            com.bumptech.glide.load.engine.j a3 = a(a2.h(), d, bVar);
            if (b.a((Bitmap) a3.b())) {
                try {
                    b.a(a2.a(a2.f()));
                    a2.c();
                    a3.d();
                    i++;
                } catch (Throwable th) {
                    a3.d();
                    throw th;
                }
            }
            a3.d();
            return false;
        }
        boolean a4 = b.a();
        if (!Log.isLoggable("GifEncoder", 2)) {
            return a4;
        }
        Log.v("GifEncoder", "Encoded gif with " + a2.e() + " frames and " + bVar.e().length + " bytes in " + com.bumptech.glide.i.d.a(a) + " ms");
        return a4;
    }

    private boolean a(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (Throwable e) {
            if (Log.isLoggable("GifEncoder", 3)) {
                Log.d("GifEncoder", "Failed to write data to output stream in GifResourceEncoder", e);
            }
            return false;
        }
    }

    private com.bumptech.glide.b.a a(byte[] bArr) {
        d a = this.d.a();
        a.a(bArr);
        com.bumptech.glide.b.c b = a.b();
        com.bumptech.glide.b.a a2 = this.d.a(this.b);
        a2.a(b, bArr);
        a2.c();
        return a2;
    }

    private com.bumptech.glide.load.engine.j<Bitmap> a(Bitmap bitmap, f<Bitmap> fVar, b bVar) {
        com.bumptech.glide.load.engine.j a = this.d.a(bitmap, this.c);
        com.bumptech.glide.load.engine.j<Bitmap> a2 = fVar.a(a, bVar.getIntrinsicWidth(), bVar.getIntrinsicHeight());
        if (!a.equals(a2)) {
            a.d();
        }
        return a2;
    }

    public String a() {
        return "";
    }
}
