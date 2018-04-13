package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.budejie.www.bean.Voted;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class l {
    Context a;
    SQLiteDatabase b;
    c c;

    public l(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void c() {
        this.b = this.c.getWritableDatabase();
    }

    private void d() {
        this.b.close();
    }

    public String a(String str, String str2, String str3) {
        Object a;
        String str4 = "";
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            c();
            contentValues.put("pid", str);
            contentValues.put(IXAdRequestInfo.CELL_ID, str3);
            contentValues.put("vid", str2);
            a = a(str);
            if (TextUtils.isEmpty(a)) {
                a = b(str3);
            }
            if (TextUtils.isEmpty(a)) {
                this.b.insert("voted", null, contentValues);
            }
            d();
        }
        return a;
    }

    public void a(ArrayList<Voted> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            synchronized (c.a) {
                ContentValues contentValues = new ContentValues();
                c();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Voted voted = (Voted) it.next();
                    contentValues.put("pid", voted.pid);
                    contentValues.put("vid", voted.vid);
                    contentValues.put(IXAdRequestInfo.CELL_ID, voted.cid);
                    if (!TextUtils.isEmpty(a(voted.pid))) {
                        this.b.update("voted", contentValues, "pid=?", new String[]{voted.pid});
                    } else if (TextUtils.isEmpty(b(voted.cid))) {
                        this.b.insert("voted", null, contentValues);
                    } else {
                        this.b.update("voted", contentValues, "cid=?", new String[]{voted.cid});
                    }
                }
                d();
            }
        }
    }

    public String a(String str) {
        String str2;
        synchronized (c.a) {
            String str3 = "";
            Cursor query = this.b.query("voted", new String[]{"vid"}, "pid = " + str, null, null, null, null);
            if (query == null || !query.moveToFirst()) {
                str2 = str3;
            } else {
                str2 = query.getString(query.getColumnIndex("vid"));
            }
            query.close();
        }
        return str2;
    }

    public String b(String str) {
        String str2;
        synchronized (c.a) {
            String str3 = "";
            Cursor query = this.b.query("voted", new String[]{"vid"}, "cid = " + str, null, null, null, null);
            if (query == null || !query.moveToFirst()) {
                str2 = str3;
            } else {
                str2 = query.getString(query.getColumnIndex("vid"));
            }
            query.close();
        }
        return str2;
    }

    public List<Voted> a() {
        List<Voted> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            c();
            Cursor query = this.b.query("voted", new String[]{"pid", "vid", IXAdRequestInfo.CELL_ID}, null, null, null, null, null);
            if (query.getCount() > 0) {
                while (query.moveToNext()) {
                    Voted voted = new Voted();
                    voted.pid = query.getString(query.getColumnIndex("pid"));
                    voted.vid = query.getString(query.getColumnIndex("vid"));
                    voted.cid = query.getString(query.getColumnIndex(IXAdRequestInfo.CELL_ID));
                    arrayList.add(voted);
                }
            }
            query.close();
            d();
        }
        return arrayList;
    }

    public void b() {
        synchronized (c.a) {
            c();
            this.b.delete("voted", null, null);
            d();
        }
    }
}
