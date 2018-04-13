package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.s.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class w {
    public static final int a = 2049;
    public static final int b = 2050;
    private static Context c = null;
    private static String d = null;
    private static final String e = "umeng+";
    private static final String f = "ek__id";
    private static final String g = "ek_key";
    private List<String> h;

    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION
    }

    private static class b {
        private static final w a = new w();

        private b() {
        }
    }

    private w() {
        this.h = new ArrayList();
        if (c != null) {
            b();
            this.h.clear();
        }
    }

    public static final w a(Context context) {
        c = context;
        return b.a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.json.JSONArray r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = c;	 Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r1 = com.umeng.analytics.pro.u.a(r1);	 Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r0 = r1.a();	 Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r0.beginTransaction();	 Catch:{ Throwable -> 0x0087, all -> 0x00a6 }
        r1 = 0;
    L_0x000f:
        r2 = r8.length();	 Catch:{ Throwable -> 0x0087, all -> 0x00a6 }
        if (r1 >= r2) goto L_0x0077;
    L_0x0015:
        r3 = r8.getJSONObject(r1);	 Catch:{ Exception -> 0x00ab }
        r4 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00ab }
        r4.<init>();	 Catch:{ Exception -> 0x00ab }
        r2 = "__i";
        r2 = r3.optString(r2);	 Catch:{ Exception -> 0x00ab }
        r5 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00ab }
        if (r5 == 0) goto L_0x0038;
    L_0x002a:
        r2 = c;	 Catch:{ Exception -> 0x00ab }
        r2 = com.umeng.analytics.pro.bd.g(r2);	 Catch:{ Exception -> 0x00ab }
        r5 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00ab }
        if (r5 == 0) goto L_0x0038;
    L_0x0036:
        r2 = "";
    L_0x0038:
        r5 = "__i";
        r4.put(r5, r2);	 Catch:{ Exception -> 0x00ab }
        r2 = "__e";
        r5 = "id";
        r5 = r3.optString(r5);	 Catch:{ Exception -> 0x00ab }
        r4.put(r2, r5);	 Catch:{ Exception -> 0x00ab }
        r2 = "__t";
        r5 = "__t";
        r5 = r3.optInt(r5);	 Catch:{ Exception -> 0x00ab }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x00ab }
        r4.put(r2, r5);	 Catch:{ Exception -> 0x00ab }
        r2 = "__i";
        r3.remove(r2);	 Catch:{ Exception -> 0x00ab }
        r2 = "__t";
        r3.remove(r2);	 Catch:{ Exception -> 0x00ab }
        r2 = "__s";
        r3 = r3.toString();	 Catch:{ Exception -> 0x00ab }
        r3 = r7.a(r3);	 Catch:{ Exception -> 0x00ab }
        r4.put(r2, r3);	 Catch:{ Exception -> 0x00ab }
        r2 = "__et";
        r3 = 0;
        r0.insert(r2, r3, r4);	 Catch:{ Exception -> 0x00ab }
    L_0x0074:
        r1 = r1 + 1;
        goto L_0x000f;
    L_0x0077:
        r0.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0087, all -> 0x00a6 }
        r0.endTransaction();
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
    L_0x0086:
        return;
    L_0x0087:
        r1 = move-exception;
        r0.endTransaction();
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0086;
    L_0x0095:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0099:
        r1.endTransaction();
        r1 = c;
        r1 = com.umeng.analytics.pro.u.a(r1);
        r1.b();
        throw r0;
    L_0x00a6:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0099;
    L_0x00ab:
        r2 = move-exception;
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.a(org.json.JSONArray):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r6, java.lang.String r7, int r8) {
        /*
        r5 = this;
        r0 = 0;
        r1 = c;	 Catch:{ Throwable -> 0x0047, all -> 0x0055 }
        r1 = com.umeng.analytics.pro.u.a(r1);	 Catch:{ Throwable -> 0x0047, all -> 0x0055 }
        r0 = r1.a();	 Catch:{ Throwable -> 0x0047, all -> 0x0055 }
        r0.beginTransaction();	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r1 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r1.<init>();	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r2 = "__i";
        r1.put(r2, r6);	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r2 = r5.a(r7);	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        if (r3 != 0) goto L_0x0036;
    L_0x0022:
        r3 = "__a";
        r1.put(r3, r2);	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r2 = "__t";
        r3 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r2 = "__er";
        r3 = 0;
        r0.insert(r2, r3, r1);	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
    L_0x0036:
        r0.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0047, all -> 0x0066 }
        r0.endTransaction();
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
    L_0x0045:
        r0 = 0;
        return r0;
    L_0x0047:
        r1 = move-exception;
        r0.endTransaction();
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0045;
    L_0x0055:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0059:
        r1.endTransaction();
        r1 = c;
        r1 = com.umeng.analytics.pro.u.a(r1);
        r1.b();
        throw r0;
    L_0x0066:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.a(java.lang.String, java.lang.String, int):boolean");
    }

    public boolean a(String str, JSONObject jSONObject, a aVar) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor2 = null;
        if (jSONObject != null) {
            SQLiteDatabase a;
            try {
                a = u.a(c).a();
                try {
                    a.beginTransaction();
                    if (aVar == a.BEGIN) {
                        long longValue = ((Long) jSONObject.get("__e")).longValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(com.umeng.analytics.pro.s.c.a.a, str);
                        contentValues.put("__e", String.valueOf(longValue));
                        a.insert(c.a, null, contentValues);
                        cursor = null;
                    } else if (aVar == a.END) {
                        a.execSQL("update __sd set __f=\"" + ((Long) jSONObject.get(com.umeng.analytics.pro.s.c.a.g)).longValue() + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                        cursor = null;
                    } else if (aVar == a.PAGE) {
                        a(str, jSONObject, a, "__a");
                        cursor = null;
                    } else if (aVar == a.AUTOPAGE) {
                        a(str, jSONObject, a, com.umeng.analytics.pro.s.c.a.c);
                        cursor = null;
                    } else if (aVar == a.NEWSESSION) {
                        Object jSONObject2;
                        Object obj;
                        Object a2;
                        try {
                            jSONObject2 = jSONObject.getJSONObject(com.umeng.analytics.pro.s.c.a.e);
                        } catch (Exception e) {
                            jSONObject2 = null;
                        }
                        if (jSONObject2 != null) {
                            cursor = a.rawQuery("select __d from __sd where __ii=\"" + str + "\"", null);
                            if (cursor != null) {
                                while (cursor.moveToNext()) {
                                    try {
                                        cursor2 = b(cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.s.c.a.e)));
                                    } catch (Throwable th2) {
                                        Throwable th3 = th2;
                                        cursor2 = cursor;
                                        th = th3;
                                    }
                                }
                                obj = cursor2;
                            } else {
                                obj = null;
                            }
                        } else {
                            obj = null;
                            cursor = null;
                        }
                        if (jSONObject2 != null) {
                            try {
                                JSONArray jSONArray = new JSONArray();
                                if (!TextUtils.isEmpty(obj)) {
                                    jSONArray = new JSONArray(obj);
                                }
                                jSONArray.put(jSONObject2);
                                a2 = a(jSONArray.toString());
                                if (!TextUtils.isEmpty(a2)) {
                                    a.execSQL("update  __sd set __d=\"" + a2 + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                                }
                            } catch (Exception e2) {
                            }
                        }
                        try {
                            JSONObject jSONObject3 = jSONObject.getJSONObject(com.umeng.analytics.pro.s.c.a.d);
                            if (jSONObject3 != null) {
                                a2 = a(jSONObject3.toString());
                                if (!TextUtils.isEmpty(a2)) {
                                    a.execSQL("update  __sd set __c=\"" + a2 + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                                }
                            }
                        } catch (Exception e3) {
                        }
                        try {
                            a.execSQL("update  __sd set __f=\"" + String.valueOf(jSONObject.getLong(com.umeng.analytics.pro.s.c.a.g)) + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                        } catch (Exception e4) {
                        }
                    } else {
                        cursor = null;
                    }
                    a.setTransactionSuccessful();
                    if (cursor != null) {
                        cursor.close();
                    }
                    a.endTransaction();
                    u.a(c).b();
                } catch (Throwable th4) {
                    th = th4;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    a.endTransaction();
                    u.a(c).b();
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                a = null;
                if (cursor2 != null) {
                    cursor2.close();
                }
                a.endTransaction();
                u.a(c).b();
                throw th;
            }
        }
        return false;
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) throws JSONException {
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            Object obj;
            rawQuery = sQLiteDatabase.rawQuery("select " + str2 + " from " + c.a + " where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"", null);
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
                sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + a + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
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

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        c(jSONObject2);
        b(jSONObject2);
        a(jSONObject2);
        try {
            if (jSONObject2.length() > 0) {
                jSONObject.put(com.umeng.analytics.a.z, jSONObject2);
            }
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private void a(JSONObject jSONObject) {
        SQLiteDatabase a;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            a = u.a(c).a();
            try {
                a.beginTransaction();
                rawQuery = a.rawQuery("select *  from __et", null);
                if (rawQuery != null) {
                    try {
                        String string;
                        JSONObject jSONObject2;
                        JSONArray optJSONArray;
                        JSONObject jSONObject3 = new JSONObject();
                        JSONObject jSONObject4 = new JSONObject();
                        while (rawQuery.moveToNext()) {
                            int i = rawQuery.getInt(rawQuery.getColumnIndex("__t"));
                            string = rawQuery.getString(rawQuery.getColumnIndex("__i"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex(com.umeng.analytics.pro.s.b.a.c));
                            if ("".equals(string)) {
                                string = bd.a();
                            }
                            switch (i) {
                                case a /*2049*/:
                                    if (!TextUtils.isEmpty(string2)) {
                                        jSONObject2 = new JSONObject(b(string2));
                                        if (jSONObject3.has(string)) {
                                            optJSONArray = jSONObject3.optJSONArray(string);
                                        } else {
                                            optJSONArray = new JSONArray();
                                        }
                                        optJSONArray.put(jSONObject2);
                                        jSONObject3.put(string, optJSONArray);
                                        break;
                                    }
                                    continue;
                                case b /*2050*/:
                                    if (!TextUtils.isEmpty(string2)) {
                                        jSONObject2 = new JSONObject(b(string2));
                                        if (jSONObject4.has(string)) {
                                            optJSONArray = jSONObject4.optJSONArray(string);
                                        } else {
                                            optJSONArray = new JSONArray();
                                        }
                                        optJSONArray.put(jSONObject2);
                                        jSONObject4.put(string, optJSONArray);
                                        break;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (jSONObject3.length() > 0) {
                            optJSONArray = new JSONArray();
                            Iterator keys = jSONObject3.keys();
                            while (keys.hasNext()) {
                                jSONObject2 = new JSONObject();
                                string = (String) keys.next();
                                jSONObject2.put(string, new JSONArray(jSONObject3.optString(string)));
                                if (jSONObject2.length() > 0) {
                                    optJSONArray.put(jSONObject2);
                                }
                            }
                            if (optJSONArray.length() > 0) {
                                jSONObject.put(x.aJ, optJSONArray);
                            }
                        }
                        if (jSONObject4.length() > 0) {
                            optJSONArray = new JSONArray();
                            Iterator keys2 = jSONObject4.keys();
                            while (keys2.hasNext()) {
                                JSONObject jSONObject5 = new JSONObject();
                                string = (String) keys2.next();
                                jSONObject5.put(string, new JSONArray(jSONObject4.optString(string)));
                                if (jSONObject5.length() > 0) {
                                    optJSONArray.put(jSONObject5);
                                }
                            }
                            if (optJSONArray.length() > 0) {
                                jSONObject.put(x.aK, optJSONArray);
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                a.setTransactionSuccessful();
                if (rawQuery != null) {
                    rawQuery.close();
                }
                a.endTransaction();
                u.a(c).b();
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                a.endTransaction();
                u.a(c).b();
                throw th;
            }
        } catch (Throwable th32) {
            a = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            a.endTransaction();
            u.a(c).b();
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(org.json.JSONObject r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = c;	 Catch:{ Throwable -> 0x0095, all -> 0x0072 }
        r1 = com.umeng.analytics.pro.u.a(r1);	 Catch:{ Throwable -> 0x0095, all -> 0x0072 }
        r1 = r1.a();	 Catch:{ Throwable -> 0x0095, all -> 0x0072 }
        r1.beginTransaction();	 Catch:{ Throwable -> 0x003f, all -> 0x0089 }
        r2 = "select *  from __er";
        r3 = 0;
        r0 = r1.rawQuery(r2, r3);	 Catch:{ Throwable -> 0x003f, all -> 0x0089 }
        if (r0 == 0) goto L_0x005d;
    L_0x0017:
        r2 = new org.json.JSONArray;	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        r2.<init>();	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
    L_0x001c:
        r3 = r0.moveToNext();	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        if (r3 == 0) goto L_0x0052;
    L_0x0022:
        r3 = "__a";
        r3 = r0.getColumnIndex(r3);	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        r3 = r0.getString(r3);	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        if (r4 != 0) goto L_0x001c;
    L_0x0032:
        r4 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        r3 = r6.b(r3);	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        r4.<init>(r3);	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        r2.put(r4);	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        goto L_0x001c;
    L_0x003f:
        r2 = move-exception;
    L_0x0040:
        if (r0 == 0) goto L_0x0045;
    L_0x0042:
        r0.close();
    L_0x0045:
        r1.endTransaction();
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
    L_0x0051:
        return;
    L_0x0052:
        r3 = r2.length();	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        if (r3 <= 0) goto L_0x005d;
    L_0x0058:
        r3 = "error";
        r7.put(r3, r2);	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
    L_0x005d:
        r1.setTransactionSuccessful();	 Catch:{ Throwable -> 0x003f, all -> 0x008f }
        if (r0 == 0) goto L_0x0065;
    L_0x0062:
        r0.close();
    L_0x0065:
        r1.endTransaction();
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0051;
    L_0x0072:
        r1 = move-exception;
        r2 = r0;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0077:
        if (r1 == 0) goto L_0x007c;
    L_0x0079:
        r1.close();
    L_0x007c:
        r2.endTransaction();
        r1 = c;
        r1 = com.umeng.analytics.pro.u.a(r1);
        r1.b();
        throw r0;
    L_0x0089:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0077;
    L_0x008f:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0077;
    L_0x0095:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.b(org.json.JSONObject):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(org.json.JSONObject r18) {
        /*
        r17 = this;
        r3 = 0;
        r2 = 0;
        r4 = c;	 Catch:{ Throwable -> 0x0118, all -> 0x014d }
        r4 = com.umeng.analytics.pro.u.a(r4);	 Catch:{ Throwable -> 0x0118, all -> 0x014d }
        r3 = r4.a();	 Catch:{ Throwable -> 0x0118, all -> 0x014d }
        r3.beginTransaction();	 Catch:{ Throwable -> 0x0118, all -> 0x0166 }
        r4 = "select *  from __sd";
        r5 = 0;
        r2 = r3.rawQuery(r4, r5);	 Catch:{ Throwable -> 0x0118, all -> 0x0166 }
        if (r2 == 0) goto L_0x0138;
    L_0x0018:
        r4 = new org.json.JSONArray;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r4.<init>();	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r0 = r17;
        r5 = r0.h;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r5.clear();	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
    L_0x0024:
        r5 = r2.moveToNext();	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r5 == 0) goto L_0x012b;
    L_0x002a:
        r5 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r5.<init>();	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r6 = "__f";
        r6 = r2.getColumnIndex(r6);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r6 = r2.getString(r6);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r7 = "__e";
        r7 = r2.getColumnIndex(r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r7 = r2.getString(r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r8 = android.text.TextUtils.isEmpty(r6);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r8 != 0) goto L_0x0024;
    L_0x0049:
        r8 = android.text.TextUtils.isEmpty(r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r8 != 0) goto L_0x0024;
    L_0x004f:
        r8 = java.lang.Long.parseLong(r6);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r10 = java.lang.Long.parseLong(r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r8 = r8 - r10;
        r10 = 0;
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r8 <= 0) goto L_0x0024;
    L_0x005e:
        r8 = "__a";
        r8 = r2.getColumnIndex(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r8 = r2.getString(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r9 = "__b";
        r9 = r2.getColumnIndex(r9);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r9 = r2.getString(r9);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r10 = "__c";
        r10 = r2.getColumnIndex(r10);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r10 = r2.getString(r10);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r11 = "__d";
        r11 = r2.getColumnIndex(r11);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r11 = r2.getString(r11);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r12 = "__ii";
        r12 = r2.getColumnIndex(r12);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r12 = r2.getString(r12);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r0 = r17;
        r13 = r0.h;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r13.add(r12);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r13 = "id";
        r5.put(r13, r12);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r12 = "start_time";
        r5.put(r12, r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r12 = "end_time";
        r5.put(r12, r6);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r12 = "duration";
        r14 = java.lang.Long.parseLong(r6);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r6 = java.lang.Long.parseLong(r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r6 = r14 - r6;
        r5.put(r12, r6);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r6 = android.text.TextUtils.isEmpty(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r6 != 0) goto L_0x00cb;
    L_0x00bb:
        r6 = "pages";
        r7 = new org.json.JSONArray;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r0 = r17;
        r8 = r0.b(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r7.<init>(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r5.put(r6, r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
    L_0x00cb:
        r6 = android.text.TextUtils.isEmpty(r9);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r6 != 0) goto L_0x00e1;
    L_0x00d1:
        r6 = "autopages";
        r7 = new org.json.JSONArray;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r0 = r17;
        r8 = r0.b(r9);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r7.<init>(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r5.put(r6, r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
    L_0x00e1:
        r6 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r6 != 0) goto L_0x00f7;
    L_0x00e7:
        r6 = "traffic";
        r7 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r0 = r17;
        r8 = r0.b(r10);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r7.<init>(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r5.put(r6, r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
    L_0x00f7:
        r6 = android.text.TextUtils.isEmpty(r11);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r6 != 0) goto L_0x010d;
    L_0x00fd:
        r6 = "locations";
        r7 = new org.json.JSONArray;	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r0 = r17;
        r8 = r0.b(r11);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r7.<init>(r8);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        r5.put(r6, r7);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
    L_0x010d:
        r6 = r5.length();	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r6 <= 0) goto L_0x0024;
    L_0x0113:
        r4.put(r5);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        goto L_0x0024;
    L_0x0118:
        r4 = move-exception;
        if (r2 == 0) goto L_0x011e;
    L_0x011b:
        r2.close();
    L_0x011e:
        r3.endTransaction();
        r2 = c;
        r2 = com.umeng.analytics.pro.u.a(r2);
        r2.b();
    L_0x012a:
        return;
    L_0x012b:
        r5 = r4.length();	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r5 <= 0) goto L_0x0138;
    L_0x0131:
        r5 = "sessions";
        r0 = r18;
        r0.put(r5, r4);	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
    L_0x0138:
        r3.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0118, all -> 0x016e }
        if (r2 == 0) goto L_0x0140;
    L_0x013d:
        r2.close();
    L_0x0140:
        r3.endTransaction();
        r2 = c;
        r2 = com.umeng.analytics.pro.u.a(r2);
        r2.b();
        goto L_0x012a;
    L_0x014d:
        r4 = move-exception;
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
    L_0x0154:
        if (r3 == 0) goto L_0x0159;
    L_0x0156:
        r3.close();
    L_0x0159:
        r4.endTransaction();
        r3 = c;
        r3 = com.umeng.analytics.pro.u.a(r3);
        r3.b();
        throw r2;
    L_0x0166:
        r4 = move-exception;
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
        goto L_0x0154;
    L_0x016e:
        r4 = move-exception;
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
        goto L_0x0154;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.c(org.json.JSONObject):void");
    }

    public void a(boolean z, boolean z2) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        SQLiteDatabase a;
        try {
            a = u.a(c).a();
            try {
                a.beginTransaction();
                a.execSQL("delete from __er");
                a.execSQL("delete from __et");
                if (!z2) {
                    if (this.h.size() > 0) {
                        for (int i = 0; i < this.h.size(); i++) {
                            a.execSQL("delete from __sd where __ii=\"" + ((String) this.h.get(i)) + "\"");
                        }
                    }
                    this.h.clear();
                } else if (z) {
                    a.execSQL("delete from __sd");
                }
                a.setTransactionSuccessful();
                a.endTransaction();
                u.a(c).b();
            } catch (Throwable th2) {
                th = th2;
                a.endTransaction();
                u.a(c).b();
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a = null;
            th = th4;
            a.endTransaction();
            u.a(c).b();
            throw th;
        }
    }

    private void b() {
        try {
            if (TextUtils.isEmpty(d)) {
                SharedPreferences a = ba.a(c);
                Object string = a.getString(f, null);
                if (TextUtils.isEmpty(string)) {
                    string = bv.A(c);
                    if (!TextUtils.isEmpty(string)) {
                        a.edit().putString(f, string).commit();
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
                    String string2 = a.getString(g, null);
                    if (TextUtils.isEmpty(string2)) {
                        a.edit().putString(g, a(e)).commit();
                    } else if (!e.equals(b(string2))) {
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
            return Base64.encodeToString(bt.a(str.getBytes(), d.getBytes()), 0);
        } catch (Exception e) {
            return null;
        }
    }

    public String b(String str) {
        try {
            if (TextUtils.isEmpty(d)) {
                return str;
            }
            return new String(bt.b(Base64.decode(str.getBytes(), 0), d.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }
}
