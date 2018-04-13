package com.alibaba.sdk.android.oss.model;

public class OSSRequest {
    private boolean isAuthorizationRequired = true;

    public boolean isAuthorizationRequired() {
        return this.isAuthorizationRequired;
    }

    public void setIsAuthorizationRequired(boolean z) {
        this.isAuthorizationRequired = z;
    }
}
