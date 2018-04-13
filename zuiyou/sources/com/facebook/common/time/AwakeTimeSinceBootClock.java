package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.d;

@d
public class AwakeTimeSinceBootClock implements b {
    @d
    private static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    private AwakeTimeSinceBootClock() {
    }

    @d
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @d
    public long now() {
        return SystemClock.uptimeMillis();
    }
}
