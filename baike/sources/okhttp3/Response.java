package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

public final class Response implements Closeable {
    final Request a;
    final Protocol b;
    final int c;
    final String d;
    @Nullable
    final Handshake e;
    final Headers f;
    @Nullable
    final ResponseBody g;
    @Nullable
    final Response h;
    @Nullable
    final Response i;
    @Nullable
    final Response j;
    final long k;
    final long l;
    private volatile CacheControl m;

    public static class Builder {
        Request a;
        Protocol b;
        int c;
        String d;
        @Nullable
        Handshake e;
        Headers$Builder f;
        ResponseBody g;
        Response h;
        Response i;
        Response j;
        long k;
        long l;

        public Builder() {
            this.c = -1;
            this.f = new Headers$Builder();
        }

        Builder(Response response) {
            this.c = -1;
            this.a = response.a;
            this.b = response.b;
            this.c = response.c;
            this.d = response.d;
            this.e = response.e;
            this.f = response.f.newBuilder();
            this.g = response.g;
            this.h = response.h;
            this.i = response.i;
            this.j = response.j;
            this.k = response.k;
            this.l = response.l;
        }

        public Builder request(Request request) {
            this.a = request;
            return this;
        }

        public Builder protocol(Protocol protocol) {
            this.b = protocol;
            return this;
        }

        public Builder code(int i) {
            this.c = i;
            return this;
        }

        public Builder message(String str) {
            this.d = str;
            return this;
        }

        public Builder handshake(@Nullable Handshake handshake) {
            this.e = handshake;
            return this;
        }

        public Builder header(String str, String str2) {
            this.f.set(str, str2);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.f.add(str, str2);
            return this;
        }

        public Builder removeHeader(String str) {
            this.f.removeAll(str);
            return this;
        }

        public Builder headers(Headers headers) {
            this.f = headers.newBuilder();
            return this;
        }

        public Builder body(@Nullable ResponseBody responseBody) {
            this.g = responseBody;
            return this;
        }

        public Builder networkResponse(@Nullable Response response) {
            if (response != null) {
                a("networkResponse", response);
            }
            this.h = response;
            return this;
        }

        public Builder cacheResponse(@Nullable Response response) {
            if (response != null) {
                a("cacheResponse", response);
            }
            this.i = response;
            return this;
        }

        private void a(String str, Response response) {
            if (response.g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            } else if (response.h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            } else if (response.i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            } else if (response.j != null) {
                throw new IllegalArgumentException(str + ".priorResponse != null");
            }
        }

        public Builder priorResponse(@Nullable Response response) {
            if (response != null) {
                a(response);
            }
            this.j = response;
            return this;
        }

        private void a(Response response) {
            if (response.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public Builder sentRequestAtMillis(long j) {
            this.k = j;
            return this;
        }

        public Builder receivedResponseAtMillis(long j) {
            this.l = j;
            return this;
        }

        public Response build() {
            if (this.a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.b == null) {
                throw new IllegalStateException("protocol == null");
            } else if (this.c < 0) {
                throw new IllegalStateException("code < 0: " + this.c);
            } else if (this.d != null) {
                return new Response(this);
            } else {
                throw new IllegalStateException("message == null");
            }
        }
    }

    Response(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f.build();
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
    }

    public Request request() {
        return this.a;
    }

    public Protocol protocol() {
        return this.b;
    }

    public int code() {
        return this.c;
    }

    public boolean isSuccessful() {
        return this.c >= 200 && this.c < 300;
    }

    public String message() {
        return this.d;
    }

    public Handshake handshake() {
        return this.e;
    }

    public List<String> headers(String str) {
        return this.f.values(str);
    }

    @Nullable
    public String header(String str) {
        return header(str, null);
    }

    @Nullable
    public String header(String str, @Nullable String str2) {
        String str3 = this.f.get(str);
        return str3 != null ? str3 : str2;
    }

    public Headers headers() {
        return this.f;
    }

    public ResponseBody peekBody(long j) throws IOException {
        BufferedSource source = this.g.source();
        source.request(j);
        Buffer clone = source.buffer().clone();
        if (clone.size() > j) {
            source = new Buffer();
            source.write(clone, j);
            clone.clear();
        } else {
            Object obj = clone;
        }
        return ResponseBody.create(this.g.contentType(), source.size(), source);
    }

    @Nullable
    public ResponseBody body() {
        return this.g;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public boolean isRedirect() {
        switch (this.c) {
            case 300:
            case 301:
            case 302:
            case 303:
            case 307:
            case 308:
                return true;
            default:
                return false;
        }
    }

    @Nullable
    public Response networkResponse() {
        return this.h;
    }

    @Nullable
    public Response cacheResponse() {
        return this.i;
    }

    @Nullable
    public Response priorResponse() {
        return this.j;
    }

    public List<Challenge> challenges() {
        String str;
        if (this.c == 401) {
            str = "WWW-Authenticate";
        } else if (this.c != 407) {
            return Collections.emptyList();
        } else {
            str = "Proxy-Authenticate";
        }
        return HttpHeaders.parseChallenges(headers(), str);
    }

    public CacheControl cacheControl() {
        CacheControl cacheControl = this.m;
        if (cacheControl != null) {
            return cacheControl;
        }
        cacheControl = CacheControl.parse(this.f);
        this.m = cacheControl;
        return cacheControl;
    }

    public long sentRequestAtMillis() {
        return this.k;
    }

    public long receivedResponseAtMillis() {
        return this.l;
    }

    public void close() {
        if (this.g == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        this.g.close();
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.a.url() + '}';
    }
}
