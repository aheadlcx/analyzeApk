package cn.xiaochuankeji.tieba.a;

import android.content.ContentValues;
import com.tencent.wcdb.Cursor;

public class b {
    public static a a(String str) {
        a aVar = null;
        Cursor rawQuery = j.a().rawQuery("select update_time,config_key,config_version,config_value,rowid from app_config where config_key='" + str + "';", null);
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    aVar = new a();
                    aVar.b = rawQuery.getLong(0);
                    aVar.c = rawQuery.getString(1);
                    aVar.d = rawQuery.getInt(2);
                    aVar.e = rawQuery.getString(3);
                    aVar.a = rawQuery.getLong(4);
                    return aVar;
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
        return aVar;
    }

    public static int a(long j, a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("update_time", Long.valueOf(aVar.b));
        contentValues.put("config_key", aVar.c);
        contentValues.put("config_version", Integer.valueOf(aVar.d));
        contentValues.put("config_value", aVar.e);
        return j.a().update("app_config", contentValues, "rowid=?", new String[]{String.valueOf(j)});
    }
}
