package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@Immutable
class z {
    private static final String[] a = new String[]{"s-maxage", HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE, HeaderConstants.PUBLIC};
    private static final Set<Integer> e = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(200), Integer.valueOf(203), Integer.valueOf(300), Integer.valueOf(301), Integer.valueOf(HttpStatus.SC_GONE)}));
    private final long b;
    private final boolean c;
    private final boolean d;
    private final Set<Integer> f;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public z(long j, boolean z, boolean z2, boolean z3) {
        this.b = j;
        this.c = z;
        this.d = z2;
        if (z3) {
            this.f = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(HttpStatus.SC_PARTIAL_CONTENT)}));
            return;
        }
        this.f = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(HttpStatus.SC_PARTIAL_CONTENT), Integer.valueOf(303)}));
    }

    public boolean isResponseCacheable(String str, HttpResponse httpResponse) {
        if ("GET".equals(str) || "HEAD".equals(str)) {
            boolean z;
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (e.contains(Integer.valueOf(statusCode))) {
                z = true;
            } else if (this.f.contains(Integer.valueOf(statusCode)) || a(statusCode)) {
                return false;
            } else {
                z = false;
            }
            Header firstHeader = httpResponse.getFirstHeader("Content-Length");
            if ((firstHeader != null && ((long) Integer.parseInt(firstHeader.getValue())) > this.b) || httpResponse.getHeaders("Age").length > 1 || httpResponse.getHeaders("Expires").length > 1) {
                return false;
            }
            Header[] headers = httpResponse.getHeaders("Date");
            if (headers.length != 1 || DateUtils.parseDate(headers[0].getValue()) == null) {
                return false;
            }
            for (Header elements : httpResponse.getHeaders("Vary")) {
                for (HeaderElement name : elements.getElements()) {
                    if ("*".equals(name.getName())) {
                        return false;
                    }
                }
            }
            if (a(httpResponse)) {
                return false;
            }
            if (z || b(httpResponse)) {
                return true;
            }
            return false;
        }
        this.log.debug("Response was not cacheable.");
        return false;
    }

    private boolean a(int i) {
        if (i >= 100 && i <= 101) {
            return false;
        }
        if (i >= 200 && i <= HttpStatus.SC_PARTIAL_CONTENT) {
            return false;
        }
        if (i >= 300 && i <= 307) {
            return false;
        }
        if (i >= 400 && i <= HttpStatus.SC_EXPECTATION_FAILED) {
            return false;
        }
        if (i < 500 || i > 505) {
            return true;
        }
        return false;
    }

    protected boolean a(HttpResponse httpResponse) {
        for (Header elements : httpResponse.getHeaders("Cache-Control")) {
            for (HeaderElement headerElement : elements.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_NO_STORE.equals(headerElement.getName()) || HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(headerElement.getName()) || (this.c && "private".equals(headerElement.getName()))) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean a(HttpMessage httpMessage, String[] strArr) {
        for (Header elements : httpMessage.getHeaders("Cache-Control")) {
            for (HeaderElement headerElement : elements.getElements()) {
                for (String equalsIgnoreCase : strArr) {
                    if (equalsIgnoreCase.equalsIgnoreCase(headerElement.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean b(HttpResponse httpResponse) {
        if (httpResponse.getFirstHeader("Expires") != null) {
            return true;
        }
        return a(httpResponse, new String[]{"max-age", "s-maxage", HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE, HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE, HeaderConstants.PUBLIC});
    }

    public boolean isResponseCacheable(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (a(httpRequest)) {
            this.log.debug("Response was not cacheable.");
            return false;
        }
        if (a(httpRequest, new String[]{HeaderConstants.CACHE_CONTROL_NO_STORE})) {
            return false;
        }
        if (httpRequest.getRequestLine().getUri().contains("?")) {
            if (this.d && d(httpResponse)) {
                this.log.debug("Response was not cacheable as it had a query string.");
                return false;
            } else if (!b(httpResponse)) {
                this.log.debug("Response was not cacheable as it is missing explicit caching headers.");
                return false;
            }
        }
        if (c(httpResponse)) {
            return false;
        }
        if (this.c) {
            Header[] headers = httpRequest.getHeaders("Authorization");
            if (!(headers == null || headers.length <= 0 || a(httpResponse, a))) {
                return false;
            }
        }
        return isResponseCacheable(httpRequest.getRequestLine().getMethod(), httpResponse);
    }

    private boolean c(HttpResponse httpResponse) {
        if (httpResponse.getFirstHeader("Cache-Control") != null) {
            return false;
        }
        Header firstHeader = httpResponse.getFirstHeader("Expires");
        Header firstHeader2 = httpResponse.getFirstHeader("Date");
        if (firstHeader == null || firstHeader2 == null) {
            return false;
        }
        Date parseDate = DateUtils.parseDate(firstHeader.getValue());
        Date parseDate2 = DateUtils.parseDate(firstHeader2.getValue());
        if (parseDate == null || parseDate2 == null) {
            return false;
        }
        if (parseDate.equals(parseDate2) || parseDate.before(parseDate2)) {
            return true;
        }
        return false;
    }

    private boolean d(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Via");
        if (firstHeader != null) {
            HeaderElement[] elements = firstHeader.getElements();
            if (0 < elements.length) {
                String str = elements[0].toString().split("\\s")[0];
                if (str.contains(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                    return str.equals("HTTP/1.0");
                }
                return str.equals("1.0");
            }
        }
        return HttpVersion.HTTP_1_0.equals(httpResponse.getProtocolVersion());
    }

    private boolean a(HttpRequest httpRequest) {
        return httpRequest.getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) > 0;
    }
}
