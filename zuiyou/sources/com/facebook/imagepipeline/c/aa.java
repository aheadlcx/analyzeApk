package com.facebook.imagepipeline.c;

import bolts.f;
import bolts.g;
import com.facebook.cache.common.b;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

public class aa implements l {
    private final e a;
    private final e b;
    private final f c;
    private final int d;

    public aa(e eVar, e eVar2, f fVar, int i) {
        this.a = eVar;
        this.b = eVar2;
        this.c = fVar;
        this.d = i;
    }

    public g<e> a(ImageRequest imageRequest, Object obj, final AtomicBoolean atomicBoolean) {
        e eVar;
        e eVar2;
        final b c = this.c.c(imageRequest, obj);
        boolean a = this.b.a(c);
        boolean a2 = this.a.a(c);
        if (a || !a2) {
            eVar = this.b;
            eVar2 = this.a;
        } else {
            eVar = this.a;
            eVar2 = this.b;
        }
        return eVar.a(c, atomicBoolean).b(new f<e, g<e>>(this) {
            final /* synthetic */ aa d;

            public /* synthetic */ Object a(g gVar) throws Exception {
                return b(gVar);
            }

            public g<e> b(g<e> gVar) throws Exception {
                if (aa.b(gVar)) {
                    return gVar;
                }
                return (gVar.d() || gVar.e() == null) ? eVar2.a(c, atomicBoolean) : gVar;
            }
        });
    }

    public void a(e eVar, ImageRequest imageRequest, Object obj) {
        b c = this.c.c(imageRequest, obj);
        switch (a(imageRequest, eVar)) {
            case DEFAULT:
                this.a.a(c, eVar);
                return;
            case SMALL:
                this.b.a(c, eVar);
                return;
            default:
                return;
        }
    }

    public ImageRequest$CacheChoice a(ImageRequest imageRequest, e eVar) {
        int k = eVar.k();
        if (k < 0 || k >= this.d) {
            return ImageRequest$CacheChoice.DEFAULT;
        }
        return ImageRequest$CacheChoice.SMALL;
    }

    private static boolean b(g<?> gVar) {
        return gVar.c() || (gVar.d() && (gVar.f() instanceof CancellationException));
    }
}
