package com.alibaba.sdk.android.common.auth;

public class StsTokenCredentialProvider extends CredentialProvider {
    private String accessKeyId;
    private String secretKeyId;
    private String securityToken;

    public StsTokenCredentialProvider(String str, String str2, String str3) {
        this.accessKeyId = str.trim();
        this.secretKeyId = str2.trim();
        this.securityToken = str3.trim();
    }

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public String getSecretKeyId() {
        return this.secretKeyId;
    }

    public void setSecretKeyId(String str) {
        this.secretKeyId = str;
    }

    public String getSecurityToken() {
        return this.securityToken;
    }

    public void setSecurityToken(String str) {
        this.securityToken = str;
    }

    public FederationToken getFederationToken() {
        return new FederationToken(this.accessKeyId, this.secretKeyId, this.securityToken, Long.MAX_VALUE);
    }
}
