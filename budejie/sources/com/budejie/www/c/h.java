package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.budejie.www.bean.DOCObject;
import com.budejie.www.bean.ListItemObject;
import java.util.ArrayList;
import java.util.List;

public class h {
    Context a;
    SQLiteDatabase b;
    c c;

    public h(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void b() {
        this.b = this.c.getWritableDatabase();
    }

    private void c() {
        this.b.close();
    }

    public void a(ListItemObject listItemObject, String str) {
        synchronized (c.a) {
            int love = listItemObject.getLove();
            int cai = listItemObject.getCai();
            ContentValues contentValues = new ContentValues();
            contentValues.put("data_id", listItemObject.getWid());
            if ("ding".equals(str)) {
                love++;
                contentValues.put("flag", "ding");
            }
            if ("cai".equals(str)) {
                cai++;
                contentValues.put("cai_flag", "cai");
            }
            contentValues.put("ding", Integer.valueOf(love));
            contentValues.put("cai", Integer.valueOf(cai));
            if (a(listItemObject.getWid())) {
                b();
                this.b.update("love", contentValues, "data_id=?", new String[]{listItemObject.getWid()});
            } else {
                b();
                this.b.insert("love", null, contentValues);
            }
            c();
        }
    }

    public boolean a(String str) {
        boolean z;
        synchronized (c.a) {
            b();
            Cursor query = this.b.query("love", new String[]{"id"}, "data_id = " + str, null, null, null, null);
            if (query == null || !query.moveToFirst()) {
                z = false;
            } else {
                z = true;
            }
            query.close();
            c();
        }
        return z;
    }

    public List<DOCObject> a() {
        List<DOCObject> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            b();
            Cursor query = this.b.query("love", new String[]{"data_id", "flag", "cai_flag", "ding", "cai"}, null, null, null, null, null);
            if (query.getCount() > 0) {
                while (query.moveToNext()) {
                    DOCObject dOCObject = new DOCObject();
                    dOCObject.setData_id(query.getString(0));
                    dOCObject.setFlag(query.getString(1));
                    dOCObject.setCai_flag(query.getString(2));
                    dOCObject.setDing(query.getInt(3));
                    dOCObject.setCai(query.getInt(4));
                    arrayList.add(dOCObject);
                }
            }
            query.close();
            c();
        }
        return arrayList;
    }
}
