package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

public class ao {
    private static ah a;
    private static HashMap<String, a> b = new HashMap();

    public static class a {
        public long a;
        public String b;
        public long c;
        public String d;
    }

    private static void a(long j, String str, String str2, String str3, HashMap<String, String> hashMap, long j2) {
        synchronized (ao.class) {
            JSONObject jSONObject;
            try {
                cb.a("timelinelog", "eventProcess ts:" + j + " id:" + str + " label:" + str2 + " wakeid:" + str3 + " map:" + hashMap + " duration:" + j2);
                jSONObject = new JSONObject();
                jSONObject.putOpt("ts", Long.valueOf(j));
                jSONObject.putOpt("id", str);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.putOpt("label", str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.putOpt("wake_id", str3);
                }
                jSONObject.putOpt("dur", Long.valueOf(j2));
                if (hashMap != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    for (Entry entry : hashMap.entrySet()) {
                        jSONObject2.put((String) entry.getKey(), entry.getValue());
                    }
                    jSONObject.put("udmap", jSONObject2);
                }
            } catch (Throwable e) {
                cb.a(e);
            } catch (Throwable th) {
                Class cls = ao.class;
            }
            try {
                if (a != null) {
                    a.a("timelinelog", jSONObject.toString());
                } else {
                    cb.a("timelinelog", "do not call onevent before sessionbegin");
                }
            } catch (Throwable e2) {
                cb.a(e2);
            } catch (Throwable e22) {
                cb.a(e22);
            }
        }
    }

    public static void a(ah ahVar) {
        a = ahVar;
    }

    public static void a(String str, String str2) {
        synchronized (ao.class) {
            try {
                Object currentTimeMillis = System.currentTimeMillis();
                a(currentTimeMillis, str, null, str2, null, 0);
            } finally {
                Class cls = ao.class;
            }
        }
    }

    public static void a(String str, String str2, String str3) {
        synchronized (ao.class) {
            try {
                Object currentTimeMillis = System.currentTimeMillis();
                a(currentTimeMillis, str, str2, str3, null, 0);
            } finally {
                Class cls = ao.class;
            }
        }
    }

    public static void a(String str, String str2, HashMap<String, String> hashMap) {
        synchronized (ao.class) {
            try {
                cb.a("timelinelog", "onEventEnd id:" + str);
                long currentTimeMillis = System.currentTimeMillis();
                if (b.containsKey(str)) {
                    a aVar = (a) b.get(str);
                    aVar.a = currentTimeMillis - aVar.c;
                    a(currentTimeMillis, str, str2, aVar.d, hashMap, aVar.a);
                    b.remove(str);
                } else {
                    cb.a("timelinelog", "call onEventEnd before onEventBegin");
                }
            } catch (Throwable th) {
                Class cls = ao.class;
            }
        }
    }

    public static void b(String str, String str2) {
        synchronized (ao.class) {
            try {
                cb.a("timelinelog", "onEventBegin id:" + str);
                Object aVar = new a();
                aVar.b = str;
                aVar.c = System.currentTimeMillis();
                aVar.d = str2;
                b.put(str, aVar);
            } finally {
                Class cls = ao.class;
            }
        }
    }
}
