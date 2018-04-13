package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ae {
    public static boolean a = false;
    private static ae b = null;
    private static af c = null;

    class a extends Thread {
        final /* synthetic */ ae a;
        private int b;
        private ad c;
        private String d;
        private ContentValues e;
        private boolean f;
        private String[] g;
        private String h;
        private String[] i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String[] o;
        private int p;
        private String q;
        private byte[] r;

        public a(ae aeVar, int i, ad adVar) {
            this.a = aeVar;
            this.b = i;
            this.c = adVar;
        }

        public void a(String str, ContentValues contentValues) {
            this.d = str;
            this.e = contentValues;
        }

        public void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.f = z;
            this.d = str;
            this.g = strArr;
            this.h = str2;
            this.i = strArr2;
            this.j = str3;
            this.k = str4;
            this.l = str5;
            this.m = str6;
        }

        public void a(String str, String str2, String[] strArr) {
            this.d = str;
            this.n = str2;
            this.o = strArr;
        }

        public void a(int i, String str, byte[] bArr) {
            this.p = i;
            this.q = str;
            this.r = bArr;
        }

        public void a(int i) {
            this.p = i;
        }

        public void a(int i, String str) {
            this.p = i;
            this.q = str;
        }

        public void run() {
            switch (this.b) {
                case 1:
                    this.a.a(this.d, this.e, this.c);
                    return;
                case 2:
                    this.a.a(this.d, this.n, this.o, this.c);
                    return;
                case 3:
                    this.a.a(this.f, this.d, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.c);
                    return;
                case 4:
                    this.a.a(this.p, this.q, this.r, this.c);
                    return;
                case 5:
                    this.a.a(this.p, this.c);
                    return;
                case 6:
                    this.a.a(this.p, this.q, this.c);
                    return;
                default:
                    return;
            }
        }
    }

    private ae(Context context, List<com.tencent.bugly.a> list) {
        c = new af(context, list);
    }

    public static synchronized ae a(Context context, List<com.tencent.bugly.a> list) {
        ae aeVar;
        synchronized (ae.class) {
            if (b == null) {
                b = new ae(context, list);
            }
            aeVar = b;
        }
        return aeVar;
    }

    public static synchronized ae a() {
        ae aeVar;
        synchronized (ae.class) {
            aeVar = b;
        }
        return aeVar;
    }

    public long a(String str, ContentValues contentValues, ad adVar, boolean z) {
        if (z) {
            return a(str, contentValues, adVar);
        }
        Runnable aVar = new a(this, 1, adVar);
        aVar.a(str, contentValues);
        am.a().a(aVar);
        return 0;
    }

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, ad adVar, boolean z) {
        return a(false, str, strArr, str2, strArr2, null, null, null, null, adVar, z);
    }

    public Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, ad adVar, boolean z2) {
        if (z2) {
            return a(z, str, strArr, str2, strArr2, str3, str4, str5, str6, adVar);
        }
        Runnable aVar = new a(this, 3, adVar);
        aVar.a(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
        am.a().a(aVar);
        return null;
    }

    public int a(String str, String str2, String[] strArr, ad adVar, boolean z) {
        if (z) {
            return a(str, str2, strArr, adVar);
        }
        Runnable aVar = new a(this, 2, adVar);
        aVar.a(str, str2, strArr);
        am.a().a(aVar);
        return 0;
    }

    private synchronized long a(String str, ContentValues contentValues, ad adVar) {
        long j = 0;
        synchronized (this) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = c.getWritableDatabase();
                if (!(sQLiteDatabase == null || contentValues == null)) {
                    long replace = sQLiteDatabase.replace(str, "_id", contentValues);
                    if (replace >= 0) {
                        an.c("[Database] insert %s success.", new Object[]{str});
                    } else {
                        an.d("[Database] replace %s error.", new Object[]{str});
                    }
                    j = replace;
                }
                if (adVar != null) {
                    adVar.a(Long.valueOf(j));
                }
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th) {
                if (adVar != null) {
                    adVar.a(Long.valueOf(0));
                }
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
        return j;
    }

    private synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, ad adVar) {
        Cursor query;
        try {
            SQLiteDatabase writableDatabase = c.getWritableDatabase();
            if (writableDatabase != null) {
                query = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            } else {
                query = null;
            }
            if (adVar != null) {
                adVar.a(query);
            }
        } catch (Throwable th) {
            if (adVar != null) {
                adVar.a(null);
            }
        }
        return query;
    }

    private synchronized int a(String str, String str2, String[] strArr, ad adVar) {
        int i;
        i = 0;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c.getWritableDatabase();
            if (sQLiteDatabase != null) {
                i = sQLiteDatabase.delete(str, str2, strArr);
            }
            if (adVar != null) {
                adVar.a(Integer.valueOf(i));
            }
            if (a && sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
        } catch (Throwable th) {
            if (adVar != null) {
                adVar.a(Integer.valueOf(0));
            }
            if (a && sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
        }
        return i;
    }

    public boolean a(int i, String str, byte[] bArr, ad adVar, boolean z) {
        if (z) {
            return a(i, str, bArr, adVar);
        }
        Runnable aVar = new a(this, 4, adVar);
        aVar.a(i, str, bArr);
        am.a().a(aVar);
        return true;
    }

    public Map<String, byte[]> a(int i, ad adVar, boolean z) {
        if (z) {
            return a(i, adVar);
        }
        Runnable aVar = new a(this, 5, adVar);
        aVar.a(i);
        am.a().a(aVar);
        return null;
    }

    public boolean a(int i, String str, ad adVar, boolean z) {
        if (z) {
            return a(i, str, adVar);
        }
        Runnable aVar = new a(this, 6, adVar);
        aVar.a(i, str);
        am.a().a(aVar);
        return false;
    }

    private boolean a(int i, String str, byte[] bArr, ad adVar) {
        boolean z = false;
        try {
            ag agVar = new ag();
            agVar.a = (long) i;
            agVar.f = str;
            agVar.e = System.currentTimeMillis();
            agVar.g = bArr;
            z = d(agVar);
            if (adVar != null) {
                adVar.a(Boolean.valueOf(z));
            }
        } catch (Throwable th) {
            if (adVar != null) {
                adVar.a(Boolean.valueOf(z));
            }
        }
        return z;
    }

    private Map<String, byte[]> a(int i, ad adVar) {
        Map<String, byte[]> map;
        Throwable th;
        Throwable th2;
        Object hashMap;
        try {
            List<ag> c = c(i);
            if (c != null) {
                hashMap = new HashMap();
                try {
                    for (ag agVar : c) {
                        Object obj = agVar.g;
                        if (obj != null) {
                            hashMap.put(agVar.f, obj);
                        }
                    }
                    map = hashMap;
                } catch (Throwable th3) {
                    th = th3;
                    if (adVar != null) {
                        adVar.a(hashMap);
                    }
                    throw th;
                }
            }
            map = null;
            if (adVar != null) {
                adVar.a(map);
            }
        } catch (Throwable th4) {
            th = th4;
            hashMap = null;
            if (adVar != null) {
                adVar.a(hashMap);
            }
            throw th;
        }
        return map;
    }

    public synchronized boolean a(ag agVar) {
        boolean z = false;
        synchronized (this) {
            if (agVar != null) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    sQLiteDatabase = c.getWritableDatabase();
                    if (sQLiteDatabase != null) {
                        ContentValues b = b(agVar);
                        if (b != null) {
                            long replace = sQLiteDatabase.replace("t_lr", "_id", b);
                            if (replace >= 0) {
                                an.c("[Database] insert %s success.", new Object[]{"t_lr"});
                                agVar.a = replace;
                                if (a && sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                z = true;
                            } else if (a && sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                        }
                    }
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                }
            }
        }
        return z;
    }

    private synchronized boolean d(ag agVar) {
        boolean z = false;
        synchronized (this) {
            if (agVar != null) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    sQLiteDatabase = c.getWritableDatabase();
                    if (sQLiteDatabase != null) {
                        ContentValues c = c(agVar);
                        if (c != null) {
                            long replace = sQLiteDatabase.replace("t_pf", "_id", c);
                            if (replace >= 0) {
                                an.c("[Database] insert %s success.", new Object[]{"t_pf"});
                                agVar.a = replace;
                                if (a && sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                z = true;
                            } else if (a && sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                        }
                    }
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                }
            }
        }
        return z;
    }

    public synchronized List<ag> a(int i) {
        Throwable th;
        Cursor cursor;
        List<ag> list;
        SQLiteDatabase writableDatabase = c.getWritableDatabase();
        if (writableDatabase != null) {
            String str;
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a && writableDatabase != null) {
                        writableDatabase.close();
                    }
                    throw th;
                }
            }
            str = null;
            cursor = writableDatabase.query("t_lr", null, str, null, null, null, null);
            if (cursor == null) {
                if (cursor != null) {
                    cursor.close();
                }
                if (a && writableDatabase != null) {
                    writableDatabase.close();
                }
                list = null;
            } else {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    List<ag> arrayList = new ArrayList();
                    while (cursor.moveToNext()) {
                        ag a = a(cursor);
                        if (a != null) {
                            arrayList.add(a);
                        } else {
                            stringBuilder.append(" or ").append("_id").append(" = ").append(cursor.getLong(cursor.getColumnIndex("_id")));
                        }
                    }
                    str = stringBuilder.toString();
                    if (str.length() > 0) {
                        int delete = writableDatabase.delete("t_lr", str.substring(" or ".length()), null);
                        an.d("[Database] deleted %s illegal data %d", new Object[]{"t_lr", Integer.valueOf(delete)});
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a && writableDatabase != null) {
                        writableDatabase.close();
                    }
                    list = arrayList;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
        list = null;
        return list;
    }

    public synchronized void a(List<ag> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = c.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (ag agVar : list) {
                        stringBuilder.append(" or ").append("_id").append(" = ").append(agVar.a);
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        stringBuilder2 = stringBuilder2.substring(" or ".length());
                    }
                    stringBuilder.setLength(0);
                    try {
                        int delete = writableDatabase.delete("t_lr", stringBuilder2, null);
                        an.c("[Database] deleted %s data %d", new Object[]{"t_lr", Integer.valueOf(delete)});
                        if (a) {
                            writableDatabase.close();
                        }
                    } catch (Throwable th) {
                        if (a) {
                            writableDatabase.close();
                        }
                    }
                }
            }
        }
    }

    public synchronized void b(int i) {
        String str = null;
        synchronized (this) {
            SQLiteDatabase writableDatabase = c.getWritableDatabase();
            if (writableDatabase != null) {
                if (i >= 0) {
                    try {
                        str = "_tp = " + i;
                    } catch (Throwable th) {
                        if (a && writableDatabase != null) {
                            writableDatabase.close();
                        }
                    }
                }
                int delete = writableDatabase.delete("t_lr", str, null);
                an.c("[Database] deleted %s data %d", new Object[]{"t_lr", Integer.valueOf(delete)});
                if (a && writableDatabase != null) {
                    writableDatabase.close();
                }
            }
        }
    }

    protected ContentValues b(ag agVar) {
        if (agVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (agVar.a > 0) {
                contentValues.put("_id", Long.valueOf(agVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(agVar.b));
            contentValues.put("_pc", agVar.c);
            contentValues.put("_th", agVar.d);
            contentValues.put("_tm", Long.valueOf(agVar.e));
            if (agVar.g != null) {
                contentValues.put("_dt", agVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (an.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    protected ag a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            ag agVar = new ag();
            agVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            agVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            agVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            agVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            agVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            agVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return agVar;
        } catch (Throwable th) {
            if (an.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private synchronized List<ag> c(int i) {
        List<ag> list;
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        synchronized (this) {
            Cursor cursor = null;
            Cursor query;
            try {
                SQLiteDatabase writableDatabase = c.getWritableDatabase();
                if (writableDatabase != null) {
                    try {
                        String str = "_id = " + i;
                        query = writableDatabase.query("t_pf", null, str, null, null, null, null);
                        if (query == null) {
                            if (query != null) {
                                query.close();
                            }
                            if (a && writableDatabase != null) {
                                writableDatabase.close();
                            }
                            list = null;
                        } else {
                            try {
                                StringBuilder stringBuilder = new StringBuilder();
                                List<ag> arrayList = new ArrayList();
                                while (query.moveToNext()) {
                                    ag b = b(query);
                                    if (b != null) {
                                        arrayList.add(b);
                                    } else {
                                        try {
                                            stringBuilder.append(" or ").append("_tp").append(" = ").append(query.getString(query.getColumnIndex("_tp")));
                                        } catch (Throwable th2) {
                                            sQLiteDatabase2 = writableDatabase;
                                            th = th2;
                                        }
                                    }
                                }
                                if (stringBuilder.length() > 0) {
                                    stringBuilder.append(" and ").append("_id").append(" = ").append(i);
                                    int delete = writableDatabase.delete("t_pf", str.substring(" or ".length()), null);
                                    an.d("[Database] deleted %s illegal data %d.", new Object[]{"t_pf", Integer.valueOf(delete)});
                                }
                                if (query != null) {
                                    query.close();
                                }
                                if (a && writableDatabase != null) {
                                    writableDatabase.close();
                                }
                                list = arrayList;
                            } catch (Throwable th22) {
                                sQLiteDatabase2 = writableDatabase;
                                th = th22;
                            }
                        }
                    } catch (Throwable th222) {
                        query = null;
                        sQLiteDatabase2 = writableDatabase;
                        th = th222;
                        if (query != null) {
                            query.close();
                        }
                        if (a && sQLiteDatabase2 != null) {
                            sQLiteDatabase2.close();
                        }
                        throw th;
                    }
                }
                if (null != null) {
                    cursor.close();
                }
                if (a && writableDatabase != null) {
                    writableDatabase.close();
                }
                list = null;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                sQLiteDatabase2.close();
                throw th;
            }
        }
        return list;
    }

    private synchronized boolean a(int i, String str, ad adVar) {
        SQLiteDatabase sQLiteDatabase = null;
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            try {
                sQLiteDatabase = c.getWritableDatabase();
                if (sQLiteDatabase != null) {
                    String str2;
                    if (ap.a(str)) {
                        str2 = "_id = " + i;
                    } else {
                        str2 = "_id = " + i + " and " + "_tp" + " = \"" + str + "\"";
                    }
                    an.c("[Database] deleted %s data %d", new Object[]{"t_pf", Integer.valueOf(sQLiteDatabase.delete("t_pf", str2, null))});
                    if (sQLiteDatabase.delete("t_pf", str2, null) <= 0) {
                        z = false;
                    }
                    z2 = z;
                }
                if (adVar != null) {
                    adVar.a(Boolean.valueOf(z2));
                }
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th) {
                if (adVar != null) {
                    adVar.a(Boolean.valueOf(false));
                }
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
        return z2;
    }

    protected ContentValues c(ag agVar) {
        if (agVar == null || ap.a(agVar.f)) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (agVar.a > 0) {
                contentValues.put("_id", Long.valueOf(agVar.a));
            }
            contentValues.put("_tp", agVar.f);
            contentValues.put("_tm", Long.valueOf(agVar.e));
            if (agVar.g == null) {
                return contentValues;
            }
            contentValues.put("_dt", agVar.g);
            return contentValues;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    protected ag b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            ag agVar = new ag();
            agVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            agVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            agVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            agVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return agVar;
        } catch (Throwable th) {
            if (an.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
