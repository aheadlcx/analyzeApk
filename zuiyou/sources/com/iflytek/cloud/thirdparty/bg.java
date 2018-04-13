package com.iflytek.cloud.thirdparty;

import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.speech.UtilityConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class bg {
    JSONObject a = new JSONObject();
    long b = 0;
    long c = 0;

    public synchronized String a() {
        return this.a.toString();
    }

    public void a(ce ceVar) {
        this.c = System.currentTimeMillis();
        this.b = SystemClock.elapsedRealtime();
        a("app_start", bt.a(this.c), false);
        String e = ceVar.e(UtilityConfig.KEY_CALLER_APPID);
        if (!TextUtils.isEmpty(e)) {
            a("app_caller_appid", e, false);
        }
        e = bp.a(null).e("app.ver.code");
        if (!TextUtils.isEmpty(e)) {
            a("app_cver", e, false);
        }
    }

    public synchronized void a(String str) {
        a(str, SystemClock.elapsedRealtime() - this.b, false);
    }

    public synchronized void a(String str, long j, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                JSONArray optJSONArray = this.a.optJSONArray(str);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                    this.a.put(str, optJSONArray);
                }
                if (optJSONArray != null) {
                    optJSONArray.put(j);
                }
            } else {
                try {
                    this.a.put(str, j);
                } catch (Throwable e) {
                    cb.a(e);
                }
            }
        }
    }

    public synchronized void a(String str, String str2, boolean z) {
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            if (z) {
                JSONArray optJSONArray = this.a.optJSONArray(str);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                    this.a.put(str, optJSONArray);
                }
                if (optJSONArray != null) {
                    optJSONArray.put(str2);
                }
            } else {
                try {
                    this.a.put(str, str2);
                } catch (Throwable e) {
                    cb.a(e);
                }
            }
        }
    }
}
