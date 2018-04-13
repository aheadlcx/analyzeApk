package org.apache.commons.httpclient.cookie;

import java.util.Collection;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

public interface CookieSpec {
    public static final String PATH_DELIM = "/";
    public static final char PATH_DELIM_CHAR = "/".charAt(0);

    boolean domainMatch(String str, String str2);

    String formatCookie(Cookie cookie);

    Header formatCookieHeader(Cookie cookie) throws IllegalArgumentException;

    Header formatCookieHeader(Cookie[] cookieArr) throws IllegalArgumentException;

    String formatCookies(Cookie[] cookieArr) throws IllegalArgumentException;

    Collection getValidDateFormats();

    boolean match(String str, int i, String str2, boolean z, Cookie cookie);

    Cookie[] match(String str, int i, String str2, boolean z, Cookie[] cookieArr);

    Cookie[] parse(String str, int i, String str2, boolean z, String str3) throws MalformedCookieException, IllegalArgumentException;

    Cookie[] parse(String str, int i, String str2, boolean z, Header header) throws MalformedCookieException, IllegalArgumentException;

    void parseAttribute(NameValuePair nameValuePair, Cookie cookie) throws MalformedCookieException, IllegalArgumentException;

    boolean pathMatch(String str, String str2);

    void setValidDateFormats(Collection collection);

    void validate(String str, int i, String str2, boolean z, Cookie cookie) throws MalformedCookieException, IllegalArgumentException;
}
