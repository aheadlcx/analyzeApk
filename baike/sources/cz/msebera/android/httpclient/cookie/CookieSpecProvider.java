package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.protocol.HttpContext;

public interface CookieSpecProvider {
    CookieSpec create(HttpContext httpContext);
}
