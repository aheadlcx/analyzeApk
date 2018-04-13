package com.alipay.android.phone.mrpc.core;

public class HttpUrlResponse extends Response {
    private int c;
    private String d;
    private long e;
    private long f;
    private String g;
    private HttpUrlHeader h;

    public HttpUrlResponse(HttpUrlHeader httpUrlHeader, int i, String str, byte[] bArr) {
        this.h = httpUrlHeader;
        this.c = i;
        this.d = str;
        this.a = bArr;
    }

    public String getCharset() {
        return this.g;
    }

    public int getCode() {
        return this.c;
    }

    public long getCreateTime() {
        return this.e;
    }

    public HttpUrlHeader getHeader() {
        return this.h;
    }

    public String getMsg() {
        return this.d;
    }

    public long getPeriod() {
        return this.f;
    }

    public void setCharset(String str) {
        this.g = str;
    }

    public void setCreateTime(long j) {
        this.e = j;
    }

    public void setHeader(HttpUrlHeader httpUrlHeader) {
        this.h = httpUrlHeader;
    }

    public void setPeriod(long j) {
        this.f = j;
    }
}
