package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import com.facebook.common.internal.g;
import com.facebook.common.references.a;
import java.util.List;
import javax.annotation.Nullable;

public class k {
    private final i a;
    private final int b;
    @Nullable
    private a<Bitmap> c;
    @Nullable
    private List<a<Bitmap>> d;

    k(l lVar) {
        this.a = (i) g.a(lVar.a());
        this.b = lVar.c();
        this.c = lVar.b();
        this.d = lVar.d();
    }

    private k(i iVar) {
        this.a = (i) g.a(iVar);
        this.b = 0;
    }

    public static k a(i iVar) {
        return new k(iVar);
    }

    public static l b(i iVar) {
        return new l(iVar);
    }

    public i a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    @Nullable
    public synchronized a<Bitmap> a(int i) {
        a<Bitmap> b;
        if (this.d != null) {
            b = a.b((a) this.d.get(i));
        } else {
            b = null;
        }
        return b;
    }

    public synchronized boolean b(int i) {
        boolean z;
        z = (this.d == null || this.d.get(i) == null) ? false : true;
        return z;
    }

    public synchronized a<Bitmap> c() {
        return a.b(this.c);
    }

    public synchronized void d() {
        a.c(this.c);
        this.c = null;
        a.a(this.d);
        this.d = null;
    }
}
