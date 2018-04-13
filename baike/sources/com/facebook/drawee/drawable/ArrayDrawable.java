package com.facebook.drawee.drawable;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import com.facebook.common.internal.Preconditions;
import javax.annotation.Nullable;

public class ArrayDrawable extends Drawable implements Callback, TransformAwareDrawable, TransformCallback {
    private final DrawableParent[] mDrawableParents;
    private final DrawableProperties mDrawableProperties = new DrawableProperties();
    private boolean mIsMutated = false;
    private boolean mIsStateful = false;
    private boolean mIsStatefulCalculated = false;
    private final Drawable[] mLayers;
    private final Rect mTmpRect = new Rect();
    private TransformCallback mTransformCallback;

    public ArrayDrawable(Drawable[] drawableArr) {
        int i = 0;
        Preconditions.checkNotNull(drawableArr);
        this.mLayers = drawableArr;
        while (i < this.mLayers.length) {
            DrawableUtils.setCallbacks(this.mLayers[i], this, this);
            i++;
        }
        this.mDrawableParents = new DrawableParent[this.mLayers.length];
    }

    public int getNumberOfLayers() {
        return this.mLayers.length;
    }

    @Nullable
    public Drawable getDrawable(int i) {
        boolean z;
        boolean z2 = true;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (i >= this.mLayers.length) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        return this.mLayers[i];
    }

    @Nullable
    public Drawable setDrawable(int i, @Nullable Drawable drawable) {
        boolean z = true;
        Preconditions.checkArgument(i >= 0);
        if (i >= this.mLayers.length) {
            z = false;
        }
        Preconditions.checkArgument(z);
        Drawable drawable2 = this.mLayers[i];
        if (drawable != drawable2) {
            if (drawable != null && this.mIsMutated) {
                drawable.mutate();
            }
            DrawableUtils.setCallbacks(this.mLayers[i], null, null);
            DrawableUtils.setCallbacks(drawable, null, null);
            DrawableUtils.setDrawableProperties(drawable, this.mDrawableProperties);
            DrawableUtils.copyProperties(drawable, this);
            DrawableUtils.setCallbacks(drawable, this, this);
            this.mIsStatefulCalculated = false;
            this.mLayers[i] = drawable;
            invalidateSelf();
        }
        return drawable2;
    }

    public int getIntrinsicWidth() {
        int i = -1;
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                i = Math.max(i, drawable.getIntrinsicWidth());
            }
        }
        return i > 0 ? i : -1;
    }

    public int getIntrinsicHeight() {
        int i = -1;
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                i = Math.max(i, drawable.getIntrinsicHeight());
            }
        }
        return i > 0 ? i : -1;
    }

    protected void onBoundsChange(Rect rect) {
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setBounds(rect);
            }
        }
    }

    public boolean isStateful() {
        if (!this.mIsStatefulCalculated) {
            this.mIsStateful = false;
            for (Drawable drawable : this.mLayers) {
                int i;
                boolean z = this.mIsStateful;
                if (drawable == null || !drawable.isStateful()) {
                    i = 0;
                } else {
                    i = 1;
                }
                this.mIsStateful = i | z;
            }
            this.mIsStatefulCalculated = true;
        }
        return this.mIsStateful;
    }

    protected boolean onStateChange(int[] iArr) {
        int i = 0;
        boolean z = false;
        while (i < this.mLayers.length) {
            Drawable drawable = this.mLayers[i];
            if (drawable != null && drawable.setState(iArr)) {
                z = true;
            }
            i++;
        }
        return z;
    }

    protected boolean onLevelChange(int i) {
        int i2 = 0;
        boolean z = false;
        while (i2 < this.mLayers.length) {
            Drawable drawable = this.mLayers[i2];
            if (drawable != null && drawable.setLevel(i)) {
                z = true;
            }
            i2++;
        }
        return z;
    }

    public void draw(Canvas canvas) {
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    public boolean getPadding(Rect rect) {
        int i = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        Rect rect2 = this.mTmpRect;
        while (i < this.mLayers.length) {
            Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.getPadding(rect2);
                rect.left = Math.max(rect.left, rect2.left);
                rect.top = Math.max(rect.top, rect2.top);
                rect.right = Math.max(rect.right, rect2.right);
                rect.bottom = Math.max(rect.bottom, rect2.bottom);
            }
            i++;
        }
        return true;
    }

    public Drawable mutate() {
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.mutate();
            }
        }
        this.mIsMutated = true;
        return this;
    }

    public int getOpacity() {
        if (this.mLayers.length == 0) {
            return -2;
        }
        int i = -1;
        for (int i2 = 1; i2 < this.mLayers.length; i2++) {
            Drawable drawable = this.mLayers[i2];
            if (drawable != null) {
                i = Drawable.resolveOpacity(i, drawable.getOpacity());
            }
        }
        return i;
    }

    public void setAlpha(int i) {
        this.mDrawableProperties.setAlpha(i);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setAlpha(i);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setDither(boolean z) {
        this.mDrawableProperties.setDither(z);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    public void setFilterBitmap(boolean z) {
        this.mDrawableProperties.setFilterBitmap(z);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setFilterBitmap(z);
            }
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setVisible(z, z2);
            }
        }
        return visible;
    }

    public DrawableParent getDrawableParentForIndex(int i) {
        boolean z = true;
        Preconditions.checkArgument(i >= 0);
        if (i >= this.mDrawableParents.length) {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (this.mDrawableParents[i] == null) {
            this.mDrawableParents[i] = createDrawableParentForIndex(i);
        }
        return this.mDrawableParents[i];
    }

    private DrawableParent createDrawableParentForIndex(final int i) {
        return new DrawableParent() {
            public Drawable setDrawable(Drawable drawable) {
                return ArrayDrawable.this.setDrawable(i, drawable);
            }

            public Drawable getDrawable() {
                return ArrayDrawable.this.getDrawable(i);
            }
        };
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    public void getTransform(Matrix matrix) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(matrix);
        } else {
            matrix.reset();
        }
    }

    public void getRootBounds(RectF rectF) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getRootBounds(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    @TargetApi(21)
    public void setHotspot(float f, float f2) {
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setHotspot(f, f2);
            }
        }
    }
}
