package com.alibaba.sdk.android.oss.common.auth;

import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;

public abstract class OSSFederationCredentialProvider extends OSSCredentialProvider {
    private volatile OSSFederationToken cachedToken;

    public abstract OSSFederationToken getFederationToken();

    public synchronized OSSFederationToken getValidFederationToken() {
        if (this.cachedToken == null || DateUtil.getFixedSkewedTimeMillis() / 1000 > this.cachedToken.getExpiration() - 300) {
            if (this.cachedToken != null) {
                OSSLog.logD("token expired! current time: " + (DateUtil.getFixedSkewedTimeMillis() / 1000) + " token expired: " + this.cachedToken.getExpiration());
            }
            this.cachedToken = getFederationToken();
        }
        return this.cachedToken;
    }
}
