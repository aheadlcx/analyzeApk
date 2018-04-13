package cz.msebera.android.httpclient;

import cz.msebera.android.httpclient.protocol.HttpContext;

public interface ConnectionReuseStrategy {
    boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext);
}
