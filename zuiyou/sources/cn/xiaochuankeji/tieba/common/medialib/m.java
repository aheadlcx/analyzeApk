package cn.xiaochuankeji.tieba.common.medialib;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import cn.xiaochuankeji.tieba.c.a.g;
import cn.xiaochuankeji.tieba.c.a.k;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(18)
public class m implements cn.xiaochuankeji.tieba.c.a.k.b {
    private static volatile boolean v = true;
    private final AtomicBoolean a;
    private volatile int b;
    private final HandlerThread c;
    private final Handler d;
    private b e;
    private a f;
    private final Handler g;
    private EGLContext h;
    private cn.xiaochuankeji.tieba.c.a i;
    private cn.xiaochuankeji.tieba.c.b j;
    private int k;
    private long l;
    private g m;
    private k n;
    private e o;
    private c p;
    private int q = 1;
    private int r = 1;
    private int s;
    private volatile long t;
    private k u;

    public interface a {
        void a(Object obj);
    }

    public interface b {
        void a(int i);
    }

    private static class c {
        int a;
        long b;
        Bitmap c;
        long d;
        Object e;

        private c() {
        }
    }

    public m(Context context, i iVar) {
        this.k = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion;
        this.a = new AtomicBoolean(false);
        this.b = 0;
        this.c = new HandlerThread("VideoRecordThread");
        this.c.start();
        this.d = new Handler(this.c.getLooper(), new Callback(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 101) {
                    this.a.a((c) message.obj);
                }
                return true;
            }
        });
        this.g = new Handler(Looper.getMainLooper(), new Callback(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 100 && this.a.e != null) {
                    this.a.e.a(message.arg1);
                }
                return true;
            }
        });
        this.o = new e(iVar);
        this.p = new c(iVar);
        i();
    }

    private void i() {
        this.d.post(new Runnable(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void run() {
                if (m.v) {
                    try {
                        this.a.o.a();
                    } catch (Throwable th) {
                        th.printStackTrace();
                        this.a.j();
                    }
                }
                this.a.p.a();
                this.a.b = 0;
            }
        });
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void a(final EGLContext eGLContext) {
        this.d.post(new Runnable(this) {
            final /* synthetic */ m b;

            public void run() {
                if (this.b.b == 1 || this.b.b == 3) {
                    this.b.h = eGLContext;
                    if (m.v) {
                        this.b.b(eGLContext);
                        return;
                    } else {
                        this.b.c(eGLContext);
                        return;
                    }
                }
                throw new IllegalStateException("current state = " + this.b.b);
            }
        });
    }

    public void a() {
        this.d.post(new Runnable(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h = null;
                this.a.o();
                this.a.p();
            }
        });
    }

    public void b() {
        this.d.post(new Runnable(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.b == 0 || this.a.b == 3 || this.a.b == 4) {
                    if (m.v) {
                        try {
                            this.a.o.c();
                        } catch (Throwable th) {
                            th.printStackTrace();
                            this.a.j();
                        }
                    }
                    this.a.b = 1;
                    return;
                }
                throw new IllegalStateException("current state = " + this.a.b);
            }
        });
    }

    private void j() {
        s();
        o();
        v = false;
    }

    public boolean c() {
        return this.a.get();
    }

    public boolean a(final String str) {
        if (this.a.get()) {
            return true;
        }
        Log.d("VideoRecorder", "start");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.d.post(new Runnable(this) {
            final /* synthetic */ m c;

            public void run() {
                if (this.c.b == 1 || this.c.b == 3) {
                    this.c.l();
                    if (m.v) {
                        try {
                            this.c.b(str);
                        } catch (Throwable th) {
                            th.printStackTrace();
                            this.c.j();
                        }
                    }
                    boolean z = false;
                    if (!m.v) {
                        z = this.c.c(str);
                    }
                    if (m.v || r0) {
                        this.c.a.set(true);
                        this.c.b = 2;
                    }
                    countDownLatch.countDown();
                    return;
                }
                throw new IllegalStateException("current state = " + this.c.b);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("VideoRecorder", "start done");
        return this.a.get();
    }

    public long d() {
        return this.t;
    }

    private boolean k() {
        return this.k >= 196608;
    }

    private void b(EGLContext eGLContext) {
        if (eGLContext != null && this.o.d() != null && this.j == null) {
            this.i = cn.xiaochuankeji.tieba.c.a.a(eGLContext, cn.xiaochuankeji.tieba.c.a.b);
            this.i.a(this.o.d());
            i b = this.o.b();
            this.i.c();
            this.l = 0;
            this.m = new g();
            this.m.a();
            this.m.a(b.a, b.b);
            this.j = new cn.xiaochuankeji.tieba.c.b();
        }
    }

    private void c(EGLContext eGLContext) {
        if (k() && eGLContext != null && this.n == null) {
            i b = this.p.b();
            this.i = cn.xiaochuankeji.tieba.c.a.a(eGLContext, cn.xiaochuankeji.tieba.c.a.a);
            this.i.a(b.d, b.e);
            this.i.c();
            this.l = 0;
            this.m = new g();
            this.m.a();
            this.m.a(b.a, b.b);
            this.n = new k();
            this.n.a((cn.xiaochuankeji.tieba.c.a.k.b) this);
            this.n.a();
            this.n.a(b.d, b.e);
        }
    }

    private void l() {
        this.s = 0;
        this.t = 0;
    }

    private void b(String str) throws IOException {
        this.o.b(str);
        b(this.h);
    }

    private boolean c(String str) {
        if (!k()) {
            return false;
        }
        c(this.h);
        this.n.j();
        this.p.a(str);
        return true;
    }

    public void a(final int i, final int i2) {
        if (!this.a.get()) {
            this.d.post(new Runnable(this) {
                final /* synthetic */ m c;

                public void run() {
                    this.c.q = i;
                    this.c.r = i2;
                }
            });
        }
    }

    public void a(int i, long j, Bitmap bitmap, long j2, Object obj) {
        if (this.a.get()) {
            c cVar = new c();
            cVar.a = i;
            cVar.b = j;
            cVar.c = bitmap;
            cVar.d = j2;
            cVar.e = obj;
            this.d.sendMessage(this.d.obtainMessage(101, cVar));
            return;
        }
        a(obj);
    }

    private void a(c cVar) {
        if (this.a.get()) {
            if (this.t == 0) {
                this.t = cVar.b;
            }
            long j = cVar.b - this.t;
            long j2 = -1;
            if (this.q <= 1) {
                j2 = ((long) this.r) * j;
            } else if (this.s % this.q == 0) {
                j2 = j / ((long) this.q);
            }
            Object obj = null;
            if (j2 >= 0) {
                if (v) {
                    a(cVar.a, j2, cVar.c, cVar.d);
                    obj = 1;
                } else {
                    b(cVar.a, j2, cVar.c, cVar.d);
                    int i = 2;
                }
                a(j2);
            }
            a(cVar.e);
            if (obj == 1) {
                m();
            } else if (obj == 2) {
                n();
            }
            this.s++;
            return;
        }
        a(cVar.e);
    }

    private void a(Object obj) {
        if (this.f != null) {
            this.f.a(obj);
        }
    }

    private boolean a(int i, long j, Bitmap bitmap, long j2) {
        try {
            this.i.c();
            GLES20.glClear(16384);
            int a = a(i, bitmap, j2);
            i b = this.o.b();
            this.j.a(a, cn.xiaochuankeji.tieba.c.c.a, 0, 0, b.a, b.b);
            this.i.a(TimeUnit.MILLISECONDS.toNanos(j));
            return true;
        } catch (Throwable e) {
            Log.e("VideoRecorder", "drawTextureInHwMode failed", e);
            return false;
        }
    }

    private boolean b(int i, long j, Bitmap bitmap, long j2) {
        if (!k()) {
            return false;
        }
        try {
            this.i.c();
            GLES20.glClear(16384);
            int a = a(i, bitmap, j2);
            this.n.a(j);
            this.n.a(a, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.c, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.d);
            this.i.e();
            return true;
        } catch (Throwable e) {
            Log.e("VideoRecorder", "drawTextureInSoftMode failed", e);
            return false;
        }
    }

    private int a(int i, Bitmap bitmap, long j) {
        if (bitmap == null) {
            return i;
        }
        if (j > this.l) {
            this.l = j;
            this.m.a(bitmap, false);
        }
        return this.m.a(i, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.c, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.d);
    }

    public void a(cn.xiaochuankeji.tieba.c.a.k.a aVar) {
        this.p.a(aVar);
    }

    private void m() {
        if (v) {
            this.o.a(false);
        }
    }

    private void n() {
        if (k()) {
            this.n.l();
        }
    }

    private void a(long j) {
        this.g.sendMessage(this.g.obtainMessage(100, (int) j, 0));
    }

    public k e() {
        if (!this.a.get()) {
            return new k();
        }
        Log.d("VideoRecorder", "stop");
        this.a.set(false);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.d.post(new Runnable(this) {
            final /* synthetic */ m b;

            public void run() {
                if (this.b.b != 2) {
                    throw new IllegalStateException("current state = " + this.b.b);
                }
                if (m.v) {
                    this.b.u = this.b.q();
                    this.b.o();
                } else {
                    this.b.u = this.b.r();
                }
                this.b.b = 3;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("VideoRecorder", "stop done");
        return this.u;
    }

    private void o() {
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        this.l = 0;
        if (this.m != null) {
            this.m.g();
            this.m = null;
        }
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
    }

    private void p() {
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        this.l = 0;
        if (this.m != null) {
            this.m.g();
            this.m = null;
        }
        if (this.n != null) {
            this.n.g();
            this.n = null;
        }
    }

    private k q() {
        this.o.a(true);
        return this.o.e();
    }

    private k r() {
        if (!k()) {
            return new k();
        }
        this.n.k();
        return this.p.c();
    }

    private void s() {
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
    }

    private void t() {
        if (this.p != null) {
            this.p.d();
            this.p = null;
        }
    }

    public void f() {
        Log.d("VideoRecorder", "release");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.d.post(new Runnable(this) {
            final /* synthetic */ m b;

            public void run() {
                if (this.b.b == 2) {
                    throw new IllegalStateException("current state = " + this.b.b);
                }
                this.b.s();
                this.b.t();
                this.b.b = 4;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.d.removeCallbacksAndMessages(null);
        this.c.getLooper().quit();
        Log.d("VideoRecorder", "release done");
    }

    public static boolean g() {
        return v;
    }

    public static void a(boolean z) {
        v = z;
    }
}
