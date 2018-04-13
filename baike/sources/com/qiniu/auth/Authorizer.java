package com.qiniu.auth;

public class Authorizer {
    private String a;

    public String getUploadToken() {
        return this.a;
    }

    public void setUploadToken(String str) {
        this.a = str;
    }
}
