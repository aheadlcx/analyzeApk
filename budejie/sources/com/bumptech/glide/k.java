package com.bumptech.glide;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.d.d;
import com.bumptech.glide.d.g;
import com.bumptech.glide.d.h;
import com.bumptech.glide.d.l;
import com.bumptech.glide.d.m;

public class k implements h {
    private final Context a;
    private final g b;
    private final l c;
    private final m d;
    private final i e;
    private final k$c f;
    private k$a g;

    public k(Context context, g gVar, l lVar) {
        this(context, gVar, lVar, new m(), new d());
    }

    k(Context context, g gVar, l lVar, m mVar, d dVar) {
        this.a = context.getApplicationContext();
        this.b = gVar;
        this.c = lVar;
        this.d = mVar;
        this.e = i.a(context);
        this.f = new k$c(this);
        h a = dVar.a(context, new k$d(mVar));
        if (com.bumptech.glide.i.h.d()) {
            new Handler(Looper.getMainLooper()).post(new k$1(this, gVar));
        } else {
            gVar.a(this);
        }
        gVar.a(a);
    }

    public void a(int i) {
        this.e.a(i);
    }

    public void a() {
        this.e.i();
    }

    public void b() {
        com.bumptech.glide.i.h.a();
        this.d.a();
    }

    public void c() {
        com.bumptech.glide.i.h.a();
        this.d.b();
    }

    public void onStart() {
        c();
    }

    public void onStop() {
        b();
    }

    public void onDestroy() {
        this.d.c();
    }

    public <A, T> k$b<A, T> a(com.bumptech.glide.load.b.l<A, T> lVar, Class<T> cls) {
        return new k$b(this, lVar, cls);
    }

    public d<String> a(String str) {
        return (d) d().a((Object) str);
    }

    public d<String> d() {
        return a(String.class);
    }

    private <T> d<T> a(Class<T> cls) {
        com.bumptech.glide.load.b.l a = i.a((Class) cls, this.a);
        com.bumptech.glide.load.b.l b = i.b((Class) cls, this.a);
        if (cls != null && a == null && b == null) {
            throw new IllegalArgumentException("Unknown type " + cls + ". You must provide a Model of a type for" + " which there is a registered ModelLoader, if you are using a custom model, you must first call" + " Glide#register with a ModelLoaderFactory for your custom model class");
        }
        return (d) this.f.a(new d(cls, a, b, this.a, this.e, this.d, this.b, this.f));
    }

    private static <T> Class<T> b(T t) {
        return t != null ? t.getClass() : null;
    }
}
