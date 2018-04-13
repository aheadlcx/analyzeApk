package com.alibaba.sdk.android.common.auth;

import com.alibaba.sdk.android.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.OSSLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class FederationToken {
    private long expiration;
    private String securityToken;
    private String tempAk;
    private String tempSk;

    public FederationToken(String str, String str2, String str3, long j) {
        this.tempAk = str;
        this.tempSk = str2;
        this.securityToken = str3;
        setExpiration(j);
    }

    public FederationToken(String str, String str2, String str3, String str4) {
        this.tempAk = str;
        this.tempSk = str2;
        this.securityToken = str3;
        setExpirationInGMTFormat(str4);
    }

    public String toString() {
        return "FederationToken [tempAk=" + this.tempAk + ", tempSk=" + this.tempSk + ", securityToken=" + this.securityToken + ", expiration=" + this.expiration + "]";
    }

    public String getTempAK() {
        return this.tempAk;
    }

    public String getTempSK() {
        return this.tempSk;
    }

    public String getSecurityToken() {
        return this.securityToken;
    }

    public void setTempAk(String str) {
        this.tempAk = str;
    }

    public void setTempSk(String str) {
        this.tempSk = str;
    }

    public void setSecurityToken(String str) {
        this.securityToken = str;
    }

    public long getExpiration() {
        return this.expiration;
    }

    public void setExpiration(long j) {
        this.expiration = j;
    }

    public void setExpirationInGMTFormat(String str) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            this.expiration = simpleDateFormat.parse(str).getTime() / 1000;
        } catch (ParseException e) {
            if (OSSLog.isEnableLog()) {
                e.printStackTrace();
            }
            this.expiration = (DateUtil.getFixedSkewedTimeMillis() / 1000) + 30;
        }
    }
}
