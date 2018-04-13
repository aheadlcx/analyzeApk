package com.alipay.sdk.tid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.encrypt.b;
import java.lang.ref.WeakReference;

public final class a extends SQLiteOpenHelper {
    private WeakReference<Context> a;

    private static boolean a(android.database.sqlite.SQLiteDatabase r7, java.lang.String r8, java.lang.String r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x002d in list []
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
        r0 = 1;
        r1 = 0;
        r3 = "select count(*) from tb_tid where name=?";
        r2 = 0;
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0027, all -> 0x002f }
        r5 = 0;	 Catch:{ Exception -> 0x0027, all -> 0x002f }
        r6 = c(r8, r9);	 Catch:{ Exception -> 0x0027, all -> 0x002f }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0027, all -> 0x002f }
        r2 = r7.rawQuery(r3, r4);	 Catch:{ Exception -> 0x0027, all -> 0x002f }
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x0027, all -> 0x002f }
        if (r3 == 0) goto L_0x0038;	 Catch:{ Exception -> 0x0027, all -> 0x002f }
    L_0x0019:
        r3 = 0;	 Catch:{ Exception -> 0x0027, all -> 0x002f }
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x0027, all -> 0x002f }
    L_0x001e:
        if (r2 == 0) goto L_0x0023;
    L_0x0020:
        r2.close();
    L_0x0023:
        r2 = r3;
    L_0x0024:
        if (r2 <= 0) goto L_0x0036;
    L_0x0026:
        return r0;
    L_0x0027:
        r3 = move-exception;
        if (r2 == 0) goto L_0x002d;
    L_0x002a:
        r2.close();
    L_0x002d:
        r2 = r1;
        goto L_0x0024;
    L_0x002f:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0035;
    L_0x0032:
        r2.close();
    L_0x0035:
        throw r0;
    L_0x0036:
        r0 = r1;
        goto L_0x0026;
    L_0x0038:
        r3 = r1;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x001f in list [B:6:0x0016]
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
        r7 = this;
        r1 = 0;
        r2 = 0;
        r1 = r7.getWritableDatabase();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = a(r1, r8, r9);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 == 0) goto L_0x0020;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x000c:
        r0 = r7;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r2 = r8;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = r9;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = r10;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = r11;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0014:
        if (r1 == 0) goto L_0x001f;
    L_0x0016:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r1.close();
    L_0x001f:
        return;
    L_0x0020:
        r3 = "insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))";	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r7.a;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r0.get();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = com.alipay.sdk.util.a.c(r0);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = com.alipay.sdk.encrypt.b.a(r4, r10, r0);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 3;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = c(r8, r9);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r5] = r0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = 2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r0] = r11;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r1.execSQL(r3, r4);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = "select name from tb_tid where tid!='' order by dt asc";	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = r1.rawQuery(r0, r3);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r3.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 14;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 > r4) goto L_0x0066;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0055:
        r3.close();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        goto L_0x0014;
    L_0x0059:
        r0 = move-exception;
        if (r1 == 0) goto L_0x001f;
    L_0x005c:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x001f;
    L_0x0062:
        r1.close();
        goto L_0x001f;
    L_0x0066:
        r0 = r3.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = r0 + -14;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r3.moveToFirst();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 == 0) goto L_0x0086;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0074:
        r0 = r2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0075:
        r6 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = r3.getString(r6);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5[r0] = r6;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r0 + 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = r3.moveToNext();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r6 == 0) goto L_0x0086;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0084:
        if (r4 > r0) goto L_0x0075;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0086:
        r3.close();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x008a:
        if (r0 >= r4) goto L_0x0014;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x008c:
        r2 = r5[r0];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r2 != 0) goto L_0x0099;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0094:
        r2 = r5[r0];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        a(r1, r2);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0099:
        r0 = r0 + 1;
        goto L_0x008a;
    L_0x009c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00a8;
    L_0x009f:
        r2 = r1.isOpen();
        if (r2 == 0) goto L_0x00a8;
    L_0x00a5:
        r1.close();
    L_0x00a8:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public a(Context context) {
        super(context, "msp.db", null, 1);
        this.a = new WeakReference(context);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
        onCreate(sQLiteDatabase);
    }

    public final String a(String str, String str2) {
        Cursor rawQuery;
        Throwable th;
        String str3 = null;
        String str4 = "select tid from tb_tid where name=?";
        SQLiteDatabase readableDatabase;
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery(str4, new String[]{c(str, str2)});
                try {
                    if (rawQuery.moveToFirst()) {
                        str3 = rawQuery.getString(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                    str4 = str3;
                } catch (Exception e) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    str4 = null;
                    if (TextUtils.isEmpty(str4)) {
                        return str4;
                    }
                    return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    throw th;
                }
            } catch (Exception e2) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                str4 = null;
                if (TextUtils.isEmpty(str4)) {
                    return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
                }
                return str4;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            str4 = null;
            if (TextUtils.isEmpty(str4)) {
                return str4;
            }
            return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th;
        }
        if (TextUtils.isEmpty(str4)) {
            return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
        }
        return str4;
    }

    public final String b(String str, String str2) {
        Throwable th;
        String str3 = null;
        String str4 = "select key_tid from tb_tid where name=?";
        SQLiteDatabase readableDatabase;
        Cursor rawQuery;
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery(str4, new String[]{c(str, str2)});
                try {
                    if (rawQuery.moveToFirst()) {
                        str3 = rawQuery.getString(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                } catch (Exception e) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    return str3;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    throw th;
                }
            } catch (Exception e2) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                return str3;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            return str3;
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th;
        }
        return str3;
    }

    static String c(String str, String str2) {
        return str + str2;
    }

    final void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[]{b.a(1, str3, com.alipay.sdk.util.a.c((Context) this.a.get())), str4, c(str, str2)});
    }

    static void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.delete("tb_tid", "name=?", new String[]{str});
        } catch (Exception e) {
        }
    }
}
