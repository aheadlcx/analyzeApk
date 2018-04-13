package qsbk.app.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ListAdapter;

public class LinearReactiveLayout extends ViewGroup {
    private final DataSetObserver a = new cg(this);
    private ListAdapter b;

    public LinearReactiveLayout(Context context) {
        super(context);
        init();
    }

    public LinearReactiveLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LinearReactiveLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        removeAllViews();
        if (isInEditMode()) {
            this.b = new ch(this);
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        int size = MeasureSpec.getSize(i);
        if (size == 0) {
            size = 1048576;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int i4 = size - paddingLeft;
        int i5 = 0;
        int i6 = 0;
        while (i3 < getCount()) {
            View a = a(i3);
            if (a.getVisibility() != 8) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) a.getLayoutParams();
                if (marginLayoutParams == null) {
                    marginLayoutParams = (MarginLayoutParams) generateDefaultLayoutParams();
                    a.setLayoutParams(marginLayoutParams);
                }
                int i7 = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                int i8 = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                a.measure(getChildMeasureSpec(i, paddingLeft + i7, marginLayoutParams.width), getChildMeasureSpec(i2, paddingTop + i8, marginLayoutParams.height));
                size = (a.getMeasuredWidth() + i6) + i7;
                if (size > i4) {
                    b(i3);
                    break;
                } else {
                    i5 = Math.max(i5, a.getMeasuredHeight() + i8);
                    i6 = size;
                }
            }
            i3++;
        }
        setMeasuredDimension(resolveSize(i6 + paddingLeft, i), resolveSize(i5 + paddingTop, i2));
    }

    private View a(int i) {
        View view = null;
        if (i < getChildCount()) {
            view = getChildAt(i);
        }
        View view2 = this.b.getView(i, view, this);
        if (view2 != view) {
            if (view != null) {
                detachViewFromParent(view);
            }
            LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
            }
            addViewInLayout(view2, -1, layoutParams, true);
        }
        return view2;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.b != listAdapter) {
            b(0);
            if (this.b != null) {
                this.b.unregisterDataSetObserver(this.a);
            }
            if (listAdapter != null) {
                listAdapter.registerDataSetObserver(this.a);
            }
            this.b = listAdapter;
            requestLayout();
        }
    }

    private int getCount() {
        return this.b == null ? 0 : this.b.getCount();
    }

    private void b(int i) {
        int childCount = i - getChildCount();
        if (childCount > 0) {
            detachViewsFromParent(i, childCount);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int i5 = 0;
        while (i5 < childCount) {
            int i6;
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() == 8) {
                i6 = paddingLeft;
            } else {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                int i7 = marginLayoutParams.topMargin + paddingTop;
                paddingLeft += marginLayoutParams.leftMargin;
                int measuredWidth = childAt.getMeasuredWidth() + paddingLeft;
                childAt.layout(paddingLeft, i7, measuredWidth, childAt.getMeasuredHeight() + i7);
                i6 = marginLayoutParams.rightMargin + measuredWidth;
            }
            i5++;
            paddingLeft = i6;
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return checkLayoutParams(layoutParams) ? layoutParams : new MarginLayoutParams(layoutParams);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-2, -2);
    }
}
