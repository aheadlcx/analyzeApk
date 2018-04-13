package org.apache.commons.httpclient.cookie;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;

public class NetscapeDraftSpec extends CookieSpecBase {
    public Cookie[] parse(String str, int i, String str2, boolean z, String str3) throws MalformedCookieException {
        CookieSpecBase.LOG.trace("enter NetscapeDraftSpec.parse(String, port, path, boolean, Header)");
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
            NameValuePair headerElement = new HeaderElement(str3.toCharArray());
            Cookie cookie = new Cookie(toLowerCase, headerElement.getName(), headerElement.getValue(), substring, null, false);
            NameValuePair[] parameters = headerElement.getParameters();
            if (parameters != null) {
                for (NameValuePair parseAttribute : parameters) {
                    parseAttribute(parseAttribute, cookie);
                }
            }
            return new Cookie[]{cookie};
        }
    }

    public void parseAttribute(NameValuePair nameValuePair, Cookie cookie) throws MalformedCookieException {
        if (nameValuePair == null) {
            throw new IllegalArgumentException("Attribute may not be null.");
        } else if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null.");
        } else {
            String toLowerCase = nameValuePair.getName().toLowerCase();
            String value = nameValuePair.getValue();
            if (!toLowerCase.equals("expires")) {
                super.parseAttribute(nameValuePair, cookie);
            } else if (value == null) {
                throw new MalformedCookieException("Missing value for expires attribute");
            } else {
                try {
                    cookie.setExpiryDate(new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", Locale.US).parse(value));
                } catch (Throwable e) {
                    throw new MalformedCookieException(new StringBuffer().append("Invalid expires attribute: ").append(e.getMessage()).toString());
                }
            }
        }
    }

    public boolean domainMatch(String str, String str2) {
        return str.endsWith(str2);
    }

    public void validate(String str, int i, String str2, boolean z, Cookie cookie) throws MalformedCookieException {
        CookieSpecBase.LOG.trace("enterNetscapeDraftCookieProcessor RCF2109CookieProcessor.validate(Cookie)");
        super.validate(str, i, str2, z, cookie);
        if (str.indexOf(".") >= 0) {
            int countTokens = new StringTokenizer(cookie.getDomain(), ".").countTokens();
            if (isSpecialDomain(cookie.getDomain())) {
                if (countTokens < 2) {
                    throw new MalformedCookieException(new StringBuffer().append("Domain attribute \"").append(cookie.getDomain()).append("\" violates the Netscape cookie specification for ").append("special domains").toString());
                }
            } else if (countTokens < 3) {
                throw new MalformedCookieException(new StringBuffer().append("Domain attribute \"").append(cookie.getDomain()).append("\" violates the Netscape cookie specification").toString());
            }
        }
    }

    private static boolean isSpecialDomain(String str) {
        String toUpperCase = str.toUpperCase();
        if (toUpperCase.endsWith(".COM") || toUpperCase.endsWith(".EDU") || toUpperCase.endsWith(".NET") || toUpperCase.endsWith(".GOV") || toUpperCase.endsWith(".MIL") || toUpperCase.endsWith(".ORG") || toUpperCase.endsWith(".INT")) {
            return true;
        }
        return false;
    }
}
