package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class LDNetAsyncTaskEx<Params, Progress, Result> {
    private static final b b = new b();
    private volatile Status a = Status.PENDING;
    private final c<Params, Result> c = new c<Params, Result>(this) {
        final /* synthetic */ LDNetAsyncTaskEx a;

        {
            this.a = r2;
        }

        public Result call() throws Exception {
            return this.a.a(this.b);
        }
    };
    private final FutureTask<Result> d = new FutureTask<Result>(this, this.c) {
        final /* synthetic */ LDNetAsyncTaskEx a;

        protected void done() {
            Object obj = null;
            try {
                obj = get();
            } catch (Throwable e) {
                Log.w(getClass().getSimpleName(), e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                LDNetAsyncTaskEx.b.obtainMessage(3, new a(this.a, (Object[]) obj)).sendToTarget();
                return;
            } catch (Throwable th) {
                RuntimeException runtimeException = new RuntimeException("An error occured while executing doInBackground()", th);
            }
            LDNetAsyncTaskEx.b.obtainMessage(1, new a(this.a, obj)).sendToTarget();
        }
    };

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
        final LDNetAsyncTaskEx a;
        final Data[] b;

        a(LDNetAsyncTaskEx lDNetAsyncTaskEx, Data... dataArr) {
            this.a = lDNetAsyncTaskEx;
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
                    aVar.a.b(aVar.b[0]);
                    return;
                case 2:
                    aVar.a.b(aVar.b);
                    return;
                case 3:
                    aVar.a.b();
                    return;
                default:
                    return;
            }
        }
    }

    protected abstract Result a(Params... paramsArr);

    protected abstract ThreadPoolExecutor d();

    protected void a() {
    }

    protected void a(Result result) {
    }

    protected void b(Progress... progressArr) {
    }

    protected void b() {
    }

    public final boolean c() {
        return this.d.isCancelled();
    }

    public final boolean a(boolean z) {
        return this.d.cancel(z);
    }

    public final LDNetAsyncTaskEx<Params, Progress, Result> c(Params... paramsArr) {
        if (this.a != Status.PENDING) {
            switch (this.a) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.a = Status.RUNNING;
        a();
        this.c.b = paramsArr;
        ThreadPoolExecutor d = d();
        if (d == null) {
            return null;
        }
        d.execute(this.d);
        return this;
    }

    protected final void d(Progress... progressArr) {
        b.obtainMessage(2, new a(this, progressArr)).sendToTarget();
    }

    protected void b(Result result) {
        Object obj;
        if (c()) {
            obj = null;
        }
        a(obj);
        this.a = Status.FINISHED;
    }
}
