package net.tsz.afinal.core;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AsyncTask<Params, Progress, Result> {
    public static final Executor a = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, e, d, new DiscardOldestPolicy());
    public static final Executor b = new c();
    public static final Executor c = Executors.newFixedThreadPool(3, d);
    private static final ThreadFactory d = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
            thread.setPriority(4);
            return thread;
        }
    };
    private static final BlockingQueue<Runnable> e = new LinkedBlockingQueue(10);
    private static final b f = new b();
    private static volatile Executor g = b;
    private static /* synthetic */ int[] m;
    private final d<Params, Result> h = new d<Params, Result>(this) {
        final /* synthetic */ AsyncTask a;

        {
            this.a = r2;
        }

        public Result call() throws Exception {
            this.a.l.set(true);
            Process.setThreadPriority(10);
            return this.a.d(this.a.a(this.b));
        }
    };
    private final FutureTask<Result> i = new FutureTask<Result>(this, this.h) {
        final /* synthetic */ AsyncTask a;

        protected void done() {
            try {
                this.a.c(get());
            } catch (Throwable e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.a.c(null);
            }
        }
    };
    private volatile Status j = Status.PENDING;
    private final AtomicBoolean k = new AtomicBoolean();
    private final AtomicBoolean l = new AtomicBoolean();

    private static abstract class d<Params, Result> implements Callable<Result> {
        Params[] b;

        private d() {
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static class a<Data> {
        final AsyncTask a;
        final Data[] b;

        a(AsyncTask asyncTask, Data... dataArr) {
            this.a = asyncTask;
            this.b = dataArr;
        }
    }

    private static class b extends Handler {
        private b() {
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

    private static class c implements Executor {
        final ArrayDeque<Runnable> a;
        Runnable b;

        private c() {
            this.a = new ArrayDeque();
        }

        public synchronized void execute(final Runnable runnable) {
            this.a.offer(new Runnable(this) {
                final /* synthetic */ c a;

                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        this.a.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }

        protected synchronized void a() {
            Runnable runnable = (Runnable) this.a.poll();
            this.b = runnable;
            if (runnable != null) {
                AsyncTask.a.execute(this.b);
            }
        }
    }

    protected abstract Result a(Params... paramsArr);

    static /* synthetic */ int[] d() {
        int[] iArr = m;
        if (iArr == null) {
            iArr = new int[Status.values().length];
            try {
                iArr[Status.FINISHED.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Status.PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Status.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            m = iArr;
        }
        return iArr;
    }

    private void c(Result result) {
        if (!this.l.get()) {
            d(result);
        }
    }

    private Result d(Result result) {
        f.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
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

    public final boolean c() {
        return this.k.get();
    }

    public final AsyncTask<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.j != Status.PENDING) {
            switch (d()[this.j.ordinal()]) {
                case 2:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case 3:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.j = Status.RUNNING;
        a();
        this.h.b = paramsArr;
        executor.execute(this.i);
        return this;
    }

    protected final void c(Progress... progressArr) {
        if (!c()) {
            f.obtainMessage(2, new a(this, progressArr)).sendToTarget();
        }
    }

    private void e(Result result) {
        if (c()) {
            b((Object) result);
        } else {
            a((Object) result);
        }
        this.j = Status.FINISHED;
    }
}
