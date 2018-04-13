package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.internal.utils.a.b;
import com.umeng.commonsdk.internal.utils.a.c;
import com.umeng.commonsdk.statistics.common.e;
import org.json.JSONArray;
import org.json.JSONObject;

public class f {
    public static JSONObject a(Context context) {
        JSONObject jSONObject;
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            try {
                String string = sharedPreferences.getString("blueinfo", null);
                if (string != null) {
                    jSONObject = new JSONObject(string);
                    return jSONObject;
                }
            } catch (Exception e) {
                return e != null ? null : null;
            }
        }
        jSONObject = null;
        return jSONObject;
    }

    public static void a(Context context, Object obj) {
        if (obj != null) {
            try {
                b bVar = (b) obj;
                SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
                String str = null;
                if (sharedPreferences != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_na", bVar.c);
                    jSONObject.put("a_st", bVar.b);
                    jSONObject.put("a_ad", bVar.a);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (jSONObject != null) {
                        str = jSONObject.toString();
                    }
                }
                if (str != null) {
                    sharedPreferences.edit().putString("blueinfo", str).commit();
                }
            } catch (Exception e) {
                if (e != null) {
                    e.e("saveBluetoothInfo:" + e.getMessage());
                }
            }
        }
    }

    public static JSONArray b(Context context) {
        try {
            JSONArray jSONArray;
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("wifiinfo", null);
                if (string != null) {
                    jSONArray = new JSONArray(string);
                    return jSONArray;
                }
            }
            jSONArray = null;
            return jSONArray;
        } catch (Exception e) {
            if (e == null) {
                return null;
            }
            e.e(e.getMessage());
            return null;
        }
    }

    public static void c(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove("wifiinfo").commit();
        }
    }

    public static void a(Context context, c cVar) {
        String str = null;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            if (sharedPreferences != null) {
                JSONArray jSONArray;
                String string = sharedPreferences.getString("wifiinfo", null);
                if (string == null) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(string);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("a_dc", cVar.a);
                jSONObject.put("bssid", cVar.b);
                jSONObject.put("ssid", cVar.c);
                jSONObject.put("a_fcy", cVar.d);
                jSONObject.put("a_hssid", cVar.e);
                jSONObject.put("a_ip", cVar.f);
                jSONObject.put("a_ls", cVar.g);
                jSONObject.put("a_mac", cVar.h);
                jSONObject.put("a_nid", cVar.i);
                jSONObject.put("rssi", cVar.j);
                jSONObject.put("sta", cVar.k);
                jSONObject.put("ts", cVar.l);
                jSONArray.put(jSONObject);
                if (jSONObject != null) {
                    str = jSONArray.toString();
                }
            }
            if (str != null) {
                sharedPreferences.edit().putString("wifiinfo", str).commit();
            }
        } catch (Exception e) {
            if (e != null) {
                e.e(e.getMessage());
            }
        }
    }

    public static void a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("ua", str).commit();
        }
    }

    public static String d(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("ua", null);
        }
        return null;
    }
}
