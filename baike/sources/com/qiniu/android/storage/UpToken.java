package com.qiniu.android.storage;

import com.baidu.mobstat.Config;
import com.qiniu.android.utils.UrlSafeBase64;
import org.json.JSONException;
import org.json.JSONObject;

public final class UpToken {
    public static UpToken NULL = new UpToken("", "", "");
    private String a = null;
    public final String accessKey;
    public final String token;

    private UpToken(String str, String str2, String str3) {
        this.a = str;
        this.token = str2;
        this.accessKey = str3;
    }

    public static UpToken parse(String str) {
        try {
            String[] split = str.split(Config.TRACE_TODAY_VISIT_SPLIT);
            if (split.length != 3) {
                return NULL;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(UrlSafeBase64.decode(split[2])));
                if (jSONObject.optString("scope").equals("")) {
                    return NULL;
                }
                if (jSONObject.optInt("deadline") == 0) {
                    return NULL;
                }
                return new UpToken(jSONObject.optString("returnUrl"), str, split[0]);
            } catch (JSONException e) {
                return NULL;
            }
        } catch (Exception e2) {
            return NULL;
        }
    }

    public String toString() {
        return this.token;
    }

    public boolean hasReturnUrl() {
        return !this.a.equals("");
    }
}
