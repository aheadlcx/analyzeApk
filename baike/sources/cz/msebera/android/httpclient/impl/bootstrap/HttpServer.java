package cz.msebera.android.httpclient.impl.bootstrap;

import cz.msebera.android.httpclient.ExceptionLogger;
import cz.msebera.android.httpclient.HttpConnectionFactory;
import cz.msebera.android.httpclient.config.SocketConfig;
import cz.msebera.android.httpclient.impl.DefaultBHttpServerConnection;
import cz.msebera.android.httpclient.protocol.HttpService;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;

public class HttpServer {
    private final int a;
    private final InetAddress b;
    private final SocketConfig c;
    private final ServerSocketFactory d;
    private final HttpService e;
    private final HttpConnectionFactory<? extends DefaultBHttpServerConnection> f;
    private final SSLServerSetupHandler g;
    private final ExceptionLogger h;
    private final ExecutorService i = Executors.newSingleThreadExecutor(new b("HTTP-listener-" + this.a));
    private final ThreadGroup j = new ThreadGroup("HTTP-workers");
    private final ExecutorService k = Executors.newCachedThreadPool(new b("HTTP-worker", this.j));
    private final AtomicReference<a> l = new AtomicReference(a.READY);
    private volatile ServerSocket m;
    private volatile a n;

    enum a {
        READY,
        ACTIVE,
        STOPPING
    }

    HttpServer(int i, InetAddress inetAddress, SocketConfig socketConfig, ServerSocketFactory serverSocketFactory, HttpService httpService, HttpConnectionFactory<? extends DefaultBHttpServerConnection> httpConnectionFactory, SSLServerSetupHandler sSLServerSetupHandler, ExceptionLogger exceptionLogger) {
        this.a = i;
        this.b = inetAddress;
        this.c = socketConfig;
        this.d = serverSocketFactory;
        this.e = httpService;
        this.f = httpConnectionFactory;
        this.g = sSLServerSetupHandler;
        this.h = exceptionLogger;
    }

    public InetAddress getInetAddress() {
        ServerSocket serverSocket = this.m;
        if (serverSocket != null) {
            return serverSocket.getInetAddress();
        }
        return null;
    }

    public int getLocalPort() {
        ServerSocket serverSocket = this.m;
        if (serverSocket != null) {
            return serverSocket.getLocalPort();
        }
        return -1;
    }

    public void start() throws IOException {
        if (this.l.compareAndSet(a.READY, a.ACTIVE)) {
            this.m = this.d.createServerSocket(this.a, this.c.getBacklogSize(), this.b);
            this.m.setReuseAddress(this.c.isSoReuseAddress());
            if (this.c.getRcvBufSize() > 0) {
                this.m.setReceiveBufferSize(this.c.getRcvBufSize());
            }
            if (this.g != null && (this.m instanceof SSLServerSocket)) {
                this.g.initialize((SSLServerSocket) this.m);
            }
            this.n = new a(this.c, this.m, this.e, this.f, this.h, this.k);
            this.i.execute(this.n);
        }
    }

    public void stop() {
        if (this.l.compareAndSet(a.ACTIVE, a.STOPPING)) {
            a aVar = this.n;
            if (aVar != null) {
                try {
                    aVar.terminate();
                } catch (Exception e) {
                    this.h.log(e);
                }
            }
            this.j.interrupt();
            this.i.shutdown();
            this.k.shutdown();
        }
    }

    public void awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        this.k.awaitTermination(j, timeUnit);
    }

    public void shutdown(long j, TimeUnit timeUnit) {
        stop();
        if (j > 0) {
            try {
                awaitTermination(j, timeUnit);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (Runnable runnable : this.k.shutdownNow()) {
            if (runnable instanceof c) {
                try {
                    ((c) runnable).getConnection().shutdown();
                } catch (Exception e2) {
                    this.h.log(e2);
                }
            }
        }
    }
}
