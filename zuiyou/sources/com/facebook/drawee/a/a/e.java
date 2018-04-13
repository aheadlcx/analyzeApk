package com.facebook.drawee.a.a;

import android.content.Context;
import android.net.Uri;
import com.facebook.cache.common.b;
import com.facebook.common.references.a;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder$CacheLevel;
import com.facebook.drawee.d.d;
import com.facebook.imagepipeline.d.g;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.f;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.Set;
import javax.annotation.Nullable;

public class e extends AbstractDraweeControllerBuilder<e, ImageRequest, a<c>, f> {
    private final g a;
    private final g b;

    public /* synthetic */ d b(@Nullable Uri uri) {
        return a(uri);
    }

    protected /* synthetic */ AbstractDraweeControllerBuilder c() {
        return b();
    }

    protected /* synthetic */ com.facebook.drawee.controller.a d() {
        return a();
    }

    public e(Context context, g gVar, g gVar2, Set<com.facebook.drawee.controller.c> set) {
        super(context, set);
        this.a = gVar2;
        this.b = gVar;
    }

    public e a(@Nullable Uri uri) {
        if (uri == null) {
            return (e) super.b(null);
        }
        return (e) super.b(ImageRequestBuilder.a(uri).a(com.facebook.imagepipeline.common.d.c()).n());
    }

    public e a(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return (e) super.b(ImageRequest.a(str));
        }
        return a(Uri.parse(str));
    }

    protected d a() {
        com.facebook.drawee.d.a j = j();
        if (!(j instanceof d)) {
            return this.b.a(o(), n(), q(), e());
        }
        d dVar = (d) j;
        dVar.a(o(), n(), q(), e());
        return dVar;
    }

    private b q() {
        ImageRequest imageRequest = (ImageRequest) f();
        com.facebook.imagepipeline.c.f c = this.a.c();
        if (c == null || imageRequest == null) {
            return null;
        }
        if (imageRequest.p() != null) {
            return c.b(imageRequest, e());
        }
        return c.a(imageRequest, e());
    }

    protected com.facebook.datasource.b<a<c>> a(ImageRequest imageRequest, Object obj, AbstractDraweeControllerBuilder$CacheLevel abstractDraweeControllerBuilder$CacheLevel) {
        return this.a.a(imageRequest, obj, a(abstractDraweeControllerBuilder$CacheLevel));
    }

    protected e b() {
        return this;
    }

    public static ImageRequest$RequestLevel a(AbstractDraweeControllerBuilder$CacheLevel abstractDraweeControllerBuilder$CacheLevel) {
        switch (abstractDraweeControllerBuilder$CacheLevel) {
            case FULL_FETCH:
                return ImageRequest$RequestLevel.FULL_FETCH;
            case DISK_CACHE:
                return ImageRequest$RequestLevel.DISK_CACHE;
            case BITMAP_MEMORY_CACHE:
                return ImageRequest$RequestLevel.BITMAP_MEMORY_CACHE;
            default:
                throw new RuntimeException("Cache level" + abstractDraweeControllerBuilder$CacheLevel + "is not supported. ");
        }
    }
}
