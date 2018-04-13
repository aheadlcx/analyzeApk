package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.protocol.HttpContext;

public interface AuthSchemeProvider {
    AuthScheme create(HttpContext httpContext);
}
