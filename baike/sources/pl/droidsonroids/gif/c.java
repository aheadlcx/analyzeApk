package pl.droidsonroids.gif;

import java.util.concurrent.TimeUnit;

class c extends a {
    final /* synthetic */ GifDrawable a;

    c(GifDrawable gifDrawable) {
        this.a = gifDrawable;
        super();
    }

    public void doWork() {
        long a = this.a.h.a(this.a.g);
        int i = (int) (a >> 1);
        if (((int) (a & 1)) == 1 && !this.a.i.isEmpty()) {
            this.a.scheduleSelf(this.a.o, 0);
        }
        if (i >= 0) {
            if (this.a.isVisible() && this.a.c) {
                this.a.b.schedule(this, (long) i, TimeUnit.MILLISECONDS);
            }
            this.a.unscheduleSelf(this.a.m);
            this.a.scheduleSelf(this.a.m, 0);
        }
    }
}
