package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.StatService.WearListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.im.TimeUtils;

public class DataCore {
    private static JSONObject a = new JSONObject();
    private static DataCore b = new DataCore();
    private JSONArray c = new JSONArray();
    private JSONArray d = new JSONArray();
    private JSONArray e = new JSONArray();
    private boolean f = false;
    private volatile int g = 0;
    private WearListener h;

    public static DataCore instance() {
        return b;
    }

    private DataCore() {
    }

    public int getCacheFileSzie() {
        return this.g;
    }

    public void putSession(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (a(jSONObject.toString())) {
                db.b("data to put exceed limit, will not put");
                return;
            }
            synchronized (this.c) {
                try {
                    this.c.put(this.c.length(), jSONObject);
                } catch (Throwable e) {
                    db.a(e);
                }
            }
        }
    }

    public void putSession(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(new JSONObject().toString())) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                putSession(jSONObject);
                db.a("Load last session:" + jSONObject);
            } catch (Throwable e) {
                db.a(e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, long r22, java.lang.String r24, java.lang.String r25, int r26, boolean r27) {
        /*
        r17 = this;
        r0 = r17;
        r6 = r0.d;
        monitor-enter(r6);
        r0 = r17;
        r2 = r0.d;	 Catch:{ all -> 0x0073 }
        r5 = r2.length();	 Catch:{ all -> 0x0073 }
        if (r21 == 0) goto L_0x0019;
    L_0x000f:
        r2 = "";
        r0 = r21;
        r2 = r0.equals(r2);	 Catch:{ JSONException -> 0x006c }
        if (r2 == 0) goto L_0x0022;
    L_0x0019:
        r2 = "s";
        r3 = "0|";
        r0 = r18;
        r0.put(r2, r3);	 Catch:{ JSONException -> 0x006c }
    L_0x0022:
        r4 = 0;
        r2 = r5;
    L_0x0024:
        if (r4 >= r5) goto L_0x0119;
    L_0x0026:
        r0 = r17;
        r3 = r0.d;	 Catch:{ JSONException -> 0x007d }
        r7 = r3.getJSONObject(r4);	 Catch:{ JSONException -> 0x007d }
        r3 = "i";
        r8 = r7.getString(r3);	 Catch:{ JSONException -> 0x007d }
        r3 = "l";
        r9 = r7.getString(r3);	 Catch:{ JSONException -> 0x007d }
        r3 = "t";
        r10 = r7.getLong(r3);	 Catch:{ JSONException -> 0x007d }
        r12 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r10 = r10 / r12;
        r3 = 0;
        r12 = "d";
        r3 = r7.getInt(r12);	 Catch:{ JSONException -> 0x0076 }
    L_0x004b:
        r12 = "h";
        r12 = r7.optString(r12);	 Catch:{ JSONException -> 0x007d }
        r13 = "p";
        r13 = r7.optString(r13);	 Catch:{ JSONException -> 0x007d }
        r14 = "v";
        r14 = r7.optInt(r14);	 Catch:{ JSONException -> 0x007d }
        r15 = "at";
        r15 = r7.optBoolean(r15);	 Catch:{ JSONException -> 0x007d }
        r10 = (r10 > r22 ? 1 : (r10 == r22 ? 0 : -1));
        if (r10 != 0) goto L_0x0069;
    L_0x0067:
        if (r3 == 0) goto L_0x0082;
    L_0x0069:
        r4 = r4 + 1;
        goto L_0x0024;
    L_0x006c:
        r2 = move-exception;
        r2 = "event put s fail";
        com.baidu.mobstat.db.a(r2);	 Catch:{ all -> 0x0073 }
        goto L_0x0022;
    L_0x0073:
        r2 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0073 }
        throw r2;
    L_0x0076:
        r12 = move-exception;
        r12 = "old version data, No duration Tag";
        com.baidu.mobstat.db.a(r12);	 Catch:{ JSONException -> 0x007d }
        goto L_0x004b;
    L_0x007d:
        r3 = move-exception;
    L_0x007e:
        com.baidu.mobstat.db.a(r3);	 Catch:{ all -> 0x0073 }
        goto L_0x0069;
    L_0x0082:
        r0 = r19;
        r3 = r8.equals(r0);	 Catch:{ JSONException -> 0x007d }
        if (r3 == 0) goto L_0x0069;
    L_0x008a:
        r0 = r20;
        r3 = r9.equals(r0);	 Catch:{ JSONException -> 0x007d }
        if (r3 == 0) goto L_0x0069;
    L_0x0092:
        r0 = r24;
        r3 = r12.equals(r0);	 Catch:{ JSONException -> 0x007d }
        if (r3 == 0) goto L_0x0069;
    L_0x009a:
        r0 = r25;
        r3 = r13.equals(r0);	 Catch:{ JSONException -> 0x007d }
        if (r3 == 0) goto L_0x0069;
    L_0x00a2:
        r0 = r26;
        if (r14 != r0) goto L_0x0069;
    L_0x00a6:
        r0 = r27;
        if (r15 != r0) goto L_0x0069;
    L_0x00aa:
        r3 = "c";
        r0 = r18;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x007d }
        r8 = "c";
        r8 = r7.getInt(r8);	 Catch:{ JSONException -> 0x007d }
        r8 = r8 + r3;
        r3 = "s";
        r3 = r7.optString(r3);	 Catch:{ JSONException -> 0x007d }
        if (r3 == 0) goto L_0x00c9;
    L_0x00c1:
        r9 = "";
        r9 = r3.equalsIgnoreCase(r9);	 Catch:{ JSONException -> 0x007d }
        if (r9 == 0) goto L_0x00cb;
    L_0x00c9:
        r3 = "0|";
    L_0x00cb:
        r9 = "t";
        r0 = r18;
        r10 = r0.getLong(r9);	 Catch:{ JSONException -> 0x007d }
        r9 = "t";
        r12 = r7.getLong(r9);	 Catch:{ JSONException -> 0x007d }
        r10 = r10 - r12;
        r9 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x007d }
        r9.<init>();	 Catch:{ JSONException -> 0x007d }
        r3 = r9.append(r3);	 Catch:{ JSONException -> 0x007d }
        r3 = r3.append(r10);	 Catch:{ JSONException -> 0x007d }
        r9 = "|";
        r3 = r3.append(r9);	 Catch:{ JSONException -> 0x007d }
        r2 = r3.toString();	 Catch:{ JSONException -> 0x007d }
        r3 = "c";
        r7.remove(r3);	 Catch:{ JSONException -> 0x0114 }
        r3 = "c";
        r7.put(r3, r8);	 Catch:{ JSONException -> 0x0114 }
        r3 = "s";
        r7.put(r3, r2);	 Catch:{ JSONException -> 0x0114 }
    L_0x0100:
        if (r4 >= r5) goto L_0x0104;
    L_0x0102:
        monitor-exit(r6);	 Catch:{ all -> 0x0073 }
    L_0x0103:
        return;
    L_0x0104:
        r0 = r17;
        r2 = r0.d;	 Catch:{ JSONException -> 0x010f }
        r0 = r18;
        r2.put(r5, r0);	 Catch:{ JSONException -> 0x010f }
    L_0x010d:
        monitor-exit(r6);	 Catch:{ all -> 0x0073 }
        goto L_0x0103;
    L_0x010f:
        r2 = move-exception;
        com.baidu.mobstat.db.a(r2);	 Catch:{ all -> 0x0073 }
        goto L_0x010d;
    L_0x0114:
        r2 = move-exception;
        r3 = r2;
        r2 = r4;
        goto L_0x007e;
    L_0x0119:
        r4 = r2;
        goto L_0x0100;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.DataCore.a(org.json.JSONObject, java.lang.String, java.lang.String, java.lang.String, long, java.lang.String, java.lang.String, int, boolean):void");
    }

    private boolean a(String str) {
        return (str.getBytes().length + ch.a().b()) + this.g > 204800;
    }

    public void putEvent(Context context, JSONObject jSONObject, boolean z) {
        if (jSONObject != null) {
            if (a(jSONObject.toString())) {
                db.b("data to put exceed limit, will not put");
                return;
            }
            int i = 0;
            String str = "";
            str = "";
            str = "";
            str = "";
            try {
                String string = jSONObject.getString("i");
                String string2 = jSONObject.getString("l");
                long j = jSONObject.getLong("t") / TimeUtils.HOUR;
                String optString = jSONObject.optString("s");
                String optString2 = jSONObject.optString("h");
                String optString3 = jSONObject.optString("p");
                int optInt = jSONObject.optInt("v");
                boolean optBoolean = jSONObject.optBoolean("at");
                CharSequence optString4 = jSONObject.optString("ext");
                CharSequence optString5 = jSONObject.optString(Config.EVENT_ATTR);
                try {
                    i = jSONObject.getInt("d");
                } catch (JSONException e) {
                    db.a("old version data, No duration Tag");
                }
                Object obj = null;
                if (!(TextUtils.isEmpty(optString4) || new JSONObject().toString().equals(optString4))) {
                    obj = 1;
                }
                Object obj2 = null;
                if (!TextUtils.isEmpty(optString5)) {
                    obj2 = 1;
                }
                if (i == 0 && r3 == null && r4 == null) {
                    a(jSONObject, string, string2, optString, j, optString2, optString3, optInt, optBoolean);
                    return;
                }
                synchronized (this.d) {
                    i = this.d.length();
                    try {
                        jSONObject.put("s", "0");
                        this.d.put(i, jSONObject);
                    } catch (Throwable e2) {
                        db.a(e2);
                    }
                }
            } catch (Throwable e22) {
                db.a(e22);
            }
        }
    }

    public void putEvent(Context context, String str, String str2, int i, long j, long j2, String str3, String str4, int i2, boolean z, ExtraInfo extraInfo, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("i", str);
            jSONObject.put("l", str2);
            jSONObject.put("c", i);
            jSONObject.put("t", j);
            jSONObject.put("d", j2);
            jSONObject.put("h", str3);
            jSONObject.put("p", str4);
            jSONObject.put("v", i2);
            jSONObject.put("at", z ? 1 : 0);
            if (!(extraInfo == null || extraInfo.dumpToJson().length() == 0)) {
                jSONObject.put("ext", extraInfo.dumpToJson());
            }
            if (map != null) {
                JSONArray jSONArray = new JSONArray();
                for (Entry entry : map.entrySet()) {
                    String str5 = (String) entry.getKey();
                    String str6 = (String) entry.getValue();
                    if (!(TextUtils.isEmpty(str5) || TextUtils.isEmpty(str6) || a(str6, 1024))) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put(Config.APP_KEY, str5);
                        jSONObject2.put("v", str6);
                        jSONArray.put(jSONObject2);
                    }
                }
                if (jSONArray.length() != 0) {
                    jSONObject.put(Config.EVENT_ATTR, jSONArray);
                }
            }
            putEvent(context, jSONObject, false);
            db.a("put event:" + jSONObject.toString());
        } catch (Throwable e) {
            db.a(e);
        }
    }

    private static boolean a(String str, int i) {
        if (str == null) {
            return false;
        }
        int length;
        try {
            length = str.getBytes().length;
        } catch (Exception e) {
            length = 0;
        }
        if (length > i) {
            return true;
        }
        return false;
    }

    public void installHeader(Context context) {
        synchronized (a) {
            CooperService.a().getHeadObject().a(context, a);
        }
    }

    public synchronized void flush(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            synchronized (this.c) {
                jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.c.toString()));
            }
            synchronized (this.d) {
                jSONObject.put(Config.EVENT_PART, new JSONArray(this.d.toString()));
            }
            synchronized (a) {
                jSONObject.put(Config.HEADER_PART, new JSONObject(a.toString()));
            }
        } catch (Exception e) {
            db.a("flushLogWithoutHeader() construct cache error");
        }
        String jSONObject2 = jSONObject.toString();
        if (a()) {
            db.a("cache.json exceed 204800B,stop flush.");
        } else {
            int length = jSONObject2.getBytes().length;
            if (length >= 204800) {
                a(true);
            } else {
                this.g = length;
                db.a("flush:cacheFileSize is:" + this.g + ", capacity is:" + 204800);
                cu.a(context, de.q(context) + Config.STAT_CACHE_FILE_NAME, jSONObject2, false);
                synchronized (this.e) {
                    jSONObject2 = this.e.toString();
                    db.a("flush wifi data: " + jSONObject2);
                    cu.a(context, Config.LAST_AP_INFO_FILE_NAME, jSONObject2, false);
                }
            }
        }
    }

    private void a(boolean z) {
        this.f = z;
    }

    private boolean a() {
        return this.f;
    }

    public void loadLastSession(Context context) {
        if (context != null) {
            String str = de.q(context) + Config.LAST_SESSION_FILE_NAME;
            if (cu.c(context, str)) {
                String a = cu.a(context, str);
                if (TextUtils.isEmpty(a)) {
                    db.a("loadLastSession(): last_session.json file not found.");
                    return;
                }
                cu.a(context, str, new JSONObject().toString(), false);
                putSession(a);
                flush(context);
            }
        }
    }

    public void loadWifiData(Context context) {
        if (context != null && cu.c(context, Config.LAST_AP_INFO_FILE_NAME)) {
            try {
                JSONArray jSONArray;
                JSONArray jSONArray2 = new JSONArray(cu.a(context, Config.LAST_AP_INFO_FILE_NAME));
                int length = jSONArray2.length();
                if (length >= 10) {
                    jSONArray = new JSONArray();
                    for (int i = length - 10; i < length; i++) {
                        jSONArray.put(jSONArray2.get(i));
                    }
                } else {
                    jSONArray = jSONArray2;
                }
                CharSequence g = de.g(1, context);
                if (!TextUtils.isEmpty(g)) {
                    jSONArray.put(g);
                }
                synchronized (this.e) {
                    this.e = jSONArray;
                    db.a("wifiPart: " + this.e.toString());
                }
            } catch (Throwable e) {
                db.b(e);
            }
        }
    }

    public void loadStatData(Context context) {
        int i = 0;
        if (context != null) {
            String str = de.q(context) + Config.STAT_CACHE_FILE_NAME;
            if (cu.c(context, str)) {
                str = cu.a(context, str);
                if (str.equals("")) {
                    db.a("stat_cache file not found.");
                    return;
                }
                db.a("loadStatData, ");
                try {
                    this.g = str.getBytes().length;
                    db.a("load Stat Data:cacheFileSize is:" + this.g);
                    JSONObject jSONObject = new JSONObject(str);
                    db.a("Load cache:" + str);
                    long currentTimeMillis = System.currentTimeMillis();
                    JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        if (currentTimeMillis - jSONObject2.getLong("s") <= 604800000) {
                            putSession(jSONObject2);
                        }
                    }
                    JSONArray jSONArray2 = jSONObject.getJSONArray(Config.EVENT_PART);
                    while (i < jSONArray2.length()) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
                        if (currentTimeMillis - jSONObject3.getLong("t") <= 604800000) {
                            putEvent(context, jSONObject3, true);
                        }
                        i++;
                    }
                    if (!isPartEmpty()) {
                        try {
                            JSONObject jSONObject4 = jSONObject.getJSONObject(Config.HEADER_PART);
                            synchronized (a) {
                                a = jSONObject4;
                            }
                        } catch (Throwable e) {
                            db.a(e);
                        }
                    }
                } catch (JSONException e2) {
                    db.a("Load stat data error:" + e2);
                }
            }
        }
    }

    public String constructLogWithEmptyBody(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        bu headObject = CooperService.a().getHeadObject();
        if (TextUtils.isEmpty(headObject.f)) {
            headObject.a(context, jSONObject2);
        } else {
            headObject.b(context, jSONObject2);
        }
        JSONArray jSONArray = new JSONArray();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject2.put("t", currentTimeMillis);
            jSONObject2.put(Config.SESSION_STARTTIME, currentTimeMillis);
            jSONObject2.put(Config.WIFI_LOCATION, jSONArray);
            jSONObject2.put(Config.SEQUENCE_INDEX, 0);
            jSONObject2.put(Config.SIGN, CooperService.a().getUUID());
            jSONObject2.put(Config.APP_KEY, str);
            jSONObject.put(Config.HEADER_PART, jSONObject2);
            try {
                jSONObject.put(Config.PRINCIPAL_PART, jSONArray);
                try {
                    jSONObject.put(Config.EVENT_PART, jSONArray);
                    try {
                        jSONObject.put(Config.EXCEPTION_PART, jSONArray);
                        return jSONObject.toString();
                    } catch (Throwable e) {
                        db.a(e);
                        return null;
                    }
                } catch (Throwable e2) {
                    db.a(e2);
                    return null;
                }
            } catch (Throwable e22) {
                db.a(e22);
                return null;
            }
        } catch (Throwable e222) {
            db.a(e222);
            return null;
        }
    }

    private void a(Context context, JSONObject jSONObject, boolean z) {
        Object obj = 1;
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(Config.TRACE_APPLICATION_SESSION, z ? 1 : 0);
            } catch (Exception e) {
            }
            try {
                jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            } catch (Exception e2) {
            }
            try {
                jSONObject.put(Config.TRACE_PART, jSONObject2);
            } catch (Exception e3) {
                obj = null;
            }
            if (obj != null) {
                a(context, jSONObject, jSONObject2);
            }
        }
    }

    private void a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        long j;
        int a = a(jSONObject);
        try {
            long j2;
            JSONObject jSONObject3 = jSONObject.getJSONObject(Config.HEADER_PART);
            if (jSONObject3 != null) {
                j2 = jSONObject3.getLong(Config.SESSION_STARTTIME);
            } else {
                j2 = 0;
            }
            j = j2;
        } catch (Exception e) {
            j = 0;
        }
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        a(context, jSONObject2, j, a);
    }

    private int a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return 0;
        }
        int i;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.HEADER_PART);
            i = (jSONObject2.getLong(Config.SESSION_STARTTIME) <= 0 || jSONObject2.getLong(Config.SEQUENCE_INDEX) != 0) ? 0 : 1;
        } catch (Exception e) {
            i = 0;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Config.PRINCIPAL_PART);
            if (jSONArray == null || jSONArray.length() == 0) {
                return i;
            }
            int i2 = 0;
            int i3 = i;
            while (i2 < jSONArray.length()) {
                try {
                    jSONObject2 = (JSONObject) jSONArray.get(i2);
                    long j = jSONObject2.getLong("c");
                    if (jSONObject2.getLong(Config.SESSTION_END_TIME) == 0 || j != 0) {
                        i = i3;
                    } else {
                        i = i3 + 1;
                    }
                    i2++;
                    i3 = i;
                } catch (Exception e2) {
                    return i3;
                }
            }
            return i3;
        } catch (Exception e3) {
            return i;
        }
    }

    private void a(Context context, JSONObject jSONObject, long j, int i) {
        int i2;
        int i3;
        long j2;
        long longValue = cq.a().b(context).longValue();
        if (longValue <= 0 && i != 0) {
            cq.a().a(context, j);
            longValue = j;
        }
        a(jSONObject, Config.TRACE_VISIT_FIRST, Long.valueOf(longValue));
        if (i != 0) {
            long longValue2 = cq.a().c(context).longValue();
            longValue = j - longValue2;
            if (longValue2 != 0 && longValue <= 0) {
                longValue = -1;
            } else if (longValue2 == 0) {
                longValue = 0;
            }
            cq.a().b(context, j);
            cq.a().c(context, longValue);
        } else {
            longValue = cq.a().d(context).longValue();
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_LAST_INTERVAL, Long.valueOf(longValue));
        CharSequence charSequence = "";
        Object obj = "";
        String e = cq.a().e(context);
        if (!TextUtils.isEmpty(e) && e.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            String[] split = e.split(Config.TRACE_TODAY_VISIT_SPLIT);
            if (split != null && split.length == 2) {
                charSequence = split[0];
                obj = split[1];
            }
        }
        if (TextUtils.isEmpty(obj)) {
            i2 = 0;
        } else {
            try {
                i2 = Integer.valueOf(obj).intValue();
            } catch (Exception e2) {
                i2 = 0;
            }
        }
        String a = dg.a(j);
        if (TextUtils.isEmpty(charSequence) || a.equals(charSequence)) {
            i3 = i + i2;
        } else {
            i3 = i;
        }
        if (i != 0) {
            cq.a().a(context, a + Config.TRACE_TODAY_VISIT_SPLIT + i3);
        }
        a(jSONObject, Config.TRACE_VISIT_SESSION_TODAY_COUNT, Integer.valueOf(i3));
        if (TextUtils.isEmpty(charSequence)) {
            j2 = 0;
        } else {
            try {
                j2 = (long) Integer.valueOf(charSequence).intValue();
            } catch (Exception e3) {
                j2 = 0;
            }
        }
        if (j2 == 0 || TextUtils.isEmpty(charSequence) || a.equals(charSequence) || i == 0) {
            Object f = cq.a().f(context);
            if (TextUtils.isEmpty(f)) {
                obj = null;
            } else {
                try {
                    obj = new JSONArray(f);
                } catch (Exception e4) {
                    obj = null;
                }
            }
            if (obj == null) {
                obj = new JSONArray();
            }
            a(jSONObject, Config.TRACE_VISIT_RECENT, obj);
            return;
        }
        obj = a(context, j2, (long) i2);
        cq.a().b(context, obj.toString());
        a(jSONObject, Config.TRACE_VISIT_RECENT, obj);
    }

    private JSONArray a(Context context, long j, long j2) {
        JSONObject jSONObject;
        Collection subList;
        List<JSONObject> arrayList = new ArrayList();
        Object f = cq.a().f(context);
        if (!TextUtils.isEmpty(f)) {
            try {
                JSONArray jSONArray = new JSONArray(f);
                if (!(jSONArray == null || jSONArray.length() == 0)) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add((JSONObject) jSONArray.get(i));
                    }
                }
            } catch (Exception e) {
            }
        }
        for (JSONObject jSONObject2 : arrayList) {
            try {
                if (jSONObject2.getLong("day") == j) {
                    f = null;
                    break;
                }
            } catch (Exception e2) {
            }
        }
        int i2 = 1;
        if (f != null) {
            try {
                jSONObject2 = new JSONObject();
                jSONObject2.put("day", j);
                jSONObject2.put("count", j2);
                arrayList.add(jSONObject2);
            } catch (Exception e3) {
            }
        }
        i2 = arrayList.size();
        if (i2 > 5) {
            subList = arrayList.subList(i2 - 5, i2);
        } else {
            f = arrayList;
        }
        return new JSONArray(subList);
    }

    private void a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject != null) {
            if (!jSONObject.has(Config.TRACE_VISIT)) {
                try {
                    jSONObject.put(Config.TRACE_VISIT, new JSONObject());
                } catch (Exception e) {
                }
            }
            try {
                ((JSONObject) jSONObject.get(Config.TRACE_VISIT)).put(str, obj);
            } catch (Exception e2) {
            }
        }
    }

    public void saveLogDataToSend(Context context, boolean z, boolean z2) {
        db.a("sendLogData() begin.");
        bu headObject = CooperService.a().getHeadObject();
        if (headObject != null) {
            synchronized (a) {
                if (TextUtils.isEmpty(headObject.f)) {
                    headObject.a(context, a);
                } else {
                    headObject.b(context, a);
                }
                db.a("constructHeader() begin." + a + a.length());
            }
            if (TextUtils.isEmpty(headObject.f)) {
                db.c("不能在manifest.xml中找到APP Key||can't find app key in manifest.xml.");
                return;
            }
        }
        JSONObject jSONObject = new JSONObject();
        synchronized (a) {
            try {
                a.put("t", System.currentTimeMillis());
                a.put(Config.SEQUENCE_INDEX, z ? 0 : 1);
                a.put(Config.SESSION_STARTTIME, ch.a().e());
                synchronized (this.e) {
                    a.put(Config.WIFI_LOCATION, this.e);
                }
                a.put(Config.SIGN, CooperService.a().getUUID());
                jSONObject.put(Config.HEADER_PART, a);
                synchronized (this.c) {
                    try {
                        jSONObject.put(Config.PRINCIPAL_PART, this.c);
                        synchronized (this.d) {
                            try {
                                jSONObject.put(Config.EVENT_PART, this.d);
                                try {
                                    jSONObject.put(Config.EXCEPTION_PART, new JSONArray());
                                    a(context, jSONObject, z2);
                                    String jSONObject2 = jSONObject.toString();
                                    db.a("---Send Data is:" + jSONObject2);
                                    a(context, jSONObject2);
                                    clearCache(context);
                                } catch (Throwable e) {
                                    db.a(e);
                                    return;
                                }
                            } catch (Throwable e2) {
                                db.a(e2);
                                return;
                            }
                        }
                    } catch (Throwable e22) {
                        db.a(e22);
                        return;
                    }
                }
            } catch (Throwable e222) {
                db.a(e222);
            }
        }
    }

    private void a(Context context, String str) {
        if (this.h == null || !this.h.onSendLogData(str)) {
            by.a().a(context, str);
        } else {
            db.a("log data has been passed to app level");
        }
    }

    public boolean isPartEmpty() {
        boolean z;
        synchronized (this.c) {
            z = this.c.length() == 0;
        }
        return z;
    }

    public void clearCache(Context context) {
        a(false);
        synchronized (a) {
            a = new JSONObject();
        }
        installHeader(context);
        a(context);
    }

    private void a(Context context) {
        synchronized (this.d) {
            this.d = new JSONArray();
        }
        synchronized (this.c) {
            this.c = new JSONArray();
        }
        synchronized (this.e) {
            this.e = new JSONArray();
        }
        flush(context);
    }
}
