package com.alipay.android.phone.mrpc.core;

import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import org.apache.http.HeaderElement;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public final class Headers {
    public static final String ACCEPT_RANGES = "accept-ranges";
    public static final String CACHE_CONTROL = "cache-control";
    public static final int CONN_CLOSE = 1;
    public static final String CONN_DIRECTIVE = "connection";
    public static final int CONN_KEEP_ALIVE = 2;
    public static final String CONTENT_DISPOSITION = "content-disposition";
    public static final String CONTENT_ENCODING = "content-encoding";
    public static final String CONTENT_LEN = "content-length";
    public static final String CONTENT_TYPE = "content-type";
    public static final String ETAG = "etag";
    public static final String EXPIRES = "expires";
    public static final String LAST_MODIFIED = "last-modified";
    public static final String LOCATION = "location";
    public static final int NO_CONN_TYPE = 0;
    public static final long NO_CONTENT_LENGTH = -1;
    public static final long NO_TRANSFER_ENCODING = 0;
    public static final String PRAGMA = "pragma";
    public static final String PROXY_AUTHENTICATE = "proxy-authenticate";
    public static final String PROXY_CONNECTION = "proxy-connection";
    public static final String REFRESH = "refresh";
    public static final String SET_COOKIE = "set-cookie";
    public static final String TRANSFER_ENCODING = "transfer-encoding";
    public static final String WWW_AUTHENTICATE = "www-authenticate";
    public static final String X_PERMITTED_CROSS_DOMAIN_POLICIES = "x-permitted-cross-domain-policies";
    private static final String[] f = new String[]{TRANSFER_ENCODING, CONTENT_LEN, "content-type", CONTENT_ENCODING, CONN_DIRECTIVE, "location", PROXY_CONNECTION, WWW_AUTHENTICATE, PROXY_AUTHENTICATE, CONTENT_DISPOSITION, ACCEPT_RANGES, "expires", CACHE_CONTROL, LAST_MODIFIED, "etag", SET_COOKIE, PRAGMA, REFRESH, X_PERMITTED_CROSS_DOMAIN_POLICIES};
    private long a = 0;
    private long b = -1;
    private int c = 0;
    private ArrayList<String> d = new ArrayList(2);
    private String[] e = new String[19];
    private ArrayList<String> g = new ArrayList(4);
    private ArrayList<String> h = new ArrayList(4);

    public interface HeaderCallback {
        void header(String str, String str2);
    }

    private void a(CharArrayBuffer charArrayBuffer, int i) {
        if (e.a(charArrayBuffer, i, HTTP.CONN_CLOSE)) {
            this.c = 1;
        } else if (e.a(charArrayBuffer, i, HTTP.CONN_KEEP_ALIVE)) {
            this.c = 2;
        }
    }

    public final String getAcceptRanges() {
        return this.e[10];
    }

    public final String getCacheControl() {
        return this.e[12];
    }

    public final int getConnectionType() {
        return this.c;
    }

    public final String getContentDisposition() {
        return this.e[9];
    }

    public final String getContentEncoding() {
        return this.e[3];
    }

    public final long getContentLength() {
        return this.b;
    }

    public final String getContentType() {
        return this.e[2];
    }

    public final String getEtag() {
        return this.e[14];
    }

    public final String getExpires() {
        return this.e[11];
    }

    public final void getHeaders(HeaderCallback headerCallback) {
        for (int i = 0; i < 19; i++) {
            String str = this.e[i];
            if (str != null) {
                headerCallback.header(f[i], str);
            }
        }
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            headerCallback.header((String) this.g.get(i2), (String) this.h.get(i2));
        }
    }

    public final String getLastModified() {
        return this.e[13];
    }

    public final String getLocation() {
        return this.e[5];
    }

    public final String getPragma() {
        return this.e[16];
    }

    public final String getProxyAuthenticate() {
        return this.e[8];
    }

    public final String getRefresh() {
        return this.e[17];
    }

    public final ArrayList<String> getSetCookie() {
        return this.d;
    }

    public final long getTransferEncoding() {
        return this.a;
    }

    public final String getWwwAuthenticate() {
        return this.e[7];
    }

    public final String getXPermittedCrossDomainPolicies() {
        return this.e[18];
    }

    public final void parseHeader(CharArrayBuffer charArrayBuffer) {
        int a = e.a(charArrayBuffer, 58);
        if (a != -1) {
            String substringTrimmed = charArrayBuffer.substringTrimmed(0, a);
            if (substringTrimmed.length() != 0) {
                a++;
                String substringTrimmed2 = charArrayBuffer.substringTrimmed(a, charArrayBuffer.length());
                switch (substringTrimmed.hashCode()) {
                    case -1345594014:
                        if (substringTrimmed.equals(X_PERMITTED_CROSS_DOMAIN_POLICIES)) {
                            this.e[18] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -1309235404:
                        if (substringTrimmed.equals("expires")) {
                            this.e[11] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -1267267485:
                        if (substringTrimmed.equals(CONTENT_DISPOSITION)) {
                            this.e[9] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -1132779846:
                        if (substringTrimmed.equals(CONTENT_LEN)) {
                            this.e[1] = substringTrimmed2;
                            try {
                                this.b = Long.parseLong(substringTrimmed2);
                                return;
                            } catch (NumberFormatException e) {
                                return;
                            }
                        }
                        return;
                    case -980228804:
                        if (substringTrimmed.equals(PRAGMA)) {
                            this.e[16] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -775651618:
                        if (substringTrimmed.equals(CONN_DIRECTIVE)) {
                            this.e[4] = substringTrimmed2;
                            a(charArrayBuffer, a);
                            return;
                        }
                        return;
                    case -301767724:
                        if (substringTrimmed.equals(PROXY_AUTHENTICATE)) {
                            this.e[8] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -243037365:
                        if (substringTrimmed.equals(WWW_AUTHENTICATE)) {
                            this.e[7] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -208775662:
                        if (!substringTrimmed.equals(CACHE_CONTROL)) {
                            return;
                        }
                        if (this.e[12] == null || this.e[12].length() <= 0) {
                            this.e[12] = substringTrimmed2;
                            return;
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        String[] strArr = this.e;
                        strArr[12] = stringBuilder.append(strArr[12]).append(',').append(substringTrimmed2).toString();
                        return;
                    case 3123477:
                        if (substringTrimmed.equals("etag")) {
                            this.e[14] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 150043680:
                        if (substringTrimmed.equals(LAST_MODIFIED)) {
                            this.e[13] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 285929373:
                        if (substringTrimmed.equals(PROXY_CONNECTION)) {
                            this.e[6] = substringTrimmed2;
                            a(charArrayBuffer, a);
                            return;
                        }
                        return;
                    case 785670158:
                        if (substringTrimmed.equals("content-type")) {
                            this.e[2] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 1085444827:
                        if (substringTrimmed.equals(REFRESH)) {
                            this.e[17] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 1237214767:
                        if (substringTrimmed.equals(SET_COOKIE)) {
                            this.e[15] = substringTrimmed2;
                            this.d.add(substringTrimmed2);
                            return;
                        }
                        return;
                    case 1274458357:
                        if (substringTrimmed.equals(TRANSFER_ENCODING)) {
                            this.e[0] = substringTrimmed2;
                            HeaderElement[] parseElements = BasicHeaderValueParser.DEFAULT.parseElements(charArrayBuffer, new ParserCursor(a, charArrayBuffer.length()));
                            int length = parseElements.length;
                            if (HTTP.IDENTITY_CODING.equalsIgnoreCase(substringTrimmed2) || length <= 0 || !HTTP.CHUNK_CODING.equalsIgnoreCase(parseElements[length - 1].getName())) {
                                this.a = -1;
                                return;
                            } else {
                                this.a = -2;
                                return;
                            }
                        }
                        return;
                    case 1397189435:
                        if (substringTrimmed.equals(ACCEPT_RANGES)) {
                            this.e[10] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 1901043637:
                        if (substringTrimmed.equals("location")) {
                            this.e[5] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 2095084583:
                        if (substringTrimmed.equals(CONTENT_ENCODING)) {
                            this.e[3] = substringTrimmed2;
                            return;
                        }
                        return;
                    default:
                        this.g.add(substringTrimmed);
                        this.h.add(substringTrimmed2);
                        return;
                }
            }
        }
    }

    public final void setAcceptRanges(String str) {
        this.e[10] = str;
    }

    public final void setCacheControl(String str) {
        this.e[12] = str;
    }

    public final void setContentDisposition(String str) {
        this.e[9] = str;
    }

    public final void setContentEncoding(String str) {
        this.e[3] = str;
    }

    public final void setContentLength(long j) {
        this.b = j;
    }

    public final void setContentType(String str) {
        this.e[2] = str;
    }

    public final void setEtag(String str) {
        this.e[14] = str;
    }

    public final void setExpires(String str) {
        this.e[11] = str;
    }

    public final void setLastModified(String str) {
        this.e[13] = str;
    }

    public final void setLocation(String str) {
        this.e[5] = str;
    }

    public final void setProxyAuthenticate(String str) {
        this.e[8] = str;
    }

    public final void setWwwAuthenticate(String str) {
        this.e[7] = str;
    }

    public final void setXPermittedCrossDomainPolicies(String str) {
        this.e[18] = str;
    }
}
