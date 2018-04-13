package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.g.a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class p {
    private static JSONObject a = new JSONObject();
    private final Map<String, Long> b = new HashMap();

    public static void a(Context context) {
        if (context != null) {
            try {
                synchronized (a) {
                    if (a.length() > 0) {
                        g.a(context).a(o.a().d(), a, a.PAGE);
                        a = new JSONObject();
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.b) {
                this.b.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str) && this.b.containsKey(str)) {
            Long l;
            synchronized (this.b) {
                l = (Long) this.b.get(str);
            }
            if (l != null) {
                long currentTimeMillis = System.currentTimeMillis() - l.longValue();
                synchronized (a) {
                    try {
                        a = new JSONObject();
                        a.put(b.u, str);
                        a.put("duration", currentTimeMillis);
                    } catch (Throwable th) {
                    }
                }
            }
        }
    }

    public void a() {
        String str = null;
        long j = 0;
        synchronized (this.b) {
            for (Entry entry : this.b.entrySet()) {
                String str2;
                long j2;
                if (((Long) entry.getValue()).longValue() > j) {
                    long longValue = ((Long) entry.getValue()).longValue();
                    str2 = (String) entry.getKey();
                    j2 = longValue;
                } else {
                    j2 = j;
                    str2 = str;
                }
                str = str2;
                j = j2;
            }
        }
        if (str != null) {
            b(str);
        }
    }
}
