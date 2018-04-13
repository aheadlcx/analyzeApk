package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class ShopVipGridView extends GridView {
    public ShopVipGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ShopVipGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ShopVipGridView(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        if (getLayoutParams().height == -2) {
            i2 = MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
