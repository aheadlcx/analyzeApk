package cz.msebera.android.httpclient.impl.client.cache;

import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import cz.msebera.android.httpclient.message.BasicStatusLine;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Immutable
class v {
    private static final List<String> b = Arrays.asList(new String[]{HeaderConstants.CACHE_CONTROL_MIN_FRESH, HeaderConstants.CACHE_CONTROL_MAX_STALE, "max-age"});
    private final boolean a;

    public v() {
        this.a = false;
    }

    public v(boolean z) {
        this.a = z;
    }

    public List<x> requestIsFatallyNonCompliant(HttpRequest httpRequest) {
        List<x> arrayList = new ArrayList();
        x j = j(httpRequest);
        if (j != null) {
            arrayList.add(j);
        }
        if (!this.a) {
            j = k(httpRequest);
            if (j != null) {
                arrayList.add(j);
            }
        }
        j = l(httpRequest);
        if (j != null) {
            arrayList.add(j);
        }
        return arrayList;
    }

    public void makeRequestCompliant(HttpRequestWrapper httpRequestWrapper) throws ClientProtocolException {
        if (d(httpRequestWrapper)) {
            ((HttpEntityEnclosingRequest) httpRequestWrapper).setEntity(null);
        }
        g(httpRequestWrapper);
        f(httpRequestWrapper);
        e(httpRequestWrapper);
        c(httpRequestWrapper);
        if (b(httpRequestWrapper) || a((HttpRequest) httpRequestWrapper)) {
            httpRequestWrapper.setProtocolVersion(HttpVersion.HTTP_1_1);
        }
    }

    private void c(HttpRequest httpRequest) {
        List arrayList = new ArrayList();
        Object obj = null;
        for (Header elements : httpRequest.getHeaders("Cache-Control")) {
            for (HeaderElement headerElement : elements.getElements()) {
                if (!b.contains(headerElement.getName())) {
                    arrayList.add(headerElement);
                }
                if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(headerElement.getName())) {
                    obj = 1;
                }
            }
        }
        if (obj != null) {
            httpRequest.removeHeaders("Cache-Control");
            httpRequest.setHeader("Cache-Control", a(arrayList));
        }
    }

    private String a(List<HeaderElement> list) {
        StringBuilder stringBuilder = new StringBuilder("");
        Object obj = 1;
        for (HeaderElement headerElement : list) {
            if (obj == null) {
                stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            } else {
                obj = null;
            }
            stringBuilder.append(headerElement.toString());
        }
        return stringBuilder.toString();
    }

    private boolean d(HttpRequest httpRequest) {
        return "TRACE".equals(httpRequest.getRequestLine().getMethod()) && (httpRequest instanceof HttpEntityEnclosingRequest);
    }

    private void e(HttpRequest httpRequest) {
        if ("OPTIONS".equals(httpRequest.getRequestLine().getMethod())) {
            Header firstHeader = httpRequest.getFirstHeader("Max-Forwards");
            if (firstHeader != null) {
                httpRequest.removeHeaders("Max-Forwards");
                httpRequest.setHeader("Max-Forwards", Integer.toString(Integer.parseInt(firstHeader.getValue()) - 1));
            }
        }
    }

    private void f(HttpRequest httpRequest) {
        if ("OPTIONS".equals(httpRequest.getRequestLine().getMethod()) && (httpRequest instanceof HttpEntityEnclosingRequest)) {
            a((HttpEntityEnclosingRequest) httpRequest);
        }
    }

    private void a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        if (httpEntityEnclosingRequest.getEntity().getContentType() == null) {
            ((AbstractHttpEntity) httpEntityEnclosingRequest.getEntity()).setContentType(ContentType.APPLICATION_OCTET_STREAM.getMimeType());
        }
    }

    private void g(HttpRequest httpRequest) {
        if (!(httpRequest instanceof HttpEntityEnclosingRequest)) {
            h(httpRequest);
        } else if (!((HttpEntityEnclosingRequest) httpRequest).expectContinue() || ((HttpEntityEnclosingRequest) httpRequest).getEntity() == null) {
            h(httpRequest);
        } else {
            i(httpRequest);
        }
    }

    private void h(HttpRequest httpRequest) {
        Header[] headers = httpRequest.getHeaders("Expect");
        ArrayList arrayList = new ArrayList();
        List list = arrayList;
        Object obj = null;
        for (Header header : headers) {
            for (HeaderElement headerElement : header.getElements()) {
                if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(headerElement.getName())) {
                    obj = 1;
                } else {
                    r4.add(headerElement);
                }
            }
            if (obj != null) {
                httpRequest.removeHeader(header);
                for (HeaderElement name : r4) {
                    httpRequest.addHeader(new BasicHeader("Expect", name.getName()));
                }
                return;
            }
            list = new ArrayList();
        }
    }

    private void i(HttpRequest httpRequest) {
        Object obj = null;
        for (Header elements : httpRequest.getHeaders("Expect")) {
            for (HeaderElement name : elements.getElements()) {
                if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(name.getName())) {
                    obj = 1;
                }
            }
        }
        if (obj == null) {
            httpRequest.addHeader("Expect", HTTP.EXPECT_CONTINUE);
        }
    }

    protected boolean a(HttpRequest httpRequest) {
        ProtocolVersion protocolVersion = httpRequest.getProtocolVersion();
        if (protocolVersion.getMajor() == HttpVersion.HTTP_1_1.getMajor() && protocolVersion.getMinor() > HttpVersion.HTTP_1_1.getMinor()) {
            return true;
        }
        return false;
    }

    protected boolean b(HttpRequest httpRequest) {
        return httpRequest.getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) < 0;
    }

    public HttpResponse getErrorForRequest(x xVar) {
        switch (w.a[xVar.ordinal()]) {
            case 1:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_LENGTH_REQUIRED, ""));
            case 2:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 400, "Weak eTag not compatible with byte range"));
            case 3:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 400, "Weak eTag not compatible with PUT or DELETE requests"));
            case 4:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 400, "No-Cache directive MUST NOT include a field name"));
            default:
                throw new IllegalStateException("The request was compliant, therefore no error can be generated for it.");
        }
    }

    private x j(HttpRequest httpRequest) {
        if (!"GET".equals(httpRequest.getRequestLine().getMethod()) || httpRequest.getFirstHeader("Range") == null) {
            return null;
        }
        Header firstHeader = httpRequest.getFirstHeader("If-Range");
        if (firstHeader == null || !firstHeader.getValue().startsWith("W/")) {
            return null;
        }
        return x.WEAK_ETAG_AND_RANGE_ERROR;
    }

    private x k(HttpRequest httpRequest) {
        String method = httpRequest.getRequestLine().getMethod();
        if (!"PUT".equals(method) && !"DELETE".equals(method)) {
            return null;
        }
        Header firstHeader = httpRequest.getFirstHeader("If-Match");
        if (firstHeader == null) {
            firstHeader = httpRequest.getFirstHeader("If-None-Match");
            if (firstHeader == null || !firstHeader.getValue().startsWith("W/")) {
                return null;
            }
            return x.WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR;
        } else if (firstHeader.getValue().startsWith("W/")) {
            return x.WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR;
        } else {
            return null;
        }
    }

    private x l(HttpRequest httpRequest) {
        for (Header elements : httpRequest.getHeaders("Cache-Control")) {
            for (HeaderElement headerElement : elements.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equalsIgnoreCase(headerElement.getName()) && headerElement.getValue() != null) {
                    return x.NO_CACHE_DIRECTIVE_WITH_FIELD_NAME;
                }
            }
        }
        return null;
    }
}
