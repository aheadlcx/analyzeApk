package com.alibaba.sdk.android.mns.model.result;

import com.alibaba.sdk.android.mns.model.MNSResult;
import com.alibaba.sdk.android.mns.model.Message;

public class SendMessageResult extends MNSResult {
    private String messageBodyMd5;
    private String messageId;
    private String receiptHandle;

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageBodyMd5(String str) {
        this.messageBodyMd5 = str;
    }

    public String getMessageBodyMd5() {
        return this.messageBodyMd5;
    }

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public void setMessageResponse(Message message) {
        setMessageId(message.getMessageId());
        setMessageBodyMd5(message.getMessageBodyMd5());
        if (message.getReceiptHandle() != null) {
            setReceiptHandle(message.getReceiptHandle());
        }
    }
}
