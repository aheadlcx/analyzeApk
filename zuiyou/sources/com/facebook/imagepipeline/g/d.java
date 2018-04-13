package com.facebook.imagepipeline.g;

import android.graphics.Bitmap;
import com.facebook.common.internal.g;
import com.facebook.common.references.a;
import com.facebook.common.references.c;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class d extends b {
    @GuardedBy
    private a<Bitmap> a;
    private volatile Bitmap b;
    private final h c;
    private final int d;

    public d(Bitmap bitmap, c<Bitmap> cVar, h hVar, int i) {
        this.b = (Bitmap) g.a((Object) bitmap);
        this.a = a.a(this.b, (c) g.a((Object) cVar));
        this.c = hVar;
        this.d = i;
    }

    public d(a<Bitmap> aVar, h hVar, int i) {
        this.a = (a) g.a(aVar.c());
        this.b = (Bitmap) this.a.a();
        this.c = hVar;
        this.d = i;
    }

    public void close() {
        a i = i();
        if (i != null) {
            i.close();
        }
    }

    private synchronized a<Bitmap> i() {
        a<Bitmap> aVar;
        aVar = this.a;
        this.a = null;
        this.b = null;
        return aVar;
    }

    public synchronized boolean c() {
        return this.a == null;
    }

    public Bitmap f() {
        return this.b;
    }

    public int d() {
        return com.facebook.d.a.a(this.b);
    }

    public int a() {
        if (this.d == 90 || this.d == 270) {
            return b(this.b);
        }
        return a(this.b);
    }

    public int b() {
        if (this.d == 90 || this.d == 270) {
            return a(this.b);
        }
        return b(this.b);
    }

    private static int a(@Nullable Bitmap bitmap) {
        return bitmap == null ? 0 : bitmap.getWidth();
    }

    private static int b(@Nullable Bitmap bitmap) {
        return bitmap == null ? 0 : bitmap.getHeight();
    }

    public int h() {
        return this.d;
    }

    public h g() {
        return this.c;
    }
}
