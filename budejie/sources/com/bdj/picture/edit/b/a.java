package com.bdj.picture.edit.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class a extends SQLiteOpenHelper {
    public static final Object a = new Object();
    private static a b = null;
    private Context c;
    private int d;

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context, "ImageEditDB", null, 1);
            }
            aVar = b;
        }
        return aVar;
    }

    private a(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.c = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (a) {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.d = i;
        synchronized (a) {
            switch (i) {
            }
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists Sticker(");
        stringBuilder.append("id integer primary key not null, ");
        stringBuilder.append("category_id varchar(10), ");
        stringBuilder.append("category_parent_id varchar(10), ");
        stringBuilder.append("name varchar(100), ");
        stringBuilder.append("image_url varchar(100), ");
        stringBuilder.append("thumb_url varchar(100), ");
        stringBuilder.append("introduce varchar(100), ");
        stringBuilder.append("status varchar(10), ");
        stringBuilder.append("is_new varchar(10), ");
        stringBuilder.append("total_times varchar(100), ");
        stringBuilder.append("used_times varchar(100), ");
        stringBuilder.append("available_times varchar(100), ");
        stringBuilder.append("start_time varchar(100), ");
        stringBuilder.append("end_time varchar(100), ");
        stringBuilder.append("create_time varchar(100), ");
        stringBuilder.append("modify_time varchar(100), ");
        stringBuilder.append("admin varchar(100), ");
        stringBuilder.append("sub_category_name varchar(100), ");
        stringBuilder.append("local_path VARCHAR(100), ");
        stringBuilder.append("down_status VARCHAR(2) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists StickerCategory(");
        stringBuilder.append("id integer primary key not null, ");
        stringBuilder.append("parent_id varchar(10), ");
        stringBuilder.append("name varchar(100), ");
        stringBuilder.append("sort_id varchar(10), ");
        stringBuilder.append("status varchar(10), ");
        stringBuilder.append("create_time varchar(100), ");
        stringBuilder.append("modify_time varchar(100)");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }
}
