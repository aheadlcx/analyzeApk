package com.meizu.cloud.pushsdk.pushtracer.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;

public class EventStoreHelper extends SQLiteOpenHelper {
    public static final String COLUMN_DATE_CREATED = "dateCreated";
    public static final String COLUMN_EVENT_DATA = "eventData";
    public static final String COLUMN_ID = "id";
    public static final String DATABASE_NAME = "PushEvents.db";
    private static final int DATABASE_VERSION = 1;
    public static final String METADATA_DATE_CREATED = "dateCreated";
    public static final String METADATA_EVENT_DATA = "eventData";
    public static final String METADATA_ID = "id";
    public static final String TABLE_EVENTS = "events";
    private static final String TAG = EventStoreHelper.class.getName();
    private static final String queryCreateTable = "CREATE TABLE IF NOT EXISTS 'events' (id INTEGER PRIMARY KEY, eventData BLOB, dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
    private static final String queryDropTable = "DROP TABLE IF EXISTS 'events'";
    private static EventStoreHelper sInstance;

    public static EventStoreHelper getInstance(Context context, String str) {
        if (sInstance == null) {
            sInstance = new EventStoreHelper(context.getApplicationContext(), str);
        }
        return sInstance;
    }

    private EventStoreHelper(Context context) {
        this(context, DATABASE_NAME);
    }

    private EventStoreHelper(Context context, String str) {
        super(context, str, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(queryCreateTable);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.d(TAG, "Upgrading database from version " + i + " to " + i2 + ". Destroying old data now..", new Object[0]);
        sQLiteDatabase.execSQL(queryDropTable);
        onCreate(sQLiteDatabase);
    }
}
