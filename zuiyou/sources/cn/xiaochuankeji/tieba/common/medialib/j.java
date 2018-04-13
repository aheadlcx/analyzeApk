package cn.xiaochuankeji.tieba.common.medialib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class j {
    private final String a;
    private HandlerThread b = new HandlerThread("VideoFrameLoaderThread");
    private Handler c;
    private Handler d;
    private FFmpegMediaMetadataRetriever e;
    private volatile h f;
    private b g;
    private int h;

    public interface a {
        void a(int i, Bitmap bitmap);
    }

    public interface b {
        void a(j jVar);
    }

    private static class c {
        int a;
        Bitmap b;
        a c;

        c(int i, Bitmap bitmap, a aVar) {
            this.a = i;
            this.b = bitmap;
            this.c = aVar;
        }
    }

    public j(String str) {
        this.a = str;
        this.b.start();
        this.c = new Handler(this.b.getLooper(), new Callback(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 200) {
                    this.a.b(message.arg1, message.arg2, (a) message.obj);
                }
                return true;
            }
        });
        this.d = new Handler(Looper.getMainLooper(), new Callback(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 100) {
                    this.a.h = message.arg1;
                    if (this.a.g != null) {
                        this.a.g.a(this.a);
                    }
                } else if (message.what == 101) {
                    c cVar = (c) message.obj;
                    if (cVar.c != null) {
                        cVar.c.a(cVar.a, cVar.b);
                    }
                }
                return true;
            }
        });
    }

    public void a(h hVar) {
        this.f = hVar;
    }

    public void a(b bVar) {
        this.g = bVar;
        this.c.post(new Runnable(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d.sendMessage(this.a.d.obtainMessage(100, this.a.c(), 0));
            }
        });
    }

    public int a() {
        return this.h;
    }

    private int c() {
        try {
            this.e = new FFmpegMediaMetadataRetriever();
            this.e.setDataSource(this.a);
            return Integer.parseInt(this.e.extractMetadata("duration"));
        } catch (Exception e) {
            e.printStackTrace();
            if (this.e != null) {
                this.e.release();
            }
            return -1;
        }
    }

    public void a(int i, int i2, a aVar) {
        a(i, i2, false, aVar);
    }

    public void a(int i, int i2, boolean z, a aVar) {
        if (z) {
            this.c.removeMessages(200);
        }
        this.c.sendMessage(this.c.obtainMessage(200, i, i2, aVar));
    }

    private void b(int i, int i2, a aVar) {
        Bitmap a;
        g gVar;
        Bitmap frameAtTime = this.e.getFrameAtTime((long) (i * 1000), 3);
        if (i2 > 0 && frameAtTime != null) {
            a = a(frameAtTime, i2);
            if (a != null) {
                if (!frameAtTime.isRecycled()) {
                    frameAtTime.recycle();
                }
                if (!(a == null || this.f == null)) {
                    gVar = new g();
                    this.f.a(i, gVar);
                    new Canvas(a).drawBitmap(gVar.a, new Rect(0, 0, gVar.a.getWidth(), gVar.a.getHeight()), new Rect(0, 0, a.getWidth(), a.getHeight()), null);
                }
                this.d.sendMessage(this.d.obtainMessage(101, new c(i, a, aVar)));
            }
        }
        a = frameAtTime;
        gVar = new g();
        this.f.a(i, gVar);
        new Canvas(a).drawBitmap(gVar.a, new Rect(0, 0, gVar.a.getWidth(), gVar.a.getHeight()), new Rect(0, 0, a.getWidth(), a.getHeight()), null);
        this.d.sendMessage(this.d.obtainMessage(101, new c(i, a, aVar)));
    }

    private Bitmap a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        float width = (((float) i) * 1.0f) / ((float) bitmap.getWidth());
        matrix.postScale(width, width);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void b() {
        Log.d("VideoFrameLoader", "release");
        this.c.removeCallbacksAndMessages(null);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.c.post(new Runnable(this) {
            final /* synthetic */ j b;

            public void run() {
                if (this.b.e != null) {
                    this.b.e.release();
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.b.getLooper().quit();
        Log.d("VideoFrameLoader", "release done");
    }
}
