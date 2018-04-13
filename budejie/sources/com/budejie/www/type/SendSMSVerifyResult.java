package com.budejie.www.type;

import com.google.gson.annotations.SerializedName;

public class SendSMSVerifyResult {
    private String code;
    @SerializedName("expir_time")
    private String expirTime;
    private String msg;
    private String seq;

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

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(String str) {
        this.seq = str;
    }

    public String getExpirTime() {
        return this.expirTime;
    }

    public void setExpirTime(String str) {
        this.expirTime = str;
    }
}
