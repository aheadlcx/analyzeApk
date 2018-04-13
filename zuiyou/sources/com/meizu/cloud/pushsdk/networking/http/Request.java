package com.meizu.cloud.pushsdk.networking.http;

import com.tencent.connect.common.Constants;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class Request {
    private final RequestBody body;
    private final Headers headers;
    private volatile URI javaNetUri;
    private final String method;
    private final Object tag;
    private final HttpUrl url;

    public static class Builder {
        private RequestBody body;
        private com.meizu.cloud.pushsdk.networking.http.Headers.Builder headers;
        private String method;
        private Object tag;
        private HttpUrl url;

        public Builder() {
            this.method = Constants.HTTP_GET;
            this.headers = new com.meizu.cloud.pushsdk.networking.http.Headers.Builder();
        }

        private Builder(Request request) {
            this.url = request.url;
            this.method = request.method;
            this.body = request.body;
            this.tag = request.tag;
            this.headers = request.headers.newBuilder();
        }

        public Builder url(HttpUrl httpUrl) {
            if (httpUrl == null) {
                throw new IllegalArgumentException("url == null");
            }
            this.url = httpUrl;
            return this;
        }

        public Builder url(String str) {
            if (str == null) {
                throw new IllegalArgumentException("url == null");
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

        public Builder url(URL url) {
            if (url == null) {
                throw new IllegalArgumentException("url == null");
            }
            HttpUrl httpUrl = HttpUrl.get(url);
            if (httpUrl != null) {
                return url(httpUrl);
            }
            throw new IllegalArgumentException("unexpected url: " + url);
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public Builder get() {
            return method(Constants.HTTP_GET, null);
        }

        public Builder head() {
            return method("HEAD", null);
        }

        public Builder post(RequestBody requestBody) {
            return method(Constants.HTTP_POST, requestBody);
        }

        public Builder delete(RequestBody requestBody) {
            return method("DELETE", requestBody);
        }

        public Builder delete() {
            return delete(RequestBody.create(null, new byte[0]));
        }

        public Builder put(RequestBody requestBody) {
            return method("PUT", requestBody);
        }

        public Builder patch(RequestBody requestBody) {
            return method("PATCH", requestBody);
        }

        public Builder method(String str, RequestBody requestBody) {
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("method == null || method.length() == 0");
            } else if (requestBody != null && !HttpMethod.permitsRequestBody(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            } else if (requestBody == null && HttpMethod.requiresRequestBody(str)) {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            } else {
                this.method = str;
                this.body = requestBody;
                return this;
            }
        }

        public Builder tag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Request build() {
            if (this.url != null) {
                return new Request();
            }
            throw new IllegalStateException("url == null");
        }
    }

    private Request(Builder builder) {
        Object access$400;
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers.build();
        this.body = builder.body;
        if (builder.tag != null) {
            access$400 = builder.tag;
        } else {
            Request request = this;
        }
        this.tag = access$400;
    }

    public HttpUrl url() {
        return this.url;
    }

    public String method() {
        return this.method;
    }

    public int getmethod() {
        if (Constants.HTTP_GET.equals(method())) {
            return 0;
        }
        if (Constants.HTTP_POST.equals(method())) {
            return 1;
        }
        if ("PUT".equals(method())) {
            return 2;
        }
        if ("DELETE".equals(method())) {
            return 3;
        }
        if ("HEAD".equals(method())) {
            return 4;
        }
        if ("PATCH".equals(method())) {
            return 5;
        }
        return 0;
    }

    public Headers headers() {
        return this.headers;
    }

    public String header(String str) {
        return this.headers.get(str);
    }

    public List<String> headers(String str) {
        return this.headers.values(str);
    }

    public RequestBody body() {
        return this.body;
    }

    public Object tag() {
        return this.tag;
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public boolean isHttps() {
        return this.url.isHttps();
    }

    public String toString() {
        return "Request{method=" + this.method + ", url=" + this.url + ", tag=" + (this.tag != this ? this.tag : null) + '}';
    }
}
