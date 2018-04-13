package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.k;
import com.xiaomi.xmpush.thrift.h;
import com.xiaomi.xmpush.thrift.j;
import com.xiaomi.xmpush.thrift.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private static volatile e b;
    private static String c = "GeoFenceDao.";
    private f a;

    private e(Context context) {
        this.a = new f(context);
    }

    private synchronized Cursor a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        synchronized (this) {
            k.a(false);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM geofence", null);
            } catch (Exception e) {
            }
        }
        return cursor;
    }

    public static e a(Context context) {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = new e(context);
                }
            }
        }
        return b;
    }

    private synchronized com.xiaomi.xmpush.thrift.k a(Cursor cursor) {
        com.xiaomi.xmpush.thrift.k kVar;
        try {
            for (com.xiaomi.xmpush.thrift.k kVar2 : com.xiaomi.xmpush.thrift.k.values()) {
                if (TextUtils.equals(cursor.getString(cursor.getColumnIndex("type")), kVar2.name())) {
                    break;
                }
            }
            kVar2 = null;
        } catch (Exception e) {
            b.d(e.toString());
            kVar2 = null;
        }
        return kVar2;
    }

    private synchronized String a(List<l> list) {
        String jSONArray;
        if (list != null) {
            if (list.size() >= 3) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    for (l lVar : list) {
                        if (lVar != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("point_lantitude", lVar.c());
                            jSONObject.put("point_longtitude", lVar.a());
                            jSONArray2.put(jSONObject);
                        }
                    }
                    jSONArray = jSONArray2.toString();
                } catch (JSONException e) {
                    b.d(e.toString());
                    jSONArray = null;
                }
            }
        }
        b.a(c + " points unvalidated");
        jSONArray = null;
        return jSONArray;
    }

    private synchronized l b(Cursor cursor) {
        l lVar;
        lVar = new l();
        try {
            lVar.b(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_lantitude"))));
            lVar.a(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_longtitude"))));
        } catch (Exception e) {
            b.d(e.toString());
            lVar = null;
        }
        return lVar;
    }

    private synchronized ArrayList<l> c(Cursor cursor) {
        ArrayList<l> arrayList;
        ArrayList<l> arrayList2 = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(cursor.getString(cursor.getColumnIndex("polygon_points")));
            for (int i = 0; i < jSONArray.length(); i++) {
                l lVar = new l();
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                lVar.b(jSONObject.getDouble("point_lantitude"));
                lVar.a(jSONObject.getDouble("point_longtitude"));
                arrayList2.add(lVar);
            }
            arrayList = arrayList2;
        } catch (JSONException e) {
            b.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    private synchronized h d(Cursor cursor) {
        h valueOf;
        try {
            valueOf = h.valueOf(cursor.getString(cursor.getColumnIndex("coordinate_provider")));
        } catch (IllegalArgumentException e) {
            b.d(e.toString());
            valueOf = null;
        }
        return valueOf;
    }

    public synchronized int a(String str, String str2) {
        int i = 0;
        synchronized (this) {
            k.a(false);
            try {
                if ("Enter".equals(str2) || "Leave".equals(str2) || "Unknown".equals(str2)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("current_status", str2);
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    int update = writableDatabase.update("geofence", contentValues, "id=?", new String[]{str});
                    writableDatabase.close();
                    i = update;
                }
            } catch (Exception e) {
                b.d(e.toString());
            }
        }
        return i;
    }

    public synchronized long a(j jVar) {
        long insert;
        k.a(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", jVar.a());
            contentValues.put("appId", Long.valueOf(jVar.e()));
            contentValues.put("name", jVar.c());
            contentValues.put("package_name", jVar.g());
            contentValues.put("create_time", Long.valueOf(jVar.i()));
            contentValues.put("type", jVar.k().name());
            contentValues.put("center_longtitude", String.valueOf(jVar.m().a()));
            contentValues.put("center_lantitude", String.valueOf(jVar.m().c()));
            contentValues.put("circle_radius", Double.valueOf(jVar.o()));
            contentValues.put("polygon_point", a(jVar.q()));
            contentValues.put("coordinate_provider", jVar.s().name());
            contentValues.put("current_status", "Unknown");
            SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
            insert = writableDatabase.insert("geofence", null, contentValues);
            writableDatabase.close();
        } catch (Exception e) {
            b.d(e.toString());
            insert = -1;
        }
        return insert;
    }

    public synchronized j a(String str) {
        j jVar;
        k.a(false);
        try {
            Iterator it = a().iterator();
            while (it.hasNext()) {
                jVar = (j) it.next();
                if (TextUtils.equals(jVar.a(), str)) {
                    break;
                }
            }
            jVar = null;
        } catch (Exception e) {
            b.d(e.toString());
            jVar = null;
        }
        return jVar;
    }

    public synchronized ArrayList<j> a() {
        ArrayList<j> arrayList;
        k.a(false);
        try {
            SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
            Cursor a = a(writableDatabase);
            arrayList = new ArrayList();
            if (a != null) {
                while (a.moveToNext()) {
                    try {
                        j jVar = new j();
                        jVar.a(a.getString(a.getColumnIndex("id")));
                        jVar.b(a.getString(a.getColumnIndex("name")));
                        jVar.a((long) a.getInt(a.getColumnIndex("appId")));
                        jVar.c(a.getString(a.getColumnIndex("package_name")));
                        jVar.b((long) a.getInt(a.getColumnIndex("create_time")));
                        com.xiaomi.xmpush.thrift.k a2 = a(a);
                        if (a2 == null) {
                            b.c(c + "findAllGeoFencing: geo type null");
                        } else {
                            jVar.a(a2);
                            if (TextUtils.equals("Circle", a2.name())) {
                                jVar.a(b(a));
                                jVar.a(a.getDouble(a.getColumnIndex("circle_radius")));
                            } else if (TextUtils.equals("Polygon", a2.name())) {
                                List c = c(a);
                                if (c == null || c.size() < 3) {
                                    b.c(c + "findAllGeoFencing: geo points null or size<3");
                                } else {
                                    jVar.a(c);
                                }
                            }
                            h d = d(a);
                            if (d == null) {
                                b.c(c + "findAllGeoFencing: geo Coordinate Provider null ");
                            } else {
                                jVar.a(d);
                                arrayList.add(jVar);
                            }
                        }
                    } catch (Exception e) {
                        b.d(e.toString());
                    }
                }
                a.close();
            }
            writableDatabase.close();
        } catch (Exception e2) {
            b.d(e2.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized ArrayList<j> b(String str) {
        ArrayList<j> arrayList;
        k.a(false);
        try {
            ArrayList a = a();
            ArrayList<j> arrayList2 = new ArrayList();
            Iterator it = a.iterator();
            while (it.hasNext()) {
                j jVar = (j) it.next();
                if (TextUtils.equals(jVar.g(), str)) {
                    arrayList2.add(jVar);
                }
            }
            arrayList = arrayList2;
        } catch (Exception e) {
            b.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized String c(String str) {
        String string;
        k.a(false);
        try {
            Cursor a = a(this.a.getWritableDatabase());
            if (a != null) {
                while (a.moveToNext()) {
                    if (TextUtils.equals(a.getString(a.getColumnIndex("id")), str)) {
                        string = a.getString(a.getColumnIndex("current_status"));
                        b.c(c + "findGeoStatueByGeoId: geo current statue is " + string + " geoId:" + str);
                        a.close();
                        break;
                    }
                }
                a.close();
            }
            string = "Unknown";
        } catch (Exception e) {
            b.d(e.toString());
            string = "Unknown";
        }
        return string;
    }

    public synchronized int d(String str) {
        int delete;
        k.a(false);
        try {
            if (a(str) != null) {
                SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                delete = writableDatabase.delete("geofence", "id = ?", new String[]{str});
                writableDatabase.close();
            } else {
                delete = 0;
            }
        } catch (Exception e) {
            b.d(e.toString());
            delete = 0;
        }
        return delete;
    }

    public synchronized int e(String str) {
        int i;
        k.a(false);
        try {
            if (TextUtils.isEmpty(str)) {
                i = 0;
            } else {
                SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                i = writableDatabase.delete("geofence", "package_name = ?", new String[]{str});
                writableDatabase.close();
            }
        } catch (Exception e) {
            b.d(e.toString());
            i = 0;
        }
        return i;
    }
}
