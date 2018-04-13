package cn.xiaochuankeji.tieba.push.d;

import android.content.ContentValues;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.xiaochuan.push.e;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.push.a;
import cn.xiaochuankeji.tieba.push.c.g;
import cn.xiaochuankeji.tieba.push.c.h;
import cn.xiaochuankeji.tieba.push.d;
import cn.xiaochuankeji.tieba.push.data.ChatRoom;
import cn.xiaochuankeji.tieba.push.data.ChatUser;
import cn.xiaochuankeji.tieba.push.data.XMessage;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.push.proto.MsgWrapper.SyncProtoItem;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechEvent;
import com.izuiyou.a.a.b;
import com.meizu.cloud.pushsdk.handler.impl.model.Statics;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.nio.charset.Charset;
import java.util.HashSet;

public class c {
    private static final HashSet<Long> a = new HashSet();

    @WorkerThread
    public static void a(String str) {
        b.c("client id: " + str);
        if (TextUtils.isEmpty(str)) {
            b.c("client id: " + str + " no change, ignore");
            return;
        }
        String a = a.a();
        if (a == null || a.isEmpty()) {
            a.a(str);
            return;
        }
        if (!str.equals(a)) {
            a.b(a);
        }
        a.a(str);
    }

    @WorkerThread
    public static void a(long j, int i, byte[] bArr) {
        try {
            boolean a = cn.xiaochuankeji.tieba.push.b.c.a(String.valueOf(j));
            com.izuiyou.a.a.a.c("ZY_MSG", "msg:" + j + "  type:" + i + (a ? " is exists " : " is not exists "));
            if (!a) {
                e eVar = new e();
                eVar.b = String.valueOf(j);
                eVar.h = System.currentTimeMillis();
                cn.xiaochuankeji.tieba.push.b.c.a(eVar);
                if (i == 2) {
                    b.a(new String(bArr, AppController.kDataCacheCharsetUTF8));
                } else if (i == 1) {
                    b(bArr);
                } else if (i == 3) {
                    a(bArr);
                } else if (i == 9 || i == 10) {
                    a(i, bArr);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            cn.xiaochuankeji.tieba.analyse.a.a(e);
            com.izuiyou.a.a.a.d(c.class.getSimpleName(), e);
        }
    }

    private static void a(int i, byte[] bArr) throws InvalidProtocolBufferException {
        SyncProtoItem parseFrom = SyncProtoItem.parseFrom(bArr);
        if (!parseFrom.hasUser() || String.valueOf(cn.xiaochuankeji.tieba.background.a.g().c()).equalsIgnoreCase(parseFrom.getUser())) {
            String toStringUtf8 = parseFrom.getMessageSt().toStringUtf8();
            if (i == 9) {
                a.a(toStringUtf8);
                return;
            } else if (i == 10) {
                a.b(toStringUtf8);
                return;
            } else {
                com.izuiyou.a.a.a.d("ZY_MSG", "it's not support debug type");
                return;
            }
        }
        com.izuiyou.a.a.a.d("ZY_MSG", "it's not yours message");
    }

    private static void a(byte[] bArr) throws InvalidProtocolBufferException {
        SyncProtoItem parseFrom = SyncProtoItem.parseFrom(bArr);
        if (!parseFrom.hasUser() || String.valueOf(cn.xiaochuankeji.tieba.background.a.g().c()).equalsIgnoreCase(parseFrom.getUser())) {
            JSONObject parseObject = JSON.parseObject(parseFrom.getMessageSt().toStringUtf8());
            b.c(parseObject);
            if ("chat".equalsIgnoreCase(parseObject.getString("type"))) {
                parseObject = parseObject.getJSONObject("data");
                if (parseObject == null) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it has a empty data");
                    return;
                }
                JSONObject jSONObject = parseObject.getJSONObject("from");
                if (jSONObject == null) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it has a empty from");
                    return;
                }
                JSONObject jSONObject2 = parseObject.getJSONObject("to");
                if (jSONObject2 == null) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it has a empty to");
                    return;
                }
                JSONObject jSONObject3 = parseObject.getJSONObject("xroom");
                if (jSONObject3 == null) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it has a empty xroom");
                    return;
                }
                int intValue = parseObject.getIntValue("session_type");
                if (intValue != 2) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it does not support session_type");
                    return;
                }
                XSession xSession;
                long longValue = parseObject.getLongValue(SpeechEvent.KEY_EVENT_SESSION_ID);
                long longValue2 = parseObject.getLongValue(Statics.TIME);
                long longValue3 = parseObject.getLongValue("msgid");
                int intValue2 = parseObject.getIntValue("mtype");
                String string = parseObject.getString("unsup");
                String string2 = parseObject.getString("content");
                ChatUser chatUser = new ChatUser();
                chatUser.id = jSONObject2.getLongValue("id");
                chatUser.name = jSONObject2.getString("name");
                chatUser.gender = jSONObject2.getIntValue("gender");
                chatUser.avatar = jSONObject2.getLongValue("avatar");
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.room_id = jSONObject3.getLongValue("xroom_id");
                chatRoom.room_name = jSONObject3.getString(SpeechConstant.SUBJECT);
                chatRoom.room_type = intValue;
                chatRoom.room_data = jSONObject3;
                chatRoom.room_mask = chatUser;
                ChatUser chatUser2 = new ChatUser();
                chatUser2.id = jSONObject.getLongValue("id");
                chatUser2.name = jSONObject.getString("name");
                chatUser2.gender = jSONObject.getIntValue("gender");
                chatUser2.avatar = jSONObject.getLongValue("avatar");
                boolean contains = a.contains(Long.valueOf(chatUser2.id));
                XMessage xMessage = new XMessage();
                xMessage.msg_id = longValue3;
                xMessage.msg_type = intValue2;
                xMessage.content = string2;
                xMessage.time = longValue2;
                xMessage.unsup = string;
                xMessage.msg_uid = chatUser2.id;
                ChatUser chatUser3 = new ChatUser();
                chatUser3.id = chatUser2.id;
                chatUser3.avatar = chatUser2.avatar;
                chatUser3.name = chatUser2.name;
                chatUser3.gender = chatUser2.gender;
                chatUser3.official = chatUser2.official;
                XSession a = cn.xiaochuankeji.tieba.push.b.e.a(2, chatUser2.id);
                if (a == null) {
                    int i;
                    a = new XSession();
                    a.session_id = longValue;
                    a.session_type = 2;
                    a.x_mask = chatUser;
                    a.status = 0;
                    if (contains) {
                        i = 0;
                    } else {
                        i = 1;
                    }
                    a.unread = i;
                    xSession = a;
                } else {
                    if (a.x_last_msg_id < longValue3 && longValue3 > 0) {
                        a.unread++;
                        a.x_last_msg_id = longValue3;
                        a.status = 0;
                    }
                    a.unread = contains ? 0 : a.unread;
                    xSession = a;
                }
                xSession.x_sid = chatUser2.id;
                xSession.x_other = chatUser3;
                xSession.x_msg = xMessage;
                xSession.time = longValue2;
                xSession.x_last_msg_id = xMessage.msg_id;
                xSession.x_msg = xMessage;
                xSession.weight = 0;
                xSession.x_room = chatRoom;
                xSession.x_room_id = chatRoom.room_id;
                cn.xiaochuankeji.tieba.push.b.e.a(chatRoom);
                cn.xiaochuankeji.tieba.push.b.e.g(xSession);
                cn.xiaochuankeji.tieba.push.b.e.k(xSession);
                cn.xiaochuankeji.tieba.push.b.e.d();
                cn.xiaochuankeji.tieba.push.data.a aVar = new cn.xiaochuankeji.tieba.push.data.a();
                aVar.j = longValue3;
                aVar.a = chatUser2.id;
                aVar.b = chatUser.id;
                aVar.k = longValue2;
                aVar.g = intValue2;
                aVar.f = string2;
                aVar.c = chatUser2.avatar;
                aVar.d = chatUser2.gender;
                aVar.e = chatUser2.name;
                aVar.l = string;
                aVar.i = d.a(false, intValue2);
                SQLiteDatabase a2 = cn.xiaochuankeji.tieba.push.b.a.a();
                String a3 = cn.xiaochuankeji.tieba.push.b.e.a(xSession);
                a2.beginTransaction();
                try {
                    if (!cn.xiaochuankeji.tieba.push.b.a.b(a3)) {
                        cn.xiaochuankeji.tieba.push.b.e.a(a2, a3);
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("msgid", Long.valueOf(aVar.j));
                    contentValues.put("fromuser", Long.valueOf(aVar.a));
                    contentValues.put("touser", Long.valueOf(aVar.b));
                    contentValues.put("mtype", Integer.valueOf(aVar.g));
                    contentValues.put(Statics.TIME, Long.valueOf(aVar.k));
                    contentValues.put("unsup", aVar.l);
                    contentValues.put("content", aVar.f.getBytes(Charset.forName("UTF-8")));
                    if (contentValues.size() > 0) {
                        if (((long) a2.updateWithOnConflict(a3, contentValues, "msgid=?", new String[]{String.valueOf(aVar.j)}, 4)) < 1) {
                            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
                            a2.insertWithOnConflict(a3, null, contentValues, 5);
                        }
                    }
                    a2.setTransactionSuccessful();
                    if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("kChatMsgNotification", true) && !contains) {
                        cn.xiaochuankeji.tieba.push.e.a().a(xSession);
                    }
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.push.c.a(aVar));
                    org.greenrobot.eventbus.c.a().d(new h());
                    org.greenrobot.eventbus.c.a().d(new g());
                    cn.xiaochuankeji.tieba.background.h.d.a().d();
                    return;
                } finally {
                    a2.endTransaction();
                }
            } else {
                com.izuiyou.a.a.a.d("ZY_MSG", "it does not support type");
                return;
            }
        }
        com.izuiyou.a.a.a.d("ZY_MSG", "it's not yours message");
    }

    @WorkerThread
    private static void b(byte[] bArr) throws InvalidProtocolBufferException {
        SyncProtoItem parseFrom = SyncProtoItem.parseFrom(bArr);
        if (!parseFrom.hasUser() || String.valueOf(cn.xiaochuankeji.tieba.background.a.g().c()).equalsIgnoreCase(parseFrom.getUser())) {
            JSONObject parseObject = JSON.parseObject(parseFrom.getMessageSt().toStringUtf8());
            b.c(parseObject);
            if ("chat".equalsIgnoreCase(parseObject.getString("type"))) {
                parseObject = parseObject.getJSONObject("data");
                if (parseObject == null) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it has a invalid data");
                    return;
                }
                String string = parseObject.getString("content");
                if (TextUtils.isEmpty(string)) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it has a empty content");
                    return;
                }
                JSONObject jSONObject = parseObject.getJSONObject("from");
                if (jSONObject == null) {
                    com.izuiyou.a.a.a.d("ZY_MSG", "it has a empty from");
                    return;
                }
                XSession xSession;
                ChatUser chatUser = new ChatUser();
                chatUser.id = jSONObject.getLongValue("id");
                chatUser.avatar = jSONObject.getLongValue("avatar");
                chatUser.name = jSONObject.getString("name");
                chatUser.gender = jSONObject.getIntValue("gender");
                chatUser.official = jSONObject.getIntValue("official");
                long longValue = parseObject.getLongValue("msgid");
                int intValue = parseObject.getIntValue("mtype");
                long longValue2 = parseObject.getLongValue(SpeechEvent.KEY_EVENT_SESSION_ID);
                long longValue3 = parseObject.getLongValue(Statics.TIME);
                long longValue4 = parseObject.getLongValue("touser");
                String string2 = parseObject.getString("unsup");
                boolean contains = a.contains(Long.valueOf(chatUser.id));
                XMessage xMessage = new XMessage();
                xMessage.msg_id = longValue;
                xMessage.msg_type = intValue;
                xMessage.content = string;
                xMessage.time = longValue3;
                xMessage.unsup = string2;
                xMessage.msg_uid = chatUser.id;
                XSession a = cn.xiaochuankeji.tieba.push.b.e.a(1, chatUser.id);
                if (a == null) {
                    int i;
                    a = new XSession();
                    a.session_id = longValue2;
                    a.session_type = 1;
                    a.status = 0;
                    Member q = cn.xiaochuankeji.tieba.background.a.g().q();
                    ChatUser chatUser2 = new ChatUser();
                    chatUser2.id = q.getId();
                    chatUser2.avatar = q.getAvatarID();
                    chatUser2.gender = q.getGender();
                    chatUser2.name = q.getRawName();
                    a.x_mask = chatUser2;
                    a.status = 0;
                    if (contains) {
                        i = 0;
                    } else {
                        i = 1;
                    }
                    a.unread = i;
                    xSession = a;
                } else {
                    if (a.x_last_msg_id < longValue && longValue > 0) {
                        a.unread++;
                        a.x_last_msg_id = longValue;
                        a.status = 0;
                    }
                    a.unread = contains ? 0 : a.unread;
                    xSession = a;
                }
                xSession.x_other = chatUser;
                xSession.x_sid = chatUser.id;
                xSession.x_msg = xMessage;
                xSession.time = longValue3;
                xSession.x_last_msg_id = xMessage.msg_id;
                xSession.x_msg = xMessage;
                xSession.weight = 0;
                cn.xiaochuankeji.tieba.push.b.e.g(xSession);
                cn.xiaochuankeji.tieba.push.data.a aVar = new cn.xiaochuankeji.tieba.push.data.a();
                aVar.j = longValue;
                aVar.a = chatUser.id;
                aVar.b = longValue4;
                aVar.k = longValue3;
                aVar.g = intValue;
                aVar.f = string;
                aVar.c = chatUser.avatar;
                aVar.d = chatUser.gender;
                aVar.e = chatUser.name;
                aVar.l = string2;
                aVar.i = d.a(false, intValue);
                SQLiteDatabase a2 = cn.xiaochuankeji.tieba.push.b.a.a();
                String a3 = cn.xiaochuankeji.tieba.push.b.e.a(xSession);
                a2.beginTransaction();
                try {
                    if (!cn.xiaochuankeji.tieba.push.b.a.b(a3)) {
                        cn.xiaochuankeji.tieba.push.b.e.a(a2, a3);
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("msgid", Long.valueOf(aVar.j));
                    contentValues.put("fromuser", Long.valueOf(aVar.a));
                    contentValues.put("touser", Long.valueOf(aVar.b));
                    contentValues.put("mtype", Integer.valueOf(aVar.g));
                    contentValues.put(Statics.TIME, Long.valueOf(aVar.k));
                    contentValues.put("unsup", aVar.l);
                    contentValues.put("content", aVar.f.getBytes(Charset.forName("UTF-8")));
                    if (contentValues.size() > 0) {
                        if (((long) a2.updateWithOnConflict(a3, contentValues, "msgid=?", new String[]{String.valueOf(aVar.j)}, 4)) < 1) {
                            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
                            a2.insertWithOnConflict(a3, null, contentValues, 5);
                        }
                    }
                    a2.setTransactionSuccessful();
                    if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("kChatMsgNotification", true) && !contains) {
                        cn.xiaochuankeji.tieba.push.e.a().a(xSession);
                    }
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.push.c.a(aVar));
                    org.greenrobot.eventbus.c.a().d(new h());
                    org.greenrobot.eventbus.c.a().d(new g());
                    cn.xiaochuankeji.tieba.background.h.d.a().d();
                    return;
                } finally {
                    a2.endTransaction();
                }
            } else {
                com.izuiyou.a.a.a.d("ZY_MSG", "it does not support type");
                return;
            }
        }
        com.izuiyou.a.a.a.d("ZY_MSG", "it's not yours message");
    }

    public static void a(XSession xSession) {
        a.add(Long.valueOf(xSession.x_sid));
    }

    public static void b(XSession xSession) {
        a.remove(Long.valueOf(xSession.x_sid));
    }
}
