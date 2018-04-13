package com.sina.weibo.sdk.exception;

public class WeiboHttpException extends WeiboException {
    private final int a;

    public WeiboHttpException(String str, int i) {
        super(str);
        this.a = i;
    }

    public int getStatusCode() {
        return this.a;
    }
}
