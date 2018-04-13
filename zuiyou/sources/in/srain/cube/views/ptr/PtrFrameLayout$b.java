package in.srain.cube.views.ptr;

import android.widget.Scroller;
import in.srain.cube.views.ptr.b.a;

class PtrFrameLayout$b implements Runnable {
    final /* synthetic */ PtrFrameLayout a;
    private int b;
    private Scroller c;
    private boolean d = false;
    private int e;
    private int f;

    public PtrFrameLayout$b(PtrFrameLayout ptrFrameLayout) {
        this.a = ptrFrameLayout;
        this.c = new Scroller(ptrFrameLayout.getContext());
    }

    public void run() {
        boolean z = !this.c.computeScrollOffset() || this.c.isFinished();
        int currY = this.c.getCurrY();
        int i = currY - this.b;
        if (PtrFrameLayout.a && i != 0) {
            a.a(this.a.b, "scroll: %s, start: %s, to: %s, currentPos: %s, current :%s, last: %s, delta: %s", Boolean.valueOf(z), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(PtrFrameLayout.b(this.a).k()), Integer.valueOf(currY), Integer.valueOf(this.b), Integer.valueOf(i));
        }
        if (z) {
            b();
            return;
        }
        this.b = currY;
        PtrFrameLayout.a(this.a, (float) i);
        this.a.post(this);
    }

    private void b() {
        if (PtrFrameLayout.a) {
            a.a(this.a.b, "finish, currentPos:%s", Integer.valueOf(PtrFrameLayout.b(this.a).k()));
        }
        c();
        this.a.b();
    }

    private void c() {
        this.d = false;
        this.b = 0;
        this.a.removeCallbacks(this);
    }

    private void d() {
        c();
        if (!this.c.isFinished()) {
            this.c.forceFinished(true);
        }
    }

    public void a() {
        if (this.d) {
            if (!this.c.isFinished()) {
                this.c.forceFinished(true);
            }
            this.a.a();
            c();
        }
    }

    public void a(int i, int i2) {
        if (!PtrFrameLayout.b(this.a).e(i)) {
            this.e = PtrFrameLayout.b(this.a).k();
            this.f = i;
            int i3 = i - this.e;
            if (PtrFrameLayout.a) {
                a.b(this.a.b, "tryToScrollTo: start: %s, distance:%s, to:%s", Integer.valueOf(this.e), Integer.valueOf(i3), Integer.valueOf(i));
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
