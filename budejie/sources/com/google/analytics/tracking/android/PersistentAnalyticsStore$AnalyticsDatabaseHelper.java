package com.google.analytics.tracking.android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.google.android.gms.common.util.VisibleForTesting;
import com.umeng.analytics.a;
import java.util.HashSet;
import java.util.Set;

@VisibleForTesting
class PersistentAnalyticsStore$AnalyticsDatabaseHelper extends SQLiteOpenHelper {
    private boolean mBadDatabase;
    private long mLastDatabaseCheckTime = 0;
    final /* synthetic */ PersistentAnalyticsStore this$0;

    boolean isBadDatabase() {
        return this.mBadDatabase;
    }

    void setBadDatabase(boolean z) {
        this.mBadDatabase = z;
    }

    PersistentAnalyticsStore$AnalyticsDatabaseHelper(PersistentAnalyticsStore persistentAnalyticsStore, Context context, String str) {
        this.this$0 = persistentAnalyticsStore;
        super(context, str, null, 1);
    }

    private boolean tablePresent(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            Cursor query = sQLiteDatabase2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean moveToFirst = query.moveToFirst();
                if (query == null) {
                    return moveToFirst;
                }
                query.close();
                return moveToFirst;
            } catch (SQLiteException e) {
                cursor = query;
                try {
                    Log.w("Error querying for table " + str);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return false;
                } catch (Throwable th2) {
                    cursor2 = cursor;
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            cursor = null;
            Log.w("Error querying for table " + str);
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public SQLiteDatabase getWritableDatabase() {
        if (!this.mBadDatabase || this.mLastDatabaseCheckTime + a.j <= PersistentAnalyticsStore.access$000(this.this$0).currentTimeMillis()) {
            SQLiteDatabase sQLiteDatabase = null;
            this.mBadDatabase = true;
            this.mLastDatabaseCheckTime = PersistentAnalyticsStore.access$000(this.this$0).currentTimeMillis();
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                PersistentAnalyticsStore.access$200(this.this$0).getDatabasePath(PersistentAnalyticsStore.access$100(this.this$0)).delete();
            }
            if (sQLiteDatabase == null) {
                sQLiteDatabase = super.getWritableDatabase();
            }
            this.mBadDatabase = false;
            return sQLiteDatabase;
        }
        throw new SQLiteException("Database creation failed");
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        if (VERSION.SDK_INT < 15) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                rawQuery.moveToFirst();
            } finally {
                rawQuery.close();
            }
        }
        if (tablePresent("hits2", sQLiteDatabase)) {
            validateColumnsPresent(sQLiteDatabase);
        } else {
            sQLiteDatabase.execSQL(PersistentAnalyticsStore.access$300());
        }
    }

    private void validateColumnsPresent(SQLiteDatabase sQLiteDatabase) {
        Object obj = null;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
        Set hashSet = new HashSet();
        try {
            String[] columnNames = rawQuery.getColumnNames();
            for (Object add : columnNames) {
                hashSet.add(add);
            }
            if (hashSet.remove("hit_id") && hashSet.remove("hit_url") && hashSet.remove("hit_string") && hashSet.remove("hit_time")) {
                if (!hashSet.remove("hit_app_id")) {
                    obj = 1;
                }
                if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                } else if (obj != null) {
                    sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
                    return;
                } else {
                    return;
                }
            }
            throw new SQLiteException("Database column missing");
        } finally {
            rawQuery.close();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        FutureApis.setOwnerOnlyReadWrite(sQLiteDatabase.getPath());
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
