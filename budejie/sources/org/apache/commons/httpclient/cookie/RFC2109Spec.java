package org.apache.commons.httpclient.cookie;

import com.ali.auth.third.login.LoginConstants;
import com.tencent.open.GameAppOperation;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterFormatter;

public class RFC2109Spec extends CookieSpecBase {
    private final ParameterFormatter formatter = new ParameterFormatter();

    public RFC2109Spec() {
        this.formatter.setAlwaysUseQuotes(true);
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
                if (value == null) {
                    throw new MalformedCookieException("Missing value for path attribute");
                } else if (value.trim().equals("")) {
                    throw new MalformedCookieException("Blank value for path attribute");
                } else {
                    cookie.setPath(value);
                    cookie.setPathAttributeSpecified(true);
                }
            } else if (!toLowerCase.equals(GameAppOperation.QQFAV_DATALINE_VERSION)) {
                super.parseAttribute(nameValuePair, cookie);
            } else if (value == null) {
                throw new MalformedCookieException("Missing value for version attribute");
            } else {
                try {
                    cookie.setVersion(Integer.parseInt(value));
                } catch (Throwable e) {
                    throw new MalformedCookieException(new StringBuffer().append("Invalid version: ").append(e.getMessage()).toString());
                }
            }
        }
    }

    public void validate(String str, int i, String str2, boolean z, Cookie cookie) throws MalformedCookieException {
        CookieSpecBase.LOG.trace("enter RFC2109Spec.validate(String, int, String, boolean, Cookie)");
        super.validate(str, i, str2, z, cookie);
        if (cookie.getName().indexOf(32) != -1) {
            throw new MalformedCookieException("Cookie name may not contain blanks");
        } else if (cookie.getName().startsWith("$")) {
            throw new MalformedCookieException("Cookie name may not start with $");
        } else if (cookie.isDomainAttributeSpecified() && !cookie.getDomain().equals(str)) {
            if (cookie.getDomain().startsWith(".")) {
                int indexOf = cookie.getDomain().indexOf(46, 1);
                if (indexOf < 0 || indexOf == cookie.getDomain().length() - 1) {
                    throw new MalformedCookieException(new StringBuffer().append("Domain attribute \"").append(cookie.getDomain()).append("\" violates RFC 2109: domain must contain an embedded dot").toString());
                }
                String toLowerCase = str.toLowerCase();
                if (!toLowerCase.endsWith(cookie.getDomain())) {
                    throw new MalformedCookieException(new StringBuffer().append("Illegal domain attribute \"").append(cookie.getDomain()).append("\". Domain of origin: \"").append(toLowerCase).append("\"").toString());
                } else if (toLowerCase.substring(0, toLowerCase.length() - cookie.getDomain().length()).indexOf(46) != -1) {
                    throw new MalformedCookieException(new StringBuffer().append("Domain attribute \"").append(cookie.getDomain()).append("\" violates RFC 2109: host minus domain may not contain any dots").toString());
                } else {
                    return;
                }
            }
            throw new MalformedCookieException(new StringBuffer().append("Domain attribute \"").append(cookie.getDomain()).append("\" violates RFC 2109: domain must start with a dot").toString());
        }
    }

    public boolean domainMatch(String str, String str2) {
        return str.equals(str2) || (str2.startsWith(".") && str.endsWith(str2));
    }

    private void formatParam(StringBuffer stringBuffer, NameValuePair nameValuePair, int i) {
        if (i < 1) {
            stringBuffer.append(nameValuePair.getName());
            stringBuffer.append(LoginConstants.EQUAL);
            if (nameValuePair.getValue() != null) {
                stringBuffer.append(nameValuePair.getValue());
                return;
            }
            return;
        }
        this.formatter.format(stringBuffer, nameValuePair);
    }

    private void formatCookieAsVer(StringBuffer stringBuffer, Cookie cookie, int i) {
        String value = cookie.getValue();
        if (value == null) {
            value = "";
        }
        formatParam(stringBuffer, new NameValuePair(cookie.getName(), value), i);
        if (cookie.getPath() != null && cookie.isPathAttributeSpecified()) {
            stringBuffer.append("; ");
            formatParam(stringBuffer, new NameValuePair("$Path", cookie.getPath()), i);
        }
        if (cookie.getDomain() != null && cookie.isDomainAttributeSpecified()) {
            stringBuffer.append("; ");
            formatParam(stringBuffer, new NameValuePair("$Domain", cookie.getDomain()), i);
        }
    }

    public String formatCookie(Cookie cookie) {
        CookieSpecBase.LOG.trace("enter RFC2109Spec.formatCookie(Cookie)");
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        }
        int version = cookie.getVersion();
        StringBuffer stringBuffer = new StringBuffer();
        formatParam(stringBuffer, new NameValuePair("$Version", Integer.toString(version)), version);
        stringBuffer.append("; ");
        formatCookieAsVer(stringBuffer, cookie, version);
        return stringBuffer.toString();
    }

    public String formatCookies(Cookie[] cookieArr) {
        int i = 0;
        CookieSpecBase.LOG.trace("enter RFC2109Spec.formatCookieHeader(Cookie[])");
        int i2 = Integer.MAX_VALUE;
        for (Cookie cookie : cookieArr) {
            if (cookie.getVersion() < i2) {
                i2 = cookie.getVersion();
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        formatParam(stringBuffer, new NameValuePair("$Version", Integer.toString(i2)), i2);
        while (i < cookieArr.length) {
            stringBuffer.append("; ");
            formatCookieAsVer(stringBuffer, cookieArr[i], i2);
            i++;
        }
        return stringBuffer.toString();
    }
}
