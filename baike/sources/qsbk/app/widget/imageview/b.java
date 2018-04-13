package qsbk.app.widget.imageview;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;

class b extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    final /* synthetic */ boolean a;
    final /* synthetic */ FrescoTouchImageView b;

    b(FrescoTouchImageView frescoTouchImageView, boolean z) {
        this.b = frescoTouchImageView;
        this.a = z;
    }

    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (dataSource.isFinished()) {
            this.b.mBigRef = (CloseableReference) dataSource.getResult();
            CloseableImage closeableImage = this.b.mBigRef != null ? (CloseableImage) this.b.mBigRef.get() : null;
            if (closeableImage instanceof CloseableBitmap) {
                Bitmap underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
                if (underlyingBitmap != null) {
                    this.b.mLoadFrom = this.a ? 1 : 0;
                    this.b.innerSetBitmap(underlyingBitmap);
                    if (this.b.mListener != null) {
                        this.b.mListener.success(this.b.bigUrl, this.b.mLoadFrom);
                    }
                }
            }
        }
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (this.b.mListener != null) {
            this.b.mListener.fail(this.b.bigUrl);
        }
    }
}
