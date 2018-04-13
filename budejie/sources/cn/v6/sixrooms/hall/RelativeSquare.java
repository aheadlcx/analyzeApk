package cn.v6.sixrooms.hall;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public class RelativeSquare extends RelativeLayout {
    public RelativeSquare(Context context) {
        super(context);
    }

    public RelativeSquare(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RelativeSquare(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }
}
