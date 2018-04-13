package qsbk.app.im.datastore;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.im.LatestUsedCollectionData;

final class az implements DatabaseHelper$RowMapping<List<LatestUsedCollectionData>> {
    az() {
    }

    public List<LatestUsedCollectionData> map(Cursor cursor) {
        List<LatestUsedCollectionData> arrayList = new ArrayList();
        if (!cursor.moveToFirst()) {
            return arrayList;
        }
        while (!cursor.isAfterLast()) {
            arrayList.add(new LatestUsedCollectionData(cursor.getInt(cursor.getColumnIndex("id")), cursor.getInt(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex("data")), cursor.getLong(cursor.getColumnIndex(DatabaseHelper$LatestUsedCollectionRow._USEDTIME))));
            cursor.moveToNext();
        }
        return arrayList;
    }
}
