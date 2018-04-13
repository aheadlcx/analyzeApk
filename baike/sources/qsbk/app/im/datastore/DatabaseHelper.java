package qsbk.app.im.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.qiushibaike.statsdk.StatSDK;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.QsbkApp;

public class DatabaseHelper {
    public static final String CREATE_GROUPS;
    public static final String CREATE_GROUP_MESSAGES;
    public static final String CREATE_TABLE_CHATS = new StringBuffer("CREATE TABLE IF NOT EXISTS ").append(TABLE_CHAT).append("( ").append("uid").append(" INTEGER,").append("name").append(" varchar(50),").append("icon").append(" varchar(128),").append("msgid").append(" varchar(16),").append("data").append(" TEXT,").append("t").append(" INTEGER,").append("type").append(" INTEGER,").append("at").append(" INTEGER,").append(DatabaseHelper$ChatRow._AT_TYPE).append(" INTEGER,").append("PRIMARY KEY (").append("uid").append(", ").append("type").append("));").toString().toLowerCase();
    public static final String CREATE_TABLE_FOUND_FRAGMENT = "create table if not exists found_fragment(type integer, json text);";
    public static final String CREATE_TABLE_GROUP_NOTICE = "create table if not exists group_notice(id integer primary key autoincrement, type integer, json text, state integer, t integer);";
    public static final String CREATE_TABLE_LATEST_USED_COLLECTION = "create table if not exists latest_used_collection(id integer primary key autoincrement, type integer, usedtime integer, data text);";
    public static final String CREATE_TABLE_MESSAGES = new StringBuffer("CREATE TABLE IF NOT EXISTS ").append(TABLE_MESSAGES).append("( ").append("id").append(" INTEGER PRIMARY KEY AUTOINCREMENT,").append("uid").append(" INTEGER,").append("msgid").append(" varchar(16),").append("inout").append(" INTEGER,").append("t").append(" INTEGER,").append("msgsrc").append(" varchar(128),").append("type").append(" INTEGER,").append("data").append(" TEXT,").append("state").append(" INTEGER,").append("icon").append(" varchar(128),").append("name").append(" varchar(50),").append(DatabaseHelper$MessageRow._MTYPE).append(" INTEGER);").toString().toLowerCase();
    public static final String CREATE_TABLE_SYNCMSG = new StringBuffer().append("CREATE TABLE IF NOT EXISTS ").append(TABLE_SYNCMSG).append("( ").append("id").append(" INTEGER,").append("type").append(" INTEGER,").append(DatabaseHelper$SyncMsgRow._PRE).append(" INTEGER,").append(DatabaseHelper$SyncMsgRow._CUR).append(" INTEGER);").toString();
    @Deprecated
    public static final String CREATE_TABLE_USERS = new StringBuffer("CREATE TABLE IF NOT EXISTS ").append(TABLE_USERS).append("( ").append("uid").append(" INTEGER PRIMARY KEY,").append("name").append(" varchar(50),").append("icon").append(" varchar(128));").toString().toLowerCase();
    public static final String CREAT_TABLE_DRAFTS = new StringBuffer().append("CREATE TABLE IF NOT EXISTS ").append(TABLE_DRAFTS).append("( ").append("uid").append(" INTEGER,").append("data").append(" TEXT,").append("type").append(" INTEGER,").append("t").append(" INTEGER,").append("PRIMARY KEY (").append("uid").append(", ").append("type").append("));").toString().toLowerCase();
    public static final String TABLE_CHAT = "chats";
    public static final String TABLE_DRAFTS = "drafts";
    public static final String TABLE_FOUND_FRAGMENT = "found_fragment";
    public static final String TABLE_GROUPS = "groups";
    public static final String TABLE_GROUP_MESSAGES = "g_messages";
    public static final String TABLE_GROUP_NOTICE = "group_notice";
    public static final String TABLE_LATEST_USED_COLLECTION = "latest_used_collection";
    public static final String TABLE_MESSAGES = "messages";
    public static final String TABLE_SYNCMSG = "sync_msg";
    @Deprecated
    public static final String TABLE_USERS = "users";
    private static DatabaseHelper a;
    private static String b;
    private final byte[] c = new byte[0];
    private SQLiteDatabase d = null;
    private DatabaseHelper$a e = null;
    private List<DatabaseHelper$LifeCycleListener> f;

    @Deprecated
    public interface UserRow {
        public static final String _ICON = "icon";
        public static final String _ID = "uid";
        public static final String _NAME = "name";
    }

    static {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("create table if not exists ").append(TABLE_GROUPS).append("( ").append("id").append(" integer primary key autoincrement, ").append("gid").append(" integer,").append("t").append(" integer,");
        for (int i2 = 0; i2 < 20; i2++) {
            stringBuffer.append("field" + i2).append(" numeric,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(");");
        CREATE_GROUPS = stringBuffer.toString().toLowerCase();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("create table if not exists ").append(TABLE_GROUP_MESSAGES).append("( ").append("id").append(" integer primary key autoincrement, ").append("inout").append(" INTEGER,").append("uid").append(" INTEGER,").append("msgid").append(" varchar(16),").append("t").append(" INTEGER,").append("msgsrc").append(" varchar(128),").append("type").append(" INTEGER,").append("data").append(" TEXT,").append("state").append(" INTEGER,").append("icon").append(" varchar(128),").append("name").append(" varchar(50),").append("gid").append(" integer,");
        while (i < 20) {
            stringBuffer2.append("field" + i).append(" numeric,");
            i++;
        }
        stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
        stringBuffer2.append(");");
        CREATE_GROUP_MESSAGES = stringBuffer2.toString().toLowerCase();
    }

    private DatabaseHelper(Context context, String str) {
        this.e = new DatabaseHelper$a(context.getApplicationContext(), str, null, 12);
        b = str;
        this.f = new ArrayList();
    }

    public static synchronized DatabaseHelper getInstance(Context context, String str) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            if (a == null) {
                a = new DatabaseHelper(context.getApplicationContext(), str);
            } else if (str != null) {
                if (!TextUtils.equals(str, b)) {
                    a.release();
                    a = new DatabaseHelper(context.getApplicationContext(), str);
                }
            }
            databaseHelper = a;
        }
        return databaseHelper;
    }

    public static ContentValues map2ContentValues(Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            String str = (String) entry.getKey();
            if (value == null) {
                contentValues.putNull(str);
            } else if (value instanceof Boolean) {
                contentValues.put(str, (Boolean) value);
            } else if (value instanceof Byte) {
                contentValues.put(str, (Byte) value);
            } else if (value instanceof Short) {
                contentValues.put(str, (Short) value);
            } else if (value instanceof Integer) {
                contentValues.put(str, (Integer) value);
            } else if (value instanceof Float) {
                contentValues.put(str, (Float) value);
            } else if (value instanceof Long) {
                contentValues.put(str, (Long) value);
            } else if (value instanceof Double) {
                contentValues.put(str, (Double) value);
            } else if (value instanceof String) {
                contentValues.put(str, (String) value);
            } else if (value instanceof byte[]) {
                contentValues.put(str, (byte[]) value);
            } else if (value instanceof ContentValues) {
                contentValues.putAll((ContentValues) value);
            } else {
                contentValues.put(str, value.toString());
            }
        }
        return contentValues;
    }

    private static boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor cursor = null;
        boolean z = false;
        try {
            cursor = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
            if (cursor.getColumnIndex(str2) != -1) {
                z = true;
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return z;
    }

    private static void a(String str, String str2, ContentValues contentValues, Throwable th) {
        String str3;
        Context context = QsbkApp.mContext;
        String str4 = "db_exception";
        String str5 = "table" + str;
        String contentValues2 = contentValues.toString();
        if (th == null) {
            str3 = "";
        } else {
            str3 = th.toString();
        }
        StatSDK.onEvent(context, str4, str5, contentValues2, str2, str3);
    }

    public synchronized void release() {
        for (DatabaseHelper$LifeCycleListener databaseHelper$LifeCycleListener : this.f) {
            if (databaseHelper$LifeCycleListener != null) {
                databaseHelper$LifeCycleListener.onRelease();
            }
        }
        this.f.clear();
        if (this.e != null) {
            this.e.close();
            this.e = null;
        }
        if (this.d != null) {
            this.d.close();
        }
        this.d = null;
        a = null;
    }

    private synchronized void a() {
        if (this.d == null || !this.d.isOpen()) {
            this.d = this.e.getWritableDatabase();
        }
    }

    private synchronized void b() {
    }

    public synchronized void doCloseDatabase() {
        this.d.close();
    }

    public <T> T query(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, DatabaseHelper$RowMapping<T> databaseHelper$RowMapping) {
        Throwable th;
        if (databaseHelper$RowMapping == null) {
            return null;
        }
        T map;
        synchronized (this.c) {
            a();
            Cursor query;
            try {
                query = this.d.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
                try {
                    map = databaseHelper$RowMapping.map(query);
                    if (query != null) {
                        query.close();
                    }
                    b();
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    b();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                b();
                throw th;
            }
        }
        return map;
    }

    public long insert(String str, String str2, ContentValues contentValues) {
        long insert;
        synchronized (this.c) {
            a();
            try {
                insert = this.d.insert(str, str2, contentValues);
                b();
            } catch (Throwable th) {
                b();
            }
        }
        return insert;
    }

    public long insert(String str, String str2, Map<String, Object> map) {
        return insert(str, str2, map2ContentValues(map));
    }

    public int insert(String str, String str2, List<ContentValues> list) {
        Throwable th;
        int i;
        if (list == null || list.isEmpty()) {
            return -1;
        }
        synchronized (this.c) {
            a();
            this.d.beginTransaction();
            int i2 = 0;
            ContentValues contentValues = null;
            try {
                int i3 = 0;
                ContentValues contentValues2 = null;
                for (ContentValues contentValues3 : list) {
                    try {
                        try {
                            if (this.d.insert(str, str2, contentValues3) > -1) {
                                i3++;
                            }
                            contentValues2 = contentValues3;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            i2 = i3;
                            contentValues = contentValues3;
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        ContentValues contentValues4 = contentValues2;
                        i2 = i3;
                        contentValues = contentValues4;
                    }
                }
                this.d.setTransactionSuccessful();
                this.d.endTransaction();
                b();
                i = i3;
            } catch (Throwable th5) {
                th = th5;
                try {
                    th.printStackTrace();
                    a(str, str2 + Constants.ACCEPT_TIME_SEPARATOR_SP + list.size() + Config.TRACE_TODAY_VISIT_SPLIT + i2, contentValues, th);
                    i = i2;
                    return i;
                } finally {
                    this.d.endTransaction();
                    b();
                }
            }
        }
        return i;
    }

    public void replace(String str, String str2, List<ContentValues> list) {
        if (list != null && !list.isEmpty()) {
            synchronized (this.c) {
                a();
                this.d.beginTransaction();
                try {
                    for (ContentValues replace : list) {
                        this.d.replace(str, str2, replace);
                    }
                    this.d.setTransactionSuccessful();
                    this.d.endTransaction();
                    b();
                } catch (Throwable th) {
                    this.d.endTransaction();
                    b();
                }
            }
        }
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        int update;
        synchronized (this.c) {
            a();
            try {
                update = this.d.update(str, contentValues, str2, strArr);
                b();
            } catch (Throwable th) {
                b();
            }
        }
        return update;
    }

    public int delete(String str, String str2, String[] strArr) {
        int delete;
        synchronized (this.c) {
            a();
            try {
                delete = this.d.delete(str, str2, strArr);
                b();
            } catch (Throwable th) {
                b();
            }
        }
        return delete;
    }

    public int deleteIds(String str, String str2, String... strArr) {
        int i = 0;
        if (strArr == null || strArr.length <= 0) {
            return 0;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr2 = new String[strArr.length];
        while (i < strArr2.length) {
            stringBuffer.append('?').append(',');
            strArr2[i] = String.valueOf(strArr[i]);
            i++;
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return delete(str, str2 + " in(" + stringBuffer.toString() + ")", strArr2);
    }

    public int deleteAll(String str) {
        return delete(str, "1", null);
    }

    public void batchSql(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            synchronized (this.c) {
                a();
                this.d.beginTransaction();
                try {
                    for (String execSQL : strArr) {
                        this.d.execSQL(execSQL);
                    }
                    this.d.setTransactionSuccessful();
                } finally {
                    this.d.endTransaction();
                    b();
                }
            }
        }
    }

    public int rawQuery(String str, String[] strArr) {
        int i = 0;
        Cursor cursor = null;
        synchronized (this.c) {
            a();
            try {
                cursor = this.d.rawQuery(str, strArr);
                if (cursor.moveToFirst()) {
                    i = cursor.getInt(0);
                }
                b();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                b();
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    public int[] rawQuery(String[] strArr, String[][] strArr2) {
        int i = 0;
        int length = strArr.length;
        int[] iArr = new int[length];
        Cursor[] cursorArr = new Cursor[length];
        synchronized (this.c) {
            a();
            int i2 = 0;
            while (i2 < length) {
                try {
                    cursorArr[i2] = this.d.rawQuery(strArr[i2], strArr2[i2]);
                    if (cursorArr[i2].moveToFirst()) {
                        iArr[i2] = cursorArr[i2].getInt(0);
                    }
                    if (i2 > 0) {
                        cursorArr[i2 - 1].close();
                    }
                    i2++;
                } catch (Throwable th) {
                    b();
                    if (cursorArr != null) {
                        length = cursorArr.length;
                        while (i < length) {
                            Cursor cursor = cursorArr[i];
                            if (!(cursor == null || cursor.isClosed())) {
                                cursor.close();
                            }
                            i++;
                        }
                    }
                }
            }
            b();
            if (cursorArr != null) {
                i2 = cursorArr.length;
                while (i < i2) {
                    Cursor cursor2 = cursorArr[i];
                    if (!(cursor2 == null || cursor2.isClosed())) {
                        cursor2.close();
                    }
                    i++;
                }
            }
        }
        return iArr;
    }

    public void execSql(String str, Object[] objArr) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.c) {
                a();
                try {
                    this.d.execSQL(str, objArr);
                } finally {
                    b();
                }
            }
        }
    }

    public boolean addLifeCycleListener(DatabaseHelper$LifeCycleListener databaseHelper$LifeCycleListener) {
        if (this.f.contains(databaseHelper$LifeCycleListener)) {
            return false;
        }
        return this.f.add(databaseHelper$LifeCycleListener);
    }

    public boolean removeLifeCycleListener(DatabaseHelper$LifeCycleListener databaseHelper$LifeCycleListener) {
        return this.f.remove(databaseHelper$LifeCycleListener);
    }
}
