package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class p {
    private static p a = null;
    private static q b = null;
    private static boolean c = false;

    class a extends Thread {
        private int a;
        private o b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;
        private /* synthetic */ p r;

        public a(p pVar, int i, o oVar) {
            this.r = pVar;
            this.a = i;
            this.b = oVar;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.e = z;
            this.c = str;
            this.f = strArr;
            this.g = str2;
            this.h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        public final void a(int i, String str, byte[] bArr) {
            this.o = i;
            this.p = str;
            this.q = bArr;
        }

        public final void run() {
            switch (this.a) {
                case 1:
                    this.r.a(this.c, this.d, this.b);
                    return;
                case 2:
                    this.r.a(this.c, this.m, this.n, this.b);
                    return;
                case 3:
                    this.r.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    return;
                case 4:
                    this.r.a(this.o, this.p, this.q, this.b);
                    return;
                case 5:
                    this.r.a(this.o, this.b);
                    return;
                case 6:
                    this.r.a(this.o, this.p, this.b);
                    return;
                default:
                    return;
            }
        }
    }

    private p(Context context, List<com.tencent.bugly.a> list) {
        b = new q(context, list);
    }

    public static synchronized p a(Context context, List<com.tencent.bugly.a> list) {
        p pVar;
        synchronized (p.class) {
            if (a == null) {
                a = new p(context, list);
            }
            pVar = a;
        }
        return pVar;
    }

    public static synchronized p a() {
        p pVar;
        synchronized (p.class) {
            pVar = a;
        }
        return pVar;
    }

    public final long a(String str, ContentValues contentValues, o oVar, boolean z) {
        return a(str, contentValues, null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, o oVar, boolean z) {
        return a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    public final int a(String str, String str2, String[] strArr, o oVar, boolean z) {
        return a(str, str2, null, null);
    }

    private synchronized long a(String str, ContentValues contentValues, o oVar) {
        long j = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (!(writableDatabase == null || contentValues == null)) {
                    long replace = writableDatabase.replace(str, FileDownloadModel.ID, contentValues);
                    if (replace >= 0) {
                        x.c("[Database] insert %s success.", str);
                    } else {
                        x.d("[Database] replace %s error.", str);
                    }
                    j = replace;
                }
                if (oVar != null) {
                    Long.valueOf(j);
                }
            } catch (Throwable th) {
                if (oVar != null) {
                    Long.valueOf(0);
                }
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized android.database.Cursor a(boolean r12, java.lang.String r13, java.lang.String[] r14, java.lang.String r15, java.lang.String[] r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, com.tencent.bugly.proguard.o r21) {
        /*
        r11 = this;
        monitor-enter(r11);
        r10 = 0;
        r0 = b;	 Catch:{ Throwable -> 0x0020 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0020 }
        if (r0 == 0) goto L_0x0035;
    L_0x000a:
        r1 = r12;
        r2 = r13;
        r3 = r14;
        r4 = r15;
        r5 = r16;
        r6 = r17;
        r7 = r18;
        r8 = r19;
        r9 = r20;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x0020 }
    L_0x001c:
        if (r21 == 0) goto L_0x001e;
    L_0x001e:
        monitor-exit(r11);
        return r0;
    L_0x0020:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.x.a(r0);	 Catch:{ all -> 0x002e }
        if (r1 != 0) goto L_0x002a;
    L_0x0027:
        r0.printStackTrace();	 Catch:{ all -> 0x002e }
    L_0x002a:
        if (r21 == 0) goto L_0x0033;
    L_0x002c:
        r0 = r10;
        goto L_0x001e;
    L_0x002e:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0030 }
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x0033:
        r0 = r10;
        goto L_0x001e;
    L_0x0035:
        r0 = r10;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(boolean, java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.bugly.proguard.o):android.database.Cursor");
    }

    private synchronized int a(String str, String str2, String[] strArr, o oVar) {
        int i = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    i = writableDatabase.delete(str, str2, strArr);
                }
                if (oVar != null) {
                    Integer.valueOf(i);
                }
            } catch (Throwable th) {
                if (oVar != null) {
                    Integer.valueOf(0);
                }
            }
        }
        return i;
    }

    public final boolean a(int i, String str, byte[] bArr, o oVar, boolean z) {
        if (z) {
            return a(i, str, bArr, null);
        }
        Runnable aVar = new a(this, 4, null);
        aVar.a(i, str, bArr);
        w.a().a(aVar);
        return true;
    }

    public final Map<String, byte[]> a(int i, o oVar, boolean z) {
        return a(i, null);
    }

    public final boolean a(int i, String str, o oVar, boolean z) {
        return a(555, str, null);
    }

    private boolean a(int i, String str, byte[] bArr, o oVar) {
        boolean z = false;
        try {
            r rVar = new r();
            rVar.a = (long) i;
            rVar.f = str;
            rVar.e = System.currentTimeMillis();
            rVar.g = bArr;
            z = b(rVar);
            if (oVar != null) {
                Boolean.valueOf(z);
            }
        } catch (Throwable th) {
            if (oVar != null) {
                Boolean.valueOf(z);
            }
        }
        return z;
    }

    private Map<String, byte[]> a(int i, o oVar) {
        Map<String, byte[]> map;
        Throwable th;
        try {
            List<r> c = c(i);
            if (c != null) {
                Map<String, byte[]> hashMap = new HashMap();
                try {
                    for (r rVar : c) {
                        Object obj = rVar.g;
                        if (obj != null) {
                            hashMap.put(rVar.f, obj);
                        }
                    }
                    map = hashMap;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    map = hashMap;
                    th = th3;
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    return oVar == null ? map : map;
                }
            } else {
                map = null;
            }
            if (oVar != null) {
            }
        } catch (Throwable th22) {
            th = th22;
            map = null;
            if (x.a(th)) {
                th.printStackTrace();
            }
            if (oVar == null) {
            }
        }
    }

    public final synchronized boolean a(r rVar) {
        boolean z = false;
        synchronized (this) {
            if (rVar != null) {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null) {
                        ContentValues c = c(rVar);
                        if (c != null) {
                            long replace = writableDatabase.replace("t_lr", FileDownloadModel.ID, c);
                            if (replace >= 0) {
                                x.c("[Database] insert %s success.", "t_lr");
                                rVar.a = replace;
                                z = true;
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    private synchronized boolean b(r rVar) {
        boolean z = false;
        synchronized (this) {
            if (rVar != null) {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null) {
                        ContentValues d = d(rVar);
                        if (d != null) {
                            long replace = writableDatabase.replace("t_pf", FileDownloadModel.ID, d);
                            if (replace >= 0) {
                                x.c("[Database] insert %s success.", "t_pf");
                                rVar.a = replace;
                                z = true;
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public final synchronized List<r> a(int i) {
        Throwable th;
        Cursor cursor;
        List<r> list;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            String str;
            Cursor cursor2;
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = null;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            str = null;
            cursor2 = writableDatabase.query("t_lr", null, str, null, null, null, null);
            if (cursor2 == null) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                list = null;
            } else {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    List<r> arrayList = new ArrayList();
                    while (cursor2.moveToNext()) {
                        r a = a(cursor2);
                        if (a != null) {
                            arrayList.add(a);
                        } else {
                            try {
                                stringBuilder.append(" or _id").append(" = ").append(cursor2.getLong(cursor2.getColumnIndex(FileDownloadModel.ID)));
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        }
                    }
                    str = stringBuilder.toString();
                    if (str.length() > 0) {
                        int delete = writableDatabase.delete("t_lr", str.substring(4), null);
                        x.d("[Database] deleted %s illegal data %d", "t_lr", Integer.valueOf(delete));
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    list = arrayList;
                } catch (Throwable th32) {
                    th = th32;
                }
            }
        }
        list = null;
        return list;
    }

    public final synchronized void a(List<r> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (r rVar : list) {
                        stringBuilder.append(" or _id").append(" = ").append(rVar.a);
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        stringBuilder2 = stringBuilder2.substring(4);
                    }
                    stringBuilder.setLength(0);
                    try {
                        int delete = writableDatabase.delete("t_lr", stringBuilder2, null);
                        x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(delete));
                    } catch (Throwable th) {
                        if (!x.a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public final synchronized void b(int i) {
        String str = null;
        synchronized (this) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                if (i >= 0) {
                    try {
                        str = "_tp = " + i;
                    } catch (Throwable th) {
                        if (!x.a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
                int delete = writableDatabase.delete("t_lr", str, null);
                x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(delete));
            }
        }
    }

    private static ContentValues c(r rVar) {
        if (rVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put(FileDownloadModel.ID, Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(rVar.b));
            contentValues.put("_pc", rVar.c);
            contentValues.put("_th", rVar.d);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static r a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.ID));
            rVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            rVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            rVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private synchronized List<r> c(int i) {
        Cursor query;
        List<r> list;
        Throwable th;
        Cursor cursor;
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                String str = "_id = " + i;
                query = writableDatabase.query("t_pf", null, str, null, null, null, null);
                if (query == null) {
                    if (query != null) {
                        query.close();
                    }
                    list = null;
                } else {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        List<r> arrayList = new ArrayList();
                        while (query.moveToNext()) {
                            r b = b(query);
                            if (b != null) {
                                arrayList.add(b);
                            } else {
                                try {
                                    stringBuilder.append(" or _tp").append(" = ").append(query.getString(query.getColumnIndex("_tp")));
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            }
                        }
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append(" and _id").append(" = ").append(i);
                            int delete = writableDatabase.delete("t_pf", str.substring(4), null);
                            x.d("[Database] deleted %s illegal data %d.", "t_pf", Integer.valueOf(delete));
                        }
                        if (query != null) {
                            query.close();
                        }
                        list = arrayList;
                    } catch (Throwable th22) {
                        th = th22;
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        list = null;
        return list;
    }

    private synchronized boolean a(int i, String str, o oVar) {
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    String str2;
                    if (z.a(str)) {
                        str2 = "_id = " + i;
                    } else {
                        str2 = "_id = " + i + " and _tp" + " = \"" + str + "\"";
                    }
                    x.c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str2, null)));
                    if (writableDatabase.delete("t_pf", str2, null) <= 0) {
                        z = false;
                    }
                    z2 = z;
                }
                if (oVar != null) {
                    Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                if (oVar != null) {
                    Boolean.valueOf(false);
                }
            }
        }
        return z2;
    }

    private static ContentValues d(r rVar) {
        if (rVar == null || z.a(rVar.f)) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put(FileDownloadModel.ID, Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", rVar.f);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g == null) {
                return contentValues;
            }
            contentValues.put("_dt", rVar.g);
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static r b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex(FileDownloadModel.ID));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
