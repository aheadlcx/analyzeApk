package qsbk.app.activity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import qsbk.app.utils.ImageUtils;
import qsbk.app.utils.Util;

class ka extends BaseBitmapDataSubscriber {
    final /* synthetic */ EditIMImageActivity a;

    ka(EditIMImageActivity editIMImageActivity) {
        this.a = editIMImageActivity;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        Bitmap scaleBitmapIfNecessary = ImageUtils.scaleBitmapIfNecessary(bitmap, Util.displaySize.x, Util.displaySize.y, false);
        if (scaleBitmapIfNecessary == bitmap) {
            scaleBitmapIfNecessary = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), new Matrix(), true);
        }
        this.a.f.setImageBitmap(scaleBitmapIfNecessary);
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
