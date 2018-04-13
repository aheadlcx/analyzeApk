package com.iflytek.cloud.thirdparty;

import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.speech.UtilityConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class an {
    long a = 0;
    long b = 0;
    JSONObject c = new JSONObject();

    public String a() {
        String jSONObject;
        synchronized (this) {
            cb.a("sessinfo:" + this.c.toString());
            jSONObject = this.c.toString();
        }
        return jSONObject;
    }

    public void a(ce ceVar) {
        this.a = System.currentTimeMillis();
        this.b = SystemClock.elapsedRealtime();
        a("app_fau", bt.a(this.a), false);
        a("stmid", ceVar.e("stmid"), false);
        a("wake_id", ceVar.e("wake_id"), false);
        String e = ceVar.e(UtilityConfig.KEY_CALLER_APPID);
        if (!TextUtils.isEmpty(e)) {
            a("app_caller_appid", e, false);
        }
        ce a = bp.a(null);
        String e2 = a.e("app.ver.code");
        if (!TextUtils.isEmpty(e2)) {
            a("app_cver", e2, false);
        }
        e = a.e("app.ver.name");
        if (!TextUtils.isEmpty(e)) {
            a("app_cver_name", e, false);
        }
    }

    public void a(String str) {
        synchronized (this) {
            a(str, SystemClock.elapsedRealtime() - this.b, false);
        }
    }

    public void a(String str, long j, boolean z) {
        synchronized (this) {
            if (!TextUtils.isEmpty(str)) {
                if (z) {
                    JSONArray optJSONArray = this.c.optJSONArray(str);
                    if (optJSONArray == null) {
                        optJSONArray = new JSONArray();
                        this.c.put(str, optJSONArray);
                    }
                    if (optJSONArray != null) {
                        optJSONArray.put(j);
                    }
                } else {
                    try {
                        this.c.put(str, j);
                    } catch (Throwable e) {
                        cb.a(e);
                    }
                }
            }
        }
    }

    public void a(String str, String str2, boolean z) {
        synchronized (this) {
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                if (z) {
                    JSONArray optJSONArray = this.c.optJSONArray(str);
                    if (optJSONArray == null) {
                        this.c.put(str, new JSONArray());
                    } else {
                        optJSONArray.put(str2);
                    }
                } else {
                    try {
                        this.c.put(str, str2);
                    } catch (Throwable e) {
                        cb.a(e);
                    }
                }
            }
        }
    }
}
