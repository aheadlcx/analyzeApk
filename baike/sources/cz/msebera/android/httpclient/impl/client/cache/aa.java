package cz.msebera.android.httpclient.impl.client.cache;

import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Immutable
class aa {
    aa() {
    }

    public void ensureProtocolCompliance(HttpRequestWrapper httpRequestWrapper, HttpResponse httpResponse) throws IOException {
        if (c(httpRequestWrapper, httpResponse)) {
            a(httpResponse);
            httpResponse.setEntity(null);
        }
        a(httpRequestWrapper, httpResponse);
        b(httpRequestWrapper, httpResponse);
        a((HttpRequest) httpRequestWrapper, httpResponse);
        b((HttpRequest) httpRequestWrapper, httpResponse);
        d(httpResponse);
        e(httpResponse);
        c(httpResponse);
        b(httpResponse);
    }

    private void a(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            s.a(entity);
        }
    }

    private void b(HttpResponse httpResponse) {
        Date parseDate = DateUtils.parseDate(httpResponse.getFirstHeader("Date").getValue());
        if (parseDate != null) {
            Header[] headers = httpResponse.getHeaders("Warning");
            if (headers != null && headers.length != 0) {
                List<Header> arrayList = new ArrayList();
                Object obj = null;
                for (Header warningValues : headers) {
                    for (af afVar : af.getWarningValues(warningValues)) {
                        Date warnDate = afVar.getWarnDate();
                        if (warnDate == null || warnDate.equals(parseDate)) {
                            arrayList.add(new BasicHeader("Warning", afVar.toString()));
                        } else {
                            obj = 1;
                        }
                    }
                }
                if (obj != null) {
                    httpResponse.removeHeaders("Warning");
                    for (Header addHeader : arrayList) {
                        httpResponse.addHeader(addHeader);
                    }
                }
            }
        }
    }

    private void c(HttpResponse httpResponse) {
        Header[] headers = httpResponse.getHeaders("Content-Encoding");
        if (headers != null && headers.length != 0) {
            Header header;
            List<Header> arrayList = new ArrayList();
            Object obj = null;
            int length = headers.length;
            int i = 0;
            while (i < length) {
                header = headers[i];
                StringBuilder stringBuilder = new StringBuilder();
                Object obj2 = 1;
                Object obj3 = obj;
                for (HeaderElement headerElement : header.getElements()) {
                    if (HTTP.IDENTITY_CODING.equalsIgnoreCase(headerElement.getName())) {
                        obj3 = 1;
                    } else {
                        if (obj2 == null) {
                            stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        }
                        stringBuilder.append(headerElement.toString());
                        obj2 = null;
                    }
                }
                String stringBuilder2 = stringBuilder.toString();
                if (!"".equals(stringBuilder2)) {
                    arrayList.add(new BasicHeader("Content-Encoding", stringBuilder2));
                }
                i++;
                obj = obj3;
            }
            if (obj != null) {
                httpResponse.removeHeaders("Content-Encoding");
                for (Header header2 : arrayList) {
                    httpResponse.addHeader(header2);
                }
            }
        }
    }

    private void d(HttpResponse httpResponse) {
        if (httpResponse.getFirstHeader("Date") == null) {
            httpResponse.addHeader("Date", DateUtils.formatDate(new Date()));
        }
    }

    private void a(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        if (httpRequest.getFirstHeader("Range") == null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT) {
            a(httpResponse);
            throw new ClientProtocolException("partial content was returned for a request that did not ask for it");
        }
    }

    private void b(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest.getRequestLine().getMethod().equalsIgnoreCase("OPTIONS") && httpResponse.getStatusLine().getStatusCode() == 200 && httpResponse.getFirstHeader("Content-Length") == null) {
            httpResponse.addHeader("Content-Length", "0");
        }
    }

    private void e(HttpResponse httpResponse) {
        int i = 0;
        String[] strArr = new String[]{"Allow", "Content-Encoding", HttpHeaders.CONTENT_LANGUAGE, "Content-Length", HttpHeaders.CONTENT_MD5, "Content-Range", "Content-Type", "Last-Modified"};
        if (httpResponse.getStatusLine().getStatusCode() == 304) {
            int length = strArr.length;
            while (i < length) {
                httpResponse.removeHeaders(strArr[i]);
                i++;
            }
        }
    }

    private boolean c(HttpRequest httpRequest, HttpResponse httpResponse) {
        return "HEAD".equals(httpRequest.getRequestLine().getMethod()) || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_RESET_CONTENT || httpResponse.getStatusLine().getStatusCode() == 304;
    }

    private void a(HttpRequestWrapper httpRequestWrapper, HttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusLine().getStatusCode() == 100) {
            HttpRequest original = httpRequestWrapper.getOriginal();
            if (!(original instanceof HttpEntityEnclosingRequest) || !((HttpEntityEnclosingRequest) original).expectContinue()) {
                a(httpResponse);
                throw new ClientProtocolException("The incoming request did not contain a 100-continue header, but the response was a Status 100, continue.");
            }
        }
    }

    private void b(HttpRequestWrapper httpRequestWrapper, HttpResponse httpResponse) {
        if (httpRequestWrapper.getOriginal().getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) < 0) {
            f(httpResponse);
        }
    }

    private void f(HttpResponse httpResponse) {
        httpResponse.removeHeaders(HttpHeaders.TE);
        httpResponse.removeHeaders("Transfer-Encoding");
    }
}
