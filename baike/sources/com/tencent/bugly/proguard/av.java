package com.tencent.bugly.proguard;

import java.util.concurrent.LinkedBlockingQueue;

final class av implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ LinkedBlockingQueue b;

    av(u uVar, int i, LinkedBlockingQueue linkedBlockingQueue) {
        this.a = i;
        this.b = linkedBlockingQueue;
    }

    public final void run() {
        int i = 0;
        while (i < this.a) {
            Runnable runnable = (Runnable) this.b.poll();
            if (runnable != null) {
                runnable.run();
                i++;
            } else {
                return;
            }
        }
    }
}
