package org.apache.commons.httpclient.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AuthPolicy {
    public static final String AUTH_SCHEME_PRIORITY = "http.auth.scheme-priority";
    public static final String BASIC = "Basic";
    public static final String DIGEST = "Digest";
    protected static final Log LOG;
    public static final String NTLM = "NTLM";
    private static final HashMap SCHEMES = new HashMap();
    private static final ArrayList SCHEME_LIST = new ArrayList();
    static Class class$org$apache$commons$httpclient$auth$AuthPolicy;
    static Class class$org$apache$commons$httpclient$auth$BasicScheme;
    static Class class$org$apache$commons$httpclient$auth$DigestScheme;
    static Class class$org$apache$commons$httpclient$auth$NTLMScheme;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        String str = NTLM;
        if (class$org$apache$commons$httpclient$auth$NTLMScheme == null) {
            class$ = class$("org.apache.commons.httpclient.auth.NTLMScheme");
            class$org$apache$commons$httpclient$auth$NTLMScheme = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$NTLMScheme;
        }
        registerAuthScheme(str, class$);
        str = DIGEST;
        if (class$org$apache$commons$httpclient$auth$DigestScheme == null) {
            class$ = class$("org.apache.commons.httpclient.auth.DigestScheme");
            class$org$apache$commons$httpclient$auth$DigestScheme = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$DigestScheme;
        }
        registerAuthScheme(str, class$);
        str = BASIC;
        if (class$org$apache$commons$httpclient$auth$BasicScheme == null) {
            class$ = class$("org.apache.commons.httpclient.auth.BasicScheme");
            class$org$apache$commons$httpclient$auth$BasicScheme = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$BasicScheme;
        }
        registerAuthScheme(str, class$);
        if (class$org$apache$commons$httpclient$auth$AuthPolicy == null) {
            class$ = class$("org.apache.commons.httpclient.auth.AuthPolicy");
            class$org$apache$commons$httpclient$auth$AuthPolicy = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$AuthPolicy;
        }
        LOG = LogFactory.getLog(class$);
    }

    public static synchronized void registerAuthScheme(String str, Class cls) {
        synchronized (AuthPolicy.class) {
            if (str == null) {
                throw new IllegalArgumentException("Id may not be null");
            } else if (cls == null) {
                throw new IllegalArgumentException("Authentication scheme class may not be null");
            } else {
                SCHEMES.put(str.toLowerCase(), cls);
                SCHEME_LIST.add(str.toLowerCase());
            }
        }
    }

    public static synchronized void unregisterAuthScheme(String str) {
        synchronized (AuthPolicy.class) {
            if (str == null) {
                throw new IllegalArgumentException("Id may not be null");
            }
            SCHEMES.remove(str.toLowerCase());
            SCHEME_LIST.remove(str.toLowerCase());
        }
    }

    public static synchronized AuthScheme getAuthScheme(String str) throws IllegalStateException {
        AuthScheme authScheme;
        synchronized (AuthPolicy.class) {
            if (str == null) {
                throw new IllegalArgumentException("Id may not be null");
            }
            Class cls = (Class) SCHEMES.get(str.toLowerCase());
            if (cls != null) {
                try {
                    authScheme = (AuthScheme) cls.newInstance();
                } catch (Throwable e) {
                    LOG.error(new StringBuffer().append("Error initializing authentication scheme: ").append(str).toString(), e);
                    throw new IllegalStateException(new StringBuffer().append(str).append(" authentication scheme implemented by ").append(cls.getName()).append(" could not be initialized").toString());
                }
            }
            throw new IllegalStateException(new StringBuffer().append("Unsupported authentication scheme ").append(str).toString());
        }
        return authScheme;
    }

    public static synchronized List getDefaultAuthPrefs() {
        List list;
        synchronized (AuthPolicy.class) {
            list = (List) SCHEME_LIST.clone();
        }
        return list;
    }
}
