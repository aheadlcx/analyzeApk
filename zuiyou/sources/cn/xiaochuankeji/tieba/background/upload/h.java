package cn.xiaochuankeji.tieba.background.upload;

import android.content.ContentValues;
import android.database.Cursor;
import cn.xiaochuankeji.tieba.a.c;
import com.iflytek.aiui.AIUIConstant;
import com.tencent.wcdb.database.SQLiteDatabase;

public class h extends cn.xiaochuankeji.tieba.a.h {

    private static class a {
        private static final h a = new h();
    }

    public static h a() {
        return a.a;
    }

    private h() {
    }

    public c a(String str, String str2) {
        c cVar = null;
        Cursor rawQuery = c.a().rawQuery("select * from upload where md5='" + str2 + "';", null);
        if (rawQuery != null && rawQuery.moveToFirst()) {
            cVar = new c();
            cVar.a = str;
            cVar.b = str2;
            cVar.c = rawQuery.getString(2);
            cVar.d = rawQuery.getString(3);
            cVar.e = rawQuery.getLong(4);
            cVar.f = rawQuery.getInt(5);
            cVar.g = rawQuery.getInt(6);
        }
        if (!(rawQuery == null || rawQuery.isClosed())) {
            rawQuery.close();
        }
        return cVar;
    }

    public void a(c cVar) {
        SQLiteDatabase a = c.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AIUIConstant.RES_TYPE_PATH, cVar.a);
        contentValues.put("md5", cVar.b);
        contentValues.put("key", cVar.c);
        contentValues.put("thumb_url", cVar.d);
        if (((long) a.updateWithOnConflict("upload", contentValues, "md5=?", new String[]{cVar.b}, 4)) < 1) {
            a.insert("upload", null, contentValues);
        }
    }

    public void b(c cVar) {
        SQLiteDatabase a = c.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AIUIConstant.RES_TYPE_PATH, cVar.a);
        contentValues.put("md5", cVar.b);
        contentValues.put("block_index", Integer.valueOf(cVar.f));
        contentValues.put("upload_id", Long.valueOf(cVar.e));
        contentValues.put("b_size", Integer.valueOf(cVar.g));
        if (((long) a.updateWithOnConflict("upload", contentValues, "md5=?", new String[]{cVar.b}, 4)) < 1) {
            a.insert("upload", null, contentValues);
        }
    }

    public void c(c cVar) {
        SQLiteDatabase a = c.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AIUIConstant.RES_TYPE_PATH, cVar.a);
        contentValues.put("md5", cVar.b);
        a.delete("upload", "md5=?", new String[]{cVar.b});
    }
}
