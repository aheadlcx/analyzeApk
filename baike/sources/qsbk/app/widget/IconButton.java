package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import qsbk.app.R;

public class IconButton extends View {
    public static final int STATE_GONE = 3;
    public static final int STATE_HIDE = 1;
    public static final int STATE_MELT = 2;
    public static final int STATE_SHOW = 0;
    private Drawable a;
    private CharSequence b;
    private TextPaint c;
    private int d;
    private int e;
    private int f;
    private StaticLayout g;
    private Rect h;
    private Rect i;
    private boolean j;

    public IconButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    public IconButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public IconButton(Context context) {
        super(context);
        a(null, 0);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.IconButton, i, 0);
        this.a = obtainStyledAttributes.getDrawable(0);
        this.b = obtainStyledAttributes.getText(4);
        this.c = new TextPaint();
        this.c.setColor(obtainStyledAttributes.getColor(2, -16777216));
        this.c.setTextSize(obtainStyledAttributes.getDimension(1, TypedValue.applyDimension(2, 14.0f, getResources().getDisplayMetrics())));
        this.c.setAntiAlias(true);
        this.d = obtainStyledAttributes.getDimensionPixelOffset(6, 0);
        this.f = obtainStyledAttributes.getInt(5, 0);
        this.e = obtainStyledAttributes.getInt(3, 3);
        obtainStyledAttributes.recycle();
        this.h = new Rect();
        this.i = new Rect();
        this.j = true;
        if (this.b == null) {
            this.b = "";
        }
        if (this.a != null) {
            this.a.setCallback(this);
        }
        setDrawingCacheEnabled(true);
        setDrawingCacheQuality(0);
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5 = 1048576;
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (size == 0) {
            size = 1048576;
        }
        if (size2 != 0) {
            i5 = size2;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        size -= paddingLeft;
        i5 -= paddingTop;
        if (!(this.a == null || this.f == 3)) {
            size2 = this.a.getIntrinsicWidth();
            int intrinsicHeight = this.a.getIntrinsicHeight();
            if (!(size2 == 0 || intrinsicHeight == 0)) {
                float min = Math.min(((float) Math.min(size, size2)) / ((float) size2), ((float) Math.min(i5, intrinsicHeight)) / ((float) intrinsicHeight));
                size2 = Math.round(((float) size2) * min);
                i5 = Math.round(min * ((float) intrinsicHeight));
                this.h.right = this.h.left + size2;
                this.h.bottom = this.h.top + i5;
                size2 += this.d;
                i3 = i5;
                i4 = size2;
                i5 = size - size2;
                if (this.g == null || this.j) {
                    this.j = false;
                    this.g = new StaticLayout(this.b, this.c, Math.min((int) Math.ceil((double) Layout.getDesiredWidth(this.b, this.c)), i5), Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                }
                i5 = this.g.getWidth();
                size2 = this.g.getHeight();
                this.i.right = this.i.left + i5;
                this.i.bottom = this.i.top + size2;
                size2 = Math.max(i3, size2) + paddingTop;
                setMeasuredDimension(resolveSize(Math.max((i5 + i4) + paddingLeft, getSuggestedMinimumWidth()), i), resolveSize(Math.max(size2, getSuggestedMinimumHeight()), i2));
            }
        }
        i3 = 0;
        i4 = 0;
        size2 = 0;
        i5 = size - size2;
        this.j = false;
        this.g = new StaticLayout(this.b, this.c, Math.min((int) Math.ceil((double) Layout.getDesiredWidth(this.b, this.c)), i5), Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        i5 = this.g.getWidth();
        size2 = this.g.getHeight();
        this.i.right = this.i.left + i5;
        this.i.bottom = this.i.top + size2;
        size2 = Math.max(i3, size2) + paddingTop;
        setMeasuredDimension(resolveSize(Math.max((i5 + i4) + paddingLeft, getSuggestedMinimumWidth()), i), resolveSize(Math.max(size2, getSuggestedMinimumHeight()), i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(i3 - i, i4 - i2);
    }

    private void a(int i, int i2) {
        int width = this.g.getWidth();
        int height = this.g.getHeight();
        Rect rect = new Rect(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        if (this.a != null && this.f <= 1) {
            int width2 = this.h.width();
            int height2 = this.h.height();
            int i3 = (width2 + width) + this.d;
            int max = Math.max(height2, height);
            Rect rect2 = new Rect();
            Gravity.apply(this.e, i3, max, rect, rect2);
            rect.set(rect2.left, rect2.top, rect2.left + width2, rect2.bottom);
            Gravity.apply(this.e, width2, height2, rect, this.h);
            rect.set(rect2.right - width, rect2.top, rect2.right, rect2.bottom);
        }
        Gravity.apply(this.e, width, height, rect, this.i);
    }

    protected void onDraw(Canvas canvas) {
        canvas.clipRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        if (this.a != null && this.f == 0) {
            this.a.setBounds(this.h);
            this.a.draw(canvas);
        }
        canvas.save();
        canvas.translate((float) this.i.left, (float) this.i.top);
        this.g.draw(canvas);
        canvas.restore();
    }

    public CharSequence getText() {
        return this.b;
    }

    public void setText(CharSequence charSequence) {
        if (!charSequence.equals(this.b)) {
            this.b = charSequence;
            this.j = true;
            requestLayout();
        }
    }

    public void setTextResource(int i) {
        setText(getResources().getString(i));
    }

    public int getTextColor() {
        return this.c.getColor();
    }

    public void setTextColor(int i) {
        this.c.setColor(i);
        invalidate(this.i);
    }

    public float getTextSize() {
        return this.c.getTextSize();
    }

    public void setTextSize(float f) {
        if (this.c.getTextSize() != f) {
            this.c.setTextSize(f);
            this.j = true;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.e;
    }

    public void setGravity(int i) {
        if (this.e != i) {
            this.e = i;
            if (this.g != null) {
                a(getWidth(), getHeight());
                invalidate();
            }
        }
    }

    public Drawable getIcon() {
        return this.a;
    }

    public void setIcon(Drawable drawable) {
        if (this.a != drawable) {
            unscheduleDrawable(this.a);
            this.a = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
            }
            requestLayout();
        }
    }

    public void setIconResource(int i) {
        setIcon(getResources().getDrawable(i));
    }

    public int getIconVisibility() {
        return this.f;
    }

    public void setIconVisibility(int i) {
        int i2 = this.f;
        if (i2 != i) {
            this.f = i;
            if (i2 < 2 && i < 2) {
                postInvalidate();
            } else if (i2 == 3 || i == 3) {
                requestLayout();
            } else {
                a(getWidth(), getHeight());
            }
        }
    }

    public int getIconPadding() {
        return this.d;
    }

    public void setIconPadding(int i) {
        if (this.d != i) {
            this.d = i;
            requestLayout();
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.a || super.verifyDrawable(drawable);
    }
}
