package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;

public class ListQueueRequest extends MNSRequest {
    private String marker;
    private String prefix;
    private Integer retNum;

    public ListQueueRequest(String str, Integer num, String str2) {
        this.prefix = str;
        this.retNum = num;
        this.marker = str2;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public Integer getRetNum() {
        return this.retNum;
    }

    public String getMarker() {
        return this.marker;
    }
}
