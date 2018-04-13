package qsbk.app.live.share;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

class i extends BaseBitmapDataSubscriber {
    final /* synthetic */ Runnable a;
    final /* synthetic */ LiveShareActivity b;

    i(LiveShareActivity liveShareActivity, Runnable runnable) {
        this.b = liveShareActivity;
        this.a = runnable;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        this.b.e = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (this.a != null) {
            this.a.run();
        }
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
