package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.c.b;
import com.bumptech.glide.load.resource.c.e;
import java.io.InputStream;

public class g<ModelType> extends e<ModelType, InputStream, b, b> {
    public /* synthetic */ e b(int i, int i2) {
        return a(i, i2);
    }

    public /* synthetic */ e b(a aVar) {
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
        return a(fVarArr);
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

    g(com.bumptech.glide.f.f<ModelType, InputStream, b, b> fVar, Class<b> cls, e<ModelType, ?, ?, ?> eVar) {
        super(fVar, cls, eVar);
    }

    public g<ModelType> a(d<InputStream, b> dVar) {
        super.b(dVar);
        return this;
    }

    public g<ModelType> a() {
        return a(this.c.c());
    }

    public g<ModelType> b() {
        return a(this.c.d());
    }

    public g<ModelType> a(com.bumptech.glide.load.resource.bitmap.d... dVarArr) {
        return a(c((f[]) dVarArr));
    }

    private e[] c(f<Bitmap>[] fVarArr) {
        e[] eVarArr = new e[fVarArr.length];
        for (int i = 0; i < fVarArr.length; i++) {
            eVarArr[i] = new e(fVarArr[i], this.c.a());
        }
        return eVarArr;
    }

    public g<ModelType> a(f<b>... fVarArr) {
        super.b(fVarArr);
        return this;
    }

    public g<ModelType> c() {
        super.a(new com.bumptech.glide.g.a.a());
        return this;
    }

    public g<ModelType> d() {
        super.h();
        return this;
    }

    public g<ModelType> a(int i) {
        super.d(i);
        return this;
    }

    public g<ModelType> a(Drawable drawable) {
        super.d(drawable);
        return this;
    }

    public g<ModelType> b(int i) {
        super.c(i);
        return this;
    }

    public g<ModelType> b(Drawable drawable) {
        super.c(drawable);
        return this;
    }

    public g<ModelType> a(boolean z) {
        super.b(z);
        return this;
    }

    public g<ModelType> a(DiskCacheStrategy diskCacheStrategy) {
        super.b(diskCacheStrategy);
        return this;
    }

    public g<ModelType> a(int i, int i2) {
        super.b(i, i2);
        return this;
    }

    public g<ModelType> a(a<InputStream> aVar) {
        super.b(aVar);
        return this;
    }

    public g<ModelType> a(com.bumptech.glide.load.b bVar) {
        super.b(bVar);
        return this;
    }

    public g<ModelType> a(ModelType modelType) {
        super.b(modelType);
        return this;
    }

    public g<ModelType> i() {
        return (g) super.g();
    }

    void e() {
        b();
    }

    void f() {
        a();
    }
}
