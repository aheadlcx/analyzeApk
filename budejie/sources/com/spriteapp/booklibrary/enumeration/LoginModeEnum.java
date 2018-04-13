package com.spriteapp.booklibrary.enumeration;

public enum LoginModeEnum {
    CHANNEL_LOGIN("/login_channel"),
    WECHAT_LOGIN("/login_wechat"),
    QQ_LOGIN("/login_qq"),
    WEIBO_LOGIN("/login_weibo");
    
    private String value;

    private LoginModeEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
