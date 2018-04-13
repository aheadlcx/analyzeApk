package com.umeng.analytics.d;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.b.f;
import com.umeng.analytics.b.f.a;
import com.umeng.analytics.b.g;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class r {
    private static JSONObject b = new JSONObject();
    private final Map<String, Long> a = new HashMap();

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                this.a.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Long l;
            synchronized (this.a) {
                l = (Long) this.a.remove(str);
            }
            if (l != null) {
                long currentTimeMillis = System.currentTimeMillis() - l.longValue();
                synchronized (b) {
                    try {
                        b = new JSONObject();
                        b.put(g.ab, str);
                        b.put("duration", currentTimeMillis);
                    } catch (Throwable th) {
                    }
                }
            }
        }
    }

    public void a() {
        String str = null;
        long j = 0;
        synchronized (this.a) {
            for (Entry entry : this.a.entrySet()) {
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

    public static void a(Context context) {
        if (context != null) {
            try {
                synchronized (b) {
                    if (b.length() > 0) {
                        f.a(context).a(p.a(), b, a.PAGE);
                        b = new JSONObject();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
