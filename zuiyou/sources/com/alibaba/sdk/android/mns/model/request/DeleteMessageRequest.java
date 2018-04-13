package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;

public class DeleteMessageRequest extends MNSRequest {
    private String queueName;
    private String receiptHandle;

    public DeleteMessageRequest(String str, String str2) {
        setQueueName(str);
        setReceiptHandle(str2);
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
}
