package com.alipay.android.phone.mrpc.core;

public class Response {
    protected byte[] a;
    protected String b;

    public String getContentType() {
        return this.b;
    }

    public byte[] getResData() {
        return this.a;
    }

    public void setContentType(String str) {
        this.b = str;
    }

    public void setResData(byte[] bArr) {
        this.a = bArr;
    }
}
