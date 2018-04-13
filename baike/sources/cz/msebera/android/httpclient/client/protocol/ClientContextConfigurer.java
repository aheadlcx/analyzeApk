package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.cookie.CookieSpecRegistry;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;

@NotThreadSafe
@Deprecated
public class ClientContextConfigurer implements ClientContext {
    private final HttpContext a;

    public ClientContextConfigurer(HttpContext httpContext) {
        Args.notNull(httpContext, "HTTP context");
        this.a = httpContext;
    }

    public void setCookieSpecRegistry(CookieSpecRegistry cookieSpecRegistry) {
        this.a.setAttribute("http.cookiespec-registry", cookieSpecRegistry);
    }

    public void setAuthSchemeRegistry(AuthSchemeRegistry authSchemeRegistry) {
        this.a.setAttribute("http.authscheme-registry", authSchemeRegistry);
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.a.setAttribute("http.cookie-store", cookieStore);
    }

    public void setCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.a.setAttribute("http.auth.credentials-provider", credentialsProvider);
    }
}
