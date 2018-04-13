package com.alibaba.sdk.android.common.auth;

public abstract class CustomSignerCredentialProvider extends CredentialProvider {
    public abstract String signContent(String str);
}
