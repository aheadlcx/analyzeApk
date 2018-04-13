package com.facebook.fresco.animation.backend;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import javax.annotation.Nullable;

public class AnimationBackendDelegate<T extends AnimationBackend> implements AnimationBackend {
    private static final int ALPHA_UNSET = -1;
    @IntRange(from = -1, to = 255)
    private int mAlpha = -1;
    @Nullable
    private T mAnimationBackend;
    @Nullable
    private Rect mBounds;
    @Nullable
    private ColorFilter mColorFilter;

    public AnimationBackendDelegate(@Nullable T t) {
        this.mAnimationBackend = t;
    }

    public int getFrameCount() {
        return this.mAnimationBackend == null ? 0 : this.mAnimationBackend.getFrameCount();
    }

    public int getFrameDurationMs(int i) {
        return this.mAnimationBackend == null ? 0 : this.mAnimationBackend.getFrameDurationMs(i);
    }

    public int getLoopCount() {
        return this.mAnimationBackend == null ? 0 : this.mAnimationBackend.getLoopCount();
    }

    public boolean drawFrame(Drawable drawable, Canvas canvas, int i) {
        return this.mAnimationBackend != null && this.mAnimationBackend.drawFrame(drawable, canvas, i);
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.setAlpha(i);
        }
        this.mAlpha = i;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.setColorFilter(colorFilter);
        }
        this.mColorFilter = colorFilter;
    }

    public void setBounds(@Nullable Rect rect) {
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.setBounds(rect);
        }
        this.mBounds = rect;
    }

    public int getSizeInBytes() {
        return this.mAnimationBackend == null ? 0 : this.mAnimationBackend.getSizeInBytes();
    }

    public void clear() {
        if (this.mAnimationBackend != null) {
            this.mAnimationBackend.clear();
        }
    }

    public int getIntrinsicWidth() {
        if (this.mAnimationBackend == null) {
            return -1;
        }
        return this.mAnimationBackend.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.mAnimationBackend == null) {
            return -1;
        }
        return this.mAnimationBackend.getIntrinsicHeight();
    }

    public void setAnimationBackend(@Nullable T t) {
        this.mAnimationBackend = t;
        if (this.mAnimationBackend != null) {
            applyBackendProperties(this.mAnimationBackend);
        }
    }

    @Nullable
    public T getAnimationBackend() {
        return this.mAnimationBackend;
    }

    @SuppressLint({"Range"})
    private void applyBackendProperties(AnimationBackend animationBackend) {
        if (this.mBounds != null) {
            animationBackend.setBounds(this.mBounds);
        }
        if (this.mAlpha >= 0 && this.mAlpha <= 255) {
            animationBackend.setAlpha(this.mAlpha);
        }
        if (this.mColorFilter != null) {
            animationBackend.setColorFilter(this.mColorFilter);
        }
    }
}
