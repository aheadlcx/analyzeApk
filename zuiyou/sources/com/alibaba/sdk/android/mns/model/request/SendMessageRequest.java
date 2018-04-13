package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;
import com.alibaba.sdk.android.mns.model.Message;

public class SendMessageRequest extends MNSRequest {
    private Message message;
    private String queueName;

    public SendMessageRequest(String str) {
        setQueueName(str);
    }

    public void setQueueName(String str) {
        this.queueName = str;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }
}
