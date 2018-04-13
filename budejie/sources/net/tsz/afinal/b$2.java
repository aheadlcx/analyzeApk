package net.tsz.afinal;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class b$2 implements HttpRequestInterceptor {
    final /* synthetic */ b a;

    b$2(b bVar) {
        this.a = bVar;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        if (!httpRequest.containsHeader("Accept-Encoding")) {
            httpRequest.addHeader("Accept-Encoding", "gzip");
        }
        for (String str : b.a(this.a).keySet()) {
            httpRequest.addHeader(str, (String) b.a(this.a).get(str));
        }
    }
}
