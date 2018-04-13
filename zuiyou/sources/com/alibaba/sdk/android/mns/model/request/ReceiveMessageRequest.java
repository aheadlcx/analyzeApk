package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;

public class ReceiveMessageRequest extends MNSRequest {
    private String queueName;

    public ReceiveMessageRequest(String str) {
        setQueueName(str);
    }

    public void setQueueName(String str) {
        this.queueName = str;
    }

    public String getQueueName() {
        return this.queueName;
    }
}
