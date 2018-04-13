package com.budejie.www.activity.clip.a;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

public class b extends a {
    private static final PointF h = new PointF();
    private final a i;
    private PointF j;
    private PointF k;
    private PointF l = new PointF();
    private PointF m = new PointF();

    public interface a {
        boolean a(b bVar);

        boolean b(b bVar);

        void c(b bVar);
    }

    public static class b implements a {
        public boolean a(b bVar) {
            return false;
        }

        public boolean b(b bVar) {
            return true;
        }

        public void c(b bVar) {
        }
    }

    public b(Context context, a aVar) {
        super(context);
        this.i = aVar;
    }

    protected void a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 0:
                a();
                this.c = MotionEvent.obtain(motionEvent);
                this.g = 0;
                b(motionEvent);
                return;
            case 2:
                this.b = this.i.b(this);
                return;
            default:
                return;
        }
    }

    protected void b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 1:
            case 3:
                this.i.c(this);
                a();
                return;
            case 2:
                b(motionEvent);
                if (this.e / this.f > 0.67f && this.i.a(this)) {
                    this.c.recycle();
                    this.c = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void b(MotionEvent motionEvent) {
        super.b(motionEvent);
        MotionEvent motionEvent2 = this.c;
        this.j = c(motionEvent);
        this.k = c(motionEvent2);
        this.m = (motionEvent2.getPointerCount() != motionEvent.getPointerCount() ? 1 : null) != null ? h : new PointF(this.j.x - this.k.x, this.j.y - this.k.y);
        PointF pointF = this.l;
        pointF.x += this.m.x;
        pointF = this.l;
        pointF.y += this.m.y;
    }

    private PointF c(MotionEvent motionEvent) {
        float f = 0.0f;
        int pointerCount = motionEvent.getPointerCount();
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f2 += motionEvent.getX(i);
            f += motionEvent.getY(i);
        }
        return new PointF(f2 / ((float) pointerCount), f / ((float) pointerCount));
    }

    public PointF b() {
        return this.m;
    }
}
