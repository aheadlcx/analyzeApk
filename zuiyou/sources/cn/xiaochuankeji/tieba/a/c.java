package cn.xiaochuankeji.tieba.a;

import android.content.Context;
import android.util.Log;
import cn.xiaochuan.base.BaseApplication;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;
import java.util.Locale;

public class c extends SQLiteOpenHelper {
    private static volatile SQLiteDatabase a;

    public c(Context context) {
        super(context, "zuiyou.db", null, 4);
    }

    public static SQLiteDatabase a() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c(BaseApplication.getAppContext()).getWritableDatabase();
                }
            }
        }
        return a;
    }

    public static synchronized void b() {
        synchronized (c.class) {
            try {
                a();
            } catch (Exception e) {
                Log.e("DataBase", e.getMessage());
            }
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("CREATE TABLE hash (\"id\" integer NOT NULL PRIMARY KEY AUTOINCREMENT,\"key\" text(64,0),\"value\" blob);");
            sQLiteDatabase.execSQL("CREATE TABLE sticker (\"name\" text NOT NULL,\"size\" integer(0),\"width\" integer(0),\"height\" integer(0),\"tag\" integer(0),\"mime_type\" text,\"path\" text,\"remote\" text,\"source\" text,\"preview\" text,\"cr\" text,\"status\" integer(0),\"percent\" integer(100),\"create_time\" integer);");
            sQLiteDatabase.execSQL("CREATE TABLE upload (\"path\" text,\"md5\" text,\"key\" integer(0),\"thumb_url\" text,\"upload_id\" text,\"block_index\" integer(0),\"b_size\" integer(0));");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("DataBase", e.getMessage());
            e.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.beginTransaction();
        if (i == 1) {
            try {
                sQLiteDatabase.execSQL("CREATE TABLE sticker (\"name\" text NOT NULL,\"size\" integer(0),\"width\" integer(0),\"height\" integer(0),\"tag\" integer(0),\"mime_type\" text,\"path\" text,\"remote\" text,\"source\" text,\"preview\" text,\"cr\" text,\"status\" integer(0),\"percent\" integer(100),\"create_time\" integer);");
            } catch (Exception e) {
                Log.e("DataBase", e.getMessage());
                return;
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
        if (i <= 2) {
            sQLiteDatabase.execSQL("CREATE TABLE upload (\"path\" text,\"md5\" text,\"key\" integer(0),\"thumb_url\" text,\"upload_id\" text,\"block_index\" integer(0),\"b_size\" integer(0));");
        }
        if (i == 3) {
            sQLiteDatabase.execSQL("drop table notice;");
        }
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            sQLiteDatabase.setLocale(Locale.CHINESE);
        }
        super.onConfigure(sQLiteDatabase);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }
}
