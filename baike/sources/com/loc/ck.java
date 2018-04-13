package com.loc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobstat.Config;
import com.facebook.common.util.UriUtil;
import com.umeng.commonsdk.proguard.g;
import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

public final class ck {
    Hashtable<String, ArrayList<a>> a = new Hashtable();
    boolean b = true;
    long c = 0;
    String d = null;
    ce e = null;
    boolean f = true;
    boolean g = true;
    private long h = 0;
    private boolean i = false;
    private String j = "2.0.201501131131".replace(".", "");
    private String k = null;
    private String l = null;
    private long m = 0;

    static class a {
        private AMapLocationServer a = null;
        private String b = null;

        protected a() {
        }

        public final AMapLocationServer a() {
            return this.a;
        }

        public final void a(AMapLocationServer aMapLocationServer) {
            this.a = aMapLocationServer;
        }

        public final void a(String str) {
            if (TextUtils.isEmpty(str)) {
                this.b = null;
            } else {
                this.b = str.replace("##", MqttTopic.MULTI_LEVEL_WILDCARD);
            }
        }

        public final String b() {
            return this.b;
        }
    }

    private AMapLocationServer a(String str, StringBuilder stringBuilder) {
        a aVar;
        a a;
        if (str.contains("cgiwifi")) {
            a = a(stringBuilder, str);
            if (a != null) {
                aVar = a;
            }
            aVar = a;
        } else if (str.contains("wifi")) {
            a = a(stringBuilder, str);
            if (a != null) {
                aVar = a;
            }
            aVar = a;
        } else {
            aVar = (str.contains("cgi") && this.a.containsKey(str)) ? (a) ((ArrayList) this.a.get(str)).get(0) : null;
        }
        if (aVar != null && de.a(aVar.a())) {
            AMapLocationServer a2 = aVar.a();
            a2.e(Config.EXCEPTION_MEMORY);
            a2.g(aVar.b());
            if (cv.b(a2.getTime())) {
                if (de.a(a2)) {
                    this.c = 0;
                }
                a2.setLocationType(4);
                return a2;
            }
        }
        return null;
    }

    private a a(StringBuilder stringBuilder, String str) {
        if (this.a.isEmpty() || TextUtils.isEmpty(stringBuilder)) {
            return null;
        }
        if (!this.a.containsKey(str)) {
            return null;
        }
        a aVar;
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        Hashtable hashtable3 = new Hashtable();
        ArrayList arrayList = (ArrayList) this.a.get(str);
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            aVar = (a) arrayList.get(size);
            if (!TextUtils.isEmpty(aVar.b())) {
                boolean z;
                Object obj = null;
                String b = aVar.b();
                if (TextUtils.isEmpty(b) || TextUtils.isEmpty(stringBuilder)) {
                    z = false;
                } else {
                    if (b.contains(",access")) {
                        if (stringBuilder.indexOf(",access") != -1) {
                            String[] split = b.split(",access");
                            Object substring = split[0].contains(MqttTopic.MULTI_LEVEL_WILDCARD) ? split[0].substring(split[0].lastIndexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1) : split[0];
                            z = TextUtils.isEmpty(substring) ? false : stringBuilder.toString().contains(substring + ",access");
                        }
                    }
                    z = false;
                }
                if (z) {
                    if (de.a(aVar.b(), stringBuilder.toString())) {
                        break;
                    }
                    obj = 1;
                }
                a(aVar.b(), hashtable);
                a(stringBuilder.toString(), hashtable2);
                hashtable3.clear();
                for (String b2 : hashtable.keySet()) {
                    hashtable3.put(b2, "");
                }
                for (String b22 : hashtable2.keySet()) {
                    hashtable3.put(b22, "");
                }
                Set keySet = hashtable3.keySet();
                double[] dArr = new double[keySet.size()];
                double[] dArr2 = new double[keySet.size()];
                Iterator it = keySet.iterator();
                int i = 0;
                while (it != null && it.hasNext()) {
                    b22 = (String) it.next();
                    dArr[i] = hashtable.containsKey(b22) ? 1.0d : 0.0d;
                    dArr2[i] = hashtable2.containsKey(b22) ? 1.0d : 0.0d;
                    i++;
                }
                keySet.clear();
                double[] a = a(dArr, dArr2);
                if (a[0] < 0.800000011920929d) {
                    if (a[1] < 0.618d) {
                        if (obj != null && a[0] >= 0.618d) {
                            break;
                        }
                    }
                    break;
                }
                break;
            }
        }
        aVar = null;
        hashtable.clear();
        hashtable2.clear();
        hashtable3.clear();
        return aVar;
    }

    private String a(String str, StringBuilder stringBuilder, Context context) {
        if (context == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.k == null) {
                this.k = cj.a("MD5", k.c(context));
            }
            if (str.contains(com.alipay.sdk.sys.a.b)) {
                str = str.substring(0, str.indexOf(com.alipay.sdk.sys.a.b));
            }
            String substring = str.substring(str.lastIndexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1);
            if (substring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - 12));
            } else if (!(TextUtils.isEmpty(stringBuilder) || stringBuilder.indexOf(g.P) == -1)) {
                jSONObject.put("cgi", str.substring(0, str.length() - (substring.length() + 9)));
                String[] split = stringBuilder.toString().split(",access");
                jSONObject.put("mmac", split[0].contains(MqttTopic.MULTI_LEVEL_WILDCARD) ? split[0].substring(split[0].lastIndexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1) : split[0]);
            }
            try {
                return o.a(cj.c(jSONObject.toString().getBytes("UTF-8"), this.k));
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r10, java.lang.String r11) throws java.lang.Exception {
        /*
        r9 = this;
        r1 = 0;
        r0 = com.loc.cv.u();
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        if (r10 == 0) goto L_0x0007;
    L_0x000a:
        r0 = "hmdb";
        r2 = 0;
        r3 = 0;
        r7 = r10.openOrCreateDatabase(r0, r2, r3);	 Catch:{ Throwable -> 0x02ea, all -> 0x02dc }
        r0 = "hist";
        r0 = com.loc.de.a(r7, r0);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        if (r0 != 0) goto L_0x0040;
    L_0x001a:
        if (r7 == 0) goto L_0x0007;
    L_0x001c:
        r0 = r7.isOpen();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        if (r0 == 0) goto L_0x0007;
    L_0x0022:
        r7.close();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        goto L_0x0007;
    L_0x0026:
        r0 = move-exception;
        r2 = r7;
    L_0x0028:
        r3 = "DB";
        r4 = "fetchHist p2";
        com.loc.cw.a(r0, r3, r4);	 Catch:{ all -> 0x02e5 }
        if (r1 == 0) goto L_0x0034;
    L_0x0031:
        r1.close();
    L_0x0034:
        if (r2 == 0) goto L_0x0007;
    L_0x0036:
        r0 = r2.isOpen();
        if (r0 == 0) goto L_0x0007;
    L_0x003c:
        r2.close();
        goto L_0x0007;
    L_0x0040:
        r8 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r8.<init>();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r0 = "SELECT feature, nb, loc FROM ";
        r8.append(r0);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r0 = "hist";
        r0 = r8.append(r0);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2 = r9.j;	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r0.append(r2);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2 = com.loc.de.a();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r4 = com.loc.cv.t();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2 = r2 - r4;
        r0 = " WHERE time > ";
        r0 = r8.append(r0);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r0.append(r2);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        if (r11 == 0) goto L_0x0085;
    L_0x0069:
        r0 = " and feature = '";
        r0 = r8.append(r0);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2.<init>();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2 = r2.append(r11);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r3 = "'";
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r0.append(r2);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
    L_0x0085:
        r0 = " ORDER BY time ASC;";
        r8.append(r0);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r0 = r8.toString();	 Catch:{ Throwable -> 0x0191, all -> 0x02e1 }
        r2 = 0;
        r1 = r7.rawQuery(r0, r2);	 Catch:{ Throwable -> 0x0191, all -> 0x02e1 }
        r6 = r1;
    L_0x0094:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r2.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r9.k;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 != 0) goto L_0x00a9;
    L_0x009d:
        r0 = "MD5";
        r1 = com.loc.k.c(r10);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = com.loc.cj.a(r0, r1);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r9.k = r0;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
    L_0x00a9:
        if (r6 == 0) goto L_0x017f;
    L_0x00ab:
        r0 = r6.moveToFirst();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 == 0) goto L_0x017f;
    L_0x00b1:
        r0 = 0;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = "{";
        r0 = r0.startsWith(r1);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 == 0) goto L_0x01ce;
    L_0x00be:
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = 0;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1.<init>(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = 0;
        r3 = r2.length();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r2.delete(r0, r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 != 0) goto L_0x01ab;
    L_0x00db:
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r2.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
    L_0x00e3:
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = 2;
        r3 = r6.getString(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = "type";
        r3 = com.loc.de.a(r0, r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r3 == 0) goto L_0x00fc;
    L_0x00f5:
        r3 = "type";
        r4 = "new";
        r0.put(r3, r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
    L_0x00fc:
        r3 = new com.autonavi.aps.amapapi.model.AMapLocationServer;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3.b(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = "mmac";
        r0 = com.loc.de.a(r1, r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 == 0) goto L_0x0285;
    L_0x010e:
        r0 = "cgi";
        r0 = com.loc.de.a(r1, r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 == 0) goto L_0x0285;
    L_0x0116:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "cgi";
        r4 = r1.getString(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "network#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "cgi";
        r1 = r1.getString(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "#";
        r1 = r1.contains(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r1 == 0) goto L_0x0270;
    L_0x0150:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = "cgiwifi";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = r0.toString();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
    L_0x0163:
        r5 = 0;
        r0 = r9;
        r4 = r10;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
    L_0x0169:
        r0 = r6.moveToNext();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 != 0) goto L_0x00b1;
    L_0x016f:
        r0 = 0;
        r1 = r2.length();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r2.delete(r0, r1);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = 0;
        r1 = r8.length();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r8.delete(r0, r1);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
    L_0x017f:
        if (r6 == 0) goto L_0x0184;
    L_0x0181:
        r6.close();
    L_0x0184:
        if (r7 == 0) goto L_0x0007;
    L_0x0186:
        r0 = r7.isOpen();
        if (r0 == 0) goto L_0x0007;
    L_0x018c:
        r7.close();
        goto L_0x0007;
    L_0x0191:
        r0 = move-exception;
        r2 = "DB";
        r3 = "fetchHist";
        com.loc.cw.a(r0, r2, r3);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r0 = r0.getMessage();	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
        if (r2 != 0) goto L_0x01a8;
    L_0x01a3:
        r2 = "no such table";
        r0.contains(r2);	 Catch:{ Throwable -> 0x0026, all -> 0x02e1 }
    L_0x01a8:
        r6 = r1;
        goto L_0x0094;
    L_0x01ab:
        r0 = "mmac";
        r0 = com.loc.de.a(r1, r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 == 0) goto L_0x00e3;
    L_0x01b3:
        r0 = "#";
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = "mmac";
        r3 = r1.getString(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0.append(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = ",access";
        r2.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        goto L_0x00e3;
    L_0x01c9:
        r0 = move-exception;
        r1 = r6;
        r2 = r7;
        goto L_0x0028;
    L_0x01ce:
        r0 = 0;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = com.loc.o.b(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = new java.lang.String;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = r9.k;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = com.loc.cj.d(r0, r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "UTF-8";
        r3.<init>(r0, r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1.<init>(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = 0;
        r3 = r2.length();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r2.delete(r0, r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 != 0) goto L_0x0253;
    L_0x01fc:
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = com.loc.o.b(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = new java.lang.String;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = r9.k;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = com.loc.cj.d(r0, r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "UTF-8";
        r3.<init>(r0, r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r2.append(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
    L_0x0215:
        r0 = 2;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = com.loc.o.b(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = new java.lang.String;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r5 = r9.k;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = com.loc.cj.d(r3, r5);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r5 = "UTF-8";
        r4.<init>(r3, r5);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0.<init>(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = "type";
        r3 = com.loc.de.a(r0, r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r3 == 0) goto L_0x00fc;
    L_0x0238:
        r3 = "type";
        r4 = "new";
        r0.put(r3, r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        goto L_0x00fc;
    L_0x0241:
        r0 = move-exception;
    L_0x0242:
        if (r6 == 0) goto L_0x0247;
    L_0x0244:
        r6.close();
    L_0x0247:
        if (r7 == 0) goto L_0x0252;
    L_0x0249:
        r1 = r7.isOpen();
        if (r1 == 0) goto L_0x0252;
    L_0x024f:
        r7.close();
    L_0x0252:
        throw r0;
    L_0x0253:
        r0 = "mmac";
        r0 = com.loc.de.a(r1, r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 == 0) goto L_0x0215;
    L_0x025b:
        r0 = "#";
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r3 = "mmac";
        r3 = r1.getString(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0.append(r3);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = ",access";
        r2.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        goto L_0x0215;
    L_0x0270:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = "wifi";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = r0.toString();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        goto L_0x0163;
    L_0x0285:
        r0 = "cgi";
        r0 = com.loc.de.a(r1, r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r0 == 0) goto L_0x0169;
    L_0x028d:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "cgi";
        r4 = r1.getString(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "network#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "cgi";
        r1 = r1.getString(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r4 = "#";
        r1 = r1.contains(r4);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        if (r1 == 0) goto L_0x0169;
    L_0x02c7:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1.<init>();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = "cgi";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        r1 = r0.toString();	 Catch:{ Throwable -> 0x01c9, all -> 0x0241 }
        goto L_0x0163;
    L_0x02dc:
        r0 = move-exception;
        r6 = r1;
        r7 = r1;
        goto L_0x0242;
    L_0x02e1:
        r0 = move-exception;
        r6 = r1;
        goto L_0x0242;
    L_0x02e5:
        r0 = move-exception;
        r6 = r1;
        r7 = r2;
        goto L_0x0242;
    L_0x02ea:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ck.a(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r10, com.amap.api.location.AMapLocation r11, java.lang.StringBuilder r12, android.content.Context r13) throws java.lang.Exception {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0005 in list [B:14:0x00bf]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r9 = this;
        r1 = 0;
        r0 = 1;
        r8 = 0;
        if (r13 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r2 = r9.k;
        if (r2 != 0) goto L_0x0016;
    L_0x000a:
        r2 = "MD5";
        r3 = com.loc.k.c(r13);
        r2 = com.loc.cj.a(r2, r3);
        r9.k = r2;
    L_0x0016:
        r2 = r9.a(r10, r12, r13);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "hmdb";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r1 = r13.openOrCreateDatabase(r4, r5, r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "CREATE TABLE IF NOT EXISTS hist";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = r9.j;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = " (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = r3.toString();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r1.execSQL(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r3.length();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.delete(r4, r5);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "REPLACE INTO ";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "hist";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r9.j;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4.append(r5);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = " VALUES (?, ?, ?, ?)";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = 4;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r5] = r2;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = 1;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r12.toString();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = "UTF-8";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r5.getBytes(r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = r9.k;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = com.loc.cj.c(r5, r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r5;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = 2;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r11.toStr();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = "UTF-8";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = r5.getBytes(r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = r9.k;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = com.loc.cj.c(r5, r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r5;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = 3;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r6 = r11.getTime();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r5;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
    L_0x0092:
        r0 = r4.length;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r0 + -1;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        if (r2 >= r0) goto L_0x00a7;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
    L_0x0097:
        r0 = r4[r2];	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = (byte[]) r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = (byte[]) r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = com.loc.o.a(r0);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4[r2] = r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r2 + 1;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = r0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        goto L_0x0092;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
    L_0x00a7:
        r0 = r3.toString();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r1.execSQL(r0, r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = 0;	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r2 = r3.length();	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r3.delete(r0, r2);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r3.length();
        r3.delete(r8, r0);
        if (r1 == 0) goto L_0x0005;
    L_0x00bf:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x0005;
    L_0x00c5:
        r1.close();
        goto L_0x0005;
    L_0x00ca:
        r0 = move-exception;
        r2 = "DB";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r4 = "updateHist";	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        com.loc.cw.a(r0, r2, r4);	 Catch:{ Throwable -> 0x00ca, all -> 0x00e6 }
        r0 = r3.length();
        r3.delete(r8, r0);
        if (r1 == 0) goto L_0x0005;
    L_0x00db:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x0005;
    L_0x00e1:
        r1.close();
        goto L_0x0005;
    L_0x00e6:
        r0 = move-exception;
        r2 = r3.length();
        r3.delete(r8, r2);
        if (r1 == 0) goto L_0x00f9;
    L_0x00f0:
        r2 = r1.isOpen();
        if (r2 == 0) goto L_0x00f9;
    L_0x00f6:
        r1.close();
    L_0x00f9:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ck.a(java.lang.String, com.amap.api.location.AMapLocation, java.lang.StringBuilder, android.content.Context):void");
    }

    private static void a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            hashtable.clear();
            for (Object obj : str.split(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                if (!(TextUtils.isEmpty(obj) || obj.contains("|"))) {
                    hashtable.put(obj, "");
                }
            }
        }
    }

    private static double[] a(double[] dArr, double[] dArr2) {
        int i;
        double[] dArr3 = new double[3];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < dArr.length; i++) {
            d2 += dArr[i] * dArr[i];
            d3 += dArr2[i] * dArr2[i];
            d += dArr[i] * dArr2[i];
            if (dArr2[i] == 1.0d) {
                i2++;
                if (dArr[i] == 1.0d) {
                    i3++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d3) * Math.sqrt(d2));
        dArr3[1] = (1.0d * ((double) i3)) / ((double) i2);
        dArr3[2] = (double) i3;
        for (i = 0; i < dArr3.length - 1; i++) {
            if (dArr3[i] > 1.0d) {
                dArr3[i] = 1.0d;
            }
        }
        return dArr3;
    }

    private boolean b() {
        return this.h == 0 ? false : this.a.size() > 360 ? true : de.b() - this.h > 36000000;
    }

    private void c() {
        this.h = 0;
        if (!this.a.isEmpty()) {
            this.a.clear();
        }
        this.i = false;
    }

    public final AMapLocationServer a(Context context, String str, StringBuilder stringBuilder, boolean z) {
        if (TextUtils.isEmpty(str) || !cv.u()) {
            return null;
        }
        String str2 = str + com.alipay.sdk.sys.a.b + this.f + com.alipay.sdk.sys.a.b + this.g;
        if (str2.contains("gps") || !cv.u() || TextUtils.isEmpty(stringBuilder)) {
            return null;
        }
        if (b()) {
            c();
            return null;
        }
        if (z && !this.i) {
            try {
                String a = a(str2, stringBuilder, context);
                c();
                a(context, a);
            } catch (Throwable th) {
            }
        }
        return !this.a.isEmpty() ? a(str2, stringBuilder) : null;
    }

    public final AMapLocationServer a(cf cfVar, boolean z, AMapLocationServer aMapLocationServer, ci ciVar, StringBuilder stringBuilder, String str, Context context) {
        Object obj = (this.b && cv.u()) ? 1 : null;
        obj = obj == null ? null : (aMapLocationServer == null || cv.b(aMapLocationServer.getTime())) ? 1 : null;
        if (obj == null) {
            return null;
        }
        try {
            Object obj2;
            boolean z2;
            ce c = cfVar.c();
            Object obj3 = (!(c == null && this.e == null) && (this.e == null || !this.e.a(c))) ? 1 : null;
            if (aMapLocationServer != null) {
                obj = (aMapLocationServer.getAccuracy() <= 299.0f || ciVar.b().size() <= 5) ? null : 1;
                obj2 = obj;
            } else {
                obj2 = null;
            }
            if (aMapLocationServer == null || this.d == null || obj2 != null || obj3 != null) {
                z2 = false;
            } else {
                z2 = de.a(this.d, stringBuilder.toString());
                if ((z2 || (this.c != 0 && de.b() - this.c < 3000)) && de.a(aMapLocationServer)) {
                    aMapLocationServer.e(Config.EXCEPTION_MEMORY);
                    aMapLocationServer.setLocationType(2);
                    return aMapLocationServer;
                }
            }
            if (z2) {
                this.c = 0;
            } else {
                this.c = de.b();
            }
            if (this.l == null || str.equals(this.l)) {
                if (this.l == null) {
                    this.m = de.a();
                    this.l = str;
                } else {
                    this.m = de.a();
                }
            } else if (de.a() - this.m < 3000) {
                str = this.l;
            } else {
                this.m = de.a();
                this.l = str;
            }
            aMapLocationServer = null;
            if (obj2 == null && !z) {
                aMapLocationServer = a(context, str, stringBuilder, false);
            }
            if ((!z && !de.a(aMapLocationServer)) || obj2 != null) {
                return null;
            }
            if (z) {
                return null;
            }
            this.c = 0;
            aMapLocationServer.setLocationType(4);
            return aMapLocationServer;
        } catch (Throwable th) {
            return null;
        }
    }

    public final void a() {
        this.c = 0;
        this.d = null;
    }

    public final void a(Context context) {
        if (!this.i) {
            try {
                c();
                a(context, null);
            } catch (Throwable th) {
                cw.a(th, "Cache", "loadDB");
            }
            this.i = true;
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.g = aMapLocationClientOption.isNeedAddress();
        this.f = aMapLocationClientOption.isOffset();
        this.b = aMapLocationClientOption.isLocationCacheEnable();
    }

    public final void a(ce ceVar) {
        this.e = ceVar;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, StringBuilder stringBuilder, AMapLocationServer aMapLocationServer, Context context, boolean z) {
        int i = 0;
        if (de.a(aMapLocationServer)) {
            int i2;
            String str2 = str + com.alipay.sdk.sys.a.b + aMapLocationServer.isOffset() + com.alipay.sdk.sys.a.b + aMapLocationServer.i();
            if (TextUtils.isEmpty(str2) || !de.a(aMapLocationServer)) {
                i2 = 0;
            } else if (str2.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                i2 = 0;
            } else if (str2.contains("network")) {
                boolean z2 = true;
            } else {
                i2 = 0;
            }
            if (i2 != 0 && !aMapLocationServer.e().equals(Config.EXCEPTION_MEMORY) && !aMapLocationServer.e().equals(UriUtil.LOCAL_FILE_SCHEME) && !aMapLocationServer.d().equals("-3")) {
                if (b()) {
                    c();
                }
                JSONObject f = aMapLocationServer.f();
                if (de.a(f, "offpct")) {
                    f.remove("offpct");
                    aMapLocationServer.a(f);
                }
                if (str2.contains("wifi")) {
                    if (!TextUtils.isEmpty(stringBuilder)) {
                        if (aMapLocationServer.getAccuracy() >= 300.0f) {
                            String[] split = stringBuilder.toString().split(MqttTopic.MULTI_LEVEL_WILDCARD);
                            int length = split.length;
                            i2 = 0;
                            while (i < length) {
                                if (split[i].contains(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                                    i2++;
                                }
                                i++;
                            }
                            if (i2 >= 8) {
                                return;
                            }
                        } else if (aMapLocationServer.getAccuracy() <= 10.0f) {
                            return;
                        }
                        if (str2.contains("cgiwifi") && !TextUtils.isEmpty(aMapLocationServer.g())) {
                            String replace = str2.replace("cgiwifi", "cgi");
                            AMapLocationServer h = aMapLocationServer.h();
                            if (de.a(h)) {
                                a(replace, new StringBuilder(), h, context, true);
                            }
                        }
                    } else {
                        return;
                    }
                } else if (str2.contains("cgi")) {
                    if (stringBuilder.indexOf(Constants.ACCEPT_TIME_SEPARATOR_SP) != -1) {
                        return;
                    }
                    if (aMapLocationServer.d().equals("4")) {
                        return;
                    }
                }
                AMapLocationServer a = a(str2, stringBuilder);
                if (!de.a(a) || !a.toStr().equals(aMapLocationServer.toStr(3))) {
                    this.h = de.b();
                    a aVar = new a();
                    aVar.a(aMapLocationServer);
                    aVar.a(TextUtils.isEmpty(stringBuilder) ? null : stringBuilder.toString());
                    if (this.a.containsKey(str2)) {
                        ((ArrayList) this.a.get(str2)).add(aVar);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(aVar);
                        this.a.put(str2, arrayList);
                    }
                    if (z) {
                        try {
                            a(str2, (AMapLocation) aMapLocationServer, stringBuilder, context);
                        } catch (Throwable th) {
                            cw.a(th, "Cache", "add");
                        }
                    }
                }
            }
        }
    }

    public final void b(Context context) {
        SQLiteDatabase sQLiteDatabase = null;
        if (context != null) {
            try {
                sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                if (de.a(sQLiteDatabase, "hist")) {
                    sQLiteDatabase.delete("hist" + this.j, "time<?", new String[]{String.valueOf(de.a() - 86400000)});
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                    }
                } else if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th) {
                try {
                    cw.a(th, "DB", "clearHist p2");
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                    }
                } catch (Throwable th2) {
                    cw.a(th2, "Cache", "destroy part");
                    return;
                }
            }
        }
        this.i = false;
        this.d = null;
        this.m = 0;
    }
}
