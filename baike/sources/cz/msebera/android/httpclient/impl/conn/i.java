package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.io.HttpMessageParserFactory;
import cz.msebera.android.httpclient.io.HttpMessageWriterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

@NotThreadSafe
class i extends DefaultManagedHttpClientConnection {
    private final HttpClientAndroidLog a;
    private final Wire b;
    public HttpClientAndroidLog log;

    public i(String str, HttpClientAndroidLog httpClientAndroidLog, HttpClientAndroidLog httpClientAndroidLog2, HttpClientAndroidLog httpClientAndroidLog3, int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, MessageConstraints messageConstraints, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, HttpMessageWriterFactory<HttpRequest> httpMessageWriterFactory, HttpMessageParserFactory<HttpResponse> httpMessageParserFactory) {
        super(str, i, i2, charsetDecoder, charsetEncoder, messageConstraints, contentLengthStrategy, contentLengthStrategy2, httpMessageWriterFactory, httpMessageParserFactory);
        this.log = httpClientAndroidLog;
        this.a = httpClientAndroidLog2;
        this.b = new Wire(httpClientAndroidLog3, str);
    }

    public void close() throws IOException {
        if (this.log.isDebugEnabled()) {
            this.log.debug(getId() + ": Close connection");
        }
        super.close();
    }

    public void shutdown() throws IOException {
        if (this.log.isDebugEnabled()) {
            this.log.debug(getId() + ": Shutdown connection");
        }
        super.shutdown();
    }

    protected InputStream a(Socket socket) throws IOException {
        InputStream a = super.a(socket);
        if (this.b.enabled()) {
            return new h(a, this.b);
        }
        return a;
    }

    protected OutputStream b(Socket socket) throws IOException {
        OutputStream b = super.b(socket);
        if (this.b.enabled()) {
            return new j(b, this.b);
        }
        return b;
    }

    protected void a(HttpResponse httpResponse) {
        if (httpResponse != null && this.a.isDebugEnabled()) {
            this.a.debug(getId() + " << " + httpResponse.getStatusLine().toString());
            for (Object obj : httpResponse.getAllHeaders()) {
                this.a.debug(getId() + " << " + obj.toString());
            }
        }
    }

    protected void a(HttpRequest httpRequest) {
        if (httpRequest != null && this.a.isDebugEnabled()) {
            this.a.debug(getId() + " >> " + httpRequest.getRequestLine().toString());
            for (Object obj : httpRequest.getAllHeaders()) {
                this.a.debug(getId() + " >> " + obj.toString());
            }
        }
    }
}
