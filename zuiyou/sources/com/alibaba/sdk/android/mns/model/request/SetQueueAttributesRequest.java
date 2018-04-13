package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;
import com.alibaba.sdk.android.mns.model.QueueMeta;

public class SetQueueAttributesRequest extends MNSRequest {
    private QueueMeta queueMeta;
    private String queueName;

    public SetQueueAttributesRequest(String str) {
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
