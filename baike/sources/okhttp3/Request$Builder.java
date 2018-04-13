package okhttp3;

import java.net.URL;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;

public class Request$Builder {
    HttpUrl a;
    String b;
    Headers$Builder c;
    RequestBody d;
    Object e;

    public Request$Builder() {
        this.b = "GET";
        this.c = new Headers$Builder();
    }

    Request$Builder(Request request) {
        this.a = request.a;
        this.b = request.b;
        this.d = request.d;
        this.e = request.e;
        this.c = request.c.newBuilder();
    }

    public Request$Builder url(HttpUrl httpUrl) {
        if (httpUrl == null) {
            throw new NullPointerException("url == null");
        }
        this.a = httpUrl;
        return this;
    }

    public Request$Builder url(String str) {
        if (str == null) {
            throw new NullPointerException("url == null");
        }
        if (str.regionMatches(true, 0, "ws:", 0, 3)) {
            str = "http:" + str.substring(3);
        } else {
            if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
        }
        HttpUrl parse = HttpUrl.parse(str);
        if (parse != null) {
            return url(parse);
        }
        throw new IllegalArgumentException("unexpected url: " + str);
    }

    public Request$Builder url(URL url) {
        if (url == null) {
            throw new NullPointerException("url == null");
        }
        HttpUrl httpUrl = HttpUrl.get(url);
        if (httpUrl != null) {
            return url(httpUrl);
        }
        throw new IllegalArgumentException("unexpected url: " + url);
    }

    public Request$Builder header(String str, String str2) {
        this.c.set(str, str2);
        return this;
    }

    public Request$Builder addHeader(String str, String str2) {
        this.c.add(str, str2);
        return this;
    }

    public Request$Builder removeHeader(String str) {
        this.c.removeAll(str);
        return this;
    }

    public Request$Builder headers(Headers headers) {
        this.c = headers.newBuilder();
        return this;
    }

    public Request$Builder cacheControl(CacheControl cacheControl) {
        String cacheControl2 = cacheControl.toString();
        if (cacheControl2.isEmpty()) {
            return removeHeader("Cache-Control");
        }
        return header("Cache-Control", cacheControl2);
    }

    public Request$Builder get() {
        return method("GET", null);
    }

    public Request$Builder head() {
        return method("HEAD", null);
    }

    public Request$Builder post(RequestBody requestBody) {
        return method("POST", requestBody);
    }

    public Request$Builder delete(@Nullable RequestBody requestBody) {
        return method("DELETE", requestBody);
    }

    public Request$Builder delete() {
        return delete(Util.EMPTY_REQUEST);
    }

    public Request$Builder put(RequestBody requestBody) {
        return method("PUT", requestBody);
    }

    public Request$Builder patch(RequestBody requestBody) {
        return method("PATCH", requestBody);
    }

    public Request$Builder method(String str, @Nullable RequestBody requestBody) {
        if (str == null) {
            throw new NullPointerException("method == null");
        } else if (str.length() == 0) {
            throw new IllegalArgumentException("method.length() == 0");
        } else if (requestBody != null && !HttpMethod.permitsRequestBody(str)) {
            throw new IllegalArgumentException("method " + str + " must not have a request body.");
        } else if (requestBody == null && HttpMethod.requiresRequestBody(str)) {
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        } else {
            this.b = str;
            this.d = requestBody;
            return this;
        }
    }

    public Request$Builder tag(Object obj) {
        this.e = obj;
        return this;
    }

    public Request build() {
        if (this.a != null) {
            return new Request(this);
        }
        throw new IllegalStateException("url == null");
    }
}
