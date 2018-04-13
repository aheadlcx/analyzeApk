package com.sina.weibo.sdk.exception;

public class WeiboAuthException extends WeiboException {
    public static final String DEFAULT_AUTH_ERROR_CODE = "-1";
    public static final String DEFAULT_AUTH_ERROR_DESC = "Unknown Error Description";
    public static final String DEFAULT_AUTH_ERROR_TYPE = "Unknown Error Type";
    private final String a;
    private final String b;

    public WeiboAuthException(String str, String str2, String str3) {
        super(str3);
        this.a = str2;
        this.b = str;
    }

    public String getErrorType() {
        return this.a;
    }

    public String getErrorCode() {
        return this.b;
    }
}
