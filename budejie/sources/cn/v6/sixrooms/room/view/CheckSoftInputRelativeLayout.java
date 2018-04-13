package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CheckSoftInputRelativeLayout extends RelativeLayout {
    private OnSizeChangedListener a;

    public interface OnSizeChangedListener {
        void onSizeChanged(int i, int i2, int i3, int i4);
    }

    public CheckSoftInputRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CheckSoftInputRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CheckSoftInputRelativeLayout(Context context) {
        super(context);
    }

    public void setOnSizeChangedListener(OnSizeChangedListener onSizeChangedListener) {
        this.a = onSizeChangedListener;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        System.out.println("liuyue_onSizeChanged: w:" + i + ", h:" + i2 + ", oldw:" + i3 + ", oldh:" + i4);
        if (this.a != null) {
            this.a.onSizeChanged(i, i2, i3, i4);
        }
        super.onSizeChanged(i, i2, i3, i4);
    }
}
