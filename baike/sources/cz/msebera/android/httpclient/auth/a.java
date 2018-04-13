package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.protocol.HttpContext;

class a implements AuthSchemeProvider {
    final /* synthetic */ String a;
    final /* synthetic */ AuthSchemeRegistry b;

    a(AuthSchemeRegistry authSchemeRegistry, String str) {
        this.b = authSchemeRegistry;
        this.a = str;
    }

    public AuthScheme create(HttpContext httpContext) {
        return this.b.getAuthScheme(this.a, ((HttpRequest) httpContext.getAttribute("http.request")).getParams());
    }
}
