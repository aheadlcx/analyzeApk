package com.zhihu.matisse;

import cn.xiaochuan.base.BaseApplication;
import com.tencent.wcdb.database.SQLiteDatabase;

public class MediasDatabase {
    private static volatile SQLiteDatabase INSTANCE;

    public static SQLiteDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (MediasDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MediasDBHelper(BaseApplication.getAppContext()).getWritableDatabase();
                }
            }
        }
        return INSTANCE;
    }

    public static void close() {
    }
}
