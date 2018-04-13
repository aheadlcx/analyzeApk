package com.alipay.android.phone.mrpc.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HttpUrlHeader implements Serializable {
    private Map<String, String> a = new HashMap();

    public String getHead(String str) {
        return (String) this.a.get(str);
    }

    public Map<String, String> getHeaders() {
        return this.a;
    }

    public void setHead(String str, String str2) {
        this.a.put(str, str2);
    }

    public void setHeaders(Map<String, String> map) {
        this.a = map;
    }
}
