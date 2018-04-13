package com.spriteapp.booklibrary.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.spriteapp.booklibrary.enumeration.ChapterEnum;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.util.CollectionUtil;
import java.util.ArrayList;
import java.util.List;

public class c {
    private a a;
    private SQLiteDatabase b;

    public c(Context context) {
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

    public void a(List<BookChapterResponse> list, int i) {
        if (!CollectionUtil.isEmpty(list)) {
            synchronized (a.a) {
                a();
                this.b.beginTransaction();
                ContentValues contentValues = new ContentValues();
                for (BookChapterResponse bookChapterResponse : list) {
                    int chapter_id = bookChapterResponse.getChapter_id();
                    if (!b(i, chapter_id)) {
                        contentValues.clear();
                        contentValues.put("book_id", Integer.valueOf(i));
                        contentValues.put("chapter_id", Integer.valueOf(chapter_id));
                        contentValues.put("chapter_title", bookChapterResponse.getChapter_title());
                        contentValues.put("chapter_order", Integer.valueOf(bookChapterResponse.getChapter_order()));
                        contentValues.put("chapter_content_byte", Long.valueOf(bookChapterResponse.getChapter_content_byte()));
                        contentValues.put("chapter_is_sub", Integer.valueOf(bookChapterResponse.getChapter_is_sub()));
                        contentValues.put("chapter_price", Integer.valueOf(bookChapterResponse.getChapter_price()));
                        contentValues.put("chapter_is_vip", Integer.valueOf(bookChapterResponse.getChapter_is_vip()));
                        contentValues.put("chapter_read_state", Integer.valueOf(bookChapterResponse.getChapterReadState()));
                        this.b.insert("chapter_table", null, contentValues);
                    }
                }
                this.b.setTransactionSuccessful();
                this.b.endTransaction();
                b();
            }
        }
    }

    private boolean b(int i, int i2) {
        boolean z;
        Cursor query = this.b.query("chapter_table", new String[]{"book_id", "chapter_id"}, "book_id = ? and chapter_id = ?", new String[]{String.valueOf(i), String.valueOf(i2)}, null, null, null);
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

    public List<BookChapterResponse> a(int i) {
        List<BookChapterResponse> arrayList;
        synchronized (a.a) {
            arrayList = new ArrayList();
            a();
            String[] strArr = new String[]{String.valueOf(i)};
            Cursor query = this.b.query("chapter_table", null, "book_id = ?", strArr, null, null, null);
            if (query.getColumnCount() != 0) {
                while (query.moveToNext()) {
                    BookChapterResponse bookChapterResponse = new BookChapterResponse();
                    bookChapterResponse.setBookId(query.getInt(query.getColumnIndex("book_id")));
                    bookChapterResponse.setChapter_id(query.getInt(query.getColumnIndex("chapter_id")));
                    bookChapterResponse.setChapter_title(query.getString(query.getColumnIndex("chapter_title")));
                    bookChapterResponse.setChapter_is_vip(query.getInt(query.getColumnIndex("chapter_is_vip")));
                    bookChapterResponse.setChapterReadState(query.getInt(query.getColumnIndex("chapter_read_state")));
                    arrayList.add(bookChapterResponse);
                }
            }
            query.close();
            b();
        }
        return arrayList;
    }

    public void a(int i, int i2) {
        synchronized (a.a) {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("chapter_read_state", Integer.valueOf(ChapterEnum.HAS_READ.getCode()));
            this.b.update("chapter_table", contentValues, "book_id = ? and chapter_id = ?", new String[]{String.valueOf(i), String.valueOf(i2)});
            b();
        }
    }

    public void b(int i) {
        synchronized (a.a) {
            a();
            this.b.delete("chapter_table", "book_id = ?", new String[]{String.valueOf(i)});
            b();
        }
    }

    public void c() {
        synchronized (a.a) {
            a();
            this.b.delete("chapter_table", null, null);
            b();
        }
    }
}
