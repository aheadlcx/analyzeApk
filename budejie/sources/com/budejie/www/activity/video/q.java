package com.budejie.www.activity.video;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Video;
import android.provider.MediaStore.Video.Thumbnails;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.activity.image.BitmapCache;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import java.io.File;

public class q {
    public static UploadInfo a(Context context, String str) {
        String[] strArr = new String[]{"_data", "video_id", IndexEntry.COLUMN_NAME_WIDTH, IndexEntry.COLUMN_NAME_HEIGHT};
        String[] strArr2 = new String[]{HistoryOpenHelper.COLUMN_ID, "_data", "_size", "duration", "date_added"};
        String str2 = "_data=?";
        if (str == null) {
            return null;
        }
        String[] strArr3;
        String str3;
        UploadInfo uploadInfo;
        if (str.toString().startsWith("content://media/")) {
            Uri.parse(str);
            strArr3 = null;
            str3 = null;
        } else {
            Uri uri = Media.EXTERNAL_CONTENT_URI;
            str3 = "_data=?";
            strArr3 = new String[]{str};
        }
        Cursor query = context.getContentResolver().query(Video.Media.EXTERNAL_CONTENT_URI, strArr2, str3, strArr3, null);
        String string;
        Bitmap a;
        if (query.moveToFirst()) {
            uploadInfo = new UploadInfo();
            int i = query.getInt(query.getColumnIndex(HistoryOpenHelper.COLUMN_ID));
            Cursor query2 = context.getContentResolver().query(Thumbnails.EXTERNAL_CONTENT_URI, strArr, "video_id=" + i, null, null);
            if (query2 != null && query2.moveToFirst()) {
                string = query2.getString(query2.getColumnIndex("_data"));
                str3 = query2.getString(query2.getColumnIndex(IndexEntry.COLUMN_NAME_WIDTH));
                String string2 = query2.getString(query2.getColumnIndex(IndexEntry.COLUMN_NAME_HEIGHT));
                if (new File(string).exists()) {
                    uploadInfo.setImage(string);
                    uploadInfo.setWidth(String.valueOf(str3));
                    uploadInfo.setHeight(String.valueOf(string2));
                }
                query2.close();
            }
            if (uploadInfo.getImage() == null) {
                a = BitmapCache.a(str, 1);
                if (a != null) {
                    string = d.b() + "/thumbnail" + System.currentTimeMillis() + ".jpg";
                    b.a(a, string, CompressFormat.JPEG);
                    uploadInfo.setImage(string);
                    uploadInfo.setWidth(String.valueOf(a.getWidth()));
                    uploadInfo.setHeight(String.valueOf(a.getHeight()));
                }
            }
            long j = query.getLong(query.getColumnIndexOrThrow("_size"));
            if (j == 0) {
                return null;
            }
            uploadInfo.setFileSize(String.valueOf(j));
            String string3 = query.getString(query.getColumnIndexOrThrow("_data"));
            uploadInfo.setVideo(string3);
            uploadInfo.setName(o.a(string3));
            uploadInfo.setDuration(String.valueOf(query.getInt(query.getColumnIndexOrThrow("duration"))));
        } else {
            uploadInfo = new UploadInfo();
            a = BitmapCache.a(str, 1);
            if (a != null) {
                string = d.b() + "/thumbnail" + System.currentTimeMillis() + ".jpg";
                b.a(a, string, CompressFormat.JPEG);
                uploadInfo.setImage(string);
                uploadInfo.setWidth(String.valueOf(a.getWidth()));
                uploadInfo.setHeight(String.valueOf(a.getHeight()));
            }
            uploadInfo.setVideo(str);
            uploadInfo.setName(o.a(str));
            MediaPlayer create = MediaPlayer.create(context, Uri.parse(str));
            if (create != null) {
                try {
                    create.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            uploadInfo.setDuration(String.valueOf(create.getDuration()));
        }
        query.close();
        return uploadInfo;
    }
}
