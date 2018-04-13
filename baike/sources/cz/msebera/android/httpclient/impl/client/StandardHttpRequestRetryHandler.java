package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Immutable
public class StandardHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler {
    private final Map<String, Boolean> a;

    public StandardHttpRequestRetryHandler(int i, boolean z) {
        super(i, z);
        this.a = new ConcurrentHashMap();
        this.a.put("GET", Boolean.TRUE);
        this.a.put("HEAD", Boolean.TRUE);
        this.a.put("PUT", Boolean.TRUE);
        this.a.put("DELETE", Boolean.TRUE);
        this.a.put("OPTIONS", Boolean.TRUE);
        this.a.put("TRACE", Boolean.TRUE);
    }

    public StandardHttpRequestRetryHandler() {
        this(3, false);
    }

    protected boolean a(HttpRequest httpRequest) {
        Boolean bool = (Boolean) this.a.get(httpRequest.getRequestLine().getMethod().toUpperCase(Locale.ROOT));
        return bool != null && bool.booleanValue();
    }
}
