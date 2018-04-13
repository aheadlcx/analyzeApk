package com.sina.weibo.sdk.exception;

public class WeiboDialogException extends WeiboException {
    private int a;
    private String b;

    public WeiboDialogException(String str, int i, String str2) {
        super(str);
        this.a = i;
        this.b = str2;
    }

    public int getErrorCode() {
        return this.a;
    }

    public String getFailingUrl() {
        return this.b;
    }
}
