package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

class t implements DatabaseHelper$RowMapping<List<String>> {
    final /* synthetic */ ChatMsgStore a;

    t(ChatMsgStore chatMsgStore) {
        this.a = chatMsgStore;
    }

    public List<String> map(Cursor cursor) {
        List arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                arrayList.add(cursor.getString(cursor.getColumnIndex("msgid")));
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}
