package master.flame.danmaku.b.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.LinkedList;
import java.util.Locale;
import master.flame.danmaku.a.c;
import master.flame.danmaku.a.c.a;
import master.flame.danmaku.a.d;
import master.flame.danmaku.a.f;
import master.flame.danmaku.a.g;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.l;

public class b extends View implements f, g {
    protected int a = 0;
    private a b;
    private HandlerThread c;
    private c d;
    private boolean e;
    private boolean f = true;
    private f.a g;
    private float h;
    private float i;
    private a j;
    private boolean k;
    private boolean l = true;
    private Object m = new Object();
    private boolean n = false;
    private boolean o = false;
    private long p;
    private LinkedList<Long> q;
    private boolean r;
    private int s = 0;
    private Runnable t = new Runnable(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.d != null) {
                this.a.s = this.a.s + 1;
                if (this.a.s > 4 || super.isShown()) {
                    this.a.d.d();
                } else {
                    this.a.d.postDelayed(this, (long) (this.a.s * 100));
                }
            }
        }
    };

    public b(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.p = Thread.currentThread().getId();
        setBackgroundColor(0);
        setDrawingCacheBackgroundColor(0);
        d.a(true, false);
        this.j = a.a((f) this);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void a(master.flame.danmaku.danmaku.model.d dVar) {
        if (this.d != null) {
            this.d.a(dVar);
        }
    }

    public void a(boolean z) {
        if (this.d != null) {
            this.d.b(z);
        }
    }

    public l getCurrentVisibleDanmakus() {
        if (this.d != null) {
            return this.d.g();
        }
        return null;
    }

    public void setCallback(a aVar) {
        this.b = aVar;
        if (this.d != null) {
            this.d.a(aVar);
        }
    }

    public void e() {
        j();
        if (this.q != null) {
            this.q.clear();
        }
    }

    public void j() {
        b();
    }

    private synchronized void b() {
        if (this.d != null) {
            c cVar = this.d;
            this.d = null;
            w();
            if (cVar != null) {
                cVar.a();
            }
            HandlerThread handlerThread = this.c;
            this.c = null;
            if (handlerThread != null) {
                try {
                    handlerThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handlerThread.quit();
            }
        }
    }

    protected synchronized Looper a(int i) {
        Looper mainLooper;
        int i2;
        if (this.c != null) {
            this.c.quit();
            this.c = null;
        }
        switch (i) {
            case 1:
                mainLooper = Looper.getMainLooper();
                break;
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
        mainLooper = this.c.getLooper();
        return mainLooper;
    }

    private void r() {
        if (this.d == null) {
            this.d = new c(a(this.a), this, this.l);
        }
    }

    public void a(master.flame.danmaku.danmaku.a.a aVar, DanmakuContext danmakuContext) {
        r();
        this.d.a(danmakuContext);
        this.d.a(aVar);
        this.d.a(this.b);
        this.d.e();
    }

    public boolean k() {
        return this.d != null && this.d.c();
    }

    public DanmakuContext getConfig() {
        if (this.d == null) {
            return null;
        }
        return this.d.j();
    }

    private float s() {
        long a = master.flame.danmaku.danmaku.c.b.a();
        this.q.addLast(Long.valueOf(a));
        Long l = (Long) this.q.peekFirst();
        if (l == null) {
            return 0.0f;
        }
        float longValue = (float) (a - l.longValue());
        if (this.q.size() > 50) {
            this.q.removeFirst();
        }
        return longValue > 0.0f ? ((float) (this.q.size() * 1000)) / longValue : 0.0f;
    }

    public long g() {
        if (!this.e) {
            return 0;
        }
        if (!isShown()) {
            return -1;
        }
        long a = master.flame.danmaku.danmaku.c.b.a();
        u();
        return master.flame.danmaku.danmaku.c.b.a() - a;
    }

    @SuppressLint({"NewApi"})
    private void t() {
        this.o = true;
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            postInvalidate();
        }
    }

    private void u() {
        if (this.l) {
            t();
            synchronized (this.m) {
                while (!this.n && this.d != null) {
                    try {
                        this.m.wait(200);
                    } catch (InterruptedException e) {
                        if (!this.l || this.d == null || this.d.b()) {
                            break;
                        }
                        Thread.currentThread().interrupt();
                    }
                }
                this.n = false;
            }
        }
    }

    private void v() {
        this.r = true;
        u();
    }

    private void w() {
        synchronized (this.m) {
            this.n = true;
            this.m.notifyAll();
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.l || this.o) {
            if (this.r) {
                d.a(canvas);
                this.r = false;
            } else if (this.d != null) {
                master.flame.danmaku.danmaku.b.a.b a = this.d.a(canvas);
                if (this.k) {
                    if (this.q == null) {
                        this.q = new LinkedList();
                    }
                    d.a(canvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(s()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a.r), Long.valueOf(a.s)}));
                }
            }
            this.o = false;
            w();
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

    public void d() {
        if (this.d != null) {
            this.d.removeCallbacks(this.t);
            this.d.f();
        }
    }

    public void c() {
        if (this.d != null && this.d.c()) {
            this.s = 0;
            this.d.post(this.t);
        } else if (this.d == null) {
            m();
        }
    }

    public boolean l() {
        if (this.d != null) {
            return this.d.b();
        }
        return false;
    }

    public void m() {
        j();
        n();
    }

    public void n() {
        a(0);
    }

    public void a(long j) {
        if (this.d == null) {
            r();
        } else {
            this.d.removeCallbacksAndMessages(null);
        }
        this.d.obtainMessage(1, Long.valueOf(j)).sendToTarget();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean a = this.j.a(motionEvent);
        if (a) {
            return a;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a(Long l) {
        if (this.d != null) {
            this.d.a(l);
        }
    }

    public void b(boolean z) {
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

    public void o() {
        b(null);
    }

    public void b(Long l) {
        this.l = true;
        this.r = false;
        if (this.d != null) {
            this.d.b(l);
        }
    }

    public void p() {
        this.l = false;
        if (this.d != null) {
            this.d.a(false);
        }
    }

    public void h() {
        if (!f()) {
            return;
        }
        if (!this.l || Thread.currentThread().getId() == this.p) {
            this.r = true;
            t();
            return;
        }
        v();
    }

    public boolean isShown() {
        return this.l && super.isShown();
    }

    public void setDrawingThreadType(int i) {
        this.a = i;
    }

    public long getCurrentTime() {
        if (this.d != null) {
            return this.d.h();
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

    public void q() {
        if (this.d != null) {
            this.d.i();
        }
    }

    public void setOnDanmakuClickListener(f.a aVar) {
        this.g = aVar;
    }

    public f.a getOnDanmakuClickListener() {
        return this.g;
    }

    public float getXOff() {
        return this.h;
    }

    public float getYOff() {
        return this.i;
    }
}
