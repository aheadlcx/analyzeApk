package cn.xiaochuankeji.tieba.ui.my.download;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Files;
import android.support.v4.content.CursorLoader;

public class h extends CursorLoader {
    private static final Uri a = Files.getContentUri("external");
    private static final String[] b = new String[]{"_id", "_display_name", "_data", "mime_type", "_size", "width", "height", "datetaken", "duration"};

    private static String[] a() {
        return new String[]{String.valueOf(1), String.valueOf(3), "%/DCIM/zuiyou%"};
    }

    private h(Context context, String str, String[] strArr) {
        super(context, a, b, str, strArr, "datetaken DESC");
    }

    public static CursorLoader a(Context context) {
        return new h(context, "(media_type=? OR media_type=?) AND _size>0 AND _id>0 AND _data like ? AND _data !=''", a());
    }

    public Cursor loadInBackground() {
        return super.loadInBackground();
    }

    public void onContentChanged() {
    }
}
