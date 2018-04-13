package com.spriteapp.booklibrary.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.spriteapp.booklibrary.enumeration.BookEnum;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import java.util.ArrayList;
import java.util.List;

public class e {
    private a a;
    private SQLiteDatabase b;

    public e(Context context) {
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

    public void a(BookDetailResponse bookDetailResponse) {
        if (bookDetailResponse != null) {
            synchronized (a.a) {
                a();
                if (b(bookDetailResponse.getBook_id())) {
                    b();
                    return;
                }
                this.b.beginTransaction();
                d();
                ContentValues contentValues = new ContentValues();
                contentValues.put("book_id", Integer.valueOf(bookDetailResponse.getBook_id()));
                contentValues.put("book_name", bookDetailResponse.getBook_name());
                contentValues.put("book_image", bookDetailResponse.getBook_image());
                contentValues.put("book_introduction", bookDetailResponse.getBook_intro());
                contentValues.put("book_share_url", bookDetailResponse.getBook_share_url());
                contentValues.put("last_chapter_id", Integer.valueOf(bookDetailResponse.getChapter_id()));
                contentValues.put("last_chapter_index", Integer.valueOf(bookDetailResponse.getLast_chapter_index()));
                contentValues.put("total_chapter", Integer.valueOf(bookDetailResponse.getBook_chapter_total()));
                contentValues.put("last_read_time", Long.valueOf(System.currentTimeMillis() / 1000));
                contentValues.put("last_update_book_datetime", Long.valueOf(System.currentTimeMillis() / 1000));
                contentValues.put("last_update_chapter_datetime", Long.valueOf(System.currentTimeMillis() / 1000));
                contentValues.put("book_finish_flag", Integer.valueOf(bookDetailResponse.getBook_finish_flag()));
                contentValues.put("book_is_vip", Integer.valueOf(bookDetailResponse.getBook_is_vip()));
                contentValues.put("book_is_recommend_data", Integer.valueOf(BookEnum.MY_BOOK.getValue()));
                this.b.insert("recent_book_table", null, contentValues);
                this.b.setTransactionSuccessful();
                this.b.endTransaction();
                b();
            }
        }
    }

    public void a(int i) {
        synchronized (a.a) {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_read_time", Long.valueOf(System.currentTimeMillis() / 1000));
            this.b.update("recent_book_table", contentValues, "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public List<BookDetailResponse> c() {
        List<BookDetailResponse> arrayList;
        synchronized (a.a) {
            a();
            arrayList = new ArrayList();
            Cursor query = this.b.query("recent_book_table", null, null, null, null, null, "last_read_time desc");
            if (query.getColumnCount() != 0) {
                while (query.moveToNext()) {
                    BookDetailResponse bookDetailResponse = new BookDetailResponse();
                    bookDetailResponse.setBook_id(query.getInt(query.getColumnIndex("book_id")));
                    bookDetailResponse.setBook_name(query.getString(query.getColumnIndex("book_name")));
                    bookDetailResponse.setBook_image(query.getString(query.getColumnIndex("book_image")));
                    bookDetailResponse.setBook_intro(query.getString(query.getColumnIndex("book_introduction")));
                    bookDetailResponse.setBook_share_url(query.getString(query.getColumnIndex("book_share_url")));
                    bookDetailResponse.setChapter_id(query.getInt(query.getColumnIndex("last_chapter_id")));
                    bookDetailResponse.setBook_chapter_total(query.getInt(query.getColumnIndex("total_chapter")));
                    bookDetailResponse.setLastReadTime(query.getInt(query.getColumnIndex("last_read_time")));
                    bookDetailResponse.setLast_chapter_index(query.getInt(query.getColumnIndex("last_chapter_index")));
                    bookDetailResponse.setLast_update_book_datetime(query.getInt(query.getColumnIndex("last_update_book_datetime")));
                    bookDetailResponse.setLast_update_chapter_datetime(query.getInt(query.getColumnIndex("last_update_chapter_datetime")));
                    bookDetailResponse.setBook_finish_flag(query.getInt(query.getColumnIndex("book_finish_flag")));
                    bookDetailResponse.setBook_add_shelf(query.getInt(query.getColumnIndex("book_add_shelf")));
                    bookDetailResponse.setIs_recommend_book(query.getInt(query.getColumnIndex("book_is_recommend_data")));
                    arrayList.add(bookDetailResponse);
                }
            }
            query.close();
            b();
        }
        return arrayList;
    }

    private void d() {
        Cursor query = this.b.query("recent_book_table", null, null, null, null, null, "last_read_time desc");
        if (query != null && query.getCount() >= 20 && query.moveToLast()) {
            int i = query.getInt(query.getColumnIndex("book_id"));
            this.b.delete("recent_book_table", "book_id = ?", new String[]{String.valueOf(i)});
            query.close();
        }
    }

    private boolean b(int i) {
        boolean z;
        Cursor query = this.b.query("recent_book_table", new String[]{"book_id"}, "book_id = ?", new String[]{String.valueOf(i)}, null, null, null);
        if (query.getColumnCount() != 0) {
            z = false;
            while (query.moveToNext()) {
                z = true;
            }
        } else {
            z = false;
        }
        query.close();
        return z;
    }
}
