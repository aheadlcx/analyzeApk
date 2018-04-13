package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import java.util.concurrent.TimeUnit;

class i implements Runnable {
    final /* synthetic */ HttpClientConnectionManager a;
    final /* synthetic */ IdleConnectionEvictor b;

    i(IdleConnectionEvictor idleConnectionEvictor, HttpClientConnectionManager httpClientConnectionManager) {
        this.b = idleConnectionEvictor;
        this.a = httpClientConnectionManager;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(this.b.d);
                this.a.closeExpiredConnections();
                if (this.b.e > 0) {
                    this.a.closeIdleConnections(this.b.e, TimeUnit.MILLISECONDS);
                }
            } catch (Exception e) {
                this.b.f = e;
                return;
            }
        }
    }
}
