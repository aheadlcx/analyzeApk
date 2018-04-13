package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.File;
import java.util.Map;

public class ResumableUploadRequest extends OSSRequest {
    private String bucketName;
    private Map<String, String> callbackParam;
    private Map<String, String> callbackVars;
    private Boolean deleteUploadOnCancelling = Boolean.valueOf(true);
    private ObjectMetadata metadata;
    private String objectKey;
    private long partSize = 262144;
    private OSSProgressCallback<ResumableUploadRequest> progressCallback;
    private String recordDirectory;
    private String uploadFilePath;

    public ResumableUploadRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadFilePath = str3;
    }

    public ResumableUploadRequest(String str, String str2, String str3, ObjectMetadata objectMetadata) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadFilePath = str3;
        this.metadata = objectMetadata;
    }

    public ResumableUploadRequest(String str, String str2, String str3, String str4) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadFilePath = str3;
        setRecordDirectory(str4);
    }

    public ResumableUploadRequest(String str, String str2, String str3, ObjectMetadata objectMetadata, String str4) {
        this.bucketName = str;
        this.objectKey = str2;
        this.uploadFilePath = str3;
        this.metadata = objectMetadata;
        setRecordDirectory(str4);
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

    public String getRecordDirectory() {
        return this.recordDirectory;
    }

    public void setRecordDirectory(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            this.recordDirectory = str;
            return;
        }
        throw new IllegalArgumentException("Record directory must exist, and it should be a directory!");
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    public OSSProgressCallback<ResumableUploadRequest> getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(OSSProgressCallback<ResumableUploadRequest> oSSProgressCallback) {
        this.progressCallback = oSSProgressCallback;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public void setPartSize(long j) {
        if (j < OSSConstants.MIN_PART_SIZE_LIMIT) {
            throw new IllegalArgumentException("Part size must be greater than or equal to 100KB!");
        }
        this.partSize = j;
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

    public Boolean deleteUploadOnCancelling() {
        return this.deleteUploadOnCancelling;
    }

    public void setDeleteUploadOnCancelling(Boolean bool) {
        this.deleteUploadOnCancelling = bool;
    }
}
