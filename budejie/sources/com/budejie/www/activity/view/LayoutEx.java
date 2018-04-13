package com.budejie.www.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;

public class LayoutEx extends ViewGroup {
    private int a = 10;
    private int b = 40;
    private int c = 480;
    private Context d;

    public LayoutEx(Context context) {
        super(context);
        this.d = context;
    }

    public LayoutEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
    }

    protected void onMeasure(int i, int i2) {
        this.c = getWidth();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            childAt.measure(0, 0);
            childAt.getWidth();
        }
        super.onMeasure(i, i2);
    }

    private void a(int i, int i2, int i3) {
        int i4 = 4;
        if (i3 * i2 < i) {
            double d = (double) ((i - (i3 * i2)) / (i3 + 1));
            if (d > ((double) this.a)) {
                this.a = (int) d;
                return;
            } else {
                this.a = (i - ((i3 - 1) * i2)) / i3;
                return;
            }
        }
        int i5 = i / i2;
        aa.b("MyViewGroup", "measureMargin  scount=" + i5);
        aa.b("MyViewGroup", "measureMargin  width=" + i);
        aa.b("MyViewGroup", "measureMargin  childWidth=" + i2);
        if (i5 <= 4) {
            i4 = i5;
        }
        double d2 = (double) ((i - (i4 * i2)) / (i4 + 1));
        if (d2 > ((double) this.a)) {
            this.a = (int) d2;
        } else {
            this.a = (i - (i4 * i2)) / i4;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = i;
        int i7 = 0;
        int i8 = i2;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i3 != 0 && i5 == 0 && this.a == 10) {
                a(i3, measuredWidth, childCount);
            }
            i6 += this.a + measuredWidth;
            i8 = (((this.b + measuredHeight) * i7) + measuredHeight) + i2;
            if (i6 > i3) {
                i6 = (this.a + measuredWidth) + i;
                i7++;
                i8 = (((this.b + measuredHeight) * i7) + measuredHeight) + i2;
            }
            childAt.layout(i6 - measuredWidth, i8 - measuredHeight, i6, i8);
            i5++;
        }
        aa.e("MyViewGroup", "宽度为： " + this.c);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i8 + an.a(this.d, 10));
        layoutParams.gravity = 1;
        setLayoutParams(layoutParams);
    }
}
