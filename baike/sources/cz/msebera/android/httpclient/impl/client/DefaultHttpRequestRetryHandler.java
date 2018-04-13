package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;

@Immutable
public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    public static final DefaultHttpRequestRetryHandler INSTANCE = new DefaultHttpRequestRetryHandler();
    private final int a;
    private final boolean b;
    private final Set<Class<? extends IOException>> c;

    protected DefaultHttpRequestRetryHandler(int i, boolean z, Collection<Class<? extends IOException>> collection) {
        this.a = i;
        this.b = z;
        this.c = new HashSet();
        for (Class add : collection) {
            this.c.add(add);
        }
    }

    public DefaultHttpRequestRetryHandler(int i, boolean z) {
        this(i, z, Arrays.asList(new Class[]{InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class}));
    }

    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        Args.notNull(iOException, "Exception parameter");
        Args.notNull(httpContext, "HTTP context");
        if (i > this.a) {
            return false;
        }
        if (this.c.contains(iOException.getClass())) {
            return false;
        }
        for (Class isInstance : this.c) {
            if (isInstance.isInstance(iOException)) {
                return false;
            }
        }
        HttpClientContext adapt = HttpClientContext.adapt(httpContext);
        HttpRequest request = adapt.getRequest();
        if (b(request)) {
            return false;
        }
        if (a(request)) {
            return true;
        }
        if (!adapt.isRequestSent() || this.b) {
            return true;
        }
        return false;
    }

    public boolean isRequestSentRetryEnabled() {
        return this.b;
    }

    public int getRetryCount() {
        return this.a;
    }

    protected boolean a(HttpRequest httpRequest) {
        return !(httpRequest instanceof HttpEntityEnclosingRequest);
    }

    @Deprecated
    protected boolean b(HttpRequest httpRequest) {
        HttpRequest original;
        if (httpRequest instanceof RequestWrapper) {
            original = ((RequestWrapper) httpRequest).getOriginal();
        } else {
            original = httpRequest;
        }
        return (original instanceof HttpUriRequest) && ((HttpUriRequest) original).isAborted();
    }
}
