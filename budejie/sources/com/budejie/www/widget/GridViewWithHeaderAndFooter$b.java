package com.budejie.www.widget;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

class GridViewWithHeaderAndFooter$b extends FrameLayout {
    final /* synthetic */ GridViewWithHeaderAndFooter a;

    public GridViewWithHeaderAndFooter$b(GridViewWithHeaderAndFooter gridViewWithHeaderAndFooter, Context context) {
        this.a = gridViewWithHeaderAndFooter;
        super(context);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = this.a.getPaddingLeft() + getPaddingLeft();
        if (paddingLeft != i) {
            offsetLeftAndRight(paddingLeft - i);
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec((this.a.getMeasuredWidth() - this.a.getPaddingLeft()) - this.a.getPaddingRight(), MeasureSpec.getMode(i)), i2);
    }
}
