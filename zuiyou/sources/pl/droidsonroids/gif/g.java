package pl.droidsonroids.gif;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

class g extends h {
    g(b bVar) {
        super(bVar);
    }

    public void a() {
        long a = this.c.f.a(this.c.e);
        if (a >= 0) {
            this.c.c = SystemClock.uptimeMillis() + a;
            if (this.c.isVisible() && this.c.b && !this.c.h) {
                this.c.a.remove(this);
                this.c.j = this.c.a.schedule(this, a, TimeUnit.MILLISECONDS);
            }
            if (!this.c.g.isEmpty() && this.c.e() == this.c.f.n() - 1) {
                this.c.i.sendEmptyMessageAtTime(this.c.f(), this.c.c);
            }
        } else {
            this.c.c = Long.MIN_VALUE;
            this.c.b = false;
        }
        if (this.c.isVisible() && !this.c.i.hasMessages(-1)) {
            this.c.i.sendEmptyMessageAtTime(-1, 0);
        }
    }
}
