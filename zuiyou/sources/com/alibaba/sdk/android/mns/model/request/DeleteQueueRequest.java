package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;

public class DeleteQueueRequest extends MNSRequest {
    private String queueName;

    public DeleteQueueRequest(String str) {
        setQueueName(str);
    }

    private void setQueueName(String str) {
        this.queueName = str;
    }

    public String getQueueName() {
        return this.queueName;
    }
}
