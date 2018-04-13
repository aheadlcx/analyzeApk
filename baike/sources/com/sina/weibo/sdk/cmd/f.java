package com.sina.weibo.sdk.cmd;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.LogUtil;
import com.umeng.analytics.pro.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class f {
    private static final String a = e.class.getName();
    private List<a> b;
    private List<c> c;
    private int d;

    public f(String str) throws WeiboException {
        a(str);
    }

    private void a(String str) throws WeiboException {
        int i = 0;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(b.J) || jSONObject.has("error_code")) {
                    LogUtil.w(a, "load cmd api has error !!!");
                    throw new WeiboException("load cmd api has error !!!");
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("cmd");
                if (optJSONObject != null) {
                    this.d = optJSONObject.optInt("frequency");
                    JSONArray optJSONArray = optJSONObject.optJSONArray("app_install");
                    if (optJSONArray != null) {
                        this.b = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            this.b.add(new a(optJSONArray.getJSONObject(i2)));
                        }
                    }
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("app_invoke");
                    if (optJSONArray2 != null) {
                        this.c = new ArrayList();
                        while (i < optJSONArray2.length()) {
                            this.c.add(new c(optJSONArray2.getJSONObject(i)));
                            i++;
                        }
                    }
                }
            } catch (JSONException e) {
                LogUtil.d(a, "parse NotificationInfo error: " + e.getMessage());
            }
        }
    }

    public List<a> getInstallCmds() {
        return this.b;
    }

    public void setInstallCmds(List<a> list) {
        this.b = list;
    }

    public List<c> getInvokeCmds() {
        return this.c;
    }

    public void setInvokeCmds(List<c> list) {
        this.c = list;
    }

    public int getFrequency() {
        return this.d;
    }

    public void setFrequency(int i) {
        this.d = i;
    }
}
