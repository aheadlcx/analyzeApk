package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class TopicHeader extends ViewGroup {
    private int a = 0;

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return a();
    }

    protected /* synthetic */ LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public TopicHeader(Context context) {
        super(context);
        b();
    }

    public TopicHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public TopicHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        measureChildren(i, i2);
        int paddingBottom = getPaddingBottom() + (getPaddingTop() + this.a);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            MarginLayoutParams a = a(childAt);
            paddingBottom = ((paddingBottom + a.topMargin) + a.bottomMargin) + childAt.getMeasuredHeight();
        }
        setMeasuredDimension(size, resolveSize(paddingBottom, i2));
    }

    public void setStatusBarHeight(int i) {
        this.a = i;
        requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int paddingTop = this.a + getPaddingTop();
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        int i6 = i3 - i;
        for (i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            MarginLayoutParams a = a(childAt);
            paddingTop += a.topMargin;
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth = childAt.getMeasuredWidth();
            int i7 = (i6 - measuredWidth) / 2;
            childAt.layout(i7, paddingTop, measuredWidth + i7, paddingTop + measuredHeight);
            paddingTop = (paddingTop + measuredHeight) + a.bottomMargin;
        }
        if (paddingTop != paddingBottom) {
            paddingTop = (paddingBottom - paddingTop) / 2;
            for (i5 = 0; i5 < getChildCount(); i5++) {
                getChildAt(i5).offsetTopAndBottom(paddingTop);
            }
        }
    }

    private MarginLayoutParams a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            return a();
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return (MarginLayoutParams) layoutParams;
        }
        return a(layoutParams);
    }

    public MarginLayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected MarginLayoutParams a(LayoutParams layoutParams) {
        return new MarginLayoutParams(layoutParams);
    }

    protected MarginLayoutParams a() {
        return new MarginLayoutParams(-2, -2);
    }
}
