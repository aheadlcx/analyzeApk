package qsbk.app.video;

import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import java.io.File;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ToastAndDialog;

class bd implements OnItemClickListener {
    final /* synthetic */ CursorAdapter a;
    final /* synthetic */ VideoPickerActivity b;

    bd(VideoPickerActivity videoPickerActivity, CursorAdapter cursorAdapter) {
        this.b = videoPickerActivity;
        this.a = cursorAdapter;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == 0) {
            this.b.startVideo();
            return;
        }
        Cursor cursor = (Cursor) this.a.getItem(i);
        cursor.moveToPosition(i);
        String string = cursor.getString(cursor.getColumnIndex("_data"));
        int i2 = cursor.getInt(cursor.getColumnIndex("duration"));
        int i3 = cursor.getInt(cursor.getColumnIndex(IndexEntry.COLUMN_NAME_WIDTH));
        int i4 = cursor.getInt(cursor.getColumnIndex(IndexEntry.COLUMN_NAME_HEIGHT));
        DebugUtil.debug(VideoPickerActivity.a, "path:" + string + "  time:" + i2 + "  width:" + i3 + "  height:" + i4);
        if (new File(string).exists()) {
            if (i3 == 0 || i4 == 0) {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever.setDataSource(string);
                    i3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                    i4 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ImageInfo imageInfo = new ImageInfo(UriUtil.getUriForFile(new File(string)).toString(), MediaFormat.VIDEO);
            imageInfo.width = i3;
            imageInfo.height = i4;
            VideoEditActivity.launchForResult(this.b, imageInfo);
            return;
        }
        ToastAndDialog.makeNeutralToast(this.b, "不合法的视频文件。").show();
    }
}
