package cz.msebera.android.httpclient.protocol;

@Deprecated
public interface HttpRequestHandlerResolver {
    HttpRequestHandler lookup(String str);
}
