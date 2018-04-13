package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;

public class AspectRatioFrameLayout extends FrameLayout {
    private float a;
    private int b = 1;

    public AspectRatioFrameLayout(Context context) {
        super(context);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context.obtainStyledAttributes(attributeSet, R.styleable.aspectRatioFrameLayout).getInt(0, 1);
    }

    public void setAspectRatio(float f) {
        if (this.a != f) {
            this.a = f;
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a != 0.0f) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f = (this.a / (((float) measuredWidth) / ((float) measuredHeight))) - 1.0f;
            if (Math.abs(f) > 0.01f) {
                if (2 == this.b) {
                    if (f > 0.0f) {
                        measuredWidth = (int) (((float) measuredHeight) * this.a);
                    } else {
                        measuredHeight = (int) (((float) measuredWidth) / this.a);
                    }
                } else if (f > 0.0f) {
                    measuredHeight = (int) (((float) measuredWidth) / this.a);
                } else {
                    measuredWidth = (int) (((float) measuredHeight) * this.a);
                }
                super.onMeasure(MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
        }
    }
}
