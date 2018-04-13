package qsbk.app.activity;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;
import qsbk.app.utils.BlurUtil;

class ik extends BasePostprocessor {
    final /* synthetic */ String a;
    final /* synthetic */ CircleTopicActivity b;

    ik(CircleTopicActivity circleTopicActivity, String str) {
        this.b = circleTopicActivity;
        this.a = str;
    }

    public CacheKey getPostprocessorCacheKey() {
        return new SimpleCacheKey(this.a);
    }

    public void process(Bitmap bitmap) {
        BlurUtil.fastblurSrc(bitmap, 16);
    }
}
