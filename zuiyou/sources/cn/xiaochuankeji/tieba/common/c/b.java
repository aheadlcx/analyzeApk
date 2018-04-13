package cn.xiaochuankeji.tieba.common.c;

import android.net.Uri;
import com.facebook.cache.common.g;
import com.facebook.imagepipeline.c.c;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public class b implements f {
    private static b a = null;

    protected b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    public com.facebook.cache.common.b a(ImageRequest imageRequest, Object obj) {
        return new c(a(imageRequest.b()), imageRequest.g(), imageRequest.h(), imageRequest.i(), null, null, obj);
    }

    public com.facebook.cache.common.b b(ImageRequest imageRequest, Object obj) {
        com.facebook.cache.common.b a;
        String str = null;
        com.facebook.imagepipeline.request.c p = imageRequest.p();
        if (p != null) {
            a = p.a();
            str = p.getClass().getName();
        } else {
            a = null;
        }
        return new c(a(imageRequest.b()), imageRequest.g(), imageRequest.h(), imageRequest.i(), a, str, obj);
    }

    public com.facebook.cache.common.b c(ImageRequest imageRequest, @Nullable Object obj) {
        return a(imageRequest, imageRequest.b(), obj);
    }

    public com.facebook.cache.common.b a(ImageRequest imageRequest, Uri uri, @Nullable Object obj) {
        return new g(a(uri));
    }

    private String a(Uri uri) {
        if (uri == null) {
            return "";
        }
        String uri2 = uri.toString();
        if (uri2 == null || !uri2.contains("/id/") || uri2.startsWith("/id/")) {
            return uri2;
        }
        return uri2.substring(uri2.indexOf("/id/"));
    }
}
