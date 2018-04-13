package com.alibaba.sdk.android.common.auth;

@Deprecated
public class PlainTextAKSKCredentialProvider extends CredentialProvider {
    private String accessKeyId;
    private String accessKeySecret;

    public PlainTextAKSKCredentialProvider(String str, String str2) {
        this.accessKeyId = str.trim();
        this.accessKeySecret = str2.trim();
    }

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public void setAccessKeySecret(String str) {
        this.accessKeySecret = str;
    }
}
