package com.sprite.ads.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class AutoGridView extends GridView {
    public AutoGridView(Context context) {
        super(context);
    }

    public AutoGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AutoGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
