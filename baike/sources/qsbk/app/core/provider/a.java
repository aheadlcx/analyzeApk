package qsbk.app.core.provider;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

class a extends BaseBitmapDataSubscriber {
    final /* synthetic */ ImageProvider$BitmapCallback a;
    final /* synthetic */ ImageProvider b;

    a(ImageProvider imageProvider, ImageProvider$BitmapCallback imageProvider$BitmapCallback) {
        this.b = imageProvider;
        this.a = imageProvider$BitmapCallback;
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        if (this.a != null) {
            this.a.call(bitmap);
        }
    }
}
