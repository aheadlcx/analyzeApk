package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.annotation.Obsolete;
import java.util.List;

public interface CookieSpec {
    List<Header> formatCookies(List<Cookie> list);

    @Obsolete
    int getVersion();

    @Obsolete
    Header getVersionHeader();

    boolean match(Cookie cookie, CookieOrigin cookieOrigin);

    List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException;

    void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException;
}
