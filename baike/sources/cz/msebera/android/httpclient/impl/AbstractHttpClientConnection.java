package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpConnectionMetrics;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.impl.entity.EntityDeserializer;
import cz.msebera.android.httpclient.impl.entity.EntitySerializer;
import cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy;
import cz.msebera.android.httpclient.impl.io.DefaultHttpResponseParser;
import cz.msebera.android.httpclient.impl.io.HttpRequestWriter;
import cz.msebera.android.httpclient.io.EofSensor;
import cz.msebera.android.httpclient.io.HttpMessageParser;
import cz.msebera.android.httpclient.io.HttpMessageWriter;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.net.SocketTimeoutException;

@NotThreadSafe
@Deprecated
public abstract class AbstractHttpClientConnection implements HttpClientConnection {
    private final EntitySerializer a = c();
    private final EntityDeserializer b = b();
    private SessionInputBuffer c = null;
    private SessionOutputBuffer d = null;
    private EofSensor e = null;
    private HttpMessageParser<HttpResponse> f = null;
    private HttpMessageWriter<HttpRequest> g = null;
    private HttpConnectionMetricsImpl h = null;

    protected abstract void a() throws IllegalStateException;

    protected EntityDeserializer b() {
        return new EntityDeserializer(new LaxContentLengthStrategy());
    }

    protected EntitySerializer c() {
        return new EntitySerializer(new StrictContentLengthStrategy());
    }

    protected HttpResponseFactory d() {
        return DefaultHttpResponseFactory.INSTANCE;
    }

    protected HttpMessageParser<HttpResponse> a(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new DefaultHttpResponseParser(sessionInputBuffer, null, httpResponseFactory, httpParams);
    }

    protected HttpMessageWriter<HttpRequest> a(SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        return new HttpRequestWriter(sessionOutputBuffer, null, httpParams);
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

    public boolean isResponseAvailable(int i) throws IOException {
        a();
        try {
            return this.c.isDataAvailable(i);
        } catch (SocketTimeoutException e) {
            return false;
        }
    }

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        a();
        this.g.write(httpRequest);
        this.h.incrementRequestCount();
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        Args.notNull(httpEntityEnclosingRequest, "HTTP request");
        a();
        if (httpEntityEnclosingRequest.getEntity() != null) {
            this.a.serialize(this.d, httpEntityEnclosingRequest, httpEntityEnclosingRequest.getEntity());
        }
    }

    protected void e() throws IOException {
        this.d.flush();
    }

    public void flush() throws IOException {
        a();
        e();
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        a();
        HttpResponse httpResponse = (HttpResponse) this.f.parse();
        if (httpResponse.getStatusLine().getStatusCode() >= 200) {
            this.h.incrementResponseCount();
        }
        return httpResponse;
    }

    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        Args.notNull(httpResponse, "HTTP response");
        a();
        httpResponse.setEntity(this.b.deserialize(this.c, httpResponse));
    }

    protected boolean f() {
        return this.e != null && this.e.isEof();
    }

    public boolean isStale() {
        boolean z = true;
        if (!isOpen() || f()) {
            return z;
        }
        try {
            this.c.isDataAvailable(1);
            return f();
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return z;
        }
    }

    public HttpConnectionMetrics getMetrics() {
        return this.h;
    }
}
