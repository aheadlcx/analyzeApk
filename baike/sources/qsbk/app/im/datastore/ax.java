package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.model.GroupInfo;

class ax implements DatabaseHelper$RowMapping<List<GroupInfo>> {
    final /* synthetic */ GroupStore a;

    ax(GroupStore groupStore) {
        this.a = groupStore;
    }

    public List<GroupInfo> map(Cursor cursor) {
        List<GroupInfo> arrayList = new ArrayList();
        if (!cursor.moveToFirst()) {
            return arrayList;
        }
        while (!cursor.isAfterLast()) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.id = cursor.getInt(cursor.getColumnIndex("gid"));
            groupInfo.dbid = cursor.getLong(cursor.getColumnIndex("id"));
            groupInfo.icon = cursor.getString(cursor.getColumnIndex("field1"));
            groupInfo.name = cursor.getString(cursor.getColumnIndex("field2"));
            groupInfo.isOwner = cursor.getInt(cursor.getColumnIndex("field3")) == 1;
            groupInfo.description = cursor.getString(cursor.getColumnIndex("field4"));
            groupInfo.level = cursor.getInt(cursor.getColumnIndex("field5"));
            groupInfo.status = cursor.getInt(cursor.getColumnIndex("field6"));
            groupInfo.statusUpdatedAt = cursor.getInt(cursor.getColumnIndex("field7"));
            groupInfo.limitMember = cursor.getInt(cursor.getColumnIndex("field8"));
            groupInfo.memberNum = cursor.getInt(cursor.getColumnIndex("field9"));
            groupInfo.joinStatus = cursor.getInt(cursor.getColumnIndex("field10"));
            groupInfo.joinedGroup = cursor.getInt(cursor.getColumnIndex("field11"));
            arrayList.add(groupInfo);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
