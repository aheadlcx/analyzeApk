package cz.msebera.android.httpclient.client.methods;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.message.BasicRequestLine;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@NotThreadSafe
public abstract class HttpRequestBase extends AbstractExecutionAwareRequest implements Configurable, HttpUriRequest {
    private ProtocolVersion c;
    private URI d;
    private RequestConfig e;

    public abstract String getMethod();

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.c = protocolVersion;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.c != null ? this.c : HttpProtocolParams.getVersion(getParams());
    }

    public URI getURI() {
        return this.d;
    }

    public RequestLine getRequestLine() {
        String method = getMethod();
        ProtocolVersion protocolVersion = getProtocolVersion();
        URI uri = getURI();
        String str = null;
        if (uri != null) {
            str = uri.toASCIIString();
        }
        if (str == null || str.isEmpty()) {
            str = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        return new BasicRequestLine(method, str, protocolVersion);
    }

    public RequestConfig getConfig() {
        return this.e;
    }

    public void setConfig(RequestConfig requestConfig) {
        this.e = requestConfig;
    }

    public void setURI(URI uri) {
        this.d = uri;
    }

    public void started() {
    }

    public void releaseConnection() {
        reset();
    }

    public String toString() {
        return getMethod() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getURI() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getProtocolVersion();
    }
}
