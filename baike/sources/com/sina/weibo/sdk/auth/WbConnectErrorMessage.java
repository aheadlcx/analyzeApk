package com.sina.weibo.sdk.auth;

import com.sina.weibo.sdk.utils.WbAuthConstants;

public class WbConnectErrorMessage {
    private String a = WbAuthConstants.AUTH_FAILED_NOT_INSTALL_MSG;
    private String b = WbAuthConstants.AUTH_FAILED_NOT_INSTALL_CODE;

    public WbConnectErrorMessage(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getErrorMessage() {
        return this.a;
    }

    public String getErrorCode() {
        return this.b;
    }

    public void setErrorMessage(String str) {
        this.a = str;
    }

    public void setErrorCode(String str) {
        this.b = str;
    }
}
