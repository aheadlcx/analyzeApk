package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;

@NotThreadSafe
public abstract class AbstractHttpMessage implements HttpMessage {
    protected HeaderGroup a;
    @Deprecated
    protected HttpParams b;

    @Deprecated
    protected AbstractHttpMessage(HttpParams httpParams) {
        this.a = new HeaderGroup();
        this.b = httpParams;
    }

    protected AbstractHttpMessage() {
        this(null);
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
        Args.notNull(str, "Header name");
        this.a.addHeader(new BasicHeader(str, str2));
    }

    public void setHeader(Header header) {
        this.a.updateHeader(header);
    }

    public void setHeader(String str, String str2) {
        Args.notNull(str, "Header name");
        this.a.updateHeader(new BasicHeader(str, str2));
    }

    public void setHeaders(Header[] headerArr) {
        this.a.setHeaders(headerArr);
    }

    public void removeHeader(Header header) {
        this.a.removeHeader(header);
    }

    public void removeHeaders(String str) {
        if (str != null) {
            HeaderIterator it = this.a.iterator();
            while (it.hasNext()) {
                if (str.equalsIgnoreCase(it.nextHeader().getName())) {
                    it.remove();
                }
            }
        }
    }

    public HeaderIterator headerIterator() {
        return this.a.iterator();
    }

    public HeaderIterator headerIterator(String str) {
        return this.a.iterator(str);
    }

    @Deprecated
    public HttpParams getParams() {
        if (this.b == null) {
            this.b = new BasicHttpParams();
        }
        return this.b;
    }

    @Deprecated
    public void setParams(HttpParams httpParams) {
        this.b = (HttpParams) Args.notNull(httpParams, "HTTP parameters");
    }
}
