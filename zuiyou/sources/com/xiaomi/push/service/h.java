package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBConstants;
import com.umeng.analytics.b.g;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.m;
import com.xiaomi.xmpush.thrift.j;
import com.xiaomi.xmpush.thrift.n;
import com.xiaomi.xmpush.thrift.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h {
    private static volatile h b;
    private static String c = "GeoFenceDao.";
    private Context a;

    private h(Context context) {
        this.a = context;
    }

    private synchronized Cursor a(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        m.a(false);
        try {
            query = sQLiteDatabase.query("geofence", null, null, null, null, null, null);
        } catch (Exception e) {
            query = null;
        }
        return query;
    }

    public static h a(Context context) {
        if (b == null) {
            synchronized (h.class) {
                if (b == null) {
                    b = new h(context);
                }
            }
        }
        return b;
    }

    private synchronized n a(Cursor cursor) {
        n nVar;
        try {
            for (n nVar2 : n.values()) {
                if (TextUtils.equals(cursor.getString(cursor.getColumnIndex("type")), nVar2.name())) {
                    break;
                }
            }
            nVar2 = null;
        } catch (Exception e) {
            b.d(e.toString());
            nVar2 = null;
        }
        return nVar2;
    }

    private synchronized String a(List<o> list) {
        String jSONArray;
        if (list != null) {
            if (list.size() >= 3) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    for (o oVar : list) {
                        if (oVar != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("point_lantitude", oVar.c());
                            jSONObject.put("point_longtitude", oVar.a());
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

    private synchronized o b(Cursor cursor) {
        o oVar;
        oVar = new o();
        try {
            oVar.b(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_lantitude"))));
            oVar.a(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_longtitude"))));
        } catch (Exception e) {
            b.d(e.toString());
            oVar = null;
        }
        return oVar;
    }

    private synchronized ArrayList<o> c(Cursor cursor) {
        ArrayList<o> arrayList;
        ArrayList<o> arrayList2 = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(cursor.getString(cursor.getColumnIndex("polygon_points")));
            for (int i = 0; i < jSONArray.length(); i++) {
                o oVar = new o();
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                oVar.b(jSONObject.getDouble("point_lantitude"));
                oVar.a(jSONObject.getDouble("point_longtitude"));
                arrayList2.add(oVar);
            }
            arrayList = arrayList2;
        } catch (JSONException e) {
            b.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    private synchronized j d(Cursor cursor) {
        j valueOf;
        try {
            valueOf = j.valueOf(cursor.getString(cursor.getColumnIndex("coordinate_provider")));
        } catch (IllegalArgumentException e) {
            b.d(e.toString());
            valueOf = null;
        }
        return valueOf;
    }

    public synchronized int a(String str, String str2) {
        int i = 0;
        synchronized (this) {
            m.a(false);
            try {
                if ("Enter".equals(str2) || "Leave".equals(str2) || "Unknown".equals(str2)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("current_status", str2);
                    int update = i.a(this.a).a().update("geofence", contentValues, "id=?", new String[]{str});
                    i.a(this.a).b();
                    i = update;
                }
            } catch (Exception e) {
                b.d(e.toString());
            }
        }
        return i;
    }

    public synchronized long a(com.xiaomi.xmpush.thrift.m mVar) {
        long insert;
        m.a(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", mVar.a());
            contentValues.put("appId", Long.valueOf(mVar.e()));
            contentValues.put("name", mVar.c());
            contentValues.put(g.e, mVar.g());
            contentValues.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, Long.valueOf(mVar.i()));
            contentValues.put("type", mVar.k().name());
            contentValues.put("center_longtitude", String.valueOf(mVar.m().a()));
            contentValues.put("center_lantitude", String.valueOf(mVar.m().c()));
            contentValues.put("circle_radius", Double.valueOf(mVar.o()));
            contentValues.put("polygon_point", a(mVar.q()));
            contentValues.put("coordinate_provider", mVar.s().name());
            contentValues.put("current_status", "Unknown");
            insert = i.a(this.a).a().insert("geofence", null, contentValues);
            i.a(this.a).b();
        } catch (Exception e) {
            b.d(e.toString());
            insert = -1;
        }
        return insert;
    }

    public synchronized com.xiaomi.xmpush.thrift.m a(String str) {
        com.xiaomi.xmpush.thrift.m mVar;
        m.a(false);
        try {
            Iterator it = a().iterator();
            while (it.hasNext()) {
                mVar = (com.xiaomi.xmpush.thrift.m) it.next();
                if (TextUtils.equals(mVar.a(), str)) {
                    break;
                }
            }
            mVar = null;
        } catch (Exception e) {
            b.d(e.toString());
            mVar = null;
        }
        return mVar;
    }

    public synchronized ArrayList<com.xiaomi.xmpush.thrift.m> a() {
        ArrayList<com.xiaomi.xmpush.thrift.m> arrayList;
        m.a(false);
        try {
            Cursor a = a(i.a(this.a).a());
            arrayList = new ArrayList();
            if (a != null) {
                while (a.moveToNext()) {
                    try {
                        com.xiaomi.xmpush.thrift.m mVar = new com.xiaomi.xmpush.thrift.m();
                        mVar.a(a.getString(a.getColumnIndex("id")));
                        mVar.b(a.getString(a.getColumnIndex("name")));
                        mVar.a((long) a.getInt(a.getColumnIndex("appId")));
                        mVar.c(a.getString(a.getColumnIndex(g.e)));
                        mVar.b((long) a.getInt(a.getColumnIndex(WBConstants.GAME_PARAMS_GAME_CREATE_TIME)));
                        n a2 = a(a);
                        if (a2 == null) {
                            b.c(c + "findAllGeoFencing: geo type null");
                        } else {
                            mVar.a(a2);
                            if (TextUtils.equals("Circle", a2.name())) {
                                mVar.a(b(a));
                                mVar.a(a.getDouble(a.getColumnIndex("circle_radius")));
                            } else if (TextUtils.equals("Polygon", a2.name())) {
                                List c = c(a);
                                if (c == null || c.size() < 3) {
                                    b.c(c + "findAllGeoFencing: geo points null or size<3");
                                } else {
                                    mVar.a(c);
                                }
                            }
                            j d = d(a);
                            if (d == null) {
                                b.c(c + "findAllGeoFencing: geo Coordinate Provider null ");
                            } else {
                                mVar.a(d);
                                arrayList.add(mVar);
                            }
                        }
                    } catch (Exception e) {
                        b.d(e.toString());
                    }
                }
                a.close();
            }
            i.a(this.a).b();
        } catch (Exception e2) {
            b.d(e2.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized ArrayList<com.xiaomi.xmpush.thrift.m> b(String str) {
        ArrayList<com.xiaomi.xmpush.thrift.m> arrayList;
        m.a(false);
        try {
            ArrayList a = a();
            ArrayList<com.xiaomi.xmpush.thrift.m> arrayList2 = new ArrayList();
            Iterator it = a.iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.m mVar = (com.xiaomi.xmpush.thrift.m) it.next();
                if (TextUtils.equals(mVar.g(), str)) {
                    arrayList2.add(mVar);
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
        m.a(false);
        try {
            Cursor a = a(i.a(this.a).a());
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
            i.a(this.a).b();
            string = "Unknown";
        } catch (Exception e) {
            b.d(e.toString());
            string = "Unknown";
        }
        return string;
    }

    public synchronized int d(String str) {
        int delete;
        m.a(false);
        try {
            if (a(str) != null) {
                delete = i.a(this.a).a().delete("geofence", "id = ?", new String[]{str});
                i.a(this.a).b();
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
        m.a(false);
        try {
            if (TextUtils.isEmpty(str)) {
                i = 0;
            } else {
                i = i.a(this.a).a().delete("geofence", "package_name = ?", new String[]{str});
                i.a(this.a).b();
            }
        } catch (Exception e) {
            b.d(e.toString());
            i = 0;
        }
        return i;
    }
}
