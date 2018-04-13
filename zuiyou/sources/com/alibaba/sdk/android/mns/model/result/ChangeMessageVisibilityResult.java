package com.alibaba.sdk.android.mns.model.result;

import com.alibaba.sdk.android.mns.model.MNSResult;
import com.alibaba.sdk.android.mns.model.Message;
import java.util.Date;

public class ChangeMessageVisibilityResult extends MNSResult {
    private Date nextVisibleTime;
    private String receiptHandle;

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public void setNextVisibleTime(Date date) {
        this.nextVisibleTime = date;
    }

    public Date getNextVisibleTime() {
        return this.nextVisibleTime;
    }

    public void setChangeVisibleResponse(Message message) {
        setReceiptHandle(message.getReceiptHandle());
        setNextVisibleTime(message.getNextVisibleTime());
    }
}
