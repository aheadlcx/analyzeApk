package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;

class d extends BasicPathHandler {
    final /* synthetic */ RFC6265CookieSpecProvider a;

    d(RFC6265CookieSpecProvider rFC6265CookieSpecProvider) {
        this.a = rFC6265CookieSpecProvider;
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
    }
}
