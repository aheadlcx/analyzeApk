package com.budejie.www.activity.video.barrage.ui.widget;

import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;
import com.budejie.www.activity.video.barrage.a.f;
import com.budejie.www.activity.video.barrage.danmaku.model.c;
import com.budejie.www.activity.video.barrage.danmaku.model.j;
import com.budejie.www.activity.video.barrage.danmaku.model.k;

public class a {
    boolean a = false;
    Runnable b = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.a) {
                this.a.a = false;
                if (this.a.c.getOnDanmakuClickListener() != null) {
                    this.a.c.getOnDanmakuClickListener().a(false);
                }
            }
        }
    };
    private f c;
    private RectF d;
    private Handler e = new Handler();

    private a(f fVar) {
        this.c = fVar;
        this.d = new RectF();
    }

    public static synchronized a a(f fVar) {
        a aVar;
        synchronized (a.class) {
            aVar = new a(fVar);
        }
        return aVar;
    }

    public boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                k a = a(motionEvent.getX(), motionEvent.getY());
                c cVar = null;
                if (!(a == null || a.f())) {
                    a(a);
                    cVar = b(a);
                }
                if (cVar != null) {
                    a(cVar, motionEvent.getX(), motionEvent.getY());
                    return true;
                }
                break;
        }
        return false;
    }

    private void a(c cVar, float f, float f2) {
        if (this.c.getOnDanmakuClickListener() != null) {
            this.c.getOnDanmakuClickListener().a(cVar, f, f2);
        }
    }

    private void a(k kVar) {
        if (this.c.getOnDanmakuClickListener() != null) {
            this.c.getOnDanmakuClickListener().a(kVar);
        }
    }

    private k a(float f, float f2) {
        k cVar = new com.budejie.www.activity.video.barrage.danmaku.model.android.c();
        this.d.setEmpty();
        k currentVisibleDanmakus = this.c.getCurrentVisibleDanmakus();
        if (!(currentVisibleDanmakus == null || currentVisibleDanmakus.f())) {
            j e = currentVisibleDanmakus.e();
            while (e.b()) {
                c a = e.a();
                if (a != null) {
                    this.d.set(a.j(), a.k(), a.l(), a.m());
                    if (this.d.contains(f, f2)) {
                        cVar.a(a);
                    }
                }
            }
        }
        return cVar;
    }

    private c b(k kVar) {
        if (kVar.f()) {
            return null;
        }
        return kVar.d();
    }
}
