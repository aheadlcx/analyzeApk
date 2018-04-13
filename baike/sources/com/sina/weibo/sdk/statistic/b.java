package com.sina.weibo.sdk.statistic;

import java.util.Map;

class b extends h {
    private String d;
    private Map<String, String> e;

    public b(String str, String str2, Map<String, String> map) {
        super(str);
        this.d = str2;
        this.e = map;
    }

    public String getEvent_id() {
        return this.d;
    }

    public String getmEvent_id() {
        return this.d;
    }

    public void setmEvent_id(String str) {
        this.d = str;
    }

    public Map<String, String> getExtend() {
        return this.e;
    }
}
