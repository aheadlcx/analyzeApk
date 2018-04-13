package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import java.util.concurrent.FutureTask;

public class HttpRequestFutureTask<V> extends FutureTask<V> {
    private final HttpUriRequest a;
    private final h<V> b;

    public HttpRequestFutureTask(HttpUriRequest httpUriRequest, h<V> hVar) {
        super(hVar);
        this.a = httpUriRequest;
        this.b = hVar;
    }

    public boolean cancel(boolean z) {
        this.b.cancel();
        if (z) {
            this.a.abort();
        }
        return super.cancel(z);
    }

    public long scheduledTime() {
        return this.b.getScheduled();
    }

    public long startedTime() {
        return this.b.getStarted();
    }

    public long endedTime() {
        if (isDone()) {
            return this.b.getEnded();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public long requestDuration() {
        if (isDone()) {
            return endedTime() - startedTime();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public long taskDuration() {
        if (isDone()) {
            return endedTime() - scheduledTime();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public String toString() {
        return this.a.getRequestLine().getUri();
    }
}
