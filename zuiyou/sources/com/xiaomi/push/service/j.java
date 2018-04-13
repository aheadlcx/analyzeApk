package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.tencent.tauth.AuthActivity;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.m;
import com.xiaomi.push.service.module.a;
import java.util.ArrayList;
import java.util.Iterator;

public class j {
    private static volatile j a;
    private Context b;

    private j(Context context) {
        this.b = context;
    }

    private synchronized Cursor a(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        m.a(false);
        try {
            query = sQLiteDatabase.query("geoMessage", null, null, null, null, null, null);
        } catch (Exception e) {
            b.d(e.toString());
            query = null;
        }
        return query;
    }

    public static j a(Context context) {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new j(context);
                }
            }
        }
        return a;
    }

    public synchronized int a(String str) {
        int i = 0;
        synchronized (this) {
            m.a(false);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int delete = i.a(this.b).a().delete("geoMessage", "message_id = ?", new String[]{str});
                    i.a(this.b).b();
                    i = delete;
                } catch (Exception e) {
                    b.d(e.toString());
                }
            }
        }
        return i;
    }

    public synchronized ArrayList<a> a() {
        ArrayList<a> arrayList;
        m.a(false);
        try {
            Cursor a = a(i.a(this.b).a());
            arrayList = new ArrayList();
            if (a != null) {
                while (a.moveToNext()) {
                    a aVar = new a();
                    aVar.a(a.getString(a.getColumnIndex("message_id")));
                    aVar.b(a.getString(a.getColumnIndex("geo_id")));
                    aVar.a(a.getBlob(a.getColumnIndex("content")));
                    aVar.a(a.getInt(a.getColumnIndex(AuthActivity.ACTION_KEY)));
                    aVar.a(a.getLong(a.getColumnIndex("deadline")));
                    arrayList.add(aVar);
                }
                a.close();
            }
            i.a(this.b).b();
        } catch (Exception e) {
            b.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized boolean a(ArrayList<ContentValues> arrayList) {
        boolean z;
        m.a(false);
        if (arrayList == null || arrayList.size() <= 0) {
            z = false;
        } else {
            try {
                SQLiteDatabase a = i.a(this.b).a();
                a.beginTransaction();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (-1 == a.insert("geoMessage", null, (ContentValues) it.next())) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    a.setTransactionSuccessful();
                }
                a.endTransaction();
                i.a(this.b).b();
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
            m.a(false);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int delete = i.a(this.b).a().delete("geoMessage", "geo_id = ?", new String[]{str});
                    i.a(this.b).b();
                    i = delete;
                } catch (Exception e) {
                    b.d(e.toString());
                }
            }
        }
        return i;
    }

    public synchronized ArrayList<a> c(String str) {
        ArrayList<a> arrayList;
        m.a(false);
        if (TextUtils.isEmpty(str)) {
            arrayList = null;
        } else {
            try {
                ArrayList a = a();
                ArrayList<a> arrayList2 = new ArrayList();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (TextUtils.equals(aVar.c(), str)) {
                        arrayList2.add(aVar);
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
