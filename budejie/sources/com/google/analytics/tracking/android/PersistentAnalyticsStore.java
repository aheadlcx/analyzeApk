package com.google.analytics.tracking.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.google.android.gms.analytics.internal.Command;
import com.google.android.gms.common.util.VisibleForTesting;
import com.umeng.analytics.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.impl.client.DefaultHttpClient;

class PersistentAnalyticsStore implements AnalyticsStore {
    private static final String CREATE_HITS_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{HITS_TABLE, HIT_ID, HIT_TIME, HIT_URL, HIT_STRING, HIT_APP_ID});
    private static final String DATABASE_FILENAME = "google_analytics_v2.db";
    @VisibleForTesting
    static final String HITS_TABLE = "hits2";
    @VisibleForTesting
    static final String HIT_APP_ID = "hit_app_id";
    @VisibleForTesting
    static final String HIT_ID = "hit_id";
    @VisibleForTesting
    static final String HIT_STRING = "hit_string";
    @VisibleForTesting
    static final String HIT_TIME = "hit_time";
    @VisibleForTesting
    static final String HIT_URL = "hit_url";
    private Clock mClock;
    private final Context mContext;
    private final String mDatabaseName;
    private final PersistentAnalyticsStore$AnalyticsDatabaseHelper mDbHelper;
    private volatile Dispatcher mDispatcher;
    private long mLastDeleteStaleHitsTime;
    private final AnalyticsStoreStateListener mListener;

    PersistentAnalyticsStore(AnalyticsStoreStateListener analyticsStoreStateListener, Context context) {
        this(analyticsStoreStateListener, context, DATABASE_FILENAME);
    }

    @VisibleForTesting
    PersistentAnalyticsStore(AnalyticsStoreStateListener analyticsStoreStateListener, Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.mDatabaseName = str;
        this.mListener = analyticsStoreStateListener;
        this.mClock = new PersistentAnalyticsStore$1(this);
        this.mDbHelper = new PersistentAnalyticsStore$AnalyticsDatabaseHelper(this, this.mContext, this.mDatabaseName);
        this.mDispatcher = new SimpleNetworkDispatcher(new DefaultHttpClient(), this.mContext);
        this.mLastDeleteStaleHitsTime = 0;
    }

    @VisibleForTesting
    public void setClock(Clock clock) {
        this.mClock = clock;
    }

    @VisibleForTesting
    public PersistentAnalyticsStore$AnalyticsDatabaseHelper getDbHelper() {
        return this.mDbHelper;
    }

    public void setDispatch(boolean z) {
        this.mDispatcher = z ? new SimpleNetworkDispatcher(new DefaultHttpClient(), this.mContext) : new NoopDispatcher();
    }

    @VisibleForTesting
    void setDispatcher(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public void clearHits(long j) {
        boolean z = true;
        SQLiteDatabase writableDatabase = getWritableDatabase("Error opening database for clearHits");
        if (writableDatabase != null) {
            if (j == 0) {
                writableDatabase.delete(HITS_TABLE, null, null);
            } else {
                writableDatabase.delete(HITS_TABLE, "hit_app_id = ?", new String[]{Long.valueOf(j).toString()});
            }
            AnalyticsStoreStateListener analyticsStoreStateListener = this.mListener;
            if (getNumStoredHits() != 0) {
                z = false;
            }
            analyticsStoreStateListener.reportStoreIsEmpty(z);
        }
    }

    public void putHit(Map<String, String> map, long j, String str, Collection<Command> collection) {
        deleteStaleHits();
        removeOldHitIfFull();
        fillVersionParameter(map, collection);
        writeHitToDatabase(map, j, str);
    }

    private void fillVersionParameter(Map<String, String> map, Collection<Command> collection) {
        String substring = "&_v".substring(1);
        if (collection != null) {
            for (Command command : collection) {
                if (Command.APPEND_VERSION.equals(command.getId())) {
                    map.put(substring, command.getValue());
                    return;
                }
            }
        }
    }

    private void removeOldHitIfFull() {
        int numStoredHits = (getNumStoredHits() - 2000) + 1;
        if (numStoredHits > 0) {
            List peekHitIds = peekHitIds(numStoredHits);
            Log.v("Store full, deleting " + peekHitIds.size() + " hits to make room.");
            deleteHits((String[]) peekHitIds.toArray(new String[0]));
        }
    }

    private void writeHitToDatabase(Map<String, String> map, long j, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase("Error opening database for putHit");
        if (writableDatabase != null) {
            long parseLong;
            ContentValues contentValues = new ContentValues();
            contentValues.put(HIT_STRING, generateHitString(map));
            contentValues.put(HIT_TIME, Long.valueOf(j));
            if (map.containsKey(Fields.ANDROID_APP_UID)) {
                try {
                    parseLong = Long.parseLong((String) map.get(Fields.ANDROID_APP_UID));
                } catch (NumberFormatException e) {
                    parseLong = 0;
                }
            } else {
                parseLong = 0;
            }
            contentValues.put(HIT_APP_ID, Long.valueOf(parseLong));
            if (str == null) {
                str = "http://www.google-analytics.com/collect";
            }
            if (str.length() == 0) {
                Log.w("Empty path: not sending hit");
                return;
            }
            contentValues.put(HIT_URL, str);
            try {
                writableDatabase.insert(HITS_TABLE, null, contentValues);
                this.mListener.reportStoreIsEmpty(false);
            } catch (SQLiteException e2) {
                Log.w("Error storing hit");
            }
        }
    }

    static String generateHitString(Map<String, String> map) {
        Iterable arrayList = new ArrayList(map.size());
        for (Entry entry : map.entrySet()) {
            arrayList.add(HitBuilder.encode((String) entry.getKey()) + LoginConstants.EQUAL + HitBuilder.encode((String) entry.getValue()));
        }
        return TextUtils.join("&", arrayList);
    }

    List<String> peekHitIds(int i) {
        SQLiteException e;
        Throwable th;
        List<String> arrayList = new ArrayList();
        if (i <= 0) {
            Log.w("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase("Error opening database for peekHitIds.");
        if (writableDatabase == null) {
            return arrayList;
        }
        Cursor query;
        try {
            query = writableDatabase.query(HITS_TABLE, new String[]{HIT_ID}, null, null, null, null, String.format("%s ASC", new Object[]{HIT_ID}), Integer.toString(i));
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(query.getLong(0)));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    Log.w("Error in peekHits fetching hitIds: " + e.getMessage());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            Log.w("Error in peekHits fetching hitIds: " + e.getMessage());
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.analytics.tracking.android.Hit> peekHits(int r17) {
        /*
        r16 = this;
        r11 = new java.util.ArrayList;
        r11.<init>();
        r2 = "Error opening database for peekHits";
        r0 = r16;
        r2 = r0.getWritableDatabase(r2);
        if (r2 != 0) goto L_0x0011;
    L_0x000f:
        r2 = r11;
    L_0x0010:
        return r2;
    L_0x0011:
        r12 = 0;
        r3 = "hits2";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r5 = 1;
        r6 = "hit_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r13 = 0;
        r14 = "hit_id";
        r10[r13] = r14;	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r10 = java.lang.Integer.toString(r17);	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
        r12 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x017b, all -> 0x0175 }
        r12.<init>();	 Catch:{ SQLiteException -> 0x017b, all -> 0x0175 }
        r3 = r13.moveToFirst();	 Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
        if (r3 == 0) goto L_0x005f;
    L_0x0046:
        r4 = new com.google.analytics.tracking.android.Hit;	 Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
        r5 = 0;
        r3 = 0;
        r6 = r13.getLong(r3);	 Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
        r3 = 1;
        r8 = r13.getLong(r3);	 Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
        r4.<init>(r5, r6, r8);	 Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
        r12.add(r4);	 Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
        r3 = r13.moveToNext();	 Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
        if (r3 != 0) goto L_0x0046;
    L_0x005f:
        if (r13 == 0) goto L_0x0064;
    L_0x0061:
        r13.close();
    L_0x0064:
        r11 = 0;
        r3 = "hits2";
        r4 = 3;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0173 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0173 }
        r5 = 1;
        r6 = "hit_string";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0173 }
        r5 = 2;
        r6 = "hit_url";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0173 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x0173 }
        r14 = 0;
        r15 = "hit_id";
        r10[r14] = r15;	 Catch:{ SQLiteException -> 0x0173 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x0173 }
        r10 = java.lang.Integer.toString(r17);	 Catch:{ SQLiteException -> 0x0173 }
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x0173 }
        r2 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        if (r2 == 0) goto L_0x00cc;
    L_0x0099:
        r4 = r11;
    L_0x009a:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteCursor) r0;	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = r0;
        r2 = r2.getWindow();	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = r2.getNumRows();	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        if (r2 <= 0) goto L_0x0100;
    L_0x00a8:
        r2 = r12.get(r4);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = (com.google.analytics.tracking.android.Hit) r2;	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r5 = 1;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2.setHitString(r5);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = r12.get(r4);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = (com.google.analytics.tracking.android.Hit) r2;	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r5 = 2;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2.setHitUrl(r5);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
    L_0x00c4:
        r2 = r4 + 1;
        r4 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        if (r4 != 0) goto L_0x0187;
    L_0x00cc:
        if (r3 == 0) goto L_0x00d1;
    L_0x00ce:
        r3.close();
    L_0x00d1:
        r2 = r12;
        goto L_0x0010;
    L_0x00d4:
        r2 = move-exception;
        r3 = r2;
        r4 = r12;
        r2 = r11;
    L_0x00d8:
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0178 }
        r5.<init>();	 Catch:{ all -> 0x0178 }
        r6 = "Error in peekHits fetching hitIds: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0178 }
        r3 = r3.getMessage();	 Catch:{ all -> 0x0178 }
        r3 = r5.append(r3);	 Catch:{ all -> 0x0178 }
        r3 = r3.toString();	 Catch:{ all -> 0x0178 }
        com.google.analytics.tracking.android.Log.w(r3);	 Catch:{ all -> 0x0178 }
        if (r4 == 0) goto L_0x0010;
    L_0x00f4:
        r4.close();
        goto L_0x0010;
    L_0x00f9:
        r2 = move-exception;
    L_0x00fa:
        if (r12 == 0) goto L_0x00ff;
    L_0x00fc:
        r12.close();
    L_0x00ff:
        throw r2;
    L_0x0100:
        r5 = "HitString for hitId %d too large.  Hit will be deleted.";
        r2 = 1;
        r6 = new java.lang.Object[r2];	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r7 = 0;
        r2 = r12.get(r4);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = (com.google.analytics.tracking.android.Hit) r2;	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r8 = r2.getHitId();	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r6[r7] = r2;	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        r2 = java.lang.String.format(r5, r6);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        com.google.analytics.tracking.android.Log.w(r2);	 Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        goto L_0x00c4;
    L_0x011e:
        r2 = move-exception;
        r13 = r3;
    L_0x0120:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0169 }
        r3.<init>();	 Catch:{ all -> 0x0169 }
        r4 = "Error in peekHits fetching hitString: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0169 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0169 }
        r2 = r3.append(r2);	 Catch:{ all -> 0x0169 }
        r2 = r2.toString();	 Catch:{ all -> 0x0169 }
        com.google.analytics.tracking.android.Log.w(r2);	 Catch:{ all -> 0x0169 }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x0169 }
        r3.<init>();	 Catch:{ all -> 0x0169 }
        r4 = 0;
        r5 = r12.iterator();	 Catch:{ all -> 0x0169 }
    L_0x0144:
        r2 = r5.hasNext();	 Catch:{ all -> 0x0169 }
        if (r2 == 0) goto L_0x015c;
    L_0x014a:
        r2 = r5.next();	 Catch:{ all -> 0x0169 }
        r2 = (com.google.analytics.tracking.android.Hit) r2;	 Catch:{ all -> 0x0169 }
        r6 = r2.getHitParams();	 Catch:{ all -> 0x0169 }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x0169 }
        if (r6 == 0) goto L_0x0165;
    L_0x015a:
        if (r4 == 0) goto L_0x0164;
    L_0x015c:
        if (r13 == 0) goto L_0x0161;
    L_0x015e:
        r13.close();
    L_0x0161:
        r2 = r3;
        goto L_0x0010;
    L_0x0164:
        r4 = 1;
    L_0x0165:
        r3.add(r2);	 Catch:{ all -> 0x0169 }
        goto L_0x0144;
    L_0x0169:
        r2 = move-exception;
    L_0x016a:
        if (r13 == 0) goto L_0x016f;
    L_0x016c:
        r13.close();
    L_0x016f:
        throw r2;
    L_0x0170:
        r2 = move-exception;
        r13 = r3;
        goto L_0x016a;
    L_0x0173:
        r2 = move-exception;
        goto L_0x0120;
    L_0x0175:
        r2 = move-exception;
        r12 = r13;
        goto L_0x00fa;
    L_0x0178:
        r2 = move-exception;
        r12 = r4;
        goto L_0x00fa;
    L_0x017b:
        r2 = move-exception;
        r3 = r2;
        r4 = r13;
        r2 = r11;
        goto L_0x00d8;
    L_0x0181:
        r2 = move-exception;
        r3 = r2;
        r4 = r13;
        r2 = r12;
        goto L_0x00d8;
    L_0x0187:
        r4 = r2;
        goto L_0x009a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.analytics.tracking.android.PersistentAnalyticsStore.peekHits(int):java.util.List<com.google.analytics.tracking.android.Hit>");
    }

    @VisibleForTesting
    void setLastDeleteStaleHitsTime(long j) {
        this.mLastDeleteStaleHitsTime = j;
    }

    int deleteStaleHits() {
        boolean z = true;
        long currentTimeMillis = this.mClock.currentTimeMillis();
        if (currentTimeMillis <= this.mLastDeleteStaleHitsTime + a.i) {
            return 0;
        }
        this.mLastDeleteStaleHitsTime = currentTimeMillis;
        SQLiteDatabase writableDatabase = getWritableDatabase("Error opening database for deleteStaleHits.");
        if (writableDatabase == null) {
            return 0;
        }
        long currentTimeMillis2 = this.mClock.currentTimeMillis() - 2592000000L;
        int delete = writableDatabase.delete(HITS_TABLE, "HIT_TIME < ?", new String[]{Long.toString(currentTimeMillis2)});
        AnalyticsStoreStateListener analyticsStoreStateListener = this.mListener;
        if (getNumStoredHits() != 0) {
            z = false;
        }
        analyticsStoreStateListener.reportStoreIsEmpty(z);
        return delete;
    }

    @Deprecated
    void deleteHits(Collection<Hit> collection) {
        if (collection == null || collection.isEmpty()) {
            Log.w("Empty/Null collection passed to deleteHits.");
            return;
        }
        String[] strArr = new String[collection.size()];
        int i = 0;
        for (Hit hitId : collection) {
            int i2 = i + 1;
            strArr[i] = String.valueOf(hitId.getHitId());
            i = i2;
        }
        deleteHits(strArr);
    }

    void deleteHits(String[] strArr) {
        boolean z = true;
        if (strArr == null || strArr.length == 0) {
            Log.w("Empty hitIds passed to deleteHits.");
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase("Error opening database for deleteHits.");
        if (writableDatabase != null) {
            try {
                writableDatabase.delete(HITS_TABLE, String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                AnalyticsStoreStateListener analyticsStoreStateListener = this.mListener;
                if (getNumStoredHits() != 0) {
                    z = false;
                }
                analyticsStoreStateListener.reportStoreIsEmpty(z);
            } catch (SQLiteException e) {
                Log.w("Error deleting hits " + strArr);
            }
        }
    }

    int getNumStoredHits() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase writableDatabase = getWritableDatabase("Error opening database for getNumStoredHits.");
        if (writableDatabase != null) {
            try {
                cursor = writableDatabase.rawQuery("SELECT COUNT(*) from hits2", null);
                if (cursor.moveToFirst()) {
                    i = (int) cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                Log.w("Error getting numStoredHits");
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    public void dispatch() {
        Log.v("Dispatch running...");
        if (this.mDispatcher.okToDispatch()) {
            List peekHits = peekHits(40);
            if (peekHits.isEmpty()) {
                Log.v("...nothing to dispatch");
                this.mListener.reportStoreIsEmpty(true);
                return;
            }
            int dispatchHits = this.mDispatcher.dispatchHits(peekHits);
            Log.v("sent " + dispatchHits + " of " + peekHits.size() + " hits");
            deleteHits(peekHits.subList(0, Math.min(dispatchHits, peekHits.size())));
            if (dispatchHits == peekHits.size() && getNumStoredHits() > 0) {
                GAServiceManager.getInstance().dispatchLocalHits();
            }
        }
    }

    public Dispatcher getDispatcher() {
        return this.mDispatcher;
    }

    public void close() {
        try {
            this.mDbHelper.getWritableDatabase().close();
            this.mDispatcher.close();
        } catch (SQLiteException e) {
            Log.w("Error opening database for close");
        }
    }

    @VisibleForTesting
    PersistentAnalyticsStore$AnalyticsDatabaseHelper getHelper() {
        return this.mDbHelper;
    }

    private SQLiteDatabase getWritableDatabase(String str) {
        try {
            return this.mDbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Log.w(str);
            return null;
        }
    }
}
