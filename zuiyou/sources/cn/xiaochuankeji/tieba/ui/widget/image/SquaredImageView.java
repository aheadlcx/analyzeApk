package cn.xiaochuankeji.tieba.ui.widget.image;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class SquaredImageView extends WebImageView {
    public SquaredImageView(Context context) {
        super(context);
    }

    public SquaredImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquaredImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, size);
    }
}
