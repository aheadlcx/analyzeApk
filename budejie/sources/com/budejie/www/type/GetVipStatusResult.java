package com.budejie.www.type;

public class GetVipStatusResult {
    private String days;
    private String expire_time;
    private String is_vip;
    private String start_time;
    private String status;
    public String token;

    public String getExpire_time() {
        return this.expire_time;
    }

    public void setExpire_time(String str) {
        this.expire_time = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStart_time() {
        return this.start_time;
    }

    public void setStart_time(String str) {
        this.start_time = str;
    }

    public String getIs_vip() {
        return this.is_vip;
    }

    public void setIs_vip(String str) {
        this.is_vip = str;
    }

    public String getDays() {
        return this.days;
    }

    public void setDays(String str) {
        this.days = str;
    }
}
