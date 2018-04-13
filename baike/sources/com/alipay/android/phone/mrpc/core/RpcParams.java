package com.alipay.android.phone.mrpc.core;

import java.util.List;
import org.apache.http.Header;

public class RpcParams {
    private String a;
    private List<Header> b;
    private boolean c;

    public String getGwUrl() {
        return this.a;
    }

    public List<Header> getHeaders() {
        return this.b;
    }

    public boolean isGzip() {
        return this.c;
    }

    public void setGwUrl(String str) {
        this.a = str;
    }

    public void setGzip(boolean z) {
        this.c = z;
    }

    public void setHeaders(List<Header> list) {
        this.b = list;
    }
}
