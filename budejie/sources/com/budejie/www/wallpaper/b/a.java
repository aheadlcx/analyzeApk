package com.budejie.www.wallpaper.b;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.provider.MediaStore.Video.Media;
import android.provider.MediaStore.Video.Thumbnails;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.activity.image.BitmapCache;
import com.budejie.www.activity.video.b;
import com.budejie.www.activity.video.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class a {
    public static void a(Context context) {
        a(context, "/DCIM/Camera");
        a(context, "/相机");
    }

    public static List<com.budejie.www.wallpaper.a.a> a(Context context, boolean z) {
        List<com.budejie.www.wallpaper.a.a> arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{HistoryOpenHelper.COLUMN_ID, "_data", "duration", "_size", "_display_name", "date_modified"}, "mime_type=?", new String[]{"video/mp4"}, "date_modified desc");
        if (query != null) {
            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("_data"));
                if (!TextUtils.isEmpty(string)) {
                    Object obj = (TextUtils.isEmpty(string) || !string.contains(b.a())) ? null : 1;
                    if (!(z && obj == null) && (z || obj == null)) {
                        int i = query.getInt(query.getColumnIndex(HistoryOpenHelper.COLUMN_ID));
                        int i2 = query.getInt(query.getColumnIndex("duration"));
                        long length = new File(string).length() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                        if (length > 0) {
                            Object obj2;
                            String string2 = query.getString(query.getColumnIndex("_display_name"));
                            Thumbnails.getThumbnail(context.getContentResolver(), (long) i, 3, null);
                            Cursor query2 = context.getContentResolver().query(Thumbnails.EXTERNAL_CONTENT_URI, new String[]{HistoryOpenHelper.COLUMN_ID, "_data"}, "video_id=?", new String[]{i + ""}, null);
                            obj = "";
                            if (query2 != null) {
                                while (query2.moveToNext()) {
                                    obj = query2.getString(query2.getColumnIndex("_data"));
                                }
                                query2.close();
                            }
                            if (TextUtils.isEmpty(obj)) {
                                obj2 = null;
                            } else {
                                File file = new File(obj);
                                file.length();
                                obj2 = file.length() > 0 ? 1 : null;
                            }
                            if (obj2 == null) {
                                obj = a(string);
                                if (!new File(obj).exists()) {
                                    Bitmap a = BitmapCache.a(string, 1);
                                    if (a != null) {
                                        if (a(string, a)) {
                                            a.recycle();
                                        } else {
                                            a.recycle();
                                        }
                                    }
                                }
                            }
                            if (!TextUtils.isEmpty(obj)) {
                                com.budejie.www.wallpaper.a.a aVar = new com.budejie.www.wallpaper.a.a();
                                aVar.a(string);
                                aVar.b(obj);
                                aVar.a(i2);
                                aVar.a(length);
                                aVar.c(string2);
                                arrayList.add(aVar);
                            }
                        }
                    }
                }
            }
            query.close();
        }
        return arrayList;
    }

    private static boolean a(String str, Bitmap bitmap) {
        if (TextUtils.isEmpty(str) || !str.endsWith(".mp4")) {
            return false;
        }
        return b.a(bitmap, a(str), CompressFormat.JPEG);
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str) || !str.endsWith(".mp4")) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf("/");
        int lastIndexOf2 = str.lastIndexOf(".");
        if (lastIndexOf2 < 0 || lastIndexOf2 > str.length()) {
            return "";
        }
        return d.b() + str.substring(lastIndexOf, lastIndexOf2) + ".jpg";
    }

    private static void a(Context context, String str) {
        if (com.budejie.www.goddubbing.c.d.q()) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + str);
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    Date date = null;
                    for (File file2 : listFiles) {
                        if (file2 != null) {
                            if (date == null) {
                                date = new Date();
                            }
                            Object name = file2.getName();
                            if (!TextUtils.isEmpty(name) && name.endsWith(".mp4")) {
                                date.setTime(file2.lastModified());
                                name = SimpleDateFormat.getDateInstance().format(date);
                                String format = SimpleDateFormat.getDateInstance().format(Long.valueOf(System.currentTimeMillis()));
                                if (!TextUtils.isEmpty(name) && name.equals(format)) {
                                    com.budejie.www.goddubbing.c.d.a(context, file2.getPath());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
