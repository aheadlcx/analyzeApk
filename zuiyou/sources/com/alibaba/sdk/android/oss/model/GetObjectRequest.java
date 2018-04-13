package com.alibaba.sdk.android.oss.model;

public class GetObjectRequest extends OSSRequest {
    private String bucketName;
    private String objectKey;
    private Range range;
    private String xOssProcess;

    public GetObjectRequest(String str, String str2) {
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

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public String getxOssProcess() {
        return this.xOssProcess;
    }

    public void setxOssProcess(String str) {
        this.xOssProcess = str;
    }
}
