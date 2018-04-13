package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.k;
import java.util.ArrayList;
import java.util.Iterator;

public class g {
    private static volatile g a;
    private f b;

    private g(Context context) {
        this.b = new f(context);
    }

    private synchronized Cursor a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        synchronized (this) {
            k.a(false);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM geoMessage", null);
            } catch (Exception e) {
                b.d(e.toString());
            }
        }
        return cursor;
    }

    public static g a(Context context) {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new g(context);
                }
            }
        }
        return a;
    }

    public synchronized int a(String str) {
        int i = 0;
        synchronized (this) {
            k.a(false);
            if (!TextUtils.isEmpty(str)) {
                try {
                    SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
                    int delete = writableDatabase.delete("geoMessage", "message_id = ?", new String[]{str});
                    writableDatabase.close();
                    i = delete;
                } catch (Exception e) {
                    b.d(e.toString());
                }
            }
        }
        return i;
    }

    public synchronized ArrayList<com.xiaomi.push.service.module.b> a() {
        ArrayList<com.xiaomi.push.service.module.b> arrayList;
        k.a(false);
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            Cursor a = a(writableDatabase);
            arrayList = new ArrayList();
            if (a != null) {
                while (a.moveToNext()) {
                    com.xiaomi.push.service.module.b bVar = new com.xiaomi.push.service.module.b();
                    bVar.a(a.getString(a.getColumnIndex("message_id")));
                    bVar.b(a.getString(a.getColumnIndex("geo_id")));
                    bVar.a(a.getBlob(a.getColumnIndex("content")));
                    bVar.a(a.getInt(a.getColumnIndex("action")));
                    bVar.a(a.getLong(a.getColumnIndex("deadline")));
                    arrayList.add(bVar);
                }
                a.close();
            }
            writableDatabase.close();
        } catch (Exception e) {
            b.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized boolean a(ArrayList<ContentValues> arrayList) {
        boolean z;
        k.a(false);
        if (arrayList == null || arrayList.size() <= 0) {
            z = false;
        } else {
            try {
                SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
                writableDatabase.beginTransaction();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (-1 == writableDatabase.insert("geoMessage", null, (ContentValues) it.next())) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    writableDatabase.setTransactionSuccessful();
                }
                writableDatabase.endTransaction();
                writableDatabase.close();
            } catch (Exception e) {
                b.d(e.toString());
                z = false;
            }
        }
        return z;
    }

    public synchronized int b(String str) {
        int i = 0;
        synchronized (this) {
            k.a(false);
            if (!TextUtils.isEmpty(str)) {
                try {
                    SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
                    int delete = writableDatabase.delete("geoMessage", "geo_id = ?", new String[]{str});
                    writableDatabase.close();
                    i = delete;
                } catch (Exception e) {
                    b.d(e.toString());
                }
            }
        }
        return i;
    }

    public synchronized ArrayList<com.xiaomi.push.service.module.b> c(String str) {
        ArrayList<com.xiaomi.push.service.module.b> arrayList;
        k.a(false);
        if (TextUtils.isEmpty(str)) {
            arrayList = null;
        } else {
            try {
                ArrayList a = a();
                ArrayList<com.xiaomi.push.service.module.b> arrayList2 = new ArrayList();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    com.xiaomi.push.service.module.b bVar = (com.xiaomi.push.service.module.b) it.next();
                    if (TextUtils.equals(bVar.c(), str)) {
                        arrayList2.add(bVar);
                    }
                }
                arrayList = arrayList2;
            } catch (Exception e) {
                b.d(e.toString());
                arrayList = null;
            }
        }
        return arrayList;
    }
}
