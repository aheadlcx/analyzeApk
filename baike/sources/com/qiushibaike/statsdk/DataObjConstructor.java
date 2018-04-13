package com.qiushibaike.statsdk;

import android.content.Context;
import com.baidu.mobstat.Config;
import java.io.IOException;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataObjConstructor {
    public static final int MAX_FILE_SIZE = 204800;
    private static DataObjConstructor a;
    private DataObj b = new DataObj();

    static class a implements Serializable {
        String a;
        String b;
        int c;
        long d;
        long e;
        String f;
        String g;
        String h;
        String i;

        public String toString() {
            return "Event [eventId=" + this.a + ", label=" + this.b + ", count=" + this.c + ", start=" + this.d + ", duration=" + this.e + ", serial=" + this.f + ", extra1=" + this.g + ", extra2=" + this.h + ", extra3=" + this.i + "]";
        }

        private a() {
        }

        a(String str, String str2, int i, long j, long j2) {
            this(str, str2, i, j, j2, null, null, null);
        }

        a(String str, String str2, int i, long j, long j2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = j;
            this.e = j2;
            if (j2 == 0) {
                this.f = "0|";
            } else {
                this.f = "0";
            }
            this.g = str3;
            this.h = str4;
            this.i = str5;
        }

        boolean a() {
            return (a(this.g) && a(this.h) && a(this.i)) ? false : true;
        }

        boolean b() {
            return !a() && this.e == 0;
        }

        static boolean a(String str) {
            return str == null || "".equals(str);
        }

        static a a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            a aVar = new a();
            aVar.a = jSONObject.optString("i");
            aVar.b = jSONObject.optString("l");
            aVar.c = jSONObject.optInt("c");
            aVar.d = jSONObject.optLong("t");
            aVar.e = jSONObject.optLong("d");
            aVar.f = jSONObject.optString("s");
            aVar.g = jSONObject.optString("e1");
            aVar.h = jSONObject.optString(Config.SESSTION_TRACK_END_TIME);
            aVar.i = jSONObject.optString("e3");
            return aVar;
        }

        static JSONObject a(a aVar) {
            if (aVar == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("i", aVar.a);
                jSONObject.put("l", aVar.b);
                jSONObject.put("c", aVar.c);
                jSONObject.put("t", aVar.d);
                jSONObject.put("d", aVar.e);
                jSONObject.put("s", aVar.f);
                jSONObject.put("e1", aVar.g);
                jSONObject.put(Config.SESSTION_TRACK_END_TIME, aVar.h);
                jSONObject.put("e3", aVar.i);
                return jSONObject;
            } catch (Throwable e) {
                L.d(L.TAG, e);
                return jSONObject;
            }
        }
    }

    private DataObjConstructor() {
    }

    public static DataObjConstructor getInstance() {
        DataObjConstructor dataObjConstructor;
        synchronized (DataObjConstructor.class) {
            if (a == null) {
                a = new DataObjConstructor();
            }
            dataObjConstructor = a;
        }
        return dataObjConstructor;
    }

    public void setSessionStart(long j) {
        this.b.a = j;
    }

    public void setSessionEnd(long j) {
        this.b.b = j;
    }

    public void setAppChannel(String str) {
        this.b.f.setAppChannel(str);
    }

    public void setAppVersionName(String str) {
        this.b.f.setAppVersionName(str);
    }

    public void setExtra(String str) {
        this.b.f.setExtra(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putEvent(org.json.JSONObject r18, boolean r19) {
        /*
        r17 = this;
        if (r18 != 0) goto L_0x000a;
    L_0x0002:
        r2 = "QsbkStatSDK";
        r3 = "event data cannot be null ";
        com.qiushibaike.statsdk.L.d(r2, r3);
    L_0x0009:
        return;
    L_0x000a:
        if (r19 == 0) goto L_0x0043;
    L_0x000c:
        r2 = r18.toString();
        r2 = r2.getBytes();
        r2 = r2.length;
        r3 = "QsbkStatSDK";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "putEvent event data size : ";
        r4 = r4.append(r5);
        r4 = r4.append(r2);
        r4 = r4.toString();
        com.qiushibaike.statsdk.L.d(r3, r4);
        r0 = r17;
        r3 = r0.b;
        r3 = r3.getDataSize();
        r2 = r2 + r3;
        r3 = 204800; // 0x32000 float:2.86986E-40 double:1.011846E-318;
        if (r2 <= r3) goto L_0x0043;
    L_0x003b:
        r2 = "QsbkStatSDK";
        r3 = "putEvent: size is full!";
        com.qiushibaike.statsdk.L.e(r2, r3);
        goto L_0x0009;
    L_0x0043:
        r0 = r17;
        r5 = r0.b;
        monitor-enter(r5);
        r6 = com.qiushibaike.statsdk.DataObjConstructor.a.a(r18);	 Catch:{ all -> 0x0081 }
        r2 = "QsbkStatSDK";
        r3 = "old events: %s, new event: %s";
        r4 = 2;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0081 }
        r7 = 0;
        r0 = r17;
        r8 = r0.b;	 Catch:{ all -> 0x0081 }
        r8 = r8.e;	 Catch:{ all -> 0x0081 }
        r4[r7] = r8;	 Catch:{ all -> 0x0081 }
        r7 = 1;
        r4[r7] = r18;	 Catch:{ all -> 0x0081 }
        com.qiushibaike.statsdk.L.d(r2, r3, r4);	 Catch:{ all -> 0x0081 }
        r2 = r6.b();	 Catch:{ all -> 0x0081 }
        if (r2 == 0) goto L_0x0130;
    L_0x0068:
        r0 = r17;
        r2 = r0.b;	 Catch:{ all -> 0x0081 }
        r2 = r2.e;	 Catch:{ all -> 0x0081 }
        r7 = r2.length();	 Catch:{ all -> 0x0081 }
        if (r7 != 0) goto L_0x0084;
    L_0x0074:
        r0 = r17;
        r2 = r0.b;	 Catch:{ all -> 0x0081 }
        r2 = r2.e;	 Catch:{ all -> 0x0081 }
        r0 = r18;
        r2.put(r0);	 Catch:{ all -> 0x0081 }
        monitor-exit(r5);	 Catch:{ all -> 0x0081 }
        goto L_0x0009;
    L_0x0081:
        r2 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0081 }
        throw r2;
    L_0x0084:
        r3 = 0;
        r2 = 0;
        r4 = r2;
        r2 = r3;
    L_0x0088:
        if (r4 >= r7) goto L_0x015f;
    L_0x008a:
        r0 = r17;
        r3 = r0.b;	 Catch:{ JSONException -> 0x0125 }
        r3 = r3.e;	 Catch:{ JSONException -> 0x0125 }
        r8 = r3.getJSONObject(r4);	 Catch:{ JSONException -> 0x0125 }
        r9 = com.qiushibaike.statsdk.DataObjConstructor.a.a(r8);	 Catch:{ JSONException -> 0x0125 }
        r3 = r9.b();	 Catch:{ JSONException -> 0x0125 }
        if (r3 == 0) goto L_0x012b;
    L_0x009e:
        r3 = r9.a;	 Catch:{ JSONException -> 0x0125 }
        r10 = r6.a;	 Catch:{ JSONException -> 0x0125 }
        r3 = r3.equals(r10);	 Catch:{ JSONException -> 0x0125 }
        if (r3 == 0) goto L_0x012b;
    L_0x00a8:
        r3 = r9.b;	 Catch:{ JSONException -> 0x0125 }
        r10 = r6.b;	 Catch:{ JSONException -> 0x0125 }
        r3 = r3.equals(r10);	 Catch:{ JSONException -> 0x0125 }
        if (r3 == 0) goto L_0x012b;
    L_0x00b2:
        r3 = r6.c;	 Catch:{ JSONException -> 0x0125 }
        r10 = r9.c;	 Catch:{ JSONException -> 0x0125 }
        r10 = r10 + r3;
        r3 = "";
        r3 = r9.f;	 Catch:{ JSONException -> 0x0125 }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x0125 }
        if (r3 == 0) goto L_0x0122;
    L_0x00c1:
        r3 = "0|";
    L_0x00c3:
        r12 = r6.d;	 Catch:{ JSONException -> 0x0125 }
        r14 = r9.d;	 Catch:{ JSONException -> 0x0125 }
        r12 = r12 - r14;
        r11 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0125 }
        r11.<init>();	 Catch:{ JSONException -> 0x0125 }
        r3 = r11.append(r3);	 Catch:{ JSONException -> 0x0125 }
        r3 = r3.append(r12);	 Catch:{ JSONException -> 0x0125 }
        r11 = "|";
        r3 = r3.append(r11);	 Catch:{ JSONException -> 0x0125 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x0125 }
        r11 = "s";
        r8.put(r11, r3);	 Catch:{ JSONException -> 0x0125 }
        r3 = "c";
        r8.put(r3, r10);	 Catch:{ JSONException -> 0x0125 }
        r0 = r17;
        r3 = r0.b;	 Catch:{ JSONException -> 0x0125 }
        r3 = r3.e;	 Catch:{ JSONException -> 0x0125 }
        r3.put(r4, r8);	 Catch:{ JSONException -> 0x0125 }
        r3 = 1;
        r2 = "QsbkStatSDK";
        r10 = "combine old ev %s and new ev %s to %s";
        r11 = 3;
        r11 = new java.lang.Object[r11];	 Catch:{ JSONException -> 0x0158 }
        r12 = 0;
        r9 = r9.toString();	 Catch:{ JSONException -> 0x0158 }
        r11[r12] = r9;	 Catch:{ JSONException -> 0x0158 }
        r9 = 1;
        r12 = r18.toString();	 Catch:{ JSONException -> 0x0158 }
        r11[r9] = r12;	 Catch:{ JSONException -> 0x0158 }
        r9 = 2;
        r8 = r8.toString();	 Catch:{ JSONException -> 0x0158 }
        r11[r9] = r8;	 Catch:{ JSONException -> 0x0158 }
        com.qiushibaike.statsdk.L.d(r2, r10, r11);	 Catch:{ JSONException -> 0x0158 }
    L_0x0112:
        if (r3 != 0) goto L_0x011f;
    L_0x0114:
        r0 = r17;
        r2 = r0.b;	 Catch:{ all -> 0x0081 }
        r2 = r2.e;	 Catch:{ all -> 0x0081 }
        r0 = r18;
        r2.put(r0);	 Catch:{ all -> 0x0081 }
    L_0x011f:
        monitor-exit(r5);	 Catch:{ all -> 0x0081 }
        goto L_0x0009;
    L_0x0122:
        r3 = r9.f;	 Catch:{ JSONException -> 0x0125 }
        goto L_0x00c3;
    L_0x0125:
        r3 = move-exception;
    L_0x0126:
        r8 = "QsbkStatSDK";
        com.qiushibaike.statsdk.L.d(r8, r3);	 Catch:{ all -> 0x0081 }
    L_0x012b:
        r3 = r4 + 1;
        r4 = r3;
        goto L_0x0088;
    L_0x0130:
        r0 = r17;
        r2 = r0.b;	 Catch:{ all -> 0x0081 }
        r2 = r2.e;	 Catch:{ all -> 0x0081 }
        r0 = r18;
        r2.put(r0);	 Catch:{ all -> 0x0081 }
        r2 = "QsbkStatSDK";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0081 }
        r3.<init>();	 Catch:{ all -> 0x0081 }
        r4 = "add duration ev ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0081 }
        r4 = r18.toString();	 Catch:{ all -> 0x0081 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0081 }
        r3 = r3.toString();	 Catch:{ all -> 0x0081 }
        com.qiushibaike.statsdk.L.d(r2, r3);	 Catch:{ all -> 0x0081 }
        goto L_0x011f;
    L_0x0158:
        r2 = move-exception;
        r16 = r2;
        r2 = r3;
        r3 = r16;
        goto L_0x0126;
    L_0x015f:
        r3 = r2;
        goto L_0x0112;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qiushibaike.statsdk.DataObjConstructor.putEvent(org.json.JSONObject, boolean):void");
    }

    public void putEvent(String str, String str2, int i, long j, long j2) {
        putEvent(constructEventData(str, str2, i, j, j2), true);
    }

    public void putEvent(String str, String str2, int i, long j, long j2, String str3, String str4, String str5) {
        putEvent(constructEventData(str, str2, i, j, j2, str3, str4, str5), true);
    }

    public void loadStatData(Context context) {
        int i = 0;
        if (context != null) {
            String internalFile = Utils.getInternalFile(context, "__stat_cache.json");
            if (internalFile == null || "".equals(internalFile)) {
                L.d(L.TAG, "%s not found", "__stat_cache.json");
                return;
            }
            this.b.setDataSize(internalFile.getBytes().length);
            try {
                JSONObject jSONObject = new JSONObject(internalFile);
                long currentTimeMillis = System.currentTimeMillis();
                JSONArray jSONArray = jSONObject.getJSONArray(Config.EVENT_PART);
                int length = jSONArray.length();
                while (i < length) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (Math.abs(currentTimeMillis - jSONObject2.getLong("t")) <= 604800000) {
                        putEvent(jSONObject2, false);
                    }
                    i++;
                }
                setSessionStart(jSONObject.getLong("s"));
                setSessionEnd(jSONObject.getLong(Config.SESSTION_END_TIME));
            } catch (Throwable e) {
                L.d(L.TAG, e);
            }
        }
    }

    public static JSONObject constructEventData(String str, String str2, int i, long j, long j2) {
        return a.a(new a(str, str2, i, j, j2));
    }

    public static JSONObject constructEventData(String str, String str2, int i, long j, long j2, String str3, String str4, String str5) {
        return a.a(new a(str, str2, i, j, j2, str3, str4, str5));
    }

    public synchronized boolean sendReportData(Context context, String str) {
        boolean z;
        boolean z2;
        Throwable e;
        z = false;
        HeadObj headObj = this.b.f;
        if (str == null || str.length() != 0) {
            this.b.generateAndGetCUID(context);
            headObj.installHeader(context, this.b.d, str);
            this.b.f = headObj;
            JSONObject jSONObject = new JSONObject();
            synchronized (this.b) {
                if (this.b.e.length() == 0) {
                    z = true;
                } else {
                    try {
                        jSONObject.put("h", this.b.d);
                    } catch (Throwable e2) {
                        L.d(L.TAG, "put head fail", e2);
                    }
                    try {
                        JSONArray jSONArray = new JSONArray();
                        int length = this.b.e.length();
                        for (int i = 0; i < length; i++) {
                            Object jSONObject2 = this.b.e.getJSONObject(i);
                            a a = a.a((JSONObject) jSONObject2);
                            if (a.b()) {
                                String[] split = a.f.split("\\|");
                                JSONArray jSONArray2 = new JSONArray();
                                for (String str2 : split) {
                                    if (!"".equalsIgnoreCase(str2)) {
                                        try {
                                            jSONArray2.put(Long.valueOf(str2));
                                        } catch (NumberFormatException e3) {
                                        }
                                    }
                                }
                                jSONObject2 = a.a(a);
                                jSONObject2.put("s", jSONArray2);
                            }
                            jSONArray.put(i, jSONObject2);
                        }
                        jSONObject.put(Config.EVENT_PART, jSONArray);
                    } catch (Throwable e22) {
                        L.d(L.TAG, "put ev fail", e22);
                    }
                    try {
                        jSONObject.put("s", this.b.a);
                        jSONObject.put(Config.SESSTION_END_TIME, this.b.b);
                        jSONObject.put("c", this.b.c);
                    } catch (JSONException e4) {
                        L.d(L.TAG, "flush session fail" + e4);
                    }
                    String jSONObject3 = jSONObject.toString();
                    L.d(L.TAG, "post to server data: " + jSONObject3);
                    if (jSONObject3.length() > 10) {
                        try {
                            Utils.post(context, "https://qbb.qiushibaike.com/r?os=android&v=0.8&appid=" + str, jSONObject3, 60000, 60000);
                            z2 = true;
                            try {
                                L.d(L.TAG, "send data success ");
                                z = true;
                            } catch (IOException e5) {
                                e = e5;
                                L.d(L.TAG, "send fail. ", e);
                                z = z2;
                                if (z) {
                                    this.b.e = new JSONArray();
                                }
                                return z;
                            }
                        } catch (Throwable e222) {
                            Throwable th = e222;
                            z2 = false;
                            e = th;
                            L.d(L.TAG, "send fail. ", e);
                            z = z2;
                            if (z) {
                                this.b.e = new JSONArray();
                            }
                            return z;
                        }
                    }
                    if (z) {
                        this.b.e = new JSONArray();
                    }
                }
            }
        } else {
            L.e(L.TAG, "app key is missing");
        }
        return z;
    }

    public void flush(Context context) {
        String jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        synchronized (this.b) {
            try {
                jSONObject2.put(Config.EVENT_PART, this.b.e);
            } catch (JSONException e) {
                L.d(L.TAG, "flush ev fail " + e);
            }
            try {
                jSONObject2.put("s", this.b.a);
                jSONObject2.put(Config.SESSTION_END_TIME, this.b.b);
                jSONObject2.put("c", this.b.c);
            } catch (JSONException e2) {
                L.d(L.TAG, "flush session fail" + e2);
            }
            jSONObject = jSONObject2.toString();
        }
        if (jSONObject == null || jSONObject.length() < 2) {
            jSONObject = "{}";
        }
        this.b.setDataSize(jSONObject.getBytes().length);
        Utils.writeFileInternal(context, "__stat_cache.json", jSONObject, false);
    }
}
