package com.tencent.open.utils;

public class Util$Statistic {
    public long reqSize;
    public String response;
    public long rspSize;

    public Util$Statistic(String str, int i) {
        this.response = str;
        this.reqSize = (long) i;
        if (this.response != null) {
            this.rspSize = (long) this.response.length();
        }
    }
}
