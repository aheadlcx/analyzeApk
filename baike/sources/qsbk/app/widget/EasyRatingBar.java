package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import qsbk.app.R;

public class EasyRatingBar extends View {
    private int a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private Drawable i;
    private Drawable j;
    private Rect k;
    private OnRateListener l;
    private boolean m = true;

    public interface OnRateListener {
        void onRate(EasyRatingBar easyRatingBar, int i);
    }

    public EasyRatingBar(Context context) {
        super(context);
        a(null, 0);
    }

    public EasyRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public EasyRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        boolean z = true;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.EasyRatingBar, 0, i);
        this.i = obtainStyledAttributes.getDrawable(0);
        this.j = obtainStyledAttributes.getDrawable(1);
        this.h = obtainStyledAttributes.getInt(2, 5);
        this.f = obtainStyledAttributes.getInt(3, 0);
        this.a = obtainStyledAttributes.getDimensionPixelOffset(4, 0);
        this.d = !obtainStyledAttributes.hasValue(5);
        if (obtainStyledAttributes.hasValue(6)) {
            z = false;
        }
        this.e = z;
        if (this.d) {
            a();
        } else {
            this.b = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        }
        if (this.e) {
            b();
        } else {
            this.c = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        }
        obtainStyledAttributes.recycle();
        this.k = new Rect();
    }

    private void a() {
        int i = 0;
        int intrinsicWidth = this.i == null ? 0 : this.i.getIntrinsicWidth();
        if (this.j != null) {
            i = this.j.getIntrinsicWidth();
        }
        this.b = Math.max(intrinsicWidth, i);
    }

    private void b() {
        int i = 0;
        int intrinsicHeight = this.i == null ? 0 : this.i.getIntrinsicHeight();
        if (this.j != null) {
            i = this.j.getIntrinsicHeight();
        }
        this.c = Math.max(intrinsicHeight, i);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize((getPaddingLeft() + getPaddingRight()) + Math.max(0, (this.b * this.h) + (this.a * (this.h - 1))), i), resolveSize((getPaddingTop() + getPaddingBottom()) + this.c, i2));
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        if (this.h > 0) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            int save = canvas.save();
            canvas.clipRect(paddingLeft, paddingTop, width, height);
            this.k.set(0, 0, this.b, this.c);
            if (this.j != null) {
                for (width = 0; width < this.h; width++) {
                    this.k.offsetTo(((this.k.width() + this.a) * width) + paddingLeft, paddingTop);
                    this.j.setBounds(this.k);
                    this.j.draw(canvas);
                }
            }
            if (this.i != null) {
                while (i < this.f) {
                    this.k.offsetTo(((this.k.width() + this.a) * i) + paddingLeft, paddingTop);
                    this.i.setBounds(this.k);
                    this.i.draw(canvas);
                    i++;
                }
            }
            canvas.restoreToCount(save);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || this.h <= 0 || !this.m) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.g = this.f;
        }
        this.f = ((int) (((motionEvent.getX() - ((float) getPaddingLeft())) + ((float) (this.a / 2))) / ((float) (this.b + this.a)))) + 1;
        if (action == 3) {
            this.f = this.g;
        } else if (!(action != 1 || this.l == null || this.f == this.g)) {
            this.l.onRate(this, this.f);
        }
        postInvalidate();
        return true;
    }

    public void setEditable(boolean z) {
        this.m = z;
    }

    public int getSpace() {
        return this.a;
    }

    public void setSpace(int i) {
        this.a = i;
        requestLayout();
    }

    public int getItemWidth() {
        return this.b;
    }

    public void setItemWidth(int i) {
        if (i < 0) {
            this.e = true;
            b();
        } else {
            this.e = false;
            this.b = i;
        }
        requestLayout();
    }

    public int getItemHeight() {
        return this.c;
    }

    public void setItemHeight(int i) {
        if (i < 0) {
            this.e = true;
            b();
        } else {
            this.e = false;
            this.c = i;
        }
        requestLayout();
    }

    public int getRate() {
        return this.f;
    }

    public void setRate(int i) {
        this.f = i;
        postInvalidate();
    }

    public void setOnRateListener(OnRateListener onRateListener) {
        this.l = onRateListener;
    }

    public int getCount() {
        return this.h;
    }

    public void setCount(int i) {
        if (this.h != i) {
            this.h = i;
            requestLayout();
        }
    }

    public Drawable getSelectDrawable() {
        return this.i;
    }

    public void setSelectDrawable(Drawable drawable) {
        this.i = drawable;
        if (this.d) {
            a();
        }
        if (this.e) {
            b();
        }
        requestLayout();
    }

    public Drawable getUnSelectDrawable() {
        return this.j;
    }

    public void setUnSelectDrawable(Drawable drawable) {
        this.j = drawable;
        if (this.d) {
            a();
        }
        if (this.e) {
            b();
        }
        requestLayout();
    }
}
