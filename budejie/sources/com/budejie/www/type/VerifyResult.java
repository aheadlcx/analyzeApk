package com.budejie.www.type;

import com.google.gson.annotations.SerializedName;

public class VerifyResult {
    private String code;
    @SerializedName("expir_time")
    private String expirTime;
    private String msg;
    private String req;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getReq() {
        return this.req;
    }

    public void setReq(String str) {
        this.req = str;
    }

    public String getExpirTime() {
        return this.expirTime;
    }

    public void setExpirTime(String str) {
        this.expirTime = str;
    }
}
