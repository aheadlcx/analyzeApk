package cn.tatagou.sdk.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class b extends SQLiteOpenHelper {
    public b(Context context) {
        super(context, "ttgsdk.db", null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table searchhistory(name varchar(55),time varchar(255))");
        sQLiteDatabase.execSQL("create table special(spId varchar(20) primary key, tbId varchar(20),onlineTime INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists searchhistory");
        sQLiteDatabase.execSQL("drop table if exists special");
        onCreate(sQLiteDatabase);
    }
}
