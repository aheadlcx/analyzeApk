package org.apache.commons.httpclient.auth;

public class HttpAuthRealm extends AuthScope {
    public HttpAuthRealm(String str, String str2) {
        super(str, -1, str2, AuthScope.ANY_SCHEME);
    }
}
