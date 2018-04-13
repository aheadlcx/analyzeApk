package cn.v6.sixrooms.room.utils;

import android.os.Handler;
import android.os.HandlerThread;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GiftAnimQueue {
    private static final String a = GiftAnimQueue.class.getSimpleName();
    private List<Gift> b;
    private Gift c;
    private Callback d;
    private Handler e;
    private HandlerThread f;
    private boolean g;
    private Random h = new Random();

    public interface Callback {
        void showH5Gift(Gift gift);

        void showNativeGift(Gift gift);
    }

    public GiftAnimQueue(Callback callback) {
        this.d = callback;
        this.b = new ArrayList();
        this.f = new HandlerThread("TAG");
        this.f.start();
        this.e = new c(this, this.f.getLooper());
    }

    public synchronized void putGift(Gift gift) {
        try {
            Gift clone = gift.clone();
            clone.randomNum = this.h.nextLong();
            this.b.add(clone);
            if (!this.g) {
                b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void dipatchGift() {
        if (this.b.size() > 0) {
            this.e.removeCallbacksAndMessages(null);
            this.c = (Gift) this.b.remove(0);
            if (this.c.getGtype().equals("3") || this.c.getGtype().equals("4")) {
                this.d.showH5Gift(this.c);
            } else {
                this.d.showNativeGift(this.c);
            }
        }
    }

    public synchronized void setH5Disptaching() {
        this.g = true;
    }

    public synchronized void setNativeDisptaching() {
        if (!(this.c == null || this.c.getGtype().equals("3") || this.c.getGtype().equals("4"))) {
            this.g = true;
        }
    }

    public void completeH5() {
        LogUtils.e(a, "completeH5");
        a();
    }

    public void completeNative() {
        if (this.c != null && !this.c.getGtype().equals("3") && !this.c.getGtype().equals("4")) {
            LogUtils.e(a, "STATE_DRAW_END");
            a();
        }
    }

    private synchronized void a() {
        this.c = null;
        this.g = false;
        b();
    }

    private void b() {
        this.e.sendEmptyMessage(1);
    }

    public void clean() {
        if (this.b != null) {
            this.b.clear();
        }
    }

    public void onDesdory() {
        if (this.e != null) {
            this.e.removeCallbacksAndMessages(null);
        }
        if (this.f != null) {
            this.f.quit();
        }
    }
}
