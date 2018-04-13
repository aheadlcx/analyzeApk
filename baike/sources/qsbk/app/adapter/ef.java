package qsbk.app.adapter;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;

class ef extends IterativeBoxBlurPostProcessor {
    final /* synthetic */ String a;
    final /* synthetic */ VideoImmersionCell b;

    ef(VideoImmersionCell videoImmersionCell, int i, String str) {
        this.b = videoImmersionCell;
        this.a = str;
        super(i);
    }

    public CacheKey getPostprocessorCacheKey() {
        return new SimpleCacheKey(this.a);
    }
}
