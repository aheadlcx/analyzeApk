package qsbk.app.adapter;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;
import qsbk.app.utils.BlurUtil;

class ae extends BasePostprocessor {
    final /* synthetic */ int a;
    final /* synthetic */ Integer b;
    final /* synthetic */ String c;
    final /* synthetic */ BaseVideoAdapter d;

    ae(BaseVideoAdapter baseVideoAdapter, int i, Integer num, String str) {
        this.d = baseVideoAdapter;
        this.a = i;
        this.b = num;
        this.c = str;
    }

    public void process(Bitmap bitmap) {
        int width = (int) ((((float) bitmap.getWidth()) / ((float) this.a)) * ((float) this.b.intValue()));
        BlurUtil.fastblurSrc(bitmap, 32, true, new int[]{(r0 - width) / 2, (bitmap.getWidth() + width) / 2}, null);
    }

    public CacheKey getPostprocessorCacheKey() {
        return new SimpleCacheKey(this.c);
    }
}
