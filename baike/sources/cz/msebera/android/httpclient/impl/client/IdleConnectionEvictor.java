package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class IdleConnectionEvictor {
    private final HttpClientConnectionManager a;
    private final ThreadFactory b;
    private final Thread c;
    private final long d;
    private final long e;
    private volatile Exception f;

    static class a implements ThreadFactory {
        a() {
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "Connection evictor");
            thread.setDaemon(true);
            return thread;
        }
    }

    public IdleConnectionEvictor(HttpClientConnectionManager httpClientConnectionManager, ThreadFactory threadFactory, long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
        this.a = (HttpClientConnectionManager) Args.notNull(httpClientConnectionManager, "Connection manager");
        if (threadFactory == null) {
            threadFactory = new a();
        }
        this.b = threadFactory;
        if (timeUnit != null) {
            j = timeUnit.toMillis(j);
        }
        this.d = j;
        if (timeUnit2 != null) {
            j2 = timeUnit2.toMillis(j2);
        }
        this.e = j2;
        this.c = this.b.newThread(new i(this, httpClientConnectionManager));
    }

    public IdleConnectionEvictor(HttpClientConnectionManager httpClientConnectionManager, long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
        this(httpClientConnectionManager, null, j, timeUnit, j2, timeUnit2);
    }

    public IdleConnectionEvictor(HttpClientConnectionManager httpClientConnectionManager, long j, TimeUnit timeUnit) {
        this(httpClientConnectionManager, null, j > 0 ? j : 5, timeUnit != null ? timeUnit : TimeUnit.SECONDS, j, timeUnit);
    }

    public void start() {
        this.c.start();
    }

    public void shutdown() {
        this.c.interrupt();
    }

    public boolean isRunning() {
        return this.c.isAlive();
    }

    public void awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        Thread thread = this.c;
        if (timeUnit == null) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        thread.join(timeUnit.toMillis(j));
    }
}
