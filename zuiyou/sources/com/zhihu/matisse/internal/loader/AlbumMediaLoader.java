package com.zhihu.matisse.internal.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.provider.MediaStore.Files;
import android.support.v4.content.CursorLoader;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

public class AlbumMediaLoader extends CursorLoader {
    private static final String ORDER_BY = "datetaken DESC";
    private static final String[] PROJECTION = new String[]{"_id", "_display_name", "_data", "mime_type", "_size", "width", "height", "datetaken", "duration"};
    private static final Uri QUERY_URI = Files.getContentUri("external");
    private static final String SELECTION_ALBUM = "(media_type=? OR media_type=?) AND  bucket_id=? AND _size>0";
    private static final String SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND  bucket_id=? AND _size>0";
    private static final String SELECTION_ALL = "(media_type=? OR media_type=?) AND _size>0 AND _id>0 AND _data!=''";
    private static final String[] SELECTION_ALL_ARGS = new String[]{String.valueOf(1), String.valueOf(3)};
    private static final String SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND _size>0 AND _id>0 AND _data!=''";
    private final boolean mEnableCapture;

    private static String[] getSelectionArgsForSingleMediaType(int i) {
        return new String[]{String.valueOf(i)};
    }

    private static String[] getSelectionAlbumArgs(String str) {
        return new String[]{String.valueOf(1), String.valueOf(3), str};
    }

    private static String[] getSelectionAlbumArgsForSingleMediaType(int i, String str) {
        return new String[]{String.valueOf(i), str};
    }

    private AlbumMediaLoader(Context context, String str, String[] strArr, boolean z) {
        super(context, QUERY_URI, PROJECTION, str, strArr, ORDER_BY);
        this.mEnableCapture = z;
    }

    public static CursorLoader newInstance(Context context, Album album, boolean z) {
        String str;
        String[] selectionAlbumArgsForSingleMediaType;
        if (!album.isAll()) {
            if (SelectionSpec.getInstance().onlyShowImages()) {
                str = SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE;
                selectionAlbumArgsForSingleMediaType = getSelectionAlbumArgsForSingleMediaType(1, album.getId());
            } else if (SelectionSpec.getInstance().onlyShowVideos()) {
                str = SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE;
                selectionAlbumArgsForSingleMediaType = getSelectionAlbumArgsForSingleMediaType(3, album.getId());
            } else {
                str = SELECTION_ALBUM;
                selectionAlbumArgsForSingleMediaType = getSelectionAlbumArgs(album.getId());
            }
            z = false;
        } else if (SelectionSpec.getInstance().onlyShowImages()) {
            str = SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE;
            selectionAlbumArgsForSingleMediaType = getSelectionArgsForSingleMediaType(1);
        } else if (SelectionSpec.getInstance().onlyShowVideos()) {
            str = SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE;
            selectionAlbumArgsForSingleMediaType = getSelectionArgsForSingleMediaType(3);
        } else {
            str = SELECTION_ALL;
            selectionAlbumArgsForSingleMediaType = SELECTION_ALL_ARGS;
        }
        return new AlbumMediaLoader(context, str, selectionAlbumArgsForSingleMediaType, z);
    }

    public Cursor loadInBackground() {
        Cursor loadInBackground = super.loadInBackground();
        if (!this.mEnableCapture || !MediaStoreCompat.hasCameraFeature(getContext())) {
            return loadInBackground;
        }
        new MatrixCursor(PROJECTION).addRow(new Object[]{Long.valueOf(-1), Item.ITEM_DISPLAY_NAME_CAPTURE, "", "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)});
        return new MergeCursor(new Cursor[]{r2, loadInBackground});
    }

    public void onContentChanged() {
    }
}
