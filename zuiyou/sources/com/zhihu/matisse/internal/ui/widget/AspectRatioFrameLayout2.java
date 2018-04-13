package com.zhihu.matisse.internal.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class AspectRatioFrameLayout2 extends FrameLayout {
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;
    private int mType = 1;
    private float videoAspectRatio;

    public AspectRatioFrameLayout2(Context context) {
        super(context);
    }

    public AspectRatioFrameLayout2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAspectRatio(float f) {
        if (this.videoAspectRatio != f) {
            this.videoAspectRatio = f;
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.videoAspectRatio != 0.0f) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f = (this.videoAspectRatio / (((float) measuredWidth) / ((float) measuredHeight))) - 1.0f;
            if (Math.abs(f) > MAX_ASPECT_RATIO_DEFORMATION_FRACTION) {
                if (this.mType == 2) {
                    if (f > 0.0f) {
                        measuredWidth = (int) (((float) measuredHeight) * this.videoAspectRatio);
                    } else {
                        measuredHeight = (int) (((float) measuredWidth) / this.videoAspectRatio);
                    }
                } else if (f > 0.0f) {
                    measuredHeight = (int) (((float) measuredWidth) / this.videoAspectRatio);
                } else {
                    measuredWidth = (int) (((float) measuredHeight) * this.videoAspectRatio);
                }
                super.onMeasure(MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
        }
    }
}
