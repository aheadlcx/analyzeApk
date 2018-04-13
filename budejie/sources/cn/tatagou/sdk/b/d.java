package cn.tatagou.sdk.b;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import cn.tatagou.sdk.android.TtgSDK;
import java.util.ArrayList;
import java.util.List;

public class d {
    private static volatile d a;

    public static d getInstance() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public synchronized void insertSearchPh(Context context, String str) {
        try {
            SQLiteDatabase openDatabase = c.getInstance(TtgSDK.getContext()).openDatabase();
            if (openDatabase != null) {
                try {
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    if (a(openDatabase, str)) {
                        openDatabase.execSQL("update searchhistory set time=? where name=?", new String[]{String.valueOf(currentTimeMillis), String.valueOf(str)});
                    } else {
                        openDatabase.execSQL("insert into searchhistory(name,time) values(?,?)", new String[]{str, String.valueOf(currentTimeMillis)});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    c.getInstance(TtgSDK.getContext()).closeDatabase();
                }
            }
            c.getInstance(TtgSDK.getContext()).closeDatabase();
        } catch (Exception e2) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.database.sqlite.SQLiteDatabase r8, java.lang.String r9) {
        /*
        r7 = this;
        r1 = 1;
        r2 = 0;
        r0 = 0;
        if (r8 == 0) goto L_0x0026;
    L_0x0005:
        r3 = "select * from searchhistory where name=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0033, all -> 0x0041 }
        r5 = 0;
        r4[r5] = r9;	 Catch:{ Exception -> 0x0033, all -> 0x0041 }
        r0 = r8.rawQuery(r3, r4);	 Catch:{ Exception -> 0x0033, all -> 0x0041 }
        if (r0 == 0) goto L_0x0026;
    L_0x0013:
        r3 = r0.moveToNext();	 Catch:{ Exception -> 0x0033, all -> 0x0051 }
        if (r3 == 0) goto L_0x0026;
    L_0x0019:
        if (r0 == 0) goto L_0x0024;
    L_0x001b:
        r2 = r0.isClosed();
        if (r2 != 0) goto L_0x0024;
    L_0x0021:
        r0.close();
    L_0x0024:
        r0 = r1;
    L_0x0025:
        return r0;
    L_0x0026:
        if (r0 == 0) goto L_0x0031;
    L_0x0028:
        r1 = r0.isClosed();
        if (r1 != 0) goto L_0x0031;
    L_0x002e:
        r0.close();
    L_0x0031:
        r0 = r2;
        goto L_0x0025;
    L_0x0033:
        r1 = move-exception;
        if (r0 == 0) goto L_0x003f;
    L_0x0036:
        r1 = r0.isClosed();
        if (r1 != 0) goto L_0x003f;
    L_0x003c:
        r0.close();
    L_0x003f:
        r0 = r2;
        goto L_0x0025;
    L_0x0041:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0045:
        if (r1 == 0) goto L_0x0050;
    L_0x0047:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x0050;
    L_0x004d:
        r1.close();
    L_0x0050:
        throw r0;
    L_0x0051:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tatagou.sdk.b.d.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    public synchronized List<String> queryTop10(Context context) {
        List<String> arrayList;
        arrayList = new ArrayList();
        try {
            SQLiteDatabase openDatabase = c.getInstance(TtgSDK.getContext()).openDatabase();
            if (openDatabase != null) {
                try {
                    Cursor rawQuery = openDatabase.rawQuery("select * from searchhistory order by time desc limit 10", null);
                    if (rawQuery != null) {
                        while (rawQuery.moveToNext()) {
                            arrayList.add(rawQuery.getString(rawQuery.getColumnIndex("name")));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.getInstance(TtgSDK.getContext()).closeDatabase();
                }
            }
            c.getInstance(TtgSDK.getContext()).closeDatabase();
        } catch (Exception e2) {
        }
        return arrayList;
    }

    public synchronized void deleteAllSearchHistory(Context context) {
        try {
            SQLiteDatabase openDatabase = c.getInstance(TtgSDK.getContext()).openDatabase();
            if (openDatabase != null) {
                try {
                    openDatabase.execSQL("delete from searchhistory", new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    c.getInstance(TtgSDK.getContext()).closeDatabase();
                }
            }
            c.getInstance(TtgSDK.getContext()).closeDatabase();
        } catch (Exception e2) {
        }
    }
}
