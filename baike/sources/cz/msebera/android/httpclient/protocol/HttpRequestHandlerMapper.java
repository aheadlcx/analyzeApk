package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpRequest;

public interface HttpRequestHandlerMapper {
    HttpRequestHandler lookup(HttpRequest httpRequest);
}
