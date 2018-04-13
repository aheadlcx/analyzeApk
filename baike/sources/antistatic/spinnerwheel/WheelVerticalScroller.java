package antistatic.spinnerwheel;

import android.content.Context;
import android.view.MotionEvent;
import antistatic.spinnerwheel.WheelScroller.ScrollingListener;

public class WheelVerticalScroller extends WheelScroller {
    public WheelVerticalScroller(Context context, ScrollingListener scrollingListener) {
        super(context, scrollingListener);
    }

    protected int a() {
        return this.a.getCurrY();
    }

    protected int b() {
        return this.a.getFinalY();
    }

    protected float a(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    protected void a(int i, int i2) {
        this.a.startScroll(0, 0, 0, i, i2);
    }

    protected void a(int i, int i2, int i3) {
        this.a.fling(0, i, 0, -i3, 0, 0, -2147483647, Integer.MAX_VALUE);
    }
}
