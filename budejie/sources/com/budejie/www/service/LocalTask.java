package com.budejie.www.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class LocalTask<Params, Progress, Result> {
    private static final b a = new b();
    private static volatile Executor b;
    private final c<Params, Result> c;
    private final FutureTask<Result> d;
    private volatile Status e = Status.PENDING;
    private final AtomicBoolean f = new AtomicBoolean();

    private static abstract class c<Params, Result> implements Callable<Result> {
        Params[] b;

        private c() {
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static class a<Data> {
        final LocalTask a;
        final Data[] b;

        a(LocalTask localTask, Data... dataArr) {
            this.a = localTask;
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
                    aVar.a.d(aVar.b[0]);
                    return;
                case 2:
                    aVar.a.b(aVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    protected abstract Result a(Params... paramsArr);

    public LocalTask() {
        if (b == null) {
            b = Executors.newFixedThreadPool(1);
        }
        this.c = new c<Params, Result>(this) {
            final /* synthetic */ LocalTask a;

            {
                this.a = r2;
            }

            public Result call() throws Exception {
                this.a.f.set(true);
                return this.a.c(this.a.a(this.b));
            }
        };
        this.d = new FutureTask<Result>(this, this.c) {
            final /* synthetic */ LocalTask a;

            protected void done() {
                try {
                    this.a.b(get());
                } catch (Throwable e) {
                    Log.w("ImageLocalTask", e);
                } catch (ExecutionException e2) {
                    throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
                } catch (CancellationException e3) {
                } catch (Throwable e4) {
                    RuntimeException runtimeException = new RuntimeException("An error occured while executing doInBackground()", e4);
                }
            }
        };
    }

    private void b(Result result) {
        if (!this.f.get()) {
            c((Object) result);
        }
    }

    private Result c(Result result) {
        a.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    protected void a() {
    }

    protected void a(Result result) {
    }

    protected void b(Progress... progressArr) {
    }

    protected boolean b() {
        return this.d.isCancelled();
    }

    protected final LocalTask<Params, Progress, Result> c(Params... paramsArr) {
        return a(b, (Object[]) paramsArr);
    }

    protected static void c() {
        if (b != null) {
            ((ExecutorService) b).shutdownNow();
            b = null;
        }
    }

    protected final LocalTask<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (executor != null) {
            if (this.e != Status.PENDING) {
                switch (this.e) {
                    case RUNNING:
                        throw new IllegalStateException("Cannot execute task: the task is already running.");
                    case FINISHED:
                        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                }
            }
            this.e = Status.RUNNING;
            a();
            this.c.b = paramsArr;
            executor.execute(this.d);
        }
        return this;
    }

    protected final void d(Progress... progressArr) {
        if (!b()) {
            a.obtainMessage(2, new a(this, progressArr)).sendToTarget();
        }
    }

    private void d(Result result) {
        a((Object) result);
        this.e = Status.FINISHED;
    }
}
