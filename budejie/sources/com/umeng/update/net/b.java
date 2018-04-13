package com.umeng.update.net;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import u.upd.m;

public class b {
    private static final String a = b.class.getName();
    private static final String b = "umeng_download_task_list";
    private static final String c = "UMENG_DATA";
    private static final String d = "cp";
    private static final String e = "url";
    private static final String f = "progress";
    private static final String g = "last_modified";
    private static final String h = "extra";
    private static Context i = null;
    private static final String j = "yyyy-MM-dd HH:mm:ss";
    private b$a k;

    private b() {
        this.k = new b$a(this, i);
    }

    public static b a(Context context) {
        if (i == null && context == null) {
            throw new NullPointerException();
        }
        if (i == null) {
            i = context;
        }
        return b$b.a;
    }

    public boolean a(String str, String str2) {
        boolean z;
        Exception exception;
        ContentValues contentValues = new ContentValues();
        contentValues.put("cp", str);
        contentValues.put("url", str2);
        contentValues.put("progress", Integer.valueOf(0));
        contentValues.put(g, m.a());
        try {
            String[] strArr = new String[]{str, str2};
            Cursor query = this.k.getReadableDatabase().query(b, new String[]{"progress"}, "cp=? and url=?", strArr, null, null, null, "1");
            if (query.getCount() > 0) {
                u.upd.b.c(a, "insert(" + str + ", " + str2 + "): " + " already exists in the db. Insert is cancelled.");
                z = false;
            } else {
                boolean z2;
                long insert = this.k.getWritableDatabase().insert(b, null, contentValues);
                if (insert == -1) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                try {
                    u.upd.b.c(a, "insert(" + str + ", " + str2 + "): " + "rowid=" + insert);
                    z = z2;
                } catch (Exception e) {
                    Exception exception2 = e;
                    z = z2;
                    exception = exception2;
                    u.upd.b.c(a, "insert(" + str + ", " + str2 + "): " + exception.getMessage(), exception);
                    return z;
                }
            }
            try {
                query.close();
            } catch (Exception e2) {
                exception = e2;
                u.upd.b.c(a, "insert(" + str + ", " + str2 + "): " + exception.getMessage(), exception);
                return z;
            }
        } catch (Exception e3) {
            exception = e3;
            z = false;
            u.upd.b.c(a, "insert(" + str + ", " + str2 + "): " + exception.getMessage(), exception);
            return z;
        }
        return z;
    }

    public void a(String str, String str2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("progress", Integer.valueOf(i));
        contentValues.put(g, m.a());
        String[] strArr = new String[]{str, str2};
        this.k.getWritableDatabase().update(b, contentValues, "cp=? and url=?", strArr);
        u.upd.b.c(a, "updateProgress(" + str + ", " + str2 + ", " + i + ")");
    }

    public void a(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(h, str3);
        contentValues.put(g, m.a());
        String[] strArr = new String[]{str, str2};
        this.k.getWritableDatabase().update(b, contentValues, "cp=? and url=?", strArr);
        u.upd.b.c(a, "updateExtra(" + str + ", " + str2 + ", " + str3 + ")");
    }

    public int b(String str, String str2) {
        int i;
        String[] strArr = new String[]{str, str2};
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{"progress"}, "cp=? and url=?", strArr, null, null, null, "1");
        if (query.getCount() > 0) {
            query.moveToFirst();
            i = query.getInt(0);
        } else {
            i = -1;
        }
        query.close();
        return i;
    }

    public String c(String str, String str2) {
        String str3 = null;
        String[] strArr = new String[]{str, str2};
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{h}, "cp=? and url=?", strArr, null, null, null, "1");
        if (query.getCount() > 0) {
            query.moveToFirst();
            str3 = query.getString(0);
        }
        query.close();
        return str3;
    }

    public Date d(String str, String str2) {
        Date date = null;
        String[] strArr = new String[]{str, str2};
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{g}, "cp=? and url=?", strArr, date, date, date, date);
        if (query.getCount() > 0) {
            query.moveToFirst();
            String string = query.getString(0);
            u.upd.b.c(a, "getLastModified(" + str + ", " + str2 + "): " + string);
            try {
                date = new SimpleDateFormat(j).parse(string);
            } catch (Exception e) {
                u.upd.b.c(a, e.getMessage());
            }
        }
        query.close();
        return date;
    }

    public void e(String str, String str2) {
        String[] strArr = new String[]{str, str2};
        this.k.getWritableDatabase().delete(b, "cp=? and url=?", strArr);
        u.upd.b.c(a, "delete(" + str + ", " + str2 + ")");
    }

    public List<String> a(String str) {
        String[] strArr = new String[]{str};
        Cursor query = this.k.getReadableDatabase().query(b, new String[]{"url"}, "cp=?", strArr, null, null, null, "1");
        List<String> arrayList = new ArrayList();
        query.moveToFirst();
        while (!query.isAfterLast()) {
            arrayList.add(query.getString(0));
            query.moveToNext();
        }
        query.close();
        return arrayList;
    }

    public void a(int i) {
        try {
            Date date = new Date(new Date().getTime() - ((long) (i * 1000)));
            this.k.getWritableDatabase().execSQL(" DELETE FROM umeng_download_task_list WHERE strftime('yyyy-MM-dd HH:mm:ss', last_modified)<=strftime('yyyy-MM-dd HH:mm:ss', '" + new SimpleDateFormat(j).format(date) + "')");
            u.upd.b.c(a, "clearOverdueTasks(" + i + ")" + " remove all tasks before " + new SimpleDateFormat(j).format(date));
        } catch (Exception e) {
            u.upd.b.b(a, e.getMessage());
        }
    }

    public void finalize() {
        this.k.close();
    }
}
