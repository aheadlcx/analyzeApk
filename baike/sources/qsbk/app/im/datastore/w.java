package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.im.ContactListItem;

final class w implements DatabaseHelper$RowMapping<List<ContactListItem>> {
    w() {
    }

    public List<ContactListItem> map(Cursor cursor) {
        List arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ContactListItem contactListItem = new ContactListItem();
                contactListItem.id = cursor.getString(cursor.getColumnIndex("uid"));
                contactListItem.msgId = cursor.getLong(cursor.getColumnIndex("msgid"));
                contactListItem.icon = cursor.getString(cursor.getColumnIndex("icon"));
                contactListItem.name = cursor.getString(cursor.getColumnIndex("name"));
                contactListItem.mLastContent = cursor.getString(cursor.getColumnIndex("data"));
                contactListItem.mLastUpdateTime = cursor.getLong(cursor.getColumnIndex("t"));
                contactListItem.type = cursor.getInt(cursor.getColumnIndex("type"));
                contactListItem.atMsgId = cursor.getLong(cursor.getColumnIndex("at"));
                contactListItem.atType = cursor.getInt(cursor.getColumnIndex(DatabaseHelper$ChatRow._AT_TYPE));
                arrayList.add(contactListItem);
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}
