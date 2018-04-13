package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;
import com.alibaba.sdk.android.mns.model.QueueMeta;

public class CreateQueueRequest extends MNSRequest {
    private QueueMeta queueMeta;
    private String queueName;

    public CreateQueueRequest(String str) {
        setQueueName(str);
    }

    private void setQueueName(String str) {
        this.queueName = str;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setQueueMeta(QueueMeta queueMeta) {
        this.queueMeta = queueMeta;
    }

    public QueueMeta getQueueMeta() {
        return this.queueMeta;
    }
}
