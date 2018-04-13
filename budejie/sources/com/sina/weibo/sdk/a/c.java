package com.sina.weibo.sdk.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class c {
    public static String a(Context context, Uri uri, int i) {
        Exception e;
        InputStream bufferedInputStream;
        File file;
        OutputStream fileOutputStream;
        int read;
        Throwable th;
        OutputStream outputStream = null;
        try {
            byte[] bArr;
            String a = com.sina.weibo.sdk.c.b(context).a();
            if (TextUtils.isEmpty(a)) {
                a = "com.sina.weibo";
            }
            String str = "/Android/data/" + a + "/files/.composerTem/";
            new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str).mkdirs();
            Calendar instance = Calendar.getInstance();
            Cursor cursor;
            try {
                if (uri.getScheme().equals(UriUtil.LOCAL_FILE_SCHEME)) {
                    a = instance.getTimeInMillis() + uri.getLastPathSegment();
                    cursor = null;
                } else {
                    cursor = context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                a = cursor.getString(cursor.getColumnIndex("_display_name"));
                            }
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                Log.v("weibo sdk rename", e.toString());
                                if (cursor == null) {
                                    cursor.close();
                                    a = null;
                                } else {
                                    a = null;
                                }
                                if (TextUtils.isEmpty(a)) {
                                    a = Calendar.getInstance().getTimeInMillis() + (i == 0 ? "_sdk_temp.jpg" : "_sdk_temp.mp4");
                                }
                                bufferedInputStream = new BufferedInputStream(new FileInputStream(context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor()));
                                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str + a);
                                if (file.exists()) {
                                    file.delete();
                                }
                                fileOutputStream = new FileOutputStream(file);
                                try {
                                    bArr = new byte[1444];
                                    while (true) {
                                        read = bufferedInputStream.read(bArr);
                                        if (read != -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, 0, read);
                                    }
                                    a = file.getPath();
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (Exception e3) {
                                            return a;
                                        }
                                    }
                                    if (fileOutputStream != null) {
                                        return a;
                                    }
                                    fileOutputStream.close();
                                    return a;
                                } catch (Exception e4) {
                                    e = e4;
                                    try {
                                        Log.v("weibo sdk copy", e.toString());
                                        if (bufferedInputStream != null) {
                                            try {
                                                bufferedInputStream.close();
                                            } catch (Exception e5) {
                                                return null;
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        return null;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        outputStream = fileOutputStream;
                                        if (bufferedInputStream != null) {
                                            try {
                                                bufferedInputStream.close();
                                            } catch (Exception e6) {
                                                throw th;
                                            }
                                        }
                                        if (outputStream != null) {
                                            outputStream.close();
                                        }
                                        throw th;
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                    }
                    CharSequence charSequence = null;
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e7) {
                e = e7;
                cursor = null;
                Log.v("weibo sdk rename", e.toString());
                if (cursor == null) {
                    a = null;
                } else {
                    cursor.close();
                    a = null;
                }
                if (TextUtils.isEmpty(a)) {
                    if (i == 0) {
                    }
                    a = Calendar.getInstance().getTimeInMillis() + (i == 0 ? "_sdk_temp.jpg" : "_sdk_temp.mp4");
                }
                bufferedInputStream = new BufferedInputStream(new FileInputStream(context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor()));
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str + a);
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream = new FileOutputStream(file);
                bArr = new byte[1444];
                while (true) {
                    read = bufferedInputStream.read(bArr);
                    if (read != -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                a = file.getPath();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileOutputStream != null) {
                    return a;
                }
                fileOutputStream.close();
                return a;
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
            if (TextUtils.isEmpty(a)) {
                if (i == 0) {
                }
                a = Calendar.getInstance().getTimeInMillis() + (i == 0 ? "_sdk_temp.jpg" : "_sdk_temp.mp4");
            }
            bufferedInputStream = new BufferedInputStream(new FileInputStream(context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor()));
            try {
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str + a);
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream = new FileOutputStream(file);
                bArr = new byte[1444];
                while (true) {
                    read = bufferedInputStream.read(bArr);
                    if (read != -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                a = file.getPath();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileOutputStream != null) {
                    return a;
                }
                fileOutputStream.close();
                return a;
            } catch (Exception e8) {
                e = e8;
                fileOutputStream = null;
                Log.v("weibo sdk copy", e.toString());
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return null;
            } catch (Throwable th5) {
                th = th5;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Exception e9) {
            e = e9;
            fileOutputStream = null;
            bufferedInputStream = null;
            Log.v("weibo sdk copy", e.toString());
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return null;
        } catch (Throwable th6) {
            th = th6;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    @TargetApi(10)
    public static long a(String str) {
        long j = 0;
        if (new File(str).exists()) {
            try {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(str);
                j = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
            } catch (Exception e) {
            }
        }
        return j;
    }
}
