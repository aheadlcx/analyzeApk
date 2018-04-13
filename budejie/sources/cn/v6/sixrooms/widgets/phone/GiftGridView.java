package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class GiftGridView extends GridView {
    public GiftGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public GiftGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GiftGridView(Context context) {
        super(context);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
