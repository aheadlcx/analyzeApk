package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;

class ag extends x {
    public ag() {
        super("ap_list3", "Create table if not exists ap_list3(_id Integer primary key AUTOINCREMENT,time VARCHAR(50),content TEXT);");
    }

    public ArrayList<w> a(int i, int i2) {
        Cursor a = a("time", i, i2);
        ArrayList<w> a2 = a(a);
        if (a != null) {
            a.close();
        }
        return a2;
    }

    public long a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", str);
        contentValues.put("content", str2);
        return a(contentValues);
    }

    public boolean b(long j) {
        return a(j);
    }

    private ArrayList<w> a(Cursor cursor) {
        ArrayList<w> arrayList = new ArrayList();
        if (!(cursor == null || cursor.getCount() == 0)) {
            int columnIndex = cursor.getColumnIndex(FileDownloadModel.ID);
            int columnIndex2 = cursor.getColumnIndex("time");
            int columnIndex3 = cursor.getColumnIndex("content");
            while (cursor.moveToNext()) {
                arrayList.add(new w(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3)));
            }
        }
        return arrayList;
    }
}
