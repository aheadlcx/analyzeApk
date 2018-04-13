package antistatic.spinnerwheel;

import antistatic.spinnerwheel.WheelScroller.ScrollingListener;

class b implements ScrollingListener {
    final /* synthetic */ AbstractWheel a;

    b(AbstractWheel abstractWheel) {
        this.a = abstractWheel;
    }

    public void onStarted() {
        this.a.f = true;
        this.a.g();
        this.a.a();
    }

    public void onTouch() {
        this.a.b();
    }

    public void onTouchUp() {
        if (!this.a.f) {
            this.a.c();
        }
    }

    public void onScroll(int i) {
        this.a.c(i);
        int baseDimension = this.a.getBaseDimension();
        if (this.a.g > baseDimension) {
            this.a.g = baseDimension;
            this.a.e.stopScrolling();
        } else if (this.a.g < (-baseDimension)) {
            this.a.g = -baseDimension;
            this.a.e.stopScrolling();
        }
    }

    public void onFinished() {
        if (this.a.f) {
            this.a.h();
            this.a.f = false;
            this.a.d();
        }
        this.a.g = 0;
        this.a.invalidate();
    }

    public void onJustify() {
        if (Math.abs(this.a.g) > 1) {
            this.a.e.scroll(this.a.g, 0);
        }
    }
}
