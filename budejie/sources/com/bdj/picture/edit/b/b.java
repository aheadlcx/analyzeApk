package com.bdj.picture.edit.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.bdj.picture.edit.sticker.StickerCategory;
import java.util.ArrayList;
import java.util.List;

public class b {
    Context a;
    SQLiteDatabase b;
    a c;

    public b(Context context) {
        this.a = context;
        this.c = a.a(context);
    }

    private void b() {
        this.b = this.c.getWritableDatabase();
    }

    private void c() {
        this.b.close();
    }

    public void a(List<StickerCategory> list) {
        synchronized (a.a) {
            b();
            this.b.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                StickerCategory stickerCategory = (StickerCategory) list.get(i);
                ContentValues contentValues = new ContentValues();
                try {
                    contentValues.put("id", stickerCategory.id);
                    contentValues.put("parent_id", stickerCategory.parent_id);
                    contentValues.put("name", stickerCategory.name);
                    contentValues.put("sort_id", stickerCategory.sort_id);
                    contentValues.put("status", stickerCategory.status);
                    contentValues.put("create_time", stickerCategory.create_time);
                    contentValues.put("modify_time", stickerCategory.modify_time);
                } catch (Exception e) {
                    Log.e("", "ljj--> insert: " + e.toString());
                }
                this.b.insert("StickerCategory", null, contentValues);
            }
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            c();
        }
    }

    public ArrayList<StickerCategory> a() {
        ArrayList<StickerCategory> arrayList;
        synchronized (a.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"id", "parent_id", "name", "sort_id", "status", "create_time", "modify_time"};
            b();
            Cursor query = this.b.query("StickerCategory", strArr, null, null, null, null, "sort_id");
            if (query != null) {
                while (query.moveToNext()) {
                    StickerCategory stickerCategory = new StickerCategory();
                    stickerCategory.id = query.getString(0);
                    stickerCategory.parent_id = query.getString(1);
                    stickerCategory.name = query.getString(2);
                    stickerCategory.sort_id = query.getString(3);
                    stickerCategory.status = query.getString(4);
                    stickerCategory.create_time = query.getString(5);
                    stickerCategory.modify_time = query.getString(6);
                    arrayList.add(stickerCategory);
                }
                query.close();
            }
            c();
        }
        return arrayList;
    }

    public void a(String str, String[] strArr) {
        synchronized (a.a) {
            b();
            this.b.delete("StickerCategory", str, strArr);
            c();
        }
    }
}
