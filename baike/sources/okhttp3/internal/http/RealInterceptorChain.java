package okhttp3.internal.http;

import com.alipay.sdk.data.a;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;

public final class RealInterceptorChain implements Interceptor$Chain {
    private final List<Interceptor> a;
    private final StreamAllocation b;
    private final HttpCodec c;
    private final RealConnection d;
    private final int e;
    private final Request f;
    private final Call g;
    private final EventListener h;
    private final int i;
    private final int j;
    private final int k;
    private int l;

    public RealInterceptorChain(List<Interceptor> list, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection, int i, Request request, Call call, EventListener eventListener, int i2, int i3, int i4) {
        this.a = list;
        this.d = realConnection;
        this.b = streamAllocation;
        this.c = httpCodec;
        this.e = i;
        this.f = request;
        this.g = call;
        this.h = eventListener;
        this.i = i2;
        this.j = i3;
        this.k = i4;
    }

    public Connection connection() {
        return this.d;
    }

    public int connectTimeoutMillis() {
        return this.i;
    }

    public Interceptor$Chain withConnectTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, Util.checkDuration(a.f, (long) i, timeUnit), this.j, this.k);
    }

    public int readTimeoutMillis() {
        return this.j;
    }

    public Interceptor$Chain withReadTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, Util.checkDuration(a.f, (long) i, timeUnit), this.k);
    }

    public int writeTimeoutMillis() {
        return this.k;
    }

    public Interceptor$Chain withWriteTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, Util.checkDuration(a.f, (long) i, timeUnit));
    }

    public StreamAllocation streamAllocation() {
        return this.b;
    }

    public HttpCodec httpStream() {
        return this.c;
    }

    public Call call() {
        return this.g;
    }

    public EventListener eventListener() {
        return this.h;
    }

    public Request request() {
        return this.f;
    }

    public Response proceed(Request request) throws IOException {
        return proceed(request, this.b, this.c, this.d);
    }

    public Response proceed(Request request, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection) throws IOException {
        if (this.e >= this.a.size()) {
            throw new AssertionError();
        }
        this.l++;
        if (this.c != null && !this.d.supportsUrl(request.url())) {
            throw new IllegalStateException("network interceptor " + this.a.get(this.e - 1) + " must retain the same host and port");
        } else if (this.c == null || this.l <= 1) {
            Object realInterceptorChain = new RealInterceptorChain(this.a, streamAllocation, httpCodec, realConnection, this.e + 1, request, this.g, this.h, this.i, this.j, this.k);
            Interceptor interceptor = (Interceptor) this.a.get(this.e);
            Response intercept = interceptor.intercept(realInterceptorChain);
            if (httpCodec != null && this.e + 1 < this.a.size() && realInterceptorChain.l != 1) {
                throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
            } else if (intercept == null) {
                throw new NullPointerException("interceptor " + interceptor + " returned null");
            } else if (intercept.body() != null) {
                return intercept;
            } else {
                throw new IllegalStateException("interceptor " + interceptor + " returned a response with no body");
            }
        } else {
            throw new IllegalStateException("network interceptor " + this.a.get(this.e - 1) + " must call proceed() exactly once");
        }
    }
}
