package com.facebook.imagepipeline.postprocessors;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.nativecode.NativeBlurFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import java.util.Locale;
import javax.annotation.Nullable;

public class IterativeBoxBlurPostProcessor extends BasePostprocessor {
    private static final int DEFAULT_ITERATIONS = 3;
    private final int mBlurRadius;
    private CacheKey mCacheKey;
    private final int mIterations;

    public IterativeBoxBlurPostProcessor(int i) {
        this(3, i);
    }

    public IterativeBoxBlurPostProcessor(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (i2 <= 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        this.mIterations = i;
        this.mBlurRadius = i2;
    }

    public void process(Bitmap bitmap) {
        NativeBlurFilter.iterativeBoxBlur(bitmap, this.mIterations, this.mBlurRadius);
    }

    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        if (this.mCacheKey == null) {
            this.mCacheKey = new SimpleCacheKey(String.format((Locale) null, "i%dr%d", new Object[]{Integer.valueOf(this.mIterations), Integer.valueOf(this.mBlurRadius)}));
        }
        return this.mCacheKey;
    }
}
