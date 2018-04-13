package com.alibaba.sdk.android.oss.model;

import java.util.ArrayList;
import java.util.List;

public class ListObjectsResult extends OSSResult {
    private String bucketName;
    private List<String> commonPrefixes = new ArrayList();
    private String delimiter;
    private String encodingType;
    private boolean isTruncated;
    private String marker;
    private int maxKeys;
    private String nextMarker;
    private List<OSSObjectSummary> objectSummaries = new ArrayList();
    private String prefix;

    public List<OSSObjectSummary> getObjectSummaries() {
        return this.objectSummaries;
    }

    public void addObjectSummary(OSSObjectSummary oSSObjectSummary) {
        this.objectSummaries.add(oSSObjectSummary);
    }

    public void setObjectSummaries(List<OSSObjectSummary> list) {
        this.objectSummaries.clear();
        if (list != null && !list.isEmpty()) {
            this.objectSummaries.addAll(list);
        }
    }

    public void clearObjectSummaries() {
        this.objectSummaries.clear();
    }

    public List<String> getCommonPrefixes() {
        return this.commonPrefixes;
    }

    public void addCommonPrefix(String str) {
        this.commonPrefixes.add(str);
    }

    public void setCommonPrefixes(List<String> list) {
        this.commonPrefixes.clear();
        if (list != null && !list.isEmpty()) {
            this.commonPrefixes.addAll(list);
        }
    }

    public void clearCommonPrefixes() {
        this.commonPrefixes.clear();
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public int getMaxKeys() {
        return this.maxKeys;
    }

    public void setMaxKeys(int i) {
        this.maxKeys = i;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    public String getEncodingType() {
        return this.encodingType;
    }

    public void setEncodingType(String str) {
        this.encodingType = str;
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    public void setTruncated(boolean z) {
        this.isTruncated = z;
    }
}
