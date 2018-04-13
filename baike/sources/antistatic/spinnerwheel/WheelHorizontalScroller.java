package antistatic.spinnerwheel;

import android.content.Context;
import android.view.MotionEvent;
import antistatic.spinnerwheel.WheelScroller.ScrollingListener;

public class WheelHorizontalScroller extends WheelScroller {
    public WheelHorizontalScroller(Context context, ScrollingListener scrollingListener) {
        super(context, scrollingListener);
    }

    protected int a() {
        return this.a.getCurrX();
    }

    protected int b() {
        return this.a.getFinalX();
    }

    protected float a(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    protected void a(int i, int i2) {
        this.a.startScroll(0, 0, i, 0, i2);
    }

    protected void a(int i, int i2, int i3) {
        this.a.fling(i, 0, -i2, 0, -2147483647, Integer.MAX_VALUE, 0, 0);
    }
}
