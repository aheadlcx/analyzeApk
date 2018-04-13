package cn.xiaochuankeji.tieba.push.b;

import android.arch.b.a.c;
import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.push.d;
import cn.xiaochuankeji.tieba.push.data.ChatRoom;
import cn.xiaochuankeji.tieba.push.data.ChatUser;
import cn.xiaochuankeji.tieba.push.data.XMessage;
import cn.xiaochuankeji.tieba.push.data.XSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.izuiyou.a.a.b;
import com.meizu.cloud.pushsdk.handler.impl.model.Statics;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public final class e {
    private static final Charset a = Charset.forName("UTF-8");
    private static final String[] b = new String[]{SpeechEvent.KEY_EVENT_SESSION_ID, "session_type", "local_id", "sync", "x_last_msg_id", Statics.TIME, "weight", "unread", NotificationCompat.CATEGORY_STATUS, "x_mask", "x_other", "x_room_id", "room_data", "x_sid", "x_msg"};

    private static String b(long j) {
        return String.valueOf("x_sequence_" + j);
    }

    public static String a(int i) {
        long c = a.g().c();
        if (i == 2) {
            return String.valueOf("anonymous_session_" + c);
        }
        return String.valueOf("x_session_" + c);
    }

    private static String c(long j) {
        return String.valueOf("x_room_" + j);
    }

    public static String a(XSession xSession) {
        return a(xSession.x_mask.id, xSession.x_sid);
    }

    public static String a(long j, long j2) {
        return String.valueOf("chat_" + j + "" + j2);
    }

    private static void b(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (session_id integer(64) DEFAULT 0,session_type integer(32),local_id integer(64) DEFAULT 0,sync integer(64) DEFAULT 0,x_last_msg_id integer(64) DEFAULT 0,time integer(64) DEFAULT 0,unread integer(64) DEFAULT 0,status integer(32) DEFAULT 0,weight integer(32) DEFAULT 0,x_mask blob,x_other blob,x_room_id integer(64) DEFAULT 0,room_data blob,x_sid integer(64) primary key,x_msg blob);");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS " + str + "_index ON " + str + "(" + Statics.TIME + "," + SpeechEvent.KEY_EVENT_SESSION_ID + "," + "unread" + "," + "weight" + ");");
    }

    private static void c(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (room_id integer(64),room_type integer(32),room_name blob,room_mask blob,room_data blob );");
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + " (msgid integer(64),fromuser integer(64),touser integer(64),content blob,mtype integer,time integer(64),unsup text,status integer DEFAULT 0,delete_status integer DEFAULT 0,sync_begin integer DEFAULT 0,sync_end integer DEFAULT 0,PRIMARY KEY (msgid));");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS " + str + "_index ON " + str + "(" + "msgid" + "," + "sync_begin" + "," + "sync_end" + ");");
    }

    @WorkerThread
    public static XSession a(int i, long j) {
        XSession xSession = null;
        SQLiteDatabase a = a.a();
        String a2 = a(i);
        if (a.b(a2)) {
            a2 = c.a(a2).a(b).a("x_sid=" + j, null).c("1").a().a();
            b.c(a2);
            Cursor rawQuery = a.rawQuery(a2, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        xSession = a(rawQuery);
                    }
                } finally {
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                }
            }
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
        return xSession;
    }

    public static boolean a(int i, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.isEmpty()) {
            return false;
        }
        SQLiteDatabase a = a.a();
        String a2 = a(i);
        a.beginTransaction();
        try {
            if (!a.b(a2)) {
                b(a, a2);
            }
            ChatUser chatUser = new ChatUser();
            Member q = a.g().q();
            chatUser.id = q.getId();
            chatUser.name = q.getRawName();
            chatUser.gender = q.getGender();
            chatUser.avatar = q.getAvatarID();
            chatUser.official = q.getOfficial();
            for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                int intValue = jSONObject.getIntValue("session_type");
                if (XSession.isSupport(intValue)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("message");
                    if (jSONObject2 != null) {
                        JSONObject jSONObject3 = jSONObject.getJSONObject("other");
                        if (jSONObject3 != null) {
                            Object obj;
                            long longValue;
                            int intValue2;
                            long longValue2;
                            long longValue3 = jSONObject.getLongValue(SpeechEvent.KEY_EVENT_SESSION_ID);
                            int intValue3 = jSONObject.getIntValue(NotificationCompat.CATEGORY_STATUS);
                            ChatUser chatUser2 = new ChatUser();
                            JSONObject jSONObject4 = jSONObject.getJSONObject("me");
                            if (intValue == 1) {
                                obj = chatUser;
                            } else {
                                longValue = jSONObject4.getLongValue("id");
                                String string = jSONObject4.getString("name");
                                intValue2 = jSONObject4.getIntValue("gender");
                                longValue2 = jSONObject4.getLongValue("avatar");
                                chatUser2.id = longValue;
                                chatUser2.name = string;
                                chatUser2.gender = intValue2;
                                chatUser2.avatar = longValue2;
                                ChatUser chatUser3 = chatUser2;
                            }
                            ChatUser chatUser4 = new ChatUser();
                            longValue = jSONObject3.getLongValue("id");
                            String string2 = jSONObject3.getString("name");
                            intValue2 = jSONObject3.getIntValue("gender");
                            longValue2 = jSONObject3.getLongValue("avatar");
                            int intValue4 = jSONObject3.getIntValue("official");
                            chatUser4.id = longValue;
                            chatUser4.name = string2;
                            chatUser4.gender = intValue2;
                            chatUser4.avatar = longValue2;
                            chatUser4.official = intValue4;
                            intValue4 = jSONObject.getIntValue("unread");
                            JSONObject jSONObject5 = jSONObject.getJSONObject("xroom");
                            ChatRoom chatRoom = new ChatRoom();
                            if (jSONObject5 != null) {
                                chatRoom.room_id = jSONObject5.getLongValue("xroom_id");
                                chatRoom.room_name = jSONObject5.getString(SpeechConstant.SUBJECT);
                                chatRoom.room_type = intValue;
                                chatRoom.room_data = jSONObject5;
                            }
                            longValue2 = jSONObject2.getLongValue("msgid");
                            XMessage xMessage = new XMessage();
                            xMessage.msg_id = longValue2;
                            xMessage.msg_type = jSONObject2.getIntValue("mtype");
                            xMessage.content = jSONObject2.getString("content");
                            xMessage.time = jSONObject2.getLongValue(Statics.TIME);
                            xMessage.unsup = jSONObject2.getString("unsup");
                            xMessage.msg_uid = jSONObject2.getLongValue("fromuser");
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(longValue3));
                            contentValues.put("session_type", Integer.valueOf(intValue));
                            contentValues.put("sync", Long.valueOf(longValue2));
                            contentValues.put("x_last_msg_id", Long.valueOf(longValue2));
                            contentValues.put(Statics.TIME, Long.valueOf(xMessage.time));
                            String str = "select x_last_msg_id from " + a2 + "  where " + "x_sid" + "=" + longValue;
                            long j = -1;
                            Cursor rawQuery = a.rawQuery(str, null);
                            if (rawQuery != null && rawQuery.moveToFirst()) {
                                j = rawQuery.getLong(0);
                            }
                            if (!(rawQuery == null || rawQuery.isClosed())) {
                                rawQuery.close();
                            }
                            if (longValue2 > j) {
                                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
                            }
                            if (intValue3 > 1) {
                                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(intValue3));
                            }
                            contentValues.put("weight", Integer.valueOf(0));
                            contentValues.put("x_mask", JSON.toJSONString(obj).getBytes(Charset.forName("UTF-8")));
                            contentValues.put("x_other", JSON.toJSONString(chatUser4).getBytes(Charset.forName("UTF-8")));
                            contentValues.put("x_room_id", Long.valueOf(chatRoom.room_id));
                            contentValues.put("room_data", JSON.toJSONString(chatRoom).getBytes(Charset.forName("UTF-8")));
                            contentValues.put("x_sid", Long.valueOf(longValue));
                            contentValues.put("x_msg", JSON.toJSONString(xMessage).getBytes(Charset.forName("UTF-8")));
                            if (j < longValue2 && intValue4 >= 0) {
                                contentValues.put("unread", Integer.valueOf(intValue4));
                            }
                            if (contentValues.size() > 0) {
                                int updateWithOnConflict;
                                if (intValue == 4) {
                                    updateWithOnConflict = a.updateWithOnConflict(a2, contentValues, "session_type=?", new String[]{String.valueOf(4)}, 4);
                                    if (updateWithOnConflict < 1) {
                                        a.insertWithOnConflict(a2, null, contentValues, 5);
                                    } else if (updateWithOnConflict > 1) {
                                        a.execSQL("delete from " + a2 + " where rowid in (select rowid from " + a2 + " where " + "session_type" + "=" + 4 + " limit " + (updateWithOnConflict - 1) + ")");
                                    }
                                } else {
                                    updateWithOnConflict = a.updateWithOnConflict(a2, contentValues, "x_sid=?", new String[]{String.valueOf(longValue)}, 4);
                                    if (updateWithOnConflict < 1) {
                                        a.insertWithOnConflict(a2, null, contentValues, 5);
                                    } else if (updateWithOnConflict > 1) {
                                        a.execSQL("delete from " + a2 + " where rowid in (select rowid from " + a2 + " where " + "x_sid" + "=" + longValue + " limit " + (updateWithOnConflict - 1) + ")");
                                    }
                                }
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            a.setTransactionSuccessful();
            return true;
        } finally {
            a.endTransaction();
        }
    }

    @Nullable
    private static XSession a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String str;
        long j = cursor.getLong(cursor.getColumnIndex(SpeechEvent.KEY_EVENT_SESSION_ID));
        int i = cursor.getInt(cursor.getColumnIndex("session_type"));
        long j2 = cursor.getLong(cursor.getColumnIndex("local_id"));
        long j3 = cursor.getLong(cursor.getColumnIndex("sync"));
        long j4 = cursor.getLong(cursor.getColumnIndex("x_last_msg_id"));
        long j5 = cursor.getLong(cursor.getColumnIndex(Statics.TIME));
        int i2 = cursor.getInt(cursor.getColumnIndex("unread"));
        int i3 = cursor.getInt(cursor.getColumnIndex(NotificationCompat.CATEGORY_STATUS));
        int i4 = cursor.getInt(cursor.getColumnIndex("weight"));
        byte[] blob = cursor.getBlob(cursor.getColumnIndex("x_mask"));
        byte[] blob2 = cursor.getBlob(cursor.getColumnIndex("x_other"));
        long j6 = cursor.getLong(cursor.getColumnIndex("x_room_id"));
        byte[] blob3 = cursor.getBlob(cursor.getColumnIndex("room_data"));
        long j7 = cursor.getLong(cursor.getColumnIndex("x_sid"));
        byte[] blob4 = cursor.getBlob(cursor.getColumnIndex("x_msg"));
        XSession xSession = new XSession();
        xSession.session_id = j;
        xSession.session_local_id = j2;
        xSession.session_type = i;
        xSession.x_sync = j3;
        xSession.x_last_msg_id = j4;
        xSession.time = j5;
        xSession.unread = i2;
        xSession.status = i3;
        xSession.weight = i4;
        if (blob != null) {
            str = new String(blob, a);
            if (!TextUtils.isEmpty(str)) {
                xSession.x_mask = (ChatUser) JSON.parseObject(str, ChatUser.class);
            }
        }
        if (blob2 != null) {
            str = new String(blob2, a);
            if (!TextUtils.isEmpty(str)) {
                xSession.x_other = (ChatUser) JSON.parseObject(str, ChatUser.class);
            }
        }
        xSession.x_room_id = j6;
        if (blob3 != null) {
            str = new String(blob3, a);
            if (!TextUtils.isEmpty(str)) {
                xSession.x_room = (ChatRoom) JSON.parseObject(str, ChatRoom.class);
            }
        }
        xSession.x_sid = j7;
        if (blob4 != null) {
            str = new String(blob4, a);
            if (!TextUtils.isEmpty(str)) {
                xSession.x_msg = (XMessage) JSON.parseObject(str, XMessage.class);
            }
        }
        return xSession;
    }

    public static ChatRoom a(long j, int i) {
        ChatRoom chatRoom = null;
        SQLiteDatabase a = a.a();
        String c = c(a.g().c());
        if (a.b(c)) {
            c = c.a(c).a(new String[]{"room_id", "room_type", "room_name", "room_mask", "room_data"}).a("room_id=" + j + " and " + "room_type" + "=" + i, null).c("1").a().a();
            b.c(c);
            Cursor rawQuery = a.rawQuery(c, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        chatRoom = b(rawQuery);
                    }
                } finally {
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                }
            }
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
        return chatRoom;
    }

    public static void a(ChatRoom chatRoom) {
        SQLiteDatabase a = a.a();
        String c = c(a.g().c());
        if (!a.b(c)) {
            c(a, c);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("room_id", Long.valueOf(chatRoom.room_id));
        contentValues.put("room_type", Integer.valueOf(chatRoom.room_type));
        contentValues.put("room_name", chatRoom.room_name);
        if (chatRoom.room_mask != null) {
            contentValues.put("room_mask", JSON.toJSONString(chatRoom.room_mask).getBytes(a));
        }
        if (chatRoom.room_data != null) {
            contentValues.put("room_data", chatRoom.room_data.toJSONString().getBytes(a));
        }
        if (((long) a.updateWithOnConflict(c, contentValues, "room_id=?", new String[]{String.valueOf(chatRoom.room_id)}, 4)) < 1) {
            a.insertWithOnConflict(c, null, contentValues, 5);
        }
    }

    @Nullable
    private static ChatRoom b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String str;
        long j = cursor.getLong(cursor.getColumnIndex("room_id"));
        int i = cursor.getInt(cursor.getColumnIndex("room_type"));
        String string = cursor.getString(cursor.getColumnIndex("room_name"));
        byte[] blob = cursor.getBlob(cursor.getColumnIndex("room_mask"));
        byte[] blob2 = cursor.getBlob(cursor.getColumnIndex("room_data"));
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.room_id = j;
        chatRoom.room_type = i;
        chatRoom.room_name = string;
        if (blob != null) {
            str = new String(blob, a);
            if (!TextUtils.isEmpty(str)) {
                chatRoom.room_mask = (ChatUser) JSON.parseObject(str, ChatUser.class);
            }
        }
        if (blob2 != null) {
            str = new String(blob2, a);
            if (!TextUtils.isEmpty(str)) {
                chatRoom.room_data = (JSONObject) JSON.parseObject(str, JSONObject.class);
            }
        }
        return chatRoom;
    }

    public static void b(XSession xSession) {
        String a = a(xSession.session_type);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            a2.beginTransaction();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(3));
                a2.updateWithOnConflict(a, contentValues, "x_sid=?", new String[]{String.valueOf(xSession.x_sid)}, 4);
                contentValues = new ContentValues();
                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(3));
                contentValues.put("delete_status", Integer.valueOf(3));
                a = a(xSession);
                if (a.b(a)) {
                    a2.updateWithOnConflict(a, contentValues, null, null, 4);
                }
                a2.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                a2.endTransaction();
            }
        }
    }

    @WorkerThread
    public static List<XSession> b(int i) {
        List linkedList = new LinkedList();
        SQLiteDatabase a = a.a();
        String a2 = a(i);
        if (a.b(a2)) {
            a2 = c.a(a2).a(b).a("status!=3", null).b("unread>0 desc,time desc").a().a();
            b.c(a2);
            Cursor rawQuery = a.rawQuery(a2, null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    linkedList.add(a(rawQuery));
                }
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
            }
        } else {
            b.c("No " + a2 + " table ");
        }
        return linkedList;
    }

    public static long c(int i) {
        String a = a(i);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            a = "select max(sync) from " + a;
            b.c(a);
            Cursor rawQuery = a2.rawQuery(a, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        long j = rawQuery.getLong(0);
                        return j;
                    } else if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                } finally {
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                }
            }
        }
        return 0;
    }

    @WorkerThread
    public static int a() {
        int i = 0;
        b.c(Thread.currentThread().getName());
        String a = a(1);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            a = "select sum(unread) from " + a + "  where " + NotificationCompat.CATEGORY_STATUS + "!=" + 3;
            b.c(a);
            Cursor rawQuery = a2.rawQuery(a, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        i = rawQuery.getInt(0);
                        if (!rawQuery.isClosed()) {
                            rawQuery.close();
                        }
                    } else if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                } catch (Throwable th) {
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                }
            }
        }
        return i;
    }

    @Deprecated
    public static void a(long j, long j2, int i, int i2) {
        if (j2 >= 1 && i == 1) {
            SQLiteDatabase a = a.a();
            String a2 = a(i);
            if (!a.b(a2)) {
                b(a, a2);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(j));
            contentValues.put("session_type", Integer.valueOf(i));
            contentValues.put("x_room_id", Integer.valueOf(0));
            contentValues.put("weight", Integer.valueOf(0));
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i2));
            contentValues.put("x_sid", Long.valueOf(j2));
            if (contentValues.size() > 0) {
                long updateWithOnConflict = (long) a.updateWithOnConflict(a2, contentValues, "x_sid=?", new String[]{String.valueOf(j2)}, 4);
                if (updateWithOnConflict < 1) {
                    a.insertWithOnConflict(a2, null, contentValues, 5);
                } else if (updateWithOnConflict > 1) {
                    a.execSQL("delete from " + a2 + " where rowid in (select rowid from " + a2 + " where " + "x_sid" + "=" + j2 + " limit " + (updateWithOnConflict - 1) + ")");
                }
            }
        }
    }

    @WorkerThread
    public static List<cn.xiaochuankeji.tieba.push.data.a> c(XSession xSession) {
        List linkedList = new LinkedList();
        SQLiteDatabase a = a.a();
        String a2 = a(xSession);
        if (a.b(a2)) {
            Cursor rawQuery = a.rawQuery("select max(msgid) from " + a2 + " where " + "sync_begin" + "=1", null);
            long j = rawQuery == null ? 0 : rawQuery.moveToFirst() ? rawQuery.getLong(0) : 0;
            String a3 = c.a(a2).a(new String[]{"msgid", "fromuser", "touser", "content", "mtype", Statics.TIME, "unsup", NotificationCompat.CATEGORY_STATUS, "sync_begin"}).a("(msgid>=" + j + " or " + "msgid" + "<0)  and " + NotificationCompat.CATEGORY_STATUS + "!=" + 3 + " and " + "delete_status" + "!=" + 3, null).b(Statics.TIME).a().a();
            b.c(a3);
            Cursor rawQuery2 = a.rawQuery(a3, null);
            if (rawQuery2 != null) {
                j = 0;
                int columnIndex = rawQuery2.getColumnIndex("msgid");
                int columnIndex2 = rawQuery2.getColumnIndex("fromuser");
                int columnIndex3 = rawQuery2.getColumnIndex("touser");
                int columnIndex4 = rawQuery2.getColumnIndex("content");
                int columnIndex5 = rawQuery2.getColumnIndex("mtype");
                int columnIndex6 = rawQuery2.getColumnIndex(Statics.TIME);
                int columnIndex7 = rawQuery2.getColumnIndex("unsup");
                int columnIndex8 = rawQuery2.getColumnIndex(NotificationCompat.CATEGORY_STATUS);
                ChatUser chatUser = xSession.x_mask;
                ChatUser chatUser2 = new ChatUser();
                chatUser2.id = xSession.x_other.id;
                chatUser2.avatar = xSession.x_other.avatar;
                chatUser2.gender = xSession.x_other.gender;
                chatUser2.name = xSession.x_other.name;
                chatUser2.official = xSession.x_other.official;
                while (rawQuery2.moveToNext()) {
                    long j2 = rawQuery2.getLong(columnIndex);
                    long j3 = rawQuery2.getLong(columnIndex2);
                    long j4 = rawQuery2.getLong(columnIndex3);
                    String str = new String(rawQuery2.getBlob(columnIndex4), Charset.forName("UTF-8"));
                    int i = rawQuery2.getInt(columnIndex5);
                    long j5 = rawQuery2.getLong(columnIndex6);
                    String string = rawQuery2.getString(columnIndex7);
                    int i2 = rawQuery2.getInt(columnIndex8);
                    cn.xiaochuankeji.tieba.push.data.a a4 = d.a(j, j5);
                    if (a4 != null) {
                        linkedList.add(a4);
                    }
                    cn.xiaochuankeji.tieba.push.data.a aVar = new cn.xiaochuankeji.tieba.push.data.a();
                    aVar.j = j2;
                    aVar.a = j3;
                    aVar.b = j4;
                    boolean z = chatUser.id == j3;
                    if (z) {
                        aVar.c = chatUser.avatar;
                        aVar.d = chatUser.gender;
                        aVar.e = chatUser.name;
                    } else {
                        aVar.c = chatUser2.avatar;
                        aVar.d = chatUser2.gender;
                        aVar.e = chatUser2.name;
                    }
                    aVar.l = string;
                    aVar.g = i;
                    aVar.i = d.a(z, i);
                    aVar.h = i2;
                    aVar.k = j5;
                    aVar.f = str;
                    linkedList.add(aVar);
                    j = j5;
                }
            }
        } else {
            b.c("No " + a2 + " table ");
        }
        return linkedList;
    }

    public static List<cn.xiaochuankeji.tieba.push.data.a> a(XSession xSession, long j, long j2) {
        b.c(Thread.currentThread().getName());
        LinkedList linkedList = new LinkedList();
        SQLiteDatabase a = a.a();
        if (a(xSession.session_type, xSession.x_sid) == null) {
            return linkedList;
        }
        String a2 = a(xSession);
        if (a.b(a2)) {
            a2 = c.a(a2).a(new String[]{"msgid", "fromuser", "touser", "content", "mtype", Statics.TIME, "unsup", NotificationCompat.CATEGORY_STATUS, "sync_begin"}).a("status!=3 and delete_status!=3 and msgid >= '" + j + "' and " + "msgid" + " < '" + j2 + "'", null).b("msgid").a().a();
            b.c(a2);
            Cursor rawQuery = a.rawQuery(a2, null);
            if (rawQuery != null) {
                long j3 = 0;
                int columnIndex = rawQuery.getColumnIndex("msgid");
                int columnIndex2 = rawQuery.getColumnIndex("fromuser");
                int columnIndex3 = rawQuery.getColumnIndex("touser");
                int columnIndex4 = rawQuery.getColumnIndex("content");
                int columnIndex5 = rawQuery.getColumnIndex("mtype");
                int columnIndex6 = rawQuery.getColumnIndex(Statics.TIME);
                int columnIndex7 = rawQuery.getColumnIndex("unsup");
                int columnIndex8 = rawQuery.getColumnIndex(NotificationCompat.CATEGORY_STATUS);
                ChatUser chatUser = xSession.x_mask;
                ChatUser chatUser2 = new ChatUser();
                chatUser2.id = xSession.x_other.id;
                chatUser2.avatar = xSession.x_other.avatar;
                chatUser2.gender = xSession.x_other.gender;
                chatUser2.name = xSession.x_other.name;
                chatUser2.official = xSession.x_other.official;
                while (rawQuery.moveToNext()) {
                    long j4 = rawQuery.getLong(columnIndex);
                    long j5 = rawQuery.getLong(columnIndex2);
                    long j6 = rawQuery.getLong(columnIndex3);
                    String str = new String(rawQuery.getBlob(columnIndex4), Charset.forName("UTF-8"));
                    int i = rawQuery.getInt(columnIndex5);
                    long j7 = rawQuery.getLong(columnIndex6);
                    String string = rawQuery.getString(columnIndex7);
                    int i2 = rawQuery.getInt(columnIndex8);
                    cn.xiaochuankeji.tieba.push.data.a a3 = d.a(j3, j7);
                    if (a3 != null) {
                        linkedList.add(a3);
                    }
                    cn.xiaochuankeji.tieba.push.data.a aVar = new cn.xiaochuankeji.tieba.push.data.a();
                    aVar.j = j4;
                    aVar.a = j5;
                    aVar.b = j6;
                    boolean z = chatUser.id == j5;
                    if (z) {
                        aVar.c = chatUser.avatar;
                        aVar.d = chatUser.gender;
                        aVar.e = chatUser.name;
                    } else {
                        aVar.c = chatUser2.avatar;
                        aVar.d = chatUser2.gender;
                        aVar.e = chatUser2.name;
                    }
                    aVar.l = string;
                    aVar.g = i;
                    aVar.i = d.a(z, i);
                    aVar.h = i2;
                    aVar.k = j7;
                    aVar.f = str;
                    linkedList.add(aVar);
                    j3 = j7;
                }
            }
        } else {
            b.c("No " + a2 + " table ");
        }
        return linkedList;
    }

    @WorkerThread
    public static boolean a(XSession xSession, long j, long j2, JSONArray jSONArray, boolean z) {
        if (jSONArray == null || jSONArray.isEmpty()) {
            return false;
        }
        b.c(Thread.currentThread().getName());
        SQLiteDatabase a = a.a();
        String a2 = a(xSession);
        a.beginTransaction();
        try {
            ContentValues contentValues;
            if (!a.b(a2)) {
                a(a, a2);
            }
            int size = jSONArray.size();
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            JSONObject jSONObject2 = jSONArray.getJSONObject(size - 1);
            long longValue = jSONObject.getLongValue("msgid");
            long longValue2 = jSONObject2.getLongValue("msgid");
            long min = Math.min(longValue, longValue2);
            long max = Math.max(longValue, longValue2);
            for (int i = 0; i < size; i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                int intValue = jSONObject3.getIntValue(NotificationCompat.CATEGORY_STATUS);
                int intValue2 = jSONObject3.getIntValue("mtype");
                Object string = jSONObject3.getString("content");
                if (!TextUtils.isEmpty(string)) {
                    long longValue3 = jSONObject3.getLongValue("msgid");
                    String str = "select msgid from " + a2 + "  where " + "msgid" + "=" + longValue3 + VoiceWakeuperAidl.PARAMS_SEPARATE;
                    longValue2 = 0;
                    Cursor rawQuery = a.rawQuery(str, null);
                    if (rawQuery != null && rawQuery.moveToFirst()) {
                        longValue2 = rawQuery.getLong(0);
                    }
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                    if (longValue2 <= 0) {
                        long longValue4 = jSONObject3.getLongValue("fromuser");
                        long longValue5 = jSONObject3.getLongValue("touser");
                        long longValue6 = jSONObject3.getLongValue(Statics.TIME);
                        String string2 = jSONObject3.getString("unsup");
                        contentValues = new ContentValues();
                        contentValues.put("msgid", Long.valueOf(longValue3));
                        contentValues.put("fromuser", Long.valueOf(longValue4));
                        contentValues.put("touser", Long.valueOf(longValue5));
                        contentValues.put("mtype", Integer.valueOf(intValue2));
                        contentValues.put(Statics.TIME, Long.valueOf(longValue6));
                        contentValues.put("unsup", string2);
                        contentValues.put("content", string.getBytes(Charset.forName("UTF-8")));
                        if (min == longValue3) {
                            contentValues.put("sync_begin", Integer.valueOf(z ? 1 : 0));
                        } else {
                            contentValues.put("sync_begin", Integer.valueOf(0));
                        }
                        if (intValue == 3) {
                            contentValues.put("delete_status", Integer.valueOf(3));
                            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(3));
                        } else {
                            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
                        }
                        if (contentValues.size() > 0) {
                            if (((long) a.updateWithOnConflict(a2, contentValues, "msgid=?", new String[]{String.valueOf(longValue3)}, 4)) < 1) {
                                a.insertWithOnConflict(a2, null, contentValues, 5);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            if (j2 == Long.MAX_VALUE) {
                contentValues = new ContentValues();
                contentValues.put("sync_end", Integer.valueOf(0));
                a.update(a2, contentValues, null, null);
                contentValues = new ContentValues();
                contentValues.put("sync_end", Integer.valueOf(1));
                a.update(a2, contentValues, "msgid=?", new String[]{String.valueOf(max)});
            }
            if (j == 0) {
                contentValues = new ContentValues();
                contentValues.put("sync_begin", Integer.valueOf(0));
                a.update(a2, contentValues, null, null);
                contentValues = new ContentValues();
                contentValues.put("sync_begin", Integer.valueOf(1));
                a.update(a2, contentValues, "msgid=?", new String[]{String.valueOf(min)});
            }
            a.setTransactionSuccessful();
            return true;
        } finally {
            a.endTransaction();
        }
    }

    public static long d(XSession xSession) {
        String a = a(xSession);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            a = "select msgid from " + a + " where " + "sync_begin" + "=1 ;";
            b.c(Thread.currentThread().getName() + " " + a);
            Cursor rawQuery = a2.rawQuery(a, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        long j = rawQuery.getLong(0);
                        return j;
                    } else if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                } finally {
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                }
            }
        }
        return Long.MAX_VALUE;
    }

    public static long e(XSession xSession) {
        String a = a(xSession);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            a = "select msgid from " + a + " where " + "sync_end" + "=1 ;";
            b.c(Thread.currentThread().getName() + " " + a);
            Cursor rawQuery = a2.rawQuery(a, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        long j = rawQuery.getLong(0);
                        return j;
                    } else if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                } finally {
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                }
            }
        }
        return 0;
    }

    public static long f(XSession xSession) {
        String a = a(xSession);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            a = "select max(msgid) from " + a + " where " + "fromuser" + "!=" + xSession.x_mask.id + " ;";
            b.c(a);
            Cursor rawQuery = a2.rawQuery(a, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        long j = rawQuery.getLong(0);
                        return j;
                    } else if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                } finally {
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                }
            }
        }
        return 0;
    }

    public static long b() {
        SQLiteDatabase a = a.a();
        long c = a.g().c();
        String b = b(c);
        if (!a.b(b)) {
            a.execSQL("CREATE TABLE IF NOT EXISTS " + b + " (_id integer(64) PRIMARY KEY,x_sequence integer(64) default 1000);");
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", Long.valueOf(c));
            contentValues.put("x_sequence", Long.valueOf(100000));
            a.insert(b, null, contentValues);
        }
        Cursor rawQuery = a.rawQuery("select x_sequence from " + b + " where " + "_id" + "=" + c, null);
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    long j = rawQuery.getLong(0);
                    if (j > 0) {
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("x_sequence", Long.valueOf(1 + j));
                        a.update(b, contentValues2, "_id=?", new String[]{String.valueOf(c)});
                        long j2 = -j;
                        return j2;
                    }
                }
            } finally {
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
            }
        }
        if (!(rawQuery == null || rawQuery.isClosed())) {
            rawQuery.close();
        }
        return -2147483648L;
    }

    public static XSession a(long j) {
        XSession xSession = null;
        SQLiteDatabase a = a.a();
        String a2 = a(2);
        if (a.b(a2)) {
            a2 = c.a(a2).a(b).a("x_sid=" + j + " and " + "session_type" + "=" + 2, null).c("1").a().a();
            b.c(a2);
            Cursor rawQuery = a.rawQuery(a2, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        xSession = a(rawQuery);
                    }
                } finally {
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                }
            }
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
        return xSession;
    }

    public static void g(XSession xSession) {
        if (xSession != null && xSession.session_type != 4) {
            b.c(Thread.currentThread().getName());
            SQLiteDatabase a = a.a();
            String a2 = a(xSession.session_type);
            a.beginTransaction();
            try {
                Object toJSONString;
                if (!a.b(a2)) {
                    a.execSQL("CREATE TABLE IF NOT EXISTS " + a2 + " (session_id integer(64) DEFAULT 0,session_type integer(32),local_id integer(64) DEFAULT 0,sync integer(64) DEFAULT 0,x_last_msg_id integer(64) DEFAULT 0,time integer(64) DEFAULT 0,unread integer(64) DEFAULT 0,status integer(32) DEFAULT 0,weight integer(32) DEFAULT 0,x_mask blob,x_other blob,x_room_id integer(64) DEFAULT 0,room_data blob,x_sid integer(64) primary key,x_msg blob);");
                }
                ContentValues contentValues = new ContentValues();
                if (xSession.session_id > 1) {
                    contentValues.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(xSession.session_id));
                }
                if (xSession.session_local_id < 0) {
                    contentValues.put("local_id", Long.valueOf(xSession.session_local_id));
                }
                contentValues.put("session_type", Integer.valueOf(xSession.session_type));
                if (xSession.x_mask != null) {
                    toJSONString = JSON.toJSONString(xSession.x_mask);
                    if (!TextUtils.isEmpty(toJSONString)) {
                        contentValues.put("x_mask", toJSONString.getBytes(Charset.forName("UTF-8")));
                    }
                }
                if (xSession.x_other != null) {
                    toJSONString = JSON.toJSONString(xSession.x_other);
                    if (!TextUtils.isEmpty(toJSONString)) {
                        contentValues.put("x_other", toJSONString.getBytes(Charset.forName("UTF-8")));
                    }
                }
                if (xSession.x_sid > 0) {
                    contentValues.put("x_sid", Long.valueOf(xSession.x_sid));
                }
                if (xSession.x_room_id > 0) {
                    contentValues.put("x_room_id", Long.valueOf(xSession.x_room_id));
                    if (xSession.x_room != null) {
                        contentValues.put("room_data", JSON.toJSONString(xSession.x_room).getBytes(Charset.forName("UTF-8")));
                    }
                }
                contentValues.put("x_last_msg_id", Long.valueOf(xSession.x_last_msg_id));
                contentValues.put("unread", Integer.valueOf(xSession.unread));
                contentValues.put(Statics.TIME, Long.valueOf(xSession.time));
                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(xSession.status));
                contentValues.put("weight", Integer.valueOf(xSession.weight));
                if (xSession.x_msg != null) {
                    contentValues.put("x_msg", JSON.toJSONString(xSession.x_msg).getBytes(Charset.forName("UTF-8")));
                }
                if (contentValues.size() > 0) {
                    long updateWithOnConflict = (long) a.updateWithOnConflict(a2, contentValues, "x_sid=?", new String[]{String.valueOf(xSession.x_sid)}, 4);
                    if (updateWithOnConflict < 1) {
                        a.insertWithOnConflict(a2, null, contentValues, 5);
                    } else if (updateWithOnConflict > 1) {
                        a.execSQL("delete from " + a2 + " where rowid in (select rowid from " + a2 + " where " + "x_sid" + "=" + xSession.x_sid + " limit " + (updateWithOnConflict - 1) + ")");
                    }
                }
                a.setTransactionSuccessful();
            } finally {
                a.endTransaction();
            }
        }
    }

    public static boolean a(XSession xSession, cn.xiaochuankeji.tieba.push.data.a aVar, long j) {
        if (aVar == null) {
            return false;
        }
        b.c(Thread.currentThread().getName());
        SQLiteDatabase a = a.a();
        String a2 = a(xSession);
        a.beginTransaction();
        try {
            if (!a.b(a2)) {
                a(a, a2);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("msgid", Long.valueOf(aVar.j));
            contentValues.put("fromuser", Long.valueOf(aVar.a));
            contentValues.put("touser", Long.valueOf(aVar.b));
            contentValues.put("mtype", Integer.valueOf(aVar.g));
            contentValues.put(Statics.TIME, Long.valueOf(aVar.k));
            contentValues.put("unsup", aVar.l);
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(aVar.h));
            contentValues.put("content", aVar.f.getBytes(Charset.forName("UTF-8")));
            if (contentValues.size() > 0) {
                if (((long) a.updateWithOnConflict(a2, contentValues, "msgid=?", new String[]{String.valueOf(j)}, 4)) < 1) {
                    a.insertWithOnConflict(a2, null, contentValues, 5);
                }
            }
            a.setTransactionSuccessful();
            return true;
        } finally {
            a.endTransaction();
        }
    }

    public static void h(XSession xSession) {
        String a;
        if (xSession.session_type == 4) {
            a = a(1);
            if (a.b(a)) {
                SQLiteDatabase a2 = a.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(3));
                a2.updateWithOnConflict(a, contentValues, "session_type=?", new String[]{String.valueOf(4)}, 4);
                d();
                return;
            }
            return;
        }
        a = a(xSession.session_type);
        if (a.b(a)) {
            a2 = a.a();
            contentValues = new ContentValues();
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(3));
            a2.updateWithOnConflict(a, contentValues, "x_sid=?", new String[]{String.valueOf(xSession.x_sid)}, 4);
            d();
        }
    }

    public static void c() {
        String a = a(2);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(3));
            a2.updateWithOnConflict(a, contentValues, null, null, 4);
            d();
        }
    }

    public static void i(XSession xSession) {
        String a = a(xSession.session_type);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
            a2.updateWithOnConflict(a, contentValues, "x_sid=?", new String[]{String.valueOf(xSession.x_sid)}, 4);
            d();
        }
    }

    public static void j(XSession xSession) {
        String a = a(xSession.session_type);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("unread", Integer.valueOf(0));
            a2.updateWithOnConflict(a, contentValues, "x_sid=?", new String[]{String.valueOf(xSession.x_sid)}, 4);
            d();
        }
    }

    public static void d() {
        String a = a(1);
        if (a.b(a)) {
            int e = e();
            SQLiteDatabase a2 = a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("unread", Integer.valueOf(e));
            a2.updateWithOnConflict(a, contentValues, "session_type=?", new String[]{String.valueOf(4)}, 4);
        }
    }

    public static void a(XSession xSession, cn.xiaochuankeji.tieba.push.data.a aVar) {
        SQLiteDatabase a = a.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(3));
        contentValues.put("delete_status", Integer.valueOf(3));
        long j;
        if (a.g().c() == aVar.a) {
            j = aVar.b;
        } else {
            j = aVar.a;
        }
        String a2 = a(xSession);
        if (a.b(a2)) {
            a.updateWithOnConflict(a2, contentValues, "msgid=?", new String[]{String.valueOf(aVar.j)}, 4);
        }
    }

    public static void k(XSession xSession) {
        SQLiteDatabase a = a.a();
        String a2 = a(1);
        a.beginTransaction();
        try {
            boolean z;
            Object toJSONString;
            if (!a.b(a2)) {
                a.execSQL("CREATE TABLE IF NOT EXISTS " + a2 + " (session_id integer(64) DEFAULT 0,session_type integer(32),local_id integer(64) DEFAULT 0,sync integer(64) DEFAULT 0,x_last_msg_id integer(64) DEFAULT 0,time integer(64) DEFAULT 0,unread integer(64) DEFAULT 0,status integer(32) DEFAULT 0,weight integer(32) DEFAULT 0,x_mask blob,x_other blob,x_room_id integer(64) DEFAULT 0,room_data blob,x_sid integer(64) primary key,x_msg blob);");
            }
            Cursor rawQuery = a.rawQuery("select session_id from " + a2 + "  where " + "session_type" + "=" + 4 + " limit 1;", null);
            if (rawQuery != null) {
                boolean moveToFirst = rawQuery.moveToFirst();
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
                z = moveToFirst;
            } else {
                z = false;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(Statics.TIME, Long.valueOf(xSession.time));
            contentValues.put("x_last_msg_id", Long.valueOf(xSession.x_last_msg_id));
            if (xSession.x_room_id > 0) {
                contentValues.put("x_room_id", Long.valueOf(xSession.x_room_id));
                if (xSession.x_room != null) {
                    contentValues.put("room_data", JSON.toJSONString(xSession.x_room).getBytes(Charset.forName("UTF-8")));
                }
            }
            if (xSession.x_msg != null) {
                contentValues.put("x_msg", JSON.toJSONString(xSession.x_msg).getBytes(Charset.forName("UTF-8")));
            }
            if (xSession.x_mask != null) {
                toJSONString = JSON.toJSONString(xSession.x_mask);
                if (!TextUtils.isEmpty(toJSONString)) {
                    contentValues.put("x_mask", toJSONString.getBytes(Charset.forName("UTF-8")));
                }
            }
            if (xSession.x_other != null) {
                toJSONString = JSON.toJSONString(xSession.x_other);
                if (!TextUtils.isEmpty(toJSONString)) {
                    contentValues.put("x_other", toJSONString.getBytes(Charset.forName("UTF-8")));
                }
            }
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
            contentValues.put(SpeechEvent.KEY_EVENT_SESSION_ID, Integer.valueOf(4));
            contentValues.put("session_type", Integer.valueOf(4));
            if (z) {
                contentValues.put("unread", Integer.valueOf(e()));
                a.updateWithOnConflict(a2, contentValues, "session_type=?", new String[]{String.valueOf(4)}, 4);
            } else {
                contentValues.put("unread", Integer.valueOf(0));
                if (a.updateWithOnConflict(a2, contentValues, "session_type=?", new String[]{String.valueOf(4)}, 4) < 1) {
                    a.insertWithOnConflict(a2, null, contentValues, 4);
                }
            }
            a.setTransactionSuccessful();
        } finally {
            a.endTransaction();
        }
    }

    @WorkerThread
    public static int e() {
        int i = 0;
        b.c(Thread.currentThread().getName());
        String a = a(2);
        if (a.b(a)) {
            SQLiteDatabase a2 = a.a();
            a = "select sum(unread) from " + a + "  where " + NotificationCompat.CATEGORY_STATUS + "!=" + 3;
            b.c(a);
            Cursor rawQuery = a2.rawQuery(a, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        i = rawQuery.getInt(0);
                        if (!rawQuery.isClosed()) {
                            rawQuery.close();
                        }
                    } else if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                } catch (Throwable th) {
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                }
            }
        }
        return i;
    }

    public static int f() {
        int i = 1;
        SQLiteDatabase a = a.a();
        Cursor rawQuery = a.rawQuery(c.a("sqlite_master").a(new String[]{"name"}).a("type='table'", null).a().a(), null);
        if (rawQuery != null) {
            a.beginTransaction();
            while (rawQuery.moveToNext()) {
                try {
                    String string = rawQuery.getString(0);
                    if (!(string.equals("msg_notify") || string.equals("msg_push"))) {
                        a.execSQL("drop table " + string);
                    }
                } catch (Exception e) {
                    com.izuiyou.a.a.a.b("Debug", e);
                    i = -1;
                    a.endTransaction();
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                } catch (Throwable th) {
                    a.endTransaction();
                    if (!rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
            a.setTransactionSuccessful();
            a.endTransaction();
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        cn.xiaochuankeji.tieba.background.h.d.a().d();
        return i;
    }

    public static int d(int i) {
        if (i == 1 || i == 2) {
            SQLiteDatabase a = a.a();
            a.beginTransaction();
            try {
                String a2 = a(i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("sync", Integer.valueOf(0));
                a.update(a2, contentValues, null, null);
                a.setTransactionSuccessful();
                a.endTransaction();
                return 1;
            } catch (Exception e) {
                com.izuiyou.a.a.a.d("Debug", e);
                a.endTransaction();
                return -1;
            } catch (Throwable th) {
                a.endTransaction();
                throw th;
            }
        }
        com.izuiyou.a.a.a.b("Debug", "clean session sync error,an un-support session_type:" + i);
        return -2;
    }

    public static int a(@NonNull String str) {
        SQLiteDatabase a = a.a();
        a.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("sync_begin", Integer.valueOf(0));
            contentValues.put("sync_end", Integer.valueOf(0));
            a.update(str, contentValues, null, null);
            a.setTransactionSuccessful();
            a.endTransaction();
            return 1;
        } catch (Exception e) {
            com.izuiyou.a.a.a.d("Debug", e);
            a.endTransaction();
            return -1;
        } catch (Throwable th) {
            a.endTransaction();
            throw th;
        }
    }
}
