package com.zxt.download2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class a extends SQLiteOpenHelper {
    public a(Context context, String str) {
        super(context, str, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.i("DownloadDBHelper", "create download table.");
        StringBuffer stringBuffer = new StringBuffer("create table ");
        stringBuffer.append("download");
        stringBuffer.append("(");
        stringBuffer.append(HistoryOpenHelper.COLUMN_ID);
        stringBuffer.append(" integer primary key autoincrement, ");
        stringBuffer.append("url");
        stringBuffer.append(" text unique, ");
        stringBuffer.append("downloadState");
        stringBuffer.append(" text,");
        stringBuffer.append("filepath");
        stringBuffer.append(" text, ");
        stringBuffer.append("filename");
        stringBuffer.append(" text, ");
        stringBuffer.append("title");
        stringBuffer.append(" text, ");
        stringBuffer.append("thumbnail");
        stringBuffer.append(" text, ");
        stringBuffer.append("finishedSize");
        stringBuffer.append(" integer, ");
        stringBuffer.append("totalSize");
        stringBuffer.append(" integer)");
        String stringBuffer2 = stringBuffer.toString();
        Log.i("DownloadDBHelper", stringBuffer2);
        sQLiteDatabase.execSQL(stringBuffer2);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    void a(f fVar) {
        getWritableDatabase().insert("download", null, d(fVar));
    }

    f a(String str) {
        Cursor query = getReadableDatabase().query("download", new String[]{"url", "downloadState", "filepath", "filename", "title", "thumbnail", "finishedSize", "totalSize"}, "url=?", new String[]{str}, null, null, null);
        if (query == null) {
            return null;
        }
        f fVar;
        if (query.moveToNext()) {
            fVar = new f(query.getString(0), query.getString(2), query.getString(3), query.getString(4), query.getString(5));
            fVar.a(DownloadState.valueOf(query.getString(1)));
            fVar.a(query.getInt(6));
            fVar.b(query.getInt(7));
        } else {
            fVar = null;
        }
        query.close();
        return fVar;
    }

    List<f> a() {
        List<f> arrayList = new ArrayList();
        Cursor query = getReadableDatabase().query("download", new String[]{"url", "downloadState", "filepath", "filename", "title", "thumbnail", "finishedSize", "totalSize"}, "downloadState='FINISHED'", null, null, null, "_id desc");
        if (query != null) {
            while (query.moveToNext()) {
                f fVar = new f(query.getString(0), query.getString(2), query.getString(3), query.getString(4), query.getString(5));
                fVar.a(DownloadState.valueOf(query.getString(1)));
                fVar.a(query.getInt(6));
                fVar.b(query.getInt(7));
                arrayList.add(fVar);
            }
            query.close();
        }
        return arrayList;
    }

    List<f> b() {
        List<f> arrayList = new ArrayList();
        Cursor query = getReadableDatabase().query("download", new String[]{"url", "downloadState", "filepath", "filename", "title", "thumbnail", "finishedSize", "totalSize"}, "downloadState<> 'FINISHED'", null, null, null, "_id desc");
        if (query != null) {
            while (query.moveToNext()) {
                f fVar = new f(query.getString(0), query.getString(2), query.getString(3), query.getString(4), query.getString(5));
                fVar.a(DownloadState.valueOf(query.getString(1)));
                fVar.a(query.getInt(6));
                fVar.b(query.getInt(7));
                arrayList.add(fVar);
            }
            query.close();
        }
        return arrayList;
    }

    void b(f fVar) {
        getWritableDatabase().update("download", d(fVar), "url=?", new String[]{fVar.getUrl()});
    }

    void c(f fVar) {
        getWritableDatabase().delete("download", "url=?", new String[]{fVar.getUrl()});
    }

    private ContentValues d(f fVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", fVar.getUrl());
        contentValues.put("downloadState", fVar.i().toString());
        contentValues.put("filepath", fVar.d());
        contentValues.put("filename", fVar.c());
        contentValues.put("title", fVar.b());
        contentValues.put("thumbnail", fVar.a());
        contentValues.put("finishedSize", Integer.valueOf(fVar.e()));
        contentValues.put("totalSize", Integer.valueOf(fVar.f()));
        return contentValues;
    }
}
