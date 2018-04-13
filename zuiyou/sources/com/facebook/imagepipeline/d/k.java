package com.facebook.imagepipeline.d;

import android.os.Process;
import java.util.concurrent.ThreadFactory;

public class k implements ThreadFactory {
    private final int a;

    public k(int i) {
        this.a = i;
    }

    public Thread newThread(final Runnable runnable) {
        return new Thread(new Runnable(this) {
            final /* synthetic */ k b;

            public void run() {
                try {
                    Process.setThreadPriority(this.b.a);
                } catch (Throwable th) {
                }
                runnable.run();
            }
        });
    }
}
