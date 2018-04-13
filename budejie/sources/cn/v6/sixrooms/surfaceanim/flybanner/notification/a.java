package cn.v6.sixrooms.surfaceanim.flybanner.notification;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.BasePostprocessor;

final class a extends BasePostprocessor {
    final /* synthetic */ NotificationElement a;

    a(NotificationElement notificationElement) {
        this.a = notificationElement;
    }

    public final CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        int width = this.a.m.getWidth();
        CloseableReference createBitmap = platformBitmapFactory.createBitmap(width, width);
        try {
            Bitmap bitmap2 = (Bitmap) createBitmap.get();
            for (int i = 0; i < bitmap2.getWidth(); i += 2) {
                for (int i2 = 0; i2 < bitmap2.getHeight(); i2 += 2) {
                    bitmap2.setPixel(i, i2, bitmap.getPixel(i, i2));
                }
            }
            CloseableReference<Bitmap> cloneOrNull = CloseableReference.cloneOrNull(createBitmap);
            return cloneOrNull;
        } finally {
            CloseableReference.closeSafely(createBitmap);
        }
    }
}
