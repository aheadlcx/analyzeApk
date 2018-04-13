package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;

public class CacheStrategy$Factory {
    final long a;
    final Request b;
    final Response c;
    private Date d;
    private String e;
    private Date f;
    private String g;
    private Date h;
    private long i;
    private long j;
    private String k;
    private int l = -1;

    public CacheStrategy$Factory(long j, Request request, Response response) {
        this.a = j;
        this.b = request;
        this.c = response;
        if (response != null) {
            this.i = response.sentRequestAtMillis();
            this.j = response.receivedResponseAtMillis();
            Headers headers = response.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String name = headers.name(i);
                String value = headers.value(i);
                if ("Date".equalsIgnoreCase(name)) {
                    this.d = HttpDate.parse(value);
                    this.e = value;
                } else if ("Expires".equalsIgnoreCase(name)) {
                    this.h = HttpDate.parse(value);
                } else if ("Last-Modified".equalsIgnoreCase(name)) {
                    this.f = HttpDate.parse(value);
                    this.g = value;
                } else if ("ETag".equalsIgnoreCase(name)) {
                    this.k = value;
                } else if ("Age".equalsIgnoreCase(name)) {
                    this.l = HttpHeaders.parseSeconds(value, -1);
                }
            }
        }
    }

    public CacheStrategy get() {
        CacheStrategy a = a();
        if (a.networkRequest == null || !this.b.cacheControl().onlyIfCached()) {
            return a;
        }
        return new CacheStrategy(null, null);
    }

    private CacheStrategy a() {
        long j = 0;
        if (this.c == null) {
            return new CacheStrategy(this.b, null);
        }
        if (this.b.isHttps() && this.c.handshake() == null) {
            return new CacheStrategy(this.b, null);
        }
        if (!CacheStrategy.isCacheable(this.c, this.b)) {
            return new CacheStrategy(this.b, null);
        }
        CacheControl cacheControl = this.b.cacheControl();
        if (cacheControl.noCache() || a(this.b)) {
            return new CacheStrategy(this.b, null);
        }
        CacheControl cacheControl2 = this.c.cacheControl();
        if (cacheControl2.immutable()) {
            return new CacheStrategy(null, this.c);
        }
        long toMillis;
        long c = c();
        long b = b();
        if (cacheControl.maxAgeSeconds() != -1) {
            b = Math.min(b, TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds()));
        }
        if (cacheControl.minFreshSeconds() != -1) {
            toMillis = TimeUnit.SECONDS.toMillis((long) cacheControl.minFreshSeconds());
        } else {
            toMillis = 0;
        }
        if (!(cacheControl2.mustRevalidate() || cacheControl.maxStaleSeconds() == -1)) {
            j = TimeUnit.SECONDS.toMillis((long) cacheControl.maxStaleSeconds());
        }
        if (cacheControl2.noCache() || c + toMillis >= r4 + b) {
            String str;
            String str2;
            if (this.k != null) {
                str = "If-None-Match";
                str2 = this.k;
            } else if (this.f != null) {
                str = "If-Modified-Since";
                str2 = this.g;
            } else if (this.d == null) {
                return new CacheStrategy(this.b, null);
            } else {
                str = "If-Modified-Since";
                str2 = this.e;
            }
            Headers$Builder newBuilder = this.b.headers().newBuilder();
            Internal.instance.addLenient(newBuilder, str, str2);
            return new CacheStrategy(this.b.newBuilder().headers(newBuilder.build()).build(), this.c);
        }
        Builder newBuilder2 = this.c.newBuilder();
        if (toMillis + c >= b) {
            newBuilder2.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
        }
        if (c > 86400000 && d()) {
            newBuilder2.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
        }
        return new CacheStrategy(null, newBuilder2.build());
    }

    private long b() {
        CacheControl cacheControl = this.c.cacheControl();
        if (cacheControl.maxAgeSeconds() != -1) {
            return TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds());
        }
        long time;
        if (this.h != null) {
            if (this.d != null) {
                time = this.d.getTime();
            } else {
                time = this.j;
            }
            time = this.h.getTime() - time;
            if (time <= 0) {
                time = 0;
            }
            return time;
        } else if (this.f == null || this.c.request().url().query() != null) {
            return 0;
        } else {
            if (this.d != null) {
                time = this.d.getTime();
            } else {
                time = this.i;
            }
            time -= this.f.getTime();
            if (time > 0) {
                return time / 10;
            }
            return 0;
        }
    }

    private long c() {
        long j = 0;
        if (this.d != null) {
            j = Math.max(0, this.j - this.d.getTime());
        }
        if (this.l != -1) {
            j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.l));
        }
        return (j + (this.j - this.i)) + (this.a - this.j);
    }

    private boolean d() {
        return this.c.cacheControl().maxAgeSeconds() == -1 && this.h == null;
    }

    private static boolean a(Request request) {
        return (request.header("If-Modified-Since") == null && request.header("If-None-Match") == null) ? false : true;
    }
}
