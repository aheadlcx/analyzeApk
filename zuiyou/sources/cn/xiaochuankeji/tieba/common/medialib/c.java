package cn.xiaochuankeji.tieba.common.medialib;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import cn.xiaochuan.media.av.AVFileWriter;
import cn.xiaochuan.media.av.VideoMediaType;
import cn.xiaochuankeji.tieba.c.a.k.a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class c {
    private final i a;
    private HandlerThread b;
    private Handler c;
    private AVFileWriter d;
    private long e;
    private long f;
    private int g;

    public c(i iVar) {
        this.a = iVar;
    }

    public void a() {
        this.b = new HandlerThread("FFmpegEncodeThread");
        this.b.start();
        this.c = new Handler(this.b.getLooper(), new Callback(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                if (message.what == 100) {
                    this.a.a(message.arg1, (a) message.obj);
                }
                return true;
            }
        });
    }

    public i b() {
        return this.a;
    }

    public void a(String str) {
        this.d = new AVFileWriter();
        this.d.initialize();
        VideoMediaType videoMediaType = new VideoMediaType();
        videoMediaType.setWidth(this.a.d);
        videoMediaType.setHeight(this.a.e);
        videoMediaType.setFrameRate(30.0f);
        videoMediaType.setType(0);
        VideoMediaType videoMediaType2 = new VideoMediaType();
        videoMediaType2.setWidth(this.a.d);
        videoMediaType2.setHeight(this.a.e);
        videoMediaType2.setFrameRate(30.0f);
        videoMediaType2.setType(101);
        this.d.setInputVideoMediaType(videoMediaType);
        this.d.setOutputVideoMediaType(videoMediaType2);
        this.d.createFile(str);
        this.e = -66666;
        this.f = -33333;
        this.g = 0;
    }

    public void a(a aVar) {
        this.c.sendMessage(this.c.obtainMessage(100, (int) aVar.b(), 0, aVar));
    }

    private void a(int i, a aVar) {
        if (aVar.c()) {
            if (((long) i) == aVar.b()) {
                byte[] a = aVar.a();
                this.e = this.f;
                this.f = TimeUnit.MILLISECONDS.toMicros(aVar.b());
                this.d.writeSampleArray(2, this.f, 0, a, (long) a.length);
                this.g++;
            }
            aVar.d();
        }
    }

    public k c() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.c.post(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.d.closeFile();
        this.d.release();
        this.d = null;
        return new k((int) (((2 * this.f) - this.e) / 1000), this.g);
    }

    public void d() {
        this.c.removeCallbacksAndMessages(null);
        this.b.getLooper().quit();
    }
}
