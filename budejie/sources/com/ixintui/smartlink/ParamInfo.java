package com.ixintui.smartlink;

import java.io.Serializable;
import org.json.JSONObject;

public class ParamInfo implements Serializable {
    private String channel;
    private String clientId;
    private String error;
    private String linkKey;
    private JSONObject param;
    private String platform;
    private String sendId;

    public JSONObject getParam() {
        return this.param;
    }

    public void setParam(JSONObject jSONObject) {
        this.param = jSONObject;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getSendId() {
        return this.sendId;
    }

    public void setSendId(String str) {
        this.sendId = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public String getLinkKey() {
        return this.linkKey;
    }

    public void setLinkKey(String str) {
        this.linkKey = str;
    }

    public String toString() {
        return "ParamInfo{param=" + this.param.toString() + ", clientId='" + this.clientId + '\'' + ", sendId='" + this.sendId + '\'' + ", channel='" + this.channel + '\'' + ", platform='" + this.platform + '\'' + ", error='" + this.error + '\'' + ", linkkey='" + this.linkKey + '\'' + '}';
    }
}
