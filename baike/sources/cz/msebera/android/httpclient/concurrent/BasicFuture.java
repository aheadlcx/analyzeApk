package cz.msebera.android.httpclient.concurrent;

import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicFuture<T> implements Cancellable, Future<T> {
    private final FutureCallback<T> a;
    private volatile boolean b;
    private volatile boolean c;
    private volatile T d;
    private volatile Exception e;

    public BasicFuture(FutureCallback<T> futureCallback) {
        this.a = futureCallback;
    }

    public boolean isCancelled() {
        return this.c;
    }

    public boolean isDone() {
        return this.b;
    }

    private T a() throws ExecutionException {
        if (this.e == null) {
            return this.d;
        }
        throw new ExecutionException(this.e);
    }

    public synchronized T get() throws InterruptedException, ExecutionException {
        while (!this.b) {
            wait();
        }
        return a();
    }

    public synchronized T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        T a;
        Args.notNull(timeUnit, "Time unit");
        long toMillis = timeUnit.toMillis(j);
        long currentTimeMillis = toMillis <= 0 ? 0 : System.currentTimeMillis();
        if (this.b) {
            a = a();
        } else if (toMillis <= 0) {
            throw new TimeoutException();
        } else {
            long j2 = toMillis;
            do {
                wait(j2);
                if (this.b) {
                    a = a();
                } else {
                    j2 = toMillis - (System.currentTimeMillis() - currentTimeMillis);
                }
            } while (j2 > 0);
            throw new TimeoutException();
        }
        return a;
    }

    public boolean completed(T t) {
        boolean z = true;
        synchronized (this) {
            if (this.b) {
                z = false;
            } else {
                this.b = true;
                this.d = t;
                notifyAll();
                if (this.a != null) {
                    this.a.completed(t);
                }
            }
        }
        return z;
    }

    public boolean failed(Exception exception) {
        boolean z = true;
        synchronized (this) {
            if (this.b) {
                z = false;
            } else {
                this.b = true;
                this.e = exception;
                notifyAll();
                if (this.a != null) {
                    this.a.failed(exception);
                }
            }
        }
        return z;
    }

    public boolean cancel(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            if (this.b) {
                z2 = false;
            } else {
                this.b = true;
                this.c = true;
                notifyAll();
                if (this.a != null) {
                    this.a.cancelled();
                }
            }
        }
        return z2;
    }

    public boolean cancel() {
        return cancel(true);
    }
}
