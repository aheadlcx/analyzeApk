package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.b.d$a;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.a.b;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason$FailType;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.d.c;
import com.nostra13.universalimageloader.core.d.d;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import pl.droidsonroids.gif.GifDrawable;

final class h implements d$a, Runnable {
    final String a;
    final com.nostra13.universalimageloader.core.c.a b;
    final c c;
    final c d;
    final d e;
    private final f f;
    private final g g;
    private final Handler h;
    private final e i;
    private final ImageDownloader j;
    private final ImageDownloader k;
    private final ImageDownloader l;
    private final b m;
    private final String n;
    private final com.nostra13.universalimageloader.core.assist.c o;
    private final boolean p;
    private LoadedFrom q = LoadedFrom.NETWORK;

    class a extends Exception {
        final /* synthetic */ h a;

        a(h hVar) {
            this.a = hVar;
        }
    }

    public h(f fVar, g gVar, Handler handler) {
        this.f = fVar;
        this.g = gVar;
        this.h = handler;
        this.i = fVar.a;
        this.j = this.i.q;
        this.k = this.i.t;
        this.l = this.i.u;
        this.m = this.i.r;
        this.a = gVar.a;
        this.n = gVar.b;
        this.b = gVar.c;
        this.o = gVar.d;
        this.c = gVar.e;
        this.d = gVar.f;
        this.e = gVar.g;
        this.p = this.c.s();
    }

    public void run() {
        if (!b() && !c()) {
            ReentrantLock reentrantLock = this.g.h;
            e.a("Start display image task [%s]", this.n);
            if (reentrantLock.isLocked()) {
                e.a("Image already is loading. Waiting... [%s]", this.n);
            }
            reentrantLock.lock();
            try {
                GifDrawable e;
                Bitmap bitmap;
                k();
                Bitmap bitmap2 = (Bitmap) this.i.n.a(this.n);
                GifDrawable gifDrawable = (GifDrawable) this.i.o.a(this.n);
                if (bitmap2 == null || bitmap2.isRecycled() || gifDrawable == null) {
                    File a = this.i.p.a(this.a);
                    if (this.a.endsWith(".gif") || (a != null && a.exists() && (a.getName().endsWith(".gif") || "gif".equals(com.nostra13.universalimageloader.b.b.a(a))))) {
                        e = e();
                        if (e == null || !e.d()) {
                            bitmap = bitmap2;
                        } else {
                            e.a();
                            e = null;
                            bitmap = d();
                        }
                    } else {
                        e = gifDrawable;
                        bitmap = d();
                    }
                    if (bitmap != null || e != null) {
                        k();
                        q();
                        if (this.c.d()) {
                            e.a("PreProcess image before caching in memory [%s]", this.n);
                            if (bitmap != null) {
                                bitmap = this.c.o().a(bitmap);
                            } else if (e != null) {
                                e = this.c.o().a(e);
                            }
                            if (bitmap == null) {
                                e.d("Pre-processor returned null [%s]", this.n);
                            }
                            if (e == null) {
                                e.d("Pre-processor returned null [%s]", this.n);
                            }
                        }
                        if (bitmap != null && this.c.h()) {
                            e.a("Cache image in memory [%s]", this.n);
                            this.i.n.a(this.n, bitmap);
                        }
                        if (e != null && this.c.h()) {
                            e.a("Cache image in gif memory [%s]", this.n);
                            this.i.o.a(this.n, e);
                        }
                    } else {
                        return;
                    }
                }
                this.q = LoadedFrom.MEMORY_CACHE;
                e.a("...Get cached bitmap from memory after waiting. [%s]", this.n);
                e = gifDrawable;
                bitmap = bitmap2;
                if (bitmap != null && this.c.e()) {
                    e.a("PostProcess image before displaying [%s]", this.n);
                    bitmap = this.c.p().a(bitmap);
                    if (bitmap == null) {
                        e.d("Post-processor returned null [%s]", this.n);
                    }
                } else if (e != null && this.c.e()) {
                    e.a("PostProcess image before displaying [%s]", this.n);
                    e = this.c.p().a(e);
                    if (e == null) {
                        e.d("Post-processor returned null [%s]", this.n);
                    }
                }
                k();
                q();
                reentrantLock.unlock();
                a(new b(bitmap, e, this.g, this.f, this.q), this.p, this.h, this.f);
            } catch (a e2) {
                i();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private boolean b() {
        AtomicBoolean c = this.f.c();
        if (c.get()) {
            synchronized (this.f.d()) {
                if (c.get()) {
                    e.a("ImageLoader is paused. Waiting...  [%s]", this.n);
                    try {
                        this.f.d().wait();
                        e.a(".. Resume loading [%s]", this.n);
                    } catch (InterruptedException e) {
                        e.d("Task was interrupted [%s]", this.n);
                        return true;
                    }
                }
            }
        }
        return l();
    }

    private boolean c() {
        if (!this.c.f()) {
            return false;
        }
        e.a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.c.l()), this.n);
        try {
            Thread.sleep((long) this.c.l());
            return l();
        } catch (InterruptedException e) {
            e.d("Task was interrupted [%s]", this.n);
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap d() throws com.nostra13.universalimageloader.core.h.a {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.i;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r0 = r0.p;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r2 = r7.a;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r0 = r0.a(r2);	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        if (r0 == 0) goto L_0x00cf;
    L_0x000d:
        r2 = r0.exists();	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        if (r2 == 0) goto L_0x00cf;
    L_0x0013:
        r2 = "Load image from disk cache [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r4 = 0;
        r5 = r7.n;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        com.nostra13.universalimageloader.b.e.a(r2, r3);	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r7.q = r2;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r7.k();	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r0 = r0.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r0 = r2.wrap(r0);	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
        r0 = r7.a(r0);	 Catch:{ IllegalStateException -> 0x0096, a -> 0x009e, IOException -> 0x00a0, OutOfMemoryError -> 0x00ad, Throwable -> 0x00ba }
    L_0x0035:
        if (r0 == 0) goto L_0x0043;
    L_0x0037:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r2 <= 0) goto L_0x0043;
    L_0x003d:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r2 > 0) goto L_0x0095;
    L_0x0043:
        r2 = "Load image from network [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r4 = 0;
        r5 = r7.n;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        com.nostra13.universalimageloader.b.e.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r7.q = r2;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r2 = r7.a;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r3 = r7.c;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r3 = r3.i();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r3 == 0) goto L_0x007a;
    L_0x005e:
        r3 = r7.f();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r3 == 0) goto L_0x007a;
    L_0x0064:
        r3 = r7.i;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r3 = r3.p;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r4 = r7.a;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r3 = r3.a(r4);	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r3 == 0) goto L_0x007a;
    L_0x0070:
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r3 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r2 = r2.wrap(r3);	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
    L_0x007a:
        r7.k();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r0 = r7.a(r2);	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r0 == 0) goto L_0x008f;
    L_0x0083:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r2 <= 0) goto L_0x008f;
    L_0x0089:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        if (r2 > 0) goto L_0x0095;
    L_0x008f:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
        r3 = 0;
        r7.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00cd, a -> 0x009e, IOException -> 0x00cb, OutOfMemoryError -> 0x00c9, Throwable -> 0x00c7 }
    L_0x0095:
        return r0;
    L_0x0096:
        r0 = move-exception;
        r0 = r1;
    L_0x0098:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.NETWORK_DENIED;
        r7.a(r2, r1);
        goto L_0x0095;
    L_0x009e:
        r0 = move-exception;
        throw r0;
    L_0x00a0:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00a4:
        com.nostra13.universalimageloader.b.e.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.IO_ERROR;
        r7.a(r2, r1);
        goto L_0x0095;
    L_0x00ad:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00b1:
        com.nostra13.universalimageloader.b.e.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.OUT_OF_MEMORY;
        r7.a(r2, r1);
        goto L_0x0095;
    L_0x00ba:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00be:
        com.nostra13.universalimageloader.b.e.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.UNKNOWN;
        r7.a(r2, r1);
        goto L_0x0095;
    L_0x00c7:
        r1 = move-exception;
        goto L_0x00be;
    L_0x00c9:
        r1 = move-exception;
        goto L_0x00b1;
    L_0x00cb:
        r1 = move-exception;
        goto L_0x00a4;
    L_0x00cd:
        r2 = move-exception;
        goto L_0x0098;
    L_0x00cf:
        r0 = r1;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.h.d():android.graphics.Bitmap");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private pl.droidsonroids.gif.GifDrawable e() throws com.nostra13.universalimageloader.core.h.a {
        /*
        r10 = this;
        r2 = 0;
        r0 = r10.i;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r0 = r0.p;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r1 = r10.a;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r3 = r0.a(r1);	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        if (r3 == 0) goto L_0x0159;
    L_0x000d:
        r0 = r3.exists();	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        if (r0 == 0) goto L_0x0159;
    L_0x0013:
        r0 = "Load image from disk cache [%s]";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r4 = 0;
        r5 = r10.n;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r1[r4] = r5;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        com.nostra13.universalimageloader.b.e.a(r0, r1);	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r0 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r10.q = r0;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r10.k();	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r0 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r1 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r0.wrap(r1);	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r0 = new pl.droidsonroids.gif.GifDrawable;	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
        r0.<init>(r3);	 Catch:{ IllegalStateException -> 0x010f, a -> 0x0117, IOException -> 0x0119, OutOfMemoryError -> 0x0125, Throwable -> 0x0131 }
    L_0x0035:
        if (r0 == 0) goto L_0x0043;
    L_0x0037:
        r1 = r0.getIntrinsicWidth();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r1 <= 0) goto L_0x0043;
    L_0x003d:
        r1 = r0.getIntrinsicHeight();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r1 > 0) goto L_0x0108;
    L_0x0043:
        r1 = "Load image from network [%s]";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r5 = 0;
        r6 = r10.n;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4[r5] = r6;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        com.nostra13.universalimageloader.b.e.a(r1, r4);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r1 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r10.q = r1;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r1 = r10.a;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4 = r10.c;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4 = r4.i();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r4 == 0) goto L_0x00aa;
    L_0x005e:
        r4 = r10.g();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r4 == 0) goto L_0x00aa;
    L_0x0064:
        r3 = r10.i;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r3 = r3.p;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4 = r10.a;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r3 = r3.a(r4);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r5 = "tangjian";
        r4 = 1;
        r6 = new java.lang.Object[r4];	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r7 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4.<init>();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r8 = "tryCacheGifImageOnDisk   ";
        r4 = r4.append(r8);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r8 = r10.a;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4 = r4.append(r8);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r8 = "    imageFile:  ";
        r8 = r4.append(r8);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r3 == 0) goto L_0x0109;
    L_0x008d:
        r4 = r3.getName();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
    L_0x0091:
        r4 = r8.append(r4);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4 = r4.toString();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r6[r7] = r4;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        com.nostra13.universalimageloader.b.e.b(r5, r6);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r3 == 0) goto L_0x00aa;
    L_0x00a0:
        r1 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r1 = r1.wrap(r4);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
    L_0x00aa:
        r10.k();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r4 = "tangjian";
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r6 = 0;
        r7 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r7.<init>();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r8 = "imageUriForDecoding  ";
        r7 = r7.append(r8);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r7 = r7.append(r1);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r8 = "    imageFile:";
        r7 = r7.append(r8);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r3 == 0) goto L_0x010c;
    L_0x00ca:
        r3 = r3.getName();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
    L_0x00ce:
        r3 = r7.append(r3);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r3 = r3.toString();	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r5[r6] = r3;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        com.nostra13.universalimageloader.b.e.b(r4, r5);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r1 == 0) goto L_0x0157;
    L_0x00dd:
        r3 = "file://";
        r3 = r1.startsWith(r3);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r3 == 0) goto L_0x0157;
    L_0x00e5:
        r3 = "file://";
        r4 = "";
        r1 = r1.replace(r3, r4);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r3 = r1;
    L_0x00ee:
        r1 = new pl.droidsonroids.gif.GifDrawable;	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        r1.<init>(r3);	 Catch:{ IllegalStateException -> 0x0152, a -> 0x0117, IOException -> 0x014b, OutOfMemoryError -> 0x0144, Throwable -> 0x013d }
        if (r1 == 0) goto L_0x0101;
    L_0x00f5:
        r0 = r1.getIntrinsicWidth();	 Catch:{ IllegalStateException -> 0x0154, a -> 0x0117, IOException -> 0x014d, OutOfMemoryError -> 0x0146, Throwable -> 0x013f }
        if (r0 <= 0) goto L_0x0101;
    L_0x00fb:
        r0 = r1.getIntrinsicHeight();	 Catch:{ IllegalStateException -> 0x0154, a -> 0x0117, IOException -> 0x014d, OutOfMemoryError -> 0x0146, Throwable -> 0x013f }
        if (r0 > 0) goto L_0x0107;
    L_0x0101:
        r0 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x0154, a -> 0x0117, IOException -> 0x014d, OutOfMemoryError -> 0x0146, Throwable -> 0x013f }
        r3 = 0;
        r10.a(r0, r3);	 Catch:{ IllegalStateException -> 0x0154, a -> 0x0117, IOException -> 0x014d, OutOfMemoryError -> 0x0146, Throwable -> 0x013f }
    L_0x0107:
        r0 = r1;
    L_0x0108:
        return r0;
    L_0x0109:
        r4 = "imageFile is empty";
        goto L_0x0091;
    L_0x010c:
        r3 = "imageFile is empty";
        goto L_0x00ce;
    L_0x010f:
        r0 = move-exception;
        r0 = r2;
    L_0x0111:
        r1 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.NETWORK_DENIED;
        r10.a(r1, r2);
        goto L_0x0108;
    L_0x0117:
        r0 = move-exception;
        throw r0;
    L_0x0119:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x011c:
        com.nostra13.universalimageloader.b.e.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.IO_ERROR;
        r10.a(r2, r1);
        goto L_0x0108;
    L_0x0125:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0128:
        com.nostra13.universalimageloader.b.e.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.OUT_OF_MEMORY;
        r10.a(r2, r1);
        goto L_0x0108;
    L_0x0131:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0134:
        com.nostra13.universalimageloader.b.e.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason$FailType.UNKNOWN;
        r10.a(r2, r1);
        goto L_0x0108;
    L_0x013d:
        r1 = move-exception;
        goto L_0x0134;
    L_0x013f:
        r0 = move-exception;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x0134;
    L_0x0144:
        r1 = move-exception;
        goto L_0x0128;
    L_0x0146:
        r0 = move-exception;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x0128;
    L_0x014b:
        r1 = move-exception;
        goto L_0x011c;
    L_0x014d:
        r0 = move-exception;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x011c;
    L_0x0152:
        r1 = move-exception;
        goto L_0x0111;
    L_0x0154:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0111;
    L_0x0157:
        r3 = r1;
        goto L_0x00ee;
    L_0x0159:
        r0 = r2;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.h.e():pl.droidsonroids.gif.GifDrawable");
    }

    private Bitmap a(String str) throws IOException {
        String str2 = str;
        return this.m.a(new com.nostra13.universalimageloader.core.a.c(this.n, str2, this.a, this.o, this.b.c(), j(), this.c));
    }

    private boolean f() throws a {
        e.a("Cache image on disk [%s]", this.n);
        try {
            boolean h = h();
            if (!h) {
                return h;
            }
            int i = this.i.d;
            int i2 = this.i.e;
            if (i <= 0 && i2 <= 0) {
                return h;
            }
            e.a("Resize image in disk cache [%s]", this.n);
            b(i, i2);
            return h;
        } catch (Throwable e) {
            e.a(e);
            if (Scheme.ofUri(this.a) == Scheme.HTTP || Scheme.ofUri(this.a) == Scheme.HTTPS) {
                com.nostra13.universalimageloader.b.d.a(this.a, e);
            }
            return false;
        }
    }

    private boolean g() throws a {
        boolean z = false;
        e.a("Cache image on disk [%s]", this.n);
        try {
            z = h();
        } catch (Throwable e) {
            e.a(e);
            if (Scheme.ofUri(this.a) == Scheme.HTTP || Scheme.ofUri(this.a) == Scheme.HTTPS) {
                com.nostra13.universalimageloader.b.d.a(this.a, e);
            }
        }
        return z;
    }

    private boolean h() throws IOException {
        return this.i.p.a(this.a, j().getStream(this.a, this.c.n()), this);
    }

    private boolean b(int i, int i2) throws IOException {
        File a = this.i.p.a(this.a);
        if (a != null && a.exists()) {
            Bitmap a2 = this.m.a(new com.nostra13.universalimageloader.core.a.c(this.n, Scheme.FILE.wrap(a.getAbsolutePath()), this.a, new com.nostra13.universalimageloader.core.assist.c(i, i2), ViewScaleType.FIT_INSIDE, j(), new com.nostra13.universalimageloader.core.c.a().a(this.c).a(ImageScaleType.IN_SAMPLE_INT).a()));
            if (!(a2 == null || this.i.f == null)) {
                e.a("Process image before cache on disk [%s]", this.n);
                a2 = this.i.f.a(a2);
                if (a2 == null) {
                    e.d("Bitmap processor for disk cache returned null [%s]", this.n);
                }
            }
            Bitmap bitmap = a2;
            if (bitmap != null) {
                boolean a3 = this.i.p.a(this.a, bitmap);
                bitmap.recycle();
                return a3;
            }
        }
        return false;
    }

    public boolean a(int i, int i2) {
        return this.p || c(i, i2);
    }

    private boolean c(final int i, final int i2) {
        if (r() || l()) {
            return false;
        }
        if (this.e != null) {
            a(new Runnable(this) {
                final /* synthetic */ h c;

                public void run() {
                    this.c.e.onProgressUpdate(this.c.a, this.c.b.d(), i, i2);
                }
            }, false, this.h, this.f);
        }
        return true;
    }

    private void a(final FailReason$FailType failReason$FailType, final Throwable th) {
        if (!this.p && !r() && !l()) {
            a(new Runnable(this) {
                final /* synthetic */ h c;

                public void run() {
                    if (this.c.c.c()) {
                        this.c.b.a(this.c.c.c(this.c.i.a));
                    }
                    this.c.d.onLoadingFailed(this.c.a, this.c.b.d(), new FailReason(failReason$FailType, th));
                }
            }, false, this.h, this.f);
        }
    }

    private void i() {
        if (!this.p && !r()) {
            a(new Runnable(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.d.onLoadingCancelled(this.a.a, this.a.b.d());
                }
            }, false, this.h, this.f);
        }
    }

    private ImageDownloader j() {
        if (this.f.e()) {
            return this.k;
        }
        if (this.f.f()) {
            return this.l;
        }
        return this.j;
    }

    private void k() throws a {
        m();
        o();
    }

    private boolean l() {
        return n() || p();
    }

    private void m() throws a {
        if (n()) {
            throw new a(this);
        }
    }

    private boolean n() {
        if (!this.b.e()) {
            return false;
        }
        e.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.n);
        return true;
    }

    private void o() throws a {
        if (p()) {
            throw new a(this);
        }
    }

    private boolean p() {
        if (!(!this.n.equals(this.f.a(this.b)))) {
            return false;
        }
        e.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.n);
        return true;
    }

    private void q() throws a {
        if (r()) {
            throw new a(this);
        }
    }

    private boolean r() {
        if (!Thread.interrupted()) {
            return false;
        }
        e.a("Task was interrupted [%s]", this.n);
        return true;
    }

    String a() {
        return this.a;
    }

    static void a(Runnable runnable, boolean z, Handler handler, f fVar) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            fVar.a(runnable);
        } else {
            handler.post(runnable);
        }
    }
}
