package com.facebook.imagepipeline.animated.factory;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import com.facebook.imagepipeline.animated.a.a;
import com.facebook.imagepipeline.animated.base.e;
import com.facebook.imagepipeline.animated.base.f;
import com.facebook.imagepipeline.animated.base.g;
import com.facebook.imagepipeline.animated.base.i;
import com.facebook.imagepipeline.animated.base.k;
import com.facebook.imagepipeline.animated.impl.d;
import com.facebook.imagepipeline.g.c;
import java.util.concurrent.ScheduledExecutorService;

public class b implements a {
    private final com.facebook.imagepipeline.animated.impl.b a;
    private final d b;
    private final a c;
    private final ScheduledExecutorService d;
    private final com.facebook.common.time.b e = new com.facebook.common.time.b(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public long now() {
            return SystemClock.uptimeMillis();
        }
    };
    private final Resources f;

    public b(com.facebook.imagepipeline.animated.impl.b bVar, d dVar, a aVar, ScheduledExecutorService scheduledExecutorService, Resources resources) {
        this.a = bVar;
        this.b = dVar;
        this.c = aVar;
        this.d = scheduledExecutorService;
        this.f = resources;
    }

    public Drawable a(c cVar) {
        if (cVar instanceof com.facebook.imagepipeline.g.a) {
            return a(((com.facebook.imagepipeline.g.a) cVar).f(), g.a);
        }
        throw new UnsupportedOperationException("Unrecognized image class: " + cVar);
    }

    private com.facebook.imagepipeline.animated.base.c a(k kVar, g gVar) {
        i a = kVar.a();
        return a(gVar, this.a.a(kVar, new Rect(0, 0, a.a(), a.b())));
    }

    private com.facebook.imagepipeline.animated.base.c a(g gVar, com.facebook.imagepipeline.animated.base.d dVar) {
        f eVar;
        DisplayMetrics displayMetrics = this.f.getDisplayMetrics();
        e a = this.b.a(dVar, gVar);
        if (gVar.e) {
            eVar = new com.facebook.imagepipeline.animated.impl.e(this.c, displayMetrics);
        } else {
            eVar = com.facebook.imagepipeline.animated.impl.f.g();
        }
        return new com.facebook.imagepipeline.animated.base.c(this.d, a, eVar, this.e);
    }
}
