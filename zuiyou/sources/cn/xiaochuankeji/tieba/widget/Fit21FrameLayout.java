package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class Fit21FrameLayout extends FrameLayout {
    public Fit21FrameLayout(Context context) {
        this(context, null);
    }

    public Fit21FrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Fit21FrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        setMeasuredDimension(size, (int) ((((double) size) * 0.5d) + 0.5d));
    }
}
