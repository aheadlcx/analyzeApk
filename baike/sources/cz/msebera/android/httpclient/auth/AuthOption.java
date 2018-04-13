package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;

@Immutable
public final class AuthOption {
    private final AuthScheme a;
    private final Credentials b;

    public AuthOption(AuthScheme authScheme, Credentials credentials) {
        Args.notNull(authScheme, "Auth scheme");
        Args.notNull(credentials, "User credentials");
        this.a = authScheme;
        this.b = credentials;
    }

    public AuthScheme getAuthScheme() {
        return this.a;
    }

    public Credentials getCredentials() {
        return this.b;
    }

    public String toString() {
        return this.a.toString();
    }
}
