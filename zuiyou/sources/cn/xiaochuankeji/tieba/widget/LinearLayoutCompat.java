package cn.xiaochuankeji.tieba.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import c.a.i.j;

@SuppressLint({"NewApi"})
public class LinearLayoutCompat extends j {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private b g;
    private Drawable h;

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new b(this);
        this.g.a(attributeSet, i);
    }

    public void setDividerDrawable(Drawable drawable) {
    }

    public void setDividerPadding(int i) {
    }

    public Drawable getDividerDrawable() {
        return null;
    }

    protected void onDraw(Canvas canvas) {
        if (this.h != null) {
            if (getOrientation() == 1) {
                a(canvas);
            } else {
                b(canvas);
            }
        }
    }

    void a(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !a(i))) {
                a(canvas, (childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin) - this.b);
            }
            i++;
        }
        if (a(childCount)) {
            int height;
            View lastNonGoneChild = getLastNonGoneChild();
            if (lastNonGoneChild == null) {
                height = (getHeight() - getPaddingBottom()) - this.b;
            } else {
                LayoutParams layoutParams = (LayoutParams) lastNonGoneChild.getLayoutParams();
                height = layoutParams.bottomMargin + lastNonGoneChild.getBottom();
            }
            a(canvas, height);
        }
    }

    void b(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !a(i))) {
                b(canvas, (childAt.getLeft() - ((LayoutParams) childAt.getLayoutParams()).leftMargin) - this.a);
            }
            i++;
        }
        if (a(childCount)) {
            int width;
            View lastNonGoneChild = getLastNonGoneChild();
            if (lastNonGoneChild == null) {
                width = (getWidth() - getPaddingRight()) - this.a;
            } else {
                LayoutParams layoutParams = (LayoutParams) lastNonGoneChild.getLayoutParams();
                width = layoutParams.rightMargin + lastNonGoneChild.getRight();
            }
            b(canvas, width);
        }
    }

    boolean a(int i) {
        if (i == getChildCount()) {
            if ((getShowDividers() & 4) != 0) {
                return true;
            }
            return false;
        } else if (b(i)) {
            if ((getShowDividers() & 1) == 0) {
                return false;
            }
            return true;
        } else if ((getShowDividers() & 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    boolean b(int i) {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            View childAt = getChildAt(i2);
            if (childAt != null && childAt.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    View getLastNonGoneChild() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt != null && childAt.getVisibility() != 8) {
                return childAt;
            }
        }
        return null;
    }

    void a(Canvas canvas, int i) {
        this.h.setBounds(getPaddingLeft() + this.c, i, (getWidth() - getPaddingRight()) - this.d, this.b + i);
        this.h.draw(canvas);
    }

    void b(Canvas canvas, int i) {
        this.h.setBounds(i, getPaddingTop() + this.e, this.a + i, (getHeight() - getPaddingBottom()) - this.f);
        this.h.draw(canvas);
    }

    public void a(Drawable drawable, int i, int i2, int i3, int i4) {
        if (drawable != null) {
            this.a = drawable.getIntrinsicWidth();
            this.b = drawable.getIntrinsicHeight();
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.h = drawable;
            setWillNotDraw(false);
            requestLayout();
        }
    }

    public void d() {
        super.d();
        if (this.g != null) {
            this.g.a();
        }
    }
}
