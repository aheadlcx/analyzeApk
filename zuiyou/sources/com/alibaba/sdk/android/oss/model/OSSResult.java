package com.alibaba.sdk.android.oss.model;

import java.util.Map;

public class OSSResult {
    private String requestId;
    private Map<String, String> responseHeader;
    private int statusCode;

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public Map<String, String> getResponseHeader() {
        return this.responseHeader;
    }

    public void setResponseHeader(Map<String, String> map) {
        this.responseHeader = map;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }
}
