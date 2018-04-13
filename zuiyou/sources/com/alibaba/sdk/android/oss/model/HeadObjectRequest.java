package com.alibaba.sdk.android.oss.model;

public class HeadObjectRequest extends OSSRequest {
    private String bucketName;
    private String objectKey;

    public HeadObjectRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }
}
