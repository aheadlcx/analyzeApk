package cz.msebera.android.httpclient;

import cz.msebera.android.httpclient.protocol.HttpContext;

public interface HttpResponseFactory {
    HttpResponse newHttpResponse(ProtocolVersion protocolVersion, int i, HttpContext httpContext);

    HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext);
}
