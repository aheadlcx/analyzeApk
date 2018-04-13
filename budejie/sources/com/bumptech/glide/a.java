package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.bumptech.glide.g.b.j;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.resource.bitmap.f;
import com.bumptech.glide.load.resource.bitmap.h;
import com.bumptech.glide.load.resource.bitmap.o;
import java.io.InputStream;

public class a<ModelType, TranscodeType> extends e<ModelType, g, Bitmap, TranscodeType> {
    private final c g;
    private f h = f.a;
    private DecodeFormat i;
    private d<InputStream, Bitmap> j;
    private d<ParcelFileDescriptor, Bitmap> k;

    public /* synthetic */ e b(int i, int i2) {
        return a(i, i2);
    }

    public /* synthetic */ e b(com.bumptech.glide.load.a aVar) {
        return a(aVar);
    }

    public /* synthetic */ e b(b bVar) {
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

    public /* synthetic */ e b(com.bumptech.glide.load.f[] fVarArr) {
        return a(fVarArr);
    }

    public /* synthetic */ e c(int i) {
        return b(i);
    }

    public /* synthetic */ e c(Drawable drawable) {
        return b(drawable);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public /* synthetic */ e d(int i) {
        return a(i);
    }

    public /* synthetic */ e d(Drawable drawable) {
        return a(drawable);
    }

    public /* synthetic */ e g() {
        return d();
    }

    public /* synthetic */ e h() {
        return c();
    }

    a(com.bumptech.glide.f.f<ModelType, g, Bitmap, TranscodeType> fVar, Class<TranscodeType> cls, e<ModelType, ?, ?, ?> eVar) {
        super(fVar, cls, eVar);
        this.g = eVar.c.a();
        this.i = eVar.c.h();
        this.j = new o(this.g, this.i);
        this.k = new h(this.g, this.i);
    }

    public a<ModelType, TranscodeType> a(d<g, Bitmap> dVar) {
        super.b(dVar);
        return this;
    }

    public a<ModelType, TranscodeType> a(com.bumptech.glide.load.resource.bitmap.d... dVarArr) {
        super.b(dVarArr);
        return this;
    }

    public a<ModelType, TranscodeType> a() {
        return a(this.c.c());
    }

    public a<ModelType, TranscodeType> b() {
        return a(this.c.d());
    }

    public a<ModelType, TranscodeType> a(com.bumptech.glide.load.f<Bitmap>... fVarArr) {
        super.b(fVarArr);
        return this;
    }

    public a<ModelType, TranscodeType> c() {
        super.h();
        return this;
    }

    public a<ModelType, TranscodeType> a(int i) {
        super.d(i);
        return this;
    }

    public a<ModelType, TranscodeType> a(Drawable drawable) {
        super.d(drawable);
        return this;
    }

    public a<ModelType, TranscodeType> b(int i) {
        super.c(i);
        return this;
    }

    public a<ModelType, TranscodeType> b(Drawable drawable) {
        super.c(drawable);
        return this;
    }

    public a<ModelType, TranscodeType> a(boolean z) {
        super.b(z);
        return this;
    }

    public a<ModelType, TranscodeType> a(DiskCacheStrategy diskCacheStrategy) {
        super.b(diskCacheStrategy);
        return this;
    }

    public a<ModelType, TranscodeType> a(int i, int i2) {
        super.b(i, i2);
        return this;
    }

    public a<ModelType, TranscodeType> a(com.bumptech.glide.load.a<g> aVar) {
        super.b(aVar);
        return this;
    }

    public a<ModelType, TranscodeType> a(b bVar) {
        super.b(bVar);
        return this;
    }

    public a<ModelType, TranscodeType> a(ModelType modelType) {
        super.b(modelType);
        return this;
    }

    public a<ModelType, TranscodeType> d() {
        return (a) super.g();
    }

    public j<TranscodeType> a(ImageView imageView) {
        return super.a(imageView);
    }

    void e() {
        b();
    }

    void f() {
        a();
    }
}
