package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.concurrent.FutureCallback;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class FutureRequestExecutionService implements Closeable {
    private final HttpClient a;
    private final ExecutorService b;
    private final FutureRequestExecutionMetrics c = new FutureRequestExecutionMetrics();
    private final AtomicBoolean d = new AtomicBoolean(false);

    public FutureRequestExecutionService(HttpClient httpClient, ExecutorService executorService) {
        this.a = httpClient;
        this.b = executorService;
    }

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest httpUriRequest, HttpContext httpContext, ResponseHandler<T> responseHandler) {
        return execute(httpUriRequest, httpContext, responseHandler, null);
    }

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest httpUriRequest, HttpContext httpContext, ResponseHandler<T> responseHandler, FutureCallback<T> futureCallback) {
        if (this.d.get()) {
            throw new IllegalStateException("Close has been called on this httpclient instance.");
        }
        this.c.b().incrementAndGet();
        Object httpRequestFutureTask = new HttpRequestFutureTask(httpUriRequest, new h(this.a, httpUriRequest, httpContext, responseHandler, futureCallback, this.c));
        this.b.execute(httpRequestFutureTask);
        return httpRequestFutureTask;
    }

    public FutureRequestExecutionMetrics metrics() {
        return this.c;
    }

    public void close() throws IOException {
        this.d.set(true);
        this.b.shutdownNow();
        if (this.a instanceof Closeable) {
            ((Closeable) this.a).close();
        }
    }
}
