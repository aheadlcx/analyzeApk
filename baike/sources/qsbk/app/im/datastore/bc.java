package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.im.SyncMsgTable;

class bc implements DatabaseHelper$RowMapping<List<SyncMsgTable>> {
    final /* synthetic */ SyncMsgStore a;

    bc(SyncMsgStore syncMsgStore) {
        this.a = syncMsgStore;
    }

    public List<SyncMsgTable> map(Cursor cursor) {
        List arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SyncMsgTable syncMsgTable = new SyncMsgTable();
                syncMsgTable.type = cursor.getInt(cursor.getColumnIndex("type"));
                syncMsgTable.localSeqid = cursor.getLong(cursor.getColumnIndex(DatabaseHelper$SyncMsgRow._PRE));
                syncMsgTable.id = cursor.getInt(cursor.getColumnIndex("id")) + "";
                syncMsgTable.serverSeqid = cursor.getLong(cursor.getColumnIndex(DatabaseHelper$SyncMsgRow._CUR));
                arrayList.add(syncMsgTable);
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}
