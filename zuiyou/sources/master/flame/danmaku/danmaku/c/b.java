package master.flame.danmaku.danmaku.c;

import android.os.SystemClock;

public class b {
    public static final long a() {
        return SystemClock.elapsedRealtime();
    }

    public static final void a(long j) {
        SystemClock.sleep(j);
    }
}
