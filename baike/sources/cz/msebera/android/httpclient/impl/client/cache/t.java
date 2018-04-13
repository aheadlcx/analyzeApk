package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.message.AbstractHttpMessage;
import cz.msebera.android.httpclient.message.BasicStatusLine;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import java.util.Locale;

@Immutable
final class t extends AbstractHttpMessage implements HttpResponse {
    private final StatusLine c = new BasicStatusLine(HttpVersion.HTTP_1_1, 501, "");
    private final ProtocolVersion d = HttpVersion.HTTP_1_1;

    t() {
    }

    public StatusLine getStatusLine() {
        return this.c;
    }

    public void setStatusLine(StatusLine statusLine) {
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i) {
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i, String str) {
    }

    public void setStatusCode(int i) throws IllegalStateException {
    }

    public void setReasonPhrase(String str) throws IllegalStateException {
    }

    public HttpEntity getEntity() {
        return null;
    }

    public void setEntity(HttpEntity httpEntity) {
    }

    public Locale getLocale() {
        return null;
    }

    public void setLocale(Locale locale) {
    }

    public ProtocolVersion getProtocolVersion() {
        return this.d;
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
    }

    public void addHeader(String str, String str2) {
    }

    public void setHeader(Header header) {
    }

    public void setHeader(String str, String str2) {
    }

    public void setHeaders(Header[] headerArr) {
    }

    public void removeHeader(Header header) {
    }

    public void removeHeaders(String str) {
    }

    public HeaderIterator headerIterator() {
        return this.a.iterator();
    }

    public HeaderIterator headerIterator(String str) {
        return this.a.iterator(str);
    }

    public HttpParams getParams() {
        if (this.b == null) {
            this.b = new BasicHttpParams();
        }
        return this.b;
    }

    public void setParams(HttpParams httpParams) {
    }
}
