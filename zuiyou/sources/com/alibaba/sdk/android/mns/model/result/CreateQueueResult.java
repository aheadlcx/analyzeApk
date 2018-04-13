package com.alibaba.sdk.android.mns.model.result;

import com.alibaba.sdk.android.mns.model.MNSResult;

public class CreateQueueResult extends MNSResult {
    private String queueLocation;

    public void setQueueLocation(String str) {
        this.queueLocation = str;
    }

    public String getQueueLocation() {
        return this.queueLocation;
    }
}
