package com.facebook.imagepipeline.c;

import android.net.Uri;
import com.facebook.cache.common.b;
import com.facebook.cache.common.g;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.c;
import javax.annotation.Nullable;

public class j implements f {
    private static j a = null;

    protected j() {
    }

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            if (a == null) {
                a = new j();
            }
            jVar = a;
        }
        return jVar;
    }

    public b a(ImageRequest imageRequest, Object obj) {
        return new c(a(imageRequest.b()).toString(), imageRequest.g(), imageRequest.h(), imageRequest.i(), null, null, obj);
    }

    public b b(ImageRequest imageRequest, Object obj) {
        b a;
        String str = null;
        c p = imageRequest.p();
        if (p != null) {
            a = p.a();
            str = p.getClass().getName();
        } else {
            a = null;
        }
        return new c(a(imageRequest.b()).toString(), imageRequest.g(), imageRequest.h(), imageRequest.i(), a, str, obj);
    }

    public b c(ImageRequest imageRequest, @Nullable Object obj) {
        return a(imageRequest, imageRequest.b(), obj);
    }

    public b a(ImageRequest imageRequest, Uri uri, @Nullable Object obj) {
        return new g(a(uri).toString());
    }

    protected Uri a(Uri uri) {
        return uri;
    }
}
