package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

public class DefaultHttpMethodRetryHandler implements HttpMethodRetryHandler {
    private static Class SSL_HANDSHAKE_EXCEPTION;
    private boolean requestSentRetryEnabled;
    private int retryCount;

    static {
        SSL_HANDSHAKE_EXCEPTION = null;
        try {
            SSL_HANDSHAKE_EXCEPTION = Class.forName("javax.net.ssl.SSLHandshakeException");
        } catch (ClassNotFoundException e) {
        }
    }

    public DefaultHttpMethodRetryHandler(int i, boolean z) {
        this.retryCount = i;
        this.requestSentRetryEnabled = z;
    }

    public DefaultHttpMethodRetryHandler() {
        this(3, false);
    }

    public boolean retryMethod(HttpMethod httpMethod, IOException iOException, int i) {
        if (httpMethod == null) {
            throw new IllegalArgumentException("HTTP method may not be null");
        } else if (iOException == null) {
            throw new IllegalArgumentException("Exception parameter may not be null");
        } else if ((httpMethod instanceof HttpMethodBase) && ((HttpMethodBase) httpMethod).isAborted()) {
            return false;
        } else {
            if (i > this.retryCount) {
                return false;
            }
            if (iOException instanceof NoHttpResponseException) {
                return true;
            }
            if (iOException instanceof InterruptedIOException) {
                return false;
            }
            if (iOException instanceof UnknownHostException) {
                return false;
            }
            if (iOException instanceof NoRouteToHostException) {
                return false;
            }
            if (SSL_HANDSHAKE_EXCEPTION != null && SSL_HANDSHAKE_EXCEPTION.isInstance(iOException)) {
                return false;
            }
            if (!httpMethod.isRequestSent() || this.requestSentRetryEnabled) {
                return true;
            }
            return false;
        }
    }

    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }

    public int getRetryCount() {
        return this.retryCount;
    }
}
