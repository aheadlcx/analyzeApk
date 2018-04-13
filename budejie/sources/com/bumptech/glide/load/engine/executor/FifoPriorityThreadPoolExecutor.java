package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import android.util.Log;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FifoPriorityThreadPoolExecutor extends ThreadPoolExecutor {
    private final AtomicInteger a;
    private final UncaughtThrowableStrategy b;

    public enum UncaughtThrowableStrategy {
        IGNORE,
        LOG {
            protected void handle(Throwable th) {
                if (Log.isLoggable("PriorityExecutor", 6)) {
                    Log.e("PriorityExecutor", "Request threw uncaught throwable", th);
                }
            }
        },
        THROW {
            protected void handle(Throwable th) {
                super.handle(th);
                throw new RuntimeException(th);
            }
        };

        protected void handle(Throwable th) {
        }
    }

    public static class a implements ThreadFactory {
        int a = 0;

        public Thread newThread(Runnable runnable) {
            Thread anonymousClass1 = new Thread(this, runnable, "fifo-pool-thread-" + this.a) {
                final /* synthetic */ a a;

                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            this.a++;
            return anonymousClass1;
        }
    }

    static class b<T> extends FutureTask<T> implements Comparable<b<?>> {
        private final int a;
        private final int b;

        public /* synthetic */ int compareTo(Object obj) {
            return a((b) obj);
        }

        public b(Runnable runnable, T t, int i) {
            super(runnable, t);
            if (runnable instanceof a) {
                this.a = ((a) runnable).b();
                this.b = i;
                return;
            }
            throw new IllegalArgumentException("FifoPriorityThreadPoolExecutor must be given Runnables that implement Prioritized");
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (this.b == bVar.b && this.a == bVar.a) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.a * 31) + this.b;
        }

        public int a(b<?> bVar) {
            int i = this.a - bVar.a;
            if (i == 0) {
                return this.b - bVar.b;
            }
            return i;
        }
    }

    public FifoPriorityThreadPoolExecutor(int i) {
        this(i, UncaughtThrowableStrategy.LOG);
    }

    public FifoPriorityThreadPoolExecutor(int i, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        this(i, i, 0, TimeUnit.MILLISECONDS, new a(), uncaughtThrowableStrategy);
    }

    public FifoPriorityThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        super(i, i2, j, timeUnit, new PriorityBlockingQueue(), threadFactory);
        this.a = new AtomicInteger();
        this.b = uncaughtThrowableStrategy;
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new b(runnable, t, this.a.getAndIncrement());
    }

    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            Future future = (Future) runnable;
            if (future.isDone() && !future.isCancelled()) {
                try {
                    future.get();
                } catch (Throwable e) {
                    this.b.handle(e);
                } catch (Throwable e2) {
                    this.b.handle(e2);
                }
            }
        }
    }
}
