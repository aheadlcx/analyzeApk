package cz.msebera.android.httpclient.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.message.HeaderGroup;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Immutable
public class HttpCacheEntry implements Serializable {
    private final Date a;
    private final Date b;
    private final StatusLine c;
    private final HeaderGroup d;
    private final Resource e;
    private final Map<String, String> f;
    private final Date g;

    public HttpCacheEntry(Date date, Date date2, StatusLine statusLine, Header[] headerArr, Resource resource, Map<String, String> map, String str) {
        Args.notNull(date, "Request date");
        Args.notNull(date2, "Response date");
        Args.notNull(statusLine, "Status line");
        Args.notNull(headerArr, "Response headers");
        this.a = date;
        this.b = date2;
        this.c = statusLine;
        this.d = new HeaderGroup();
        this.d.setHeaders(headerArr);
        this.e = resource;
        this.f = map != null ? new HashMap(map) : null;
        this.g = a();
    }

    public HttpCacheEntry(Date date, Date date2, StatusLine statusLine, Header[] headerArr, Resource resource, Map<String, String> map) {
        this(date, date2, statusLine, headerArr, resource, map, null);
    }

    public HttpCacheEntry(Date date, Date date2, StatusLine statusLine, Header[] headerArr, Resource resource) {
        this(date, date2, statusLine, headerArr, resource, new HashMap());
    }

    public HttpCacheEntry(Date date, Date date2, StatusLine statusLine, Header[] headerArr, Resource resource, String str) {
        this(date, date2, statusLine, headerArr, resource, new HashMap(), str);
    }

    private Date a() {
        Header firstHeader = getFirstHeader("Date");
        if (firstHeader == null) {
            return null;
        }
        return DateUtils.parseDate(firstHeader.getValue());
    }

    public StatusLine getStatusLine() {
        return this.c;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.c.getProtocolVersion();
    }

    public String getReasonPhrase() {
        return this.c.getReasonPhrase();
    }

    public int getStatusCode() {
        return this.c.getStatusCode();
    }

    public Date getRequestDate() {
        return this.a;
    }

    public Date getResponseDate() {
        return this.b;
    }

    public Header[] getAllHeaders() {
        HeaderGroup headerGroup = new HeaderGroup();
        HeaderIterator it = this.d.iterator();
        while (it.hasNext()) {
            Header header = (Header) it.next();
            if (!"Hc-Request-Method".equals(header.getName())) {
                headerGroup.addHeader(header);
            }
        }
        return headerGroup.getAllHeaders();
    }

    public Header getFirstHeader(String str) {
        if ("Hc-Request-Method".equalsIgnoreCase(str)) {
            return null;
        }
        return this.d.getFirstHeader(str);
    }

    public Header[] getHeaders(String str) {
        if ("Hc-Request-Method".equalsIgnoreCase(str)) {
            return new Header[0];
        }
        return this.d.getHeaders(str);
    }

    public Date getDate() {
        return this.g;
    }

    public Resource getResource() {
        return this.e;
    }

    public boolean hasVariants() {
        return getFirstHeader("Vary") != null;
    }

    public Map<String, String> getVariantMap() {
        return Collections.unmodifiableMap(this.f);
    }

    public String getRequestMethod() {
        Header firstHeader = this.d.getFirstHeader("Hc-Request-Method");
        if (firstHeader != null) {
            return firstHeader.getValue();
        }
        return "GET";
    }

    public String toString() {
        return "[request date=" + this.a + "; response date=" + this.b + "; statusLine=" + this.c + "]";
    }
}
