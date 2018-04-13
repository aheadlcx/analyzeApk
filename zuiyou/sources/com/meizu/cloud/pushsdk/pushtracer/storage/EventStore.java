package com.meizu.cloud.pushsdk.pushtracer.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.pushtracer.dataload.DataLoad;
import com.meizu.cloud.pushsdk.pushtracer.dataload.TrackerDataload;
import com.meizu.cloud.pushsdk.pushtracer.emitter.EmittableEvents;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventStore implements Store {
    private String TAG = EventStore.class.getSimpleName();
    private String[] allColumns = new String[]{"id", "eventData", "dateCreated"};
    private SQLiteDatabase database;
    private EventStoreHelper dbHelper;
    private long lastInsertedRowId = -1;
    private int sendLimit;

    public EventStore(Context context, int i) {
        this.dbHelper = EventStoreHelper.getInstance(context, getDataBaseName(context));
        open();
        this.sendLimit = i;
    }

    private String getDataBaseName(Context context) {
        Object processName = MzSystemUtils.getProcessName(context);
        if (TextUtils.isEmpty(processName)) {
            return EventStoreHelper.DATABASE_NAME;
        }
        return processName + "_" + EventStoreHelper.DATABASE_NAME;
    }

    public void add(DataLoad dataLoad) {
        insertEvent(dataLoad);
    }

    public boolean isOpen() {
        return isDatabaseOpen();
    }

    public void open() {
        if (!isDatabaseOpen()) {
            try {
                this.database = this.dbHelper.getWritableDatabase();
                this.database.enableWriteAheadLogging();
            } catch (Exception e) {
                Logger.e(this.TAG, " open database error " + e.getMessage(), new Object[0]);
            }
        }
    }

    public void close() {
        this.dbHelper.close();
    }

    public long insertEvent(DataLoad dataLoad) {
        if (isDatabaseOpen()) {
            byte[] serialize = serialize(dataLoad.getMap());
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("eventData", serialize);
            this.lastInsertedRowId = this.database.insert(EventStoreHelper.TABLE_EVENTS, null, contentValues);
        }
        Logger.d(this.TAG, "Added event to database: " + this.lastInsertedRowId, new Object[0]);
        return this.lastInsertedRowId;
    }

    public boolean removeEvent(long j) {
        int i = -1;
        if (isDatabaseOpen()) {
            i = this.database.delete(EventStoreHelper.TABLE_EVENTS, "id=" + j, null);
        }
        Logger.d(this.TAG, "Removed event from database: " + j, new Object[0]);
        return i == 1;
    }

    public boolean removeAllEvents() {
        int i = -1;
        if (isDatabaseOpen()) {
            i = this.database.delete(EventStoreHelper.TABLE_EVENTS, null, null);
        }
        Logger.d(this.TAG, "Removing all events from database.", new Object[0]);
        return i == 0;
    }

    public static byte[] serialize(Map<String, String> map) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> deserializer(byte[] bArr) {
        Exception e;
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            HashMap hashMap = (HashMap) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return hashMap;
        } catch (ClassNotFoundException e2) {
            e = e2;
        } catch (IOException e3) {
            e = e3;
        }
        e.printStackTrace();
        return null;
    }

    public List<Map<String, Object>> queryDatabase(String str, String str2) {
        List<Map<String, Object>> arrayList = new ArrayList();
        if (isDatabaseOpen()) {
            Cursor query = this.database.query(EventStoreHelper.TABLE_EVENTS, this.allColumns, str, null, null, null, str2);
            query.moveToFirst();
            while (!query.isAfterLast()) {
                Map hashMap = new HashMap();
                hashMap.put("id", Long.valueOf(query.getLong(0)));
                hashMap.put("eventData", deserializer(query.getBlob(1)));
                hashMap.put("dateCreated", query.getString(2));
                query.moveToNext();
                arrayList.add(hashMap);
            }
            query.close();
        }
        return arrayList;
    }

    public long getSize() {
        if (isDatabaseOpen()) {
            return DatabaseUtils.queryNumEntries(this.database, EventStoreHelper.TABLE_EVENTS);
        }
        return 0;
    }

    public long getLastInsertedRowId() {
        return this.lastInsertedRowId;
    }

    public EmittableEvents getEmittableEvents() {
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        for (Map map : getDescEventsInRange(this.sendLimit)) {
            TrackerDataload trackerDataload = new TrackerDataload();
            trackerDataload.addMap((Map) map.get("eventData"));
            linkedList.add((Long) map.get("id"));
            arrayList.add(trackerDataload);
        }
        return new EmittableEvents(arrayList, linkedList);
    }

    public Map<String, Object> getEvent(long j) {
        List queryDatabase = queryDatabase("id=" + j, null);
        if (queryDatabase.isEmpty()) {
            return null;
        }
        return (Map) queryDatabase.get(0);
    }

    public List<Map<String, Object>> getAllEvents() {
        return queryDatabase(null, null);
    }

    public List<Map<String, Object>> getDescEventsInRange(int i) {
        return queryDatabase(null, "id ASC LIMIT " + i);
    }

    public boolean isDatabaseOpen() {
        return this.database != null && this.database.isOpen();
    }
}
