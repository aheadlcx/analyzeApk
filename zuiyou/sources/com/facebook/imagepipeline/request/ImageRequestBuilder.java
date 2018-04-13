package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.a;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.h.b;
import javax.annotation.Nullable;

public class ImageRequestBuilder {
    private Uri a = null;
    private ImageRequest$RequestLevel b = ImageRequest$RequestLevel.FULL_FETCH;
    @Nullable
    private c c = null;
    @Nullable
    private d d = null;
    private a e = a.a();
    private ImageRequest$CacheChoice f = ImageRequest$CacheChoice.DEFAULT;
    private boolean g = h.e().a();
    private boolean h = false;
    private Priority i = Priority.HIGH;
    @Nullable
    private c j = null;
    private boolean k = true;
    @Nullable
    private b l;
    @Nullable
    private b m = null;

    public static ImageRequestBuilder a(Uri uri) {
        return new ImageRequestBuilder().b(uri);
    }

    public static ImageRequestBuilder a(ImageRequest imageRequest) {
        return a(imageRequest.b()).a(imageRequest.i()).a(imageRequest.a()).c(imageRequest.k()).a(imageRequest.m()).a(imageRequest.d()).a(imageRequest.p()).b(imageRequest.j()).a(imageRequest.l()).a(imageRequest.g()).a(imageRequest.q()).a(imageRequest.h());
    }

    private ImageRequestBuilder() {
    }

    public ImageRequestBuilder b(Uri uri) {
        g.a(uri);
        this.a = uri;
        return this;
    }

    public Uri a() {
        return this.a;
    }

    public ImageRequestBuilder a(b bVar) {
        this.m = bVar;
        return this;
    }

    @Nullable
    public b b() {
        return this.m;
    }

    public ImageRequestBuilder a(ImageRequest$RequestLevel imageRequest$RequestLevel) {
        this.b = imageRequest$RequestLevel;
        return this;
    }

    public ImageRequest$RequestLevel c() {
        return this.b;
    }

    @Deprecated
    public ImageRequestBuilder a(boolean z) {
        if (z) {
            return a(d.a());
        }
        return a(d.b());
    }

    public ImageRequestBuilder a(@Nullable c cVar) {
        this.c = cVar;
        return this;
    }

    @Nullable
    public c d() {
        return this.c;
    }

    public ImageRequestBuilder a(@Nullable d dVar) {
        this.d = dVar;
        return this;
    }

    @Nullable
    public d e() {
        return this.d;
    }

    public ImageRequestBuilder a(a aVar) {
        this.e = aVar;
        return this;
    }

    public a f() {
        return this.e;
    }

    public ImageRequestBuilder a(ImageRequest$CacheChoice imageRequest$CacheChoice) {
        this.f = imageRequest$CacheChoice;
        return this;
    }

    public ImageRequest$CacheChoice g() {
        return this.f;
    }

    public ImageRequestBuilder b(boolean z) {
        this.g = z;
        return this;
    }

    public boolean h() {
        return this.g;
    }

    public ImageRequestBuilder c(boolean z) {
        this.h = z;
        return this;
    }

    public boolean i() {
        return this.h;
    }

    public boolean j() {
        return this.k && com.facebook.common.util.d.a(this.a);
    }

    public ImageRequestBuilder a(Priority priority) {
        this.i = priority;
        return this;
    }

    public Priority k() {
        return this.i;
    }

    public ImageRequestBuilder a(c cVar) {
        this.j = cVar;
        return this;
    }

    @Nullable
    public c l() {
        return this.j;
    }

    public ImageRequestBuilder a(b bVar) {
        this.l = bVar;
        return this;
    }

    @Nullable
    public b m() {
        return this.l;
    }

    public ImageRequest n() {
        o();
        return new ImageRequest(this);
    }

    protected void o() {
        if (this.a == null) {
            throw new ImageRequestBuilder$BuilderException("Source must be set!");
        }
        if (com.facebook.common.util.d.g(this.a)) {
            if (!this.a.isAbsolute()) {
                throw new ImageRequestBuilder$BuilderException("Resource URI path must be absolute.");
            } else if (this.a.getPath().isEmpty()) {
                throw new ImageRequestBuilder$BuilderException("Resource URI must not be empty");
            } else {
                try {
                    Integer.parseInt(this.a.getPath().substring(1));
                } catch (NumberFormatException e) {
                    throw new ImageRequestBuilder$BuilderException("Resource URI path must be a resource id.");
                }
            }
        }
        if (com.facebook.common.util.d.f(this.a) && !this.a.isAbsolute()) {
            throw new ImageRequestBuilder$BuilderException("Asset URI path must be absolute.");
        }
    }
}
