package qsbk.app.core;

import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AsyncTask<Params, Progress, Result> {
    public static final Executor SERIAL_EXECUTOR = new AsyncTask$c(null);
    public static final Executor THREAD_POOL_EXECUTOR;
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final int b = Math.max(2, Math.min(a - 1, 4));
    private static final int c = ((a * 2) + 1);
    private static final ThreadFactory d = new b();
    private static final BlockingQueue<Runnable> e = new LinkedBlockingQueue(128);
    private static volatile Executor f = SERIAL_EXECUTOR;
    private static AsyncTask$b g;
    private final AsyncTask$d<Params, Result> h = new c(this);
    private final FutureTask<Result> i = new d(this, this.h);
    private final AtomicBoolean j = new AtomicBoolean();
    private final AtomicBoolean k = new AtomicBoolean();
    private volatile AsyncTask$Status l = AsyncTask$Status.PENDING;

    @WorkerThread
    protected abstract Result a(Params... paramsArr);

    static {
        Executor threadPoolExecutor = new ThreadPoolExecutor(b, c, 30, TimeUnit.SECONDS, e, d, new DiscardOldestPolicy());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }

    private static Handler c() {
        Handler handler;
        synchronized (AsyncTask.class) {
            if (g == null) {
                g = new AsyncTask$b();
            }
            handler = g;
        }
        return handler;
    }

    public static void setDefaultExecutor(Executor executor) {
        f = executor;
    }

    @MainThread
    public static void execute(Runnable runnable) {
        f.execute(runnable);
    }

    private void c(Result result) {
        if (!this.k.get()) {
            d((Object) result);
        }
    }

    private Result d(Result result) {
        c().obtainMessage(1, new AsyncTask$a(this, new Object[]{result})).sendToTarget();
        return result;
    }

    public final AsyncTask$Status getStatus() {
        return this.l;
    }

    @MainThread
    protected void a() {
    }

    @MainThread
    protected void a(Result result) {
    }

    @MainThread
    protected void b(Progress... progressArr) {
    }

    @MainThread
    protected void b(Result result) {
        b();
    }

    @MainThread
    protected void b() {
    }

    public final boolean isCancelled() {
        return this.j.get();
    }

    public final boolean cancel(boolean z) {
        this.j.set(true);
        return this.i.cancel(z);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.i.get();
    }

    public final Result get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.i.get(j, timeUnit);
    }

    @MainThread
    public final AsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(f, paramsArr);
    }

    @MainThread
    public final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.l != AsyncTask$Status.PENDING) {
            switch (e.a[this.l.ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case 2:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.l = AsyncTask$Status.RUNNING;
        a();
        this.h.b = paramsArr;
        executor.execute(this.i);
        return this;
    }

    @WorkerThread
    protected final void d(Progress... progressArr) {
        if (!isCancelled()) {
            c().obtainMessage(2, new AsyncTask$a(this, progressArr)).sendToTarget();
        }
    }

    private void e(Result result) {
        if (isCancelled()) {
            b((Object) result);
        } else {
            a((Object) result);
        }
        this.l = AsyncTask$Status.FINISHED;
    }
}
