package com.spriteapp.booklibrary.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.spriteapp.booklibrary.enumeration.BookEnum;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.util.CollectionUtil;
import java.util.ArrayList;
import java.util.List;

public class b {
    private a a;
    private SQLiteDatabase b;

    public b(Context context) {
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

    public void a(List<BookDetailResponse> list, BookEnum bookEnum, BookEnum bookEnum2) {
        if (!CollectionUtil.isEmpty(list)) {
            synchronized (a.a) {
                a();
                this.b.beginTransaction();
                ContentValues contentValues = new ContentValues();
                for (BookDetailResponse bookDetailResponse : list) {
                    if (!e(bookDetailResponse.getBook_id())) {
                        contentValues.clear();
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
                        if (bookEnum2 != null) {
                            contentValues.put("book_is_recommend_data", Integer.valueOf(bookEnum2.getValue()));
                        }
                        if (bookEnum != null) {
                            contentValues.put("book_add_shelf", Integer.valueOf(bookEnum.getValue()));
                        }
                        this.b.insert("book_table", null, contentValues);
                    }
                }
                this.b.setTransactionSuccessful();
                this.b.endTransaction();
                b();
            }
        }
    }

    public void a(BookDetailResponse bookDetailResponse, BookEnum bookEnum) {
        synchronized (a.a) {
            a();
            this.b.beginTransaction();
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
            if (bookEnum != null) {
                contentValues.put("book_add_shelf", Integer.valueOf(bookEnum.getValue()));
            }
            this.b.insert("book_table", null, contentValues);
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            b();
        }
    }

    public void a(int i, BookEnum bookEnum) {
        synchronized (a.a) {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_read_time", Long.valueOf(System.currentTimeMillis() / 1000));
            contentValues.put("book_add_shelf", Integer.valueOf(bookEnum.getValue()));
            this.b.update("book_table", contentValues, "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public void a(BookDetailResponse bookDetailResponse, int i) {
        synchronized (a.a) {
            a();
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
            contentValues.put("book_finish_flag", Integer.valueOf(bookDetailResponse.getBook_finish_flag()));
            contentValues.put("book_is_vip", Integer.valueOf(bookDetailResponse.getBook_is_vip()));
            contentValues.put("book_add_shelf", Integer.valueOf(i));
            this.b.update("book_table", contentValues, "book_id = ?", new String[]{String.valueOf(bookDetailResponse.getBook_id())});
            b();
        }
    }

    public void a(int i) {
        synchronized (a.a) {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_read_time", Long.valueOf(System.currentTimeMillis() / 1000));
            this.b.update("book_table", contentValues, "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public void a(int i, int i2) {
        synchronized (a.a) {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("book_is_recommend_data", Integer.valueOf(i2));
            this.b.update("book_table", contentValues, "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public void a(BookDetailResponse bookDetailResponse) {
        synchronized (a.a) {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_chapter_id", Integer.valueOf(bookDetailResponse.getChapter_id()));
            contentValues.put("last_chapter_index", Integer.valueOf(bookDetailResponse.getLast_chapter_index()));
            contentValues.put("total_chapter", Integer.valueOf(bookDetailResponse.getBook_chapter_total()));
            this.b.update("book_table", contentValues, "book_id = ?", new String[]{String.valueOf(bookDetailResponse.getBook_id())});
            b();
        }
    }

    public void b(int i) {
        synchronized (a.a) {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_update_chapter_datetime", Long.valueOf(System.currentTimeMillis() / 1000));
            this.b.update("book_table", contentValues, "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public List<BookDetailResponse> c() {
        List<BookDetailResponse> arrayList;
        synchronized (a.a) {
            a();
            arrayList = new ArrayList();
            Cursor query = this.b.query("book_table", null, "book_add_shelf = ?", new String[]{String.valueOf(BookEnum.ADD_SHELF.getValue())}, null, null, "last_read_time desc");
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

    private boolean e(int i) {
        boolean z;
        Cursor query = this.b.query("book_table", new String[]{"book_id"}, "book_id = ?", new String[]{String.valueOf(i)}, null, null, null);
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

    public BookDetailResponse c(int i) {
        BookDetailResponse bookDetailResponse;
        synchronized (a.a) {
            a();
            Cursor query = this.b.query("book_table", null, "book_id = ?", new String[]{String.valueOf(i)}, null, null, null);
            if (query.getColumnCount() != 0) {
                bookDetailResponse = null;
                while (query.moveToNext()) {
                    bookDetailResponse = new BookDetailResponse();
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
                }
            } else {
                bookDetailResponse = null;
            }
            query.close();
            b();
        }
        return bookDetailResponse;
    }

    public void d(int i) {
        synchronized (a.a) {
            a();
            this.b.delete("book_table", "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public void a(List<BookDetailResponse> list) {
        synchronized (a.a) {
            a();
            for (int i = 0; i < list.size(); i++) {
                BookDetailResponse bookDetailResponse = (BookDetailResponse) list.get(i);
                this.b.delete("book_table", "book_id = ?", new String[]{String.valueOf(bookDetailResponse.getBook_id())});
            }
            b();
        }
    }

    public void d() {
        synchronized (a.a) {
            a();
            this.b.delete("book_table", "book_is_recommend_data = ?", new String[]{String.valueOf(BookEnum.RECOMMEND_BOOK.getValue())});
            b();
        }
    }

    public void e() {
        synchronized (a.a) {
            a();
            this.b.delete("book_table", "last_chapter_index = ?", new String[]{"0"});
            b();
        }
    }

    public void f() {
        synchronized (a.a) {
            a();
            this.b.delete("book_table", null, null);
            b();
        }
    }
}
