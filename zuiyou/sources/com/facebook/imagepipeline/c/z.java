package com.facebook.imagepipeline.c;

import bolts.g;
import com.facebook.cache.common.b;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import java.util.concurrent.atomic.AtomicBoolean;

public class z implements l {
    private final e a;
    private final e b;
    private final f c;

    public z(e eVar, e eVar2, f fVar) {
        this.a = eVar;
        this.b = eVar2;
        this.c = fVar;
    }

    public g<e> a(ImageRequest imageRequest, Object obj, AtomicBoolean atomicBoolean) {
        b c = this.c.c(imageRequest, obj);
        if (imageRequest.a() == ImageRequest$CacheChoice.SMALL) {
            return this.b.a(c, atomicBoolean);
        }
        return this.a.a(c, atomicBoolean);
    }

    public void a(e eVar, ImageRequest imageRequest, Object obj) {
        b c = this.c.c(imageRequest, obj);
        if (a(imageRequest, eVar) == ImageRequest$CacheChoice.SMALL) {
            this.b.a(c, eVar);
        } else {
            this.a.a(c, eVar);
        }
    }

    public ImageRequest$CacheChoice a(ImageRequest imageRequest, e eVar) {
        if (imageRequest.a() == null) {
            return ImageRequest$CacheChoice.DEFAULT;
        }
        return imageRequest.a();
    }
}
