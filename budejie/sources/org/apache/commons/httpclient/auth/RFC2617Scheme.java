package org.apache.commons.httpclient.auth;

import java.util.Map;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

public abstract class RFC2617Scheme implements AuthScheme {
    private Map params = null;

    public abstract String authenticate(Credentials credentials, String str, String str2) throws AuthenticationException;

    public abstract String authenticate(Credentials credentials, HttpMethod httpMethod) throws AuthenticationException;

    public abstract String getSchemeName();

    public abstract boolean isComplete();

    public abstract boolean isConnectionBased();

    public RFC2617Scheme(String str) throws MalformedChallengeException {
        processChallenge(str);
    }

    public void processChallenge(String str) throws MalformedChallengeException {
        if (AuthChallengeParser.extractScheme(str).equalsIgnoreCase(getSchemeName())) {
            this.params = AuthChallengeParser.extractParams(str);
            return;
        }
        throw new MalformedChallengeException(new StringBuffer().append("Invalid ").append(getSchemeName()).append(" challenge: ").append(str).toString());
    }

    protected Map getParameters() {
        return this.params;
    }

    public String getParameter(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter name may not be null");
        } else if (this.params == null) {
            return null;
        } else {
            return (String) this.params.get(str.toLowerCase());
        }
    }

    public String getRealm() {
        return getParameter("realm");
    }

    public String getID() {
        return getRealm();
    }
}
