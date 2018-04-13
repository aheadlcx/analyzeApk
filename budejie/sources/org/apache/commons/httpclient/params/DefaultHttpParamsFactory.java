package org.apache.commons.httpclient.params;

import com.ali.auth.third.core.model.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.cookie.CookiePolicy;

public class DefaultHttpParamsFactory implements HttpParamsFactory {
    static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
    private HttpParams httpParams;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public synchronized HttpParams getDefaultParams() {
        if (this.httpParams == null) {
            this.httpParams = createParams();
        }
        return this.httpParams;
    }

    protected HttpParams createParams() {
        Class class$;
        Object property;
        String property2;
        HttpParams httpClientParams = new HttpClientParams(null);
        httpClientParams.setParameter(HttpMethodParams.USER_AGENT, "Jakarta Commons-HttpClient/3.0.1");
        httpClientParams.setVersion(HttpVersion.HTTP_1_1);
        if (class$org$apache$commons$httpclient$SimpleHttpConnectionManager == null) {
            class$ = class$("org.apache.commons.httpclient.SimpleHttpConnectionManager");
            class$org$apache$commons$httpclient$SimpleHttpConnectionManager = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
        }
        httpClientParams.setConnectionManagerClass(class$);
        httpClientParams.setCookiePolicy(CookiePolicy.RFC_2109);
        httpClientParams.setHttpElementCharset("US-ASCII");
        httpClientParams.setContentCharset("ISO-8859-1");
        httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"}));
        httpClientParams.setParameter(HttpMethodParams.DATE_PATTERNS, arrayList);
        try {
            property = System.getProperty("httpclient.useragent");
        } catch (SecurityException e) {
            property = null;
        }
        if (property != null) {
            httpClientParams.setParameter(HttpMethodParams.USER_AGENT, property);
        }
        try {
            property2 = System.getProperty(HttpState.PREEMPTIVE_PROPERTY);
        } catch (SecurityException e2) {
            property2 = null;
        }
        if (property2 != null) {
            property2 = property2.trim().toLowerCase();
            if (property2.equals(Constants.SERVICE_SCOPE_FLAG_VALUE)) {
                httpClientParams.setParameter(HttpClientParams.PREEMPTIVE_AUTHENTICATION, Boolean.TRUE);
            } else if (property2.equals(HttpState.PREEMPTIVE_DEFAULT)) {
                httpClientParams.setParameter(HttpClientParams.PREEMPTIVE_AUTHENTICATION, Boolean.FALSE);
            }
        }
        try {
            property2 = System.getProperty("apache.commons.httpclient.cookiespec");
        } catch (SecurityException e3) {
            property2 = null;
        }
        if (property2 != null) {
            if ("COMPATIBILITY".equalsIgnoreCase(property2)) {
                httpClientParams.setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            } else if ("NETSCAPE_DRAFT".equalsIgnoreCase(property2)) {
                httpClientParams.setCookiePolicy(CookiePolicy.NETSCAPE);
            } else if ("RFC2109".equalsIgnoreCase(property2)) {
                httpClientParams.setCookiePolicy(CookiePolicy.RFC_2109);
            }
        }
        return httpClientParams;
    }
}
