package qsbk.app.im.datastore;

import android.database.Cursor;

public interface DatabaseHelper$RowMapping<T> {
    T map(Cursor cursor);
}
