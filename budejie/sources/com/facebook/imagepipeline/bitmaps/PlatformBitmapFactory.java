package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

public abstract class PlatformBitmapFactory {
    private static PlatformBitmapFactory$BitmapCreationObserver sBitmapCreationObserver;

    public abstract CloseableReference<Bitmap> createBitmapInternal(int i, int i2, Config config);

    public CloseableReference<Bitmap> createBitmap(int i, int i2, Config config) {
        return createBitmap(i, i2, config, null);
    }

    public CloseableReference<Bitmap> createBitmap(int i, int i2) {
        return createBitmap(i, i2, Config.ARGB_8888);
    }

    public CloseableReference<Bitmap> createBitmap(int i, int i2, Config config, @Nullable Object obj) {
        CloseableReference<Bitmap> createBitmapInternal = createBitmapInternal(i, i2, config);
        addBitmapReference((Bitmap) createBitmapInternal.get(), obj);
        return createBitmapInternal;
    }

    public CloseableReference<Bitmap> createBitmap(int i, int i2, @Nullable Object obj) {
        return createBitmap(i, i2, Config.ARGB_8888, obj);
    }

    public CloseableReference<Bitmap> createBitmap(Bitmap bitmap) {
        return createBitmap(bitmap, null);
    }

    public CloseableReference<Bitmap> createBitmap(Bitmap bitmap, @Nullable Object obj) {
        return createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), obj);
    }

    public CloseableReference<Bitmap> createBitmap(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return createBitmap(bitmap, i, i2, i3, i4, null);
    }

    public CloseableReference<Bitmap> createBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, @Nullable Object obj) {
        return createBitmap(bitmap, i, i2, i3, i4, null, false, obj);
    }

    public CloseableReference<Bitmap> createBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, @Nullable Matrix matrix, boolean z) {
        return createBitmap(bitmap, i, i2, i3, i4, matrix, z, null);
    }

    public CloseableReference<Bitmap> createScaledBitmap(Bitmap bitmap, int i, int i2, boolean z) {
        return createScaledBitmap(bitmap, i, i2, z, null);
    }

    public CloseableReference<Bitmap> createScaledBitmap(Bitmap bitmap, int i, int i2, boolean z, @Nullable Object obj) {
        checkWidthHeight(i, i2);
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        matrix.setScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return createBitmap(bitmap, 0, 0, width, height, matrix, z, obj);
    }

    public CloseableReference<Bitmap> createBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, @Nullable Matrix matrix, boolean z, @Nullable Object obj) {
        CloseableReference<Bitmap> createBitmap;
        Paint paint;
        Preconditions.checkNotNull(bitmap, "Source bitmap cannot be null");
        checkXYSign(i, i2);
        checkWidthHeight(i3, i4);
        checkFinalImageBounds(bitmap, i, i2, i3, i4);
        Canvas canvas = new Canvas();
        Rect rect = new Rect(i, i2, i + i3, i2 + i4);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i3, (float) i4);
        Config suitableBitmapConfig = getSuitableBitmapConfig(bitmap);
        if (matrix == null || matrix.isIdentity()) {
            createBitmap = createBitmap(i3, i4, suitableBitmapConfig, bitmap.hasAlpha(), obj);
            paint = null;
        } else {
            Object obj2;
            if (matrix.rectStaysRect()) {
                obj2 = null;
            } else {
                obj2 = 1;
            }
            RectF rectF2 = new RectF();
            matrix.mapRect(rectF2, rectF);
            int round = Math.round(rectF2.width());
            int round2 = Math.round(rectF2.height());
            if (obj2 != null) {
                suitableBitmapConfig = Config.ARGB_8888;
            }
            boolean z2 = obj2 != null || bitmap.hasAlpha();
            CloseableReference<Bitmap> createBitmap2 = createBitmap(round, round2, suitableBitmapConfig, z2, obj);
            canvas.translate(-rectF2.left, -rectF2.top);
            canvas.concat(matrix);
            Paint paint2 = new Paint();
            paint2.setFilterBitmap(z);
            if (obj2 != null) {
                paint2.setAntiAlias(true);
            }
            createBitmap = createBitmap2;
            paint = paint2;
        }
        Bitmap bitmap2 = (Bitmap) createBitmap.get();
        bitmap2.setDensity(bitmap.getDensity());
        if (VERSION.SDK_INT >= 12) {
            bitmap2.setHasAlpha(bitmap.hasAlpha());
        }
        if (VERSION.SDK_INT >= 19) {
            bitmap2.setPremultiplied(bitmap.isPremultiplied());
        }
        canvas.setBitmap(bitmap2);
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    public CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int i, int i2, Config config) {
        return createBitmap(displayMetrics, i, i2, config, null);
    }

    public CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int i, int i2, Config config, @Nullable Object obj) {
        return createBitmap(displayMetrics, i, i2, config, true, obj);
    }

    private CloseableReference<Bitmap> createBitmap(int i, int i2, Config config, boolean z) {
        return createBitmap(i, i2, config, z, null);
    }

    private CloseableReference<Bitmap> createBitmap(int i, int i2, Config config, boolean z, @Nullable Object obj) {
        return createBitmap(null, i, i2, config, z, obj);
    }

    private CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int i, int i2, Config config, boolean z) {
        return createBitmap(displayMetrics, i, i2, config, z, null);
    }

    private CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int i, int i2, Config config, boolean z, @Nullable Object obj) {
        checkWidthHeight(i, i2);
        CloseableReference<Bitmap> createBitmapInternal = createBitmapInternal(i, i2, config);
        Bitmap bitmap = (Bitmap) createBitmapInternal.get();
        if (displayMetrics != null) {
            bitmap.setDensity(displayMetrics.densityDpi);
        }
        if (VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(z);
        }
        if (config == Config.ARGB_8888 && !z) {
            bitmap.eraseColor(-16777216);
        }
        addBitmapReference((Bitmap) createBitmapInternal.get(), obj);
        return createBitmapInternal;
    }

    public CloseableReference<Bitmap> createBitmap(int[] iArr, int i, int i2, Config config) {
        return createBitmap(iArr, i, i2, config, null);
    }

    public CloseableReference<Bitmap> createBitmap(int[] iArr, int i, int i2, Config config, @Nullable Object obj) {
        CloseableReference<Bitmap> createBitmapInternal = createBitmapInternal(i, i2, config);
        ((Bitmap) createBitmapInternal.get()).setPixels(iArr, 0, i, 0, 0, i, i2);
        addBitmapReference((Bitmap) createBitmapInternal.get(), obj);
        return createBitmapInternal;
    }

    public CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int[] iArr, int i, int i2, Config config) {
        return createBitmap(displayMetrics, iArr, i, i2, config, null);
    }

    public CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int[] iArr, int i, int i2, Config config, @Nullable Object obj) {
        return createBitmap(displayMetrics, iArr, 0, i, i, i2, config, obj);
    }

    public CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int[] iArr, int i, int i2, int i3, int i4, Config config) {
        return createBitmap(displayMetrics, iArr, i, i2, i3, i4, config, null);
    }

    public CloseableReference<Bitmap> createBitmap(DisplayMetrics displayMetrics, int[] iArr, int i, int i2, int i3, int i4, Config config, @Nullable Object obj) {
        CloseableReference<Bitmap> createBitmap = createBitmap(displayMetrics, i3, i4, config, obj);
        ((Bitmap) createBitmap.get()).setPixels(iArr, i, i2, 0, 0, i3, i4);
        return createBitmap;
    }

    private static Config getSuitableBitmapConfig(Bitmap bitmap) {
        Config config = Config.ARGB_8888;
        Config config2 = bitmap.getConfig();
        if (config2 == null) {
            return config;
        }
        switch (PlatformBitmapFactory$1.$SwitchMap$android$graphics$Bitmap$Config[config2.ordinal()]) {
            case 1:
                return Config.RGB_565;
            case 2:
                return Config.ALPHA_8;
            default:
                return Config.ARGB_8888;
        }
    }

    private static void checkWidthHeight(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "width must be > 0");
        if (i2 <= 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "height must be > 0");
    }

    private static void checkXYSign(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "x must be >= 0");
        if (i2 < 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "y must be >= 0");
    }

    private static void checkFinalImageBounds(Bitmap bitmap, int i, int i2, int i3, int i4) {
        boolean z = true;
        Preconditions.checkArgument(i + i3 <= bitmap.getWidth(), "x + width must be <= bitmap.width()");
        if (i2 + i4 > bitmap.getHeight()) {
            z = false;
        }
        Preconditions.checkArgument(z, "y + height must be <= bitmap.height()");
    }

    public void setCreationListener(PlatformBitmapFactory$BitmapCreationObserver platformBitmapFactory$BitmapCreationObserver) {
        if (sBitmapCreationObserver == null) {
            sBitmapCreationObserver = platformBitmapFactory$BitmapCreationObserver;
        }
    }

    public void addBitmapReference(Bitmap bitmap, @Nullable Object obj) {
        if (sBitmapCreationObserver != null) {
            sBitmapCreationObserver.onBitmapCreated(bitmap, obj);
        }
    }
}
