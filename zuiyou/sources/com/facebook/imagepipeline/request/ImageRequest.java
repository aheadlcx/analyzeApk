package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.common.internal.f;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.a;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.h.b;
import java.io.File;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class ImageRequest {
    private final ImageRequest$CacheChoice a;
    private final Uri b;
    private final int c = b(this.b);
    @Nullable
    private final b d;
    private File e;
    private final boolean f;
    private final boolean g;
    private final a h;
    @Nullable
    private final c i;
    private final d j;
    private final Priority k;
    private final ImageRequest$RequestLevel l;
    private final boolean m;
    private final c n;
    @Nullable
    private final b o;

    public static ImageRequest a(@Nullable Uri uri) {
        return uri == null ? null : ImageRequestBuilder.a(uri).n();
    }

    public static ImageRequest a(@Nullable String str) {
        return (str == null || str.length() == 0) ? null : a(Uri.parse(str));
    }

    protected ImageRequest(ImageRequestBuilder imageRequestBuilder) {
        this.a = imageRequestBuilder.g();
        this.b = imageRequestBuilder.a();
        this.d = imageRequestBuilder.b();
        this.f = imageRequestBuilder.h();
        this.g = imageRequestBuilder.i();
        this.h = imageRequestBuilder.f();
        this.i = imageRequestBuilder.d();
        this.j = imageRequestBuilder.e() == null ? d.a() : imageRequestBuilder.e();
        this.k = imageRequestBuilder.k();
        this.l = imageRequestBuilder.c();
        this.m = imageRequestBuilder.j();
        this.n = imageRequestBuilder.l();
        this.o = imageRequestBuilder.m();
    }

    public ImageRequest$CacheChoice a() {
        return this.a;
    }

    public Uri b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    @Nullable
    public b d() {
        return this.d;
    }

    public int e() {
        return this.i != null ? this.i.a : 2048;
    }

    public int f() {
        return this.i != null ? this.i.b : 2048;
    }

    @Nullable
    public c g() {
        return this.i;
    }

    public d h() {
        return this.j;
    }

    public a i() {
        return this.h;
    }

    public boolean j() {
        return this.f;
    }

    public boolean k() {
        return this.g;
    }

    public Priority l() {
        return this.k;
    }

    public ImageRequest$RequestLevel m() {
        return this.l;
    }

    public boolean n() {
        return this.m;
    }

    public synchronized File o() {
        if (this.e == null) {
            this.e = new File(this.b.getPath());
        }
        return this.e;
    }

    @Nullable
    public c p() {
        return this.n;
    }

    @Nullable
    public b q() {
        return this.o;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageRequest)) {
            return false;
        }
        ImageRequest imageRequest = (ImageRequest) obj;
        if (f.a(this.b, imageRequest.b) && f.a(this.a, imageRequest.a) && f.a(this.d, imageRequest.d) && f.a(this.e, imageRequest.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return f.a(this.a, this.b, this.d, this.e);
    }

    public String toString() {
        return f.a((Object) this).a("uri", this.b).a("cacheChoice", this.a).a("decodeOptions", this.h).a("postprocessor", this.n).a("priority", this.k).a("resizeOptions", this.i).a("rotationOptions", this.j).a("mediaVariations", this.d).toString();
    }

    private static int b(Uri uri) {
        if (uri == null) {
            return -1;
        }
        if (com.facebook.common.util.d.a(uri)) {
            return 0;
        }
        if (com.facebook.common.util.d.b(uri)) {
            if (com.facebook.common.d.a.a(com.facebook.common.d.a.b(uri.getPath()))) {
                return 2;
            }
            return 3;
        } else if (com.facebook.common.util.d.c(uri)) {
            return 4;
        } else {
            if (com.facebook.common.util.d.f(uri)) {
                return 5;
            }
            if (com.facebook.common.util.d.g(uri)) {
                return 6;
            }
            if (com.facebook.common.util.d.i(uri)) {
                return 7;
            }
            if (com.facebook.common.util.d.h(uri)) {
                return 8;
            }
            return -1;
        }
    }
}
