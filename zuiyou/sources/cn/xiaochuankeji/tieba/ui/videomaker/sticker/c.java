package cn.xiaochuankeji.tieba.ui.videomaker.sticker;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.SystemClock;
import java.util.concurrent.atomic.AtomicInteger;

public class c {
    private Bitmap a;
    private volatile long b;
    private AtomicInteger c;

    c(int i, int i2, float f) {
        float f2 = (((float) i) * 1.0f) / ((float) i2);
        if (f != 0.0f) {
            if (f >= f2) {
                i = (int) (((float) i2) * f);
            } else {
                i2 = (int) (((float) i) / f);
            }
        }
        this.a = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        this.c = new AtomicInteger(0);
    }

    public Bitmap a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public void c() {
        this.b = SystemClock.elapsedRealtime();
    }

    public boolean d() {
        return this.c.compareAndSet(0, 1);
    }

    public boolean e() {
        return this.c.compareAndSet(0, 2);
    }

    public void f() {
        this.c.set(0);
    }
}
