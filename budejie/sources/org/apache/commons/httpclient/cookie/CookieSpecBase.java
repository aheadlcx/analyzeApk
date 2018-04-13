package org.apache.commons.httpclient.cookie;

import com.ali.auth.third.login.LoginConstants;
import com.alipay.sdk.util.h;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieSpecBase implements CookieSpec {
    protected static final Log LOG;
    static Class class$org$apache$commons$httpclient$cookie$CookieSpec;
    private Collection datepatterns = null;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$cookie$CookieSpec == null) {
            class$ = class$("org.apache.commons.httpclient.cookie.CookieSpec");
            class$org$apache$commons$httpclient$cookie$CookieSpec = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$cookie$CookieSpec;
        }
        LOG = LogFactory.getLog(class$);
    }

    public Cookie[] parse(String str, int i, String str2, boolean z, String str3) throws MalformedCookieException {
        LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, Header)");
        if (str == null) {
            throw new IllegalArgumentException("Host of origin may not be null");
        } else if (str.trim().equals("")) {
            throw new IllegalArgumentException("Host of origin may not be blank");
        } else if (i < 0) {
            throw new IllegalArgumentException(new StringBuffer().append("Invalid port: ").append(i).toString());
        } else if (str2 == null) {
            throw new IllegalArgumentException("Path of origin may not be null.");
        } else if (str3 == null) {
            throw new IllegalArgumentException("Header may not be null.");
        } else {
            String substring;
            HeaderElement[] headerElementArr;
            if (str2.trim().equals("")) {
                str2 = "/";
            }
            String toLowerCase = str.toLowerCase();
            int lastIndexOf = str2.lastIndexOf("/");
            if (lastIndexOf >= 0) {
                if (lastIndexOf == 0) {
                    lastIndexOf = 1;
                }
                substring = str2.substring(0, lastIndexOf);
            } else {
                substring = str2;
            }
            Object obj = null;
            int indexOf = str3.toLowerCase().indexOf("expires=");
            if (indexOf != -1) {
                int length = "expires=".length() + indexOf;
                indexOf = str3.indexOf(h.b, length);
                if (indexOf == -1) {
                    indexOf = str3.length();
                }
                try {
                    DateUtil.parseDate(str3.substring(length, indexOf), this.datepatterns);
                    obj = 1;
                } catch (DateParseException e) {
                }
            }
            if (obj != null) {
                headerElementArr = new HeaderElement[]{new HeaderElement(str3.toCharArray())};
            } else {
                headerElementArr = HeaderElement.parseElements(str3.toCharArray());
            }
            Cookie[] cookieArr = new Cookie[headerElementArr.length];
            int i2 = 0;
            while (i2 < headerElementArr.length) {
                NameValuePair nameValuePair = headerElementArr[i2];
                try {
                    Cookie cookie = new Cookie(toLowerCase, nameValuePair.getName(), nameValuePair.getValue(), substring, null, false);
                    NameValuePair[] parameters = nameValuePair.getParameters();
                    if (parameters != null) {
                        for (NameValuePair parseAttribute : parameters) {
                            parseAttribute(parseAttribute, cookie);
                        }
                    }
                    cookieArr[i2] = cookie;
                    i2++;
                } catch (Throwable e2) {
                    throw new MalformedCookieException(e2.getMessage());
                }
            }
            return cookieArr;
        }
    }

    public Cookie[] parse(String str, int i, String str2, boolean z, Header header) throws MalformedCookieException {
        LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, String)");
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null.");
        }
        return parse(str, i, str2, z, header.getValue());
    }

    public void parseAttribute(NameValuePair nameValuePair, Cookie cookie) throws MalformedCookieException {
        if (nameValuePair == null) {
            throw new IllegalArgumentException("Attribute may not be null.");
        } else if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null.");
        } else {
            String toLowerCase = nameValuePair.getName().toLowerCase();
            String value = nameValuePair.getValue();
            if (toLowerCase.equals("path")) {
                if (value == null || value.trim().equals("")) {
                    value = "/";
                }
                cookie.setPath(value);
                cookie.setPathAttributeSpecified(true);
            } else if (toLowerCase.equals("domain")) {
                if (value == null) {
                    throw new MalformedCookieException("Missing value for domain attribute");
                } else if (value.trim().equals("")) {
                    throw new MalformedCookieException("Blank value for domain attribute");
                } else {
                    cookie.setDomain(value);
                    cookie.setDomainAttributeSpecified(true);
                }
            } else if (toLowerCase.equals("max-age")) {
                if (value == null) {
                    throw new MalformedCookieException("Missing value for max-age attribute");
                }
                try {
                    cookie.setExpiryDate(new Date(System.currentTimeMillis() + (((long) Integer.parseInt(value)) * 1000)));
                } catch (Throwable e) {
                    throw new MalformedCookieException(new StringBuffer().append("Invalid max-age attribute: ").append(e.getMessage()).toString());
                }
            } else if (toLowerCase.equals("secure")) {
                cookie.setSecure(true);
            } else if (toLowerCase.equals("comment")) {
                cookie.setComment(value);
            } else if (toLowerCase.equals("expires")) {
                if (value == null) {
                    throw new MalformedCookieException("Missing value for expires attribute");
                }
                try {
                    cookie.setExpiryDate(DateUtil.parseDate(value, this.datepatterns));
                } catch (Throwable e2) {
                    LOG.debug("Error parsing cookie date", e2);
                    throw new MalformedCookieException(new StringBuffer().append("Unable to parse expiration date parameter: ").append(value).toString());
                }
            } else if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Unrecognized cookie attribute: ").append(nameValuePair.toString()).toString());
            }
        }
    }

    public Collection getValidDateFormats() {
        return this.datepatterns;
    }

    public void setValidDateFormats(Collection collection) {
        this.datepatterns = collection;
    }

    public void validate(String str, int i, String str2, boolean z, Cookie cookie) throws MalformedCookieException {
        LOG.trace("enter CookieSpecBase.validate(String, port, path, boolean, Cookie)");
        if (str == null) {
            throw new IllegalArgumentException("Host of origin may not be null");
        } else if (str.trim().equals("")) {
            throw new IllegalArgumentException("Host of origin may not be blank");
        } else if (i < 0) {
            throw new IllegalArgumentException(new StringBuffer().append("Invalid port: ").append(i).toString());
        } else if (str2 == null) {
            throw new IllegalArgumentException("Path of origin may not be null.");
        } else {
            if (str2.trim().equals("")) {
                str2 = "/";
            }
            String toLowerCase = str.toLowerCase();
            if (cookie.getVersion() < 0) {
                throw new MalformedCookieException(new StringBuffer().append("Illegal version number ").append(cookie.getValue()).toString());
            }
            if (toLowerCase.indexOf(".") >= 0) {
                if (!toLowerCase.endsWith(cookie.getDomain())) {
                    Object domain = cookie.getDomain();
                    if (domain.startsWith(".")) {
                        domain = domain.substring(1, domain.length());
                    }
                    if (!toLowerCase.equals(domain)) {
                        throw new MalformedCookieException(new StringBuffer().append("Illegal domain attribute \"").append(cookie.getDomain()).append("\". Domain of origin: \"").append(toLowerCase).append("\"").toString());
                    }
                }
            } else if (!toLowerCase.equals(cookie.getDomain())) {
                throw new MalformedCookieException(new StringBuffer().append("Illegal domain attribute \"").append(cookie.getDomain()).append("\". Domain of origin: \"").append(toLowerCase).append("\"").toString());
            }
            if (!str2.startsWith(cookie.getPath())) {
                throw new MalformedCookieException(new StringBuffer().append("Illegal path attribute \"").append(cookie.getPath()).append("\". Path of origin: \"").append(str2).append("\"").toString());
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(java.lang.String r6, int r7, java.lang.String r8, boolean r9, org.apache.commons.httpclient.Cookie r10) {
        /*
        r5 = this;
        r0 = 1;
        r1 = 0;
        r2 = LOG;
        r3 = "enter CookieSpecBase.match(String, int, String, boolean, Cookie";
        r2.trace(r3);
        if (r6 != 0) goto L_0x0013;
    L_0x000b:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Host of origin may not be null";
        r0.<init>(r1);
        throw r0;
    L_0x0013:
        r2 = r6.trim();
        r3 = "";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0027;
    L_0x001f:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Host of origin may not be blank";
        r0.<init>(r1);
        throw r0;
    L_0x0027:
        if (r7 >= 0) goto L_0x0042;
    L_0x0029:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r2 = "Invalid port: ";
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0042:
        if (r8 != 0) goto L_0x004c;
    L_0x0044:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Path of origin may not be null.";
        r0.<init>(r1);
        throw r0;
    L_0x004c:
        if (r10 != 0) goto L_0x0056;
    L_0x004e:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Cookie may not be null";
        r0.<init>(r1);
        throw r0;
    L_0x0056:
        r2 = r8.trim();
        r3 = "";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0064;
    L_0x0062:
        r8 = "/";
    L_0x0064:
        r2 = r6.toLowerCase();
        r3 = r10.getDomain();
        if (r3 != 0) goto L_0x0076;
    L_0x006e:
        r0 = LOG;
        r2 = "Invalid cookie state: domain not specified";
        r0.warn(r2);
    L_0x0075:
        return r1;
    L_0x0076:
        r3 = r10.getPath();
        if (r3 != 0) goto L_0x0084;
    L_0x007c:
        r0 = LOG;
        r2 = "Invalid cookie state: path not specified";
        r0.warn(r2);
        goto L_0x0075;
    L_0x0084:
        r3 = r10.getExpiryDate();
        if (r3 == 0) goto L_0x0099;
    L_0x008a:
        r3 = r10.getExpiryDate();
        r4 = new java.util.Date;
        r4.<init>();
        r3 = r3.after(r4);
        if (r3 == 0) goto L_0x00b9;
    L_0x0099:
        r3 = r10.getDomain();
        r2 = r5.domainMatch(r2, r3);
        if (r2 == 0) goto L_0x00b9;
    L_0x00a3:
        r2 = r10.getPath();
        r2 = r5.pathMatch(r8, r2);
        if (r2 == 0) goto L_0x00b9;
    L_0x00ad:
        r2 = r10.getSecure();
        if (r2 == 0) goto L_0x00b7;
    L_0x00b3:
        if (r9 == 0) goto L_0x00b9;
    L_0x00b5:
        r1 = r0;
        goto L_0x0075;
    L_0x00b7:
        r9 = r0;
        goto L_0x00b3;
    L_0x00b9:
        r0 = r1;
        goto L_0x00b5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.httpclient.cookie.CookieSpecBase.match(java.lang.String, int, java.lang.String, boolean, org.apache.commons.httpclient.Cookie):boolean");
    }

    public boolean domainMatch(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (!str2.startsWith(".")) {
            str2 = new StringBuffer().append(".").append(str2).toString();
        }
        if (str.endsWith(str2) || str.equals(str2.substring(1))) {
            return true;
        }
        return false;
    }

    public boolean pathMatch(String str, String str2) {
        boolean startsWith = str.startsWith(str2);
        if (!startsWith || str.length() == str2.length() || str2.endsWith("/")) {
            return startsWith;
        }
        return str.charAt(str2.length()) == CookieSpec.PATH_DELIM_CHAR;
    }

    public Cookie[] match(String str, int i, String str2, boolean z, Cookie[] cookieArr) {
        LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie[])");
        if (cookieArr == null) {
            return null;
        }
        List linkedList = new LinkedList();
        for (int i2 = 0; i2 < cookieArr.length; i2++) {
            if (match(str, i, str2, z, cookieArr[i2])) {
                addInPathOrder(linkedList, cookieArr[i2]);
            }
        }
        return (Cookie[]) linkedList.toArray(new Cookie[linkedList.size()]);
    }

    private static void addInPathOrder(List list, Cookie cookie) {
        int i = 0;
        while (i < list.size() && cookie.compare(cookie, (Cookie) list.get(i)) <= 0) {
            i++;
        }
        list.add(i, cookie);
    }

    public String formatCookie(Cookie cookie) {
        LOG.trace("enter CookieSpecBase.formatCookie(Cookie)");
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cookie.getName());
        stringBuffer.append(LoginConstants.EQUAL);
        String value = cookie.getValue();
        if (value != null) {
            stringBuffer.append(value);
        }
        return stringBuffer.toString();
    }

    public String formatCookies(Cookie[] cookieArr) throws IllegalArgumentException {
        LOG.trace("enter CookieSpecBase.formatCookies(Cookie[])");
        if (cookieArr == null) {
            throw new IllegalArgumentException("Cookie array may not be null");
        } else if (cookieArr.length == 0) {
            throw new IllegalArgumentException("Cookie array may not be empty");
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < cookieArr.length; i++) {
                if (i > 0) {
                    stringBuffer.append("; ");
                }
                stringBuffer.append(formatCookie(cookieArr[i]));
            }
            return stringBuffer.toString();
        }
    }

    public Header formatCookieHeader(Cookie[] cookieArr) {
        LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie[])");
        return new Header("Cookie", formatCookies(cookieArr));
    }

    public Header formatCookieHeader(Cookie cookie) {
        LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie)");
        return new Header("Cookie", formatCookie(cookie));
    }
}
