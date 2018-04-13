package cn.xiaochuankeji.tieba.push.b;

import android.content.ContentValues;
import android.support.v4.app.NotificationCompat;
import cn.xiaochuankeji.tieba.background.a;
import com.alibaba.fastjson.JSONArray;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;

public class d {
    private static String a(SQLiteDatabase sQLiteDatabase, long j) {
        String str = "x_block_" + j;
        if (!a.b(str)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (" + "b_mid" + " integer(64)," + NotificationCompat.CATEGORY_STATUS + " integer DEFAULT 0,PRIMARY KEY (" + "b_mid" + "));");
        }
        return str;
    }

    public static boolean a(long j) {
        SQLiteDatabase a = a.a();
        String a2 = a(a, a.g().c());
        ContentValues contentValues = new ContentValues();
        contentValues.put("b_mid", Long.valueOf(j));
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
        if (contentValues.size() > 0) {
            if (((long) a.updateWithOnConflict(a2, contentValues, "b_mid=?", new String[]{String.valueOf(j)}, 4)) < 1) {
                a.insertWithOnConflict(a2, null, contentValues, 5);
            }
        }
        return true;
    }

    public static boolean b(long j) {
        SQLiteDatabase a = a.a();
        if (a.delete(a(a, a.g().c()), "b_mid=? ", new String[]{String.valueOf(j)}) > 0) {
            return true;
        }
        return false;
    }

    public static boolean c(long j) {
        SQLiteDatabase a = a.a();
        Cursor rawQuery = a.rawQuery("select b_mid from " + a(a, a.g().c()) + " where " + "b_mid" + "=" + j + VoiceWakeuperAidl.PARAMS_SEPARATE, null);
        if (rawQuery == null) {
            return false;
        }
        try {
            boolean moveToFirst = rawQuery.moveToFirst();
            return moveToFirst;
        } finally {
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
    }

    public static boolean a(long j, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.isEmpty()) {
            return false;
        }
        SQLiteDatabase a = a.a();
        String a2 = a(a, j);
        a.beginTransaction();
        int i = 0;
        while (i < jSONArray.size()) {
            try {
                long longValue = jSONArray.getLongValue(i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("b_mid", Long.valueOf(longValue));
                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
                if (contentValues.size() > 0) {
                    if (((long) a.updateWithOnConflict(a2, contentValues, "b_mid=?", new String[]{String.valueOf(j)}, 4)) < 1) {
                        a.insertWithOnConflict(a2, null, contentValues, 5);
                    }
                }
                i++;
            } finally {
                a.endTransaction();
            }
        }
        a.setTransactionSuccessful();
        return true;
    }
}
