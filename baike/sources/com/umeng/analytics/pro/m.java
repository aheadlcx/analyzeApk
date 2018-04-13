package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.i.d;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class m {
    private static Context e;
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private JSONObject f;

    private static class a {
        private static final m a = new m();
    }

    private m() {
        this.a = 128;
        this.b = 256;
        this.c = 1024;
        this.d = 10;
        this.f = null;
        try {
            if (this.f == null) {
                b(e);
            }
        } catch (Throwable th) {
        }
    }

    public static m a(Context context) {
        if (e == null && context != null) {
            e = context.getApplicationContext();
        }
        return a.a;
    }

    public void a(String str, Map<String, Object> map, long j) {
        try {
            if (!a(str) || !b((Map) map)) {
                return;
            }
            if (Arrays.asList(b.as).contains(str)) {
                MLog.e("the id is valid!");
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", str);
            jSONObject.put("ts", System.currentTimeMillis());
            if (j > 0) {
                jSONObject.put(b.R, j);
            }
            jSONObject.put("__t", g.a);
            Iterator it = map.entrySet().iterator();
            for (int i = 0; i < 10 && it.hasNext(); i++) {
                Entry entry = (Entry) it.next();
                if (Arrays.asList(b.as).contains(entry.getKey())) {
                    MLog.e("the key in map is invalid.  ");
                } else {
                    Object value = entry.getValue();
                    if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                        jSONObject.put((String) entry.getKey(), value);
                    }
                }
            }
            Object c = o.a().c();
            String str2 = "__i";
            if (TextUtils.isEmpty(c)) {
                c = "-1";
            }
            jSONObject.put(str2, c);
            UMWorkDispatch.sendEvent(e, 4097, CoreProtocol.getInstance(e), jSONObject);
        } catch (Throwable th) {
        }
    }

    public void a(String str, String str2, long j, int i) {
        try {
            if (!a(str) || !b(str2)) {
                return;
            }
            if (Arrays.asList(b.as).contains(str)) {
                MLog.e("the id is valid!");
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", str);
            jSONObject.put("ts", System.currentTimeMillis());
            if (j > 0) {
                jSONObject.put(b.R, j);
            }
            jSONObject.put("__t", g.a);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put(str, str2);
            Object c = o.a().c();
            String str3 = "__i";
            if (TextUtils.isEmpty(c)) {
                c = "-1";
            }
            jSONObject.put(str3, c);
            UMWorkDispatch.sendEvent(e, 4097, CoreProtocol.getInstance(e), jSONObject);
        } catch (Throwable th) {
        }
    }

    public void a(Object obj) {
        try {
            d dVar = (d) obj;
            Object c = dVar.c();
            Map a = dVar.a();
            Object b = dVar.b();
            long d = dVar.d();
            if (!TextUtils.isEmpty(c)) {
                String subStr = HelperUtils.subStr(c, 128);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(b.aa, subStr);
                jSONObject.put("_$!ts", d);
                c = o.a().c();
                String str = "__ii";
                if (TextUtils.isEmpty(c)) {
                    c = "-1";
                }
                jSONObject.put(str, c);
                if (!TextUtils.isEmpty(b)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(b);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(b.ab, jSONObject2);
                        }
                    } catch (JSONException e) {
                    }
                }
                a();
                if (a != null) {
                    JSONObject a2 = a(a);
                    if (a2.length() > 0) {
                        Iterator keys = a2.keys();
                        while (keys.hasNext()) {
                            String str2 = (String) keys.next();
                            if (Arrays.asList(b.at).contains(str2)) {
                                MLog.e("the key in map about track interface is invalid.  ");
                            } else {
                                jSONObject.put(str2, a2.get(str2));
                            }
                        }
                    }
                }
                if (!(this.f == null || !this.f.has(subStr) || ((Boolean) this.f.get(subStr)).booleanValue())) {
                    jSONObject.put(b.T, 1);
                    this.f.put(subStr, true);
                    c(e);
                }
                i.a(e).a(jSONObject, 0, false);
            }
        } catch (Throwable th) {
        }
    }

    public void a(String str, Map<String, Object> map) {
        try {
            if (a(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(b.R, 0);
                jSONObject.put("__t", g.b);
                Iterator it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Entry entry = (Entry) it.next();
                    if (!(b.T.equals(entry.getKey()) || b.R.equals(entry.getKey()) || "id".equals(entry.getKey()) || "ts".equals(entry.getKey()))) {
                        Object value = entry.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put((String) entry.getKey(), value);
                        }
                    }
                }
                Object c = o.a().c();
                String str2 = "__i";
                if (TextUtils.isEmpty(c)) {
                    c = "-1";
                }
                jSONObject.put(str2, c);
                UMWorkDispatch.sendEvent(e, 4098, CoreProtocol.getInstance(e), jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    private void b(Context context) {
        try {
            Object string = PreferenceWrapper.getDefault(context).getString("fs_lc_tl", null);
            if (!TextUtils.isEmpty(string)) {
                this.f = new JSONObject(string);
            }
            a();
        } catch (Exception e) {
        }
    }

    private void a() {
        int i = 0;
        try {
            Object imprintProperty = UMEnvelopeBuild.imprintProperty(e, "track_list", "");
            if (!TextUtils.isEmpty(imprintProperty)) {
                String[] split = imprintProperty.split("!");
                JSONObject jSONObject = new JSONObject();
                if (this.f != null) {
                    for (String subStr : split) {
                        String subStr2 = HelperUtils.subStr(subStr2, 128);
                        if (this.f.has(subStr2)) {
                            jSONObject.put(subStr2, this.f.get(subStr2));
                        }
                    }
                }
                this.f = new JSONObject();
                if (split.length >= 10) {
                    while (i < 10) {
                        a(split[i], jSONObject);
                        i++;
                    }
                } else {
                    while (i < split.length) {
                        a(split[i], jSONObject);
                        i++;
                    }
                }
                c(e);
            }
        } catch (Exception e) {
        }
    }

    private void a(String str, JSONObject jSONObject) throws JSONException {
        String subStr = HelperUtils.subStr(str, 128);
        if (jSONObject.has(subStr)) {
            a(subStr, ((Boolean) jSONObject.get(subStr)).booleanValue());
        } else {
            a(subStr, false);
        }
    }

    private void a(String str, boolean z) {
        try {
            if (!b.T.equals(str) && !b.R.equals(str) && !"id".equals(str) && !"ts".equals(str) && !this.f.has(str)) {
                this.f.put(str, z);
            }
        } catch (Exception e) {
        }
    }

    private void c(Context context) {
        try {
            if (this.f != null) {
                PreferenceWrapper.getDefault(e).edit().putString("fs_lc_tl", this.f.toString()).commit();
            }
        } catch (Throwable th) {
        }
    }

    public void a(List<String> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    a();
                    int i;
                    if (this.f == null) {
                        this.f = new JSONObject();
                        int size = list.size();
                        for (i = 0; i < size; i++) {
                            if (this.f != null) {
                                if (this.f.length() >= 5) {
                                    break;
                                }
                            }
                            this.f = new JSONObject();
                            String str = (String) list.get(i);
                            if (!TextUtils.isEmpty(str)) {
                                a(HelperUtils.subStr(str, 128), false);
                            }
                        }
                        c(e);
                    } else if (this.f.length() >= 5) {
                        MLog.d("already setFistLaunchEvent, igone.");
                    } else {
                        i = 0;
                        while (i < list.size()) {
                            if (this.f.length() >= 5) {
                                MLog.d(" add setFistLaunchEvent over.");
                                return;
                            } else {
                                a(HelperUtils.subStr((String) list.get(i), 128), false);
                                i++;
                            }
                        }
                        c(e);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private JSONObject a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                try {
                    String str = (String) entry.getKey();
                    if (str != null) {
                        String subStr = HelperUtils.subStr(str, 128);
                        Object value = entry.getValue();
                        if (value != null) {
                            JSONArray jSONArray;
                            int i;
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    jSONArray = new JSONArray();
                                    for (int put : iArr) {
                                        jSONArray.put(put);
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    jSONArray = new JSONArray();
                                    for (double put2 : dArr) {
                                        jSONArray.put(put2);
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    jSONArray = new JSONArray();
                                    for (long put3 : jArr) {
                                        jSONArray.put(put3);
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    jSONArray = new JSONArray();
                                    for (float f : fArr) {
                                        jSONArray.put((double) f);
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    jSONArray = new JSONArray();
                                    for (short put4 : sArr) {
                                        jSONArray.put(put4);
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                }
                            } else if (value instanceof List) {
                                List list = (List) value;
                                jSONArray = new JSONArray();
                                for (i = 0; i < list.size(); i++) {
                                    Object obj = list.get(i);
                                    if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                                        jSONArray.put(list.get(i));
                                    }
                                }
                                if (jSONArray.length() > 0) {
                                    jSONObject.put(subStr, jSONArray);
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(subStr, HelperUtils.subStr(value.toString(), 256));
                            } else if ((value instanceof Long) || (value instanceof Integer) || (value instanceof Float) || (value instanceof Double) || (value instanceof Short)) {
                                jSONObject.put(subStr, value);
                            } else {
                                MLog.e("The param has not support type. please check !");
                            }
                        }
                    }
                } catch (Throwable e) {
                    MLog.e(e);
                }
            }
        } catch (Exception e2) {
        }
        return jSONObject;
    }

    private boolean a(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        MLog.e("Event id is empty or too long in tracking Event");
        return false;
    }

    private boolean b(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 256) {
                return true;
            }
            MLog.e("Event label or value is empty or too long in tracking Event");
            return false;
        } catch (Exception e) {
        }
    }

    private boolean c(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 1024) {
                return true;
            }
            MLog.e("DeepLink value too long in tracking Event.");
            return false;
        } catch (Exception e) {
        }
    }

    private boolean b(Map<String, Object> map) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    for (Entry entry : map.entrySet()) {
                        if (!a((String) entry.getKey())) {
                            MLog.e("map has NULL key. please check!");
                            return false;
                        } else if (entry.getValue() == null) {
                            return false;
                        } else {
                            if (entry.getValue() instanceof String) {
                                if (b.aq.equals(entry.getKey())) {
                                    if (!c(entry.getValue().toString())) {
                                        return false;
                                    }
                                } else if (!b(entry.getValue().toString())) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
            }
        }
        MLog.e("map is null or empty in onEvent");
        return false;
    }
}
