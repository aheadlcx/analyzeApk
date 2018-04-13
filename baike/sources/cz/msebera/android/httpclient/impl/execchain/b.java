package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.params.HttpParams;
import java.io.IOException;
import java.util.Locale;

@NotThreadSafe
class b implements CloseableHttpResponse {
    private final HttpResponse a;
    private final a b;

    public b(HttpResponse httpResponse, a aVar) {
        this.a = httpResponse;
        this.b = aVar;
        d.enchance(httpResponse, aVar);
    }

    public void close() throws IOException {
        if (this.b != null) {
            this.b.abortConnection();
        }
    }

    public StatusLine getStatusLine() {
        return this.a.getStatusLine();
    }

    public void setStatusLine(StatusLine statusLine) {
        this.a.setStatusLine(statusLine);
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i) {
        this.a.setStatusLine(protocolVersion, i);
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        this.a.setStatusLine(protocolVersion, i, str);
    }

    public void setStatusCode(int i) throws IllegalStateException {
        this.a.setStatusCode(i);
    }

    public void setReasonPhrase(String str) throws IllegalStateException {
        this.a.setReasonPhrase(str);
    }

    public HttpEntity getEntity() {
        return this.a.getEntity();
    }

    public void setEntity(HttpEntity httpEntity) {
        this.a.setEntity(httpEntity);
    }

    public Locale getLocale() {
        return this.a.getLocale();
    }

    public void setLocale(Locale locale) {
        this.a.setLocale(locale);
    }

    public ProtocolVersion getProtocolVersion() {
        return this.a.getProtocolVersion();
    }

    public boolean containsHeader(String str) {
        return this.a.containsHeader(str);
    }

    public Header[] getHeaders(String str) {
        return this.a.getHeaders(str);
    }

    public Header getFirstHeader(String str) {
        return this.a.getFirstHeader(str);
    }

    public Header getLastHeader(String str) {
        return this.a.getLastHeader(str);
    }

    public Header[] getAllHeaders() {
        return this.a.getAllHeaders();
    }

    public void addHeader(Header header) {
        this.a.addHeader(header);
    }

    public void addHeader(String str, String str2) {
        this.a.addHeader(str, str2);
    }

    public void setHeader(Header header) {
        this.a.setHeader(header);
    }

    public void setHeader(String str, String str2) {
        this.a.setHeader(str, str2);
    }

    public void setHeaders(Header[] headerArr) {
        this.a.setHeaders(headerArr);
    }

    public void removeHeader(Header header) {
        this.a.removeHeader(header);
    }

    public void removeHeaders(String str) {
        this.a.removeHeaders(str);
    }

    public HeaderIterator headerIterator() {
        return this.a.headerIterator();
    }

    public HeaderIterator headerIterator(String str) {
        return this.a.headerIterator(str);
    }

    @Deprecated
    public HttpParams getParams() {
        return this.a.getParams();
    }

    @Deprecated
    public void setParams(HttpParams httpParams) {
        this.a.setParams(httpParams);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpResponseProxy{");
        stringBuilder.append(this.a);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
