package com.zhihu.matisse.internal.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.provider.MediaStore.Files;
import android.support.v4.content.CursorLoader;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.SelectionSpec;

public class AlbumLoader extends CursorLoader {
    private static final String BUCKET_ORDER_BY = "datetaken DESC";
    private static final String[] COLUMNS = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", "count"};
    public static final String COLUMN_COUNT = "count";
    private static final String[] PROJECTION = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", "COUNT(*) AS count"};
    private static final Uri QUERY_URI = Files.getContentUri("external");
    private static final String SELECTION = "(media_type=? OR media_type=?) AND _size>0) GROUP BY (bucket_id";
    private static final String[] SELECTION_ARGS = new String[]{String.valueOf(1), String.valueOf(3)};
    private static final String SELECTION_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND _size>0) GROUP BY (bucket_id";

    private static String[] getSelectionArgsForSingleMediaType(int i) {
        return new String[]{String.valueOf(i)};
    }

    private AlbumLoader(Context context, String str, String[] strArr) {
        super(context, QUERY_URI, PROJECTION, str, strArr, BUCKET_ORDER_BY);
    }

    public static CursorLoader newInstance(Context context) {
        String str;
        String[] selectionArgsForSingleMediaType;
        if (SelectionSpec.getInstance().onlyShowImages()) {
            str = SELECTION_FOR_SINGLE_MEDIA_TYPE;
            selectionArgsForSingleMediaType = getSelectionArgsForSingleMediaType(1);
        } else if (SelectionSpec.getInstance().onlyShowVideos()) {
            str = SELECTION_FOR_SINGLE_MEDIA_TYPE;
            selectionArgsForSingleMediaType = getSelectionArgsForSingleMediaType(3);
        } else {
            str = SELECTION;
            selectionArgsForSingleMediaType = SELECTION_ARGS;
        }
        return new AlbumLoader(context, str, selectionArgsForSingleMediaType);
    }

    public Cursor loadInBackground() {
        int i;
        String string;
        Cursor loadInBackground = super.loadInBackground();
        MatrixCursor matrixCursor = new MatrixCursor(COLUMNS);
        String str = "";
        if (loadInBackground != null) {
            int i2 = 0;
            while (loadInBackground.moveToNext()) {
                i2 += loadInBackground.getInt(loadInBackground.getColumnIndex("count"));
            }
            if (loadInBackground.moveToFirst()) {
                i = i2;
                string = loadInBackground.getString(loadInBackground.getColumnIndex("_data"));
            } else {
                String str2 = str;
                i = i2;
                string = str2;
            }
        } else {
            string = str;
            i = 0;
        }
        matrixCursor.addRow(new String[]{Album.ALBUM_ID_ALL, Album.ALBUM_ID_ALL, Album.ALBUM_NAME_ALL, string, String.valueOf(i)});
        return new MergeCursor(new Cursor[]{matrixCursor, loadInBackground});
    }

    public void onContentChanged() {
    }
}
