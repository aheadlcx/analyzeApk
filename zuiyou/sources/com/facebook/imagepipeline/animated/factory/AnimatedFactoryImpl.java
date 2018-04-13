package com.facebook.imagepipeline.animated.factory;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import com.facebook.common.b.g;
import com.facebook.common.b.i;
import com.facebook.common.internal.d;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.animated.a.a;
import com.facebook.imagepipeline.animated.base.k;
import com.facebook.imagepipeline.animated.impl.b;
import com.facebook.imagepipeline.animated.impl.c;
import com.facebook.imagepipeline.b.f;
import com.facebook.imagepipeline.d.e;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.NotThreadSafe;

@d
@NotThreadSafe
public class AnimatedFactoryImpl implements c {
    private b a;
    private a b;
    private a c;
    private f d;
    private e e;
    private f f;

    @d
    public AnimatedFactoryImpl(f fVar, e eVar) {
        this.f = fVar;
        this.e = eVar;
    }

    private a a(g gVar, ActivityManager activityManager, a aVar, b bVar, ScheduledExecutorService scheduledExecutorService, com.facebook.common.time.b bVar2, Resources resources) {
        final g gVar2 = gVar;
        final ActivityManager activityManager2 = activityManager;
        final a aVar2 = aVar;
        final com.facebook.common.time.b bVar3 = bVar2;
        return a(bVar, new com.facebook.imagepipeline.animated.impl.d(this) {
            final /* synthetic */ AnimatedFactoryImpl e;

            public c a(com.facebook.imagepipeline.animated.base.d dVar, com.facebook.imagepipeline.animated.base.g gVar) {
                return new c(gVar2, activityManager2, aVar2, bVar3, dVar, gVar);
            }
        }, aVar, scheduledExecutorService, resources);
    }

    private b b() {
        if (this.a == null) {
            this.a = new b(this) {
                final /* synthetic */ AnimatedFactoryImpl a;

                {
                    this.a = r1;
                }

                public com.facebook.imagepipeline.animated.base.d a(k kVar, Rect rect) {
                    return new com.facebook.imagepipeline.animated.impl.a(this.a.c(), kVar, rect);
                }
            };
        }
        return this.a;
    }

    public a a(Context context) {
        if (this.c == null) {
            this.c = a(new com.facebook.common.b.c(this.e.c()), (ActivityManager) context.getSystemService("activity"), c(), b(), i.b(), RealtimeSinceBootClock.get(), context.getResources());
        }
        return this.c;
    }

    private a c() {
        if (this.b == null) {
            this.b = new a();
        }
        return this.b;
    }

    private f d() {
        return new g(new b(this) {
            final /* synthetic */ AnimatedFactoryImpl a;

            {
                this.a = r1;
            }

            public com.facebook.imagepipeline.animated.base.d a(k kVar, Rect rect) {
                return new com.facebook.imagepipeline.animated.impl.a(this.a.c(), kVar, rect);
            }
        }, this.f);
    }

    public f a() {
        if (this.d == null) {
            this.d = d();
        }
        return this.d;
    }

    protected a a(b bVar, com.facebook.imagepipeline.animated.impl.d dVar, a aVar, ScheduledExecutorService scheduledExecutorService, Resources resources) {
        return new b(bVar, dVar, aVar, scheduledExecutorService, resources);
    }
}
