package cz.msebera.android.httpclient.client.methods;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.message.AbstractHttpMessage;
import cz.msebera.android.httpclient.message.BasicRequestLine;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@NotThreadSafe
public class HttpRequestWrapper extends AbstractHttpMessage implements HttpUriRequest {
    private final HttpRequest c;
    private final HttpHost d;
    private final String e;
    private ProtocolVersion f;
    private URI g;

    private HttpRequestWrapper(HttpRequest httpRequest, HttpHost httpHost) {
        this.c = (HttpRequest) Args.notNull(httpRequest, "HTTP request");
        this.d = httpHost;
        this.f = this.c.getRequestLine().getProtocolVersion();
        this.e = this.c.getRequestLine().getMethod();
        if (httpRequest instanceof HttpUriRequest) {
            this.g = ((HttpUriRequest) httpRequest).getURI();
        } else {
            this.g = null;
        }
        setHeaders(httpRequest.getAllHeaders());
    }

    public ProtocolVersion getProtocolVersion() {
        return this.f != null ? this.f : this.c.getProtocolVersion();
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.f = protocolVersion;
    }

    public URI getURI() {
        return this.g;
    }

    public void setURI(URI uri) {
        this.g = uri;
    }

    public String getMethod() {
        return this.e;
    }

    public void abort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public boolean isAborted() {
        return false;
    }

    public RequestLine getRequestLine() {
        String toASCIIString;
        if (this.g != null) {
            toASCIIString = this.g.toASCIIString();
        } else {
            toASCIIString = this.c.getRequestLine().getUri();
        }
        if (toASCIIString == null || toASCIIString.isEmpty()) {
            toASCIIString = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        return new BasicRequestLine(this.e, toASCIIString, getProtocolVersion());
    }

    public HttpRequest getOriginal() {
        return this.c;
    }

    public HttpHost getTarget() {
        return this.d;
    }

    public String toString() {
        return getRequestLine() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a;
    }

    public static HttpRequestWrapper wrap(HttpRequest httpRequest) {
        return wrap(httpRequest, null);
    }

    public static HttpRequestWrapper wrap(HttpRequest httpRequest, HttpHost httpHost) {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            return new HttpRequestWrapper$a((HttpEntityEnclosingRequest) httpRequest, httpHost);
        }
        return new HttpRequestWrapper(httpRequest, httpHost);
    }

    @Deprecated
    public HttpParams getParams() {
        if (this.b == null) {
            this.b = this.c.getParams().copy();
        }
        return this.b;
    }
}
