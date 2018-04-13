package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.protocol.HttpContext;

class a implements CookieSpecProvider {
    final /* synthetic */ String a;
    final /* synthetic */ CookieSpecRegistry b;

    a(CookieSpecRegistry cookieSpecRegistry, String str) {
        this.b = cookieSpecRegistry;
        this.a = str;
    }

    public CookieSpec create(HttpContext httpContext) {
        return this.b.getCookieSpec(this.a, ((HttpRequest) httpContext.getAttribute("http.request")).getParams());
    }
}
