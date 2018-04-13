package com.facebook.imagepipeline.request;

import android.graphics.Bitmap;
import com.facebook.cache.common.b;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.b.f;
import javax.annotation.Nullable;

public interface c {
    @Nullable
    b a();

    a<Bitmap> a(Bitmap bitmap, f fVar);

    String b();
}
