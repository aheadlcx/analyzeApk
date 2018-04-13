package com.sina.weibo.sdk.cmd;

import com.sina.weibo.sdk.exception.WeiboException;
import org.json.JSONException;
import org.json.JSONObject;

class e {
    private String a;
    private String b;
    private long c;

    public e(String str) throws WeiboException {
        a(str);
    }

    public e(JSONObject jSONObject) {
        initFromJsonObj(jSONObject);
    }

    protected void a(String str) throws WeiboException {
        if (str != null) {
            try {
                initFromJsonObj(new JSONObject(str));
            } catch (JSONException e) {
                e.printStackTrace();
                throw new WeiboException("pase cmd has error !!!");
            }
        }
    }

    protected void initFromJsonObj(JSONObject jSONObject) {
        this.a = jSONObject.optString("notification_text");
        this.b = jSONObject.optString("notification_title");
        this.c = jSONObject.optLong("notification_delay");
    }

    public String getNotificationText() {
        return this.a;
    }

    public void setNotificationText(String str) {
        this.a = str;
    }

    public String getNotificationTitle() {
        return this.b;
    }

    public void setNotificationTitle(String str) {
        this.b = str;
    }

    public long getNotificationDelay() {
        return this.c;
    }

    public void setNotificationDelay(long j) {
        this.c = j;
    }
}
