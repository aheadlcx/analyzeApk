package cn.tatagou.sdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class TagLayout extends LinearLayout {
    private List<int[]> a = new ArrayList();

    public TagLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i7 < childCount) {
            int i8;
            View childAt = getChildAt(i7);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (i7 == 0) {
                i5 = (layoutParams.topMargin + childAt.getMeasuredHeight()) + layoutParams.bottomMargin;
            }
            if (((layoutParams.leftMargin + i3) + childAt.getMeasuredWidth()) + layoutParams.rightMargin > getMeasuredWidth()) {
                i3 = 0;
                i4 += (layoutParams.topMargin + childAt.getMeasuredHeight()) + layoutParams.bottomMargin;
                i5 += (layoutParams.topMargin + childAt.getMeasuredHeight()) + layoutParams.bottomMargin;
            }
            this.a.add(new int[]{layoutParams.leftMargin + i3, layoutParams.topMargin + i4, (layoutParams.leftMargin + i3) + childAt.getMeasuredWidth(), (layoutParams.topMargin + i4) + childAt.getMeasuredHeight()});
            i3 += layoutParams.rightMargin + (childAt.getMeasuredWidth() + layoutParams.leftMargin);
            if (i3 > i6) {
                i8 = i3;
            } else {
                i8 = i6;
            }
            i7++;
            i6 = i8;
        }
        if (MeasureSpec.getMode(i2) == 1073741824) {
            i5 = MeasureSpec.getSize(i2);
        }
        if (MeasureSpec.getMode(i) == 1073741824) {
            i6 = MeasureSpec.getSize(i);
        }
        setMeasuredDimension(i6, i5);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            int[] iArr = (int[]) this.a.get(i5);
            getChildAt(i5).layout(iArr[0], iArr[1], iArr[2], iArr[3]);
        }
    }
}
