package cz.msebera.android.httpclient.client.config;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public final class AuthSchemes {
    public static final String BASIC = "Basic";
    public static final String DIGEST = "Digest";
    public static final String KERBEROS = "Kerberos";
    public static final String NTLM = "NTLM";
    public static final String SPNEGO = "Negotiate";

    private AuthSchemes() {
    }
}
