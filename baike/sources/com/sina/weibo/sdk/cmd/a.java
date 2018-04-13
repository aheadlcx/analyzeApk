package com.sina.weibo.sdk.cmd;

import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboException;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

class a extends e {
    private long a;
    private List<String> b;
    private String c;
    private String d;

    public a(String str) throws WeiboException {
        super(str);
    }

    public a(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void initFromJsonObj(JSONObject jSONObject) {
        super.initFromJsonObj(jSONObject);
        this.d = jSONObject.optString("download_url");
        Object optString = jSONObject.optString("app_package");
        if (!TextUtils.isEmpty(optString)) {
            this.b = Arrays.asList(optString.split("\\|"));
        }
        this.c = jSONObject.optString("app_sign");
        this.a = jSONObject.optLong("app_version");
    }

    public long getAppVersion() {
        return this.a;
    }

    public void setAppVersion(long j) {
        this.a = j;
    }

    public List<String> getAppPackage() {
        return this.b;
    }

    public void setAppPackage(List<String> list) {
        this.b = list;
    }

    public String getAppSign() {
        return this.c;
    }

    public void setAppSign(String str) {
        this.c = str;
    }

    public String getDownloadUrl() {
        return this.d;
    }

    public void setDownloadUrl(String str) {
        this.d = str;
    }
}
