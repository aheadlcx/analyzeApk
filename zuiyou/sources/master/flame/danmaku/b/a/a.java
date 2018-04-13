package master.flame.danmaku.b.a;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import master.flame.danmaku.a.f;
import master.flame.danmaku.danmaku.model.android.e;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.l.c;

public class a {
    private final GestureDetector a;
    private f b;
    private RectF c;
    private float d;
    private float e;
    private final OnGestureListener f = new SimpleOnGestureListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean onDown(MotionEvent motionEvent) {
            if (this.a.b == null || this.a.b.getOnDanmakuClickListener() == null) {
                return false;
            }
            this.a.d = this.a.b.getXOff();
            this.a.e = this.a.b.getYOff();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            boolean z = false;
            l a = this.a.a(motionEvent.getX(), motionEvent.getY());
            if (!(a == null || a.e())) {
                z = this.a.a(a, false);
            }
            if (z) {
                return z;
            }
            return this.a.a();
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.a.b.getOnDanmakuClickListener() != null) {
                this.a.d = this.a.b.getXOff();
                this.a.e = this.a.b.getYOff();
                l a = this.a.a(motionEvent.getX(), motionEvent.getY());
                if (a != null && !a.e()) {
                    this.a.a(a, true);
                }
            }
        }
    };

    private a(f fVar) {
        this.b = fVar;
        this.c = new RectF();
        this.a = new GestureDetector(((View) fVar).getContext(), this.f);
    }

    public static synchronized a a(f fVar) {
        a aVar;
        synchronized (a.class) {
            aVar = new a(fVar);
        }
        return aVar;
    }

    public boolean a(MotionEvent motionEvent) {
        return this.a.onTouchEvent(motionEvent);
    }

    private boolean a(l lVar, boolean z) {
        master.flame.danmaku.a.f.a onDanmakuClickListener = this.b.getOnDanmakuClickListener();
        if (onDanmakuClickListener == null) {
            return false;
        }
        if (z) {
            return onDanmakuClickListener.b(lVar);
        }
        return onDanmakuClickListener.a(lVar);
    }

    private boolean a() {
        master.flame.danmaku.a.f.a onDanmakuClickListener = this.b.getOnDanmakuClickListener();
        if (onDanmakuClickListener != null) {
            return onDanmakuClickListener.a(this.b);
        }
        return false;
    }

    private l a(final float f, final float f2) {
        final l eVar = new e();
        this.c.setEmpty();
        l currentVisibleDanmakus = this.b.getCurrentVisibleDanmakus();
        if (!(currentVisibleDanmakus == null || currentVisibleDanmakus.e())) {
            currentVisibleDanmakus.a(new c<d>(this) {
                final /* synthetic */ a d;

                public int a(d dVar) {
                    if (dVar != null) {
                        this.d.c.set(dVar.k(), dVar.l(), dVar.m(), dVar.n());
                        if (this.d.c.intersect(f - this.d.d, f2 - this.d.e, f + this.d.d, f2 + this.d.e)) {
                            eVar.a(dVar);
                        }
                    }
                    return 0;
                }
            });
        }
        return eVar;
    }
}
