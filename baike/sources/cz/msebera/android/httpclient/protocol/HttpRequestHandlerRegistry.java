package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.Map;

@ThreadSafe
@Deprecated
public class HttpRequestHandlerRegistry implements HttpRequestHandlerResolver {
    private final UriPatternMatcher<HttpRequestHandler> a = new UriPatternMatcher();

    public void register(String str, HttpRequestHandler httpRequestHandler) {
        Args.notNull(str, "URI request pattern");
        Args.notNull(httpRequestHandler, "Request handler");
        this.a.register(str, httpRequestHandler);
    }

    public void unregister(String str) {
        this.a.unregister(str);
    }

    public void setHandlers(Map<String, HttpRequestHandler> map) {
        this.a.setObjects(map);
    }

    public Map<String, HttpRequestHandler> getHandlers() {
        return this.a.getObjects();
    }

    public HttpRequestHandler lookup(String str) {
        return (HttpRequestHandler) this.a.lookup(str);
    }
}
