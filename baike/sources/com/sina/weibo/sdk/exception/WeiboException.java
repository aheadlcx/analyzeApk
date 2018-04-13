package com.sina.weibo.sdk.exception;

public class WeiboException extends RuntimeException {
    public WeiboException(String str) {
        super(str);
    }

    public WeiboException(String str, Throwable th) {
        super(str, th);
    }

    public WeiboException(Throwable th) {
        super(th);
    }
}
