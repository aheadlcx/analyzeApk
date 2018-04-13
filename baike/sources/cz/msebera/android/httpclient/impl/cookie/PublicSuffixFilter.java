package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.conn.util.PublicSuffixMatcher;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import java.util.Collection;

@Deprecated
public class PublicSuffixFilter implements CookieAttributeHandler {
    private final CookieAttributeHandler a;
    private Collection<String> b;
    private Collection<String> c;
    private PublicSuffixMatcher d;

    public PublicSuffixFilter(CookieAttributeHandler cookieAttributeHandler) {
        this.a = cookieAttributeHandler;
    }

    public void setPublicSuffixes(Collection<String> collection) {
        this.c = collection;
        this.d = null;
    }

    public void setExceptions(Collection<String> collection) {
        this.b = collection;
        this.d = null;
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        if (a(cookie)) {
            return false;
        }
        return this.a.match(cookie, cookieOrigin);
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        this.a.parse(setCookie, str);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        this.a.validate(cookie, cookieOrigin);
    }

    private boolean a(Cookie cookie) {
        if (this.d == null) {
            this.d = new PublicSuffixMatcher(this.c, this.b);
        }
        return this.d.matches(cookie.getDomain());
    }
}
