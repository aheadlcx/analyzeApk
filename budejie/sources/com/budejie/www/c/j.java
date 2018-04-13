package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.budejie.www.activity.plate.bean.PlateBean;
import java.util.ArrayList;

public class j {
    Context a;
    SQLiteDatabase b;
    c c;

    public j(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void b() {
        this.b = this.c.getWritableDatabase();
    }

    private void c() {
        this.b.close();
    }

    public void a(String str, int i, int i2) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            b();
            contentValues.put("theme_id", str);
            contentValues.put("read_time", Integer.valueOf(i));
            contentValues.put("read_count", Integer.valueOf(i2));
            if (TextUtils.isEmpty(a(str))) {
                this.b.insert("plate", null, contentValues);
            } else {
                this.b.update("plate", contentValues, "theme_id=?", new String[]{str});
            }
            c();
        }
    }

    public String a(String str) {
        String str2;
        synchronized (c.a) {
            String str3 = "";
            Cursor query = this.b.query("plate", new String[]{"theme_id"}, "theme_id = " + str, null, null, null, null);
            if (query == null || !query.moveToFirst()) {
                str2 = str3;
            } else {
                str2 = query.getString(query.getColumnIndex("theme_id"));
            }
            query.close();
        }
        return str2;
    }

    public ArrayList<PlateBean> a() {
        ArrayList<PlateBean> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            b();
            Cursor query = this.b.query("plate", new String[]{"theme_id", "read_time", "read_count"}, null, null, null, null, null);
            if (query.getCount() > 0) {
                while (query.moveToNext()) {
                    PlateBean plateBean = new PlateBean();
                    plateBean.theme_id = query.getString(query.getColumnIndex("theme_id"));
                    plateBean.read_count = query.getInt(query.getColumnIndex("read_count"));
                    plateBean.read_time = query.getInt(query.getColumnIndex("read_time"));
                    arrayList.add(plateBean);
                }
            }
            query.close();
            c();
        }
        return arrayList;
    }
}
