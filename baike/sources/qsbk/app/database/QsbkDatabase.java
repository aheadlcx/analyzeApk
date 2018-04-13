package qsbk.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import qsbk.app.QsbkApp;
import qsbk.app.model.Vote;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;

public class QsbkDatabase {
    public static final String A = "a";
    public static final String ACTION = "action";
    public static final String CACHE_ARTICLE = "cache_article";
    public static final String CREATE_AT = "created_at";
    public static final String ICON = "icon";
    public static final String LAST_DEVICE = "last_device";
    public static final String LAST_VISITED_AT = "last_visited_at";
    public static final String LOGIN = "login";
    public static final String ROLE = "role";
    public static final String SESSION = "session";
    public static final String STATE = "state";
    public static final String T = "t";
    public static final String TARGET = "target";
    public static final String TOKEN = "token";
    public static final String TYPE = "type";
    public static final String T_ALL = "t_all";
    public static final String USER_EMAIL = "email";
    public static final String USER_ID = "user_id";
    public static final String USER_TABLE_NAME = "userInfo";
    public static final String VOTE_TABLE_NAME = "votes";
    private static QsbkDatabase a;
    private final String b = " create table cache_article(tid integer primary key,id long,content varchar,anonymous varchar,comment_count long,tag varchar,state varchar,vote_up integer,vote_down integer,create_at long,image varchar,allow_comment varchar,user_id long,published_at long)";
    private final String c = "CREATE TABLE userInfo( tid INTEGER PRIMARY KEY AUTOINCREMENT, user_id varchar(20),icon varchar(50),state varchar(2),last_visited_at varchar(50),role varchar(50),created_at varchar(50),login varchar(50),token varchar(50),t_all INTEGER,t INTEGER,a INTEGER,email varchar(50),last_device varchar(50));";
    private final String d = "CREATE TABLE votes( tid INTEGER PRIMARY KEY AUTOINCREMENT, session varchar(50),target varchar(50),action varchar(2),type varchar(2),state varchar(50));";
    private byte[] e = new byte[0];
    private Context f = null;
    private SQLiteDatabase g = null;
    private a h = null;

    private class a extends SQLiteOpenHelper {
        final /* synthetic */ QsbkDatabase a;

        public a(QsbkDatabase qsbkDatabase, Context context) {
            this.a = qsbkDatabase;
            super(context, "qiushibaike", null, 5);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(" create table cache_article(tid integer primary key,id long,content varchar,anonymous varchar,comment_count long,tag varchar,state varchar,vote_up integer,vote_down integer,create_at long,image varchar,allow_comment varchar,user_id long,published_at long)");
            sQLiteDatabase.execSQL("CREATE TABLE userInfo( tid INTEGER PRIMARY KEY AUTOINCREMENT, user_id varchar(20),icon varchar(50),state varchar(2),last_visited_at varchar(50),role varchar(50),created_at varchar(50),login varchar(50),token varchar(50),t_all INTEGER,t INTEGER,a INTEGER,email varchar(50),last_device varchar(50));");
            sQLiteDatabase.execSQL("CREATE TABLE votes( tid INTEGER PRIMARY KEY AUTOINCREMENT, session varchar(50),target varchar(50),action varchar(2),type varchar(2),state varchar(50));");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache_article");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS userInfo");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS votes");
            onCreate(sQLiteDatabase);
        }
    }

    private QsbkDatabase(Context context) {
        this.f = context;
        this.h = new a(this, this.f);
    }

    public static synchronized QsbkDatabase getInstance() {
        QsbkDatabase qsbkDatabase;
        synchronized (QsbkDatabase.class) {
            if (a == null) {
                a = new QsbkDatabase(QsbkApp.mContext);
            }
            qsbkDatabase = a;
        }
        return qsbkDatabase;
    }

    public void deleteUser(Integer num) {
        delete(USER_TABLE_NAME, num);
    }

    public boolean isHasVote(String str, String str2) {
        return queryVote(str, str2) != null;
    }

    public long insertVote(Vote vote) {
        HashMap hashMap = new HashMap();
        hashMap.put("session", vote.session);
        hashMap.put(TARGET, vote.target);
        hashMap.put("type", vote.type);
        hashMap.put("action", vote.action);
        hashMap.put("state", "0");
        return insert(VOTE_TABLE_NAME, hashMap);
    }

    public void deleteVote(Integer num) {
        delete(VOTE_TABLE_NAME, num);
    }

    public Integer queryVote(String str, String str2) {
        Throwable th;
        try {
            String str3 = "target= ? and type = ? ";
            String[] strArr = new String[]{str, str2};
            synchronized (this.e) {
                Integer valueOf;
                try {
                    a();
                    Cursor query = this.g.query(VOTE_TABLE_NAME, new String[]{b.c}, str3, strArr, null, null, null);
                    query.moveToFirst();
                    Integer num = null;
                    while (!query.isAfterLast()) {
                        try {
                            valueOf = Integer.valueOf(query.getInt(0));
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            valueOf = num;
                            th = th3;
                        }
                        try {
                            query.moveToNext();
                            num = valueOf;
                        } catch (Throwable th4) {
                            th = th4;
                            try {
                                throw th;
                            } catch (SQLiteException e) {
                                return valueOf;
                            }
                        }
                    }
                    query.close();
                    close();
                    return num;
                } catch (Throwable th5) {
                    th = th5;
                    valueOf = null;
                    throw th;
                }
            }
        } catch (SQLiteException e2) {
            return null;
        }
    }

    public void updateVote() {
        HashMap hashMap = new HashMap();
        hashMap.put("state", "1");
        a.update(VOTE_TABLE_NAME, hashMap, null);
    }

    public HashMap<String, Vote> queryVote() {
        HashMap<String, Vote> hashMap = new HashMap();
        synchronized (this.e) {
            try {
                TimeDelta timeDelta = new TimeDelta();
                a();
                Cursor rawQuery = this.g.rawQuery("select tid,session,type,target,action,state from votes order by target limit 0,500 ", null);
                rawQuery.moveToFirst();
                while (!rawQuery.isAfterLast()) {
                    String string = rawQuery.getString(2);
                    String string2 = rawQuery.getString(3);
                    Vote createFromDB = Vote.createFromDB(rawQuery.getString(1), string, string2, rawQuery.getString(4));
                    createFromDB.state = rawQuery.getString(5);
                    hashMap.put(string2 + "_" + string, createFromDB);
                    rawQuery.moveToNext();
                }
                rawQuery.close();
                LogUtil.d("get vote time use:" + timeDelta.getDelta() + " vote:" + hashMap.size());
                close();
            } catch (SQLiteException e) {
                close();
            } catch (Throwable th) {
                close();
            }
        }
        return hashMap;
    }

    public HashMap<String, Vote> queryWaitSendVote() {
        HashMap<String, Vote> hashMap = new HashMap();
        String str = "state = 0";
        String str2 = "target limit 0,100 ";
        String[] strArr = new String[]{b.c, "session", "type", TARGET, "action"};
        synchronized (this.e) {
            a();
            Cursor query = this.g.query(VOTE_TABLE_NAME, strArr, str, null, null, null, str2);
            query.moveToFirst();
            while (!query.isAfterLast()) {
                String string = query.getString(2);
                String string2 = query.getString(3);
                hashMap.put(string2 + "_" + string, Vote.createFromDB(query.getString(1), string, string2, query.getString(4)));
                query.moveToNext();
            }
            query.close();
            close();
        }
        return hashMap;
    }

    public long insert(String str, HashMap<String, String> hashMap) {
        long insert;
        ContentValues contentValues = new ContentValues();
        for (String str2 : hashMap.keySet()) {
            contentValues.put(str2, (String) hashMap.get(str2));
        }
        synchronized (this.e) {
            try {
                a();
                insert = this.g.insert(str, b.c, contentValues);
                close();
            } catch (SQLiteException e) {
                close();
                insert = 0;
            } catch (Throwable th) {
                close();
            }
        }
        return insert;
    }

    public void update(String str, HashMap<String, String> hashMap, Integer num) {
        ContentValues contentValues = new ContentValues();
        for (String str2 : hashMap.keySet()) {
            contentValues.put(str2, (String) hashMap.get(str2));
        }
        synchronized (this.e) {
            try {
                a();
                if (num != null) {
                    this.g.update(str, contentValues, "tid=?", new String[]{String.valueOf(num)});
                } else {
                    this.g.update(str, contentValues, null, null);
                }
                close();
            } catch (SQLiteException e) {
                close();
            } catch (Throwable th) {
                close();
            }
        }
    }

    public void delete(String str, Integer num) {
        synchronized (this.e) {
            a();
            this.g.delete(str, "tid=? ", new String[]{String.valueOf(num)});
            close();
        }
    }

    public Integer query(String str, String[] strArr, String str2, String[] strArr2) {
        Integer num;
        synchronized (this.e) {
            a();
            Cursor query = this.g.query(str, strArr, str2, strArr2, null, null, null);
            query.moveToFirst();
            num = null;
            while (!query.isAfterLast()) {
                num = Integer.valueOf(query.getInt(0));
                query.moveToNext();
            }
            query.close();
            close();
        }
        return num;
    }

    public boolean hasArticles(String str, String str2) {
        Cursor cursor = null;
        Boolean valueOf = Boolean.valueOf(false);
        synchronized (this.e) {
            a();
            if (TextUtils.isEmpty(str2)) {
                cursor = this.g.query(str, null, null, null, null, null, null);
                valueOf = Boolean.valueOf(cursor.moveToFirst());
            }
            cursor.close();
            close();
        }
        return valueOf.booleanValue();
    }

    private boolean a() {
        if (this.g == null || !this.g.isOpen()) {
            this.g = this.h.getWritableDatabase();
        }
        return true;
    }

    public void close() {
        if (this.g != null && this.g.isOpen()) {
            this.g.close();
        }
    }
}
