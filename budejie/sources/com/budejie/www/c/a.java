package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.budejie.www.bean.AdItem;
import java.util.ArrayList;

public class a {
    Context a;
    c b;
    SQLiteDatabase c;

    public a(Context context) {
        this.a = context;
        this.b = c.a(context);
    }

    private void b() {
        this.c = this.b.getWritableDatabase();
    }

    private void c() {
        this.c.close();
    }

    public ArrayList<AdItem> a() {
        ArrayList<AdItem> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"id", "url"};
            b();
            Cursor query = this.c.query("ad", strArr, "state= 2", null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    AdItem adItem = new AdItem();
                    adItem.setId(query.getInt(0));
                    adItem.setUrl(query.getString(1));
                    arrayList.add(adItem);
                }
                query.close();
            }
            c();
        }
        return arrayList;
    }

    public void a(int i, int i2) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("state", Integer.valueOf(i2));
            b();
            this.c.update("ad", contentValues, "id = " + i, null);
            c();
        }
    }

    public void a(int i) {
        synchronized (c.a) {
            b();
            this.c.delete("ad", "id = " + i, null);
            c();
        }
    }
}
