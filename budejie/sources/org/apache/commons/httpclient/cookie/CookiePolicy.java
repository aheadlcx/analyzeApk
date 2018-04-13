package org.apache.commons.httpclient.cookie;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CookiePolicy {
    public static final String BROWSER_COMPATIBILITY = "compatibility";
    public static final int COMPATIBILITY = 0;
    public static final String DEFAULT = "default";
    public static final String IGNORE_COOKIES = "ignoreCookies";
    protected static final Log LOG;
    public static final String NETSCAPE = "netscape";
    public static final int NETSCAPE_DRAFT = 1;
    public static final int RFC2109 = 2;
    public static final String RFC_2109 = "rfc2109";
    private static Map SPECS = Collections.synchronizedMap(new HashMap());
    static Class class$org$apache$commons$httpclient$cookie$CookiePolicy;
    static Class class$org$apache$commons$httpclient$cookie$CookieSpecBase;
    static Class class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec;
    static Class class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec;
    static Class class$org$apache$commons$httpclient$cookie$RFC2109Spec;
    private static int defaultPolicy = 2;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        String str = DEFAULT;
        if (class$org$apache$commons$httpclient$cookie$RFC2109Spec == null) {
            class$ = class$("org.apache.commons.httpclient.cookie.RFC2109Spec");
            class$org$apache$commons$httpclient$cookie$RFC2109Spec = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$cookie$RFC2109Spec;
        }
        registerCookieSpec(str, class$);
        str = RFC_2109;
        if (class$org$apache$commons$httpclient$cookie$RFC2109Spec == null) {
            class$ = class$("org.apache.commons.httpclient.cookie.RFC2109Spec");
            class$org$apache$commons$httpclient$cookie$RFC2109Spec = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$cookie$RFC2109Spec;
        }
        registerCookieSpec(str, class$);
        str = BROWSER_COMPATIBILITY;
        if (class$org$apache$commons$httpclient$cookie$CookieSpecBase == null) {
            class$ = class$("org.apache.commons.httpclient.cookie.CookieSpecBase");
            class$org$apache$commons$httpclient$cookie$CookieSpecBase = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$cookie$CookieSpecBase;
        }
        registerCookieSpec(str, class$);
        str = NETSCAPE;
        if (class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec == null) {
            class$ = class$("org.apache.commons.httpclient.cookie.NetscapeDraftSpec");
            class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec;
        }
        registerCookieSpec(str, class$);
        str = IGNORE_COOKIES;
        if (class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec == null) {
            class$ = class$("org.apache.commons.httpclient.cookie.IgnoreCookiesSpec");
            class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec;
        }
        registerCookieSpec(str, class$);
        if (class$org$apache$commons$httpclient$cookie$CookiePolicy == null) {
            class$ = class$("org.apache.commons.httpclient.cookie.CookiePolicy");
            class$org$apache$commons$httpclient$cookie$CookiePolicy = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$cookie$CookiePolicy;
        }
        LOG = LogFactory.getLog(class$);
    }

    public static void registerCookieSpec(String str, Class cls) {
        if (str == null) {
            throw new IllegalArgumentException("Id may not be null");
        } else if (cls == null) {
            throw new IllegalArgumentException("Cookie spec class may not be null");
        } else {
            SPECS.put(str.toLowerCase(), cls);
        }
    }

    public static void unregisterCookieSpec(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        SPECS.remove(str.toLowerCase());
    }

    public static CookieSpec getCookieSpec(String str) throws IllegalStateException {
        if (str == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        Class cls = (Class) SPECS.get(str.toLowerCase());
        if (cls != null) {
            try {
                return (CookieSpec) cls.newInstance();
            } catch (Throwable e) {
                LOG.error(new StringBuffer().append("Error initializing cookie spec: ").append(str).toString(), e);
                throw new IllegalStateException(new StringBuffer().append(str).append(" cookie spec implemented by ").append(cls.getName()).append(" could not be initialized").toString());
            }
        }
        throw new IllegalStateException(new StringBuffer().append("Unsupported cookie spec ").append(str).toString());
    }

    public static int getDefaultPolicy() {
        return defaultPolicy;
    }

    public static void setDefaultPolicy(int i) {
        defaultPolicy = i;
    }

    public static CookieSpec getSpecByPolicy(int i) {
        switch (i) {
            case 0:
                return new CookieSpecBase();
            case 1:
                return new NetscapeDraftSpec();
            case 2:
                return new RFC2109Spec();
            default:
                return getDefaultSpec();
        }
    }

    public static CookieSpec getDefaultSpec() {
        try {
            return getCookieSpec(DEFAULT);
        } catch (IllegalStateException e) {
            LOG.warn("Default cookie policy is not registered");
            return new RFC2109Spec();
        }
    }

    public static CookieSpec getSpecByVersion(int i) {
        switch (i) {
            case 0:
                return new NetscapeDraftSpec();
            case 1:
                return new RFC2109Spec();
            default:
                return getDefaultSpec();
        }
    }

    public static CookieSpec getCompatibilitySpec() {
        return getSpecByPolicy(0);
    }
}
