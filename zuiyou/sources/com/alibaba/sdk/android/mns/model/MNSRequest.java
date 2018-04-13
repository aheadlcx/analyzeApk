package com.alibaba.sdk.android.mns.model;

public class MNSRequest {
    private boolean isAuthorizationRequired = true;

    public boolean isAuthorizationRequired() {
        return this.isAuthorizationRequired;
    }

    public void setIsAuthorizationRequired(boolean z) {
        this.isAuthorizationRequired = z;
    }
}
