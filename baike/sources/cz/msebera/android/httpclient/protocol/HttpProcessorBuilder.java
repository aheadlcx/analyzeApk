package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import java.util.List;

public class HttpProcessorBuilder {
    private a<HttpRequestInterceptor> a;
    private a<HttpResponseInterceptor> b;

    public static HttpProcessorBuilder create() {
        return new HttpProcessorBuilder();
    }

    HttpProcessorBuilder() {
    }

    private a<HttpRequestInterceptor> a() {
        if (this.a == null) {
            this.a = new a();
        }
        return this.a;
    }

    private a<HttpResponseInterceptor> b() {
        if (this.b == null) {
            this.b = new a();
        }
        return this.b;
    }

    public HttpProcessorBuilder addFirst(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            a().addFirst(httpRequestInterceptor);
        }
        return this;
    }

    public HttpProcessorBuilder addLast(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            a().addLast(httpRequestInterceptor);
        }
        return this;
    }

    public HttpProcessorBuilder add(HttpRequestInterceptor httpRequestInterceptor) {
        return addLast(httpRequestInterceptor);
    }

    public HttpProcessorBuilder addAllFirst(HttpRequestInterceptor... httpRequestInterceptorArr) {
        if (httpRequestInterceptorArr != null) {
            a().addAllFirst((Object[]) httpRequestInterceptorArr);
        }
        return this;
    }

    public HttpProcessorBuilder addAllLast(HttpRequestInterceptor... httpRequestInterceptorArr) {
        if (httpRequestInterceptorArr != null) {
            a().addAllLast((Object[]) httpRequestInterceptorArr);
        }
        return this;
    }

    public HttpProcessorBuilder addAll(HttpRequestInterceptor... httpRequestInterceptorArr) {
        return addAllLast(httpRequestInterceptorArr);
    }

    public HttpProcessorBuilder addFirst(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            b().addFirst(httpResponseInterceptor);
        }
        return this;
    }

    public HttpProcessorBuilder addLast(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            b().addLast(httpResponseInterceptor);
        }
        return this;
    }

    public HttpProcessorBuilder add(HttpResponseInterceptor httpResponseInterceptor) {
        return addLast(httpResponseInterceptor);
    }

    public HttpProcessorBuilder addAllFirst(HttpResponseInterceptor... httpResponseInterceptorArr) {
        if (httpResponseInterceptorArr != null) {
            b().addAllFirst((Object[]) httpResponseInterceptorArr);
        }
        return this;
    }

    public HttpProcessorBuilder addAllLast(HttpResponseInterceptor... httpResponseInterceptorArr) {
        if (httpResponseInterceptorArr != null) {
            b().addAllLast((Object[]) httpResponseInterceptorArr);
        }
        return this;
    }

    public HttpProcessorBuilder addAll(HttpResponseInterceptor... httpResponseInterceptorArr) {
        return addAllLast(httpResponseInterceptorArr);
    }

    public HttpProcessor build() {
        List build;
        List list = null;
        if (this.a != null) {
            build = this.a.build();
        } else {
            build = null;
        }
        if (this.b != null) {
            list = this.b.build();
        }
        return new ImmutableHttpProcessor(build, list);
    }
}
