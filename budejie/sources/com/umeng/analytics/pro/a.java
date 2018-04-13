package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.umeng.analytics.pro.d.b;
import com.umeng.analytics.pro.d.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.database.sqlite.SQLiteDatabase r12, java.util.Map<java.lang.String, com.umeng.analytics.pro.k> r13, com.umeng.analytics.pro.f r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0013 in list [B:5:0x0010]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r6 = 0;
        r4 = 0;
        r1 = 0;
        r0 = "__ag_of";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r13.get(r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = (com.umeng.analytics.pro.k) r0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r0 != 0) goto L_0x0014;
    L_0x000e:
        if (r1 == 0) goto L_0x0013;
    L_0x0010:
        r1.close();
    L_0x0013:
        return;
    L_0x0014:
        r5 = "system where key=\"__ag_of\"";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r3 = "select * from ";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.append(r3);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.append(r5);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r3 = 0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r1 = r12.rawQuery(r2, r3);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r1.moveToFirst();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r6;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x0032:
        r8 = r1.isAfterLast();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r8 != 0) goto L_0x008d;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x0038:
        r8 = r1.getCount();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r8 <= 0) goto L_0x0068;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x003e:
        r2 = "count";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r1.getColumnIndex(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r4 = r1.getInt(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = "timeStamp";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r1.getColumnIndex(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r1.getLong(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r9 = "delete from ";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = r8.append(r9);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = r8.append(r5);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8 = r8.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r12.execSQL(r8);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x0068:
        r1.moveToNext();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        goto L_0x0032;
    L_0x006c:
        r0 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r3 = "save to system table error ";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = r2.append(r3);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r0.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r2.append(r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = r0.toString();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        com.umeng.analytics.pro.by.e(r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r1 == 0) goto L_0x0013;
    L_0x0089:
        r1.close();
        goto L_0x0013;
    L_0x008d:
        r8 = new android.content.ContentValues;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.<init>();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r5 = "key";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r9 = r0.c();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.put(r5, r9);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r9 = "count";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r4 != 0) goto L_0x00ce;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x009f:
        r4 = r0.e();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x00a3:
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.put(r9, r4);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r4 = "timeStamp";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r5 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r5 != 0) goto L_0x00b4;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x00b0:
        r2 = r0.d();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
    L_0x00b4:
        r0 = java.lang.Long.valueOf(r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r8.put(r4, r0);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = "system";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = 0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r12.insert(r0, r2, r8);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r0 = "success";	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r2 = 0;	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r14.a(r0, r2);	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        if (r1 == 0) goto L_0x0013;
    L_0x00c9:
        r1.close();
        goto L_0x0013;
    L_0x00ce:
        r4 = (long) r4;
        r10 = r0.e();	 Catch:{ SQLException -> 0x006c, all -> 0x00d5 }
        r4 = r4 + r10;
        goto L_0x00a3;
    L_0x00d5:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00db;
    L_0x00d8:
        r1.close();
    L_0x00db:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.a.a(android.database.sqlite.SQLiteDatabase, java.util.Map, com.umeng.analytics.pro.f):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void d(android.database.sqlite.SQLiteDatabase r2, java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0027 in list [B:3:0x0024]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r2.beginTransaction();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0.<init>();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r1 = "update system set count=count+1 where key like '";	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.append(r1);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.append(r3);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r1 = "'";	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.append(r1);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r0 = r0.toString();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r2.execSQL(r0);	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        r2.setTransactionSuccessful();	 Catch:{ SQLException -> 0x0028, all -> 0x002f }
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r2.endTransaction();
    L_0x0027:
        return;
    L_0x0028:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0027;
    L_0x002b:
        r2.endTransaction();
        goto L_0x0027;
    L_0x002f:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0035;
    L_0x0032:
        r2.endTransaction();
    L_0x0035:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.a.d(android.database.sqlite.SQLiteDatabase, java.lang.String):void");
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("drop table if exists " + str);
            return true;
        } catch (SQLException e) {
            by.e("delete table faild!");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            if (c(sQLiteDatabase, str) >= 0) {
                sQLiteDatabase.execSQL("delete from " + str);
            }
            return true;
        } catch (SQLException e) {
            by.e("cleanTableData faild!" + e.toString());
            return false;
        }
    }

    public static int c(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = sQLiteDatabase.rawQuery("select * from " + str, null);
            i = cursor.getCount();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            by.e("count error " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return i;
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, Collection<i> collection) {
        try {
            sQLiteDatabase.beginTransaction();
            if (c(sQLiteDatabase, com.umeng.analytics.pro.d.a.b) > 0) {
                b(sQLiteDatabase, com.umeng.analytics.pro.d.a.b);
            }
            for (i a : collection) {
                sQLiteDatabase.insert(com.umeng.analytics.pro.d.a.b, null, a(a));
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            return true;
        } catch (SQLException e) {
            by.e("insert to Aggregated cache table faild!");
            sQLiteDatabase.endTransaction();
            return false;
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    public static boolean a(f fVar, SQLiteDatabase sQLiteDatabase, Collection<i> collection) {
        try {
            sQLiteDatabase.beginTransaction();
            for (i a : collection) {
                sQLiteDatabase.insert(com.umeng.analytics.pro.d.a.a, null, a(a));
            }
            sQLiteDatabase.setTransactionSuccessful();
            b(sQLiteDatabase, com.umeng.analytics.pro.d.a.b);
            fVar.a("success", false);
            return true;
        } catch (SQLException e) {
            by.e("insert to Aggregated cache table faild!");
            return false;
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, f fVar) {
        try {
            sQLiteDatabase.beginTransaction();
            if (c(sQLiteDatabase, com.umeng.analytics.pro.d.a.b) <= 0) {
                fVar.a("faild", false);
                return false;
            }
            sQLiteDatabase.execSQL("insert into aggregated(key, count, value, totalTimestamp, timeWindowNum, label) select key, count, value, totalTimestamp, timeWindowNum, label from aggregated_cache");
            sQLiteDatabase.setTransactionSuccessful();
            b(sQLiteDatabase, com.umeng.analytics.pro.d.a.b);
            fVar.a("success", false);
            sQLiteDatabase.endTransaction();
            return true;
        } catch (SQLException e) {
            fVar.a(Boolean.valueOf(false), false);
            by.e("cacheToAggregatedTable happen " + e.toString());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private static ContentValues a(i iVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", iVar.a());
        contentValues.put("label", iVar.c());
        contentValues.put("count", Long.valueOf(iVar.g()));
        contentValues.put("value", Long.valueOf(iVar.f()));
        contentValues.put(com.umeng.analytics.pro.d.a.a.b, Long.valueOf(iVar.e()));
        contentValues.put(com.umeng.analytics.pro.d.a.a.f, iVar.h());
        return contentValues;
    }

    public static boolean b(SQLiteDatabase sQLiteDatabase, f fVar) {
        Cursor cursor = null;
        try {
            Map hashMap = new HashMap();
            cursor = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
            while (cursor.moveToNext()) {
                i iVar = new i();
                iVar.a(d.b(cursor.getString(cursor.getColumnIndex("key"))));
                iVar.b(d.b(cursor.getString(cursor.getColumnIndex("label"))));
                iVar.c((long) cursor.getInt(cursor.getColumnIndex("count")));
                iVar.b((long) cursor.getInt(cursor.getColumnIndex("value")));
                iVar.b(cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.d.a.a.f)));
                iVar.a(Long.parseLong(cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.d.a.a.b))));
                hashMap.put(d.b(cursor.getString(cursor.getColumnIndex("key"))), iVar);
            }
            if (hashMap.size() > 0) {
                fVar.a(hashMap, false);
            } else {
                fVar.a("faild", false);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException e) {
            fVar.a(Boolean.valueOf(false), false);
            by.e("cacheToMemory happen " + e.toString());
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, boolean z, f fVar) {
        b(sQLiteDatabase, c.a);
        b(sQLiteDatabase, com.umeng.analytics.pro.d.a.a);
        if (!z) {
            b(sQLiteDatabase, b.a);
            fVar.a("success", false);
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, long j, long j2) {
        try {
            int c = c(sQLiteDatabase, c.a);
            int c2 = n.a().c();
            ContentValues contentValues;
            if (c < c2) {
                contentValues = new ContentValues();
                contentValues.put("key", str);
                contentValues.put(com.umeng.analytics.pro.d.c.a.b, Long.valueOf(j2));
                contentValues.put("count", Long.valueOf(j));
                sQLiteDatabase.insert(c.a, null, contentValues);
            } else if (c == c2) {
                contentValues = new ContentValues();
                contentValues.put("key", com.umeng.analytics.a.x);
                contentValues.put(com.umeng.analytics.pro.d.c.a.b, Long.valueOf(System.currentTimeMillis()));
                contentValues.put("count", Integer.valueOf(1));
                sQLiteDatabase.insert(c.a, null, contentValues);
            } else {
                d(sQLiteDatabase, com.umeng.analytics.a.x);
            }
        } catch (SQLException e) {
        }
    }

    public static void a(f fVar, SQLiteDatabase sQLiteDatabase, List<String> list) {
        try {
            sQLiteDatabase.beginTransaction();
            if (c(sQLiteDatabase, b.a) > 0) {
                b(sQLiteDatabase, b.a);
            }
            for (String str : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(com.umeng.analytics.pro.d.b.a.a, str);
                sQLiteDatabase.insert(b.a, null, contentValues);
            }
            sQLiteDatabase.setTransactionSuccessful();
            fVar.a("success", false);
        } catch (SQLException e) {
            by.e("insertToLimitCKTable error " + e.toString());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public static String a(SQLiteDatabase sQLiteDatabase) {
        String valueOf;
        Object obj;
        SQLException sQLException;
        SQLException sQLException2;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            sQLiteDatabase.beginTransaction();
            if (c(sQLiteDatabase, com.umeng.analytics.pro.d.a.b) <= 0) {
                valueOf = String.valueOf(0);
                if (cursor != null) {
                    cursor.close();
                }
                sQLiteDatabase.endTransaction();
            } else {
                rawQuery = sQLiteDatabase.rawQuery("select * from aggregated_cache", null);
                try {
                    if (rawQuery.moveToLast()) {
                        valueOf = rawQuery.getString(rawQuery.getColumnIndex(com.umeng.analytics.pro.d.a.a.f));
                    } else {
                        obj = cursor;
                    }
                } catch (SQLException e) {
                    sQLException = e;
                    obj = cursor;
                    sQLException2 = sQLException;
                    try {
                        by.e("queryLastTimeWindowNumFromCache error " + sQLException2.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.endTransaction();
                        return valueOf;
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.endTransaction();
                        throw th;
                    }
                }
                try {
                    sQLiteDatabase.setTransactionSuccessful();
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    sQLiteDatabase.endTransaction();
                } catch (SQLException e2) {
                    sQLException2 = e2;
                    by.e("queryLastTimeWindowNumFromCache error " + sQLException2.toString());
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    sQLiteDatabase.endTransaction();
                    return valueOf;
                }
            }
        } catch (SQLException e3) {
            rawQuery = cursor;
            sQLException = e3;
            valueOf = cursor;
            sQLException2 = sQLException;
            by.e("queryLastTimeWindowNumFromCache error " + sQLException2.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.endTransaction();
            return valueOf;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.endTransaction();
            throw th;
        }
        return valueOf;
    }

    public static JSONObject b(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        SQLException e;
        Throwable th;
        Cursor cursor = null;
        try {
            if (c(sQLiteDatabase, com.umeng.analytics.pro.d.a.a) > 0) {
                rawQuery = sQLiteDatabase.rawQuery("select * from aggregated", null);
                try {
                    JSONObject jSONObject = new JSONObject();
                    while (rawQuery.moveToNext()) {
                        try {
                            JSONArray jSONArray;
                            String string = rawQuery.getString(rawQuery.getColumnIndex("key"));
                            if (jSONObject.has(string)) {
                                jSONArray = jSONObject.getJSONArray(string);
                                jSONObject.remove(string);
                            } else {
                                jSONArray = new JSONArray();
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put(x.aw, rawQuery.getLong(rawQuery.getColumnIndex("value")));
                            jSONObject2.put(x.ax, rawQuery.getLong(rawQuery.getColumnIndex(com.umeng.analytics.pro.d.a.a.b)));
                            jSONObject2.put(x.ay, Integer.parseInt(rawQuery.getString(rawQuery.getColumnIndex(com.umeng.analytics.pro.d.a.a.f))));
                            jSONObject2.put("count", rawQuery.getInt(rawQuery.getColumnIndex("count")));
                            jSONObject2.put(x.aA, d.a(rawQuery.getString(rawQuery.getColumnIndex("label"))));
                            jSONArray.put(jSONObject2);
                            jSONObject.put(string, jSONArray);
                        } catch (Exception e2) {
                        }
                    }
                    if (rawQuery == null) {
                        return jSONObject;
                    }
                    rawQuery.close();
                    return jSONObject;
                } catch (SQLException e3) {
                    e = e3;
                    try {
                        by.e("readAllAggregatedDataForUpload error " + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return cursor;
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return cursor;
        } catch (SQLException e4) {
            e = e4;
            rawQuery = cursor;
            by.e("readAllAggregatedDataForUpload error " + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            return cursor;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    public static JSONObject a(f fVar, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        SQLException sQLException;
        Throwable th;
        try {
            Cursor rawQuery;
            JSONObject jSONObject = new JSONObject();
            if (c(sQLiteDatabase, c.a) > 0) {
                rawQuery = sQLiteDatabase.rawQuery("select * from system", null);
                while (rawQuery.moveToNext()) {
                    try {
                        try {
                            JSONArray jSONArray;
                            String string = rawQuery.getString(rawQuery.getColumnIndex("key"));
                            if (jSONObject.has(string)) {
                                jSONArray = jSONObject.getJSONArray(string);
                                jSONObject.remove(string);
                            } else {
                                jSONArray = new JSONArray();
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("value", rawQuery.getInt(rawQuery.getColumnIndex("count")));
                            jSONObject2.put("ts", rawQuery.getLong(rawQuery.getColumnIndex(com.umeng.analytics.pro.d.c.a.b)));
                            jSONArray.put(jSONObject2);
                            jSONObject.put(string, jSONArray);
                        } catch (Exception e) {
                        }
                    } catch (SQLException e2) {
                        SQLException sQLException2 = e2;
                        cursor = rawQuery;
                        sQLException = sQLException2;
                    } catch (Throwable th2) {
                        cursor = rawQuery;
                        th = th2;
                    }
                }
            } else {
                rawQuery = null;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return jSONObject;
        } catch (SQLException e3) {
            sQLException = e3;
            cursor = null;
            try {
                fVar.a("faild", false);
                by.e("readAllSystemDataForUpload error " + sQLException.toString());
                if (cursor == null) {
                    return null;
                }
                cursor.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static List<String> c(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        SQLException e;
        Throwable th;
        Cursor cursor = null;
        try {
            if (c(sQLiteDatabase, b.a) > 0) {
                rawQuery = sQLiteDatabase.rawQuery("select * from limitedck", null);
                try {
                    List<String> arrayList = new ArrayList();
                    while (rawQuery.moveToNext()) {
                        arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(com.umeng.analytics.pro.d.b.a.a)));
                    }
                    if (rawQuery == null) {
                        return arrayList;
                    }
                    rawQuery.close();
                    return arrayList;
                } catch (SQLException e2) {
                    e = e2;
                    try {
                        by.e("loadLimitCKFromDB error " + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return cursor;
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return cursor;
        } catch (SQLException e3) {
            e = e3;
            rawQuery = cursor;
            by.e("loadLimitCKFromDB error " + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            return cursor;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }
}
