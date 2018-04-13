package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GiftSceneUtil {
    public static final String GIFT_NUM_BITMAP_URI = "cn.v6.sixrooms.gift";

    public static void getScaleBitmap(String str, float f, float f2, BaseBitmapDataSubscriber baseBitmapDataSubscriber) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(true).setResizeOptions(new ResizeOptions((int) f, (int) f2)).build(), null).subscribe(baseBitmapDataSubscriber, CallerThreadExecutor.getInstance());
    }

    public static void getIconBitmap(String str, BaseBitmapDataSubscriber baseBitmapDataSubscriber) {
        if (baseBitmapDataSubscriber == null) {
            Fresco.getImagePipeline().prefetchToBitmapCache(ImageRequest.fromUri(str), null);
        } else {
            Fresco.getImagePipeline().fetchImageFromBitmapCache(ImageRequest.fromUri(str), null).subscribe(baseBitmapDataSubscriber, CallerThreadExecutor.getInstance());
        }
    }

    public static void getOriginIconBitmap(String str, BaseBitmapDataSubscriber baseBitmapDataSubscriber) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(true).build(), null).subscribe(baseBitmapDataSubscriber, CallerThreadExecutor.getInstance());
    }

    public static String generateNumBitmapKey(NumType numType, int i, int i2) {
        return new StringBuilder(GIFT_NUM_BITMAP_URI).append(numType.getValue()).append(i).append("_").append(i2).toString();
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    int height = bitmap.getHeight();
                    int width = bitmap.getWidth();
                    float f = ((float) i) / ((float) width);
                    float f2 = ((float) i2) / ((float) height);
                    Matrix matrix = new Matrix();
                    matrix.postScale(f, f2);
                    return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Bitmap createCircleImage(Bitmap bitmap, int i) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawCircle((float) (i / 2), (float) (i / 2), (float) (i / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static void scaleBitmap(String str, BaseBitmapDataSubscriber baseBitmapDataSubscriber, BasePostprocessor basePostprocessor) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(true).setPostprocessor(basePostprocessor).build(), null).subscribe(baseBitmapDataSubscriber, CallerThreadExecutor.getInstance());
    }

    public static Bitmap processImage(String str, int i, NumType numType) {
        int i2 = 0;
        try {
            int i3;
            Bitmap externalBitmap;
            List arrayList = new ArrayList();
            while (i > 0) {
                arrayList.add(0, Integer.valueOf(i % 10));
                i /= 10;
            }
            Bitmap[] bitmapArr = new Bitmap[arrayList.size()];
            Bitmap externalBitmap2 = AnimSceneResManager.getInstance().getExternalBitmap(str + File.separator + numType.getValue() + "x.png");
            int width = externalBitmap2.getWidth();
            int height = externalBitmap2.getHeight();
            int i4 = width;
            for (i3 = 0; i3 < arrayList.size(); i3++) {
                externalBitmap = AnimSceneResManager.getInstance().getExternalBitmap(str + File.separator + numType.getValue() + ((Integer) arrayList.get(i3)).intValue() + ".png");
                i4 += externalBitmap.getWidth();
                bitmapArr[i3] = externalBitmap;
            }
            externalBitmap = Bitmap.createBitmap(i4, height, externalBitmap2.getConfig());
            Canvas canvas = new Canvas(externalBitmap);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawBitmap(externalBitmap2, 0.0f, 0.0f, paint);
            i3 = externalBitmap2.getWidth();
            while (i2 < bitmapArr.length) {
                canvas.drawBitmap(bitmapArr[i2], (float) i3, 0.0f, paint);
                i3 += bitmapArr[i2].getWidth();
                i2++;
            }
            return externalBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
