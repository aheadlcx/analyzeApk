package com.tencent.weibo.sdk.android.model;

public class AccountModel {
    private String accessToken;
    private long expiresIn;
    private String name;
    private String nike;
    private String openID;
    private String openKey;
    private String refreshToken;

    public AccountModel(String str) {
        this.accessToken = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(long j) {
        this.expiresIn = j;
    }

    public String getOpenID() {
        return this.openID;
    }

    public void setOpenID(String str) {
        this.openID = str;
    }

    public String getOpenKey() {
        return this.openKey;
    }

    public void setOpenKey(String str) {
        this.openKey = str;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getNike() {
        return this.nike;
    }

    public void setNike(String str) {
        this.nike = str;
    }
}
