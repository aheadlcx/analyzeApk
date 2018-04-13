package com.alibaba.sdk.android.common.auth;

import com.alibaba.sdk.android.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.OSSLog;

public abstract class FederationCredentialProvider extends CredentialProvider {
    private volatile FederationToken cachedToken;

    public abstract FederationToken getFederationToken();

    public synchronized FederationToken getValidFederationToken() {
        if (this.cachedToken == null || DateUtil.getFixedSkewedTimeMillis() / 1000 > this.cachedToken.getExpiration() - 15) {
            if (this.cachedToken != null) {
                OSSLog.logD("token expired! current time: " + (DateUtil.getFixedSkewedTimeMillis() / 1000) + " token expired: " + this.cachedToken.getExpiration());
            }
            this.cachedToken = getFederationToken();
        }
        return this.cachedToken;
    }
}
