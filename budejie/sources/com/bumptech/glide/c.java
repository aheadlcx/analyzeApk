package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.d.m;
import com.bumptech.glide.g.b.j;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.load.resource.d.a;

public class c<ModelType> extends e<ModelType, g, a, b> {
    public /* synthetic */ e b(int i, int i2) {
        return a(i, i2);
    }

    public /* synthetic */ e b(com.bumptech.glide.load.a aVar) {
        return a(aVar);
    }

    public /* synthetic */ e b(com.bumptech.glide.load.b bVar) {
        return a(bVar);
    }

    public /* synthetic */ e b(d dVar) {
        return a(dVar);
    }

    public /* synthetic */ e b(DiskCacheStrategy diskCacheStrategy) {
        return a(diskCacheStrategy);
    }

    public /* synthetic */ e b(Object obj) {
        return a(obj);
    }

    public /* synthetic */ e b(boolean z) {
        return a(z);
    }

    public /* synthetic */ e b(f[] fVarArr) {
        return c(fVarArr);
    }

    public /* synthetic */ e c(int i) {
        return b(i);
    }

    public /* synthetic */ e c(Drawable drawable) {
        return b(drawable);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return i();
    }

    public /* synthetic */ e d(int i) {
        return a(i);
    }

    public /* synthetic */ e d(Drawable drawable) {
        return a(drawable);
    }

    public /* synthetic */ e g() {
        return i();
    }

    public /* synthetic */ e h() {
        return d();
    }

    c(Context context, Class<ModelType> cls, com.bumptech.glide.f.f<ModelType, g, a, b> fVar, i iVar, m mVar, com.bumptech.glide.d.g gVar) {
        super(context, cls, fVar, b.class, iVar, mVar, gVar);
        c();
    }

    public c<ModelType> a(d<g, a> dVar) {
        super.b((d) dVar);
        return this;
    }

    public c<ModelType> a() {
        return c(this.c.e());
    }

    public c<ModelType> b() {
        return c(this.c.f());
    }

    public c<ModelType> a(f<Bitmap>... fVarArr) {
        f[] fVarArr2 = new com.bumptech.glide.load.resource.d.f[fVarArr.length];
        for (int i = 0; i < fVarArr.length; i++) {
            fVarArr2[i] = new com.bumptech.glide.load.resource.d.f(this.c.a(), fVarArr[i]);
        }
        return c(fVarArr2);
    }

    public c<ModelType> c(f<a>... fVarArr) {
        super.b((f[]) fVarArr);
        return this;
    }

    public final c<ModelType> c() {
        super.a(new com.bumptech.glide.g.a.a());
        return this;
    }

    public c<ModelType> d() {
        super.h();
        return this;
    }

    public c<ModelType> a(int i) {
        super.d(i);
        return this;
    }

    public c<ModelType> a(Drawable drawable) {
        super.d(drawable);
        return this;
    }

    public c<ModelType> b(int i) {
        super.c(i);
        return this;
    }

    public c<ModelType> b(Drawable drawable) {
        super.c(drawable);
        return this;
    }

    public c<ModelType> a(DiskCacheStrategy diskCacheStrategy) {
        super.b(diskCacheStrategy);
        return this;
    }

    public c<ModelType> a(boolean z) {
        super.b(z);
        return this;
    }

    public c<ModelType> a(int i, int i2) {
        super.b(i, i2);
        return this;
    }

    public c<ModelType> a(com.bumptech.glide.load.a<g> aVar) {
        super.b((com.bumptech.glide.load.a) aVar);
        return this;
    }

    public c<ModelType> a(com.bumptech.glide.load.b bVar) {
        super.b(bVar);
        return this;
    }

    public c<ModelType> a(ModelType modelType) {
        super.b((Object) modelType);
        return this;
    }

    public c<ModelType> i() {
        return (c) super.g();
    }

    public j<b> a(ImageView imageView) {
        return super.a(imageView);
    }

    void e() {
        b();
    }

    void f() {
        a();
    }
}
