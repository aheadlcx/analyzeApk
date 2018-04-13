package cn.v6.sixrooms.bitmap;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

final class a extends BaseBitmapDataSubscriber {
    final /* synthetic */ ThreadGetAnimationBitmap a;

    a(ThreadGetAnimationBitmap threadGetAnimationBitmap) {
        this.a = threadGetAnimationBitmap;
    }

    protected final void onNewResultImpl(Bitmap bitmap) {
        if (bitmap != null) {
            this.a.c.onBitmapGet(this.a.a, this.a.b, bitmap);
        }
    }

    protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
