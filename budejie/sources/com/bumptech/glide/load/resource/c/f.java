package com.bumptech.glide.load.resource.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.e;
import com.bumptech.glide.g.b.g;
import com.bumptech.glide.i;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

class f {
    private final b a;
    private final com.bumptech.glide.b.a b;
    private final Handler c;
    private boolean d;
    private boolean e;
    private e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> f;
    private a g;
    private boolean h;

    public interface b {
        void b(int i);
    }

    static class a extends g<Bitmap> {
        private final Handler a;
        private final int b;
        private final long c;
        private Bitmap d;

        public /* synthetic */ void onResourceReady(Object obj, com.bumptech.glide.g.a.c cVar) {
            a((Bitmap) obj, cVar);
        }

        public a(Handler handler, int i, long j) {
            this.a = handler;
            this.b = i;
            this.c = j;
        }

        public Bitmap a() {
            return this.d;
        }

        public void a(Bitmap bitmap, com.bumptech.glide.g.a.c<? super Bitmap> cVar) {
            this.d = bitmap;
            this.a.sendMessageAtTime(this.a.obtainMessage(1, this), this.c);
        }
    }

    private class c implements Callback {
        final /* synthetic */ f a;

        private c(f fVar) {
            this.a = fVar;
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                this.a.a((a) message.obj);
                return true;
            }
            if (message.what == 2) {
                i.a((a) message.obj);
            }
            return false;
        }
    }

    static class d implements com.bumptech.glide.load.b {
        private final UUID a;

        public d() {
            this(UUID.randomUUID());
        }

        d(UUID uuid) {
            this.a = uuid;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return ((d) obj).a.equals(this.a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    public f(Context context, b bVar, com.bumptech.glide.b.a aVar, int i, int i2) {
        this(bVar, aVar, null, a(context, aVar, i, i2, i.a(context).a()));
    }

    f(b bVar, com.bumptech.glide.b.a aVar, Handler handler, e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> eVar) {
        this.d = false;
        this.e = false;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper(), new c());
        }
        this.a = bVar;
        this.b = aVar;
        this.c = handler;
        this.f = eVar;
    }

    public void a(com.bumptech.glide.load.f<Bitmap> fVar) {
        if (fVar == null) {
            throw new NullPointerException("Transformation must not be null");
        }
        this.f = this.f.b(new com.bumptech.glide.load.f[]{fVar});
    }

    public void a() {
        if (!this.d) {
            this.d = true;
            this.h = false;
            e();
        }
    }

    public void b() {
        this.d = false;
    }

    public void c() {
        b();
        if (this.g != null) {
            i.a(this.g);
            this.g = null;
        }
        this.h = true;
    }

    public Bitmap d() {
        return this.g != null ? this.g.a() : null;
    }

    private void e() {
        if (this.d && !this.e) {
            this.e = true;
            this.b.c();
            this.f.b(new d()).a(new a(this.c, this.b.f(), SystemClock.uptimeMillis() + ((long) this.b.d())));
        }
    }

    void a(a aVar) {
        if (this.h) {
            this.c.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        a aVar2 = this.g;
        this.g = aVar;
        this.a.b(aVar.b);
        if (aVar2 != null) {
            this.c.obtainMessage(2, aVar2).sendToTarget();
        }
        this.e = false;
        e();
    }

    private static e<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> a(Context context, com.bumptech.glide.b.a aVar, int i, int i2, com.bumptech.glide.load.engine.a.c cVar) {
        com.bumptech.glide.load.d hVar = new h(cVar);
        l gVar = new g();
        return i.b(context).a(gVar, com.bumptech.glide.b.a.class).a((Object) aVar).a(Bitmap.class).b(com.bumptech.glide.load.resource.a.b()).b(hVar).b(true).b(DiskCacheStrategy.NONE).b(i, i2);
    }
}
