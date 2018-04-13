package com.budejie.www.activity.video.barrage.ui.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
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

@SuppressLint({"NewApi"})
public class DanmakuTextureView extends TextureView implements SurfaceTextureListener, f, g {
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
    private LinkedList<Long> k;

    @TargetApi(11)
    private void k() {
        setLayerType(2, null);
        setOpaque(false);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        setSurfaceTextureListener(this);
        d.a(true, true);
        this.h = a.a((f) this);
    }

    public DanmakuTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        k();
    }

    public DanmakuTextureView(Context context, AttributeSet attributeSet, int i) {
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

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.e = true;
    }

    public synchronized boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.e = false;
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.d != null) {
            this.d.a(i, i2);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void c() {
        j();
        if (this.k != null) {
            this.k.clear();
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
                this.c.join();
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
        this.k.addLast(Long.valueOf(currentTimeMillis));
        float longValue = (float) (currentTimeMillis - ((Long) this.k.getFirst()).longValue());
        if (this.k.size() > 50) {
            this.k.removeFirst();
        }
        return longValue > 0.0f ? ((float) (this.k.size() * 1000)) / longValue : 0.0f;
    }

    public synchronized long g() {
        long currentTimeMillis;
        if (this.e) {
            currentTimeMillis = System.currentTimeMillis();
            if (isShown()) {
                Canvas lockCanvas = lockCanvas();
                if (lockCanvas != null) {
                    if (this.d != null) {
                        com.budejie.www.activity.video.barrage.danmaku.b.a.a a = this.d.a(lockCanvas);
                        if (this.i) {
                            if (this.k == null) {
                                this.k = new LinkedList();
                            }
                            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                            d.a(lockCanvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(n()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a.m), Long.valueOf(a.n)}));
                        }
                    }
                    if (this.e) {
                        unlockCanvasAndPost(lockCanvas);
                    }
                }
                currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
            } else {
                currentTimeMillis = -1;
            }
        } else {
            currentTimeMillis = 0;
        }
        return currentTimeMillis;
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
            this.h.a(motionEvent);
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

    public void setOnDanmakuClickListener(f$a f_a) {
        this.g = f_a;
        setClickable(f_a != null);
    }

    public f$a getOnDanmakuClickListener() {
        return this.g;
    }

    public synchronized void h() {
        if (f()) {
            Canvas lockCanvas = lockCanvas();
            if (lockCanvas != null) {
                d.a(lockCanvas);
                unlockCanvasAndPost(lockCanvas);
            }
        }
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

    public boolean isHardwareAccelerated() {
        return false;
    }
}
