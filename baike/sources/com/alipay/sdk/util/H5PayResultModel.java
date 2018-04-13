package com.alipay.sdk.util;

public class H5PayResultModel {
    private String a;
    private String b;

    public String getReturnUrl() {
        return this.a;
    }

    public void setReturnUrl(String str) {
        this.a = str;
    }

    public String getResultCode() {
        return this.b;
    }

    public void setResultCode(String str) {
        this.b = str;
    }
}
