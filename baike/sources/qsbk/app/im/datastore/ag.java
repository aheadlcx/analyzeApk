package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.im.ChatMsg;

final class ag implements DatabaseHelper$RowMapping<List<ChatMsg>> {
    ag() {
    }

    public List<ChatMsg> map(Cursor cursor) {
        List arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ChatMsg chatMsg = new ChatMsg(100);
                chatMsg.time = cursor.getLong(cursor.getColumnIndex("t"));
                chatMsg.data = cursor.getString(cursor.getColumnIndex("data"));
                chatMsg.uid = cursor.getString(cursor.getColumnIndex("uid"));
                chatMsg.status = 6;
                arrayList.add(chatMsg);
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}
