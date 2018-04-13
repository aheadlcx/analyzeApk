package com.alibaba.baichuan.android.trade.adapter.mtop;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.Map;

public class NetworkRequest implements Serializable {
    public String accessToken;
    public String apiName;
    public String apiVersion = "1.0";
    public String authParams = "";
    public Map extHeaders;
    public boolean isPost = true;
    public boolean needAuth = false;
    public boolean needCache = false;
    public boolean needLogin = false;
    public boolean needWua = false;
    public String openAppKey;
    public Map paramMap;
    public int requestType;
    public int timeOut = -1;

    public boolean check() {
        return (TextUtils.isEmpty(this.apiName) || TextUtils.isEmpty(this.apiVersion)) ? false : true;
    }

    public String toString() {
        return "NetworkRequest [apiName=" + this.apiName + ", apiVersion=" + this.apiVersion + ", params=" + this.paramMap + ", openAppKey=" + this.openAppKey + ", accessToken=" + this.accessToken + ", needAuth=" + this.needAuth + ", needWua=" + this.needWua + "]";
    }
}
