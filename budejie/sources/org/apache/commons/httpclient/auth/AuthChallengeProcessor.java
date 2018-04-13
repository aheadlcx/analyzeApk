package org.apache.commons.httpclient.auth;

import java.util.Collection;
import java.util.Map;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class AuthChallengeProcessor {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
    private HttpParams params = null;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$auth$AuthChallengeProcessor == null) {
            class$ = class$("org.apache.commons.httpclient.auth.AuthChallengeProcessor");
            class$org$apache$commons$httpclient$auth$AuthChallengeProcessor = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
        }
        LOG = LogFactory.getLog(class$);
    }

    public AuthChallengeProcessor(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameter collection may not be null");
        }
        this.params = httpParams;
    }

    public AuthScheme selectAuthScheme(Map map) throws AuthChallengeException {
        if (map == null) {
            throw new IllegalArgumentException("Challenge map may not be null");
        }
        AuthScheme authScheme;
        Collection collection = (Collection) this.params.getParameter(AuthPolicy.AUTH_SCHEME_PRIORITY);
        if (collection == null || collection.isEmpty()) {
            collection = AuthPolicy.getDefaultAuthPrefs();
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Supported authentication schemes in the order of preference: ").append(r0).toString());
        }
        for (String str : r0) {
            if (((String) map.get(str.toLowerCase())) != null) {
                if (LOG.isInfoEnabled()) {
                    LOG.info(new StringBuffer().append(str).append(" authentication scheme selected").toString());
                }
                try {
                    authScheme = AuthPolicy.getAuthScheme(str);
                    if (authScheme == null) {
                        return authScheme;
                    }
                    throw new AuthChallengeException(new StringBuffer().append("Unable to respond to any of these challenges: ").append(map).toString());
                } catch (Throwable e) {
                    throw new AuthChallengeException(e.getMessage());
                }
            } else if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Challenge for ").append(str).append(" authentication scheme not available").toString());
            }
        }
        authScheme = null;
        if (authScheme == null) {
            return authScheme;
        }
        throw new AuthChallengeException(new StringBuffer().append("Unable to respond to any of these challenges: ").append(map).toString());
    }

    public AuthScheme processChallenge(AuthState authState, Map map) throws MalformedChallengeException, AuthenticationException {
        if (authState == null) {
            throw new IllegalArgumentException("Authentication state may not be null");
        } else if (map == null) {
            throw new IllegalArgumentException("Challenge map may not be null");
        } else {
            if (authState.isPreemptive() || authState.getAuthScheme() == null) {
                authState.setAuthScheme(selectAuthScheme(map));
            }
            AuthScheme authScheme = authState.getAuthScheme();
            String schemeName = authScheme.getSchemeName();
            if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Using authentication scheme: ").append(schemeName).toString());
            }
            String str = (String) map.get(schemeName.toLowerCase());
            if (str == null) {
                throw new AuthenticationException(new StringBuffer().append(schemeName).append(" authorization challenge expected, but not found").toString());
            }
            authScheme.processChallenge(str);
            LOG.debug("Authorization challenge processed");
            return authScheme;
        }
    }
}
