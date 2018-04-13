package antistatic.spinnerwheel;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public abstract class WheelScroller {
    public static final int MIN_DELTA_FOR_SCROLLING = 1;
    protected Scroller a;
    private final int b = 0;
    private final int c = 1;
    private ScrollingListener d;
    private Context e;
    private GestureDetector f;
    private int g;
    private float h;
    private boolean i;
    private Handler j = new e(this);

    public interface ScrollingListener {
        void onFinished();

        void onJustify();

        void onScroll(int i);

        void onStarted();

        void onTouch();

        void onTouchUp();
    }

    protected abstract float a(MotionEvent motionEvent);

    protected abstract int a();

    protected abstract void a(int i, int i2);

    protected abstract void a(int i, int i2, int i3);

    protected abstract int b();

    public WheelScroller(Context context, ScrollingListener scrollingListener) {
        this.f = new GestureDetector(context, new f(this));
        this.f.setIsLongpressEnabled(false);
        this.a = new Scroller(context);
        this.d = scrollingListener;
        this.e = context;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.a.forceFinished(true);
        this.a = new Scroller(this.e, interpolator);
    }

    public void scroll(int i, int i2) {
        this.a.forceFinished(true);
        this.g = 0;
        if (i2 == 0) {
            i2 = 400;
        }
        a(i, i2);
        a(0);
        f();
    }

    public void stopScrolling() {
        this.a.forceFinished(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.h = a(motionEvent);
                this.a.forceFinished(true);
                d();
                this.d.onTouch();
                break;
            case 1:
                if (this.a.isFinished()) {
                    this.d.onTouchUp();
                    break;
                }
                break;
            case 2:
                int a = (int) (a(motionEvent) - this.h);
                if (a != 0) {
                    f();
                    this.d.onScroll(a);
                    this.h = a(motionEvent);
                    break;
                }
                break;
        }
        if (!this.f.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            e();
        }
        return true;
    }

    private void a(int i) {
        d();
        this.j.sendEmptyMessage(i);
    }

    private void d() {
        this.j.removeMessages(0);
        this.j.removeMessages(1);
    }

    private void e() {
        this.d.onJustify();
        a(1);
    }

    private void f() {
        if (!this.i) {
            this.i = true;
            this.d.onStarted();
        }
    }

    protected void c() {
        if (this.i) {
            this.d.onFinished();
            this.i = false;
        }
    }
}
