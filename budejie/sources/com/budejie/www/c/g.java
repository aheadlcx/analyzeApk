package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.budejie.www.activity.view.SideBar;
import com.budejie.www.bean.Fans;
import java.util.ArrayList;
import java.util.Collection;

public class g {
    Context a;
    SQLiteDatabase b;
    c c;

    public g(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void e() {
        this.b = this.c.getWritableDatabase();
    }

    private void f() {
        this.b.close();
    }

    public void a(Collection<Fans> collection) {
        synchronized (c.a) {
            e();
            ContentValues contentValues = new ContentValues();
            for (Fans fans : collection) {
                if (!TextUtils.isEmpty(fans.getId())) {
                    a(contentValues, fans);
                    this.b.insert("follow_list", null, contentValues);
                }
            }
            f();
        }
    }

    public void a(Fans fans) {
        synchronized (c.a) {
            e();
            this.b.insert("follow_list", null, a(null, fans));
            f();
        }
    }

    public void a() {
        synchronized (c.a) {
            e();
            this.b.delete("follow_list", null, null);
            f();
        }
    }

    public void a(String str) {
        synchronized (c.a) {
            e();
            this.b.delete("follow_list", "user_id = ?", new String[]{str});
            f();
        }
    }

    public ArrayList<Fans> b() {
        ArrayList<Fans> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            e();
            Cursor query = this.b.query("follow_list", null, "user_id is not null AND user_id is not ''", null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    Fans fans = new Fans();
                    fans.setId(query.getString(query.getColumnIndex(UserTrackerConstants.USER_ID)));
                    arrayList.add(fans);
                }
                query.close();
            }
            f();
        }
        return arrayList;
    }

    public ArrayList<Fans> c() {
        ArrayList<Fans> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            e();
            Cursor query = this.b.query("follow_list", null, "user_name is not null AND user_name is not ''", null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    arrayList.add(a(query));
                }
                query.close();
            }
            f();
        }
        return arrayList;
    }

    public ArrayList<Fans> d() {
        ArrayList<Fans> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            e();
            Cursor query = this.b.query("follow_list", null, "user_name is not null AND user_name is not '' AND recent_contact_time != 0", null, null, null, "recent_contact_time DESC");
            if (query != null) {
                while (query.moveToNext()) {
                    Fans a = a(query);
                    a.setSortLetters(SideBar.a[1]);
                    arrayList.add(a);
                }
                query.close();
            }
            f();
        }
        return arrayList;
    }

    public int b(String str) {
        int update;
        synchronized (c.a) {
            e();
            ContentValues contentValues = new ContentValues();
            contentValues.put("recent_contact_time", Long.valueOf(System.currentTimeMillis()));
            update = this.b.update("follow_list", contentValues, "user_id = ?", new String[]{str});
            f();
        }
        return update;
    }

    private ContentValues a(ContentValues contentValues, Fans fans) {
        if (contentValues == null) {
            contentValues = new ContentValues();
        } else {
            contentValues.clear();
        }
        contentValues.put(UserTrackerConstants.USER_ID, fans.getId());
        contentValues.put("user_name", fans.getUsername());
        contentValues.put("profile_image", fans.getUserPic());
        return contentValues;
    }

    private Fans a(Cursor cursor) {
        Fans fans = new Fans();
        fans.setId(cursor.getString(cursor.getColumnIndex(UserTrackerConstants.USER_ID)));
        fans.setUsername(cursor.getString(cursor.getColumnIndex("user_name")));
        fans.setUserPic(cursor.getString(cursor.getColumnIndex("profile_image")));
        fans.setRecentContactTime(cursor.getLong(cursor.getColumnIndex("recent_contact_time")));
        return fans;
    }
}
