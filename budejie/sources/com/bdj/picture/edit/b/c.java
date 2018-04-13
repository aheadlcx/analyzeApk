package com.bdj.picture.edit.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.bdj.picture.edit.sticker.StickerItem;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;

public class c {
    Context a;
    SQLiteDatabase b;
    a c;

    public c(Context context) {
        this.a = context;
        this.c = a.a(context);
    }

    private void b() {
        this.b = this.c.getWritableDatabase();
    }

    private void c() {
        this.b.close();
    }

    public void a(StickerItem stickerItem) {
        synchronized (a.a) {
            ContentValues contentValues = new ContentValues();
            b();
            this.b.beginTransaction();
            try {
                contentValues.put("id", stickerItem.id);
                contentValues.put("category_id", stickerItem.category_id);
                contentValues.put("category_parent_id", stickerItem.category_parent_id);
                contentValues.put("name", stickerItem.name);
                contentValues.put(GameAppOperation.QQFAV_DATALINE_IMAGEURL, stickerItem.image_url);
                contentValues.put("thumb_url", stickerItem.thumb_url);
                contentValues.put("introduce", stickerItem.introduce);
                contentValues.put("status", stickerItem.status);
                contentValues.put("is_new", stickerItem.is_new);
                contentValues.put("total_times", stickerItem.total_times);
                contentValues.put("used_times", stickerItem.used_times);
                contentValues.put("available_times", stickerItem.available_times);
                contentValues.put(x.W, stickerItem.start_time);
                contentValues.put(x.X, stickerItem.end_time);
                contentValues.put("create_time", stickerItem.create_time);
                contentValues.put("modify_time", stickerItem.modify_time);
                contentValues.put("admin", stickerItem.admin);
                contentValues.put("sub_category_name", stickerItem.sub_category_name);
                contentValues.put("local_path", stickerItem.local_path);
                contentValues.put("down_status", stickerItem.down_status);
            } catch (Exception e) {
                Log.e("", "ljj--> insertSigleStickerItem: " + e.toString());
            }
            this.b.insert("Sticker", null, contentValues);
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            c();
        }
    }

    public ArrayList<StickerItem> a() {
        ArrayList<StickerItem> arrayList;
        synchronized (a.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"id", "category_id", "category_parent_id", "name", GameAppOperation.QQFAV_DATALINE_IMAGEURL, "thumb_url", "introduce", "status", "is_new", "total_times", "used_times", "available_times", x.W, x.X, "create_time", "modify_time", "admin", "sub_category_name", "local_path", "down_status"};
            b();
            Cursor query = this.b.query("Sticker", strArr, null, null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    StickerItem stickerItem = new StickerItem();
                    stickerItem.id = query.getString(0);
                    stickerItem.category_id = query.getString(1);
                    stickerItem.category_parent_id = query.getString(2);
                    stickerItem.name = query.getString(3);
                    stickerItem.image_url = query.getString(4);
                    stickerItem.thumb_url = query.getString(5);
                    stickerItem.introduce = query.getString(6);
                    stickerItem.status = query.getString(7);
                    stickerItem.is_new = query.getString(8);
                    stickerItem.total_times = query.getString(9);
                    stickerItem.used_times = query.getString(10);
                    stickerItem.available_times = query.getString(11);
                    stickerItem.start_time = query.getString(12);
                    stickerItem.end_time = query.getString(13);
                    stickerItem.create_time = query.getString(14);
                    stickerItem.modify_time = query.getString(15);
                    stickerItem.admin = query.getString(16);
                    stickerItem.sub_category_name = query.getString(17);
                    stickerItem.local_path = query.getString(18);
                    stickerItem.down_status = query.getString(19);
                    arrayList.add(stickerItem);
                }
                query.close();
            }
            c();
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r12) {
        /*
        r11 = this;
        r8 = 1;
        r9 = 0;
        r10 = com.bdj.picture.edit.b.a.a;
        monitor-enter(r10);
        r0 = android.text.TextUtils.isEmpty(r12);	 Catch:{ all -> 0x004d }
        if (r0 != 0) goto L_0x004a;
    L_0x000b:
        r11.b();	 Catch:{ all -> 0x004d }
        r0 = r11.b;	 Catch:{ all -> 0x004d }
        r1 = "Sticker";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ all -> 0x004d }
        r3 = 0;
        r4 = "local_path";
        r2[r3] = r4;	 Catch:{ all -> 0x004d }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004d }
        r3.<init>();	 Catch:{ all -> 0x004d }
        r4 = "local_path='";
        r3 = r3.append(r4);	 Catch:{ all -> 0x004d }
        r3 = r3.append(r12);	 Catch:{ all -> 0x004d }
        r4 = "' and down_status='1'";
        r3 = r3.append(r4);	 Catch:{ all -> 0x004d }
        r3 = r3.toString();	 Catch:{ all -> 0x004d }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x004d }
        r1 = r0.moveToFirst();	 Catch:{ all -> 0x004d }
        if (r1 == 0) goto L_0x0044;
    L_0x0041:
        monitor-exit(r10);	 Catch:{ all -> 0x004d }
        r0 = r8;
    L_0x0043:
        return r0;
    L_0x0044:
        r0.close();	 Catch:{ all -> 0x004d }
        r11.c();	 Catch:{ all -> 0x004d }
    L_0x004a:
        monitor-exit(r10);	 Catch:{ all -> 0x004d }
        r0 = r9;
        goto L_0x0043;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x004d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bdj.picture.edit.b.c.a(java.lang.String):boolean");
    }

    public void b(StickerItem stickerItem) {
        synchronized (a.a) {
            b();
            this.b.execSQL("update Sticker set down_status='" + stickerItem.down_status + "', sub_category_name='" + stickerItem.sub_category_name + "' where local_path='" + stickerItem.local_path + "'");
            c();
        }
    }

    public void b(String str) {
        synchronized (a.a) {
            b();
            this.b.execSQL("delete from Sticker where local_path='" + str + "'");
            c();
        }
    }
}
