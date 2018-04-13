package com.zhihu.matisse.thumbnail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;

class ThumbnailGeneratorHelper {
    ThumbnailGeneratorHelper() {
    }

    static Bitmap getImageThumbnail(String str, int i, int i2) {
        Bitmap extractThumbnail;
        Exception exception;
        int i3 = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i4 = options.outWidth / i;
        int i5 = options.outHeight / i2;
        if (i4 >= i5) {
            i4 = i5;
        }
        if (i4 > 0) {
            i3 = i4;
        }
        options.inSampleSize = i3;
        try {
            Bitmap decodeFile2 = BitmapFactory.decodeFile(str, options);
            try {
                extractThumbnail = ThumbnailUtils.extractThumbnail(decodeFile2, i, i2, 2);
            } catch (Exception e) {
                Exception exception2 = e;
                extractThumbnail = decodeFile2;
                exception = exception2;
                exception.printStackTrace();
                return extractThumbnail;
            }
        } catch (Exception e2) {
            exception = e2;
            extractThumbnail = decodeFile;
            exception.printStackTrace();
            return extractThumbnail;
        }
        return extractThumbnail;
    }

    static Bitmap getVideoThumbnail(String str, int i, int i2, int i3) {
        Bitmap extractThumbnail;
        Exception exception;
        Exception exception2;
        Bitmap bitmap = null;
        try {
            bitmap = ThumbnailUtils.createVideoThumbnail(str, i3);
            try {
                extractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, i, i2, 2);
            } catch (Exception e) {
                exception = e;
                extractThumbnail = bitmap;
                exception2 = exception;
                exception2.printStackTrace();
                return extractThumbnail;
            }
        } catch (Exception e2) {
            exception = e2;
            extractThumbnail = bitmap;
            exception2 = exception;
            exception2.printStackTrace();
            return extractThumbnail;
        }
        return extractThumbnail;
    }
}
