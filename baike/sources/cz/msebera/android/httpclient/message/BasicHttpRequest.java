package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;

@NotThreadSafe
public class BasicHttpRequest extends AbstractHttpMessage implements HttpRequest {
    private final String c;
    private final String d;
    private RequestLine e;

    public BasicHttpRequest(String str, String str2) {
        this.c = (String) Args.notNull(str, "Method name");
        this.d = (String) Args.notNull(str2, "Request URI");
        this.e = null;
    }

    public BasicHttpRequest(String str, String str2, ProtocolVersion protocolVersion) {
        this(new BasicRequestLine(str, str2, protocolVersion));
    }

    public BasicHttpRequest(RequestLine requestLine) {
        this.e = (RequestLine) Args.notNull(requestLine, "Request line");
        this.c = requestLine.getMethod();
        this.d = requestLine.getUri();
    }

    public ProtocolVersion getProtocolVersion() {
        return getRequestLine().getProtocolVersion();
    }

    public RequestLine getRequestLine() {
        if (this.e == null) {
            this.e = new BasicRequestLine(this.c, this.d, HttpVersion.HTTP_1_1);
        }
        return this.e;
    }

    public String toString() {
        return this.c + TokenParser.SP + this.d + TokenParser.SP + this.a;
    }
}
