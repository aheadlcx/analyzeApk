package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.concurrent.FutureCallback;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

class h<V> implements Callable<V> {
    private final HttpUriRequest a;
    private final HttpClient b;
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final long d = System.currentTimeMillis();
    private long e = -1;
    private long f = -1;
    private final HttpContext g;
    private final ResponseHandler<V> h;
    private final FutureCallback<V> i;
    private final FutureRequestExecutionMetrics j;

    h(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext, ResponseHandler<V> responseHandler, FutureCallback<V> futureCallback, FutureRequestExecutionMetrics futureRequestExecutionMetrics) {
        this.b = httpClient;
        this.h = responseHandler;
        this.a = httpUriRequest;
        this.g = httpContext;
        this.i = futureCallback;
        this.j = futureRequestExecutionMetrics;
    }

    public long getScheduled() {
        return this.d;
    }

    public long getStarted() {
        return this.e;
    }

    public long getEnded() {
        return this.f;
    }

    public V call() throws Exception {
        if (this.c.get()) {
            throw new IllegalStateException("call has been cancelled for request " + this.a.getURI());
        }
        try {
            this.j.a().incrementAndGet();
            this.e = System.currentTimeMillis();
            this.j.b().decrementAndGet();
            V execute = this.b.execute(this.a, this.h, this.g);
            this.f = System.currentTimeMillis();
            this.j.c().increment(this.e);
            if (this.i != null) {
                this.i.completed(execute);
            }
            this.j.e().increment(this.e);
            this.j.f().increment(this.e);
            this.j.a().decrementAndGet();
            return execute;
        } catch (Exception e) {
            this.j.d().increment(this.e);
            this.f = System.currentTimeMillis();
            if (this.i != null) {
                this.i.failed(e);
            }
            throw e;
        } catch (Throwable th) {
            this.j.e().increment(this.e);
            this.j.f().increment(this.e);
            this.j.a().decrementAndGet();
        }
    }

    public void cancel() {
        this.c.set(true);
        if (this.i != null) {
            this.i.cancelled();
        }
    }
}
