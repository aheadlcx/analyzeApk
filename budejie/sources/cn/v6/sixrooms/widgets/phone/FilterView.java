package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class FilterView extends FrameLayout {
    public FilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FilterView(Context context) {
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }
}
