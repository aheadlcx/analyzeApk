package qsbk.app;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CountDownLatch;

public class DispatchQueue extends Thread {
    private volatile Handler a = null;
    private CountDownLatch b = new CountDownLatch(1);

    public DispatchQueue(String str) {
        setName(str);
        start();
    }

    public void cancelRunnable(Runnable runnable) {
        try {
            this.b.await();
            this.a.removeCallbacks(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postRunnable(Runnable runnable) {
        postRunnable(runnable, 0);
    }

    public void postRunnable(Runnable runnable, long j) {
        try {
            this.b.await();
            if (j <= 0) {
                this.a.post(runnable);
            } else {
                this.a.postDelayed(runnable, j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanupQueue() {
        try {
            this.b.await();
            this.a.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Looper.prepare();
        this.a = new Handler();
        this.b.countDown();
        Looper.loop();
    }
}
