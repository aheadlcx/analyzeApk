package cn.v6.sixrooms.utils.phone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;

public class HistoryOpenHelper extends SQLiteOpenHelper {
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_PIC = "pic";
    public static final String COLUMN_RID = "rid";
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_USERNAME = "username";
    public static final String DB_NAME = "HistoryWatch.db";
    public static final String TABLE_NAME = "HistoryWatchData";
    public static final String createTableUpdate = "CREATE TABLE HistoryWatchData(_id integer primary key autoincrement,rid text,pic text,username text,level text,date text, uid varchar(50))";

    public HistoryOpenHelper(Context context) {
        super(context, DB_NAME, null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(createTableUpdate);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1 && i2 == 2 && !a(sQLiteDatabase, TABLE_NAME, COLUMN_UID)) {
            sQLiteDatabase.execSQL("alter table HistoryWatchData add [uid] varchar(50)");
        }
    }

    @SuppressLint({"NewApi"})
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (VERSION.SDK_INT >= 11) {
            super.onDowngrade(sQLiteDatabase, i, i2);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.database.sqlite.SQLiteDatabase r8, java.lang.String r9, java.lang.String r10) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = 0;
        r3 = "select * from sqlite_master where name = ? and sql like ?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r5 = 0;
        r4[r5] = r9;	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r5 = 1;
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r7 = "%";
        r6.<init>(r7);	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r7 = "%";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r6 = r6.toString();	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r4[r5] = r6;	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        r2 = r8.rawQuery(r3, r4);	 Catch:{ Exception -> 0x003d, all -> 0x004c }
        if (r2 == 0) goto L_0x003b;
    L_0x0029:
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x0059, all -> 0x004c }
        if (r3 == 0) goto L_0x003b;
    L_0x002f:
        if (r2 == 0) goto L_0x003a;
    L_0x0031:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x003a;
    L_0x0037:
        r2.close();
    L_0x003a:
        return r0;
    L_0x003b:
        r0 = r1;
        goto L_0x002f;
    L_0x003d:
        r0 = move-exception;
        r0 = r2;
    L_0x003f:
        if (r0 == 0) goto L_0x005c;
    L_0x0041:
        r2 = r0.isClosed();
        if (r2 != 0) goto L_0x005c;
    L_0x0047:
        r0.close();
        r0 = r1;
        goto L_0x003a;
    L_0x004c:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0058;
    L_0x004f:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0058;
    L_0x0055:
        r2.close();
    L_0x0058:
        throw r0;
    L_0x0059:
        r0 = move-exception;
        r0 = r2;
        goto L_0x003f;
    L_0x005c:
        r0 = r1;
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.utils.phone.HistoryOpenHelper.a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String):boolean");
    }
}
