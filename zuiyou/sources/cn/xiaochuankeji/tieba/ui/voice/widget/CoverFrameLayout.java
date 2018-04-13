package cn.xiaochuankeji.tieba.ui.voice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import c.a.i.g;
import cn.xiaochuankeji.tieba.R;

public class CoverFrameLayout extends g {
    private float a;
    private float b;
    private float c;

    public CoverFrameLayout(Context context) {
        super(context);
        a(context, null);
    }

    public CoverFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public CoverFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CoverFrameLayout);
        this.a = obtainStyledAttributes.getFloat(0, 0.0f);
        this.c = obtainStyledAttributes.getFloat(2, 0.0f);
        this.b = obtainStyledAttributes.getFloat(1, 0.0f);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        if (this.a != 0.0f) {
            setMeasuredDimension(measuredWidth, (int) (((float) measuredWidth) * this.a));
        } else if (this.c != 0.0f && this.b != 0.0f) {
            setMeasuredDimension(measuredWidth, (int) (((float) measuredWidth) * (this.c / this.b)));
        }
    }
}
