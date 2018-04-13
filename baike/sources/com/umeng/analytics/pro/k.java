package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.MLog;
import org.json.JSONArray;
import org.json.JSONObject;

public class k {
    public static JSONObject a(Context context, long j, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            g.a(context).a();
            Object jSONObject3 = new JSONObject();
            long j2 = 0;
            if (jSONObject.has("header")) {
                jSONObject3 = jSONObject.getJSONObject("header");
                if (jSONObject3 == null || jSONObject3.length() <= 0) {
                    jSONObject3 = null;
                } else {
                    j2 = j - a((JSONObject) jSONObject3);
                }
            }
            if (jSONObject.has("content")) {
                JSONObject jSONObject4 = jSONObject.getJSONObject("content");
                if (jSONObject4 != null && jSONObject4.length() > 0) {
                    a(context, j2, jSONObject4, jSONObject2);
                    if (jSONObject2.length() < 1) {
                        b(context, j2, jSONObject4, jSONObject2);
                    }
                    if (jSONObject2.length() > 0 && jSONObject3 != null) {
                        jSONObject2.put("header", jSONObject3);
                    }
                }
            }
        } catch (Throwable th) {
        }
        return jSONObject2;
    }

    private static void a(Context context, long j, JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (jSONObject.has("dplus")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("dplus");
                if (jSONObject3 != null && jSONObject3.length() > 0 && a(jSONObject3) > j) {
                    jSONObject3 = b(context, j, jSONObject3);
                }
                if (jSONObject3.length() > 0) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("dplus", jSONObject3);
                    jSONObject2.put("content", jSONObject4);
                }
            }
        } catch (Throwable th) {
        }
    }

    private static void b(Context context, long j, JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (jSONObject.has("analytics")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("analytics");
                if (jSONObject3 != null && jSONObject3.length() > 0) {
                    if (a(jSONObject3) > j) {
                        jSONObject3 = a(context, j);
                    }
                    if (jSONObject3.length() > 0) {
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("analytics", jSONObject3);
                        jSONObject2.put("content", jSONObject4);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    private static JSONObject b(Context context, long j, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONArray jSONArray;
            if (jSONObject.has("session")) {
                jSONArray = jSONObject.getJSONArray("session");
                if (a(jSONArray) > j) {
                    jSONObject.remove("session");
                    g.a(context).a(4);
                    return b(context, j, jSONObject);
                }
                jSONObject2.put("session", jSONArray);
                return jSONObject2;
            } else if (jSONObject.has(b.Y)) {
                jSONArray = jSONObject.getJSONArray(b.Y);
                if (a(jSONArray) > j) {
                    jSONObject.remove(b.Y);
                    g.a(context).a(0);
                    return b(context, j, jSONObject);
                }
                jSONObject2.put(b.Y, jSONArray);
                return jSONObject2;
            } else if (!jSONObject.has(b.ah)) {
                return jSONObject2;
            } else {
                jSONArray = jSONObject.getJSONArray(b.ah);
                if (a(jSONArray) > j) {
                    jSONObject.remove(b.ah);
                    g.a(context).a(1);
                    return b(context, j, jSONObject);
                }
                jSONObject2.put(b.ah, jSONArray);
                return jSONObject2;
            }
        } catch (Throwable th) {
            return jSONObject2;
        }
    }

    private static JSONObject a(Context context, long j) {
        Throwable th;
        JSONObject jSONObject;
        Throwable th2;
        boolean z = true;
        JSONObject jSONObject2 = new JSONObject();
        try {
            MLog.e("splitAnalyticsData========");
            jSONObject2 = i.a(context).b(true);
            if (jSONObject2 != null) {
                try {
                    if (jSONObject2.length() > 0 && a(jSONObject2) > j) {
                        String str = null;
                        if (jSONObject2.has(b.n)) {
                            JSONObject jSONObject3 = jSONObject2.getJSONArray(b.n).getJSONObject(0);
                            if (jSONObject3 != null) {
                                str = jSONObject3.getString("id");
                                jSONObject3.remove(b.t);
                                jSONObject3.remove(b.s);
                                JSONArray jSONArray = new JSONArray();
                                jSONArray.put(jSONObject3);
                                jSONObject2.put(b.n, jSONArray);
                            }
                        } else {
                            z = false;
                        }
                        if (jSONObject2.has(b.N)) {
                            jSONObject2.remove(b.N);
                        }
                        if (jSONObject2.has(b.O)) {
                            jSONObject2.remove(b.O);
                        }
                        if (jSONObject2.has(b.J)) {
                            jSONObject2.remove(b.J);
                        }
                        g.a(context).a(z, str);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    jSONObject = jSONObject2;
                    th2 = th;
                    MLog.e(th2);
                    return jSONObject;
                }
            }
            return jSONObject2;
        } catch (Throwable th32) {
            th = th32;
            jSONObject = jSONObject2;
            th2 = th;
            MLog.e(th2);
            return jSONObject;
        }
    }

    public static long a(JSONObject jSONObject) {
        return (long) jSONObject.toString().getBytes().length;
    }

    public static long a(JSONArray jSONArray) {
        return (long) jSONArray.toString().getBytes().length;
    }
}
