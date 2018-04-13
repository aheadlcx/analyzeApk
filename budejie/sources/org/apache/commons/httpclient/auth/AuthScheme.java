package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

public interface AuthScheme {
    String authenticate(Credentials credentials, String str, String str2) throws AuthenticationException;

    String authenticate(Credentials credentials, HttpMethod httpMethod) throws AuthenticationException;

    String getID();

    String getParameter(String str);

    String getRealm();

    String getSchemeName();

    boolean isComplete();

    boolean isConnectionBased();

    void processChallenge(String str) throws MalformedChallengeException;
}
