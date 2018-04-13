package qsbk.app.im.datastore;

import android.content.ContentValues;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.comm.ArrayUtils;

public class GroupChatMsgStore extends BaseChatMsgStore {
    protected static HashMap<String, GroupChatMsgStore> d = new HashMap();
    private final DatabaseHelper$RowMapping<List<ChatMsg>> e = new aq(this);
    private final DatabaseHelper$RowMapping<List<int[]>> f = new ar(this);

    private GroupChatMsgStore(String str) {
        super(str);
    }

    public static synchronized GroupChatMsgStore getInstance(String str) {
        GroupChatMsgStore groupChatMsgStore;
        synchronized (GroupChatMsgStore.class) {
            groupChatMsgStore = (GroupChatMsgStore) d.get(str);
            if (groupChatMsgStore == null) {
                groupChatMsgStore = new GroupChatMsgStore(str);
                d.put(str, groupChatMsgStore);
            }
        }
        return groupChatMsgStore;
    }

    public static ContentValues chatMsg2ContentValues(ChatMsg chatMsg) {
        if (chatMsg == null) {
            throw new RuntimeException("Msg cannot be null");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(chatMsg.dbid));
        contentValues.put("data", chatMsg.data);
        contentValues.put("inout", Integer.valueOf(chatMsg.inout));
        contentValues.put("msgid", chatMsg.msgid);
        contentValues.put("msgsrc", chatMsg.msgsrc);
        contentValues.put("state", Integer.valueOf(chatMsg.status));
        contentValues.put("t", Long.valueOf(chatMsg.time));
        contentValues.put("type", Integer.valueOf(chatMsg.type));
        contentValues.put("uid", chatMsg.uid);
        contentValues.put("icon", chatMsg.fromicon);
        contentValues.put("name", chatMsg.fromnick);
        contentValues.put("gid", chatMsg.gid);
        contentValues.put("field1", Integer.valueOf(chatMsg.fromage));
        contentValues.put("field2", chatMsg.fromgender);
        return contentValues;
    }

    protected void a() {
        d.clear();
        super.a();
    }

    public List<ChatMsg> get(int i, int i2, String str, String str2) {
        if (TextUtils.isEmpty(str) || i < 0 || i2 <= 0 || TextUtils.isEmpty(str2)) {
            return null;
        }
        int i3 = (i + 1) * i2;
        String format = String.format("%s,%s", new Object[]{Integer.valueOf(i * i2), Integer.valueOf(i3)});
        List<ChatMsg> list = (List) this.a.query(false, DatabaseHelper.TABLE_GROUP_MESSAGES, null, "gid =? AND id < ? ", new String[]{str, str2}, null, null, "id DESC ", format, this.e);
        sortMsgsByTime(list);
        return list;
    }

    public int getUnReadCountWith(String str) {
        return a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" where ").append("state").append(" = ? and ").append("gid").append(" = ? and ").append("inout").append(" = ?; ").toString(), new String[]{"4", str, "1"});
    }

    public int deleteMessagesWith(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return 0;
        }
        return this.a.deleteIds(DatabaseHelper.TABLE_GROUP_MESSAGES, "msgid", strArr);
    }

    public int deleteMessagesWithUid(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return 0;
        }
        return this.a.deleteIds(DatabaseHelper.TABLE_GROUP_MESSAGES, "gid", strArr);
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
        return this.a.deleteIds(DatabaseHelper.TABLE_GROUP_MESSAGES, "id", strArr);
    }

    public int deleteMessagesWith(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.a.delete(DatabaseHelper.TABLE_GROUP_MESSAGES, "gid = ? ", new String[]{str});
    }

    public int deleteAllMessages() {
        return this.a.deleteAll(DatabaseHelper.TABLE_GROUP_MESSAGES);
    }

    public int getTotalUnReadCount() {
        return a(new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" where ").append("state").append(" = ? and ").append("inout").append(" = ? and ").append("type").append(" < ?;").toString(), new String[]{"4", "1", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO});
    }

    public List<int[]> getUnReadCounts() {
        return (List) this.a.query(true, DatabaseHelper.TABLE_GROUP_MESSAGES, new String[]{"gid", "count(*)"}, "state = ? ", new String[]{"4"}, "gid", null, null, null, this.f);
    }

    public int updateMessageState(String str, int i) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("state").append(" =? ").append(" where ").append("msgid").append(" =? ;").toString(), new String[]{i + "", str});
        return 0;
    }

    public int updateMessageState(long j, int i) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("state").append(" =? ").append(" where ").append("id").append(" =? ;").toString(), new String[]{i + "", j + ""});
        return 0;
    }

    public void markMessagesToReadWith(int i) {
        if (i >= 0) {
            a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("state").append(" = ").append(5).append(" where ").append("gid").append(" = ? ").append(" and ").append("state").append(" = ").append(4).toString(), new String[]{i + ""});
        }
    }

    public void markAllMessagesToRead() {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("state").append(" = ? ").append(" where ").append("state").append(" = ").append(4).append(" ; ").toString(), new String[]{"5"});
    }

    public int[] getUnreadCountWithIds(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        int length = strArr.length;
        String[] strArr2 = new String[length];
        String[][] strArr3 = new String[length][];
        for (int i = 0; i < length; i++) {
            strArr2[i] = new StringBuffer("select count(distinct ").append("id").append(")").append("from ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" where ").append("state").append(" = ? and ").append("gid").append(" = ? and ").append("inout").append(" = ?; ").toString();
            strArr3[i] = new String[]{"4", strArr[i], "1"};
        }
        return a(strArr2, strArr3);
    }

    public List<ChatMsg> get(int i, int i2, String str) {
        if (TextUtils.isEmpty(str) || i < 0 || i2 <= 0) {
            return null;
        }
        int i3 = (i + 1) * i2;
        return (List) this.a.query(false, DatabaseHelper.TABLE_GROUP_MESSAGES, null, "gid =? ", new String[]{str}, null, null, "id DESC ", String.format("%s,%s", new Object[]{Integer.valueOf(i * i2), Integer.valueOf(i3)}), this.e);
    }

    public long insert(ChatMsg chatMsg) {
        if (chatMsg == null || TextUtils.isEmpty(chatMsg.gid)) {
            return 0;
        }
        ContentValues chatMsg2ContentValues = chatMsg2ContentValues(chatMsg);
        chatMsg2ContentValues.remove("id");
        return this.a.insert(DatabaseHelper.TABLE_GROUP_MESSAGES, null, chatMsg2ContentValues);
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
        return this.a.insert(DatabaseHelper.TABLE_GROUP_MESSAGES, null, arrayList);
    }

    @Deprecated
    public void insertOrReplace(List<ChatMsg> list) {
        if (list != null && !list.isEmpty()) {
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
            this.a.replace(DatabaseHelper.TABLE_GROUP_MESSAGES, null, arrayList);
        }
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
            return (List) this.a.query(false, DatabaseHelper.TABLE_GROUP_MESSAGES, null, "id in(" + stringBuffer.toString() + ")", strArr, null, null, null, null, this.e);
        }
        List<ChatMsg> arrayList = new ArrayList(get(ArrayUtils.copyOfRange(jArr, 0, 500)));
        arrayList.addAll(get(ArrayUtils.copyOfRange(jArr, 500, jArr.length)));
        return arrayList;
    }

    public List<ChatMsg> getByMsgIds(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        int length = strArr.length;
        if (length <= 500) {
            String[] strArr2 = new String[length];
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < length; i++) {
                strArr2[i] = strArr[i];
                stringBuffer.append("?").append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            return (List) this.a.query(false, DatabaseHelper.TABLE_GROUP_MESSAGES, null, "msgid in(" + stringBuffer.toString() + ")", strArr2, null, null, null, null, this.e);
        }
        List<ChatMsg> arrayList = new ArrayList(getByMsgIds(ArrayUtils.copyOfRange(strArr, 0, 500)));
        arrayList.addAll(getByMsgIds(ArrayUtils.copyOfRange(strArr, 500, strArr.length)));
        return arrayList;
    }

    public int updateMessageData(ChatMsg chatMsg) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("data").append(" =? ").append(" where ").append("msgid").append(" =? ;").toString(), new String[]{chatMsg.data, chatMsg.msgid});
        return 0;
    }

    public int setMessageReadedBatch(List<Long> list) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("state").append(" = ").append(5).append(" where ").append("id").append(" in (" + ArrayUtils.join(list, com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP) + ") ;").toString(), new String[0]);
        return 0;
    }

    public int setMessageReaded(List<String> list) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("state").append(" = ").append(5).append(" where ").append("msgid").append(" in ( '" + ArrayUtils.join(list, "','") + "');").toString(), new String[0]);
        return 0;
    }

    public List<String> getUnreadMsgIds(String str) {
        String stringBuffer = new StringBuffer().append("state").append(" = ? and ").append("gid").append(" = ? and ").append("inout").append(" = ? ").toString();
        String[] strArr = new String[]{"4", str, "1"};
        DatabaseHelper$RowMapping asVar = new as(this);
        return (List) this.a.query(false, DatabaseHelper.TABLE_GROUP_MESSAGES, new String[]{"msgid"}, stringBuffer, strArr, null, null, null, null, asVar);
    }

    public int updateMessageStateBatch(List<String> list, int i) {
        a(new StringBuffer("update ").append(DatabaseHelper.TABLE_GROUP_MESSAGES).append(" set ").append("state").append(" =? ").append(" where ").append("msgid").append(" in (" + ArrayUtils.join(ArrayUtils.map(list, new at(this)), com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP) + ") ;").toString(), new String[]{i + ""});
        return 0;
    }

    public List<ChatMsg> getSendFailMsg() {
        return (List) this.a.query(false, DatabaseHelper.TABLE_GROUP_MESSAGES, null, "state=3", null, null, null, "id", null, this.e);
    }

    public boolean isChatMsgExist(String str) {
        String stringBuffer = new StringBuffer().append("msgid").append(" = ?").toString();
        DatabaseHelper$RowMapping auVar = new au(this);
        if (ArrayUtils.isEmpty((List) this.a.query(false, DatabaseHelper.TABLE_GROUP_MESSAGES, new String[]{"msgid"}, stringBuffer, new String[]{str}, null, null, null, null, auVar))) {
            return false;
        }
        return true;
    }
}
