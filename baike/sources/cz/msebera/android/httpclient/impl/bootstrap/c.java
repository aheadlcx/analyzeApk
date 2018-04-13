package cz.msebera.android.httpclient.impl.bootstrap;

import cz.msebera.android.httpclient.ExceptionLogger;
import cz.msebera.android.httpclient.HttpServerConnection;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.HttpCoreContext;
import cz.msebera.android.httpclient.protocol.HttpService;

class c implements Runnable {
    private final HttpService a;
    private final HttpServerConnection b;
    private final ExceptionLogger c;

    c(HttpService httpService, HttpServerConnection httpServerConnection, ExceptionLogger exceptionLogger) {
        this.a = httpService;
        this.b = httpServerConnection;
        this.c = exceptionLogger;
    }

    public HttpServerConnection getConnection() {
        return this.b;
    }

    public void run() {
        try {
            Object basicHttpContext = new BasicHttpContext();
            HttpContext adapt = HttpCoreContext.adapt(basicHttpContext);
            while (!Thread.interrupted() && this.b.isOpen()) {
                this.a.handleRequest(this.b, adapt);
                basicHttpContext.clear();
            }
            this.b.close();
            try {
                this.b.shutdown();
            } catch (Exception e) {
                this.c.log(e);
            }
        } catch (Exception e2) {
            this.c.log(e2);
            try {
                this.b.shutdown();
            } catch (Exception e22) {
                this.c.log(e22);
            }
        } catch (Throwable th) {
            try {
                this.b.shutdown();
            } catch (Exception e3) {
                this.c.log(e3);
            }
            throw th;
        }
    }
}
