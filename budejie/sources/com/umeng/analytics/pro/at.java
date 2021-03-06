package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class at {
    private static final String a = "fs_lc_tl";
    private final int b = 128;
    private final int c = 256;
    private final int d = 10;
    private Context e;
    private ar f = null;
    private aq g = null;
    private JSONObject h = null;
    private ar i;

    public at(Context context) {
        if (context == null) {
            try {
                by.e("Context is null, can't track event");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.i = ar.b(context);
        this.e = context;
        this.f = ar.b(this.e);
        this.g = this.f.a(this.e);
        if (this.h == null) {
            a(context);
        }
    }

    public void a(String str, Map<String, Object> map, long j) {
        try {
            if (a(str) && b((Map) map)) {
                Object jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                if (j > 0) {
                    jSONObject.put(x.aN, j);
                }
                jSONObject.put("__t", w.a);
                Iterator it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Entry entry = (Entry) it.next();
                    if (!(x.aQ.equals(entry.getKey()) || x.aO.equals(entry.getKey()) || x.aN.equals(entry.getKey()) || "id".equals(entry.getKey()) || "ts".equals(entry.getKey()))) {
                        Object value = entry.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put((String) entry.getKey(), value);
                        }
                    }
                }
                jSONObject.put("__i", bd.g(this.e));
                jSONObject.put("_umpname", ap.a);
                this.i.a(jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    public void a(String str, String str2, long j, int i) {
        try {
            if (a(str) && b(str2)) {
                Object jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                if (j > 0) {
                    jSONObject.put(x.aN, j);
                }
                jSONObject.put("__t", w.a);
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(str, str2);
                jSONObject.put("__i", bd.g(this.e));
                jSONObject.put("_umpname", ap.a);
                this.i.a(jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    public void a(String str, Map<String, Object> map) {
    }

    public void b(String str, Map<String, Object> map) {
        try {
            if (a(str)) {
                Object jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(x.aN, 0);
                jSONObject.put("__t", w.b);
                Iterator it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Entry entry = (Entry) it.next();
                    if (!(x.aQ.equals(entry.getKey()) || x.aO.equals(entry.getKey()) || x.aN.equals(entry.getKey()) || "id".equals(entry.getKey()) || "ts".equals(entry.getKey()))) {
                        Object value = entry.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put((String) entry.getKey(), value);
                        }
                    }
                }
                jSONObject.put("__i", bd.g(this.e));
                this.i.a(jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    public boolean a(List<String> list, int i, String str) {
        int i2 = 0;
        try {
            n a = n.a();
            if (list == null) {
                by.e("cklist is null!");
            } else if (list.size() <= 0) {
                by.e("the KeyList is null!");
                return false;
            } else {
                List arrayList = new ArrayList(list);
                if (a.b((String) arrayList.get(0))) {
                    String str2;
                    String str3;
                    if (arrayList.size() > 8) {
                        str3 = (String) arrayList.get(0);
                        arrayList.clear();
                        arrayList.add(str3);
                        arrayList.add("__cc");
                        arrayList.add("illegal");
                    } else if (!a.a(arrayList)) {
                        str3 = (String) arrayList.get(0);
                        arrayList.clear();
                        arrayList.add(str3);
                        arrayList.add("__cc");
                        arrayList.add("illegal");
                    } else if (a.b(arrayList)) {
                        while (i2 < arrayList.size()) {
                            str3 = (String) arrayList.get(i2);
                            if (str3.length() > 16) {
                                arrayList.remove(i2);
                                arrayList.add(i2, str3.substring(0, 16));
                            }
                            i2++;
                        }
                    } else {
                        str3 = (String) arrayList.get(0);
                        arrayList.clear();
                        arrayList.add(str3);
                        arrayList.add("__cc");
                        arrayList.add("illegal");
                    }
                    if (a.a(str)) {
                        str2 = str;
                    } else {
                        by.e("label  Invalid!");
                        str2 = "__illegal";
                    }
                    final Map hashMap = new HashMap();
                    hashMap.put(arrayList, new l(arrayList, (long) i, str2, System.currentTimeMillis()));
                    bz.b(new cb(this) {
                        final /* synthetic */ at b;

                        public void a() {
                            m.a(this.b.e).a(new f(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void a(Object obj, boolean z) {
                                    if (!obj.equals("success")) {
                                    }
                                }
                            }, hashMap);
                        }
                    });
                } else {
                    by.e("Primary key Invalid!");
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    private void a(Context context) {
        try {
            Object string = ba.a(context).getString(a, null);
            if (!TextUtils.isEmpty(string)) {
                this.h = new JSONObject(string);
            }
            a();
        } catch (Exception e) {
        }
    }

    private void a() {
        int i = 0;
        try {
            if (!TextUtils.isEmpty(this.g.a)) {
                String[] split = this.g.a.split("!");
                JSONObject jSONObject = new JSONObject();
                if (this.h != null) {
                    for (String a : split) {
                        String a2 = bw.a(a2, 128);
                        if (this.h.has(a2)) {
                            jSONObject.put(a2, this.h.get(a2));
                        }
                    }
                }
                this.h = new JSONObject();
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
                b(this.e);
                this.g.a = null;
            }
        } catch (Exception e) {
        }
    }

    private void a(String str, JSONObject jSONObject) throws JSONException {
        String a = bw.a(str, 128);
        if (jSONObject.has(a)) {
            a(a, ((Boolean) jSONObject.get(a)).booleanValue());
        } else {
            a(a, false);
        }
    }

    private void a(String str, boolean z) {
        try {
            if (!x.aQ.equals(str) && !x.aO.equals(str) && !x.aN.equals(str) && !"id".equals(str) && !"ts".equals(str) && !this.h.has(str)) {
                this.h.put(str, z);
            }
        } catch (Exception e) {
        }
    }

    private void b(Context context) {
        try {
            if (this.h != null) {
                ba.a(this.e).edit().putString(a, this.h.toString()).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, List<String> list) {
        try {
            if (this.e == null && context != null) {
                this.e = context;
            }
        } catch (Exception e) {
        }
    }

    private JSONObject a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                try {
                    String str = (String) entry.getKey();
                    if (str != null) {
                        String a = bw.a(str, 128);
                        Object value = entry.getValue();
                        if (value != null) {
                            if (value.getClass().isArray()) {
                                List arrayList;
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    arrayList = new ArrayList();
                                    for (int valueOf : iArr) {
                                        arrayList.add(Integer.valueOf(valueOf));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    arrayList = new ArrayList();
                                    for (double valueOf2 : dArr) {
                                        arrayList.add(Double.valueOf(valueOf2));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    arrayList = new ArrayList();
                                    for (long valueOf3 : jArr) {
                                        arrayList.add(Long.valueOf(valueOf3));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    arrayList = new ArrayList();
                                    for (float valueOf4 : fArr) {
                                        arrayList.add(Float.valueOf(valueOf4));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else if (value instanceof boolean[]) {
                                    boolean[] zArr = (boolean[]) value;
                                    arrayList = new ArrayList();
                                    for (boolean valueOf5 : zArr) {
                                        arrayList.add(Boolean.valueOf(valueOf5));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else if (value instanceof byte[]) {
                                    byte[] bArr = (byte[]) value;
                                    arrayList = new ArrayList();
                                    for (byte valueOf6 : bArr) {
                                        arrayList.add(Byte.valueOf(valueOf6));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    arrayList = new ArrayList();
                                    for (short valueOf7 : sArr) {
                                        arrayList.add(Short.valueOf(valueOf7));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else if (value instanceof char[]) {
                                    char[] cArr = (char[]) value;
                                    arrayList = new ArrayList();
                                    for (char valueOf8 : cArr) {
                                        arrayList.add(Character.valueOf(valueOf8));
                                    }
                                    jSONObject.put(a, arrayList);
                                } else {
                                    jSONObject.put(a, new ArrayList(Arrays.asList((Object[]) value)));
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(a, bw.a(value.toString(), 256));
                            } else {
                                jSONObject.put(a, value);
                            }
                        }
                    }
                } catch (Throwable e) {
                    by.e(e);
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
        by.e("Event id is empty or too long in tracking Event");
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
            by.e("Event label or value is empty or too long in tracking Event");
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
                            return false;
                        }
                        if (entry.getValue() == null) {
                            return false;
                        }
                        if ((entry.getValue() instanceof String) && !b(entry.getValue().toString())) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
            }
        }
        by.e("map is null or empty in onEvent");
        return false;
    }
}
