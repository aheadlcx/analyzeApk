package com.crashlytics.android.internal;

import android.os.Process;

public abstract class aa implements Runnable {
    protected abstract void a();

    public final void run() {
        Process.setThreadPriority(10);
        a();
    }
}
