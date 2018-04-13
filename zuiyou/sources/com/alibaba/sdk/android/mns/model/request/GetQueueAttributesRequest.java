package com.alibaba.sdk.android.mns.model.request;

import com.alibaba.sdk.android.mns.model.MNSRequest;
import com.alibaba.sdk.android.mns.model.QueueMeta;

public class GetQueueAttributesRequest extends MNSRequest {
    private QueueMeta queueMeta;
    private String queueName;

    public GetQueueAttributesRequest(String str) {
        setQueueName(str);
    }

    private void setQueueName(String str) {
        this.queueName = str;
    }

    public String getQueueName() {
        return this.queueName;
    }
}
