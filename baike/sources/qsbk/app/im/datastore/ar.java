package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

class ar implements DatabaseHelper$RowMapping<List<int[]>> {
    final /* synthetic */ GroupChatMsgStore a;

    ar(GroupChatMsgStore groupChatMsgStore) {
        this.a = groupChatMsgStore;
    }

    public List<int[]> map(Cursor cursor) {
        List arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                arrayList.add(new int[]{cursor.getInt(0), cursor.getInt(1)});
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}
