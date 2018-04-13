package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class CookieRestrictionViolationException extends MalformedCookieException {
    public CookieRestrictionViolationException(String str) {
        super(str);
    }
}
