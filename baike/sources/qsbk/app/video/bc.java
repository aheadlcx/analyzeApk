package qsbk.app.video;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.common.util.UriUtil;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;

class bc extends CursorAdapter {
    final /* synthetic */ VideoPickerActivity a;

    bc(VideoPickerActivity videoPickerActivity, Context context, Cursor cursor) {
        this.a = videoPickerActivity;
        super(context, cursor);
    }

    public int getItemViewType(int i) {
        Cursor cursor = (Cursor) getItem(i);
        if (cursor.getInt(cursor.getColumnIndex(FileDownloadModel.ID)) == -2147483647) {
            return 0;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        int i = cursor.getInt(cursor.getColumnIndex(FileDownloadModel.ID));
        Log.i(VideoPickerActivity.a, "id = " + i);
        if (i == -2147483647) {
            return LayoutInflater.from(context).inflate(R.layout.local_shot_item, viewGroup, false);
        }
        return LayoutInflater.from(context).inflate(R.layout.local_video_item, viewGroup, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        Log.i(VideoPickerActivity.a, "id = " + cursor.getInt(cursor.getColumnIndex(FileDownloadModel.ID)));
        switch (getItemViewType(cursor.getPosition())) {
            case 1:
                ((TextView) view.findViewById(R.id.video_duration)).setText("时长：" + String.format("%.2f", new Object[]{Double.valueOf(((double) ((long) cursor.getInt(cursor.getColumnIndex("duration")))) / 1000.0d)}) + "s");
                ImageView imageView = (ImageView) view.findViewById(R.id.video_thumb);
                Object string = cursor.getString(cursor.getColumnIndex("_data"));
                if (!TextUtils.isEmpty(string)) {
                    Uri uriForFile = UriUtil.getUriForFile(new File(string));
                    if (uriForFile != null) {
                        FrescoImageloader.displayImage(imageView, uriForFile.toString());
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }
}
