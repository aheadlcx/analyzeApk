package com.alibaba.sdk.android.oss.common.auth;

public abstract class OSSCustomSignerCredentialProvider extends OSSCredentialProvider {
    public abstract String signContent(String str);
}
