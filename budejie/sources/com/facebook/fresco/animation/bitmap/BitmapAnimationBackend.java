package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegateWithInactivityCheck.InactivityListener;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nullable;

public class BitmapAnimationBackend implements AnimationBackend, InactivityListener {
    public static final int FRAME_TYPE_CACHED = 0;
    public static final int FRAME_TYPE_CREATED = 2;
    public static final int FRAME_TYPE_FALLBACK = 3;
    public static final int FRAME_TYPE_REUSED = 1;
    public static final int FRAME_TYPE_UNKNOWN = -1;
    private final AnimationInformation mAnimationInformation;
    private Config mBitmapConfig = Config.ARGB_8888;
    private final BitmapFrameCache mBitmapFrameCache;
    @Nullable
    private final BitmapFramePreparationStrategy mBitmapFramePreparationStrategy;
    @Nullable
    private final BitmapFramePreparer mBitmapFramePreparer;
    private final BitmapFrameRenderer mBitmapFrameRenderer;
    private int mBitmapHeight;
    private int mBitmapWidth;
    @Nullable
    private Rect mBounds;
    @Nullable
    private FrameListener mFrameListener;
    private final Paint mPaint;
    private final PlatformBitmapFactory mPlatformBitmapFactory;

    public interface FrameListener {
        void onDrawFrameStart(BitmapAnimationBackend bitmapAnimationBackend, int i);

        void onFrameDrawn(BitmapAnimationBackend bitmapAnimationBackend, int i, int i2);

        void onFrameDropped(BitmapAnimationBackend bitmapAnimationBackend, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameType {
    }

    public BitmapAnimationBackend(PlatformBitmapFactory platformBitmapFactory, BitmapFrameCache bitmapFrameCache, AnimationInformation animationInformation, BitmapFrameRenderer bitmapFrameRenderer, @Nullable BitmapFramePreparationStrategy bitmapFramePreparationStrategy, @Nullable BitmapFramePreparer bitmapFramePreparer) {
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mBitmapFrameCache = bitmapFrameCache;
        this.mAnimationInformation = animationInformation;
        this.mBitmapFrameRenderer = bitmapFrameRenderer;
        this.mBitmapFramePreparationStrategy = bitmapFramePreparationStrategy;
        this.mBitmapFramePreparer = bitmapFramePreparer;
        this.mPaint = new Paint(6);
        updateBitmapDimensions();
    }

    public void setBitmapConfig(Config config) {
        this.mBitmapConfig = config;
    }

    public void setFrameListener(@Nullable FrameListener frameListener) {
        this.mFrameListener = frameListener;
    }

    public int getFrameCount() {
        return this.mAnimationInformation.getFrameCount();
    }

    public int getFrameDurationMs(int i) {
        return this.mAnimationInformation.getFrameDurationMs(i);
    }

    public int getLoopCount() {
        return this.mAnimationInformation.getLoopCount();
    }

    public boolean drawFrame(Drawable drawable, Canvas canvas, int i) {
        if (this.mFrameListener != null) {
            this.mFrameListener.onDrawFrameStart(this, i);
        }
        boolean drawFrameOrFallback = drawFrameOrFallback(canvas, i, 0);
        if (!(drawFrameOrFallback || this.mFrameListener == null)) {
            this.mFrameListener.onFrameDropped(this, i);
        }
        if (!(this.mBitmapFramePreparationStrategy == null || this.mBitmapFramePreparer == null)) {
            this.mBitmapFramePreparationStrategy.prepareFrames(this.mBitmapFramePreparer, this.mBitmapFrameCache, this, i);
        }
        return drawFrameOrFallback;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean drawFrameOrFallback(android.graphics.Canvas r10, int r11, int r12) {
        /*
        r9 = this;
        r3 = 3;
        r2 = 2;
        r4 = -1;
        r0 = 1;
        r1 = 0;
        r5 = 0;
        switch(r12) {
            case 0: goto L_0x000d;
            case 1: goto L_0x0024;
            case 2: goto L_0x0041;
            case 3: goto L_0x005f;
            default: goto L_0x0009;
        };
    L_0x0009:
        com.facebook.common.references.CloseableReference.closeSafely(r5);
    L_0x000c:
        return r1;
    L_0x000d:
        r1 = r9.mBitmapFrameCache;	 Catch:{ all -> 0x006e }
        r2 = r1.getCachedFrame(r11);	 Catch:{ all -> 0x006e }
        r1 = 0;
        r1 = r9.drawBitmapAndCache(r11, r2, r10, r1);	 Catch:{ all -> 0x0074 }
    L_0x0018:
        com.facebook.common.references.CloseableReference.closeSafely(r2);
        if (r1 != 0) goto L_0x000c;
    L_0x001d:
        if (r0 == r4) goto L_0x000c;
    L_0x001f:
        r1 = r9.drawFrameOrFallback(r10, r11, r0);
        goto L_0x000c;
    L_0x0024:
        r3 = r9.mBitmapFrameCache;	 Catch:{ all -> 0x006e }
        r6 = r9.mBitmapWidth;	 Catch:{ all -> 0x006e }
        r7 = r9.mBitmapHeight;	 Catch:{ all -> 0x006e }
        r3 = r3.getBitmapToReuseForFrame(r11, r6, r7);	 Catch:{ all -> 0x006e }
        r5 = r9.renderFrameInBitmap(r11, r3);	 Catch:{ all -> 0x0076 }
        if (r5 == 0) goto L_0x003f;
    L_0x0034:
        r5 = 1;
        r5 = r9.drawBitmapAndCache(r11, r3, r10, r5);	 Catch:{ all -> 0x0076 }
        if (r5 == 0) goto L_0x003f;
    L_0x003b:
        r1 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x0018;
    L_0x003f:
        r0 = r1;
        goto L_0x003b;
    L_0x0041:
        r2 = r9.mPlatformBitmapFactory;	 Catch:{ all -> 0x006e }
        r6 = r9.mBitmapWidth;	 Catch:{ all -> 0x006e }
        r7 = r9.mBitmapHeight;	 Catch:{ all -> 0x006e }
        r8 = r9.mBitmapConfig;	 Catch:{ all -> 0x006e }
        r2 = r2.createBitmap(r6, r7, r8);	 Catch:{ all -> 0x006e }
        r5 = r9.renderFrameInBitmap(r11, r2);	 Catch:{ all -> 0x0074 }
        if (r5 == 0) goto L_0x005d;
    L_0x0053:
        r5 = 2;
        r5 = r9.drawBitmapAndCache(r11, r2, r10, r5);	 Catch:{ all -> 0x0074 }
        if (r5 == 0) goto L_0x005d;
    L_0x005a:
        r1 = r0;
        r0 = r3;
        goto L_0x0018;
    L_0x005d:
        r0 = r1;
        goto L_0x005a;
    L_0x005f:
        r0 = r9.mBitmapFrameCache;	 Catch:{ all -> 0x006e }
        r1 = r0.getFallbackFrame(r11);	 Catch:{ all -> 0x006e }
        r0 = 3;
        r0 = r9.drawBitmapAndCache(r11, r1, r10, r0);	 Catch:{ all -> 0x0079 }
        r2 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0018;
    L_0x006e:
        r0 = move-exception;
        r2 = r5;
    L_0x0070:
        com.facebook.common.references.CloseableReference.closeSafely(r2);
        throw r0;
    L_0x0074:
        r0 = move-exception;
        goto L_0x0070;
    L_0x0076:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0070;
    L_0x0079:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.BitmapAnimationBackend.drawFrameOrFallback(android.graphics.Canvas, int, int):boolean");
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setBounds(@Nullable Rect rect) {
        this.mBounds = rect;
        this.mBitmapFrameRenderer.setBounds(rect);
        updateBitmapDimensions();
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public int getSizeInBytes() {
        return this.mBitmapFrameCache.getSizeInBytes();
    }

    public void clear() {
        this.mBitmapFrameCache.clear();
    }

    public void onInactive() {
        clear();
    }

    private void updateBitmapDimensions() {
        int i = -1;
        this.mBitmapWidth = this.mBitmapFrameRenderer.getIntrinsicWidth();
        if (this.mBitmapWidth == -1) {
            this.mBitmapWidth = this.mBounds == null ? -1 : this.mBounds.width();
        }
        this.mBitmapHeight = this.mBitmapFrameRenderer.getIntrinsicHeight();
        if (this.mBitmapHeight == -1) {
            if (this.mBounds != null) {
                i = this.mBounds.height();
            }
            this.mBitmapHeight = i;
        }
    }

    private boolean renderFrameInBitmap(int i, @Nullable CloseableReference<Bitmap> closeableReference) {
        if (!CloseableReference.isValid(closeableReference)) {
            return false;
        }
        boolean renderFrame = this.mBitmapFrameRenderer.renderFrame(i, (Bitmap) closeableReference.get());
        if (renderFrame) {
            return renderFrame;
        }
        CloseableReference.closeSafely((CloseableReference) closeableReference);
        return renderFrame;
    }

    private boolean drawBitmapAndCache(int i, @Nullable CloseableReference<Bitmap> closeableReference, Canvas canvas, int i2) {
        if (!CloseableReference.isValid(closeableReference)) {
            return false;
        }
        if (this.mBounds == null) {
            canvas.drawBitmap((Bitmap) closeableReference.get(), 0.0f, 0.0f, this.mPaint);
        } else {
            canvas.drawBitmap((Bitmap) closeableReference.get(), null, this.mBounds, this.mPaint);
        }
        if (i2 != 3) {
            this.mBitmapFrameCache.onFrameRendered(i, closeableReference, i2);
        }
        if (this.mFrameListener != null) {
            this.mFrameListener.onFrameDrawn(this, i, i2);
        }
        return true;
    }
}
