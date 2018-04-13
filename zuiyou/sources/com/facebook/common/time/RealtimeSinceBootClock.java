package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.d;

@d
public class RealtimeSinceBootClock implements b {
    private static final RealtimeSinceBootClock a = new RealtimeSinceBootClock();

    private RealtimeSinceBootClock() {
    }

    @d
    public static RealtimeSinceBootClock get() {
        return a;
    }

    public long now() {
        return SystemClock.elapsedRealtime();
    }
}
