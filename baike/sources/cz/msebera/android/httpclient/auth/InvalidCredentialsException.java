package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException(String str) {
        super(str);
    }

    public InvalidCredentialsException(String str, Throwable th) {
        super(str, th);
    }
}
