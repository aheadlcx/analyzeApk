package cn.xiaochuankeji.tieba.ui.widget.image;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView.ScaleType;

public class FitScaleImageView extends WebImageView {
    public FitScaleImageView(Context context) {
        this(context, null);
    }

    public FitScaleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FitScaleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setScaleType(ScaleType.MATRIX);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        setMeasuredDimension(size, (int) (((((float) size) * 525.0f) / 1158.0f) + 0.5f));
    }
}
