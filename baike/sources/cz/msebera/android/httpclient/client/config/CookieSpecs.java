package cz.msebera.android.httpclient.client.config;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public final class CookieSpecs {
    @Deprecated
    public static final String BEST_MATCH = "best-match";
    @Deprecated
    public static final String BROWSER_COMPATIBILITY = "compatibility";
    public static final String DEFAULT = "default";
    public static final String IGNORE_COOKIES = "ignoreCookies";
    public static final String NETSCAPE = "netscape";
    public static final String STANDARD = "standard";
    public static final String STANDARD_STRICT = "standard-strict";

    private CookieSpecs() {
    }
}
