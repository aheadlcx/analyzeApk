package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import qsbk.app.R;

public class IndicatorView extends View {
    private static final int[] a = new int[]{16842908};
    private static final int[] b = new int[0];
    private Drawable c;
    private int d;
    private int e;
    private float f;
    private int g;
    private Rect h;

    public IndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    public IndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public IndicatorView(Context context) {
        super(context);
        a(null, 0);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.IndicatorView, i, 0);
        this.c = obtainStyledAttributes.getDrawable(2);
        this.d = obtainStyledAttributes.getInt(0, 1);
        this.e = obtainStyledAttributes.getInt(3, 0);
        this.g = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        obtainStyledAttributes.recycle();
        this.h = new Rect();
        if (this.c != null) {
            this.c.setState(b);
            this.c.setCallback(this);
        }
    }

    public Drawable getDrawable() {
        return this.c;
    }

    public void setDrawable(Drawable drawable) {
        if (this.c != drawable) {
            unscheduleDrawable(this.c);
            this.c = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
            }
            requestLayout();
        }
    }

    public int getCount() {
        return this.d;
    }

    public void setCount(int i) {
        if (this.d != i) {
            this.d = i;
            requestLayout();
        }
    }

    public int getPos() {
        return this.e;
    }

    public void setPos(int i) {
        setPos(i, 0.0f);
    }

    public void setPos(int i, float f) {
        if (this.e != i || this.f != f) {
            this.e = i;
            this.f = f;
            invalidate();
        }
    }

    public int getSpace() {
        return this.g;
    }

    public void setSpace(int i) {
        if (this.g != i) {
            this.g = i;
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        int intrinsicWidth = this.c == null ? 0 : this.c.getIntrinsicWidth();
        int intrinsicHeight = this.c == null ? 0 : this.c.getIntrinsicHeight();
        if (this.d != 0) {
            i3 = getPaddingRight() + (((intrinsicWidth * this.d) + (this.g * (this.d - 1))) + getPaddingLeft());
        }
        setMeasuredDimension(resolveSize(i3, i), resolveSize((getPaddingTop() + intrinsicHeight) + getPaddingBottom(), i2));
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        if (this.c != null) {
            int intrinsicWidth = this.c.getIntrinsicWidth();
            this.h.set(0, 0, this.c.getIntrinsicWidth(), intrinsicWidth);
            this.c.setCallback(null);
            this.c.setState(b);
            while (i < this.d) {
                this.h.offsetTo(((this.g + intrinsicWidth) * i) + getPaddingLeft(), getPaddingTop());
                if (this.f == 0.0f && this.e == i) {
                    this.c.setState(a);
                } else {
                    this.c.setState(b);
                }
                this.c.setBounds(this.h);
                this.c.draw(canvas);
                i++;
            }
            if (this.f != 0.0f) {
                this.c.setState(a);
                this.h.offsetTo(((int) (((float) (intrinsicWidth + this.g)) * (((float) this.e) + this.f))) + getPaddingLeft(), getPaddingTop());
                this.c.setBounds(this.h);
                this.c.draw(canvas);
            }
            this.c.setCallback(this);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return this.c == drawable || super.verifyDrawable(drawable);
    }
}
