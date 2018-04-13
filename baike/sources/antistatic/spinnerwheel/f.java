package antistatic.spinnerwheel;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

class f extends SimpleOnGestureListener {
    final /* synthetic */ WheelScroller a;

    f(WheelScroller wheelScroller) {
        this.a = wheelScroller;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.a.g = 0;
        this.a.a(this.a.g, (int) f, (int) f2);
        this.a.a(0);
        return true;
    }
}
