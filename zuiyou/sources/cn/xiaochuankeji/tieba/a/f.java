package cn.xiaochuankeji.tieba.a;

import android.content.ContentValues;
import cn.xiaochuankeji.tieba.background.splash.SplashInfo;
import com.tencent.wcdb.Cursor;
import com.umeng.analytics.b.g;
import java.util.ArrayList;

public class f {
    public static e a() {
        e eVar = null;
        Cursor rawQuery = j.a().rawQuery("select start_time,end_time,type,duration,image_http_url,image_local_uri,data_tid,data_activity_url,STATUS,VERSION from splash_config order by start_time desc limit 1;", null);
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    eVar = new e();
                    eVar.a = rawQuery.getLong(0);
                    eVar.b = rawQuery.getLong(1);
                    eVar.c = rawQuery.getInt(2);
                    eVar.d = rawQuery.getLong(3);
                    eVar.e = rawQuery.getString(4);
                    eVar.f = rawQuery.getString(5);
                    eVar.g = rawQuery.getLong(6);
                    eVar.h = rawQuery.getString(7);
                    eVar.i = rawQuery.getInt(8);
                    eVar.j = rawQuery.getInt(9);
                    return eVar;
                }
            } finally {
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
            }
        }
        if (!(rawQuery == null || rawQuery.isClosed())) {
            rawQuery.close();
        }
        return eVar;
    }

    public static ArrayList<e> b() {
        ArrayList<e> arrayList = new ArrayList();
        Cursor rawQuery = j.a().rawQuery("select start_time,end_time,type,duration,image_http_url,image_local_uri,data_tid,data_activity_url,STATUS,VERSION from splash_config order by start_time desc limit 1;", null);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                try {
                    e eVar = new e();
                    eVar.a = rawQuery.getLong(0);
                    eVar.b = rawQuery.getLong(1);
                    eVar.c = rawQuery.getInt(2);
                    eVar.d = rawQuery.getLong(3);
                    eVar.e = rawQuery.getString(4);
                    eVar.f = rawQuery.getString(5);
                    eVar.g = rawQuery.getLong(6);
                    eVar.h = rawQuery.getString(7);
                    eVar.i = rawQuery.getInt(8);
                    eVar.j = rawQuery.getInt(9);
                    arrayList.add(eVar);
                } catch (Throwable th) {
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                }
            }
        }
        if (!(rawQuery == null || rawQuery.isClosed())) {
            rawQuery.close();
        }
        return arrayList;
    }

    public static void c() {
        j.a().delete("splash_config", null, null);
    }

    public static long a(SplashInfo splashInfo, int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(g.W, Long.valueOf(splashInfo.st));
        contentValues.put(g.X, Long.valueOf(splashInfo.et));
        contentValues.put("type", Integer.valueOf(splashInfo.type));
        contentValues.put("duration", Integer.valueOf(splashInfo.dur));
        contentValues.put("image_http_url", splashInfo.url);
        contentValues.put("data_tid", Integer.valueOf(splashInfo.data.tid));
        contentValues.put("data_activity_url", splashInfo.data.url);
        contentValues.put("STATUS", Integer.valueOf(i));
        contentValues.put("VERSION", Integer.valueOf(i2));
        return j.a().insert("splash_config", null, contentValues);
    }

    public static int a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("image_local_uri", str2);
        return j.a().update("splash_config", contentValues, "image_http_url=?", new String[]{str});
    }
}
