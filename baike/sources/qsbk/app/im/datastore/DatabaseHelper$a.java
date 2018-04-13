package qsbk.app.im.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.QiushiNotificationCountManager;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.QiuyouCircleNotificationItemView.Type;

class DatabaseHelper$a extends SQLiteOpenHelper {
    private boolean a = false;

    public DatabaseHelper$a(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_FOUND_FRAGMENT);
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_CHATS);
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_MESSAGES);
        sQLiteDatabase.execSQL(DatabaseHelper.CREAT_TABLE_DRAFTS);
        sQLiteDatabase.execSQL("create index idx_time on chats(t);");
        sQLiteDatabase.execSQL("create index idx_uid on messages(uid);");
        sQLiteDatabase.execSQL("create unique index idx_msgid on messages(msgid);");
        sQLiteDatabase.execSQL("create index idx_state on messages(state);");
        sQLiteDatabase.execSQL("create index idx_draft_time on drafts(t);");
        a(sQLiteDatabase);
        b(sQLiteDatabase);
        e(sQLiteDatabase);
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_LATEST_USED_COLLECTION);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_GROUPS);
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_GROUP_MESSAGES);
        sQLiteDatabase.execSQL("insert into sqlite_sequence (name, seq) values ( 'g_messages', 4611686018427387903 );");
        sQLiteDatabase.execSQL("create index idx_gid on groups(gid);");
        sQLiteDatabase.execSQL("create index idx_gmsg_gid on g_messages(gid);");
        sQLiteDatabase.execSQL("create unique index idx_gmsg_msgid on g_messages(msgid);");
        sQLiteDatabase.execSQL("create index idx_gmsg_state on g_messages(state);");
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_GROUP_NOTICE);
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_LATEST_USED_COLLECTION);
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        ChatMsg chatMsg = null;
        LogUtil.e("DEV 9----");
        if (QsbkApp.currentUser != null) {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            Cursor query = sQLiteDatabase2.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=?", new String[]{"20"}, null, null, "id DESC ", null);
            List<ChatMsg> arrayList = new ArrayList();
            query.moveToFirst();
            while (!query.isAfterLast()) {
                chatMsg = new ChatMsg();
                chatMsg.data = query.getString(query.getColumnIndex("data"));
                chatMsg.dbid = query.getLong(query.getColumnIndex("id"));
                chatMsg.uid = query.getString(query.getColumnIndex("uid"));
                arrayList.add(chatMsg);
                query.moveToNext();
            }
            query.close();
            for (ChatMsg chatMsg2 : arrayList) {
                try {
                    JSONObject jSONObject = new JSONObject(chatMsg2.data);
                    if (TextUtils.equals(chatMsg.uid, QiushiNotificationCountManager.QIUSHI_PUSH_UID)) {
                        jSONObject = jSONObject.optJSONObject("jump_data");
                        String str = "";
                        if (jSONObject != null) {
                            CharSequence optString = jSONObject.optString("m_type");
                            if (!TextUtils.isEmpty(optString)) {
                                if (Type.QIUSHI_SMILE.equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 4 where id=" + chatMsg2.dbid + h.b);
                                } else if (Type.QIUSHI_COMMENT.equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 5 where id=" + chatMsg2.dbid + h.b);
                                } else if (Type.QIUSHI_COMMENT_LIKE.equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 6 where id=" + chatMsg2.dbid + h.b);
                                } else if ("up".equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 7 where id=" + chatMsg2.dbid + h.b);
                                } else if (Type.QIUSHI_VIDEO_LOOP.equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 8 where id=" + chatMsg2.dbid + h.b);
                                } else if ("comment".equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 10 where id=" + chatMsg2.dbid + h.b);
                                } else if ("promote".equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 9 where id=" + chatMsg2.dbid + h.b);
                                } else if (Type.QIUSHI_COMMENT_AT.equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 12 where id=" + chatMsg2.dbid + h.b);
                                } else if (Type.QIUSHI_COMMENT_LIKE_TOTAL.equals(optString)) {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 11 where id=" + chatMsg2.dbid + h.b);
                                } else {
                                    sQLiteDatabase.execSQL("UPDATE messages SET mtype = 13 where id=" + chatMsg2.dbid + h.b);
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_SYNCMSG);
        sQLiteDatabase.execSQL("create index idx_type on sync_msg(type,id);");
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        if (!DatabaseHelper.a(sQLiteDatabase, DatabaseHelper.TABLE_CHAT, "at")) {
            sQLiteDatabase.execSQL("ALTER TABLE chats ADD COLUMN at INTEGER;");
            sQLiteDatabase.execSQL("UPDATE chats SET at = 0;");
        }
    }

    private void g(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("create temporary table chats_backup(uid, name, icon, msgid, data, t, type);");
            sQLiteDatabase.execSQL("insert into chats_backup select uid, name, icon, msgid, data, t, type from chats;");
            sQLiteDatabase.execSQL("drop table chats;");
            sQLiteDatabase.execSQL(new StringBuffer("CREATE TABLE IF NOT EXISTS ").append(DatabaseHelper.TABLE_CHAT).append("( ").append("uid").append(" INTEGER,").append("name").append(" varchar(50),").append("icon").append(" varchar(128),").append("msgid").append(" varchar(16),").append("data").append(" TEXT,").append("t").append(" INTEGER,").append("type").append(" INTEGER,").append("PRIMARY KEY (").append("uid").append(", ").append("type").append("));").toString().toLowerCase());
            sQLiteDatabase.execSQL("create index idx_time on chats(t);");
            sQLiteDatabase.execSQL("insert into chats select uid, name, icon, msgid, data, t, type from chats_backup;");
            sQLiteDatabase.execSQL("drop table chats_backup;");
            sQLiteDatabase.execSQL("ALTER TABLE drafts ADD COLUMN type INTEGER;");
            sQLiteDatabase.execSQL("UPDATE drafts SET type = 0;");
            sQLiteDatabase.execSQL("create temporary table drafts_backup(uid, data, type, t);");
            sQLiteDatabase.execSQL("insert into drafts_backup select uid, data, type, t from drafts;");
            sQLiteDatabase.execSQL("drop table drafts;");
            sQLiteDatabase.execSQL(DatabaseHelper.CREAT_TABLE_DRAFTS);
            sQLiteDatabase.execSQL("create index idx_draft_time on drafts(t);");
            sQLiteDatabase.execSQL("insert into drafts select uid, data, type, t from drafts_backup;");
            sQLiteDatabase.execSQL("drop table drafts_backup;");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void i(SQLiteDatabase sQLiteDatabase) {
        if (!DatabaseHelper.a(sQLiteDatabase, DatabaseHelper.TABLE_MESSAGES, DatabaseHelper$MessageRow._MTYPE)) {
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN mtype INTEGER;");
            sQLiteDatabase.execSQL("UPDATE messages SET mtype = 0;");
            if (QsbkApp.currentUser != null) {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                Cursor query = sQLiteDatabase2.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=?", new String[]{"20"}, null, null, "id DESC ", null);
                List<ChatMsg> arrayList = new ArrayList();
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    ChatMsg chatMsg = new ChatMsg();
                    chatMsg.data = query.getString(query.getColumnIndex("data"));
                    chatMsg.dbid = query.getLong(query.getColumnIndex("id"));
                    arrayList.add(chatMsg);
                    query.moveToNext();
                }
                query.close();
                for (ChatMsg chatMsg2 : arrayList) {
                    try {
                        CharSequence optString = new JSONObject(chatMsg2.data).optString("jump");
                        if (!TextUtils.isEmpty(optString)) {
                            if ("comment".equals(optString)) {
                                sQLiteDatabase.execSQL("UPDATE messages SET mtype = 3 where id=" + chatMsg2.dbid + h.b);
                            } else if (Type.TYPE_COMMENT_AT.equals(optString)) {
                                sQLiteDatabase.execSQL("UPDATE messages SET mtype = 2 where id=" + chatMsg2.dbid + h.b);
                            } else if (Type.TYPE_LIKE.equals(optString)) {
                                sQLiteDatabase.execSQL("UPDATE messages SET mtype = 1 where id=" + chatMsg2.dbid + h.b);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void j(SQLiteDatabase sQLiteDatabase) {
        c(sQLiteDatabase);
    }

    private void k(SQLiteDatabase sQLiteDatabase) {
        d(sQLiteDatabase);
    }

    private void l(SQLiteDatabase sQLiteDatabase) {
    }

    private void m(SQLiteDatabase sQLiteDatabase) {
        e(sQLiteDatabase);
    }

    private void n(SQLiteDatabase sQLiteDatabase) {
        if (!DatabaseHelper.a(sQLiteDatabase, DatabaseHelper.TABLE_CHAT, DatabaseHelper$ChatRow._AT_TYPE)) {
            sQLiteDatabase.execSQL("ALTER TABLE chats ADD COLUMN attype INTEGER;");
            sQLiteDatabase.execSQL("UPDATE chats SET attype = 0;");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (int i3 = i + 1; i3 <= i2; i3++) {
            switch (i3) {
                case 2:
                    sQLiteDatabase.execSQL("ALTER TABLE chats ADD COLUMN type INTEGER;");
                    sQLiteDatabase.execSQL("UPDATE chats SET type = 0;");
                    sQLiteDatabase.execSQL("UPDATE chats SET type = 2 WHERE uid = 22584215;");
                    break;
                case 3:
                    h(sQLiteDatabase);
                    break;
                case 4:
                    g(sQLiteDatabase);
                    break;
                case 5:
                    f(sQLiteDatabase);
                    break;
                case 6:
                    this.a = true;
                    i(sQLiteDatabase);
                    break;
                case 7:
                    if (!this.a) {
                        i(sQLiteDatabase);
                        break;
                    }
                    break;
                case 8:
                    j(sQLiteDatabase);
                    break;
                case 9:
                    k(sQLiteDatabase);
                    break;
                case 10:
                    l(sQLiteDatabase);
                    break;
                case 11:
                    m(sQLiteDatabase);
                    break;
                case 12:
                    n(sQLiteDatabase);
                    break;
                default:
                    break;
            }
        }
    }
}
