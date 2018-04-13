package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.c.e;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g {
    public static final int a = 2049;
    public static final int b = 2050;
    private static Context c = null;
    private static String d = null;
    private List<String> e;
    private List<Integer> f;
    private String g;

    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION
    }

    private static class b {
        private static final g a = new g();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r4) {
        /*
        r3 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053, all -> 0x0063 }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053, all -> 0x0063 }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053, all -> 0x0063 }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053 }
        if (r4 != 0) goto L_0x0027;
    L_0x0010:
        r1 = "delete from __dp where __ty=0";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053 }
    L_0x0015:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053 }
        if (r0 == 0) goto L_0x001d;
    L_0x001a:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0076 }
    L_0x001d:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0026:
        return;
    L_0x0027:
        r1 = 4;
        if (r4 != r1) goto L_0x004a;
    L_0x002a:
        r1 = "delete from __dp where __ty=3";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053 }
        r1 = "delete from __dp where __ty=2";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053 }
        goto L_0x0015;
    L_0x0035:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x007e }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x007e }
        if (r0 == 0) goto L_0x0040;
    L_0x003d:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0078 }
    L_0x0040:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0026;
    L_0x004a:
        r1 = 1;
        if (r4 != r1) goto L_0x0015;
    L_0x004d:
        r1 = "delete from __dp where __ty=1";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0035, Throwable -> 0x0053 }
        goto L_0x0015;
    L_0x0053:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0059;
    L_0x0056:
        r0.endTransaction();	 Catch:{ Throwable -> 0x007a }
    L_0x0059:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0026;
    L_0x0063:
        r1 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r2;
    L_0x0067:
        if (r1 == 0) goto L_0x006c;
    L_0x0069:
        r1.endTransaction();	 Catch:{ Throwable -> 0x007c }
    L_0x006c:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x0076:
        r0 = move-exception;
        goto L_0x001d;
    L_0x0078:
        r0 = move-exception;
        goto L_0x0040;
    L_0x007a:
        r0 = move-exception;
        goto L_0x0059;
    L_0x007c:
        r1 = move-exception;
        goto L_0x006c;
    L_0x007e:
        r1 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r2;
        goto L_0x0067;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(int):void");
    }

    private g() {
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = null;
    }

    public static g a(Context context) {
        g a = b.a;
        if (c == null && context != null) {
            c = context.getApplicationContext();
            a.h();
        }
        return a;
    }

    private void h() {
        synchronized (this) {
            i();
            this.e.clear();
        }
    }

    public void a() {
        this.e.clear();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.json.JSONObject r10, int r11) {
        /*
        r9 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089, all -> 0x0099 }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089, all -> 0x0099 }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089, all -> 0x0099 }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r2 = new android.content.ContentValues;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r2.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r1 = "__ii";
        r1 = r10.optString(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r3 = "__id";
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r6 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4 = r4 - r6;
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r2.put(r3, r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r3 = "__ii";
        r4 = android.text.TextUtils.isEmpty(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        if (r4 == 0) goto L_0x0033;
    L_0x0031:
        r1 = "-1";
    L_0x0033:
        r2.put(r3, r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r1 = "__ii";
        r10.remove(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r1 = "__io";
        r3 = r10.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r3 = r9.a(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r2.put(r1, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r1 = "__ty";
        r3 = java.lang.Integer.valueOf(r11);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r2.put(r1, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r1 = "__ve";
        r3 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r3 = com.umeng.commonsdk.statistics.common.DeviceConfig.getAppVersionCode(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r2.put(r1, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r1 = "__dp";
        r3 = 0;
        r0.insert(r1, r3, r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0074, Throwable -> 0x0089 }
        if (r0 == 0) goto L_0x006a;
    L_0x0067:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00ac }
    L_0x006a:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0073:
        return;
    L_0x0074:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x00b4 }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x00b4 }
        if (r0 == 0) goto L_0x007f;
    L_0x007c:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00ae }
    L_0x007f:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0073;
    L_0x0089:
        r1 = move-exception;
        if (r0 == 0) goto L_0x008f;
    L_0x008c:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00b0 }
    L_0x008f:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0073;
    L_0x0099:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x009d:
        if (r1 == 0) goto L_0x00a2;
    L_0x009f:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00b2 }
    L_0x00a2:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x00ac:
        r0 = move-exception;
        goto L_0x006a;
    L_0x00ae:
        r0 = move-exception;
        goto L_0x007f;
    L_0x00b0:
        r0 = move-exception;
        goto L_0x008f;
    L_0x00b2:
        r1 = move-exception;
        goto L_0x00a2;
    L_0x00b4:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x009d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONObject, int):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.json.JSONArray r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x00a8, all -> 0x00b8 }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x00a8, all -> 0x00b8 }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x00a8, all -> 0x00b8 }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x00a8 }
        r1 = 0;
    L_0x000f:
        r2 = r8.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x00a8 }
        if (r1 >= r2) goto L_0x0081;
    L_0x0015:
        r3 = r8.getJSONObject(r1);	 Catch:{ Exception -> 0x00d8 }
        r4 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00d8 }
        r4.<init>();	 Catch:{ Exception -> 0x00d8 }
        r2 = "__i";
        r2 = r3.optString(r2);	 Catch:{ Exception -> 0x00d8 }
        r5 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00d8 }
        if (r5 != 0) goto L_0x0032;
    L_0x002a:
        r5 = "-1";
        r5 = r5.equals(r2);	 Catch:{ Exception -> 0x00d8 }
        if (r5 == 0) goto L_0x0042;
    L_0x0032:
        r2 = com.umeng.analytics.pro.o.a();	 Catch:{ Exception -> 0x00d8 }
        r2 = r2.c();	 Catch:{ Exception -> 0x00d8 }
        r5 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00d8 }
        if (r5 == 0) goto L_0x0042;
    L_0x0040:
        r2 = "-1";
    L_0x0042:
        r5 = "__i";
        r4.put(r5, r2);	 Catch:{ Exception -> 0x00d8 }
        r2 = "__e";
        r5 = "id";
        r5 = r3.optString(r5);	 Catch:{ Exception -> 0x00d8 }
        r4.put(r2, r5);	 Catch:{ Exception -> 0x00d8 }
        r2 = "__t";
        r5 = "__t";
        r5 = r3.optInt(r5);	 Catch:{ Exception -> 0x00d8 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x00d8 }
        r4.put(r2, r5);	 Catch:{ Exception -> 0x00d8 }
        r2 = "__i";
        r3.remove(r2);	 Catch:{ Exception -> 0x00d8 }
        r2 = "__t";
        r3.remove(r2);	 Catch:{ Exception -> 0x00d8 }
        r2 = "__s";
        r3 = r3.toString();	 Catch:{ Exception -> 0x00d8 }
        r3 = r7.a(r3);	 Catch:{ Exception -> 0x00d8 }
        r4.put(r2, r3);	 Catch:{ Exception -> 0x00d8 }
        r2 = "__et";
        r3 = 0;
        r0.insert(r2, r3, r4);	 Catch:{ Exception -> 0x00d8 }
    L_0x007e:
        r1 = r1 + 1;
        goto L_0x000f;
    L_0x0081:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0093, Throwable -> 0x00a8 }
        if (r0 == 0) goto L_0x0089;
    L_0x0086:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00cb }
    L_0x0089:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0092:
        return;
    L_0x0093:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x00d3 }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x009e;
    L_0x009b:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00cd }
    L_0x009e:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0092;
    L_0x00a8:
        r1 = move-exception;
        if (r0 == 0) goto L_0x00ae;
    L_0x00ab:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00cf }
    L_0x00ae:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0092;
    L_0x00b8:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x00bc:
        if (r1 == 0) goto L_0x00c1;
    L_0x00be:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00d1 }
    L_0x00c1:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x00cb:
        r0 = move-exception;
        goto L_0x0089;
    L_0x00cd:
        r0 = move-exception;
        goto L_0x009e;
    L_0x00cf:
        r0 = move-exception;
        goto L_0x00ae;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00c1;
    L_0x00d3:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x00bc;
    L_0x00d8:
        r2 = move-exception;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(org.json.JSONArray):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r6, java.lang.String r7, int r8) {
        /*
        r5 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e, all -> 0x006e }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e, all -> 0x006e }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e, all -> 0x006e }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r1 = new android.content.ContentValues;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r1.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = "__i";
        r1.put(r2, r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = r5.a(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        if (r3 != 0) goto L_0x0036;
    L_0x0022:
        r3 = "__a";
        r1.put(r3, r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = "__t";
        r3 = java.lang.Integer.valueOf(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r1.put(r2, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = "__er";
        r3 = 0;
        r0.insert(r2, r3, r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
    L_0x0036:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        if (r0 == 0) goto L_0x003e;
    L_0x003b:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0081 }
    L_0x003e:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0047:
        r0 = 0;
        return r0;
    L_0x0049:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x0089 }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x0089 }
        if (r0 == 0) goto L_0x0054;
    L_0x0051:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0083 }
    L_0x0054:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0047;
    L_0x005e:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0064;
    L_0x0061:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0085 }
    L_0x0064:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0047;
    L_0x006e:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0072:
        if (r1 == 0) goto L_0x0077;
    L_0x0074:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0087 }
    L_0x0077:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x0081:
        r0 = move-exception;
        goto L_0x003e;
    L_0x0083:
        r0 = move-exception;
        goto L_0x0054;
    L_0x0085:
        r0 = move-exception;
        goto L_0x0064;
    L_0x0087:
        r1 = move-exception;
        goto L_0x0077;
    L_0x0089:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(java.lang.String, java.lang.String, int):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        r7 = this;
        r1 = 0;
        r0 = 0;
        r2 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a, all -> 0x00aa }
        r2 = com.umeng.analytics.pro.f.a(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a, all -> 0x00aa }
        r0 = r2.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a, all -> 0x00aa }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r2 = com.umeng.analytics.pro.o.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r2 = r2.d();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        if (r3 == 0) goto L_0x002c;
    L_0x001d:
        if (r0 == 0) goto L_0x0022;
    L_0x001f:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00bd }
    L_0x0022:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x002b:
        return;
    L_0x002c:
        r3 = 2;
        r3 = new java.lang.String[r3];	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r4 = 0;
        r5 = "";
        r3[r4] = r5;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r4 = 1;
        r5 = "-1";
        r3[r4] = r5;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
    L_0x0039:
        r4 = r3.length;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        if (r1 >= r4) goto L_0x0073;
    L_0x003c:
        r4 = new java.lang.StringBuilder;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r4.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r5 = "update __et set __i=\"";
        r4 = r4.append(r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r4 = r4.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r5 = "\" where ";
        r4 = r4.append(r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r5 = "__i";
        r4 = r4.append(r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r5 = "=\"";
        r4 = r4.append(r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r5 = r3[r1];	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r4 = r4.append(r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r5 = "\"";
        r4 = r4.append(r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r4 = r4.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r0.execSQL(r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        r1 = r1 + 1;
        goto L_0x0039;
    L_0x0073:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0085, Throwable -> 0x009a }
        if (r0 == 0) goto L_0x007b;
    L_0x0078:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00c0 }
    L_0x007b:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x002b;
    L_0x0085:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x00c8 }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x00c8 }
        if (r0 == 0) goto L_0x0090;
    L_0x008d:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00c2 }
    L_0x0090:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x002b;
    L_0x009a:
        r1 = move-exception;
        if (r0 == 0) goto L_0x00a0;
    L_0x009d:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00c4 }
    L_0x00a0:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x002b;
    L_0x00aa:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x00ae:
        if (r1 == 0) goto L_0x00b3;
    L_0x00b0:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00c6 }
    L_0x00b3:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x00bd:
        r0 = move-exception;
        goto L_0x0022;
    L_0x00c0:
        r0 = move-exception;
        goto L_0x007b;
    L_0x00c2:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00c4:
        r0 = move-exception;
        goto L_0x00a0;
    L_0x00c6:
        r1 = move-exception;
        goto L_0x00b3;
    L_0x00c8:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x00ae;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b():void");
    }

    public boolean a(String str, JSONObject jSONObject, a aVar) {
        SQLiteDatabase a;
        Throwable th;
        Throwable th2;
        SQLiteDatabase sQLiteDatabase = null;
        if (jSONObject != null) {
            try {
                a = f.a(c).a();
                try {
                    a.beginTransaction();
                    if (aVar == a.BEGIN) {
                        long longValue = ((Long) jSONObject.get("__e")).longValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("__ii", str);
                        contentValues.put("__e", String.valueOf(longValue));
                        a.insert(e.a, null, contentValues);
                    } else if (aVar == a.END) {
                        a.execSQL("update __sd set __f=\"" + ((Long) jSONObject.get(com.umeng.analytics.pro.c.e.a.g)).longValue() + "\" where " + "__ii" + "=\"" + str + "\"");
                    } else if (aVar == a.PAGE) {
                        a(str, jSONObject, a, "__a");
                    } else if (aVar == a.AUTOPAGE) {
                        a(str, jSONObject, a, com.umeng.analytics.pro.c.e.a.c);
                    } else if (aVar == a.NEWSESSION) {
                        a(str, jSONObject, a);
                    }
                    a.setTransactionSuccessful();
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th3) {
                        }
                    }
                    f.a(c).b();
                } catch (SQLiteDatabaseCorruptException e) {
                    sQLiteDatabase = a;
                    try {
                        v.a(c);
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                            } catch (Throwable th4) {
                            }
                        }
                        f.a(c).b();
                        return false;
                    } catch (Throwable th5) {
                        th = th5;
                        a = sQLiteDatabase;
                        th2 = th;
                        if (a != null) {
                            try {
                                a.endTransaction();
                            } catch (Throwable th6) {
                            }
                        }
                        f.a(c).b();
                        throw th2;
                    }
                } catch (Throwable th7) {
                    th2 = th7;
                    if (a != null) {
                        a.endTransaction();
                    }
                    f.a(c).b();
                    throw th2;
                }
            } catch (SQLiteDatabaseCorruptException e2) {
                v.a(c);
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                f.a(c).b();
                return false;
            } catch (Throwable th52) {
                th = th52;
                a = null;
                th2 = th;
                if (a != null) {
                    a.endTransaction();
                }
                f.a(c).b();
                throw th2;
            }
        }
        return false;
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            Object obj;
            Object a;
            JSONObject jSONObject2 = jSONObject.getJSONObject(com.umeng.analytics.pro.c.e.a.e);
            if (jSONObject2 != null) {
                rawQuery = sQLiteDatabase.rawQuery("select __d from __sd where __ii=\"" + str + "\"", null);
                if (rawQuery != null) {
                    while (rawQuery.moveToNext()) {
                        try {
                            cursor = b(rawQuery.getString(rawQuery.getColumnIndex(com.umeng.analytics.pro.c.e.a.e)));
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            cursor = rawQuery;
                            th = th3;
                        }
                    }
                    obj = cursor;
                } else {
                    obj = null;
                }
            } else {
                obj = null;
                rawQuery = null;
            }
            if (jSONObject2 != null) {
                JSONArray jSONArray = new JSONArray();
                if (!TextUtils.isEmpty(obj)) {
                    jSONArray = new JSONArray(obj);
                }
                jSONArray.put(jSONObject2);
                a = a(jSONArray.toString());
                if (!TextUtils.isEmpty(a)) {
                    sQLiteDatabase.execSQL("update  __sd set __d=\"" + a + "\" where " + "__ii" + "=\"" + str + "\"");
                }
            }
            JSONObject jSONObject3 = jSONObject.getJSONObject(com.umeng.analytics.pro.c.e.a.d);
            if (jSONObject3 != null) {
                a = a(jSONObject3.toString());
                if (!TextUtils.isEmpty(a)) {
                    sQLiteDatabase.execSQL("update  __sd set __c=\"" + a + "\" where " + "__ii" + "=\"" + str + "\"");
                }
            }
            sQLiteDatabase.execSQL("update  __sd set __f=\"" + String.valueOf(jSONObject.getLong(com.umeng.analytics.pro.c.e.a.g)) + "\" where " + "__ii" + "=\"" + str + "\"");
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) throws JSONException {
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            Object obj;
            rawQuery = sQLiteDatabase.rawQuery("select " + str2 + " from " + e.a + " where " + "__ii" + "=\"" + str + "\"", null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    try {
                        cursor = b(rawQuery.getString(rawQuery.getColumnIndex(str2)));
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        cursor = rawQuery;
                        th = th3;
                    }
                }
                obj = cursor;
            } else {
                obj = null;
            }
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(obj)) {
                jSONArray = new JSONArray(obj);
            }
            jSONArray.put(jSONObject);
            Object a = a(jSONArray.toString());
            if (!TextUtils.isEmpty(a)) {
                sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + a + "\" where " + "__ii" + "=\"" + str + "\"");
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public JSONObject a(boolean z) {
        a();
        this.f.clear();
        JSONObject jSONObject = new JSONObject();
        if (z) {
            String a = a(jSONObject, z);
            if (!TextUtils.isEmpty(a)) {
                b(jSONObject, a);
                a(jSONObject, a);
            }
        } else {
            a(jSONObject, z);
            b(jSONObject, null);
            a(jSONObject, null);
        }
        return jSONObject;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject c() {
        /*
        r10 = this;
        r0 = 0;
        r2 = new org.json.JSONObject;
        r2.<init>();
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x010e, Throwable -> 0x010b, all -> 0x00ff }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x010e, Throwable -> 0x010b, all -> 0x00ff }
        r1 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x010e, Throwable -> 0x010b, all -> 0x00ff }
        r1.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5, all -> 0x0105 }
        r3 = "select *  from __dp";
        r4 = 0;
        r0 = r1.rawQuery(r3, r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5, all -> 0x0105 }
        if (r0 == 0) goto L_0x00e0;
    L_0x001c:
        r3 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r3.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r4 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r4.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r5 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r5.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
    L_0x002b:
        r6 = r0.moveToNext();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r6 == 0) goto L_0x00e0;
    L_0x0031:
        r6 = "__io";
        r6 = r0.getColumnIndex(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r6 = r0.getString(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r7 = "__ty";
        r7 = r0.getColumnIndex(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r7 = r0.getInt(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r8 = android.text.TextUtils.isEmpty(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r8 != 0) goto L_0x0062;
    L_0x004b:
        r8 = new org.json.JSONObject;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r6 = r10.b(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        r8.<init>(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r8 == 0) goto L_0x0062;
    L_0x0056:
        r6 = r8.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r6 <= 0) goto L_0x0062;
    L_0x005c:
        r6 = 2;
        if (r7 != r6) goto L_0x009e;
    L_0x005f:
        r4.put(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
    L_0x0062:
        r6 = r3.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r6 <= 0) goto L_0x006d;
    L_0x0068:
        r6 = "events";
        r2.put(r6, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
    L_0x006d:
        r6 = r4.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r6 <= 0) goto L_0x0078;
    L_0x0073:
        r6 = "session";
        r2.put(r6, r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
    L_0x0078:
        r6 = r5.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r6 <= 0) goto L_0x002b;
    L_0x007e:
        r6 = "pageview";
        r2.put(r6, r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        goto L_0x002b;
    L_0x0084:
        r3 = move-exception;
    L_0x0085:
        r3 = c;	 Catch:{ all -> 0x00c0 }
        com.umeng.analytics.pro.v.a(r3);	 Catch:{ all -> 0x00c0 }
        if (r0 == 0) goto L_0x008f;
    L_0x008c:
        r0.close();
    L_0x008f:
        if (r1 == 0) goto L_0x0094;
    L_0x0091:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00f9 }
    L_0x0094:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x009d:
        return r2;
    L_0x009e:
        r6 = 3;
        if (r7 != r6) goto L_0x00ba;
    L_0x00a1:
        r4.put(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        goto L_0x0062;
    L_0x00a5:
        r3 = move-exception;
    L_0x00a6:
        if (r0 == 0) goto L_0x00ab;
    L_0x00a8:
        r0.close();
    L_0x00ab:
        if (r1 == 0) goto L_0x00b0;
    L_0x00ad:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00fb }
    L_0x00b0:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x009d;
    L_0x00ba:
        if (r7 != 0) goto L_0x00d9;
    L_0x00bc:
        r3.put(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        goto L_0x0062;
    L_0x00c0:
        r2 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r9;
    L_0x00c5:
        if (r1 == 0) goto L_0x00ca;
    L_0x00c7:
        r1.close();
    L_0x00ca:
        if (r2 == 0) goto L_0x00cf;
    L_0x00cc:
        r2.endTransaction();	 Catch:{ Throwable -> 0x00fd }
    L_0x00cf:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x00d9:
        r6 = 1;
        if (r7 != r6) goto L_0x0062;
    L_0x00dc:
        r5.put(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        goto L_0x0062;
    L_0x00e0:
        r1.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0084, Throwable -> 0x00a5 }
        if (r0 == 0) goto L_0x00e8;
    L_0x00e5:
        r0.close();
    L_0x00e8:
        if (r1 == 0) goto L_0x00ed;
    L_0x00ea:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00f7 }
    L_0x00ed:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x009d;
    L_0x00f7:
        r0 = move-exception;
        goto L_0x00ed;
    L_0x00f9:
        r0 = move-exception;
        goto L_0x0094;
    L_0x00fb:
        r0 = move-exception;
        goto L_0x00b0;
    L_0x00fd:
        r1 = move-exception;
        goto L_0x00cf;
    L_0x00ff:
        r1 = move-exception;
        r2 = r0;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x00c5;
    L_0x0105:
        r2 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00c5;
    L_0x010b:
        r1 = move-exception;
        r1 = r0;
        goto L_0x00a6;
    L_0x010e:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.c():org.json.JSONObject");
    }

    private void a(JSONObject jSONObject, String str) {
        SQLiteDatabase a;
        Cursor rawQuery;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Throwable th2;
        Cursor cursor = null;
        try {
            a = f.a(c).a();
            try {
                a.beginTransaction();
                String str2 = "select *  from __et";
                if (!TextUtils.isEmpty(str)) {
                    str2 = "select *  from __et where __i=\"" + str + "\"";
                }
                rawQuery = a.rawQuery(str2, null);
                if (rawQuery != null) {
                    try {
                        String string;
                        JSONArray jSONArray;
                        Iterator keys;
                        JSONObject jSONObject2 = new JSONObject();
                        JSONObject jSONObject3 = new JSONObject();
                        CharSequence c = o.a().c();
                        while (rawQuery.moveToNext()) {
                            int i = rawQuery.getInt(rawQuery.getColumnIndex("__t"));
                            string = rawQuery.getString(rawQuery.getColumnIndex("__i"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex(com.umeng.analytics.pro.c.d.a.c));
                            if (TextUtils.isEmpty(string) || "-1".equals(string)) {
                                if (TextUtils.isEmpty(c)) {
                                    continue;
                                } else {
                                    string = c;
                                }
                            }
                            this.f.add(Integer.valueOf(rawQuery.getInt(0)));
                            JSONObject jSONObject4;
                            JSONArray optJSONArray;
                            switch (i) {
                                case a /*2049*/:
                                    if (!TextUtils.isEmpty(string2)) {
                                        jSONObject4 = new JSONObject(b(string2));
                                        if (jSONObject2.has(string)) {
                                            optJSONArray = jSONObject2.optJSONArray(string);
                                        } else {
                                            optJSONArray = new JSONArray();
                                        }
                                        optJSONArray.put(jSONObject4);
                                        jSONObject2.put(string, optJSONArray);
                                        break;
                                    }
                                    continue;
                                case b /*2050*/:
                                    if (!TextUtils.isEmpty(string2)) {
                                        jSONObject4 = new JSONObject(b(string2));
                                        if (jSONObject3.has(string)) {
                                            optJSONArray = jSONObject3.optJSONArray(string);
                                        } else {
                                            optJSONArray = new JSONArray();
                                        }
                                        optJSONArray.put(jSONObject4);
                                        jSONObject3.put(string, optJSONArray);
                                        break;
                                    }
                                    continue;
                                default:
                                    break;
                            }
                        }
                        if (jSONObject2.length() > 0) {
                            jSONArray = new JSONArray();
                            keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                JSONObject jSONObject5 = new JSONObject();
                                string = (String) keys.next();
                                jSONObject5.put(string, new JSONArray(jSONObject2.optString(string)));
                                if (jSONObject5.length() > 0) {
                                    jSONArray.put(jSONObject5);
                                }
                            }
                            if (jSONArray.length() > 0) {
                                jSONObject.put(b.N, jSONArray);
                            }
                        }
                        if (jSONObject3.length() > 0) {
                            jSONArray = new JSONArray();
                            keys = jSONObject3.keys();
                            while (keys.hasNext()) {
                                jSONObject2 = new JSONObject();
                                string = (String) keys.next();
                                jSONObject2.put(string, new JSONArray(jSONObject3.optString(string)));
                                if (jSONObject2.length() > 0) {
                                    jSONArray.put(jSONObject2);
                                }
                            }
                            if (jSONArray.length() > 0) {
                                jSONObject.put(b.O, jSONArray);
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException e) {
                        cursor = rawQuery;
                        sQLiteDatabase = a;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                a.setTransactionSuccessful();
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (a != null) {
                    try {
                        a.endTransaction();
                    } catch (Throwable th4) {
                    }
                }
                f.a(c).b();
            } catch (SQLiteDatabaseCorruptException e2) {
                sQLiteDatabase = a;
                try {
                    v.a(c);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th5) {
                        }
                    }
                    f.a(c).b();
                } catch (Throwable th6) {
                    th2 = th6;
                    a = sQLiteDatabase;
                    rawQuery = cursor;
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th7) {
                        }
                    }
                    f.a(c).b();
                    throw th;
                }
            } catch (Throwable th8) {
                th2 = th8;
                rawQuery = null;
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (a != null) {
                    a.endTransaction();
                }
                f.a(c).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
            sQLiteDatabase = null;
            v.a(c);
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            f.a(c).b();
        } catch (Throwable th82) {
            a = null;
            th = th82;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (a != null) {
                a.endTransaction();
            }
            f.a(c).b();
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(org.json.JSONObject r7, java.lang.String r8) {
        /*
        r6 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00df, Throwable -> 0x009a, all -> 0x00b0 }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00df, Throwable -> 0x009a, all -> 0x00b0 }
        r1 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00df, Throwable -> 0x009a, all -> 0x00b0 }
        r1.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        r2 = "select *  from __er";
        r3 = android.text.TextUtils.isEmpty(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        if (r3 != 0) goto L_0x002f;
    L_0x0016:
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        r2.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        r3 = "select *  from __er where __i=\"";
        r2 = r2.append(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        r2 = r2.append(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        r3 = "\"";
        r2 = r2.append(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        r2 = r2.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
    L_0x002f:
        r3 = 0;
        r0 = r1.rawQuery(r2, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd, all -> 0x00d1 }
        if (r0 == 0) goto L_0x0083;
    L_0x0036:
        r2 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        r2.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
    L_0x003b:
        r3 = r0.moveToNext();	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        if (r3 == 0) goto L_0x0078;
    L_0x0041:
        r3 = "__a";
        r3 = r0.getColumnIndex(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        r3 = r0.getString(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        if (r4 != 0) goto L_0x003b;
    L_0x0051:
        r4 = new org.json.JSONObject;	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        r3 = r6.b(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        r4.<init>(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        r2.put(r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        goto L_0x003b;
    L_0x005e:
        r2 = move-exception;
    L_0x005f:
        r2 = c;	 Catch:{ all -> 0x00d7 }
        com.umeng.analytics.pro.v.a(r2);	 Catch:{ all -> 0x00d7 }
        if (r0 == 0) goto L_0x0069;
    L_0x0066:
        r0.close();
    L_0x0069:
        if (r1 == 0) goto L_0x006e;
    L_0x006b:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00cb }
    L_0x006e:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0077:
        return;
    L_0x0078:
        r3 = r2.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        if (r3 <= 0) goto L_0x0083;
    L_0x007e:
        r3 = "error";
        r7.put(r3, r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
    L_0x0083:
        r1.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x005e, Throwable -> 0x00dd }
        if (r0 == 0) goto L_0x008b;
    L_0x0088:
        r0.close();
    L_0x008b:
        if (r1 == 0) goto L_0x0090;
    L_0x008d:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00c9 }
    L_0x0090:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0077;
    L_0x009a:
        r1 = move-exception;
        r1 = r0;
    L_0x009c:
        if (r0 == 0) goto L_0x00a1;
    L_0x009e:
        r0.close();
    L_0x00a1:
        if (r1 == 0) goto L_0x00a6;
    L_0x00a3:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00cd }
    L_0x00a6:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0077;
    L_0x00b0:
        r1 = move-exception;
        r2 = r0;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x00b5:
        if (r1 == 0) goto L_0x00ba;
    L_0x00b7:
        r1.close();
    L_0x00ba:
        if (r2 == 0) goto L_0x00bf;
    L_0x00bc:
        r2.endTransaction();	 Catch:{ Throwable -> 0x00cf }
    L_0x00bf:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x00c9:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00cb:
        r0 = move-exception;
        goto L_0x006e;
    L_0x00cd:
        r0 = move-exception;
        goto L_0x00a6;
    L_0x00cf:
        r1 = move-exception;
        goto L_0x00bf;
    L_0x00d1:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00b5;
    L_0x00d7:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00b5;
    L_0x00dd:
        r2 = move-exception;
        goto L_0x009c;
    L_0x00df:
        r1 = move-exception;
        r1 = r0;
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(org.json.JSONObject, java.lang.String):void");
    }

    private String a(JSONObject jSONObject, boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        Cursor cursor = null;
        String str = null;
        try {
            sQLiteDatabase = f.a(c).a();
            sQLiteDatabase.beginTransaction();
            cursor = sQLiteDatabase.rawQuery("select *  from __sd", null);
            if (cursor != null) {
                JSONArray jSONArray = new JSONArray();
                while (cursor.moveToNext()) {
                    JSONObject jSONObject2 = new JSONObject();
                    String string = cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.c.e.a.g));
                    String string2 = cursor.getString(cursor.getColumnIndex("__e"));
                    str = cursor.getString(cursor.getColumnIndex("__ii"));
                    if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2))) {
                        if (Long.parseLong(string) - Long.parseLong(string2) > 0) {
                            String string3 = cursor.getString(cursor.getColumnIndex("__a"));
                            String string4 = cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.c.e.a.c));
                            String string5 = cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.c.e.a.d));
                            String string6 = cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.c.e.a.e));
                            this.e.add(str);
                            jSONObject2.put("id", str);
                            jSONObject2.put(b.p, string2);
                            jSONObject2.put(b.q, string);
                            jSONObject2.put("duration", Long.parseLong(string) - Long.parseLong(string2));
                            if (!TextUtils.isEmpty(string3)) {
                                jSONObject2.put(b.s, new JSONArray(b(string3)));
                            }
                            if (!TextUtils.isEmpty(string4)) {
                                jSONObject2.put(b.t, new JSONArray(b(string4)));
                            }
                            if (!TextUtils.isEmpty(string5)) {
                                jSONObject2.put(b.A, new JSONObject(b(string5)));
                            }
                            if (!TextUtils.isEmpty(string6)) {
                                jSONObject2.put(b.w, new JSONArray(b(string6)));
                            }
                            if (jSONObject2.length() > 0) {
                                jSONArray.put(jSONObject2);
                            }
                        }
                        if (z) {
                            break;
                        }
                    }
                }
                if (this.e.size() < 1) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th) {
                        }
                    }
                    f.a(c).b();
                    return str;
                } else if (jSONArray.length() > 0) {
                    jSONObject.put(b.n, jSONArray);
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th2) {
                }
            }
            f.a(c).b();
        } catch (SQLiteDatabaseCorruptException e) {
            v.a(c);
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th3) {
        }
        return str;
        f.a(c).b();
        return str;
        f.a(c).b();
        return str;
    }

    public void a(boolean z, boolean z2) {
        Throwable th;
        Throwable th2;
        SQLiteDatabase sQLiteDatabase = null;
        SQLiteDatabase a;
        try {
            a = f.a(c).a();
            try {
                a.beginTransaction();
                String str = "";
                if (z2) {
                    if (z) {
                        a.execSQL("delete from __sd");
                    }
                } else if (this.e.size() > 0) {
                    for (int i = 0; i < this.e.size(); i++) {
                        a.execSQL("delete from __sd where __ii=\"" + ((String) this.e.get(i)) + "\"");
                    }
                }
                a.setTransactionSuccessful();
                if (a != null) {
                    try {
                        a.endTransaction();
                    } catch (Throwable th3) {
                    }
                }
                f.a(c).b();
            } catch (SQLiteDatabaseCorruptException e) {
                sQLiteDatabase = a;
                try {
                    v.a(c);
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th4) {
                        }
                    }
                    f.a(c).b();
                } catch (Throwable th5) {
                    th = th5;
                    a = sQLiteDatabase;
                    th2 = th;
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th6) {
                        }
                    }
                    f.a(c).b();
                    throw th2;
                }
            } catch (Throwable th7) {
                th2 = th7;
                if (a != null) {
                    a.endTransaction();
                }
                f.a(c).b();
                throw th2;
            }
        } catch (SQLiteDatabaseCorruptException e2) {
            v.a(c);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            f.a(c).b();
        } catch (Throwable th52) {
            th = th52;
            a = null;
            th2 = th;
            if (a != null) {
                a.endTransaction();
            }
            f.a(c).b();
            throw th2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
        r5 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a, all -> 0x007a }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a, all -> 0x007a }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a, all -> 0x007a }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r1 = r5.f;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r1 = r1.size();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        if (r1 <= 0) goto L_0x003e;
    L_0x0016:
        r1 = 0;
    L_0x0017:
        r2 = r5.f;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r2 = r2.size();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        if (r1 >= r2) goto L_0x003e;
    L_0x001f:
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r2.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r3 = "delete from __et where rowid=";
        r2 = r2.append(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r3 = r5.f;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r3 = r3.get(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r2 = r2.append(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r2 = r2.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r0.execSQL(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r1 = r1 + 1;
        goto L_0x0017;
    L_0x003e:
        r1 = r5.f;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r1.clear();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0055, Throwable -> 0x006a }
        if (r0 == 0) goto L_0x004b;
    L_0x0048:
        r0.endTransaction();	 Catch:{ Throwable -> 0x008d }
    L_0x004b:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0054:
        return;
    L_0x0055:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x0095 }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x0095 }
        if (r0 == 0) goto L_0x0060;
    L_0x005d:
        r0.endTransaction();	 Catch:{ Throwable -> 0x008f }
    L_0x0060:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0054;
    L_0x006a:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0070;
    L_0x006d:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0091 }
    L_0x0070:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0054;
    L_0x007a:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x007e:
        if (r1 == 0) goto L_0x0083;
    L_0x0080:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0093 }
    L_0x0083:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x008d:
        r0 = move-exception;
        goto L_0x004b;
    L_0x008f:
        r0 = move-exception;
        goto L_0x0060;
    L_0x0091:
        r0 = move-exception;
        goto L_0x0070;
    L_0x0093:
        r1 = move-exception;
        goto L_0x0083;
    L_0x0095:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.d():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
        r3 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0025, Throwable -> 0x003a, all -> 0x004a }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0025, Throwable -> 0x003a, all -> 0x004a }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0025, Throwable -> 0x003a, all -> 0x004a }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0025, Throwable -> 0x003a }
        r1 = "delete from __er";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0025, Throwable -> 0x003a }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0025, Throwable -> 0x003a }
        if (r0 == 0) goto L_0x001b;
    L_0x0018:
        r0.endTransaction();	 Catch:{ Throwable -> 0x005d }
    L_0x001b:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0024:
        return;
    L_0x0025:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x0065 }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x0065 }
        if (r0 == 0) goto L_0x0030;
    L_0x002d:
        r0.endTransaction();	 Catch:{ Throwable -> 0x005f }
    L_0x0030:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0024;
    L_0x003a:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0040;
    L_0x003d:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0061 }
    L_0x0040:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0024;
    L_0x004a:
        r1 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r2;
    L_0x004e:
        if (r1 == 0) goto L_0x0053;
    L_0x0050:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0063 }
    L_0x0053:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x005d:
        r0 = move-exception;
        goto L_0x001b;
    L_0x005f:
        r0 = move-exception;
        goto L_0x0030;
    L_0x0061:
        r0 = move-exception;
        goto L_0x0040;
    L_0x0063:
        r1 = move-exception;
        goto L_0x0053;
    L_0x0065:
        r1 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r2;
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.e():void");
    }

    public void f() {
        b(-1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(int r5) {
        /*
        r4 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054, all -> 0x0099 }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054, all -> 0x0099 }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054, all -> 0x0099 }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        r1 = "delete from __dp";
        r2 = -1;
        if (r5 != r2) goto L_0x0028;
    L_0x0013:
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
    L_0x0016:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        if (r0 == 0) goto L_0x001e;
    L_0x001b:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0091 }
    L_0x001e:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x0027:
        return;
    L_0x0028:
        r1 = 1;
        if (r5 != r1) goto L_0x0046;
    L_0x002b:
        r1 = "delete from __dp where __ty=1";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        goto L_0x0016;
    L_0x0031:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x006d }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x006d }
        if (r0 == 0) goto L_0x003c;
    L_0x0039:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0093 }
    L_0x003c:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0027;
    L_0x0046:
        r1 = 4;
        if (r5 != r1) goto L_0x0064;
    L_0x0049:
        r1 = "delete from __dp where __ty=3";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        r1 = "delete from __dp where __ty=2";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        goto L_0x0016;
    L_0x0054:
        r1 = move-exception;
        if (r0 == 0) goto L_0x005a;
    L_0x0057:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0095 }
    L_0x005a:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x0027;
    L_0x0064:
        r1 = 3;
        if (r5 != r1) goto L_0x0080;
    L_0x0067:
        r1 = "delete from __dp where __ty=3";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        goto L_0x0016;
    L_0x006d:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x0071:
        if (r1 == 0) goto L_0x0076;
    L_0x0073:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0097 }
    L_0x0076:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x0080:
        if (r5 != 0) goto L_0x0088;
    L_0x0082:
        r1 = "delete from __dp where __ty=0";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        goto L_0x0016;
    L_0x0088:
        r1 = 2;
        if (r5 != r1) goto L_0x0016;
    L_0x008b:
        r1 = "delete from __dp where __ty=2";
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0031, Throwable -> 0x0054 }
        goto L_0x0016;
    L_0x0091:
        r0 = move-exception;
        goto L_0x001e;
    L_0x0093:
        r0 = move-exception;
        goto L_0x003c;
    L_0x0095:
        r0 = move-exception;
        goto L_0x005a;
    L_0x0097:
        r1 = move-exception;
        goto L_0x0076;
    L_0x0099:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.b(int):void");
    }

    public void g() {
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        if (!TextUtils.isEmpty(this.g)) {
            SQLiteDatabase a;
            try {
                a = f.a(c).a();
                try {
                    a.beginTransaction();
                    a.execSQL("delete from __er where __i=\"" + this.g + "\"");
                    a.execSQL("delete from __et where __i=\"" + this.g + "\"");
                    a.setTransactionSuccessful();
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th2) {
                        }
                    }
                    f.a(c).b();
                } catch (SQLiteDatabaseCorruptException e) {
                    try {
                        v.a(c);
                        if (a != null) {
                            try {
                                a.endTransaction();
                            } catch (Throwable th3) {
                            }
                        }
                        f.a(c).b();
                        this.g = null;
                    } catch (Throwable th4) {
                        Throwable th5 = th4;
                        sQLiteDatabase = a;
                        th = th5;
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                            } catch (Throwable th6) {
                            }
                        }
                        f.a(c).b();
                        throw th;
                    }
                } catch (Throwable th7) {
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th8) {
                        }
                    }
                    f.a(c).b();
                    this.g = null;
                }
            } catch (SQLiteDatabaseCorruptException e2) {
                a = null;
                v.a(c);
                if (a != null) {
                    a.endTransaction();
                }
                f.a(c).b();
                this.g = null;
            } catch (Throwable th9) {
                th = th9;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                f.a(c).b();
                throw th;
            }
        }
        this.g = null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r5, java.lang.String r6) {
        /*
        r4 = this;
        r0 = 0;
        r4.g = r6;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd, all -> 0x00ed }
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd, all -> 0x00ed }
        r1 = com.umeng.analytics.pro.f.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd, all -> 0x00ed }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd, all -> 0x00ed }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = android.text.TextUtils.isEmpty(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        if (r1 != 0) goto L_0x00b6;
    L_0x0016:
        r1 = new java.lang.StringBuilder;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "delete from __er where __i=\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.append(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = new java.lang.StringBuilder;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "delete from __et where __i=\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.append(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        if (r5 == 0) goto L_0x00b6;
    L_0x0050:
        r1 = new java.lang.StringBuilder;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "update __sd set __b=\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = 0;
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "\" where ";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "__ii";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "=\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.append(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = new java.lang.StringBuilder;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "update __sd set __a=\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = 0;
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "\" where ";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "__ii";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "=\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.append(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r2 = "\"";
        r1 = r1.append(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r1 = r1.toString();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        r0.execSQL(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
    L_0x00b6:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c8, Throwable -> 0x00dd }
        if (r0 == 0) goto L_0x00be;
    L_0x00bb:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0100 }
    L_0x00be:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
    L_0x00c7:
        return;
    L_0x00c8:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x0108 }
        com.umeng.analytics.pro.v.a(r1);	 Catch:{ all -> 0x0108 }
        if (r0 == 0) goto L_0x00d3;
    L_0x00d0:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0102 }
    L_0x00d3:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x00c7;
    L_0x00dd:
        r1 = move-exception;
        if (r0 == 0) goto L_0x00e3;
    L_0x00e0:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0104 }
    L_0x00e3:
        r0 = c;
        r0 = com.umeng.analytics.pro.f.a(r0);
        r0.b();
        goto L_0x00c7;
    L_0x00ed:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x00f1:
        if (r1 == 0) goto L_0x00f6;
    L_0x00f3:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0106 }
    L_0x00f6:
        r1 = c;
        r1 = com.umeng.analytics.pro.f.a(r1);
        r1.b();
        throw r0;
    L_0x0100:
        r0 = move-exception;
        goto L_0x00be;
    L_0x0102:
        r0 = move-exception;
        goto L_0x00d3;
    L_0x0104:
        r0 = move-exception;
        goto L_0x00e3;
    L_0x0106:
        r1 = move-exception;
        goto L_0x00f6;
    L_0x0108:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x00f1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.g.a(boolean, java.lang.String):void");
    }

    private void i() {
        try {
            if (TextUtils.isEmpty(d)) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(c);
                Object string = sharedPreferences.getString("ek__id", null);
                if (TextUtils.isEmpty(string)) {
                    string = DeviceConfig.getDBencryptID(c);
                    if (!TextUtils.isEmpty(string)) {
                        sharedPreferences.edit().putString("ek__id", string).commit();
                    }
                }
                if (!TextUtils.isEmpty(string)) {
                    String substring = string.substring(1, 9);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < substring.length(); i++) {
                        char charAt = substring.charAt(i);
                        if (!Character.isDigit(charAt)) {
                            stringBuilder.append(charAt);
                        } else if (Integer.parseInt(Character.toString(charAt)) == 0) {
                            stringBuilder.append(0);
                        } else {
                            stringBuilder.append(10 - Integer.parseInt(Character.toString(charAt)));
                        }
                    }
                    d = stringBuilder.toString();
                }
                if (!TextUtils.isEmpty(d)) {
                    d += new StringBuilder(d).reverse().toString();
                    String string2 = sharedPreferences.getString("ek_key", null);
                    if (TextUtils.isEmpty(string2)) {
                        sharedPreferences.edit().putString("ek_key", a("umeng+")).commit();
                    } else if (!"umeng+".equals(b(string2))) {
                        a(true, false);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public String a(String str) {
        try {
            if (TextUtils.isEmpty(d)) {
                return str;
            }
            return Base64.encodeToString(DataHelper.encrypt(str.getBytes(), d.getBytes()), 0);
        } catch (Exception e) {
            return null;
        }
    }

    public String b(String str) {
        try {
            if (TextUtils.isEmpty(d)) {
                return str;
            }
            return new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), d.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }
}
