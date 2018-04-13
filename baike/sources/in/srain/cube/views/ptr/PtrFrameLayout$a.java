package in.srain.cube.views.ptr;

import android.widget.Scroller;
import in.srain.cube.views.ptr.util.PtrCLog;

class PtrFrameLayout$a implements Runnable {
    final /* synthetic */ PtrFrameLayout a;
    private int b;
    private Scroller c;
    private boolean d = false;
    private int e;
    private int f;

    public PtrFrameLayout$a(PtrFrameLayout ptrFrameLayout) {
        this.a = ptrFrameLayout;
        this.c = new Scroller(ptrFrameLayout.getContext());
    }

    public void run() {
        boolean z = !this.c.computeScrollOffset() || this.c.isFinished();
        int currY = this.c.getCurrY();
        int i = currY - this.b;
        if (PtrFrameLayout.DEBUG && i != 0) {
            PtrCLog.v(this.a.a, "scroll: %s, start: %s, to: %s, currentPos: %s, current :%s, last: %s, delta: %s", Boolean.valueOf(z), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.a.mPtrIndicator.getCurrentPosY()), Integer.valueOf(currY), Integer.valueOf(this.b), Integer.valueOf(i));
        }
        if (z) {
            a();
            return;
        }
        this.b = currY;
        PtrFrameLayout.a(this.a, (float) i);
        this.a.post(this);
    }

    private void a() {
        if (PtrFrameLayout.DEBUG) {
            PtrCLog.v(this.a.a, "finish, currentPos:%s", Integer.valueOf(this.a.mPtrIndicator.getCurrentPosY()));
        }
        b();
        this.a.b();
    }

    private void b() {
        this.d = false;
        this.b = 0;
        this.a.removeCallbacks(this);
    }

    private void c() {
        b();
        if (!this.c.isFinished()) {
            this.c.forceFinished(true);
        }
    }

    public void abortIfWorking() {
        if (this.d) {
            if (!this.c.isFinished()) {
                this.c.forceFinished(true);
            }
            this.a.a();
            b();
        }
    }

    public void tryToScrollTo(int i, int i2) {
        if (!this.a.mPtrIndicator.isAlreadyHere(i)) {
            this.e = this.a.mPtrIndicator.getCurrentPosY();
            this.f = i;
            int i3 = i - this.e;
            if (PtrFrameLayout.DEBUG) {
                PtrCLog.d(this.a.a, "tryToScrollTo: start: %s, distance:%s, to:%s", Integer.valueOf(this.e), Integer.valueOf(i3), Integer.valueOf(i));
            }
            this.a.removeCallbacks(this);
            this.b = 0;
            if (!this.c.isFinished()) {
                this.c.forceFinished(true);
            }
            this.c.startScroll(0, 0, 0, i3, i2);
            this.a.post(this);
            this.d = true;
        }
    }
}
