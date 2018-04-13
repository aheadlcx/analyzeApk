package cn.xiaochuankeji.tieba.a;

import cn.xiaochuan.base.BaseApplication;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteTrace;
import java.util.List;

public class j {
    private static volatile SQLiteDatabase a;
    private static SQLiteTrace b = new SQLiteTrace() {
        public void onSQLExecuted(SQLiteDatabase sQLiteDatabase, String str, int i, long j) {
        }

        public void onConnectionObtained(SQLiteDatabase sQLiteDatabase, String str, long j, boolean z) {
        }

        public void onConnectionPoolBusy(SQLiteDatabase sQLiteDatabase, String str, List<String> list, String str2) {
        }

        public void onDatabaseCorrupted(SQLiteDatabase sQLiteDatabase) {
        }
    };

    public static SQLiteDatabase a() {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new i(BaseApplication.getAppContext()).getWritableDatabase();
                }
            }
        }
        return a;
    }
}
