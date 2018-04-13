package com.facebook.imagepipeline.c;

import bolts.g;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import java.util.concurrent.atomic.AtomicBoolean;

public interface l {
    g<e> a(ImageRequest imageRequest, Object obj, AtomicBoolean atomicBoolean);

    ImageRequest$CacheChoice a(ImageRequest imageRequest, e eVar);

    void a(e eVar, ImageRequest imageRequest, Object obj);
}
