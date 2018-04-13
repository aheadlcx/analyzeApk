package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import c.a.i.g;

public class Fit43FrameLayout extends g {
    public Fit43FrameLayout(Context context) {
        this(context, null);
    }

    public Fit43FrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Fit43FrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (((float) size) * 0.75f));
    }
}
