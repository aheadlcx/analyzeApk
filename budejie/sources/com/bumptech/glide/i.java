package com.bumptech.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.d.k;
import com.bumptech.glide.g.b.f;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b.b.b$a;
import com.bumptech.glide.load.b.b.h;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.engine.b;
import com.bumptech.glide.load.engine.c.a;
import com.bumptech.glide.load.resource.bitmap.e;
import com.bumptech.glide.load.resource.bitmap.g;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.bitmap.n;
import com.bumptech.glide.load.resource.e.d;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class i {
    private static volatile i a;
    private final c b;
    private final b c;
    private final com.bumptech.glide.load.engine.a.c d;
    private final com.bumptech.glide.load.engine.b.i e;
    private final DecodeFormat f;
    private final f g = new f();
    private final d h = new d();
    private final com.bumptech.glide.f.c i;
    private final e j;
    private final com.bumptech.glide.load.resource.d.f k;
    private final com.bumptech.glide.load.resource.bitmap.i l;
    private final com.bumptech.glide.load.resource.d.f m;
    private final Handler n;
    private final a o;

    public static i a(Context context) {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    Context applicationContext = context.getApplicationContext();
                    List<com.bumptech.glide.e.a> a = new com.bumptech.glide.e.b(applicationContext).a();
                    j jVar = new j(applicationContext);
                    for (com.bumptech.glide.e.a applyOptions : a) {
                        applyOptions.applyOptions(applicationContext, jVar);
                    }
                    a = jVar.a();
                    for (com.bumptech.glide.e.a applyOptions2 : a) {
                        applyOptions2.registerComponents(applicationContext, a);
                    }
                }
            }
        }
        return a;
    }

    i(b bVar, com.bumptech.glide.load.engine.b.i iVar, com.bumptech.glide.load.engine.a.c cVar, Context context, DecodeFormat decodeFormat) {
        this.c = bVar;
        this.d = cVar;
        this.e = iVar;
        this.f = decodeFormat;
        this.b = new c(context);
        this.n = new Handler(Looper.getMainLooper());
        this.o = new a(iVar, cVar, decodeFormat);
        this.i = new com.bumptech.glide.f.c();
        com.bumptech.glide.f.b nVar = new n(cVar, decodeFormat);
        this.i.a(InputStream.class, Bitmap.class, nVar);
        com.bumptech.glide.f.b gVar = new g(cVar, decodeFormat);
        this.i.a(ParcelFileDescriptor.class, Bitmap.class, gVar);
        com.bumptech.glide.f.b mVar = new m(nVar, gVar);
        this.i.a(com.bumptech.glide.load.b.g.class, Bitmap.class, mVar);
        nVar = new com.bumptech.glide.load.resource.c.c(context, cVar);
        this.i.a(InputStream.class, com.bumptech.glide.load.resource.c.b.class, nVar);
        this.i.a(com.bumptech.glide.load.b.g.class, com.bumptech.glide.load.resource.d.a.class, new com.bumptech.glide.load.resource.d.g(mVar, nVar, cVar));
        this.i.a(InputStream.class, File.class, new com.bumptech.glide.load.resource.b.d());
        a(File.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.a.a());
        a(File.class, InputStream.class, new com.bumptech.glide.load.b.b.c.a());
        a(Integer.TYPE, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.TYPE, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(Integer.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.class, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(String.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.d.a());
        a(String.class, InputStream.class, new com.bumptech.glide.load.b.b.f.a());
        a(Uri.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.e.a());
        a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.b.g.a());
        a(URL.class, InputStream.class, new h.a());
        a(com.bumptech.glide.load.b.d.class, InputStream.class, new com.bumptech.glide.load.b.b.a.a());
        a(byte[].class, InputStream.class, new b$a());
        this.h.a(Bitmap.class, j.class, new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar));
        this.h.a(com.bumptech.glide.load.resource.d.a.class, com.bumptech.glide.load.resource.a.b.class, new com.bumptech.glide.load.resource.e.a(new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar)));
        this.j = new e(cVar);
        this.k = new com.bumptech.glide.load.resource.d.f(cVar, this.j);
        this.l = new com.bumptech.glide.load.resource.bitmap.i(cVar);
        this.m = new com.bumptech.glide.load.resource.d.f(cVar, this.l);
    }

    public com.bumptech.glide.load.engine.a.c a() {
        return this.d;
    }

    <Z, R> com.bumptech.glide.load.resource.e.c<Z, R> a(Class<Z> cls, Class<R> cls2) {
        return this.h.a(cls, cls2);
    }

    <T, Z> com.bumptech.glide.f.b<T, Z> b(Class<T> cls, Class<Z> cls2) {
        return this.i.a(cls, cls2);
    }

    <R> com.bumptech.glide.g.b.j<R> a(ImageView imageView, Class<R> cls) {
        return this.g.a(imageView, cls);
    }

    b b() {
        return this.c;
    }

    e c() {
        return this.j;
    }

    com.bumptech.glide.load.resource.bitmap.i d() {
        return this.l;
    }

    com.bumptech.glide.load.resource.d.f e() {
        return this.k;
    }

    com.bumptech.glide.load.resource.d.f f() {
        return this.m;
    }

    Handler g() {
        return this.n;
    }

    DecodeFormat h() {
        return this.f;
    }

    private c k() {
        return this.b;
    }

    public void i() {
        com.bumptech.glide.i.h.a();
        this.e.a();
        this.d.a();
    }

    public void a(int i) {
        com.bumptech.glide.i.h.a();
        this.e.a(i);
        this.d.a(i);
    }

    public void j() {
        com.bumptech.glide.i.h.b();
        b().a();
    }

    public static void a(com.bumptech.glide.g.b.j<?> jVar) {
        com.bumptech.glide.i.h.a();
        com.bumptech.glide.g.c request = jVar.getRequest();
        if (request != null) {
            request.d();
            jVar.setRequest(null);
        }
    }

    public <T, Y> void a(Class<T> cls, Class<Y> cls2, com.bumptech.glide.load.b.m<T, Y> mVar) {
        com.bumptech.glide.load.b.m a = this.b.a(cls, cls2, mVar);
        if (a != null) {
            a.a();
        }
    }

    public static <T, Y> l<T, Y> a(Class<T> cls, Class<Y> cls2, Context context) {
        if (cls != null) {
            return a(context).k().a(cls, cls2);
        }
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Unable to load null model, setting placeholder only");
        }
        return null;
    }

    public static <T> l<T, InputStream> a(Class<T> cls, Context context) {
        return a((Class) cls, InputStream.class, context);
    }

    public static <T> l<T, ParcelFileDescriptor> b(Class<T> cls, Context context) {
        return a((Class) cls, ParcelFileDescriptor.class, context);
    }

    public static k b(Context context) {
        return k.a().a(context);
    }

    public static k a(Activity activity) {
        return k.a().a(activity);
    }

    public static k a(FragmentActivity fragmentActivity) {
        return k.a().a(fragmentActivity);
    }

    public static k a(Fragment fragment) {
        return k.a().a(fragment);
    }
}
