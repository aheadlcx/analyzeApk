package com.bumptech.glide.load.resource.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.i.h;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Queue;

public class i implements d<InputStream, b> {
    private static final b a = new b();
    private static final a b = new a();
    private final Context c;
    private final b d;
    private final c e;
    private final a f;
    private final a g;

    static class a {
        private final Queue<com.bumptech.glide.b.a> a = h.a(0);

        a() {
        }

        public synchronized com.bumptech.glide.b.a a(com.bumptech.glide.b.a.a aVar) {
            com.bumptech.glide.b.a aVar2;
            aVar2 = (com.bumptech.glide.b.a) this.a.poll();
            if (aVar2 == null) {
                aVar2 = new com.bumptech.glide.b.a(aVar);
            }
            return aVar2;
        }

        public synchronized void a(com.bumptech.glide.b.a aVar) {
            aVar.i();
            this.a.offer(aVar);
        }
    }

    static class b {
        private final Queue<com.bumptech.glide.b.d> a = h.a(0);

        b() {
        }

        public synchronized com.bumptech.glide.b.d a(byte[] bArr) {
            com.bumptech.glide.b.d dVar;
            dVar = (com.bumptech.glide.b.d) this.a.poll();
            if (dVar == null) {
                dVar = new com.bumptech.glide.b.d();
            }
            return dVar.a(bArr);
        }

        public synchronized void a(com.bumptech.glide.b.d dVar) {
            dVar.a();
            this.a.offer(dVar);
        }
    }

    public i(Context context, c cVar) {
        this(context, cVar, a, b);
    }

    i(Context context, c cVar, b bVar, a aVar) {
        this.c = context;
        this.e = cVar;
        this.f = aVar;
        this.g = new a(cVar);
        this.d = bVar;
    }

    public d a(InputStream inputStream, int i, int i2) {
        byte[] a = a(inputStream);
        com.bumptech.glide.b.d a2 = this.d.a(a);
        com.bumptech.glide.b.a a3 = this.f.a(this.g);
        try {
            d a4 = a(a, i, i2, a2, a3);
            return a4;
        } finally {
            this.d.a(a2);
            this.f.a(a3);
        }
    }

    private d a(byte[] bArr, int i, int i2, com.bumptech.glide.b.d dVar, com.bumptech.glide.b.a aVar) {
        com.bumptech.glide.b.c b = dVar.b();
        if (b.a() <= 0 || b.b() != 0) {
            return null;
        }
        Bitmap a = a(aVar, b, bArr);
        if (a == null) {
            return null;
        }
        return new d(new b(this.c, this.g, this.e, com.bumptech.glide.load.resource.d.b(), i, i2, b, bArr, a));
    }

    private Bitmap a(com.bumptech.glide.b.a aVar, com.bumptech.glide.b.c cVar, byte[] bArr) {
        aVar.a(cVar, bArr);
        aVar.c();
        return aVar.h();
    }

    public String a() {
        return "";
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
        } catch (Throwable e) {
            Log.w("GifResourceDecoder", "Error reading data from stream", e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
