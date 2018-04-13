package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthSchemeFactory;
import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.nio.charset.Charset;

@Immutable
public class DigestSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final Charset a;

    public DigestSchemeFactory(Charset charset) {
        this.a = charset;
    }

    public DigestSchemeFactory() {
        this(null);
    }

    public AuthScheme newInstance(HttpParams httpParams) {
        return new DigestScheme();
    }

    public AuthScheme create(HttpContext httpContext) {
        return new DigestScheme(this.a);
    }
}
