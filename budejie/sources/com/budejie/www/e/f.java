package com.budejie.www.e;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.a.a;
import com.nostra13.universalimageloader.core.a.c;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

public class f extends a {
    public f(boolean z) {
        super(z);
    }

    protected Bitmap a(Bitmap bitmap, c cVar, int i, boolean z) {
        Bitmap createBitmap;
        Matrix matrix = new Matrix();
        ImageScaleType d = cVar.d();
        if (d == ImageScaleType.EXACTLY || d == ImageScaleType.EXACTLY_STRETCHED) {
            float b = com.nostra13.universalimageloader.b.c.b(new com.nostra13.universalimageloader.core.assist.c(bitmap.getWidth(), bitmap.getHeight(), i), cVar.c(), cVar.e(), d == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(b, 1.0f) != 0) {
                matrix.setScale(b, b);
                if (this.a) {
                    e.a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", new Object[]{r2, r2.a(b), Float.valueOf(b), cVar.a()});
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.a) {
                e.a("Flip image horizontally [%s]", new Object[]{cVar.a()});
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.a) {
                e.a("Rotate image on %1$d° [%2$s]", new Object[]{Integer.valueOf(i), cVar.a()});
            }
        }
        if (cVar.e() == ViewScaleType.FIT_INSIDE) {
            createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } else {
            int height;
            com.nostra13.universalimageloader.core.assist.c c = cVar.c();
            int width = bitmap.getWidth();
            int height2 = bitmap.getHeight();
            if (width != height2) {
                height2 = (bitmap.getWidth() * c.b()) / c.a();
            }
            if (height2 > bitmap.getHeight()) {
                height = bitmap.getHeight();
                width = (c.a() * height) / c.b();
            } else {
                height = height2;
            }
            try {
                createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            } catch (Exception e) {
                e.printStackTrace();
                createBitmap = null;
            }
        }
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
