package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.g;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

public class as {
    private boolean a = false;
    private final Deque<Runnable> b;
    private final Executor c;

    public as(Executor executor) {
        this.c = (Executor) g.a((Object) executor);
        this.b = new ArrayDeque();
    }

    public synchronized void a(Runnable runnable) {
        if (this.a) {
            this.b.add(runnable);
        } else {
            this.c.execute(runnable);
        }
    }

    public synchronized void b(Runnable runnable) {
        this.b.remove(runnable);
    }
}
