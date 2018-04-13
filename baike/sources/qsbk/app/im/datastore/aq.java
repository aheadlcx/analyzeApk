package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.im.ChatMsg;

class aq implements DatabaseHelper$RowMapping<List<ChatMsg>> {
    final /* synthetic */ GroupChatMsgStore a;

    aq(GroupChatMsgStore groupChatMsgStore) {
        this.a = groupChatMsgStore;
    }

    public List<ChatMsg> map(Cursor cursor) {
        List<ChatMsg> arrayList = new ArrayList();
        if (!cursor.moveToFirst()) {
            return arrayList;
        }
        while (!cursor.isAfterLast()) {
            String str;
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.type = cursor.getInt(cursor.getColumnIndex("type"));
            chatMsg.data = cursor.getString(cursor.getColumnIndex("data"));
            chatMsg.dbid = cursor.getLong(cursor.getColumnIndex("id"));
            chatMsg.gid = cursor.getString(cursor.getColumnIndex("gid"));
            chatMsg.uid = cursor.getString(cursor.getColumnIndex("uid"));
            chatMsg.inout = cursor.getInt(cursor.getColumnIndex("inout"));
            chatMsg.msgsrc = cursor.getString(cursor.getColumnIndex("msgsrc"));
            chatMsg.from = chatMsg.inout == 1 ? chatMsg.uid : this.a.b;
            if (chatMsg.inout == 2) {
                str = chatMsg.uid;
            } else {
                str = this.a.b;
            }
            chatMsg.to = str;
            chatMsg.msgid = cursor.getString(cursor.getColumnIndex("msgid"));
            chatMsg.status = cursor.getInt(cursor.getColumnIndex("state"));
            chatMsg.time = cursor.getLong(cursor.getColumnIndex("t"));
            chatMsg.fromicon = cursor.getString(cursor.getColumnIndex("icon"));
            chatMsg.fromnick = cursor.getString(cursor.getColumnIndex("name"));
            chatMsg.fromage = cursor.getInt(cursor.getColumnIndex("field1"));
            chatMsg.fromgender = cursor.getString(cursor.getColumnIndex("field2"));
            arrayList.add(chatMsg);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
