package com.spriteapp.booklibrary.d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class a extends SQLiteOpenHelper {
    public static final Object a = new Object();
    private static a b;

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context, "HuaXiDb", null, 1);
                }
            }
        }
        return b;
    }

    private a(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (a) {
            a(sQLiteDatabase);
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(a("book_table"));
        sQLiteDatabase.execSQL(a());
        sQLiteDatabase.execSQL(b());
        sQLiteDatabase.execSQL(a("recent_book_table"));
    }

    private String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists " + str + "(");
        stringBuilder.append("book_id integer primary key not null, ");
        stringBuilder.append("book_name").append(" text,");
        stringBuilder.append("book_image").append(" text,");
        stringBuilder.append("last_chapter_id").append(" integer,");
        stringBuilder.append("last_chapter_index").append(" integer,");
        stringBuilder.append("total_chapter").append(" integer,");
        stringBuilder.append("last_read_time").append(" integer,");
        stringBuilder.append("last_update_book_datetime").append(" integer,");
        stringBuilder.append("last_update_chapter_datetime").append(" integer,");
        stringBuilder.append("book_finish_flag").append(" integer,");
        stringBuilder.append("book_is_vip").append(" integer,");
        stringBuilder.append("book_add_shelf").append(" integer,");
        stringBuilder.append("book_is_recommend_data").append(" integer,");
        stringBuilder.append("book_introduction").append(" text,");
        stringBuilder.append("book_share_url").append(" text");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists content_table(");
        stringBuilder.append("id integer primary key not null, ");
        stringBuilder.append("book_id").append(" integer,");
        stringBuilder.append("chapter_id").append(" integer,");
        stringBuilder.append("chapter_title").append(" text,");
        stringBuilder.append("chapter_price").append(" integer,");
        stringBuilder.append("chapter_is_vip").append(" integer,");
        stringBuilder.append("chapter_content_byte").append(" integer,");
        stringBuilder.append("auto_sub").append(" integer,");
        stringBuilder.append("chapter_intro").append(" text,");
        stringBuilder.append("chapter_content_key").append(" text,");
        stringBuilder.append("chapter_content").append(" text,");
        stringBuilder.append("chapter_need_buy").append(" integer,");
        stringBuilder.append("chapter_pay_type").append(" integer");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists chapter_table(");
        stringBuilder.append("id integer primary key not null, ");
        stringBuilder.append("book_id").append(" integer,");
        stringBuilder.append("chapter_id").append(" integer,");
        stringBuilder.append("chapter_title").append(" text,");
        stringBuilder.append("chapter_content_byte").append(" integer,");
        stringBuilder.append("chapter_is_sub").append(" integer,");
        stringBuilder.append("chapter_price").append(" integer,");
        stringBuilder.append("chapter_is_vip").append(" integer,");
        stringBuilder.append("chapter_order").append(" integer,");
        stringBuilder.append("chapter_read_state").append(" integer");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
