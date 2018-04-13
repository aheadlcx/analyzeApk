package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.message.AbstractHttpMessage;
import cz.msebera.android.httpclient.message.BasicRequestLine;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.util.Args;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@NotThreadSafe
@Deprecated
public class RequestWrapper extends AbstractHttpMessage implements HttpUriRequest {
    private final HttpRequest c;
    private URI d;
    private String e;
    private ProtocolVersion f;
    private int g;

    public RequestWrapper(HttpRequest httpRequest) throws ProtocolException {
        Args.notNull(httpRequest, "HTTP request");
        this.c = httpRequest;
        setParams(httpRequest.getParams());
        setHeaders(httpRequest.getAllHeaders());
        if (httpRequest instanceof HttpUriRequest) {
            this.d = ((HttpUriRequest) httpRequest).getURI();
            this.e = ((HttpUriRequest) httpRequest).getMethod();
            this.f = null;
        } else {
            RequestLine requestLine = httpRequest.getRequestLine();
            try {
                this.d = new URI(requestLine.getUri());
                this.e = requestLine.getMethod();
                this.f = httpRequest.getProtocolVersion();
            } catch (Throwable e) {
                throw new ProtocolException("Invalid request URI: " + requestLine.getUri(), e);
            }
        }
        this.g = 0;
    }

    public void resetHeaders() {
        this.a.clear();
        setHeaders(this.c.getAllHeaders());
    }

    public String getMethod() {
        return this.e;
    }

    public void setMethod(String str) {
        Args.notNull(str, "Method name");
        this.e = str;
    }

    public ProtocolVersion getProtocolVersion() {
        if (this.f == null) {
            this.f = HttpProtocolParams.getVersion(getParams());
        }
        return this.f;
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.f = protocolVersion;
    }

    public URI getURI() {
        return this.d;
    }

    public void setURI(URI uri) {
        this.d = uri;
    }

    public RequestLine getRequestLine() {
        ProtocolVersion protocolVersion = getProtocolVersion();
        String str = null;
        if (this.d != null) {
            str = this.d.toASCIIString();
        }
        if (str == null || str.isEmpty()) {
            str = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        return new BasicRequestLine(getMethod(), str, protocolVersion);
    }

    public void abort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public boolean isAborted() {
        return false;
    }

    public HttpRequest getOriginal() {
        return this.c;
    }

    public boolean isRepeatable() {
        return true;
    }

    public int getExecCount() {
        return this.g;
    }

    public void incrementExecCount() {
        this.g++;
    }
}
