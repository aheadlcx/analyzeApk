package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

final class bh extends OrientationHelper {
    bh(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public int getEndAfterPadding() {
        return this.a.getHeight() - this.a.getPaddingBottom();
    }

    public int getEnd() {
        return this.a.getHeight();
    }

    public void offsetChildren(int i) {
        this.a.offsetChildrenVertical(i);
    }

    public int getStartAfterPadding() {
        return this.a.getPaddingTop();
    }

    public int getDecoratedMeasurement(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + (this.a.getDecoratedMeasuredHeight(view) + layoutParams.topMargin);
    }

    public int getDecoratedMeasurementInOther(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + (this.a.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin);
    }

    public int getDecoratedEnd(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + this.a.getDecoratedBottom(view);
    }

    public int getDecoratedStart(View view) {
        return this.a.getDecoratedTop(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
    }

    public int getTransformedEndWithDecoration(View view) {
        this.a.getTransformedBoundingBox(view, true, this.b);
        return this.b.bottom;
    }

    public int getTransformedStartWithDecoration(View view) {
        this.a.getTransformedBoundingBox(view, true, this.b);
        return this.b.top;
    }

    public int getTotalSpace() {
        return (this.a.getHeight() - this.a.getPaddingTop()) - this.a.getPaddingBottom();
    }

    public void offsetChild(View view, int i) {
        view.offsetTopAndBottom(i);
    }

    public int getEndPadding() {
        return this.a.getPaddingBottom();
    }

    public int getMode() {
        return this.a.getHeightMode();
    }

    public int getModeInOther() {
        return this.a.getWidthMode();
    }
}
