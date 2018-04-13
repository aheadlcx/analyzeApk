package org.apache.commons.httpclient.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BasicScheme extends RFC2617Scheme {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$auth$BasicScheme;
    private boolean complete = false;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$auth$BasicScheme == null) {
            class$ = class$("org.apache.commons.httpclient.auth.BasicScheme");
            class$org$apache$commons$httpclient$auth$BasicScheme = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$BasicScheme;
        }
        LOG = LogFactory.getLog(class$);
    }

    public BasicScheme(String str) throws MalformedChallengeException {
        super(str);
    }

    public String getSchemeName() {
        return AuthState.PREEMPTIVE_AUTH_SCHEME;
    }

    public void processChallenge(String str) throws MalformedChallengeException {
        super.processChallenge(str);
        this.complete = true;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public String authenticate(Credentials credentials, String str, String str2) throws AuthenticationException {
        LOG.trace("enter BasicScheme.authenticate(Credentials, String, String)");
        try {
            return authenticate((UsernamePasswordCredentials) credentials);
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException(new StringBuffer().append("Credentials cannot be used for basic authentication: ").append(credentials.getClass().getName()).toString());
        }
    }

    public boolean isConnectionBased() {
        return false;
    }

    public String authenticate(Credentials credentials, HttpMethod httpMethod) throws AuthenticationException {
        LOG.trace("enter BasicScheme.authenticate(Credentials, HttpMethod)");
        if (httpMethod == null) {
            throw new IllegalArgumentException("Method may not be null");
        }
        try {
            return authenticate((UsernamePasswordCredentials) credentials, httpMethod.getParams().getCredentialCharset());
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException(new StringBuffer().append("Credentials cannot be used for basic authentication: ").append(credentials.getClass().getName()).toString());
        }
    }

    public static String authenticate(UsernamePasswordCredentials usernamePasswordCredentials) {
        return authenticate(usernamePasswordCredentials, "ISO-8859-1");
    }

    public static String authenticate(UsernamePasswordCredentials usernamePasswordCredentials, String str) {
        LOG.trace("enter BasicScheme.authenticate(UsernamePasswordCredentials, String)");
        if (usernamePasswordCredentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("charset may not be null or empty");
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(usernamePasswordCredentials.getUserName());
            stringBuffer.append(":");
            stringBuffer.append(usernamePasswordCredentials.getPassword());
            return new StringBuffer().append("Basic ").append(EncodingUtil.getAsciiString(Base64.encodeBase64(EncodingUtil.getBytes(stringBuffer.toString(), str)))).toString();
        }
    }
}
