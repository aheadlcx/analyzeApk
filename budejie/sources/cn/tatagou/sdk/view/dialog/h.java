package cn.tatagou.sdk.view.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class h {
    private a a;
    private Context b;
    private GestureDetector c;
    private Scroller d;
    private int e;
    private float f;
    private boolean g;
    private SimpleOnGestureListener h = new SimpleOnGestureListener(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.a.e = 0;
            this.a.d.fling(0, this.a.e, 0, (int) (-f2), 0, 0, -2147483647, Integer.MAX_VALUE);
            this.a.a(0);
            return true;
        }
    };
    private final int i = 0;
    private final int j = 1;
    private Handler k = new Handler(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.d.computeScrollOffset();
            int currY = this.a.d.getCurrY();
            int a = this.a.e - currY;
            this.a.e = currY;
            if (a != 0) {
                this.a.a.onScroll(a);
            }
            if (Math.abs(currY - this.a.d.getFinalY()) < 1) {
                this.a.d.getFinalY();
                this.a.d.forceFinished(true);
            }
            if (!this.a.d.isFinished()) {
                this.a.k.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                this.a.c();
            } else {
                this.a.a();
            }
        }
    };

    public interface a {
        void onFinished();

        void onJustify();

        void onScroll(int i);

        void onStarted();
    }

    public h(Context context, a aVar) {
        this.c = new GestureDetector(context, this.h);
        this.c.setIsLongpressEnabled(false);
        this.d = new Scroller(context);
        this.a = aVar;
        this.b = context;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.d.forceFinished(true);
        this.d = new Scroller(this.b, interpolator);
    }

    public void scroll(int i, int i2) {
        this.d.forceFinished(true);
        this.e = 0;
        this.d.startScroll(0, 0, 0, i, i2 != 0 ? i2 : 400);
        a(0);
        d();
    }

    public void stopScrolling() {
        this.d.forceFinished(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f = motionEvent.getY();
                this.d.forceFinished(true);
                b();
                break;
            case 2:
                int y = (int) (motionEvent.getY() - this.f);
                if (y != 0) {
                    d();
                    this.a.onScroll(y);
                    this.f = motionEvent.getY();
                    break;
                }
                break;
        }
        if (!this.c.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            c();
        }
        return true;
    }

    private void a(int i) {
        b();
        this.k.sendEmptyMessage(i);
    }

    private void b() {
        this.k.removeMessages(0);
        this.k.removeMessages(1);
    }

    private void c() {
        this.a.onJustify();
        a(1);
    }

    private void d() {
        if (!this.g) {
            this.g = true;
            this.a.onStarted();
        }
    }

    void a() {
        if (this.g) {
            this.a.onFinished();
            this.g = false;
        }
    }
}
