package com.budejie.www.activity.video.barrage.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
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

public class DanmakuView extends View implements f, g {
    protected int a = 0;
    private a b;
    private HandlerThread c;
    private c d;
    private boolean e;
    private boolean f = true;
    private f$a g;
    private a h;
    private boolean i;
    private boolean j = true;
    private Object k = new Object();
    private boolean l = false;
    private boolean m = false;
    private long n;
    private LinkedList<Long> o;
    private boolean p;
    private int q = 0;
    private Runnable r = new Runnable(this) {
        final /* synthetic */ DanmakuView a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.d != null) {
                this.a.q = this.a.q + 1;
                if (this.a.q > 4 || super.isShown()) {
                    this.a.d.c();
                } else {
                    this.a.d.postDelayed(this, (long) (this.a.q * 100));
                }
            }
        }
    };

    public DanmakuView(Context context) {
        super(context);
        k();
    }

    private void k() {
        this.n = Thread.currentThread().getId();
        setBackgroundColor(0);
        setDrawingCacheBackgroundColor(0);
        d.a(true, false);
        this.h = a.a((f) this);
    }

    public DanmakuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        k();
    }

    public DanmakuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        k();
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        if (this.d != null) {
            this.d.a(cVar);
        }
    }

    public void a() {
        if (this.d != null) {
            this.d.e();
        }
    }

    public k getCurrentVisibleDanmakus() {
        if (this.d != null) {
            return this.d.f();
        }
        return null;
    }

    public void setCallback(a aVar) {
        this.b = aVar;
        if (this.d != null) {
            this.d.a(aVar);
        }
    }

    public void c() {
        j();
        if (this.o != null) {
            this.o.clear();
        }
    }

    public void j() {
        l();
    }

    private void l() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        if (this.c != null) {
            try {
                this.c.join(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.c.quit();
            this.c = null;
        }
    }

    protected Looper a(int i) {
        int i2;
        if (this.c != null) {
            this.c.quit();
            this.c = null;
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
        this.c = new HandlerThread("DFM Handler Thread #" + i2, i2);
        this.c.start();
        return this.c.getLooper();
    }

    private void m() {
        if (this.d == null) {
            this.d = new c(a(this.a), this, this.j);
        }
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.a.a aVar, DanmakuContext danmakuContext) {
        m();
        this.d.a(danmakuContext);
        this.d.a(aVar);
        this.d.a(this.b);
        this.d.d();
    }

    public DanmakuContext getConfig() {
        if (this.d == null) {
            return null;
        }
        return this.d.h();
    }

    public void b(boolean z) {
        this.i = z;
    }

    private float n() {
        long currentTimeMillis = System.currentTimeMillis();
        this.o.addLast(Long.valueOf(currentTimeMillis));
        float longValue = (float) (currentTimeMillis - ((Long) this.o.getFirst()).longValue());
        if (this.o.size() > 50) {
            this.o.removeFirst();
        }
        return longValue > 0.0f ? ((float) (this.o.size() * 1000)) / longValue : 0.0f;
    }

    public long g() {
        if (!this.e) {
            return 0;
        }
        if (!isShown()) {
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        p();
        return System.currentTimeMillis() - currentTimeMillis;
    }

    @SuppressLint({"NewApi"})
    private void o() {
        this.m = true;
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            postInvalidate();
        }
    }

    private void p() {
        if (this.j) {
            o();
            synchronized (this.k) {
                while (!this.l && this.d != null) {
                    try {
                        this.k.wait(200);
                    } catch (InterruptedException e) {
                        if (!this.j || this.d == null || this.d.b()) {
                            break;
                        }
                        Thread.currentThread().interrupt();
                    }
                }
                this.l = false;
            }
        }
    }

    private void q() {
        this.p = true;
        p();
    }

    private void r() {
        synchronized (this.k) {
            this.l = true;
            this.k.notifyAll();
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.j || this.m) {
            if (this.p) {
                d.a(canvas);
                this.p = false;
            } else if (this.d != null) {
                com.budejie.www.activity.video.barrage.danmaku.b.a.a a = this.d.a(canvas);
                if (this.i) {
                    if (this.o == null) {
                        this.o = new LinkedList();
                    }
                    d.a(canvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(n()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a.m), Long.valueOf(a.n)}));
                }
            }
            this.m = false;
            r();
            return;
        }
        super.onDraw(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.d != null) {
            this.d.a(i3 - i, i4 - i2);
        }
        this.e = true;
    }

    public void b() {
        a(0);
    }

    public void a(long j) {
        if (this.d == null) {
            m();
        } else {
            this.d.removeCallbacksAndMessages(null);
        }
        this.d.obtainMessage(1, Long.valueOf(j)).sendToTarget();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.h != null) {
            return this.h.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean i() {
        return this.f;
    }

    public boolean f() {
        return this.e;
    }

    public View getView() {
        return this;
    }

    public void d() {
        a(null);
    }

    public void a(Long l) {
        this.j = true;
        this.p = false;
        if (this.d != null) {
            this.d.a(l);
        }
    }

    public void e() {
        this.j = false;
        if (this.d != null) {
            this.d.a(false);
        }
    }

    public void h() {
        if (!f()) {
            return;
        }
        if (!this.j || Thread.currentThread().getId() == this.n) {
            this.p = true;
            o();
            return;
        }
        q();
    }

    public boolean isShown() {
        return this.j && super.isShown();
    }

    public void setDrawingThreadType(int i) {
        this.a = i;
    }

    public long getCurrentTime() {
        if (this.d != null) {
            return this.d.g();
        }
        return 0;
    }

    @SuppressLint({"NewApi"})
    public boolean isHardwareAccelerated() {
        if (VERSION.SDK_INT >= 11) {
            return super.isHardwareAccelerated();
        }
        return false;
    }

    public void setOnDanmakuClickListener(f$a f_a) {
        this.g = f_a;
        setClickable(f_a != null);
    }

    public f$a getOnDanmakuClickListener() {
        return this.g;
    }
}
