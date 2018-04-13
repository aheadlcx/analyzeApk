package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.protocol.HttpContext;

public interface UserTokenHandler {
    Object getUserToken(HttpContext httpContext);
}
