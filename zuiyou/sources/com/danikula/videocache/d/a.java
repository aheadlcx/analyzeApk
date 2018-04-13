package com.danikula.videocache.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.danikula.videocache.k;
import com.danikula.videocache.o;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

class a extends SQLiteOpenHelper implements c {
    private static final String[] a = new String[]{"_id", "url", "length", IMediaFormat.KEY_MIME};

    a(Context context) {
        super(context, "AndroidVideoCache.db", null, 1);
        k.a((Object) context);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        k.a((Object) sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }

    public o a(String str) {
        Throwable th;
        Cursor cursor = null;
        k.a((Object) str);
        try {
            o a;
            Cursor query = getReadableDatabase().query("SourceInfo", a, "url=?", new String[]{str}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        a = a(query);
                        if (query != null) {
                            query.close();
                        }
                        return a;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            a = null;
            if (query != null) {
                query.close();
            }
            return a;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void a(String str, o oVar) {
        k.a(str, oVar);
        int i = a(str) != null ? 1 : 0;
        ContentValues a = a(oVar);
        if (i != 0) {
            getWritableDatabase().update("SourceInfo", a, "url=?", new String[]{str});
        } else {
            getWritableDatabase().insert("SourceInfo", null, a);
        }
    }

    private o a(Cursor cursor) {
        return new o(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getLong(cursor.getColumnIndexOrThrow("length")), cursor.getString(cursor.getColumnIndexOrThrow(IMediaFormat.KEY_MIME)));
    }

    private ContentValues a(o oVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", oVar.a);
        contentValues.put("length", Long.valueOf(oVar.b));
        contentValues.put(IMediaFormat.KEY_MIME, oVar.c);
        return contentValues;
    }
}
