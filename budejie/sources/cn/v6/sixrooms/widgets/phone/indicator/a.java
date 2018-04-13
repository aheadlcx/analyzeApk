package cn.v6.sixrooms.widgets.phone.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

final class a extends LinearLayout {
    private static final int[] a = new int[]{16843049, 16843561, 16843562};
    private Drawable b;
    private int c;
    private int d;
    private int e;
    private int f;

    public a(Context context, int i) {
        super(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, a, i, 0);
        setDividerDrawable(obtainStyledAttributes.getDrawable(0));
        this.f = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.e = obtainStyledAttributes.getInteger(1, 0);
        obtainStyledAttributes.recycle();
    }

    public final void setDividerDrawable(Drawable drawable) {
        boolean z = false;
        if (drawable != this.b) {
            this.b = drawable;
            if (drawable != null) {
                this.c = drawable.getIntrinsicWidth();
                this.d = drawable.getIntrinsicHeight();
            } else {
                this.c = 0;
                this.d = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    protected final void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (a(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.d;
            } else {
                layoutParams.leftMargin = this.c;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && a(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.d;
            } else {
                layoutParams.rightMargin = this.c;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    protected final void onDraw(Canvas canvas) {
        if (this.b != null) {
            int childCount;
            int i;
            View childAt;
            View childAt2;
            int height;
            if (getOrientation() == 1) {
                childCount = getChildCount();
                i = 0;
                while (i < childCount) {
                    childAt = getChildAt(i);
                    if (!(childAt == null || childAt.getVisibility() == 8 || !a(i))) {
                        a(canvas, childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin);
                    }
                    i++;
                }
                if (a(childCount)) {
                    childAt2 = getChildAt(childCount - 1);
                    if (childAt2 == null) {
                        height = (getHeight() - getPaddingBottom()) - this.d;
                    } else {
                        height = childAt2.getBottom();
                    }
                    a(canvas, height);
                }
            } else {
                childCount = getChildCount();
                i = 0;
                while (i < childCount) {
                    childAt = getChildAt(i);
                    if (!(childAt == null || childAt.getVisibility() == 8 || !a(i))) {
                        b(canvas, childAt.getLeft() - ((LayoutParams) childAt.getLayoutParams()).leftMargin);
                    }
                    i++;
                }
                if (a(childCount)) {
                    childAt2 = getChildAt(childCount - 1);
                    if (childAt2 == null) {
                        height = (getWidth() - getPaddingRight()) - this.c;
                    } else {
                        height = childAt2.getRight();
                    }
                    b(canvas, height);
                }
            }
        }
        super.onDraw(canvas);
    }

    private void a(Canvas canvas, int i) {
        this.b.setBounds(getPaddingLeft() + this.f, i, (getWidth() - getPaddingRight()) - this.f, this.d + i);
        this.b.draw(canvas);
    }

    private void b(Canvas canvas, int i) {
        this.b.setBounds(i, getPaddingTop() + this.f, this.c + i, (getHeight() - getPaddingBottom()) - this.f);
        this.b.draw(canvas);
    }

    private boolean a(int i) {
        if (i == 0 || i == getChildCount() || (this.e & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }
}
