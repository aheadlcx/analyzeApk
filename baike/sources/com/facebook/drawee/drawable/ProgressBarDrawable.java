package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class ProgressBarDrawable extends Drawable implements CloneableDrawable {
    private int mBackgroundColor = Integer.MIN_VALUE;
    private int mBarWidth = 20;
    private int mColor = -2147450625;
    private boolean mHideWhenZero = false;
    private boolean mIsVertical = false;
    private int mLevel = 0;
    private int mPadding = 10;
    private final Paint mPaint = new Paint(1);
    private final Path mPath = new Path();
    private int mRadius = 0;
    private final RectF mRect = new RectF();

    public void setColor(int i) {
        if (this.mColor != i) {
            this.mColor = i;
            invalidateSelf();
        }
    }

    public int getColor() {
        return this.mColor;
    }

    public void setBackgroundColor(int i) {
        if (this.mBackgroundColor != i) {
            this.mBackgroundColor = i;
            invalidateSelf();
        }
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setPadding(int i) {
        if (this.mPadding != i) {
            this.mPadding = i;
            invalidateSelf();
        }
    }

    public boolean getPadding(Rect rect) {
        rect.set(this.mPadding, this.mPadding, this.mPadding, this.mPadding);
        return this.mPadding != 0;
    }

    public void setBarWidth(int i) {
        if (this.mBarWidth != i) {
            this.mBarWidth = i;
            invalidateSelf();
        }
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public void setHideWhenZero(boolean z) {
        this.mHideWhenZero = z;
    }

    public boolean getHideWhenZero() {
        return this.mHideWhenZero;
    }

    public void setRadius(int i) {
        if (this.mRadius != i) {
            this.mRadius = i;
            invalidateSelf();
        }
    }

    public int getRadius() {
        return this.mRadius;
    }

    public void setIsVertical(boolean z) {
        if (this.mIsVertical != z) {
            this.mIsVertical = z;
            invalidateSelf();
        }
    }

    public boolean getIsVertical() {
        return this.mIsVertical;
    }

    protected boolean onLevelChange(int i) {
        this.mLevel = i;
        invalidateSelf();
        return true;
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(this.mPaint.getColor());
    }

    public void draw(Canvas canvas) {
        if (!this.mHideWhenZero || this.mLevel != 0) {
            if (this.mIsVertical) {
                drawVerticalBar(canvas, 10000, this.mBackgroundColor);
                drawVerticalBar(canvas, this.mLevel, this.mColor);
                return;
            }
            drawHorizontalBar(canvas, 10000, this.mBackgroundColor);
            drawHorizontalBar(canvas, this.mLevel, this.mColor);
        }
    }

    public Drawable cloneDrawable() {
        Drawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.mBackgroundColor = this.mBackgroundColor;
        progressBarDrawable.mColor = this.mColor;
        progressBarDrawable.mPadding = this.mPadding;
        progressBarDrawable.mBarWidth = this.mBarWidth;
        progressBarDrawable.mLevel = this.mLevel;
        progressBarDrawable.mRadius = this.mRadius;
        progressBarDrawable.mHideWhenZero = this.mHideWhenZero;
        progressBarDrawable.mIsVertical = this.mIsVertical;
        return progressBarDrawable;
    }

    private void drawHorizontalBar(Canvas canvas, int i, int i2) {
        Rect bounds = getBounds();
        int width = ((bounds.width() - (this.mPadding * 2)) * i) / 10000;
        int i3 = bounds.left + this.mPadding;
        int i4 = (bounds.bottom - this.mPadding) - this.mBarWidth;
        this.mRect.set((float) i3, (float) i4, (float) (width + i3), (float) (i4 + this.mBarWidth));
        drawBar(canvas, i2);
    }

    private void drawVerticalBar(Canvas canvas, int i, int i2) {
        Rect bounds = getBounds();
        int height = ((bounds.height() - (this.mPadding * 2)) * i) / 10000;
        int i3 = bounds.left + this.mPadding;
        int i4 = bounds.top + this.mPadding;
        this.mRect.set((float) i3, (float) i4, (float) (i3 + this.mBarWidth), (float) (i4 + height));
        drawBar(canvas, i2);
    }

    private void drawBar(Canvas canvas, int i) {
        this.mPaint.setColor(i);
        this.mPaint.setStyle(Style.FILL_AND_STROKE);
        this.mPath.reset();
        this.mPath.setFillType(FillType.EVEN_ODD);
        this.mPath.addRoundRect(this.mRect, (float) Math.min(this.mRadius, this.mBarWidth / 2), (float) Math.min(this.mRadius, this.mBarWidth / 2), Direction.CW);
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
