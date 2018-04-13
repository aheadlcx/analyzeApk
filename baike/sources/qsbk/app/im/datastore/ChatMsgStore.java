package qsbk.app.im.datastore;

import android.content.ContentValues;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.QiushiNotificationCountManager;
import qsbk.app.im.QiuyouquanNotificationCountManager;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.comm.ArrayUtils;

public class ChatMsgStore extends BaseChatMsgStore {
    protected static HashMap<String, ChatMsgStore> d = new HashMap();
    private final DatabaseHelper$RowMapping<List<ChatMsg>> e = new s(this);

    private ChatMsgStore(String str) {
        super(str);
    }

    public static synchronized ChatMsgStore getChatMsgStore(String str) {
        ChatMsgStore chatMsgStore;
        synchronized (ChatMsgStore.class) {
            chatMsgStore = (ChatMsgStore) d.get(str);
            if (chatMsgStore == null) {
                chatMsgStore = new ChatMsgStore(str);
                d.put(str, chatMsgStore);
            }
        }
        return chatMsgStore;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.ContentValues chatMsg2ContentValues(qsbk.app.im.ChatMsg r4) {
        /*
        if (r4 != 0) goto L_0x000a;
    L_0x0002:
        r0 = new java.lang.RuntimeException;
        r1 = "Msg cannot be null";
        r0.<init>(r1);
        throw r0;
    L_0x000a:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "ChatMsgStore 548";
        r0 = r0.append(r1);
        r1 = r4.data;
        r0 = r0.append(r1);
        r0 = r0.toString();
        qsbk.app.utils.LogUtil.e(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "chatmsgstore :";
        r0 = r0.append(r1);
        r1 = r4.toString();
        r0 = r0.append(r1);
        r0 = r0.toString();
        qsbk.app.utils.LogUtil.e(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "userId:";
        r0 = r0.append(r1);
        r1 = r4.uid;
        r0 = r0.append(r1);
        r0 = r0.toString();
        qsbk.app.utils.LogUtil.e(r0);
        r1 = new android.content.ContentValues;
        r1.<init>();
        r0 = "id";
        r2 = r4.dbid;
        r2 = java.lang.Long.valueOf(r2);
        r1.put(r0, r2);
        r0 = "data";
        r2 = r4.data;
        r1.put(r0, r2);
        r0 = "inout";
        r2 = r4.inout;
        r2 = java.lang.Integer.valueOf(r2);
        r1.put(r0, r2);
        r0 = "msgid";
        r2 = r4.msgid;
        r1.put(r0, r2);
        r0 = "msgsrc";
        r2 = r4.msgsrc;
        r1.put(r0, r2);
        r0 = "state";
        r2 = r4.status;
        r2 = java.lang.Integer.valueOf(r2);
        r1.put(r0, r2);
        r0 = "t";
        r2 = r4.time;
        r2 = java.lang.Long.valueOf(r2);
        r1.put(r0, r2);
        r0 = "type";
        r2 = r4.type;
        r2 = java.lang.Integer.valueOf(r2);
        r1.put(r0, r2);
        r0 = "uid";
        r2 = r4.uid;
        r1.put(r0, r2);
        r0 = "icon";
        r2 = r4.fromicon;
        r1.put(r0, r2);
        r0 = "name";
        r2 = r4.fromnick;
        r1.put(r0, r2);
        r0 = r4.uid;	 Catch:{ JSONException -> 0x00eb }
        r2 = "27685144";
        r0 = android.text.TextUtils.equals(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        if (r0 == 0) goto L_0x0142;
    L_0x00c4:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00eb }
        r2 = r4.data;	 Catch:{ JSONException -> 0x00eb }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x00eb }
        if (r0 == 0) goto L_0x00df;
    L_0x00cd:
        r2 = "jump_data";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x00eb }
        if (r0 == 0) goto L_0x00df;
    L_0x00d5:
        r2 = "m_type";
        r3 = -1;
        r0 = r0.optInt(r2, r3);	 Catch:{ JSONException -> 0x00eb }
        switch(r0) {
            case 2: goto L_0x00f0;
            case 3: goto L_0x00fb;
            case 4: goto L_0x0106;
            case 101: goto L_0x00e0;
            case 102: goto L_0x0112;
            case 103: goto L_0x011e;
            case 105: goto L_0x012a;
            case 106: goto L_0x0136;
            default: goto L_0x00df;
        };	 Catch:{ JSONException -> 0x00eb }
    L_0x00df:
        return r1;
    L_0x00e0:
        r0 = "mtype";
        r2 = 1;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x00eb:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00df;
    L_0x00f0:
        r0 = "mtype";
        r2 = 3;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x00fb:
        r0 = "mtype";
        r2 = 2;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0106:
        r0 = "mtype";
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0112:
        r0 = "mtype";
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x011e:
        r0 = "mtype";
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x012a:
        r0 = "mtype";
        r2 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0136:
        r0 = "mtype";
        r2 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0142:
        r0 = r4.uid;	 Catch:{ JSONException -> 0x00eb }
        r2 = "21089551";
        r0 = android.text.TextUtils.equals(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        if (r0 == 0) goto L_0x00df;
    L_0x014c:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00eb }
        r2 = r4.data;	 Catch:{ JSONException -> 0x00eb }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x00eb }
        r2 = "jump_data";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x00eb }
        r2 = "";
        if (r0 == 0) goto L_0x00df;
    L_0x015d:
        r2 = "m_type";
        r0 = r0.optString(r2);	 Catch:{ JSONException -> 0x00eb }
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00eb }
        r2.<init>();	 Catch:{ JSONException -> 0x00eb }
        r3 = "publishType:";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x00eb }
        r2 = r2.append(r0);	 Catch:{ JSONException -> 0x00eb }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x00eb }
        qsbk.app.utils.LogUtil.e(r2);	 Catch:{ JSONException -> 0x00eb }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 != 0) goto L_0x0277;
    L_0x017f:
        r2 = "s_up";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x0193;
    L_0x0187:
        r0 = "mtype";
        r2 = 4;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0193:
        r2 = "s_comment";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x01a7;
    L_0x019b:
        r0 = "mtype";
        r2 = 5;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x01a7:
        r2 = "s_comment_like";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x01bb;
    L_0x01af:
        r0 = "mtype";
        r2 = 6;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x01bb:
        r2 = "up";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x01cf;
    L_0x01c3:
        r0 = "mtype";
        r2 = 7;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x01cf:
        r2 = "loop";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x01e4;
    L_0x01d7:
        r0 = "mtype";
        r2 = 8;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x01e4:
        r2 = "comment";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x01f9;
    L_0x01ec:
        r0 = "mtype";
        r2 = 10;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x01f9:
        r2 = "promote";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x020e;
    L_0x0201:
        r0 = "mtype";
        r2 = 9;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x020e:
        r2 = "AT";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x0223;
    L_0x0216:
        r0 = "mtype";
        r2 = 12;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0223:
        r2 = "comment_like";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x0238;
    L_0x022b:
        r0 = "mtype";
        r2 = 11;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0238:
        r2 = "at_user";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x024d;
    L_0x0240:
        r0 = "mtype";
        r2 = 14;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x024d:
        r2 = "hour_hot";
        r2 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r2 == 0) goto L_0x0262;
    L_0x0255:
        r0 = "mtype";
        r2 = 15;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0262:
        r2 = "prefer";
        r0 = r2.equals(r0);	 Catch:{ JSONException -> 0x00eb }
        if (r0 == 0) goto L_0x00df;
    L_0x026a:
        r0 = "mtype";
        r2 = 16;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
    L_0x0277:
        r0 = "mtype";
        r2 = 13;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00eb }
        goto L_0x00df;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.im.datastore.ChatMsgStore.chatMsg2ContentValues(qsbk.app.im.ChatMsg):android.content.ContentValues");
    }

    public ChatMsg getLatestMsgWithUser(String str) {
        List list = get(str);
        if (ArrayUtils.isEmpty(list)) {
            return null;
        }
        return (ChatMsg) list.get(list.size() - 1);
    }

    public int getUnreadCountWithUser(String str) {
        return getUnReadCountWith(str);
    }

    protected void a() {
        d.clear();
        super.a();
    }

    public long insert(ChatMsg chatMsg) {
        if (chatMsg == null || TextUtils.isEmpty(chatMsg.uid)) {
            return 0;
        }
        ContentValues chatMsg2ContentValues = chatMsg2ContentValues(chatMsg);
        chatMsg2ContentValues.remove("id");
        return this.a.insert(DatabaseHelper.TABLE_MESSAGES, null, chatMsg2ContentValues);
    }

    public int insert(List<ChatMsg> list) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        int size = list.size();
        List arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            ChatMsg chatMsg = (ChatMsg) list.get(i);
            if (chatMsg != null) {
                ContentValues chatMsg2ContentValues = chatMsg2ContentValues(chatMsg);
                chatMsg2ContentValues.remove("id");
                arrayList.add(chatMsg2ContentValues);
            }
        }
        return this.a.insert(DatabaseHelper.TABLE_MESSAGES, null, arrayList);
    }

    public List<ChatMsg> get(int i, int i2, String str) {
        if (TextUtils.isEmpty(str) || i < 0 || i2 <= 0) {
            return null;
        }
        int i3 = (i + 1) * i2;
        return (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "uid =? ", new String[]{str}, null, null, "id DESC ", String.format("%s,%s", new Object[]{Integer.valueOf(i * i2), Integer.valueOf(i3)}), this.e);
    }

    public List<ChatMsg> get(long... jArr) {
        if (jArr == null || jArr.length <= 0) {
            return null;
        }
        int length = jArr.length;
        if (length <= 500) {
            String[] strArr = new String[length];
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < length; i++) {
                strArr[i] = jArr[i] + "";
                stringBuffer.append('?').append(',');
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            return (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "id in(" + stringBuffer.toString() + ")", strArr, null, null, null, null, this.e);
        }
        List<ChatMsg> arrayList = new ArrayList(get(ArrayUtils.copyOfRange(jArr, 0, 500)));
        arrayList.addAll(get(ArrayUtils.copyOfRange(jArr, 500, jArr.length)));
        return arrayList;
    }

    public List<ChatMsg> get(int i, int i2, String str, String str2) {
        if (TextUtils.isEmpty(str) || i < 0 || i2 <= 0 || TextUtils.isEmpty(str2)) {
            return null;
        }
        int i3 = (i + 1) * i2;
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i * i2), Integer.valueOf(i3)});
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "uid =? AND id < ? ", new String[]{str, str2}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getSendFailMsg() {
        return (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "state=3", null, null, null, "id", null, this.e);
    }

    public List<ChatMsg> getByType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=?", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllLikeMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=1", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllLikeReadedMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=1 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllLikeReadedMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=1 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        if (i2 == 0) {
            if (a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 1 and state=? ;").toString(), new String[]{i + "", "5"}) > list.size()) {
                hashMap.put("hasMore", Boolean.valueOf(true));
            } else {
                hashMap.put("hasMore", Boolean.valueOf(false));
            }
        } else {
            hashMap.put("hasMore", Boolean.valueOf(true));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllLikeUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=1 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllLikeUnreadMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=1 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 1 and state=? ;").toString(), new String[]{i + "", "4"});
        hashMap.put("total", Integer.valueOf(a));
        if (a > list.size()) {
            hashMap.put("hasMore", Boolean.valueOf(true));
        } else {
            hashMap.put("hasMore", Boolean.valueOf(false));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllAtMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=101", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllAtReadedMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=101 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllAtReadedMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=101 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        if (i2 == 0) {
            if (a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 101 and state=? ;").toString(), new String[]{i + "", "5"}) > list.size()) {
                hashMap.put("hasMore", Boolean.valueOf(true));
            } else {
                hashMap.put("hasMore", Boolean.valueOf(false));
            }
        } else {
            hashMap.put("hasMore", Boolean.valueOf(true));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllAtUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 101 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllAtUnreadMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 101 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 101 and state=? ;").toString(), new String[]{i + "", "4"});
        hashMap.put("total", Integer.valueOf(a));
        if (a > list.size()) {
            hashMap.put("hasMore", Boolean.valueOf(true));
        } else {
            hashMap.put("hasMore", Boolean.valueOf(false));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllCircleCommentLikeMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=105", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllCircleComemntLikeReadedMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=105 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllCircleComemntLikeReadedMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=105 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        if (i2 == 0) {
            if (a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 105 and state=? ;").toString(), new String[]{i + "", "5"}) > list.size()) {
                hashMap.put("hasMore", Boolean.valueOf(true));
            } else {
                hashMap.put("hasMore", Boolean.valueOf(false));
            }
        } else {
            hashMap.put("hasMore", Boolean.valueOf(true));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllCircleCommentLikeUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 105 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllCircleCommentLikeUnreadMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 105 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 105 and state=? ;").toString(), new String[]{i + "", "4"});
        hashMap.put("total", Integer.valueOf(a));
        if (a > list.size()) {
            hashMap.put("hasMore", Boolean.valueOf(true));
        } else {
            hashMap.put("hasMore", Boolean.valueOf(false));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllForwardReadedMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 106 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllForwardReadedMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 106 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        if (i2 == 0) {
            if (a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 106 and state=? ;").toString(), new String[]{i + "", "5"}) > list.size()) {
                hashMap.put("hasMore", Boolean.valueOf(true));
            } else {
                hashMap.put("hasMore", Boolean.valueOf(false));
            }
        } else {
            hashMap.put("hasMore", Boolean.valueOf(true));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllForwardUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 106 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllForwardUnreadMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 106 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 106 and state=? ;").toString(), new String[]{i + "", "4"});
        hashMap.put("total", Integer.valueOf(a));
        if (a > list.size()) {
            hashMap.put("hasMore", Boolean.valueOf(true));
        } else {
            hashMap.put("hasMore", Boolean.valueOf(false));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllForwardMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype= 106", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllOtherMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and (mtype= 2 or mtype= 3 )", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllSmileMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=4", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllSmileMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=4", new String[]{i + ""}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        return hashMap;
    }

    public HashMap getByAllSmileUnreadMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=4 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 4 and state=? ;").toString(), new String[]{i + "", "4"});
        hashMap.put("total", Integer.valueOf(a));
        if (a > list.size()) {
            hashMap.put("hasMore", Boolean.valueOf(true));
        } else {
            hashMap.put("hasMore", Boolean.valueOf(false));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllSmileUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=4 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap<String, Object> getByAllSmileReadedMsgType(int i, int i2, int i3, int i4) {
        HashMap<String, Object> hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=4 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        if (i2 == 0) {
            if (a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 4 and state=? ;").toString(), new String[]{i + "", "5"}) > list.size()) {
                hashMap.put("hasMore", Boolean.valueOf(true));
            } else {
                hashMap.put("hasMore", Boolean.valueOf(false));
            }
        } else {
            hashMap.put("hasMore", Boolean.valueOf(true));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllSmileReadedMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=4 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllCommentLikeMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=6", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllCommentLikeMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=6", new String[]{i + ""}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        return hashMap;
    }

    public List<ChatMsg> getByAllCommentLikeUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=6 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllCommentLikeUnreadMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=6 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 6 and state=? ;").toString(), new String[]{i + "", "4"});
        hashMap.put("total", Integer.valueOf(a));
        if (a > list.size()) {
            hashMap.put("hasMore", Boolean.valueOf(true));
        } else {
            hashMap.put("hasMore", Boolean.valueOf(false));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllCommentLikeReadedMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=6 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap<String, Object> getByAllCommentLikeReadedMsgType(int i, int i2, int i3, int i4) {
        HashMap<String, Object> hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=6 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        if (i2 == 0) {
            if (a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 6 and state=? ;").toString(), new String[]{i + "", "5"}) > list.size()) {
                hashMap.put("hasMore", Boolean.valueOf(true));
            } else {
                hashMap.put("hasMore", Boolean.valueOf(false));
            }
        } else {
            hashMap.put("hasMore", Boolean.valueOf(true));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllAtInfoMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=14", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllAtInfoMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=14", new String[]{i + ""}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        return hashMap;
    }

    public List<ChatMsg> getByAllAtInfoUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=14 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public HashMap getByAllAtInfoUnreadMsgType(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=14 and state=?", new String[]{i + "", "4"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 14 and state=? ;").toString(), new String[]{i + "", "4"});
        hashMap.put("total", Integer.valueOf(a));
        if (a > list.size()) {
            hashMap.put("hasMore", Boolean.valueOf(true));
        } else {
            hashMap.put("hasMore", Boolean.valueOf(false));
        }
        return hashMap;
    }

    public HashMap<String, Object> getByAllAtInfoReadedMsgType(int i, int i2, int i3, int i4) {
        HashMap<String, Object> hashMap = new HashMap();
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)});
        List list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=14 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", format, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        hashMap.put("data", list);
        if (i2 == 0) {
            if (a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("type").append(" = ? and ").append(DatabaseHelper$MessageRow._MTYPE).append(" = 14 and state=? ;").toString(), new String[]{i + "", "5"}) > list.size()) {
                hashMap.put("hasMore", Boolean.valueOf(true));
            } else {
                hashMap.put("hasMore", Boolean.valueOf(false));
            }
        } else {
            hashMap.put("hasMore", Boolean.valueOf(true));
        }
        return hashMap;
    }

    public List<ChatMsg> getByAllAtInfoReadedMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=14 and state=?", new String[]{i + "", "5"}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllHourHotMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=15", new String[]{i + ""}, null, null, "id DESC", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllHourHotUnreadMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=15 and state=?", new String[]{i + "", "4"}, null, null, "id DESC", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllHourHotReadedMsgTYpe(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and mtype=15 and state=?", new String[]{i + "", "5"}, null, null, "id DESC", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public List<ChatMsg> getByAllOtherExcepttSmileOrLikeMsgType(int i) {
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, null, "type=? and (mtype= 5 or mtype= 7 or mtype = 8 or mtype = 9 or mtype = 10 or mtype = 11 or mtype = 12 or mtype = 16 or mtype = 13 or mtype = 14 )", new String[]{i + ""}, null, null, "id DESC ", null, this.e);
        BaseChatMsgStore.sortMsgsByTime(list);
        return list;
    }

    public int getTotalUnReadCount() {
        int a = a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("state").append(" = ? and ").append("inout").append(" = ? ;").toString(), new String[]{"4", "1"});
        String stringBuffer = new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("state").append(" = ? and ").append("uid").append(" = ? and ").append("inout").append(" = ? ;").toString();
        int a2 = a(stringBuffer, new String[]{"4", QiushiNotificationCountManager.QIUSHI_PUSH_UID, "1"});
        int a3 = a(stringBuffer, new String[]{"4", QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID, "1"});
        return (((a - a2) - a3) - a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("state").append(" = ? and ").append("type").append(" = ? ;").toString(), new String[]{"4", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO})) - getTotalLiveBeginUnreadCount();
    }

    public int getTotalLiveBeginUnreadCount() {
        return a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("uid").append(" = ? and ").append("state").append(" = ? and ").append("type").append(" = ? and ").append("inout").append(" = ? ;").toString(), new String[]{ChatMsg.UID_LIVE, "4", "26", "1"});
    }

    public int[] getUnreadCountWithIds(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        int length = strArr.length;
        String[] strArr2 = new String[length];
        String[][] strArr3 = new String[length][];
        for (int i = 0; i < length; i++) {
            strArr2[i] = new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("state").append(" = ? and ").append("uid").append(" = ? and ").append("inout").append(" = ? and ").append("type").append(" != ?; ").toString();
            strArr3[i] = new String[]{"4", strArr[i], "1", "26"};
        }
        return a(strArr2, strArr3);
    }

    public int getUnReadCountWith(String str) {
        return a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_MESSAGES).append(" where ").append("state").append(" = ? and ").append("uid").append(" = ? and ").append("inout").append(" = ?; ").toString(), new String[]{"4", str, "1"});
    }

    public boolean isChatMsgExist(String str) {
        String stringBuffer = new StringBuffer().append("msgid").append(" = ?").toString();
        DatabaseHelper$RowMapping tVar = new t(this);
        if (ArrayUtils.isEmpty((List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, new String[]{"msgid"}, stringBuffer, new String[]{str}, null, null, null, null, tVar))) {
            return false;
        }
        return true;
    }

    public List<String> getUnreadMsgIds(String str) {
        String stringBuffer = new StringBuffer().append("state").append(" = ? and ").append("uid").append(" = ? and ").append("inout").append(" = ? ").toString();
        String[] strArr = new String[]{"4", str, "1"};
        DatabaseHelper$RowMapping uVar = new u(this);
        return (List) this.a.query(false, DatabaseHelper.TABLE_MESSAGES, new String[]{"msgid"}, stringBuffer, strArr, null, null, null, null, uVar);
    }

    public void markAllMessagesToRead() {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("state").append(" = ? ").append(" where ").append("inout").append(" = ? ").append(" and ").append("state").append(" =  ?;").toString(), (Object[]) new String[]{"5", "1", "4"});
    }

    public void markMessagesToReadWith(int i) {
        if (i >= 0) {
            a(new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("state").append(" = ").append(5).append(" where ").append("uid").append(" = ? ").append(" and ").append("state").append(" =  ? ").append(" and ").append("inout").append(" = ? ;").toString(), (Object[]) new String[]{i + "", "4", "1"});
        }
    }

    public int markOtherMessagesToReadWith(int i) {
        if (i < 0) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(5));
        return this.a.update(DatabaseHelper.TABLE_MESSAGES, contentValues, "uid=? and inout=? and mtype<> ? and state =?", new String[]{i + "", "1", "1", "4"});
    }

    public int markLikeMessagesToReadWith(int i) {
        if (i < 0) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(5));
        return this.a.update(DatabaseHelper.TABLE_MESSAGES, contentValues, "uid=? and inout=? and mtype=?", new String[]{i + "", "1", "1"});
    }

    public int updateMessageState(long j, int i) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("state").append(" =? ").append(" where ").append("id").append(" =? ;").toString(), (Object[]) new String[]{i + "", j + ""});
        return 0;
    }

    public int setMessageReadedBatch(List<Long> list) {
        String stringBuffer = new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("state").append(" = ").append(5).append(" where ").append("id").append(" in (" + ArrayUtils.join(list, com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP) + ") ;").toString();
        String[] strArr = new String[0];
        LogUtil.d("update msg readed:" + stringBuffer);
        a(stringBuffer, (Object[]) strArr);
        return 0;
    }

    public int setMessageReaded(List<String> list) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("state").append(" = ").append(5).append(" where ").append("msgid").append(" in ('" + ArrayUtils.join(list, "','") + "');").toString(), (Object[]) new String[0]);
        return 0;
    }

    public int updateMessageStateBatch(List<String> list, int i) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("state").append(" =? ").append(" where ").append("msgid").append(" in (" + ArrayUtils.join(ArrayUtils.map(list, new v(this)), com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP) + ") ;").toString(), (Object[]) new String[]{i + ""});
        return 0;
    }

    public int updateMessageData(ChatMsg chatMsg) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("data").append(" =? ").append(" where ").append("msgid").append(" =? ;").toString(), (Object[]) new String[]{chatMsg.data, chatMsg.msgid});
        return 0;
    }

    public int updateMessageState(String str, int i) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_MESSAGES).append(" set ").append("state").append(" =? ").append(" where ").append("msgid").append(" =? ;").toString(), (Object[]) new String[]{i + "", str});
        return 0;
    }

    public int deleteAllMessages() {
        return this.a.deleteAll(DatabaseHelper.TABLE_MESSAGES);
    }

    public int deleteMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? ", new String[]{str});
    }

    public int deleteLikedMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? and mtype = ?", new String[]{str, "1"});
    }

    public int deleteAtMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? and mtype = ?", new String[]{str, "101"});
    }

    public int deleteCircleCommentLikeMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? and mtype = ?", new String[]{str, "105"});
    }

    public int deleteCircleForwardMessageWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? and mtype = ?", new String[]{str, "106"});
    }

    public int deleteSmileMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? and (mtype = ? or mtype = ? )", new String[]{str, "4", "7"});
    }

    public int deleteCommentLikeMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? and (mtype = ? or mtype = ? )", new String[]{str, Constants.VIA_SHARE_TYPE_INFO, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE});
    }

    public int deleteAtInfoMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_MESSAGES, "uid = ? and (mtype = ? )", new String[]{str, Constants.VIA_REPORT_TYPE_MAKE_FRIEND});
    }

    public int deleteMessagesWith(long... jArr) {
        int i = 0;
        if (jArr == null || jArr.length == 0) {
            return 0;
        }
        int length = jArr.length;
        String[] strArr = new String[length];
        while (i < length) {
            strArr[i] = jArr[i] + "";
            i++;
        }
        return this.a.deleteIds(DatabaseHelper.TABLE_MESSAGES, "id", strArr);
    }

    public int deleteMessagesWithUid(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return 0;
        }
        return this.a.deleteIds(DatabaseHelper.TABLE_MESSAGES, "uid", strArr);
    }

    public int deleteMessagesWith(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return 0;
        }
        return this.a.deleteIds(DatabaseHelper.TABLE_MESSAGES, "msgid", strArr);
    }
}
