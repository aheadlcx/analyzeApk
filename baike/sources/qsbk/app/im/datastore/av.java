package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.model.GroupNotice;

class av implements DatabaseHelper$RowMapping<List<GroupNotice>> {
    final /* synthetic */ GroupNoticeStore a;

    av(GroupNoticeStore groupNoticeStore) {
        this.a = groupNoticeStore;
    }

    public List<GroupNotice> map(Cursor cursor) {
        List arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int i = cursor.getInt(cursor.getColumnIndex("id"));
                String string = cursor.getString(cursor.getColumnIndex("json"));
                GroupNotice groupNotice = new GroupNotice(i, cursor.getInt(cursor.getColumnIndex("state")), cursor.getLong(cursor.getColumnIndex("t")));
                groupNotice.parse(string, null);
                arrayList.add(groupNotice);
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}
