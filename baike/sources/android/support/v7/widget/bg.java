package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

final class bg extends OrientationHelper {
    bg(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public int getEndAfterPadding() {
        return this.a.getWidth() - this.a.getPaddingRight();
    }

    public int getEnd() {
        return this.a.getWidth();
    }

    public void offsetChildren(int i) {
        this.a.offsetChildrenHorizontal(i);
    }

    public int getStartAfterPadding() {
        return this.a.getPaddingLeft();
    }

    public int getDecoratedMeasurement(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + (this.a.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin);
    }

    public int getDecoratedMeasurementInOther(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + (this.a.getDecoratedMeasuredHeight(view) + layoutParams.topMargin);
    }

    public int getDecoratedEnd(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + this.a.getDecoratedRight(view);
    }

    public int getDecoratedStart(View view) {
        return this.a.getDecoratedLeft(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
    }

    public int getTransformedEndWithDecoration(View view) {
        this.a.getTransformedBoundingBox(view, true, this.b);
        return this.b.right;
    }

    public int getTransformedStartWithDecoration(View view) {
        this.a.getTransformedBoundingBox(view, true, this.b);
        return this.b.left;
    }

    public int getTotalSpace() {
        return (this.a.getWidth() - this.a.getPaddingLeft()) - this.a.getPaddingRight();
    }

    public void offsetChild(View view, int i) {
        view.offsetLeftAndRight(i);
    }

    public int getEndPadding() {
        return this.a.getPaddingRight();
    }

    public int getMode() {
        return this.a.getWidthMode();
    }

    public int getModeInOther() {
        return this.a.getHeightMode();
    }
}
