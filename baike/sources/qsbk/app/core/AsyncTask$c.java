package qsbk.app.core;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

class AsyncTask$c implements Executor {
    final ArrayDeque<Runnable> a;
    Runnable b;

    private AsyncTask$c() {
        this.a = new ArrayDeque();
    }

    public synchronized void execute(Runnable runnable) {
        this.a.offer(new f(this, runnable));
        if (this.b == null) {
            a();
        }
    }

    protected synchronized void a() {
        Runnable runnable = (Runnable) this.a.poll();
        this.b = runnable;
        if (runnable != null) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(this.b);
        }
    }
}
