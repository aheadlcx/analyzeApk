package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class e {
    Context a;
    SQLiteDatabase b;
    c c;

    public e(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void b() {
        this.b = this.c.getWritableDatabase();
    }

    private void c() {
        this.b.close();
    }

    public void a(String str, String str2) {
        synchronized (c.a) {
            b();
            ContentValues contentValues = new ContentValues();
            contentValues.put("commentId", str);
            contentValues.put("data2", str2);
            this.b.insert("comment", null, contentValues);
            c();
        }
    }

    public boolean b(String str, String str2) {
        boolean z;
        synchronized (c.a) {
            b();
            Cursor query = this.b.query("comment", new String[]{"id"}, "data1 = ? and  commentId = ?", new String[]{str2, str}, null, null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    int i = query.getInt(query.getColumnIndex("id"));
                    if (i > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Log.i("===", "id: " + i);
                } else {
                    z = false;
                }
                query.close();
            } else {
                z = false;
            }
            c();
        }
        return z;
    }

    public List<String> a() {
        List<String> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            b();
            Cursor query = this.b.query("comment", new String[]{"commentId", "data2"}, null, null, null, null, null);
            if (query.getCount() > 0) {
                while (query.moveToNext()) {
                    String string = query.getString(1);
                    if (TextUtils.isEmpty(string)) {
                        string = "like";
                    }
                    arrayList.add(query.getString(0) + "#" + string);
                }
            }
            c();
        }
        return arrayList;
    }
}
