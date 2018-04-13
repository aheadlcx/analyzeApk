package com.facebook.widget.text.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BetterImageSpan extends ReplacementSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    private final int mAlignment;
    private Rect mBounds;
    private final Drawable mDrawable;
    private final FontMetricsInt mFontMetricsInt;
    private int mHeight;
    private int mWidth;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BetterImageSpanAlignment {
    }

    public static final int normalizeAlignment(int i) {
        switch (i) {
            case 0:
                return 0;
            case 2:
                return 2;
            default:
                return 1;
        }
    }

    public BetterImageSpan(Drawable drawable) {
        this(drawable, 1);
    }

    public BetterImageSpan(Drawable drawable, int i) {
        this.mFontMetricsInt = new FontMetricsInt();
        this.mDrawable = drawable;
        this.mAlignment = i;
        updateBounds();
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        updateBounds();
        if (fontMetricsInt == null) {
            return this.mWidth;
        }
        int offsetAboveBaseline = getOffsetAboveBaseline(fontMetricsInt);
        int i3 = this.mHeight + offsetAboveBaseline;
        if (offsetAboveBaseline < fontMetricsInt.ascent) {
            fontMetricsInt.ascent = offsetAboveBaseline;
        }
        if (offsetAboveBaseline < fontMetricsInt.top) {
            fontMetricsInt.top = offsetAboveBaseline;
        }
        if (i3 > fontMetricsInt.descent) {
            fontMetricsInt.descent = i3;
        }
        if (i3 > fontMetricsInt.bottom) {
            fontMetricsInt.bottom = i3;
        }
        return this.mWidth;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        paint.getFontMetricsInt(this.mFontMetricsInt);
        int offsetAboveBaseline = getOffsetAboveBaseline(this.mFontMetricsInt) + i4;
        canvas.translate(f, (float) offsetAboveBaseline);
        this.mDrawable.draw(canvas);
        canvas.translate(-f, (float) (-offsetAboveBaseline));
    }

    public void updateBounds() {
        this.mBounds = this.mDrawable.getBounds();
        this.mWidth = this.mBounds.width();
        this.mHeight = this.mBounds.height();
    }

    private int getOffsetAboveBaseline(FontMetricsInt fontMetricsInt) {
        switch (this.mAlignment) {
            case 0:
                return fontMetricsInt.descent - this.mHeight;
            case 2:
                return (((fontMetricsInt.descent - fontMetricsInt.ascent) - this.mHeight) / 2) + fontMetricsInt.ascent;
            default:
                return -this.mHeight;
        }
    }
}
