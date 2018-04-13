package cn.xiaochuankeji.tieba.widget.rich;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import c.a.i.aa;

public class LineSpaceExtraContainer extends aa {
    public LineSpaceExtraContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        if (getChildCount() < 1) {
            throw new IllegalStateException("must has one child view");
        }
        View childAt = getChildAt(0);
        if (childAt instanceof b) {
            childAt.measure(i, i2);
            setMeasuredDimension(childAt.getMeasuredWidth(), childAt.getMeasuredHeight() - ((b) childAt).getSpaceExtra());
            return;
        }
        throw new IllegalStateException("child view mast is child of DividerLineTextView");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() < 1) {
            throw new IllegalStateException("must has one child view");
        }
        getChildAt(0).layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }
}
