package qsbk.app.utils;

import android.os.SystemClock;

public class TimeDelta {
    public long start = SystemClock.elapsedRealtime();

    public long getDelta() {
        return SystemClock.elapsedRealtime() - this.start;
    }

    public void renew() {
        this.start = SystemClock.elapsedRealtime();
    }
}
