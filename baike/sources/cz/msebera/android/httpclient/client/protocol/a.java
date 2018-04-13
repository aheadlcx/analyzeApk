package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.auth.AuthOption;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.AuthenticationException;
import cz.msebera.android.httpclient.auth.ContextAwareAuthScheme;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.Queue;

@Deprecated
abstract class a implements HttpRequestInterceptor {
    final HttpClientAndroidLog a = new HttpClientAndroidLog(getClass());

    void a(AuthState authState, HttpRequest httpRequest, HttpContext httpContext) {
        AuthScheme authScheme = authState.getAuthScheme();
        Credentials credentials = authState.getCredentials();
        switch (b.a[authState.getState().ordinal()]) {
            case 1:
                return;
            case 2:
                a(authScheme);
                if (authScheme.isConnectionBased()) {
                    return;
                }
                break;
            case 3:
                Queue authOptions = authState.getAuthOptions();
                if (authOptions == null) {
                    a(authScheme);
                    break;
                }
                while (!authOptions.isEmpty()) {
                    AuthOption authOption = (AuthOption) authOptions.remove();
                    authScheme = authOption.getAuthScheme();
                    credentials = authOption.getCredentials();
                    authState.update(authScheme, credentials);
                    if (this.a.isDebugEnabled()) {
                        this.a.debug("Generating response to an authentication challenge using " + authScheme.getSchemeName() + " scheme");
                    }
                    try {
                        httpRequest.addHeader(a(authScheme, credentials, httpRequest, httpContext));
                        return;
                    } catch (AuthenticationException e) {
                        if (this.a.isWarnEnabled()) {
                            this.a.warn(authScheme + " authentication error: " + e.getMessage());
                        }
                    }
                }
                return;
        }
        if (authScheme != null) {
            try {
                httpRequest.addHeader(a(authScheme, credentials, httpRequest, httpContext));
            } catch (AuthenticationException e2) {
                if (this.a.isErrorEnabled()) {
                    this.a.error(authScheme + " authentication error: " + e2.getMessage());
                }
            }
        }
    }

    private void a(AuthScheme authScheme) {
        Asserts.notNull(authScheme, "Auth scheme");
    }

    private Header a(AuthScheme authScheme, Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) throws AuthenticationException {
        Asserts.notNull(authScheme, "Auth scheme");
        if (authScheme instanceof ContextAwareAuthScheme) {
            return ((ContextAwareAuthScheme) authScheme).authenticate(credentials, httpRequest, httpContext);
        }
        return authScheme.authenticate(credentials, httpRequest);
    }
}
