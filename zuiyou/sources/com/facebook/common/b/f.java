package com.facebook.common.b;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

public class f<V> implements RunnableFuture<V>, ScheduledFuture<V> {
    private final Handler a;
    private final FutureTask<V> b;

    public /* synthetic */ int compareTo(Object obj) {
        return a((Delayed) obj);
    }

    public f(Handler handler, Callable<V> callable) {
        this.a = handler;
        this.b = new FutureTask(callable);
    }

    public f(Handler handler, Runnable runnable, @Nullable V v) {
        this.a = handler;
        this.b = new FutureTask(runnable, v);
    }

    public long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public int a(Delayed delayed) {
        throw new UnsupportedOperationException();
    }

    public void run() {
        this.b.run();
    }

    public boolean cancel(boolean z) {
        return this.b.cancel(z);
    }

    public boolean isCancelled() {
        return this.b.isCancelled();
    }

    public boolean isDone() {
        return this.b.isDone();
    }

    public V get() throws InterruptedException, ExecutionException {
        return this.b.get();
    }

    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.b.get(j, timeUnit);
    }
}
