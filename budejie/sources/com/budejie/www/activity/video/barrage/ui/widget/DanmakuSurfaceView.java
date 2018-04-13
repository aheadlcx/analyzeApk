package com.budejie.www.activity.video.barrage.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import com.budejie.www.activity.video.barrage.a.c;
import com.budejie.www.activity.video.barrage.a.c.a;
import com.budejie.www.activity.video.barrage.a.d;
import com.budejie.www.activity.video.barrage.a.f;
import com.budejie.www.activity.video.barrage.a.f$a;
import com.budejie.www.activity.video.barrage.a.g;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import java.util.LinkedList;
import java.util.Locale;

public class DanmakuSurfaceView extends SurfaceView implements Callback, f, g {
    protected int a = 0;
    private a b;
    private SurfaceHolder c;
    private HandlerThread d;
    private c e;
    private boolean f;
    private boolean g = true;
    private f$a h;
    private a i;
    private boolean j;
    private boolean k = true;
    private LinkedList<Long> l;

    private void k() {
        setZOrderMediaOverlay(true);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        this.c = getHolder();
        this.c.addCallback(this);
        this.c.setFormat(-2);
        d.a(true, true);
        this.i = a.a((f) this);
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        k();
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        k();
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        if (this.e != null) {
            this.e.a(cVar);
        }
    }

    public void a() {
        if (this.e != null) {
            this.e.e();
        }
    }

    public k getCurrentVisibleDanmakus() {
        if (this.e != null) {
            return this.e.f();
        }
        return null;
    }

    public void setCallback(a aVar) {
        this.b = aVar;
        if (this.e != null) {
            this.e.a(aVar);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f = true;
        Canvas lockCanvas = surfaceHolder.lockCanvas();
        if (lockCanvas != null) {
            d.a(lockCanvas);
            surfaceHolder.unlockCanvasAndPost(lockCanvas);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.e != null) {
            this.e.a(i2, i3);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f = false;
    }

    public void c() {
        j();
        if (this.l != null) {
            this.l.clear();
        }
    }

    public void j() {
        l();
    }

    private void l() {
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
        if (this.d != null) {
            try {
                this.d.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.d.quit();
            this.d = null;
        }
    }

    protected Looper a(int i) {
        int i2;
        if (this.d != null) {
            this.d.quit();
            this.d = null;
        }
        switch (i) {
            case 1:
                return Looper.getMainLooper();
            case 2:
                i2 = -8;
                break;
            case 3:
                i2 = 19;
                break;
            default:
                i2 = 0;
                break;
        }
        this.d = new HandlerThread("DFM Handler Thread #" + i2, i2);
        this.d.start();
        return this.d.getLooper();
    }

    private void m() {
        if (this.e == null) {
            this.e = new c(a(this.a), this, this.k);
        }
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.a.a aVar, DanmakuContext danmakuContext) {
        m();
        this.e.a(danmakuContext);
        this.e.a(aVar);
        this.e.a(this.b);
        this.e.d();
    }

    public DanmakuContext getConfig() {
        if (this.e == null) {
            return null;
        }
        return this.e.h();
    }

    public void b(boolean z) {
        this.j = z;
    }

    private float n() {
        long currentTimeMillis = System.currentTimeMillis();
        this.l.addLast(Long.valueOf(currentTimeMillis));
        float longValue = (float) (currentTimeMillis - ((Long) this.l.getFirst()).longValue());
        if (this.l.size() > 50) {
            this.l.removeFirst();
        }
        return longValue > 0.0f ? ((float) (this.l.size() * 1000)) / longValue : 0.0f;
    }

    public long g() {
        if (!this.f) {
            return 0;
        }
        if (!isShown()) {
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Canvas lockCanvas = this.c.lockCanvas();
        if (lockCanvas != null) {
            if (this.e != null) {
                com.budejie.www.activity.video.barrage.danmaku.b.a.a a = this.e.a(lockCanvas);
                if (this.j) {
                    if (this.l == null) {
                        this.l = new LinkedList();
                    }
                    long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                    d.a(lockCanvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(n()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a.m), Long.valueOf(a.n)}));
                }
            }
            if (this.f) {
                this.c.unlockCanvasAndPost(lockCanvas);
            }
        }
        return System.currentTimeMillis() - currentTimeMillis;
    }

    public void b() {
        a(0);
    }

    public void a(long j) {
        if (this.e == null) {
            m();
        } else {
            this.e.removeCallbacksAndMessages(null);
        }
        this.e.obtainMessage(1, Long.valueOf(j)).sendToTarget();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.i != null) {
            this.i.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean i() {
        return this.g;
    }

    public boolean f() {
        return this.f;
    }

    public View getView() {
        return this;
    }

    public void d() {
        a(null);
    }

    public void a(Long l) {
        this.k = true;
        if (this.e != null) {
            this.e.a(l);
        }
    }

    public void e() {
        this.k = false;
        if (this.e != null) {
            this.e.a(false);
        }
    }

    public void setOnDanmakuClickListener(f$a f_a) {
        this.h = f_a;
        setClickable(f_a != null);
    }

    public f$a getOnDanmakuClickListener() {
        return this.h;
    }

    public void h() {
        if (f()) {
            Canvas lockCanvas = this.c.lockCanvas();
            if (lockCanvas != null) {
                d.a(lockCanvas);
                this.c.unlockCanvasAndPost(lockCanvas);
            }
        }
    }

    public boolean isShown() {
        return this.k && super.isShown();
    }

    public void setDrawingThreadType(int i) {
        this.a = i;
    }

    public long getCurrentTime() {
        if (this.e != null) {
            return this.e.g();
        }
        return 0;
    }

    public boolean isHardwareAccelerated() {
        return false;
    }
}
