package org.apache.commons.httpclient.auth;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class HttpAuthenticator {
    private static final Log LOG;
    public static final String PROXY_AUTH = "Proxy-Authenticate";
    public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
    public static final String WWW_AUTH = "WWW-Authenticate";
    public static final String WWW_AUTH_RESP = "Authorization";
    static Class class$org$apache$commons$httpclient$auth$HttpAuthenticator;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$auth$HttpAuthenticator == null) {
            class$ = class$("org.apache.commons.httpclient.auth.HttpAuthenticator");
            class$org$apache$commons$httpclient$auth$HttpAuthenticator = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$HttpAuthenticator;
        }
        LOG = LogFactory.getLog(class$);
    }

    public static AuthScheme selectAuthScheme(Header[] headerArr) throws MalformedChallengeException {
        LOG.trace("enter HttpAuthenticator.selectAuthScheme(Header[])");
        if (headerArr == null) {
            throw new IllegalArgumentException("Array of challenges may not be null");
        } else if (headerArr.length == 0) {
            throw new IllegalArgumentException("Array of challenges may not be empty");
        } else {
            Map hashMap = new HashMap(headerArr.length);
            for (NameValuePair value : headerArr) {
                String value2 = value.getValue();
                hashMap.put(AuthChallengeParser.extractScheme(value2), value2);
            }
            String str = (String) hashMap.get("ntlm");
            if (str != null) {
                return new NTLMScheme(str);
            }
            str = (String) hashMap.get("digest");
            if (str != null) {
                return new DigestScheme(str);
            }
            str = (String) hashMap.get(AuthState.PREEMPTIVE_AUTH_SCHEME);
            if (str != null) {
                return new BasicScheme(str);
            }
            throw new UnsupportedOperationException(new StringBuffer().append("Authentication scheme(s) not supported: ").append(hashMap.toString()).toString());
        }
    }

    private static boolean doAuthenticateDefault(HttpMethod httpMethod, HttpConnection httpConnection, HttpState httpState, boolean z) throws AuthenticationException {
        if (httpMethod == null) {
            throw new IllegalArgumentException("HTTP method may not be null");
        } else if (httpState == null) {
            throw new IllegalArgumentException("HTTP state may not be null");
        } else {
            String proxyHost = httpConnection != null ? z ? httpConnection.getProxyHost() : httpConnection.getHost() : null;
            Object proxyCredentials = z ? httpState.getProxyCredentials(null, proxyHost) : httpState.getCredentials(null, proxyHost);
            if (proxyCredentials == null) {
                return false;
            }
            if (proxyCredentials instanceof UsernamePasswordCredentials) {
                String authenticate = BasicScheme.authenticate((UsernamePasswordCredentials) proxyCredentials, httpMethod.getParams().getCredentialCharset());
                if (authenticate == null) {
                    return false;
                }
                httpMethod.addRequestHeader(new Header(z ? "Proxy-Authorization" : "Authorization", authenticate, true));
                return true;
            }
            throw new InvalidCredentialsException(new StringBuffer().append("Credentials cannot be used for basic authentication: ").append(proxyCredentials.toString()).toString());
        }
    }

    public static boolean authenticateDefault(HttpMethod httpMethod, HttpConnection httpConnection, HttpState httpState) throws AuthenticationException {
        LOG.trace("enter HttpAuthenticator.authenticateDefault(HttpMethod, HttpConnection, HttpState)");
        return doAuthenticateDefault(httpMethod, httpConnection, httpState, false);
    }

    public static boolean authenticateProxyDefault(HttpMethod httpMethod, HttpConnection httpConnection, HttpState httpState) throws AuthenticationException {
        LOG.trace("enter HttpAuthenticator.authenticateProxyDefault(HttpMethod, HttpState)");
        return doAuthenticateDefault(httpMethod, httpConnection, httpState, true);
    }

    private static boolean doAuthenticate(AuthScheme authScheme, HttpMethod httpMethod, HttpConnection httpConnection, HttpState httpState, boolean z) throws AuthenticationException {
        if (authScheme == null) {
            throw new IllegalArgumentException("Authentication scheme may not be null");
        } else if (httpMethod == null) {
            throw new IllegalArgumentException("HTTP method may not be null");
        } else if (httpState == null) {
            throw new IllegalArgumentException("HTTP state may not be null");
        } else {
            String str = null;
            if (httpConnection != null) {
                if (z) {
                    str = httpConnection.getProxyHost();
                } else {
                    str = httpMethod.getParams().getVirtualHost();
                    if (str == null) {
                        str = httpConnection.getHost();
                    }
                }
            }
            String realm = authScheme.getRealm();
            if (LOG.isDebugEnabled()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Using credentials for ");
                if (realm == null) {
                    stringBuffer.append(CookiePolicy.DEFAULT);
                } else {
                    stringBuffer.append('\'');
                    stringBuffer.append(realm);
                    stringBuffer.append('\'');
                }
                stringBuffer.append(" authentication realm at ");
                stringBuffer.append(str);
                LOG.debug(stringBuffer.toString());
            }
            Credentials proxyCredentials = z ? httpState.getProxyCredentials(realm, str) : httpState.getCredentials(realm, str);
            if (proxyCredentials == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("No credentials available for the ");
                if (realm == null) {
                    stringBuffer2.append(CookiePolicy.DEFAULT);
                } else {
                    stringBuffer2.append('\'');
                    stringBuffer2.append(realm);
                    stringBuffer2.append('\'');
                }
                stringBuffer2.append(" authentication realm at ");
                stringBuffer2.append(str);
                throw new CredentialsNotAvailableException(stringBuffer2.toString());
            }
            String authenticate = authScheme.authenticate(proxyCredentials, httpMethod);
            if (authenticate == null) {
                return false;
            }
            httpMethod.addRequestHeader(new Header(z ? "Proxy-Authorization" : "Authorization", authenticate, true));
            return true;
        }
    }

    public static boolean authenticate(AuthScheme authScheme, HttpMethod httpMethod, HttpConnection httpConnection, HttpState httpState) throws AuthenticationException {
        LOG.trace("enter HttpAuthenticator.authenticate(AuthScheme, HttpMethod, HttpConnection, HttpState)");
        return doAuthenticate(authScheme, httpMethod, httpConnection, httpState, false);
    }

    public static boolean authenticateProxy(AuthScheme authScheme, HttpMethod httpMethod, HttpConnection httpConnection, HttpState httpState) throws AuthenticationException {
        LOG.trace("enter HttpAuthenticator.authenticateProxy(AuthScheme, HttpMethod, HttpState)");
        return doAuthenticate(authScheme, httpMethod, httpConnection, httpState, true);
    }
}
