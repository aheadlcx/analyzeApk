package qsbk.app.slide;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;
import qsbk.app.utils.BlurUtil;

class ac extends BasePostprocessor {
    final /* synthetic */ ImageView a;
    final /* synthetic */ String b;
    final /* synthetic */ SingleArticleFragment c;

    ac(SingleArticleFragment singleArticleFragment, ImageView imageView, String str) {
        this.c = singleArticleFragment;
        this.a = imageView;
        this.b = str;
    }

    public void process(Bitmap bitmap) {
        int i = this.a.getLayoutParams().width;
        i = (int) ((((float) bitmap.getWidth()) / ((float) i)) * ((float) this.c.aJ));
        BlurUtil.fastblurSrc(bitmap, 32, true, new int[]{(r1 - i) / 2, (i + bitmap.getWidth()) / 2}, null);
    }

    public CacheKey getPostprocessorCacheKey() {
        return new SimpleCacheKey(this.b);
    }
}
