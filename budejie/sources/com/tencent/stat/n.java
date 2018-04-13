package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Handler;
import android.os.HandlerThread;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.tencent.open.GameAppOperation;
import com.tencent.stat.a.e;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class n {
    private static StatLogger e = k.b();
    private static n f = null;
    Handler a = null;
    volatile int b = 0;
    DeviceInfo c = null;
    private w d;
    private HashMap<String, String> g = new HashMap();

    private n(Context context) {
        try {
            HandlerThread handlerThread = new HandlerThread("StatStore");
            handlerThread.start();
            e.w("Launch store thread:" + handlerThread);
            this.a = new Handler(handlerThread.getLooper());
            Context applicationContext = context.getApplicationContext();
            this.d = new w(applicationContext);
            this.d.getWritableDatabase();
            this.d.getReadableDatabase();
            b(applicationContext);
            c();
            f();
            this.a.post(new o(this));
        } catch (Object th) {
            e.e(th);
        }
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (f == null) {
                f = new n(context);
            }
            nVar = f;
        }
        return nVar;
    }

    public static n b() {
        return f;
    }

    private synchronized void b(int i) {
        try {
            if (this.b > 0 && i > 0) {
                e.i("Load " + Integer.toString(this.b) + " unsent events");
                List arrayList = new ArrayList();
                List<x> arrayList2 = new ArrayList();
                if (i == -1 || i > StatConfig.a()) {
                    i = StatConfig.a();
                }
                this.b -= i;
                c(arrayList2, i);
                e.i("Peek " + Integer.toString(arrayList2.size()) + " unsent events.");
                if (!arrayList2.isEmpty()) {
                    b((List) arrayList2, 2);
                    for (x xVar : arrayList2) {
                        arrayList.add(xVar.b);
                    }
                    d.b().b(arrayList, new u(this, arrayList2, i));
                }
            }
        } catch (Object th) {
            e.e(th);
        }
    }

    private synchronized void b(e eVar, c cVar) {
        if (StatConfig.getMaxStoreEventCount() > 0) {
            try {
                this.d.getWritableDatabase().beginTransaction();
                if (this.b > StatConfig.getMaxStoreEventCount()) {
                    e.warn("Too many events stored in db.");
                    this.b -= this.d.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
                }
                ContentValues contentValues = new ContentValues();
                String c = k.c(eVar.d());
                contentValues.put("content", c);
                contentValues.put("send_count", "0");
                contentValues.put("status", Integer.toString(1));
                contentValues.put("timestamp", Long.valueOf(eVar.b()));
                if (this.d.getWritableDatabase().insert("events", null, contentValues) == -1) {
                    e.error("Failed to store event:" + c);
                } else {
                    this.b++;
                    this.d.getWritableDatabase().setTransactionSuccessful();
                    if (cVar != null) {
                        cVar.a();
                    }
                }
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
            }
        }
        return;
    }

    private synchronized void b(b bVar) {
        Cursor query;
        Object obj;
        Throwable th;
        try {
            long update;
            String a = bVar.a();
            String a2 = k.a(a);
            ContentValues contentValues = new ContentValues();
            contentValues.put("content", bVar.b.toString());
            contentValues.put("md5sum", a2);
            bVar.c = a2;
            contentValues.put(GameAppOperation.QQFAV_DATALINE_VERSION, Integer.valueOf(bVar.d));
            query = this.d.getReadableDatabase().query("config", null, null, null, null, null, null);
            do {
                try {
                    if (!query.moveToNext()) {
                        obj = null;
                        break;
                    }
                } catch (Throwable th2) {
                    obj = th2;
                }
            } while (query.getInt(0) != bVar.a);
            obj = 1;
            if (1 == obj) {
                update = (long) this.d.getWritableDatabase().update("config", contentValues, "type=?", new String[]{Integer.toString(bVar.a)});
            } else {
                contentValues.put("type", Integer.valueOf(bVar.a));
                update = this.d.getWritableDatabase().insert("config", null, contentValues);
            }
            if (update == -1) {
                e.e("Failed to store cfg:" + a);
            } else {
                e.d("Sucessed to store cfg:" + a);
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private synchronized void b(List<x> list) {
        try {
            e.i("Delete " + list.size() + " sent events in thread:" + Thread.currentThread());
            try {
                this.d.getWritableDatabase().beginTransaction();
                for (x xVar : list) {
                    this.b -= this.d.getWritableDatabase().delete("events", "event_id = ?", new String[]{Long.toString(xVar.a)});
                }
                this.d.getWritableDatabase().setTransactionSuccessful();
                this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
                this.d.getWritableDatabase().endTransaction();
            } catch (Throwable th) {
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Exception e) {
                    e.e(e);
                }
                throw th;
            }
        } catch (Exception e2) {
            e.e(e2);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    private synchronized void b(List<x> list, int i) {
        try {
            e.i("Update " + list.size() + " sending events to status:" + i + " in thread:" + Thread.currentThread());
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("status", Integer.toString(i));
                this.d.getWritableDatabase().beginTransaction();
                for (x xVar : list) {
                    if (xVar.d + 1 > StatConfig.getMaxSendRetryCount()) {
                        this.b -= this.d.getWritableDatabase().delete("events", "event_id=?", new String[]{Long.toString(xVar.a)});
                    } else {
                        contentValues.put("send_count", Integer.valueOf(xVar.d + 1));
                        e.i("Update event:" + xVar.a + " for content:" + contentValues);
                        int update = this.d.getWritableDatabase().update("events", contentValues, "event_id=?", new String[]{Long.toString(xVar.a)});
                        if (update <= 0) {
                            e.e("Failed to update db, error code:" + Integer.toString(update));
                        }
                    }
                }
                this.d.getWritableDatabase().setTransactionSuccessful();
                this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
                this.d.getWritableDatabase().endTransaction();
            } catch (Throwable th) {
                try {
                    this.d.getWritableDatabase().endTransaction();
                } catch (Exception e) {
                    e.e(e);
                }
                throw th;
            }
        } catch (Exception e2) {
            e.e(e2);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    private void c(List<x> list, int i) {
        Object th;
        Cursor cursor;
        Throwable th2;
        Cursor cursor2 = null;
        try {
            Cursor query = this.d.getReadableDatabase().query("events", null, "status=?", new String[]{Integer.toString(1)}, null, null, "event_id", Integer.toString(i));
            while (query.moveToNext()) {
                try {
                    list.add(new x(query.getLong(0), k.d(query.getString(1)), query.getInt(2), query.getInt(3)));
                } catch (Throwable th3) {
                    th2 = th3;
                    cursor2 = query;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th4) {
            th2 = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th2;
        }
    }

    private void e() {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", Integer.valueOf(1));
            this.d.getWritableDatabase().update("events", contentValues, "status=?", new String[]{Long.toString(2)});
            this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
            e.i("Total " + this.b + " unsent events.");
        } catch (Object th) {
            e.e(th);
        }
    }

    private void f() {
        Object th;
        Throwable th2;
        Cursor query;
        try {
            query = this.d.getReadableDatabase().query("keyvalues", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    this.g.put(query.getString(0), query.getString(1));
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th4) {
            th2 = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    public int a() {
        return this.b;
    }

    void a(int i) {
        this.a.post(new v(this, i));
    }

    void a(e eVar, c cVar) {
        if (StatConfig.isEnableStatService()) {
            try {
                if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                    b(eVar, cVar);
                } else {
                    this.a.post(new r(this, eVar, cVar));
                }
            } catch (Object th) {
                e.e(th);
            }
        }
    }

    void a(b bVar) {
        if (bVar != null) {
            this.a.post(new s(this, bVar));
        }
    }

    void a(List<x> list) {
        try {
            if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                b((List) list);
            } else {
                this.a.post(new q(this, list));
            }
        } catch (Exception e) {
            e.e(e);
        }
    }

    void a(List<x> list, int i) {
        try {
            if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
                b((List) list, i);
            } else {
                this.a.post(new p(this, list, i));
            }
        } catch (Object th) {
            e.e(th);
        }
    }

    public synchronized DeviceInfo b(Context context) {
        DeviceInfo deviceInfo;
        Cursor query;
        Cursor cursor;
        Throwable th;
        if (this.c != null) {
            deviceInfo = this.c;
        } else {
            Object obj;
            try {
                query = this.d.getReadableDatabase().query("user", null, null, null, null, null, null, null);
                obj = null;
                try {
                    String d;
                    String str;
                    String l;
                    String str2 = "";
                    if (query.moveToNext()) {
                        Object obj2;
                        d = k.d(query.getString(0));
                        int i = query.getInt(1);
                        str2 = query.getString(2);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        int i2 = (i == 1 || k.a(query.getLong(3) * 1000).equals(k.a(1000 * currentTimeMillis))) ? i : 1;
                        int i3 = !str2.equals(k.r(context)) ? i2 | 2 : i2;
                        String[] split = d.split(",");
                        if (split == null || split.length <= 0) {
                            str2 = k.b(context);
                            d = str2;
                            str = str2;
                            int i4 = 1;
                        } else {
                            str2 = split[0];
                            if (str2 == null || str2.length() < 11) {
                                l = k.l(context);
                                if (l == null || l.length() <= 10) {
                                    l = str2;
                                    obj2 = null;
                                } else {
                                    obj2 = 1;
                                }
                                str = d;
                                d = l;
                            } else {
                                String str3 = str2;
                                obj2 = null;
                                str = d;
                                d = str3;
                            }
                        }
                        if (split == null || split.length < 2) {
                            l = k.c(context);
                            if (l != null && l.length() > 0) {
                                str = d + "," + l;
                                obj2 = 1;
                            }
                        } else {
                            l = split[1];
                            str = d + "," + l;
                        }
                        this.c = new DeviceInfo(d, l, i3);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(HistoryOpenHelper.COLUMN_UID, k.c(str));
                        contentValues.put("user_type", Integer.valueOf(i3));
                        contentValues.put("app_ver", k.r(context));
                        contentValues.put("ts", Long.valueOf(currentTimeMillis));
                        if (obj2 != null) {
                            this.d.getWritableDatabase().update("user", contentValues, "uid=?", new String[]{r10});
                        }
                        if (i3 != i) {
                            this.d.getWritableDatabase().replace("user", null, contentValues);
                            obj = 1;
                        } else {
                            i2 = 1;
                        }
                    }
                    if (obj == null) {
                        str2 = k.b(context);
                        str = k.c(context);
                        l = (str == null || str.length() <= 0) ? str2 : str2 + "," + str;
                        long currentTimeMillis2 = System.currentTimeMillis() / 1000;
                        d = k.r(context);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put(HistoryOpenHelper.COLUMN_UID, k.c(l));
                        contentValues2.put("user_type", Integer.valueOf(0));
                        contentValues2.put("app_ver", d);
                        contentValues2.put("ts", Long.valueOf(currentTimeMillis2));
                        this.d.getWritableDatabase().insert("user", null, contentValues2);
                        this.c = new DeviceInfo(str2, str, 0);
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
            deviceInfo = this.c;
        }
        return deviceInfo;
    }

    void c() {
        this.a.post(new t(this));
    }
}
