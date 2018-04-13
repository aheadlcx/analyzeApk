package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.e.a;
import pl.droidsonroids.gif.GifDrawable;

final class i implements Runnable {
    private final f a;
    private final Bitmap b;
    private final GifDrawable c;
    private final g d;
    private final Handler e;

    public i(f fVar, Bitmap bitmap, GifDrawable gifDrawable, g gVar, Handler handler) {
        this.a = fVar;
        this.b = bitmap;
        this.c = gifDrawable;
        this.d = gVar;
        this.e = handler;
    }

    public void run() {
        e.a("PostProcess image before displaying [%s]", this.d.b);
        a p = this.d.e.p();
        h.a(new b(p.a(this.b), p.a(this.c), this.d, this.a, LoadedFrom.MEMORY_CACHE), this.d.e.s(), this.e, this.a);
    }
}
