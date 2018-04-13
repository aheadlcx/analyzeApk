package org.apache.commons.httpclient;

public class DefaultMethodRetryHandler implements MethodRetryHandler {
    private boolean requestSentRetryEnabled = false;
    private int retryCount = 3;

    public boolean retryMethod(HttpMethod httpMethod, HttpConnection httpConnection, HttpRecoverableException httpRecoverableException, int i, boolean z) {
        return (!z || this.requestSentRetryEnabled) && i <= this.retryCount;
    }

    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void setRequestSentRetryEnabled(boolean z) {
        this.requestSentRetryEnabled = z;
    }

    public void setRetryCount(int i) {
        this.retryCount = i;
    }
}
