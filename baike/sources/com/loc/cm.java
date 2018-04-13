package com.loc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.Hashtable;
import java.util.Map.Entry;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.utils.PrefrenceKeys;

public final class cm {
    Hashtable<String, JSONObject> a = new Hashtable();
    Hashtable<String, JSONObject> b = new Hashtable();
    boolean c = false;
    private Context d = null;
    private String e = "2.0.201501131131".replace(".", "");

    public cm(Context context) {
        if (context != null) {
            this.d = context.getApplicationContext();
        }
    }

    private void a(Context context, String str, String str2, int i, long j, boolean z) {
        Throwable th;
        if (bw.a && context != null && !TextUtils.isEmpty(str)) {
            int optInt;
            JSONObject jSONObject = (JSONObject) this.a.get(str);
            JSONObject jSONObject2 = jSONObject == null ? new JSONObject() : jSONObject;
            try {
                jSONObject2.put("x", str2);
                jSONObject2.put("time", j);
                optInt = jSONObject2.optInt("num", 0) + i;
                try {
                    jSONObject2.put("num", optInt);
                } catch (Throwable th2) {
                    th = th2;
                    cw.a(th, "HeatMap", "update1");
                    this.a.put(str, jSONObject2);
                    cw.j = true;
                    dd.a(context, "pref", "ddex", true);
                    if (!z) {
                        try {
                            this.b.put(str, jSONObject2);
                        } catch (Throwable th3) {
                            cw.a(th3, "HeatMap", PrefrenceKeys.UPDATE);
                            return;
                        }
                    }
                }
            } catch (Throwable th32) {
                th = th32;
                optInt = i;
                cw.a(th, "HeatMap", "update1");
                this.a.put(str, jSONObject2);
                cw.j = true;
                dd.a(context, "pref", "ddex", true);
                if (!z) {
                    this.b.put(str, jSONObject2);
                }
            }
            this.a.put(str, jSONObject2);
            if (!(optInt < 120 || cw.j || dd.b(context, "pref", "ddex", false))) {
                cw.j = true;
                dd.a(context, "pref", "ddex", true);
            }
            if (!z) {
                this.b.put(str, jSONObject2);
            }
        }
    }

    public final void a() {
        SQLiteDatabase openOrCreateDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        if (!this.a.isEmpty()) {
            this.a.clear();
        }
        if (this.d != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                openOrCreateDatabase = this.d.openOrCreateDatabase("hmdb", 0, null);
                try {
                    stringBuilder.append("CREATE TABLE IF NOT EXISTS hm");
                    stringBuilder.append(this.e);
                    stringBuilder.append(" (hash VARCHAR PRIMARY KEY, num INTEGER, extra VARCHAR, time VARCHAR);");
                    openOrCreateDatabase.execSQL(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                    openOrCreateDatabase.beginTransaction();
                    for (Entry entry : this.b.entrySet()) {
                        String str = (String) entry.getKey();
                        String optString = ((JSONObject) entry.getValue()).optString("x");
                        if (!TextUtils.isEmpty(optString)) {
                            str = de.c(str);
                            optString = de.c(optString);
                            stringBuilder.append("REPLACE INTO ");
                            stringBuilder.append("hm").append(this.e);
                            stringBuilder.append(" VALUES (?, ?, ?, ?);");
                            openOrCreateDatabase.execSQL(stringBuilder.toString(), new Object[]{str, Integer.valueOf(r0.optInt("num", 1)), optString, Long.valueOf(r0.optLong("time", de.a()))});
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                    }
                    if (openOrCreateDatabase != null) {
                        openOrCreateDatabase.setTransactionSuccessful();
                        openOrCreateDatabase.endTransaction();
                        if (openOrCreateDatabase.isOpen()) {
                            openOrCreateDatabase.close();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (openOrCreateDatabase != null) {
                        openOrCreateDatabase.setTransactionSuccessful();
                        openOrCreateDatabase.endTransaction();
                        if (openOrCreateDatabase.isOpen()) {
                            openOrCreateDatabase.close();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                openOrCreateDatabase = null;
                th = th3;
                if (openOrCreateDatabase != null) {
                    openOrCreateDatabase.setTransactionSuccessful();
                    openOrCreateDatabase.endTransaction();
                    if (openOrCreateDatabase.isOpen()) {
                        openOrCreateDatabase.close();
                    }
                }
                throw th;
            }
        }
        if (this.b.isEmpty()) {
            this.b.clear();
        }
        this.c = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.content.Context r13) {
        /*
        r12 = this;
        r11 = 1;
        r1 = 0;
        r0 = com.loc.bw.a;
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = r12.c;
        if (r0 != 0) goto L_0x0006;
    L_0x000b:
        r0 = com.loc.bw.a;	 Catch:{ Throwable -> 0x004a }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        if (r13 != 0) goto L_0x0014;
    L_0x0011:
        r12.c = r11;
        goto L_0x0006;
    L_0x0014:
        r0 = "hmdb";
        r2 = 0;
        r3 = 0;
        r10 = r13.openOrCreateDatabase(r0, r2, r3);	 Catch:{ Throwable -> 0x0119, all -> 0x00fe }
        r0 = "hm";
        r0 = com.loc.de.a(r10, r0);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        if (r0 != 0) goto L_0x0053;
    L_0x0024:
        if (r10 == 0) goto L_0x0011;
    L_0x0026:
        r0 = r10.isOpen();	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        if (r0 == 0) goto L_0x0011;
    L_0x002c:
        r10.close();	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        goto L_0x0011;
    L_0x0030:
        r0 = move-exception;
        r2 = r10;
    L_0x0032:
        r3 = "DB";
        r4 = "fetchHm p2";
        com.loc.cw.a(r0, r3, r4);	 Catch:{ all -> 0x0116 }
        if (r1 == 0) goto L_0x003e;
    L_0x003b:
        r1.close();	 Catch:{ Throwable -> 0x004a }
    L_0x003e:
        if (r2 == 0) goto L_0x0011;
    L_0x0040:
        r0 = r2.isOpen();	 Catch:{ Throwable -> 0x004a }
        if (r0 == 0) goto L_0x0011;
    L_0x0046:
        r2.close();	 Catch:{ Throwable -> 0x004a }
        goto L_0x0011;
    L_0x004a:
        r0 = move-exception;
        r1 = "HeatMap";
        r2 = "loadDB";
        com.loc.cw.a(r0, r1, r2);
        goto L_0x0011;
    L_0x0053:
        r2 = com.loc.de.a();	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r4 = 1209600000; // 0x48190800 float:156704.0 double:5.97621805E-315;
        r2 = r2 - r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r4.<init>();	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0 = "SELECT hash, num, extra, time FROM ";
        r4.append(r0);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0 = "hm";
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r5 = r12.e;	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0.append(r5);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0 = " WHERE time > ";
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0.append(r2);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0 = " ORDER BY num DESC LIMIT 0,";
        r4.append(r0);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r2 = ";";
        r0.append(r2);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0 = r4.toString();	 Catch:{ Throwable -> 0x00e5, all -> 0x0111 }
        r2 = 0;
        r1 = r10.rawQuery(r0, r2);	 Catch:{ Throwable -> 0x00e5, all -> 0x0111 }
        r9 = r1;
    L_0x0093:
        r0 = 0;
        r1 = r4.length();	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        r4.delete(r0, r1);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        if (r9 == 0) goto L_0x00d3;
    L_0x009d:
        r0 = r9.moveToFirst();	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        if (r0 == 0) goto L_0x00d3;
    L_0x00a3:
        r0 = 0;
        r3 = r9.getString(r0);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        r0 = 1;
        r5 = r9.getInt(r0);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        r0 = 2;
        r4 = r9.getString(r0);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        r0 = 3;
        r6 = r9.getLong(r0);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        r0 = "{";
        r0 = r4.startsWith(r0);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        if (r0 != 0) goto L_0x00c7;
    L_0x00bf:
        r3 = com.loc.de.d(r3);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        r4 = com.loc.de.d(r4);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
    L_0x00c7:
        r8 = 0;
        r1 = r12;
        r2 = r13;
        r1.a(r2, r3, r4, r5, r6, r8);	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        r0 = r9.moveToNext();	 Catch:{ Throwable -> 0x011d, all -> 0x0113 }
        if (r0 != 0) goto L_0x00a3;
    L_0x00d3:
        if (r9 == 0) goto L_0x00d8;
    L_0x00d5:
        r9.close();	 Catch:{ Throwable -> 0x004a }
    L_0x00d8:
        if (r10 == 0) goto L_0x0011;
    L_0x00da:
        r0 = r10.isOpen();	 Catch:{ Throwable -> 0x004a }
        if (r0 == 0) goto L_0x0011;
    L_0x00e0:
        r10.close();	 Catch:{ Throwable -> 0x004a }
        goto L_0x0011;
    L_0x00e5:
        r0 = move-exception;
        r2 = "DB";
        r3 = "fetchHm";
        com.loc.cw.a(r0, r2, r3);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r0 = r0.getMessage();	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
        if (r2 != 0) goto L_0x00fc;
    L_0x00f7:
        r2 = "no such table";
        r0.contains(r2);	 Catch:{ Throwable -> 0x0030, all -> 0x0111 }
    L_0x00fc:
        r9 = r1;
        goto L_0x0093;
    L_0x00fe:
        r0 = move-exception;
        r10 = r1;
    L_0x0100:
        if (r1 == 0) goto L_0x0105;
    L_0x0102:
        r1.close();	 Catch:{ Throwable -> 0x004a }
    L_0x0105:
        if (r10 == 0) goto L_0x0110;
    L_0x0107:
        r1 = r10.isOpen();	 Catch:{ Throwable -> 0x004a }
        if (r1 == 0) goto L_0x0110;
    L_0x010d:
        r10.close();	 Catch:{ Throwable -> 0x004a }
    L_0x0110:
        throw r0;	 Catch:{ Throwable -> 0x004a }
    L_0x0111:
        r0 = move-exception;
        goto L_0x0100;
    L_0x0113:
        r0 = move-exception;
        r1 = r9;
        goto L_0x0100;
    L_0x0116:
        r0 = move-exception;
        r10 = r2;
        goto L_0x0100;
    L_0x0119:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0032;
    L_0x011d:
        r0 = move-exception;
        r1 = r9;
        r2 = r10;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cm.a(android.content.Context):void");
    }

    public final void a(Context context, String str, AMapLocationServer aMapLocationServer) {
        String str2 = null;
        if (de.a(aMapLocationServer) && context != null && bw.a) {
            if (this.a.size() > 500) {
                str2 = bz.a(aMapLocationServer.getLatitude(), aMapLocationServer.getLongitude());
                if (!this.a.containsKey(str2)) {
                    return;
                }
            }
            if (str2 == null) {
                str2 = bz.a(aMapLocationServer.getLatitude(), aMapLocationServer.getLongitude());
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(PayPWDUniversalActivity.KEY, str);
                jSONObject.put("lat", aMapLocationServer.getLatitude());
                jSONObject.put("lon", aMapLocationServer.getLongitude());
                a(context, str2, jSONObject.toString(), 1, de.a(), true);
            } catch (Throwable th) {
                cw.a(th, "HeatMap", PrefrenceKeys.UPDATE);
            }
        }
    }
}
