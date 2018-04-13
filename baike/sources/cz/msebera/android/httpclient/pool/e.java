package cz.msebera.android.httpclient.pool;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.concurrent.FutureCallback;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@ThreadSafe
abstract class e<T> implements Future<T> {
    private final Lock a;
    private final FutureCallback<T> b;
    private final Condition c;
    private volatile boolean d;
    private volatile boolean e;
    private T f;

    protected abstract T getPoolEntry(long j, TimeUnit timeUnit) throws IOException, InterruptedException, TimeoutException;

    e(Lock lock, FutureCallback<T> futureCallback) {
        this.a = lock;
        this.c = lock.newCondition();
        this.b = futureCallback;
    }

    public boolean cancel(boolean z) {
        this.a.lock();
        try {
            if (this.e) {
                return false;
            }
            this.e = true;
            this.d = true;
            if (this.b != null) {
                this.b.cancelled();
            }
            this.c.signalAll();
            this.a.unlock();
            return true;
        } finally {
            this.a.unlock();
        }
    }

    public boolean isCancelled() {
        return this.d;
    }

    public boolean isDone() {
        return this.e;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return get(0, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            throw new ExecutionException(e);
        }
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        Args.notNull(timeUnit, "Time unit");
        this.a.lock();
        try {
            T t;
            if (this.e) {
                t = this.f;
                this.a.unlock();
            } else {
                this.f = getPoolEntry(j, timeUnit);
                this.e = true;
                if (this.b != null) {
                    this.b.completed(this.f);
                }
                t = this.f;
                this.a.unlock();
            }
            return t;
        } catch (Throwable e) {
            this.e = true;
            this.f = null;
            if (this.b != null) {
                this.b.failed(e);
            }
            throw new ExecutionException(e);
        } catch (Throwable th) {
            this.a.unlock();
        }
    }

    public boolean await(Date date) throws InterruptedException {
        this.a.lock();
        try {
            if (this.d) {
                throw new InterruptedException("Operation interrupted");
            }
            boolean awaitUntil;
            if (date != null) {
                awaitUntil = this.c.awaitUntil(date);
            } else {
                this.c.await();
                awaitUntil = true;
            }
            if (!this.d) {
                return awaitUntil;
            }
            throw new InterruptedException("Operation interrupted");
        } finally {
            this.a.unlock();
        }
    }

    public void wakeup() {
        this.a.lock();
        try {
            this.c.signalAll();
        } finally {
            this.a.unlock();
        }
    }
}
