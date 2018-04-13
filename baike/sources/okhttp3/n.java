package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;

final class n implements Call {
    final OkHttpClient a;
    final RetryAndFollowUpInterceptor b;
    final Request c;
    final boolean d;
    private EventListener e;
    private boolean f;

    final class a extends NamedRunnable {
        final /* synthetic */ n a;
        private final Callback c;

        a(n nVar, Callback callback) {
            this.a = nVar;
            super("OkHttp %s", nVar.c());
            this.c = callback;
        }

        String a() {
            return this.a.c.url().host();
        }

        n b() {
            return this.a;
        }

        protected void execute() {
            Throwable e;
            Object obj = 1;
            Object obj2 = null;
            try {
                Response d = this.a.d();
                if (this.a.b.isCanceled()) {
                    try {
                        this.c.onFailure(this.a, new IOException("Canceled"));
                    } catch (IOException e2) {
                        e = e2;
                        if (obj == null) {
                            try {
                                Platform.get().log(4, "Callback failure for " + this.a.b(), e);
                            } catch (Throwable th) {
                                this.a.a.dispatcher().b(this);
                            }
                        } else {
                            this.a.e.callFailed(this.a, e);
                            this.c.onFailure(this.a, e);
                        }
                        this.a.a.dispatcher().b(this);
                    }
                }
                this.c.onResponse(this.a, d);
                this.a.a.dispatcher().b(this);
            } catch (IOException e3) {
                e = e3;
                obj = obj2;
                if (obj == null) {
                    this.a.e.callFailed(this.a, e);
                    this.c.onFailure(this.a, e);
                } else {
                    Platform.get().log(4, "Callback failure for " + this.a.b(), e);
                }
                this.a.a.dispatcher().b(this);
            }
        }
    }

    private n(OkHttpClient okHttpClient, Request request, boolean z) {
        this.a = okHttpClient;
        this.c = request;
        this.d = z;
        this.b = new RetryAndFollowUpInterceptor(okHttpClient, z);
    }

    static n a(OkHttpClient okHttpClient, Request request, boolean z) {
        n nVar = new n(okHttpClient, request, z);
        nVar.e = okHttpClient.eventListenerFactory().create(nVar);
        return nVar;
    }

    public Request request() {
        return this.c;
    }

    public Response execute() throws IOException {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        e();
        this.e.callStart(this);
        try {
            this.a.dispatcher().a(this);
            Response d = d();
            if (d == null) {
                throw new IOException("Canceled");
            }
            this.a.dispatcher().b(this);
            return d;
        } catch (IOException e) {
            this.e.callFailed(this, e);
            throw e;
        } catch (Throwable th) {
            this.a.dispatcher().b(this);
        }
    }

    private void e() {
        this.b.setCallStackTrace(Platform.get().getStackTraceForCloseable("response.body().close()"));
    }

    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        e();
        this.e.callStart(this);
        this.a.dispatcher().a(new a(this, callback));
    }

    public void cancel() {
        this.b.cancel();
    }

    public synchronized boolean isExecuted() {
        return this.f;
    }

    public boolean isCanceled() {
        return this.b.isCanceled();
    }

    public n clone() {
        return a(this.a, this.c, this.d);
    }

    StreamAllocation a() {
        return this.b.streamAllocation();
    }

    String b() {
        return (isCanceled() ? "canceled " : "") + (this.d ? "web socket" : "call") + " to " + c();
    }

    String c() {
        return this.c.url().redact();
    }

    Response d() throws IOException {
        List arrayList = new ArrayList();
        arrayList.addAll(this.a.interceptors());
        arrayList.add(this.b);
        arrayList.add(new BridgeInterceptor(this.a.cookieJar()));
        arrayList.add(new CacheInterceptor(this.a.a()));
        arrayList.add(new ConnectInterceptor(this.a));
        if (!this.d) {
            arrayList.addAll(this.a.networkInterceptors());
        }
        arrayList.add(new CallServerInterceptor(this.d));
        return new RealInterceptorChain(arrayList, null, null, null, 0, this.c, this, this.e, this.a.connectTimeoutMillis(), this.a.readTimeoutMillis(), this.a.writeTimeoutMillis()).proceed(this.c);
    }
}
