package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.R;

public class FlowLayout extends ViewGroup {
    public static final int SPACING_ALIGN = -65537;
    public static final int SPACING_AUTO = -65536;
    private static final String a = FlowLayout.class.getSimpleName();
    private boolean b;
    private int c;
    private int d;
    private float e;
    private float f;
    private boolean g;
    private int h;
    private List<Float> i;
    private List<Integer> j;
    private List<Integer> k;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = true;
        this.c = 0;
        this.d = -65538;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = false;
        this.h = Integer.MAX_VALUE;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FlowLayout, 0, 0);
        try {
            this.b = obtainStyledAttributes.getBoolean(0, true);
            this.c = obtainStyledAttributes.getInt(1, 0);
        } catch (NumberFormatException e) {
            this.c = obtainStyledAttributes.getDimensionPixelSize(1, (int) a(0.0f));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
        try {
            this.d = obtainStyledAttributes.getInt(2, -65538);
        } catch (NumberFormatException e2) {
            this.d = obtainStyledAttributes.getDimensionPixelSize(2, (int) a(0.0f));
        }
        try {
            this.e = (float) obtainStyledAttributes.getInt(3, 0);
        } catch (NumberFormatException e3) {
            this.e = obtainStyledAttributes.getDimension(3, a(0.0f));
        }
        this.h = obtainStyledAttributes.getInt(5, Integer.MAX_VALUE);
        this.g = obtainStyledAttributes.getBoolean(4, false);
        obtainStyledAttributes.recycle();
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        this.i.clear();
        this.k.clear();
        this.j.clear();
        int i6 = 0;
        int i7 = 0;
        int childCount = getChildCount();
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        Object obj = (mode == 0 || !this.b) ? null : 1;
        int i11 = (this.c == -65536 && mode == 0) ? 0 : this.c;
        float f = i11 == -65536 ? 0.0f : (float) i11;
        int i12 = 0;
        while (i12 < childCount) {
            int i13;
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() == 8) {
                i3 = i10;
                i4 = i9;
                i13 = i8;
                i5 = i7;
            } else {
                LayoutParams layoutParams = childAt.getLayoutParams();
                i13 = 0;
                i3 = 0;
                if (layoutParams instanceof MarginLayoutParams) {
                    measureChildWithMargins(childAt, i, 0, i2, i6);
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                    i13 = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                    i3 = marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                } else {
                    measureChild(childAt, i, i2);
                }
                i13 += childAt.getMeasuredWidth();
                i4 = childAt.getMeasuredHeight() + i3;
                if (obj == null || i8 + i13 <= paddingLeft) {
                    i3 = i10 + 1;
                    i13 = (int) ((((float) i13) + f) + ((float) i8));
                    i4 = Math.max(i9, i4);
                    i5 = i7;
                } else {
                    this.i.add(Float.valueOf(a(i11, paddingLeft, i8, i10)));
                    this.k.add(Integer.valueOf(i10));
                    this.j.add(Integer.valueOf(i9));
                    if (this.i.size() <= this.h) {
                        i6 += i9;
                    }
                    i5 = Math.max(i7, i8);
                    i3 = 1;
                    i13 += (int) f;
                }
            }
            i12++;
            i10 = i3;
            i9 = i4;
            i8 = i13;
            i7 = i5;
        }
        if (this.d == SPACING_ALIGN) {
            if (this.i.size() >= 1) {
                this.i.add(this.i.get(this.i.size() - 1));
            } else {
                this.i.add(Float.valueOf(a(i11, paddingLeft, i8, i10)));
            }
        } else if (this.d != -65538) {
            this.i.add(Float.valueOf(a(this.d, paddingLeft, i8, i10)));
        } else {
            this.i.add(Float.valueOf(a(i11, paddingLeft, i8, i10)));
        }
        this.k.add(Integer.valueOf(i10));
        this.j.add(Integer.valueOf(i9));
        if (this.i.size() <= this.h) {
            i6 += i9;
        }
        i3 = Math.max(i7, i8);
        if (i11 == -65536) {
            i3 = size;
        } else if (mode == 0) {
            i3 = (i3 + getPaddingLeft()) + getPaddingRight();
        } else {
            i3 = Math.min((i3 + getPaddingLeft()) + getPaddingRight(), size);
        }
        i4 = (getPaddingTop() + getPaddingBottom()) + i6;
        i5 = Math.min(this.i.size(), this.h);
        float f2 = (this.e == -65536.0f && mode2 == 0) ? 0.0f : this.e;
        if (f2 == -65536.0f) {
            if (i5 > 1) {
                this.f = (float) ((size2 - i4) / (i5 - 1));
            } else {
                this.f = 0.0f;
            }
            i4 = size2;
        } else {
            this.f = f2;
            if (i5 > 1) {
                if (mode2 == 0) {
                    i4 = (int) (((float) i4) + (this.f * ((float) (i5 - 1))));
                } else {
                    i4 = Math.min((int) (((float) i4) + (this.f * ((float) (i5 - 1)))), size2);
                }
            }
        }
        if (mode != 1073741824) {
            size = i3;
        }
        if (mode2 == 1073741824) {
            i3 = size2;
        } else {
            i3 = i4;
        }
        setMeasuredDimension(size, i3);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int width = this.g ? getWidth() - paddingRight : paddingLeft;
        int size = this.k.size();
        int i5 = 0;
        int i6 = paddingTop;
        int i7 = 0;
        int i8 = width;
        while (i5 < size && i5 < this.h) {
            int intValue = ((Integer) this.k.get(i5)).intValue();
            int intValue2 = ((Integer) this.j.get(i5)).intValue();
            float floatValue = ((Float) this.i.get(i5)).floatValue();
            width = 0;
            int i9 = i8;
            while (width < intValue && i7 < getChildCount()) {
                int i10 = i7 + 1;
                View childAt = getChildAt(i7);
                if (childAt.getVisibility() == 8) {
                    i7 = i10;
                } else {
                    int i11 = width + 1;
                    LayoutParams layoutParams = childAt.getLayoutParams();
                    if (layoutParams instanceof MarginLayoutParams) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                        i8 = marginLayoutParams.leftMargin;
                        int i12 = marginLayoutParams.rightMargin;
                        i7 = marginLayoutParams.topMargin;
                        width = i12;
                    } else {
                        width = 0;
                        i7 = 0;
                        i8 = 0;
                    }
                    paddingTop = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (this.g) {
                        childAt.layout((i9 - width) - paddingTop, i6 + i7, i9 - width, (i7 + i6) + measuredHeight);
                        width = (int) (((float) i9) - (((float) width) + (((float) i8) + (((float) paddingTop) + floatValue))));
                    } else {
                        childAt.layout(i9 + i8, i6 + i7, (i9 + i8) + paddingTop, (i7 + i6) + measuredHeight);
                        width = (int) ((((float) width) + (((float) i8) + (((float) paddingTop) + floatValue))) + ((float) i9));
                    }
                    i7 = i10;
                    i9 = width;
                    width = i11;
                }
            }
            i5++;
            i6 = (int) (((float) i6) + (((float) intValue2) + this.f));
            i8 = this.g ? getWidth() - paddingRight : paddingLeft;
        }
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new MarginLayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    public boolean isFlow() {
        return this.b;
    }

    public void setFlow(boolean z) {
        this.b = z;
        requestLayout();
    }

    public int getChildSpacing() {
        return this.c;
    }

    public void setChildSpacing(int i) {
        this.c = i;
        requestLayout();
    }

    public int getChildSpacingForLastRow() {
        return this.d;
    }

    public void setChildSpacingForLastRow(int i) {
        this.d = i;
        requestLayout();
    }

    public float getRowSpacing() {
        return this.e;
    }

    public void setRowSpacing(float f) {
        this.e = f;
        requestLayout();
    }

    public int getMaxRows() {
        return this.h;
    }

    public void setMaxRows(int i) {
        this.h = i;
        requestLayout();
    }

    private float a(int i, int i2, int i3, int i4) {
        if (i != -65536) {
            return (float) i;
        }
        if (i4 > 1) {
            return (float) ((i2 - i3) / (i4 - 1));
        }
        return 0.0f;
    }

    private float a(float f) {
        return TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }
}
