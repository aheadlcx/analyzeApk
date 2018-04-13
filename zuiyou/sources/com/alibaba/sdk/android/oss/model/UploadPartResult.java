package com.alibaba.sdk.android.oss.model;

public class UploadPartResult extends OSSResult {
    private String eTag;

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String str) {
        this.eTag = str;
    }
}
