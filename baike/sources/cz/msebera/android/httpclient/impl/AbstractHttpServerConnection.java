package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.HttpConnectionMetrics;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestFactory;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpServerConnection;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.impl.entity.DisallowIdentityContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.EntityDeserializer;
import cz.msebera.android.httpclient.impl.entity.EntitySerializer;
import cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy;
import cz.msebera.android.httpclient.impl.io.DefaultHttpRequestParser;
import cz.msebera.android.httpclient.impl.io.HttpResponseWriter;
import cz.msebera.android.httpclient.io.EofSensor;
import cz.msebera.android.httpclient.io.HttpMessageParser;
import cz.msebera.android.httpclient.io.HttpMessageWriter;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;

@NotThreadSafe
@Deprecated
public abstract class AbstractHttpServerConnection implements HttpServerConnection {
    private final EntitySerializer a = c();
    private final EntityDeserializer b = b();
    private SessionInputBuffer c = null;
    private SessionOutputBuffer d = null;
    private EofSensor e = null;
    private HttpMessageParser<HttpRequest> f = null;
    private HttpMessageWriter<HttpResponse> g = null;
    private HttpConnectionMetricsImpl h = null;

    protected abstract void a() throws IllegalStateException;

    protected EntityDeserializer b() {
        return new EntityDeserializer(new DisallowIdentityContentLengthStrategy(new LaxContentLengthStrategy(0)));
    }

    protected EntitySerializer c() {
        return new EntitySerializer(new StrictContentLengthStrategy());
    }

    protected HttpRequestFactory d() {
        return DefaultHttpRequestFactory.INSTANCE;
    }

    protected HttpMessageParser<HttpRequest> a(SessionInputBuffer sessionInputBuffer, HttpRequestFactory httpRequestFactory, HttpParams httpParams) {
        return new DefaultHttpRequestParser(sessionInputBuffer, null, httpRequestFactory, httpParams);
    }

    protected HttpMessageWriter<HttpResponse> a(SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        return new HttpResponseWriter(sessionOutputBuffer, null, httpParams);
    }

    protected HttpConnectionMetricsImpl a(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        return new HttpConnectionMetricsImpl(httpTransportMetrics, httpTransportMetrics2);
    }

    protected void a(SessionInputBuffer sessionInputBuffer, SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        this.c = (SessionInputBuffer) Args.notNull(sessionInputBuffer, "Input session buffer");
        this.d = (SessionOutputBuffer) Args.notNull(sessionOutputBuffer, "Output session buffer");
        if (sessionInputBuffer instanceof EofSensor) {
            this.e = (EofSensor) sessionInputBuffer;
        }
        this.f = a(sessionInputBuffer, d(), httpParams);
        this.g = a(sessionOutputBuffer, httpParams);
        this.h = a(sessionInputBuffer.getMetrics(), sessionOutputBuffer.getMetrics());
    }

    public HttpRequest receiveRequestHeader() throws HttpException, IOException {
        a();
        HttpRequest httpRequest = (HttpRequest) this.f.parse();
        this.h.incrementRequestCount();
        return httpRequest;
    }

    public void receiveRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        Args.notNull(httpEntityEnclosingRequest, "HTTP request");
        a();
        httpEntityEnclosingRequest.setEntity(this.b.deserialize(this.c, httpEntityEnclosingRequest));
    }

    protected void e() throws IOException {
        this.d.flush();
    }

    public void flush() throws IOException {
        a();
        e();
    }

    public void sendResponseHeader(HttpResponse httpResponse) throws HttpException, IOException {
        Args.notNull(httpResponse, "HTTP response");
        a();
        this.g.write(httpResponse);
        if (httpResponse.getStatusLine().getStatusCode() >= 200) {
            this.h.incrementResponseCount();
        }
    }

    public void sendResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        if (httpResponse.getEntity() != null) {
            this.a.serialize(this.d, httpResponse, httpResponse.getEntity());
        }
    }

    protected boolean f() {
        return this.e != null && this.e.isEof();
    }

    public boolean isStale() {
        boolean z = true;
        if (isOpen() && !f()) {
            try {
                this.c.isDataAvailable(1);
                z = f();
            } catch (IOException e) {
            }
        }
        return z;
    }

    public HttpConnectionMetrics getMetrics() {
        return this.h;
    }
}
