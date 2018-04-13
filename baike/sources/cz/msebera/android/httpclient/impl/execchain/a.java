package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@ThreadSafe
class a implements Cancellable, ConnectionReleaseTrigger, Closeable {
    private final HttpClientConnectionManager a;
    private final HttpClientConnection b;
    private volatile boolean c;
    private volatile Object d;
    private volatile long e;
    private volatile TimeUnit f;
    private volatile boolean g;
    public HttpClientAndroidLog log;

    public a(HttpClientAndroidLog httpClientAndroidLog, HttpClientConnectionManager httpClientConnectionManager, HttpClientConnection httpClientConnection) {
        this.log = httpClientAndroidLog;
        this.a = httpClientConnectionManager;
        this.b = httpClientConnection;
    }

    public boolean isReusable() {
        return this.c;
    }

    public void markReusable() {
        this.c = true;
    }

    public void markNonReusable() {
        this.c = false;
    }

    public void setState(Object obj) {
        this.d = obj;
    }

    public void setValidFor(long j, TimeUnit timeUnit) {
        synchronized (this.b) {
            this.e = j;
            this.f = timeUnit;
        }
    }

    public void releaseConnection() {
        synchronized (this.b) {
            if (this.g) {
                return;
            }
            this.g = true;
            if (this.c) {
                this.a.releaseConnection(this.b, this.d, this.e, this.f);
            } else {
                try {
                    this.b.close();
                    this.log.debug("Connection discarded");
                } catch (Throwable e) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug(e.getMessage(), e);
                    }
                } finally {
                    this.a.releaseConnection(this.b, null, 0, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    public void abortConnection() {
        synchronized (this.b) {
            if (this.g) {
                return;
            }
            this.g = true;
            try {
                this.b.shutdown();
                this.log.debug("Connection discarded");
            } catch (Throwable e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug(e.getMessage(), e);
                }
            } finally {
                this.a.releaseConnection(this.b, null, 0, TimeUnit.MILLISECONDS);
            }
        }
    }

    public boolean cancel() {
        boolean z = this.g;
        this.log.debug("Cancelling request execution");
        abortConnection();
        return !z;
    }

    public boolean isReleased() {
        return this.g;
    }

    public void close() throws IOException {
        abortConnection();
    }
}
