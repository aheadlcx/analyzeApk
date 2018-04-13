package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.TextView.BufferType;
import c.a.i.w;

public class AutoFitTextView extends w {
    public AutoFitTextView(Context context) {
        super(context);
    }

    public AutoFitTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AutoFitTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        super.setText(charSequence, bufferType);
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), i2);
        if (size < getMeasuredWidth()) {
            setMeasuredDimension(size, getMeasuredHeight());
        }
    }
}
