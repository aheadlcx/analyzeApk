package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import java.util.Map;

public class PutObjectRequest extends OSSRequest {
    private String bucketName;
    private Map<String, String> callbackParam;
    private Map<String, String> callbackVars;
    private ObjectMetadata metadata;
    private String objectKey;
    private OSSProgressCallback<PutObjectRequest> progressCallback;
    private byte[] uploadData;
    private String uploadFilePath;

    public PutObjectRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadFilePath = str3;
    }

    public PutObjectRequest(String str, String str2, String str3, ObjectMetadata objectMetadata) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadFilePath = str3;
        this.metadata = objectMetadata;
    }

    public PutObjectRequest(String str, String str2, byte[] bArr) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadData = bArr;
    }

    public PutObjectRequest(String str, String str2, byte[] bArr, ObjectMetadata objectMetadata) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadData = bArr;
        this.metadata = objectMetadata;
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

    public String getUploadFilePath() {
        return this.uploadFilePath;
    }

    public void setUploadFilePath(String str) {
        this.uploadFilePath = str;
    }

    public byte[] getUploadData() {
        return this.uploadData;
    }

    public void setUploadData(byte[] bArr) {
        this.uploadData = bArr;
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    public OSSProgressCallback<PutObjectRequest> getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(OSSProgressCallback<PutObjectRequest> oSSProgressCallback) {
        this.progressCallback = oSSProgressCallback;
    }

    public Map<String, String> getCallbackParam() {
        return this.callbackParam;
    }

    public void setCallbackParam(Map<String, String> map) {
        this.callbackParam = map;
    }

    public Map<String, String> getCallbackVars() {
        return this.callbackVars;
    }

    public void setCallbackVars(Map<String, String> map) {
        this.callbackVars = map;
    }
}
