package cn.xiaochuankeji.tieba.common.medialib;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import cn.xiaochuankeji.tieba.c.a.g;
import cn.xiaochuankeji.tieba.c.a.h;
import cn.xiaochuankeji.tieba.c.b;
import cn.xiaochuankeji.tieba.common.medialib.d.c;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(18)
public class l implements OnFrameAvailableListener {
    private static boolean B = true;
    private static boolean C = true;
    private c A;
    private final i a;
    private final AtomicBoolean b = new AtomicBoolean(false);
    private a c;
    private volatile h d;
    private Handler e;
    private HandlerThread f;
    private Handler g;
    private HandlerThread h;
    private Handler i;
    private cn.xiaochuankeji.tieba.c.a j;
    private SurfaceTexture k;
    private int l = -1;
    private h m;
    private g n;
    private g o;
    private b p;
    private MediaExtractor q;
    private d r;
    private long s;
    private int t = -1;
    private final AtomicBoolean u = new AtomicBoolean(false);
    private e v;
    private String w;
    private final AtomicBoolean x = new AtomicBoolean(false);
    private final Object y = new Object();
    private final Queue<c> z = new LinkedList();

    public interface a {
        void a();

        void a(int i);

        void a(String str);
    }

    public l(i iVar) {
        this.a = iVar;
        this.e = new Handler(Looper.getMainLooper(), new Callback(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 100) {
                    if (this.a.c != null) {
                        this.a.c.a(message.arg1);
                    }
                } else if (message.what == 101) {
                    if (this.a.c != null) {
                        this.a.c.a(100);
                        this.a.c.a((String) message.obj);
                    }
                } else if (message.what == 102 && this.a.c != null) {
                    this.a.c.a();
                }
                return true;
            }
        });
        this.f = new HandlerThread("VideoDecodeThread");
        this.f.start();
        this.g = new Handler(this.f.getLooper());
        this.h = new HandlerThread("VideoEncodeThread");
        this.h.start();
        this.i = new Handler(this.h.getLooper());
        this.n = new g();
        this.o = new g();
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(h hVar) {
        if (this.b.get()) {
            throw new IllegalStateException("Already started.");
        }
        this.d = hVar;
    }

    public void a(final String str, final String str2) {
        if (!this.b.get()) {
            if (B && C) {
                this.b.set(true);
                this.i.post(new Runnable(this) {
                    final /* synthetic */ l b;

                    public void run() {
                        try {
                            this.b.b(str2);
                        } catch (Throwable th) {
                            th.printStackTrace();
                            this.b.k();
                            this.b.x.set(true);
                            l.B = false;
                        }
                    }
                });
                this.g.post(new Runnable(this) {
                    final /* synthetic */ l b;

                    public void run() {
                        do {
                        } while (!this.b.x.get());
                        this.b.u.set(false);
                        if (l.B) {
                            try {
                                this.b.a(str);
                            } catch (Throwable th) {
                                this.b.j();
                                th.printStackTrace();
                                l.C = false;
                            }
                            if (l.C) {
                                this.b.g();
                                return;
                            } else {
                                this.b.f();
                                return;
                            }
                        }
                        this.b.f();
                    }
                });
                return;
            }
            f();
        }
    }

    private void f() {
        this.e.sendEmptyMessage(102);
    }

    private void a(String str) throws IOException {
        this.q = new MediaExtractor();
        this.q.setDataSource(str);
        int trackCount = this.q.getTrackCount();
        MediaFormat mediaFormat = null;
        for (int i = 0; i < trackCount; i++) {
            mediaFormat = this.q.getTrackFormat(i);
            if (mediaFormat.getString(IMediaFormat.KEY_MIME).startsWith("video/")) {
                this.q.selectTrack(i);
                break;
            }
        }
        this.s = mediaFormat.getLong("durationUs");
        mediaFormat.setInteger("profile", 1);
        mediaFormat.setInteger("level", 1);
        Surface surface = new Surface(this.k);
        this.r = new d();
        if (!this.r.a(mediaFormat, surface)) {
            throw new RuntimeException("hw decoder init failed.");
        }
    }

    private void b(String str) throws IOException {
        this.w = str;
        this.v = new e(this.a);
        this.v.a();
        this.v.b(str);
        this.j = cn.xiaochuankeji.tieba.c.a.a(null, cn.xiaochuankeji.tieba.c.a.b);
        this.j.a(this.v.d());
        this.p = new b();
        this.m = new h();
        this.j.c();
        this.l = cn.xiaochuankeji.tieba.c.c.a(36197);
        this.k = new SurfaceTexture(this.l);
        this.k.setOnFrameAvailableListener(this);
        this.m.a();
        this.m.a(this.a.a, this.a.b);
        this.n.a();
        this.n.a(this.a.a, this.a.b);
        this.x.set(true);
    }

    private void g() {
        while (!this.u.get()) {
            d.b b = this.r.b();
            if (b == null || b.a < 0) {
                c(false);
            } else {
                int readSampleData = this.q.readSampleData(b.b, 0);
                if (readSampleData < 0) {
                    a(b.a);
                    return;
                }
                long sampleTime = this.q.getSampleTime();
                this.r.a(b.a, readSampleData, sampleTime);
                int i = (int) ((100 * sampleTime) / this.s);
                if (i > this.t) {
                    this.e.sendMessage(this.e.obtainMessage(100, i, 0));
                    this.t = i;
                }
                this.q.advance();
            }
        }
    }

    private void c(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            h();
            c c = this.r.c();
            if (c.a >= 0) {
                this.z.add(c);
            }
            h();
            if (z) {
                if ((c.a >= 0 && c.b.flags == 4) || SystemClock.elapsedRealtime() - elapsedRealtime >= 1000) {
                    return;
                }
            } else if (c.a < 0) {
                return;
            }
        }
    }

    private void h() {
        if (!this.z.isEmpty() && !c()) {
            c cVar = (c) this.z.remove();
            a(cVar);
            this.r.b(cVar.a);
        }
    }

    private void a(int i) {
        this.r.a(i);
        c(true);
        while (!this.z.isEmpty()) {
            h();
        }
        j();
        this.i.post(new Runnable(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.i();
            }
        });
    }

    private void i() {
        String str = this.w;
        k();
        this.b.set(false);
        if (C && C) {
            this.e.sendMessage(this.e.obtainMessage(101, str));
        }
    }

    private void a(int i, float[] fArr, long j) {
        b(i, fArr, j);
        this.v.a(false);
    }

    private boolean b(int i, float[] fArr, long j) {
        try {
            this.j.c();
            GLES20.glClear(16384);
            this.p.a(a(this.m.a(i, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.c, fArr, cn.xiaochuankeji.tieba.c.c.d), (int) (j / 1000000)), cn.xiaochuankeji.tieba.c.c.a, 0, 0, this.a.a, this.a.b);
            this.j.a(j);
            return true;
        } catch (Throwable e) {
            Log.e("VideoProcessor", "encodeTexture failed", e);
            return false;
        }
    }

    private int a(int i, int i2) {
        if (this.d == null) {
            return i;
        }
        this.o.a = null;
        long j = this.o.b;
        this.d.a(i2, this.o);
        if (this.o.b > j) {
            this.n.a(this.o.a, false);
        }
        return this.n.a(i, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.c, cn.xiaochuankeji.tieba.c.c.a, cn.xiaochuankeji.tieba.c.c.d);
    }

    public void a() {
        if (this.b.get()) {
            Log.d("VideoProcessor", "stop");
            this.u.set(true);
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            this.g.post(new Runnable(this) {
                final /* synthetic */ l b;

                public void run() {
                    this.b.j();
                    countDownLatch.countDown();
                }
            });
            this.i.post(new Runnable(this) {
                final /* synthetic */ l b;

                public void run() {
                    this.b.k();
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.b.set(false);
            Log.d("VideoProcessor", "stop done");
        }
    }

    private void j() {
        Log.d("VideoProcessor", "releaseDecoder");
        if (this.q != null) {
            this.q.release();
            this.q = null;
        }
        if (this.r != null) {
            this.r.a();
            this.r = null;
        }
        this.s = 0;
        this.t = -1;
    }

    private void k() {
        Log.d("VideoProcessor", "releaseHwEncoder");
        if (this.j != null) {
            this.j.c();
            GLES20.glDeleteTextures(1, new int[]{this.l}, 0);
            this.j.b();
            this.j = null;
        }
        this.l = -1;
        if (this.v != null) {
            this.v.a(true, 1000);
            this.v.e();
            this.v.f();
            this.v = null;
        }
        if (this.k != null) {
            this.k.release();
            this.k = null;
        }
        if (this.m != null) {
            this.m.g();
            this.m = null;
        }
        this.n.g();
        this.o = null;
        if (this.p != null) {
            this.p.a();
            this.p = null;
        }
        if (!C) {
            f();
        }
    }

    public void b() {
        if (this.b.get()) {
            a();
        }
        this.i.removeCallbacksAndMessages(null);
        this.h.getLooper().quit();
        this.g.removeCallbacksAndMessages(null);
        this.f.getLooper().quit();
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this.y) {
            this.k.updateTexImage();
            float[] fArr = new float[16];
            this.k.getTransformMatrix(fArr);
            a(this.l, fArr, TimeUnit.MICROSECONDS.toNanos(this.A.b.presentationTimeUs));
            this.A = null;
        }
    }

    private void a(c cVar) {
        synchronized (this.y) {
            if (this.A != null) {
                throw new IllegalStateException("Waiting for a texture.");
            }
            this.A = cVar;
        }
    }

    public boolean c() {
        boolean z;
        synchronized (this.y) {
            z = this.A != null;
        }
        return z;
    }
}
