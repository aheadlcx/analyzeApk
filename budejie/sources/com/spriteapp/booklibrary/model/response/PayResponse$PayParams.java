package com.spriteapp.booklibrary.model.response;

public class PayResponse$PayParams {
    private String app_id;
    private String charset;
    private String method;
    private String notify_url;
    private String sign;
    private String sign_type;
    private String timestamp;
    private String version;

    public String getApp_id() {
        return this.app_id;
    }

    public void setApp_id(String str) {
        this.app_id = str;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public String getSign_type() {
        return this.sign_type;
    }

    public void setSign_type(String str) {
        this.sign_type = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getNotify_url() {
        return this.notify_url;
    }

    public void setNotify_url(String str) {
        this.notify_url = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }
}
