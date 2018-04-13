package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.c.a;
import com.nostra13.universalimageloader.core.d.c;
import pl.droidsonroids.gif.GifDrawable;

final class b implements Runnable {
    private final Bitmap a;
    private final GifDrawable b;
    private final String c;
    private final a d;
    private final String e;
    private final com.nostra13.universalimageloader.core.b.a f;
    private final c g;
    private final f h;
    private final LoadedFrom i;

    public b(Bitmap bitmap, GifDrawable gifDrawable, g gVar, f fVar, LoadedFrom loadedFrom) {
        this.a = bitmap;
        this.b = gifDrawable;
        this.c = gVar.a;
        this.d = gVar.c;
        this.e = gVar.b;
        this.f = gVar.e.q();
        this.g = gVar.f;
        this.h = fVar;
        this.i = loadedFrom;
    }

    public void run() {
        if (this.d.e()) {
            e.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.e);
            this.g.onLoadingCancelled(this.c, this.d.d());
        } else if (a()) {
            e.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.e);
            this.g.onLoadingCancelled(this.c, this.d.d());
        } else {
            e.a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.i, this.e);
            if (this.b != null) {
                this.f.a(this.b, this.d, this.i);
            } else {
                this.f.a(this.a, this.d, this.i);
            }
            this.h.b(this.d);
            if (this.g instanceof com.nostra13.universalimageloader.core.d.a) {
                ((com.nostra13.universalimageloader.core.d.a) this.g).onLoadingComplete(this.c, this.d.d(), this.a, this.b);
            } else {
                this.g.onLoadingComplete(this.c, this.d.d(), this.a);
            }
        }
    }

    private boolean a() {
        return !this.e.equals(this.h.a(this.d));
    }
}
