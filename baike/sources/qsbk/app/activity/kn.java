package qsbk.app.activity;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;

class kn extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    final /* synthetic */ EventWindowActivity a;

    kn(EventWindowActivity eventWindowActivity) {
        this.a = eventWindowActivity;
    }

    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (!this.a.isFinishing()) {
            CloseableReference closeableReference = (CloseableReference) dataSource.getResult();
            if (closeableReference != null) {
                CloseableImage closeableImage = (CloseableImage) closeableReference.get();
                if (closeableImage instanceof CloseableBitmap) {
                    Bitmap underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
                    if (underlyingBitmap != null && underlyingBitmap.getHeight() != 0) {
                        this.a.a(underlyingBitmap.getWidth(), underlyingBitmap.getHeight());
                    }
                }
            }
        }
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
