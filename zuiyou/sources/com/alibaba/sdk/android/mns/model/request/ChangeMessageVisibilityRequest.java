package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;

public class ChangeMessageVisibilityRequest extends MNSRequest {
    private String queueName;
    private String receiptHandle;
    private Integer visibleTime;

    public ChangeMessageVisibilityRequest(String str, String str2, Integer num) {
        setQueueName(str);
        setReceiptHandle(str2);
        setVisibleTime(num);
    }

    public void setQueueName(String str) {
        this.queueName = str;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public void setVisibleTime(Integer num) {
        this.visibleTime = num;
    }

    public Integer getVisibleTime() {
        return this.visibleTime;
    }
}
