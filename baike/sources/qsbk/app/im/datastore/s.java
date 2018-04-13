package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.im.ChatMsg;

class s implements DatabaseHelper$RowMapping<List<ChatMsg>> {
    final /* synthetic */ ChatMsgStore a;

    s(ChatMsgStore chatMsgStore) {
        this.a = chatMsgStore;
    }

    public List<ChatMsg> map(Cursor cursor) {
        List<ChatMsg> arrayList = new ArrayList();
        if (!cursor.moveToFirst()) {
            return arrayList;
        }
        while (!cursor.isAfterLast()) {
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.type = cursor.getInt(cursor.getColumnIndex("type"));
            chatMsg.data = cursor.getString(cursor.getColumnIndex("data"));
            chatMsg.dbid = cursor.getLong(cursor.getColumnIndex("id"));
            chatMsg.inout = cursor.getInt(cursor.getColumnIndex("inout"));
            chatMsg.uid = cursor.getString(cursor.getColumnIndex("uid"));
            chatMsg.msgsrc = cursor.getString(cursor.getColumnIndex("msgsrc"));
            chatMsg.from = chatMsg.inout == 1 ? chatMsg.uid : this.a.b;
            chatMsg.to = chatMsg.inout == 2 ? chatMsg.uid : this.a.b;
            chatMsg.msgid = cursor.getString(cursor.getColumnIndex("msgid"));
            chatMsg.status = cursor.getInt(cursor.getColumnIndex("state"));
            chatMsg.time = cursor.getLong(cursor.getColumnIndex("t"));
            chatMsg.fromicon = cursor.getString(cursor.getColumnIndex("icon"));
            chatMsg.fromnick = cursor.getString(cursor.getColumnIndex("name"));
            chatMsg.mType = cursor.getInt(cursor.getColumnIndex(DatabaseHelper$MessageRow._MTYPE));
            arrayList.add(chatMsg);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
