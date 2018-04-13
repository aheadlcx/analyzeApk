package com.meizu.cloud.pushsdk.platform.message;

public class StrategyMessage implements Comparable<StrategyMessage> {
    private String appId;
    private String appKey;
    long currentTime;
    private String packageName;
    private String params;
    private String pushId;
    private int retryCount = 0;
    private int strategyChildType;
    private int strategyType;

    public StrategyMessage(String str, String str2, String str3, String str4, int i, int i2, String str5) {
        this.appId = str;
        this.appKey = str2;
        this.packageName = str3;
        this.pushId = str4;
        this.strategyType = i;
        this.strategyChildType = i2;
        this.params = str5;
        this.currentTime = System.currentTimeMillis();
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getPushId() {
        return this.pushId;
    }

    public void setPushId(String str) {
        this.pushId = str;
    }

    public int getStrategyType() {
        return this.strategyType;
    }

    public void setStrategyType(int i) {
        this.strategyType = i;
    }

    public int getStrategyChildType() {
        return this.strategyChildType;
    }

    public void setStrategyChildType(int i) {
        this.strategyChildType = i;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String str) {
        this.params = str;
    }

    public void countDownRetryCount() {
        this.retryCount = 0;
    }

    public boolean isRetry() {
        int i = this.retryCount + 1;
        this.retryCount = i;
        if (i > 1) {
            return false;
        }
        return true;
    }

    public int compareTo(StrategyMessage strategyMessage) {
        return (int) (this.currentTime - strategyMessage.currentTime);
    }

    public String toString() {
        return "StrategyMessage{strategyType=" + this.strategyType + ", packageName='" + this.packageName + '\'' + ", appKey='" + this.appKey + '\'' + ", appId='" + this.appId + '\'' + ", pushId='" + this.pushId + '\'' + ", strategyChildType=" + this.strategyChildType + ", params='" + this.params + '\'' + '}';
    }
}
