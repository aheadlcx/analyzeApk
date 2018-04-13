package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

public abstract class AuthSchemeBase implements AuthScheme {
    private String challenge = null;

    public abstract String authenticate(Credentials credentials, String str, String str2) throws AuthenticationException;

    public abstract String authenticate(Credentials credentials, HttpMethod httpMethod) throws AuthenticationException;

    public abstract String getID();

    public abstract String getParameter(String str);

    public abstract String getRealm();

    public abstract String getSchemeName();

    public abstract boolean isComplete();

    public abstract boolean isConnectionBased();

    public abstract void processChallenge(String str) throws MalformedChallengeException;

    public AuthSchemeBase(String str) throws MalformedChallengeException {
        if (str == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        this.challenge = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AuthSchemeBase) {
            return this.challenge.equals(((AuthSchemeBase) obj).challenge);
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.challenge.hashCode();
    }

    public String toString() {
        return this.challenge;
    }
}
