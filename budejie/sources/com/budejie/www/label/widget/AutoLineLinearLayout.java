package com.budejie.www.label.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.budejie.www.R$styleable;
import com.budejie.www.util.an;

public class AutoLineLinearLayout extends ViewGroup {
    private int a;
    private int b;

    public AutoLineLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = an.a(context, 10);
        this.b = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public AutoLineLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = an.a(context, 10);
        this.b = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public AutoLineLinearLayout(Context context) {
        super(context);
        this.a = an.a(context, 10);
        this.b = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        Log.d("AutoLineLinearLayout", "widthMeasureSpec = " + i + " heightMeasureSpec" + i2);
        int i4 = this.a;
        int i5 = 0;
        for (i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            childAt.measure(0, 0);
            Log.d("AutoLineLinearLayout", "child.getWidth() = " + childAt.getWidth() + " child.getHeight() = " + childAt.getHeight());
            if (childAt.getWidth() == 0) {
                i4 += this.a + R$styleable.Theme_Custom_shape_cmt_like4_bg;
            } else {
                i4 += childAt.getWidth() + this.a;
            }
            if (i5 <= childAt.getHeight()) {
                i5 = childAt.getHeight();
            }
        }
        i3 = i4 / this.b;
        Log.d("AutoLineLinearLayout", "width = " + i4 + " screenWidth=" + this.b);
        Log.d("AutoLineLinearLayout", "childHeight = " + i5 + " VIEW_MARGIN=" + this.a);
        setMeasuredDimension(i, ((i3 + 1) * (this.a + i5)) + this.a);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Log.d("AutoLineLinearLayout", "changed = " + z + " left = " + i + " top = " + i2 + " right = " + i3 + " bottom = " + i4);
        int childCount = getChildCount();
        int i5 = i;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            i5 += this.a + measuredWidth;
            int i8 = ((i2 + measuredHeight) + this.a) + ((this.a + measuredHeight) * i6);
            if (i5 > i3) {
                i6++;
                i5 = this.a + (i + measuredWidth);
                i8 = ((i2 + measuredHeight) + this.a) + ((this.a + measuredHeight) * i6);
            }
            childAt.layout(i5 - measuredWidth, i8 - measuredHeight, i5, i8);
        }
    }
}
