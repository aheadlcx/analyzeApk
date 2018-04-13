package com.spriteapp.booklibrary.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.spriteapp.booklibrary.model.response.SubscriberContent;

public class d {
    private a a;
    private SQLiteDatabase b;

    public d(Context context) {
        this.a = a.a(context);
    }

    public void a() {
        if (this.a != null) {
            this.b = this.a.getWritableDatabase();
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.close();
        }
    }

    public void a(SubscriberContent subscriberContent) {
        if (subscriberContent != null) {
            synchronized (a.a) {
                a();
                this.b.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("book_id", Integer.valueOf(subscriberContent.getBook_id()));
                contentValues.put("chapter_id", Integer.valueOf(subscriberContent.getChapter_id()));
                contentValues.put("chapter_title", subscriberContent.getChapter_title());
                contentValues.put("chapter_price", Integer.valueOf(subscriberContent.getChapter_price()));
                contentValues.put("chapter_is_vip", Integer.valueOf(subscriberContent.getChapter_is_vip()));
                contentValues.put("chapter_content_byte", Long.valueOf(subscriberContent.getChapter_content_byte()));
                contentValues.put("auto_sub", Integer.valueOf(subscriberContent.getAuto_sub()));
                contentValues.put("chapter_intro", subscriberContent.getChapter_intro());
                contentValues.put("chapter_content_key", subscriberContent.getChapter_content_key());
                contentValues.put("chapter_content", subscriberContent.getChapter_content());
                contentValues.put("chapter_need_buy", Integer.valueOf(subscriberContent.getChapter_need_buy()));
                contentValues.put("chapter_pay_type", Integer.valueOf(subscriberContent.getChapter_pay_type()));
                this.b.insert("content_table", null, contentValues);
                this.b.setTransactionSuccessful();
                this.b.endTransaction();
                b();
            }
        }
    }

    public void a(int i) {
        synchronized (a.a) {
            a();
            if (this.b == null) {
                return;
            }
            this.b.delete("content_table", "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public void a(int i, int i2, SubscriberContent subscriberContent) {
        synchronized (a.a) {
            a();
            if (this.b == null) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("chapter_content_key", subscriberContent.getChapter_content_key());
            contentValues.put("chapter_content", subscriberContent.getChapter_content());
            contentValues.put("chapter_need_buy", Integer.valueOf(subscriberContent.getChapter_need_buy()));
            contentValues.put("chapter_pay_type", Integer.valueOf(subscriberContent.getChapter_pay_type()));
            this.b.update("content_table", contentValues, "book_id = ? and chapter_id = ?", new String[]{String.valueOf(i), String.valueOf(i2)});
            b();
        }
    }

    public void a(int i, int i2, int i3) {
        synchronized (a.a) {
            a();
            if (this.b == null) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("chapter_pay_type", Integer.valueOf(i3));
            this.b.update("content_table", contentValues, "book_id = ? and chapter_id = ?", new String[]{String.valueOf(i), String.valueOf(i2)});
            b();
        }
    }

    public SubscriberContent a(int i, int i2) {
        SubscriberContent subscriberContent;
        synchronized (a.a) {
            a();
            String[] strArr = new String[]{String.valueOf(i), String.valueOf(i2)};
            Cursor query = this.b.query("content_table", null, "book_id = ? and chapter_id = ?", strArr, null, null, null);
            if (query.getColumnCount() != 0) {
                subscriberContent = null;
                while (query.moveToNext()) {
                    if (subscriberContent == null) {
                        subscriberContent = new SubscriberContent();
                    }
                    subscriberContent.setBook_id(query.getInt(query.getColumnIndex("book_id")));
                    subscriberContent.setChapter_id(query.getInt(query.getColumnIndex("chapter_id")));
                    subscriberContent.setChapter_title(query.getString(query.getColumnIndex("chapter_title")));
                    subscriberContent.setChapter_price(query.getInt(query.getColumnIndex("chapter_price")));
                    subscriberContent.setChapter_is_vip(query.getInt(query.getColumnIndex("chapter_is_vip")));
                    subscriberContent.setChapter_content_byte((long) query.getInt(query.getColumnIndex("chapter_content_byte")));
                    subscriberContent.setAuto_sub(query.getInt(query.getColumnIndex("auto_sub")));
                    subscriberContent.setChapter_intro(query.getString(query.getColumnIndex("chapter_intro")));
                    subscriberContent.setChapter_content_key(query.getString(query.getColumnIndex("chapter_content_key")));
                    subscriberContent.setChapter_content(query.getString(query.getColumnIndex("chapter_content")));
                    subscriberContent.setChapter_need_buy(query.getInt(query.getColumnIndex("chapter_need_buy")));
                    subscriberContent.setChapter_pay_type(query.getInt(query.getColumnIndex("chapter_pay_type")));
                    subscriberContent.SQLiteId = query.getInt(query.getColumnIndex("id"));
                }
            } else {
                subscriberContent = null;
            }
            query.close();
            b();
        }
        return subscriberContent;
    }

    public void c() {
        synchronized (a.a) {
            a();
            this.b.delete("content_table", null, null);
            b();
        }
    }
}
