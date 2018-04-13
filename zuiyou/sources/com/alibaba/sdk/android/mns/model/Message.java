package com.alibaba.sdk.android.mns.model;

import java.util.Date;

public class Message {
    private Integer delaySeconds = null;
    private Integer dequeueCount = null;
    private Date enqueueTime = null;
    private Date firstDequeueTime = null;
    private String messageBody = null;
    private String messageBodyMd5 = null;
    private String messageId = null;
    private Date nextVisibleTime = null;
    private Integer priority = null;
    private String receiptHandle = null;

    public Integer getDelaySeconds() {
        return this.delaySeconds;
    }

    public void setDelaySeconds(int i) {
        this.delaySeconds = Integer.valueOf(i);
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public Date getEnqueueTime() {
        return this.enqueueTime;
    }

    public void setEnqueueTime(Date date) {
        this.enqueueTime = date;
    }

    public Date getNextVisibleTime() {
        return this.nextVisibleTime;
    }

    public void setNextVisibleTime(Date date) {
        this.nextVisibleTime = date;
    }

    public Date getFirstDequeueTime() {
        return this.firstDequeueTime;
    }

    public void setFirstDequeueTime(Date date) {
        this.firstDequeueTime = date;
    }

    public Integer getDequeueCount() {
        return this.dequeueCount;
    }

    public void setDequeueCount(int i) {
        this.dequeueCount = Integer.valueOf(i);
    }

    public void setMessageBody(String str) {
        this.messageBody = str;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = Integer.valueOf(i);
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public String getMessageBodyMd5() {
        return this.messageBodyMd5;
    }

    public void setMessageBodyMd5(String str) {
        this.messageBodyMd5 = str;
    }
}
