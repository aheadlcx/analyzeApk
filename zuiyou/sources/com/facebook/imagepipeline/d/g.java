package com.facebook.imagepipeline.d;

import android.net.Uri;
import com.android.internal.util.Predicate;
import com.facebook.common.internal.i;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.util.d;
import com.facebook.imagepipeline.c.e;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.h.a;
import com.facebook.imagepipeline.h.b;
import com.facebook.imagepipeline.producers.ai;
import com.facebook.imagepipeline.producers.ap;
import com.facebook.imagepipeline.producers.as;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class g {
    private static final CancellationException a = new CancellationException("Prefetching is not enabled");
    private final m b;
    private final b c;
    private final i<Boolean> d;
    private final t<com.facebook.cache.common.b, c> e;
    private final t<com.facebook.cache.common.b, PooledByteBuffer> f;
    private final e g;
    private final e h;
    private final f i;
    private final as j;
    private final i<Boolean> k;
    private AtomicLong l = new AtomicLong();

    public g(m mVar, Set<b> set, i<Boolean> iVar, t<com.facebook.cache.common.b, c> tVar, t<com.facebook.cache.common.b, PooledByteBuffer> tVar2, e eVar, e eVar2, f fVar, as asVar, i<Boolean> iVar2) {
        this.b = mVar;
        this.c = new a((Set) set);
        this.d = iVar;
        this.e = tVar;
        this.f = tVar2;
        this.g = eVar;
        this.h = eVar2;
        this.i = fVar;
        this.j = asVar;
        this.k = iVar2;
    }

    private String d() {
        return String.valueOf(this.l.getAndIncrement());
    }

    public com.facebook.datasource.b<com.facebook.common.references.a<c>> a(ImageRequest imageRequest, Object obj) {
        return a(imageRequest, obj, ImageRequest$RequestLevel.FULL_FETCH);
    }

    public com.facebook.datasource.b<com.facebook.common.references.a<c>> a(ImageRequest imageRequest, Object obj, ImageRequest$RequestLevel imageRequest$RequestLevel) {
        try {
            return a(this.b.b(imageRequest), imageRequest, imageRequest$RequestLevel, obj);
        } catch (Throwable e) {
            return com.facebook.datasource.c.a(e);
        }
    }

    public com.facebook.datasource.b<com.facebook.common.references.a<PooledByteBuffer>> b(ImageRequest imageRequest, Object obj) {
        com.facebook.common.internal.g.a(imageRequest.b());
        try {
            ai a = this.b.a(imageRequest);
            if (imageRequest.g() != null) {
                imageRequest = ImageRequestBuilder.a(imageRequest).a(null).n();
            }
            return a(a, imageRequest, ImageRequest$RequestLevel.FULL_FETCH, obj);
        } catch (Throwable e) {
            return com.facebook.datasource.c.a(e);
        }
    }

    public void a(Uri uri) {
        Predicate b = b(uri);
        this.e.a(b);
        this.f.a(b);
    }

    public void a() {
        Predicate anonymousClass1 = new Predicate<com.facebook.cache.common.b>(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public /* synthetic */ boolean apply(Object obj) {
                return a((com.facebook.cache.common.b) obj);
            }

            public boolean a(com.facebook.cache.common.b bVar) {
                return true;
            }
        };
        this.e.a(anonymousClass1);
        this.f.a(anonymousClass1);
    }

    public t<com.facebook.cache.common.b, c> b() {
        return this.e;
    }

    private <T> com.facebook.datasource.b<com.facebook.common.references.a<T>> a(ai<com.facebook.common.references.a<T>> aiVar, ImageRequest imageRequest, ImageRequest$RequestLevel imageRequest$RequestLevel, Object obj) {
        boolean z = false;
        Object a = a(imageRequest);
        try {
            ImageRequest$RequestLevel max = ImageRequest$RequestLevel.getMax(imageRequest.m(), imageRequest$RequestLevel);
            String d = d();
            if (!(!imageRequest.j() && imageRequest.d() == null && d.a(imageRequest.b()))) {
                z = true;
            }
            return com.facebook.imagepipeline.e.b.a(aiVar, new ap(imageRequest, d, a, obj, max, false, z, imageRequest.l()), a);
        } catch (Throwable e) {
            return com.facebook.datasource.c.a(e);
        }
    }

    private b a(ImageRequest imageRequest) {
        if (imageRequest.q() == null) {
            return this.c;
        }
        return new a(this.c, imageRequest.q());
    }

    private Predicate<com.facebook.cache.common.b> b(final Uri uri) {
        return new Predicate<com.facebook.cache.common.b>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ boolean apply(Object obj) {
                return a((com.facebook.cache.common.b) obj);
            }

            public boolean a(com.facebook.cache.common.b bVar) {
                return bVar.a(uri);
            }
        };
    }

    public f c() {
        return this.i;
    }
}
