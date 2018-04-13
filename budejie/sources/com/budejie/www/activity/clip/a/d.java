package com.budejie.www.activity.clip.a;

import android.content.Context;
import android.view.MotionEvent;

public class d extends e {
    private float l;
    private float m;
    private final a n;
    private boolean o;

    public interface a {
        boolean a(d dVar);

        boolean b(d dVar);

        void c(d dVar);
    }

    public static class b implements a {
        public boolean a(d dVar) {
            return false;
        }

        public boolean b(d dVar) {
            return true;
        }

        public void c(d dVar) {
        }
    }

    public d(Context context, a aVar) {
        super(context);
        this.n = aVar;
    }

    protected void a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.o) {
                    this.o = c(motionEvent);
                    if (!this.o) {
                        this.b = this.n.b(this);
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
                this.o = c(motionEvent);
                if (!this.o) {
                    this.b = this.n.b(this);
                    return;
                }
                return;
            case 6:
                if (!this.o) {
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
                if (this.e / this.f > 0.67f && Math.abs(b()) > 0.5f && this.n.a(this)) {
                    this.c.recycle();
                    this.c = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.o) {
                    this.n.c(this);
                }
                a();
                return;
            case 6:
                b(motionEvent);
                if (!this.o) {
                    this.n.c(this);
                }
                a();
                return;
            default:
                return;
        }
    }

    protected void a() {
        super.a();
        this.o = false;
        this.l = 0.0f;
        this.m = 0.0f;
    }

    protected void b(MotionEvent motionEvent) {
        super.b(motionEvent);
        MotionEvent motionEvent2 = this.c;
        this.l = (motionEvent2.getY(1) + motionEvent2.getY(0)) / 2.0f;
        this.m = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
    }

    protected boolean c(MotionEvent motionEvent) {
        if (super.c(motionEvent)) {
            return true;
        }
        double abs = Math.abs(Math.atan2((double) this.k, (double) this.j));
        if ((0.0d >= abs || abs >= 0.3499999940395355d) && (2.7899999618530273d >= abs || abs >= 3.141592653589793d)) {
            return true;
        }
        return false;
    }

    public float b() {
        return this.m - this.l;
    }
}
