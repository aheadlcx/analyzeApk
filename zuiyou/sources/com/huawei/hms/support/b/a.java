package com.huawei.hms.support.b;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.c.g;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static a a;
    private static final Object b = new Object();

    public static a a() {
        a aVar;
        synchronized (b) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public void a(Context context, String str, Map<String, String> map) {
        if (!b()) {
            Object a = a(map);
            if (!TextUtils.isEmpty(a)) {
                com.b.a.c.a.a(context, str, a);
            }
        }
    }

    private String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            com.huawei.hms.support.log.a.d("HiAnalyticsUtil", "AnalyticsHelper create json exception" + e.getMessage());
        }
        return jSONObject.toString();
    }

    public boolean b() {
        if (g.a()) {
            return false;
        }
        com.huawei.hms.support.log.a.a("HiAnalyticsUtil", "not ChinaROM ");
        return true;
    }
}
