package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.c.a;
import com.nostra13.universalimageloader.core.c.b;
import com.nostra13.universalimageloader.core.d.c;
import com.nostra13.universalimageloader.core.d.f;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import pl.droidsonroids.gif.GifDrawable;

public class d {
    public static final String a = d.class.getSimpleName();
    private static volatile d e;
    private e b;
    private f c;
    private c d = new f();
    private volatile boolean f;
    private Map<Integer, String> g = new HashMap();
    private List<WeakReference<GifDrawable>> h = new ArrayList();

    public static d a() {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d();
                }
            }
        }
        return e;
    }

    protected d() {
    }

    public synchronized void a(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else if (this.b == null) {
            e.a("Initialize ImageLoader with configuration", new Object[0]);
            this.c = new f(eVar);
            this.b = eVar;
        } else {
            e.c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    public boolean b() {
        return this.b != null;
    }

    public void a(String str, a aVar, c cVar) {
        a(str, aVar, cVar, null, null);
    }

    public void a(String str, a aVar, c cVar, c cVar2) {
        a(str, aVar, cVar, cVar2, null);
    }

    public void a(String str, a aVar, c cVar, c cVar2, com.nostra13.universalimageloader.core.d.d dVar) {
        l();
        if (aVar == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        c cVar3;
        c cVar4;
        if (cVar2 == null) {
            cVar3 = this.d;
        } else {
            cVar3 = cVar2;
        }
        if (cVar == null) {
            cVar4 = this.b.s;
        } else {
            cVar4 = cVar;
        }
        if (TextUtils.isEmpty(str)) {
            this.c.b(aVar);
            cVar3.onLoadingStarted(str, aVar.d());
            if (cVar4.b()) {
                aVar.a(cVar4.b(this.b.a));
            } else {
                aVar.a(null);
            }
            if (cVar3 instanceof com.nostra13.universalimageloader.core.d.a) {
                ((com.nostra13.universalimageloader.core.d.a) cVar3).onLoadingComplete(str, aVar.d(), null, null);
                return;
            } else {
                cVar3.onLoadingComplete(str, aVar.d(), null);
                return;
            }
        }
        Bitmap bitmap;
        com.nostra13.universalimageloader.core.assist.c a = com.nostra13.universalimageloader.b.c.a(aVar, this.b.a());
        String a2 = com.nostra13.universalimageloader.b.f.a(str, a);
        this.c.a(aVar, a2);
        cVar3.onLoadingStarted(str, aVar.d());
        GifDrawable gifDrawable = null;
        Object obj = 1;
        if (str.endsWith(".gif")) {
            Object valueOf;
            if (aVar.d() != null) {
                valueOf = Integer.valueOf(aVar.d().hashCode());
            } else {
                valueOf = null;
            }
            GifDrawable gifDrawable2 = (GifDrawable) this.b.o.a(a2);
            if (!(gifDrawable2 == null || valueOf == null)) {
                this.g.remove(valueOf);
                if (this.g.containsValue(a2)) {
                    obj = null;
                    a(a2);
                }
            }
            if (valueOf != null) {
                this.g.put(valueOf, a2);
            }
            bitmap = null;
            gifDrawable = gifDrawable2;
            Object obj2 = obj;
        } else {
            bitmap = (Bitmap) this.b.n.a(a2);
            int i = 1;
        }
        if ((bitmap == null || bitmap.isRecycled()) && (gifDrawable == null || r0 == null)) {
            if (cVar4.a()) {
                aVar.a(cVar4.a(this.b.a));
            } else if (cVar4.g()) {
                aVar.a(null);
            }
            h hVar = new h(this.c, new g(str, aVar, a, a2, cVar4, cVar3, dVar, this.c.a(str)), a(cVar4));
            if (cVar4.s()) {
                hVar.run();
                return;
            } else {
                this.c.a(hVar);
                return;
            }
        }
        e.a("Load image from memory cache [%s]", new Object[]{a2});
        e.a("Load image from gif memory cache [%s]", new Object[]{a2});
        if (cVar4.e()) {
            g gVar = new g(str, aVar, a, a2, cVar4, cVar3, dVar, this.c.a(str));
            i iVar = new i(this.c, bitmap, gifDrawable, gVar, a(cVar4));
            if (cVar4.s()) {
                iVar.run();
                return;
            } else {
                this.c.a(iVar);
                return;
            }
        }
        if (bitmap != null) {
            cVar4.q().a(bitmap, aVar, LoadedFrom.MEMORY_CACHE);
        } else if (gifDrawable != null) {
            cVar4.q().a(gifDrawable, aVar, LoadedFrom.MEMORY_CACHE);
        }
        if (cVar3 instanceof com.nostra13.universalimageloader.core.d.a) {
            ((com.nostra13.universalimageloader.core.d.a) cVar3).onLoadingComplete(str, aVar.d(), bitmap, gifDrawable);
        } else {
            cVar3.onLoadingComplete(str, aVar.d(), bitmap);
        }
    }

    public void a(String str, ImageView imageView, c cVar) {
        a(str, new b(imageView), cVar, null, null);
    }

    public void a(String str, ImageView imageView, c cVar, c cVar2) {
        a(str, imageView, cVar, cVar2, null);
    }

    public void a(String str, ImageView imageView, c cVar, c cVar2, com.nostra13.universalimageloader.core.d.d dVar) {
        a(str, new b(imageView), cVar, cVar2, dVar);
    }

    public void a(String str, c cVar, c cVar2) {
        a(str, null, cVar, cVar2, null);
    }

    public void a(String str, com.nostra13.universalimageloader.core.assist.c cVar, c cVar2, c cVar3) {
        a(str, cVar, cVar2, cVar3, null);
    }

    public void a(String str, com.nostra13.universalimageloader.core.assist.c cVar, c cVar2, c cVar3, com.nostra13.universalimageloader.core.d.d dVar) {
        c cVar4;
        l();
        if (cVar == null) {
            cVar = this.b.a();
        }
        if (cVar2 == null) {
            cVar4 = this.b.s;
        } else {
            cVar4 = cVar2;
        }
        a(str, new com.nostra13.universalimageloader.core.c.c(str, cVar, ViewScaleType.CROP), cVar4, cVar3, dVar);
    }

    private void l() {
        if (this.b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    public void c() {
        l();
        this.b.n.b();
    }

    public void d() {
        l();
        this.b.o.b();
    }

    public com.nostra13.universalimageloader.a.a.b e() {
        l();
        return this.b.p;
    }

    public void f() {
        l();
        this.b.p.a();
    }

    public void g() {
        this.c.a();
    }

    public void h() {
        this.c.b();
    }

    private static Handler a(c cVar) {
        Handler r = cVar.r();
        if (cVar.s()) {
            return null;
        }
        if (r == null && Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        return r;
    }

    public void a(String str) {
        Set<Entry> entrySet = this.g.entrySet();
        List<Integer> arrayList = new ArrayList();
        for (Entry entry : entrySet) {
            if (str.equals(entry.getValue())) {
                arrayList.add(entry.getKey());
            }
        }
        for (Integer remove : arrayList) {
            this.g.remove(remove);
        }
    }

    public boolean i() {
        return this.f;
    }

    public void a(GifDrawable gifDrawable) {
        if (gifDrawable != null) {
            GifDrawable gifDrawable2;
            for (int size = this.h.size() - 1; size >= 0; size--) {
                gifDrawable2 = (GifDrawable) ((WeakReference) this.h.get(size)).get();
                if (gifDrawable2 == null) {
                    this.h.remove(size);
                } else if (gifDrawable2 == gifDrawable) {
                    return;
                }
            }
            if (this.c.c().get()) {
                gifDrawable.pause();
            }
            if (this.h.size() > 4) {
                gifDrawable2 = (GifDrawable) ((WeakReference) this.h.remove(0)).get();
                if (!(gifDrawable2 == null || gifDrawable2.isPlaying())) {
                    gifDrawable2.start();
                }
            }
            this.h.add(new WeakReference(gifDrawable));
        }
    }

    public void j() {
        if (!this.f) {
            for (int size = this.h.size() - 1; size >= 0; size--) {
                GifDrawable gifDrawable = (GifDrawable) ((WeakReference) this.h.get(size)).get();
                if (gifDrawable == null) {
                    this.h.remove(size);
                } else if (gifDrawable.isPlaying()) {
                    gifDrawable.pause();
                }
            }
            this.f = true;
        }
    }

    public void k() {
        if (this.f) {
            for (int size = this.h.size() - 1; size >= 0; size--) {
                GifDrawable gifDrawable = (GifDrawable) ((WeakReference) this.h.get(size)).get();
                if (gifDrawable == null) {
                    this.h.remove(size);
                } else if (!gifDrawable.isPlaying()) {
                    gifDrawable.start();
                }
            }
            this.f = false;
        }
    }
}
