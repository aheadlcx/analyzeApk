package com.alibaba.sdk.android.mns.model;

import java.util.Date;

public class QueueMeta {
    protected Long activeMessages = null;
    protected Date createTime = null;
    protected Long delayMessages = null;
    protected Long delaySeconds = null;
    protected Long inactiveMessages = null;
    protected Date lastModifyTime = null;
    protected Integer loggingEnabled = null;
    protected Long maxMessageSize = null;
    protected Long messageRetentionPeriod = null;
    protected Integer pollingWaitSeconds = null;
    protected String queueName = null;
    protected String queueURL = null;
    protected Long visibilityTimeout = null;

    public Integer getLoggingEnabled() {
        return this.loggingEnabled;
    }

    public void setLoggingEnabled(Integer num) {
        this.loggingEnabled = num;
    }

    public void setLoggingEnabled(boolean z) {
        if (z) {
            this.loggingEnabled = Integer.valueOf(1);
        } else {
            this.loggingEnabled = Integer.valueOf(0);
        }
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setQueueName(String str) {
        this.queueName = str;
    }

    public Long getDelaySeconds() {
        return this.delaySeconds;
    }

    public void setDelaySeconds(Long l) {
        this.delaySeconds = l;
    }

    public Long getMessageRetentionPeriod() {
        return this.messageRetentionPeriod;
    }

    public void setMessageRetentionPeriod(Long l) {
        this.messageRetentionPeriod = l;
    }

    public Long getMaxMessageSize() {
        return this.maxMessageSize;
    }

    public void setMaxMessageSize(Long l) {
        this.maxMessageSize = l;
    }

    public Integer getPollingWaitSeconds() {
        return this.pollingWaitSeconds;
    }

    public void setPollingWaitSeconds(Integer num) {
        this.pollingWaitSeconds = num;
    }

    public Long getVisibilityTimeout() {
        return this.visibilityTimeout;
    }

    public void setVisibilityTimeout(Long l) {
        this.visibilityTimeout = l;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public Date getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(Date date) {
        this.lastModifyTime = date;
    }

    public Long getActiveMessages() {
        return this.activeMessages;
    }

    public void setActiveMessages(Long l) {
        this.activeMessages = l;
    }

    public Long getInactiveMessages() {
        return this.inactiveMessages;
    }

    public void setInactiveMessages(Long l) {
        this.inactiveMessages = l;
    }

    public Long getDelayMessages() {
        return this.delayMessages;
    }

    public void setDelayMessages(Long l) {
        this.delayMessages = l;
    }

    public String getQueueURL() {
        return this.queueURL;
    }

    public void setQueueURL(String str) {
        this.queueURL = str;
    }
}
