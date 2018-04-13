package org.apache.commons.httpclient.auth;

public class AuthState {
    public static final String PREEMPTIVE_AUTH_SCHEME = "basic";
    private boolean authAttempted = false;
    private boolean authRequested = false;
    private AuthScheme authScheme = null;
    private boolean preemptive = false;

    public void invalidate() {
        this.authScheme = null;
        this.authRequested = false;
        this.authAttempted = false;
        this.preemptive = false;
    }

    public boolean isAuthRequested() {
        return this.authRequested;
    }

    public void setAuthRequested(boolean z) {
        this.authRequested = z;
    }

    public boolean isAuthAttempted() {
        return this.authAttempted;
    }

    public void setAuthAttempted(boolean z) {
        this.authAttempted = z;
    }

    public void setPreemptive() {
        if (!this.preemptive) {
            if (this.authScheme != null) {
                throw new IllegalStateException("Authentication state already initialized");
            }
            this.authScheme = AuthPolicy.getAuthScheme(PREEMPTIVE_AUTH_SCHEME);
            this.preemptive = true;
        }
    }

    public boolean isPreemptive() {
        return this.preemptive;
    }

    public void setAuthScheme(AuthScheme authScheme) {
        if (authScheme == null) {
            invalidate();
            return;
        }
        if (this.preemptive && !this.authScheme.getClass().isInstance(authScheme)) {
            this.preemptive = false;
            this.authAttempted = false;
        }
        this.authScheme = authScheme;
    }

    public AuthScheme getAuthScheme() {
        return this.authScheme;
    }

    public String getRealm() {
        if (this.authScheme != null) {
            return this.authScheme.getRealm();
        }
        return null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Auth state: auth requested [");
        stringBuffer.append(this.authRequested);
        stringBuffer.append("]; auth attempted [");
        stringBuffer.append(this.authAttempted);
        if (this.authScheme != null) {
            stringBuffer.append("]; auth scheme [");
            stringBuffer.append(this.authScheme.getSchemeName());
            stringBuffer.append("]; realm [");
            stringBuffer.append(this.authScheme.getRealm());
        }
        stringBuffer.append("] preemptive [");
        stringBuffer.append(this.preemptive);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
