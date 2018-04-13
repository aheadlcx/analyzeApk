package okhttp3.internal.cache;

import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class CacheInterceptor implements Interceptor {
    final InternalCache a;

    public CacheInterceptor(InternalCache internalCache) {
        this.a = internalCache;
    }

    public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
        Response response;
        Response response2 = null;
        if (this.a != null) {
            response = this.a.get(interceptor$Chain.request());
        } else {
            response = response2;
        }
        CacheStrategy cacheStrategy = new CacheStrategy$Factory(System.currentTimeMillis(), interceptor$Chain.request(), response).get();
        Request request = cacheStrategy.networkRequest;
        Response response3 = cacheStrategy.cacheResponse;
        if (this.a != null) {
            this.a.trackResponse(cacheStrategy);
        }
        if (response != null && response3 == null) {
            Util.closeQuietly(response.body());
        }
        if (request == null && response3 == null) {
            return new Builder().request(interceptor$Chain.request()).protocol(Protocol.HTTP_1_1).code(HttpStatus.SC_GATEWAY_TIMEOUT).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1).receivedResponseAtMillis(System.currentTimeMillis()).build();
        }
        if (request == null) {
            return response3.newBuilder().cacheResponse(a(response3)).build();
        }
        try {
            response2 = interceptor$Chain.proceed(request);
            if (response3 != null) {
                if (response2.code() == 304) {
                    response = response3.newBuilder().headers(a(response3.headers(), response2.headers())).sentRequestAtMillis(response2.sentRequestAtMillis()).receivedResponseAtMillis(response2.receivedResponseAtMillis()).cacheResponse(a(response3)).networkResponse(a(response2)).build();
                    response2.body().close();
                    this.a.trackConditionalCacheHit();
                    this.a.update(response3, response);
                    return response;
                }
                Util.closeQuietly(response3.body());
            }
            response = response2.newBuilder().cacheResponse(a(response3)).networkResponse(a(response2)).build();
            if (this.a == null) {
                return response;
            }
            if (HttpHeaders.hasBody(response) && CacheStrategy.isCacheable(response, request)) {
                return a(this.a.put(response), response);
            }
            if (!HttpMethod.invalidatesCache(request.method())) {
                return response;
            }
            try {
                this.a.remove(request);
                return response;
            } catch (IOException e) {
                return response;
            }
        } finally {
            if (response2 == null && response != null) {
                Util.closeQuietly(response.body());
            }
        }
    }

    private static Response a(Response response) {
        if (response == null || response.body() == null) {
            return response;
        }
        return response.newBuilder().body(null).build();
    }

    private Response a(CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink body = cacheRequest.body();
        if (body == null) {
            return response;
        }
        Source aVar = new a(this, response.body().source(), cacheRequest, Okio.buffer(body));
        return response.newBuilder().body(new RealResponseBody(response.header("Content-Type"), response.body().contentLength(), Okio.buffer(aVar))).build();
    }

    private static Headers a(Headers headers, Headers headers2) {
        int i;
        int i2 = 0;
        Headers$Builder headers$Builder = new Headers$Builder();
        int size = headers.size();
        for (i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if (!("Warning".equalsIgnoreCase(name) && value.startsWith("1")) && (!a(name) || headers2.get(name) == null)) {
                Internal.instance.addLenient(headers$Builder, name, value);
            }
        }
        i = headers2.size();
        while (i2 < i) {
            String name2 = headers2.name(i2);
            if (!"Content-Length".equalsIgnoreCase(name2) && a(name2)) {
                Internal.instance.addLenient(headers$Builder, name2, headers2.value(i2));
            }
            i2++;
        }
        return headers$Builder.build();
    }

    static boolean a(String str) {
        if ("Connection".equalsIgnoreCase(str) || HTTP.CONN_KEEP_ALIVE.equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || cz.msebera.android.httpclient.HttpHeaders.TE.equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || cz.msebera.android.httpclient.HttpHeaders.UPGRADE.equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
