package okhttp3;

import java.util.List;
import javax.annotation.Nullable;

public final class Request {
    final HttpUrl a;
    final String b;
    final Headers c;
    @Nullable
    final RequestBody d;
    final Object e;
    private volatile CacheControl f;

    Request(Request$Builder request$Builder) {
        Object obj;
        this.a = request$Builder.a;
        this.b = request$Builder.b;
        this.c = request$Builder.c.build();
        this.d = request$Builder.d;
        if (request$Builder.e != null) {
            obj = request$Builder.e;
        } else {
            Request request = this;
        }
        this.e = obj;
    }

    public HttpUrl url() {
        return this.a;
    }

    public String method() {
        return this.b;
    }

    public Headers headers() {
        return this.c;
    }

    public String header(String str) {
        return this.c.get(str);
    }

    public List<String> headers(String str) {
        return this.c.values(str);
    }

    @Nullable
    public RequestBody body() {
        return this.d;
    }

    public Object tag() {
        return this.e;
    }

    public Request$Builder newBuilder() {
        return new Request$Builder(this);
    }

    public CacheControl cacheControl() {
        CacheControl cacheControl = this.f;
        if (cacheControl != null) {
            return cacheControl;
        }
        cacheControl = CacheControl.parse(this.c);
        this.f = cacheControl;
        return cacheControl;
    }

    public boolean isHttps() {
        return this.a.isHttps();
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.a + ", tag=" + (this.e != this ? this.e : null) + '}';
    }
}
