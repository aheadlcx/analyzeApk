package cn.xiaochuankeji.tieba.ui.videomaker.b;

import android.content.Context;
import android.view.MotionEvent;

public class b extends c {
    private final a l;
    private boolean m;

    public interface a {
        boolean a(b bVar);

        boolean b(b bVar);

        void c(b bVar);
    }

    public b(Context context, a aVar) {
        super(context);
        this.l = aVar;
    }

    protected void a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.m) {
                    this.m = c(motionEvent);
                    if (!this.m) {
                        this.b = this.l.b(this);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                a();
                this.c = MotionEvent.obtain(motionEvent);
                this.g = 0;
                b(motionEvent);
                this.m = c(motionEvent);
                if (!this.m) {
                    this.b = this.l.b(this);
                    return;
                }
                return;
            case 6:
                if (!this.m) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                b(motionEvent);
                if (this.e / this.f > 0.67f && this.l.a(this)) {
                    this.c.recycle();
                    this.c = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.m) {
                    this.l.c(this);
                }
                a();
                return;
            case 6:
                b(motionEvent);
                if (!this.m) {
                    this.l.c(this);
                }
                a();
                return;
            default:
                return;
        }
    }

    protected void a() {
        super.a();
        this.m = false;
    }

    public float c() {
        return (float) (((Math.atan2((double) this.i, (double) this.h) - Math.atan2((double) this.k, (double) this.j)) * 180.0d) / 3.141592653589793d);
    }
}
