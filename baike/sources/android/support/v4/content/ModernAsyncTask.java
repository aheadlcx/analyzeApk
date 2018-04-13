package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class ModernAsyncTask<Params, Progress, Result> {
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, b, a);
    private static final ThreadFactory a = new b();
    private static final BlockingQueue<Runnable> b = new LinkedBlockingQueue(10);
    private static b c;
    private static volatile Executor d = THREAD_POOL_EXECUTOR;
    private final c<Params, Result> e = new c(this);
    private final FutureTask<Result> f = new d(this, this.e);
    private volatile Status g = Status.PENDING;
    private final AtomicBoolean h = new AtomicBoolean();
    private final AtomicBoolean i = new AtomicBoolean();

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static class a<Data> {
        final ModernAsyncTask a;
        final Data[] b;

        a(ModernAsyncTask modernAsyncTask, Data... dataArr) {
            this.a = modernAsyncTask;
            this.b = dataArr;
        }
    }

    private static class b extends Handler {
        b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.a.e(aVar.b[0]);
                    return;
                case 2:
                    aVar.a.b(aVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static abstract class c<Params, Result> implements Callable<Result> {
        Params[] b;

        c() {
        }
    }

    protected abstract Result a(Params... paramsArr);

    private static Handler c() {
        Handler handler;
        synchronized (ModernAsyncTask.class) {
            if (c == null) {
                c = new b();
            }
            handler = c;
        }
        return handler;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void setDefaultExecutor(Executor executor) {
        d = executor;
    }

    ModernAsyncTask() {
    }

    void c(Result result) {
        if (!this.i.get()) {
            d(result);
        }
    }

    Result d(Result result) {
        c().obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    public final Status getStatus() {
        return this.g;
    }

    protected void a() {
    }

    protected void a(Result result) {
    }

    protected void b(Progress... progressArr) {
    }

    protected void b(Result result) {
        b();
    }

    protected void b() {
    }

    public final boolean isCancelled() {
        return this.h.get();
    }

    public final boolean cancel(boolean z) {
        this.h.set(true);
        return this.f.cancel(z);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.f.get();
    }

    public final Result get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.f.get(j, timeUnit);
    }

    public final ModernAsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(d, paramsArr);
    }

    public final ModernAsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.g != Status.PENDING) {
            switch (e.a[this.g.ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case 2:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                default:
                    throw new IllegalStateException("We should never reach this state");
            }
        }
        this.g = Status.RUNNING;
        a();
        this.e.b = paramsArr;
        executor.execute(this.f);
        return this;
    }

    public static void execute(Runnable runnable) {
        d.execute(runnable);
    }

    void e(Result result) {
        if (isCancelled()) {
            b((Object) result);
        } else {
            a((Object) result);
        }
        this.g = Status.FINISHED;
    }
}
