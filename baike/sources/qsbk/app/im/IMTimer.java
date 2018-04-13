package qsbk.app.im;

import android.os.SystemClock;

public class IMTimer {
    private static IMTimer a;
    private static byte[] b = new byte[0];
    private long c = -1;
    private long d = -1;

    private IMTimer() {
    }

    public static IMTimer getInstance() {
        IMTimer iMTimer;
        synchronized (IMTimer.class) {
            if (a == null) {
                a = new IMTimer();
            }
            iMTimer = a;
        }
        return iMTimer;
    }

    public void correctTime(long j) {
        synchronized (b) {
            this.d = j;
            this.c = SystemClock.elapsedRealtime();
        }
    }

    public long getCorrectTime() {
        long currentTimeMillis;
        synchronized (b) {
            if (this.d == -1) {
                currentTimeMillis = System.currentTimeMillis();
            } else {
                currentTimeMillis = this.d + (SystemClock.elapsedRealtime() - this.c);
            }
        }
        return currentTimeMillis;
    }
}
