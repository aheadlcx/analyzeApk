package com.facebook.imagepipeline.c;

import android.net.Uri;
import com.facebook.cache.common.b;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public interface f {
    b a(ImageRequest imageRequest, Uri uri, @Nullable Object obj);

    b a(ImageRequest imageRequest, Object obj);

    b b(ImageRequest imageRequest, Object obj);

    b c(ImageRequest imageRequest, @Nullable Object obj);
}
