package com.facebook.common.b;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class e extends AbstractExecutorService implements d {
    private final Handler a;

    protected /* synthetic */ RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return a(runnable, obj);
    }

    protected /* synthetic */ RunnableFuture newTaskFor(Callable callable) {
        return a(callable);
    }

    public /* synthetic */ Future submit(Runnable runnable) {
        return a(runnable);
    }

    public /* synthetic */ Future submit(Runnable runnable, @Nullable Object obj) {
        return b(runnable, obj);
    }

    public /* synthetic */ Future submit(Callable callable) {
        return b(callable);
    }

    public e(Handler handler) {
        this.a = handler;
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    public void execute(Runnable runnable) {
        this.a.post(runnable);
    }

    protected <T> f<T> a(Runnable runnable, T t) {
        return new f(this.a, runnable, t);
    }

    protected <T> f<T> a(Callable<T> callable) {
        return new f(this.a, callable);
    }

    public ScheduledFuture<?> a(Runnable runnable) {
        return b(runnable, (Void) null);
    }

    public <T> ScheduledFuture<T> b(Runnable runnable, @Nullable T t) {
        if (runnable == null) {
            throw new NullPointerException();
        }
        Object a = a(runnable, t);
        execute(a);
        return a;
    }

    public <T> ScheduledFuture<T> b(Callable<T> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        Object a = a((Callable) callable);
        execute(a);
        return a;
    }

    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        Object a = a(runnable, null);
        this.a.postDelayed(a, timeUnit.toMillis(j));
        return a;
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        Object a = a((Callable) callable);
        this.a.postDelayed(a, timeUnit.toMillis(j));
        return a;
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public boolean a() {
        return Thread.currentThread() == this.a.getLooper().getThread();
    }
}
