package com.alibaba.mtl.log.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.mtl.log.e.i;
import com.qq.e.comm.constants.Constants.KEYS;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class b implements a {
    a a;
    String aa = "SELECT * FROM %s ORDER BY %s ASC LIMIT %s";
    String ab = "SELECT count(*) FROM %s";
    String ac = "DELETE FROM log where _id in ( select _id from log  ORDER BY _id ASC LIMIT %d )";

    class a extends SQLiteOpenHelper {
        private SQLiteDatabase a;
        /* renamed from: a */
        final /* synthetic */ b f37a;
        private AtomicInteger e = new AtomicInteger();

        a(b bVar, Context context) {
            this.f37a = bVar;
            super(context, "ut.db", null, 2);
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery;
            Cursor cursor = null;
            try {
                rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=DELETE", null);
            } catch (Throwable th) {
                rawQuery = th;
            } finally {
                this.f37a.a(
/*
Method generation error in method: com.alibaba.mtl.log.c.b.a.onOpen(android.database.sqlite.SQLiteDatabase):void, dex: classes2.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001b: INVOKE  (wrap: com.alibaba.mtl.log.c.b
  0x0019: IGET  (r1_1 com.alibaba.mtl.log.c.b) = (r3_0 'this' com.alibaba.mtl.log.c.b$a) com.alibaba.mtl.log.c.b.a.a com.alibaba.mtl.log.c.b), (wrap: android.database.Cursor
  ?: MERGE  (r2_1 android.database.Cursor) = (r2_0 'cursor' android.database.Cursor), (r0_4 'rawQuery' android.database.Cursor)) com.alibaba.mtl.log.c.b.a(com.alibaba.mtl.log.c.b, android.database.Cursor):void type: STATIC in method: com.alibaba.mtl.log.c.b.a.onOpen(android.database.sqlite.SQLiteDatabase):void, dex: classes2.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:203)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:299)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:233)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:219)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0000: INVOKE  (wrap: com.alibaba.mtl.log.c.b
  0x0019: IGET  (r1_1 com.alibaba.mtl.log.c.b) = (r3_0 'this' com.alibaba.mtl.log.c.b$a) com.alibaba.mtl.log.c.b.a.a com.alibaba.mtl.log.c.b), (wrap: android.database.Cursor
  ?: MERGE  (r2_1 android.database.Cursor) = (r2_0 'cursor' android.database.Cursor), (r0_4 'rawQuery' android.database.Cursor)) com.alibaba.mtl.log.c.b.a(android.database.Cursor):void type: DIRECT in method: com.alibaba.mtl.log.c.b.a.onOpen(android.database.sqlite.SQLiteDatabase):void, dex: classes2.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:777)
	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:617)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:338)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 24 more
Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: MERGE  (r2_1 android.database.Cursor) = (r2_0 'cursor' android.database.Cursor), (r0_4 'rawQuery' android.database.Cursor) in method: com.alibaba.mtl.log.c.b.a.onOpen(android.database.sqlite.SQLiteDatabase):void, dex: classes2.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:101)
	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:686)
	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:656)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:338)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:211)
	... 28 more
Caused by: jadx.core.utils.exceptions.CodegenException: MERGE can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:211)
	... 33 more

*/

                public void onCreate(SQLiteDatabase sQLiteDatabase) {
                    sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS log (_id INTEGER PRIMARY KEY AUTOINCREMENT, eventId TEXT,priority TEXT, streamId TEXT, time TEXT, content TEXT, _index TEXT )");
                }

                public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                    if (i == 1 && i2 == 2) {
                        try {
                            sQLiteDatabase.execSQL("ALTER TABLE log ADD COLUMN _index TEXT ");
                        } catch (Throwable th) {
                            i.a("UTSqliteLogStore", "DB Upgrade Error", th);
                        }
                    }
                }

                public synchronized SQLiteDatabase getWritableDatabase() {
                    try {
                        if (this.e.incrementAndGet() == 1) {
                            this.a = super.getWritableDatabase();
                        }
                    } catch (Throwable th) {
                        i.a("TAG", AppLinkConstants.E, th);
                        com.alibaba.mtl.appmonitor.b.b.a(th);
                    }
                    return this.a;
                }

                public synchronized void a(SQLiteDatabase sQLiteDatabase) {
                    if (sQLiteDatabase != null) {
                        try {
                            if (this.e.decrementAndGet() == 0 && this.a != null) {
                                this.a.close();
                                this.a = null;
                            }
                        } catch (Throwable th) {
                        }
                    }
                }
            }

            protected b(Context context) {
                this.a = new a(this, context);
            }

            /* renamed from: a */
            public synchronized boolean m24a(List<com.alibaba.mtl.log.model.a> list) {
                boolean z;
                Throwable th;
                Throwable th2;
                SQLiteDatabase sQLiteDatabase;
                if (list != null) {
                    if (list.size() != 0) {
                        SQLiteDatabase sQLiteDatabase2 = null;
                        try {
                            sQLiteDatabase2 = this.a.getWritableDatabase();
                            if (sQLiteDatabase2 != null) {
                                try {
                                    sQLiteDatabase2.beginTransaction();
                                    int i = 0;
                                    while (i < list.size()) {
                                        try {
                                            com.alibaba.mtl.log.model.a aVar = (com.alibaba.mtl.log.model.a) list.get(i);
                                            if (aVar != null) {
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("eventId", aVar.T);
                                                contentValues.put("priority", aVar.U);
                                                contentValues.put("content", aVar.i());
                                                contentValues.put(AppLinkConstants.TIME, aVar.W);
                                                contentValues.put("_index", aVar.X);
                                                if (sQLiteDatabase2.insert("log", "", contentValues) == -1) {
                                                    z = false;
                                                    break;
                                                }
                                                i.a("UTSqliteLogStore", "[insert] ", aVar.X, " isSuccess:", Boolean.valueOf(true), KEYS.RET, Long.valueOf(sQLiteDatabase2.insert("log", "", contentValues)));
                                            }
                                            i++;
                                        } catch (Throwable th3) {
                                            th2 = th3;
                                        }
                                    }
                                    z = true;
                                } catch (Throwable th32) {
                                    th2 = th32;
                                }
                            } else {
                                i.a("UTSqliteLogStore", "db is null");
                                z = false;
                            }
                            if (sQLiteDatabase2 != null) {
                                try {
                                    sQLiteDatabase2.setTransactionSuccessful();
                                } catch (Throwable th4) {
                                }
                                try {
                                    sQLiteDatabase2.endTransaction();
                                } catch (Throwable th5) {
                                }
                            }
                            this.a.a(sQLiteDatabase2);
                        } catch (Throwable th322) {
                            th2 = th322;
                        }
                    }
                }
                z = true;
                return z;
            }

            public synchronized int a(List<com.alibaba.mtl.log.model.a> list) {
                SQLiteDatabase writableDatabase;
                boolean z = true;
                int i = 0;
                synchronized (this) {
                    if (list != null) {
                        if (list.size() != 0) {
                            writableDatabase = this.a.getWritableDatabase();
                            if (writableDatabase != null) {
                                try {
                                    writableDatabase.beginTransaction();
                                    int i2 = 0;
                                    int i3 = 0;
                                    while (i2 < list.size()) {
                                        boolean z2;
                                        int i4;
                                        if (((long) writableDatabase.delete("log", "_id=?", new String[]{((com.alibaba.mtl.log.model.a) list.get(i2)).id + ""})) <= 0) {
                                            i.a("UTSqliteLogStore", "[delete]  ", Integer.valueOf(((com.alibaba.mtl.log.model.a) list.get(i2)).id), " ret:", Long.valueOf((long) writableDatabase.delete("log", "_id=?", new String[]{((com.alibaba.mtl.log.model.a) list.get(i2)).id + ""})));
                                            z2 = false;
                                            i4 = i3;
                                        } else if ("6005".equalsIgnoreCase(((com.alibaba.mtl.log.model.a) list.get(i2)).T)) {
                                            z2 = z;
                                            i4 = i3;
                                        } else {
                                            boolean z3 = z;
                                            i4 = i3 + 1;
                                            z2 = z3;
                                        }
                                        i2++;
                                        i3 = i4;
                                        z = z2;
                                    }
                                    try {
                                        writableDatabase.setTransactionSuccessful();
                                    } catch (Throwable th) {
                                    }
                                    try {
                                        writableDatabase.endTransaction();
                                    } catch (Throwable th2) {
                                    }
                                    this.a.a(writableDatabase);
                                    i = i3;
                                } catch (Throwable th3) {
                                }
                            } else {
                                i.a("UTSqliteLogStore", "db is null");
                                z = false;
                            }
                            i.a("UTSqliteLogStore", "delete ", Integer.valueOf(list.size()), " isSuccess:", Boolean.valueOf(z));
                        }
                    }
                }
                return i;
                this.a.a(writableDatabase);
                writableDatabase.endTransaction();
                this.a.a(writableDatabase);
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized java.util.ArrayList<com.alibaba.mtl.log.model.a> a(java.lang.String r10, int r11) {
                /*
                r9 = this;
                r2 = 0;
                monitor-enter(r9);
                if (r11 > 0) goto L_0x000a;
            L_0x0004:
                r0 = java.util.Collections.EMPTY_LIST;	 Catch:{ Throwable -> 0x0148, all -> 0x012c }
                r0 = (java.util.ArrayList) r0;	 Catch:{ Throwable -> 0x0148, all -> 0x012c }
            L_0x0008:
                monitor-exit(r9);
                return r0;
            L_0x000a:
                r0 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x0148, all -> 0x012c }
                r0.<init>(r11);	 Catch:{ Throwable -> 0x0148, all -> 0x012c }
                r1 = r9.a;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r3 = r1.getWritableDatabase();	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                if (r3 == 0) goto L_0x0139;
            L_0x0017:
                r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r1.<init>();	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r4 = "SELECT * FROM ";
                r4 = r1.append(r4);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5 = "log";
                r4.append(r5);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r4 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                if (r4 != 0) goto L_0x0036;
            L_0x002d:
                r4 = " WHERE ";
                r4 = r1.append(r4);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r4.append(r10);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
            L_0x0036:
                r4 = " ORDER BY ";
                r4 = r1.append(r4);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5 = "time";
                r4 = r4.append(r5);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5 = " ASC ";
                r4.append(r5);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r4 = " LIMIT ";
                r4 = r1.append(r4);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5.<init>();	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5 = r5.append(r11);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r6 = "";
                r5 = r5.append(r6);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5 = r5.toString();	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r4.append(r5);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r1 = r1.toString();	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r4 = "UTSqliteLogStore";
                r5 = 1;
                r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r6 = 0;
                r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r7.<init>();	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r8 = "sql:";
                r7 = r7.append(r8);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r7 = r7.append(r1);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r7 = r7.toString();	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r5[r6] = r7;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                com.alibaba.mtl.log.e.i.a(r4, r5);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r4 = 0;
                r2 = r3.rawQuery(r1, r4);	 Catch:{ Throwable -> 0x010d }
            L_0x008a:
                if (r2 == 0) goto L_0x0122;
            L_0x008c:
                r1 = r2.moveToNext();	 Catch:{ Throwable -> 0x010d }
                if (r1 == 0) goto L_0x0122;
            L_0x0092:
                r1 = new com.alibaba.mtl.log.model.a;	 Catch:{ Throwable -> 0x010d }
                r1.<init>();	 Catch:{ Throwable -> 0x010d }
                r4 = "UTSqliteLogStore";
                r5 = 4;
                r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x010d }
                r6 = 0;
                r7 = "pos";
                r5[r6] = r7;	 Catch:{ Throwable -> 0x010d }
                r6 = 1;
                r7 = r2.getPosition();	 Catch:{ Throwable -> 0x010d }
                r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Throwable -> 0x010d }
                r5[r6] = r7;	 Catch:{ Throwable -> 0x010d }
                r6 = 2;
                r7 = "count";
                r5[r6] = r7;	 Catch:{ Throwable -> 0x010d }
                r6 = 3;
                r7 = r2.getCount();	 Catch:{ Throwable -> 0x010d }
                r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Throwable -> 0x010d }
                r5[r6] = r7;	 Catch:{ Throwable -> 0x010d }
                com.alibaba.mtl.log.e.i.a(r4, r5);	 Catch:{ Throwable -> 0x010d }
                r4 = "_id";
                r4 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x010d }
                r4 = r2.getInt(r4);	 Catch:{ Throwable -> 0x010d }
                r1.id = r4;	 Catch:{ Throwable -> 0x010d }
                r4 = "eventId";
                r4 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x010d }
                r4 = r2.getString(r4);	 Catch:{ Throwable -> 0x010d }
                r1.T = r4;	 Catch:{ Throwable -> 0x010d }
                r4 = "priority";
                r4 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x010d }
                r4 = r2.getString(r4);	 Catch:{ Throwable -> 0x010d }
                r1.U = r4;	 Catch:{ Throwable -> 0x010d }
                r4 = "content";
                r4 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x010d }
                r4 = r2.getString(r4);	 Catch:{ Throwable -> 0x010d }
                r1.k(r4);	 Catch:{ Throwable -> 0x010d }
                r4 = "time";
                r4 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x010d }
                r4 = r2.getString(r4);	 Catch:{ Throwable -> 0x010d }
                r1.W = r4;	 Catch:{ Throwable -> 0x010d }
                r4 = "_index";
                r4 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x014c }
                r4 = r2.getString(r4);	 Catch:{ Throwable -> 0x014c }
                r1.X = r4;	 Catch:{ Throwable -> 0x014c }
            L_0x0108:
                r0.add(r1);	 Catch:{ Throwable -> 0x010d }
                goto L_0x008a;
            L_0x010d:
                r1 = move-exception;
                r4 = "UTSqliteLogStore";
                r5 = "[get]";
                com.alibaba.mtl.log.e.i.a(r4, r5, r1);	 Catch:{ all -> 0x012f }
                r9.a(r2);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r1 = r9.a;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r1.a(r3);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                goto L_0x0008;
            L_0x011f:
                r1 = move-exception;
                goto L_0x0008;
            L_0x0122:
                r9.a(r2);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r1 = r9.a;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r1.a(r3);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                goto L_0x0008;
            L_0x012c:
                r0 = move-exception;
                monitor-exit(r9);
                throw r0;
            L_0x012f:
                r1 = move-exception;
                r9.a(r2);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r2 = r9.a;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r2.a(r3);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                throw r1;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
            L_0x0139:
                r1 = "UTSqliteLogStore";
                r2 = 1;
                r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                r3 = 0;
                r4 = "db is null";
                r2[r3] = r4;	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                com.alibaba.mtl.log.e.i.a(r1, r2);	 Catch:{ Throwable -> 0x011f, all -> 0x012c }
                goto L_0x0008;
            L_0x0148:
                r0 = move-exception;
                r0 = r2;
                goto L_0x0008;
            L_0x014c:
                r4 = move-exception;
                goto L_0x0108;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.c.b.a(java.lang.String, int):java.util.ArrayList<com.alibaba.mtl.log.model.a>");
            }

            public synchronized int g() {
                int i = 0;
                synchronized (this) {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    if (writableDatabase != null) {
                        try {
                            Cursor rawQuery = writableDatabase.rawQuery(String.format(this.ab, new Object[]{"log"}), null);
                            if (rawQuery != null) {
                                rawQuery.moveToFirst();
                                i = rawQuery.getInt(0);
                            }
                            a(rawQuery);
                            this.a.a(writableDatabase);
                        } catch (Throwable th) {
                            a(null);
                            this.a.a(writableDatabase);
                        }
                    } else {
                        i.a("UTSqliteLogStore", "db is null");
                    }
                }
                return i;
            }

            public synchronized void clear() {
                SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                if (writableDatabase != null) {
                    writableDatabase.delete("log", null, null);
                    this.a.a(writableDatabase);
                }
            }

            public synchronized void c(String str, String str2) {
                SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                if (writableDatabase != null) {
                    try {
                        writableDatabase.delete("log", str + " < ?", new String[]{String.valueOf(str2)});
                        this.a.a(writableDatabase);
                    } catch (Throwable th) {
                        this.a.a(writableDatabase);
                    }
                } else {
                    i.a("UTSqliteLogStore", "db is null");
                }
            }

            public void e(int i) {
                if (i > 0) {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    if (writableDatabase != null) {
                        try {
                            writableDatabase.execSQL(String.format(this.ac, new Object[]{Integer.valueOf(i)}));
                        } catch (Throwable th) {
                        } finally {
                            this.a.a(writableDatabase);
                        }
                    } else {
                        i.a("UTSqliteLogStore", "db is null");
                    }
                }
            }

            private void a(Cursor cursor) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th) {
                    }
                }
            }
        }
