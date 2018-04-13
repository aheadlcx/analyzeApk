package com.soundcloud.android.crop;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.soundcloud.android.crop.MonitoredActivity.LifeCycleAdapter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import qsbk.app.core.model.CustomButton;

class i {

    private static class a extends LifeCycleAdapter implements Runnable {
        private final MonitoredActivity a;
        private final ProgressDialog b;
        private final Runnable c;
        private final Handler d;
        private final Runnable e = new j(this);

        public a(MonitoredActivity monitoredActivity, Runnable runnable, ProgressDialog progressDialog, Handler handler) {
            this.a = monitoredActivity;
            this.b = progressDialog;
            this.c = runnable;
            this.a.addLifeCycleListener(this);
            this.d = handler;
        }

        public void run() {
            try {
                this.c.run();
            } finally {
                this.d.post(this.e);
            }
        }

        public void onActivityDestroyed(MonitoredActivity monitoredActivity) {
            this.e.run();
            this.d.removeCallbacks(this.e);
        }

        public void onActivityStopped(MonitoredActivity monitoredActivity) {
            this.b.hide();
        }

        public void onActivityStarted(MonitoredActivity monitoredActivity) {
            this.b.show();
        }
    }

    public static void closeSilently(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    public static int getExifRotation(File file) {
        if (file == null) {
            return 0;
        }
        try {
            switch (new ExifInterface(file.getAbsolutePath()).getAttributeInt("Orientation", 0)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (Throwable e) {
            n.e("Error getting Exif data", e);
            return 0;
        }
    }

    public static boolean copyExifRotation(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            ExifInterface exifInterface2 = new ExifInterface(file2.getAbsolutePath());
            exifInterface2.setAttribute("Orientation", exifInterface.getAttribute("Orientation"));
            exifInterface2.saveAttributes();
            return true;
        } catch (Throwable e) {
            n.e("Error copying Exif data", e);
            return false;
        }
    }

    @Nullable
    public static File getFromMediaUri(Context context, ContentResolver contentResolver, Uri uri) {
        File file;
        Throwable th;
        Cursor cursor = null;
        if (uri == null) {
            return null;
        }
        if (UriUtil.LOCAL_FILE_SCHEME.equals(uri.getScheme())) {
            return new File(uri.getPath());
        }
        if ("content".equals(uri.getScheme())) {
            Cursor query;
            try {
                query = contentResolver.query(uri, new String[]{"_data", "_display_name"}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int columnIndex;
                            if (uri.toString().startsWith("content://com.google.android.gallery3d")) {
                                columnIndex = query.getColumnIndex("_display_name");
                            } else {
                                columnIndex = query.getColumnIndex("_data");
                            }
                            if (columnIndex != -1) {
                                Object string = query.getString(columnIndex);
                                if (!TextUtils.isEmpty(string)) {
                                    file = new File(string);
                                    if (query == null) {
                                        return file;
                                    }
                                    query.close();
                                    return file;
                                }
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        cursor = query;
                        try {
                            file = a(context, contentResolver, uri);
                            if (cursor != null) {
                                return file;
                            }
                            cursor.close();
                            return file;
                        } catch (Throwable th2) {
                            th = th2;
                            query = cursor;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    } catch (SecurityException e2) {
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (IllegalArgumentException e3) {
                file = a(context, contentResolver, uri);
                if (cursor != null) {
                    return file;
                }
                cursor.close();
                return file;
            } catch (SecurityException e4) {
                query = null;
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
        return null;
    }

    private static String a(Context context) throws IOException {
        return File.createTempFile("image", "tmp", context.getCacheDir()).getAbsolutePath();
    }

    @Nullable
    private static File a(Context context, ContentResolver contentResolver, Uri uri) {
        Closeable fileInputStream;
        Closeable closeable;
        Throwable th;
        if (uri == null) {
            return null;
        }
        Closeable fileOutputStream;
        try {
            String a;
            fileInputStream = new FileInputStream(contentResolver.openFileDescriptor(uri, CustomButton.POSITION_RIGHT).getFileDescriptor());
            try {
                a = a(context);
                fileOutputStream = new FileOutputStream(a);
            } catch (IOException e) {
                closeable = null;
                fileOutputStream = fileInputStream;
                closeSilently(fileOutputStream);
                closeSilently(closeable);
                return null;
            } catch (Throwable th2) {
                fileOutputStream = null;
                th = th2;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
                throw th;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        File file = new File(a);
                        closeSilently(fileInputStream);
                        closeSilently(fileOutputStream);
                        return file;
                    }
                }
            } catch (IOException e2) {
                closeable = fileOutputStream;
                fileOutputStream = fileInputStream;
                closeSilently(fileOutputStream);
                closeSilently(closeable);
                return null;
            } catch (Throwable th3) {
                th = th3;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            closeable = null;
            fileOutputStream = null;
            closeSilently(fileOutputStream);
            closeSilently(closeable);
            return null;
        } catch (Throwable th22) {
            fileOutputStream = null;
            fileInputStream = null;
            th = th22;
            closeSilently(fileInputStream);
            closeSilently(fileOutputStream);
            throw th;
        }
    }

    public static void startBackgroundJob(MonitoredActivity monitoredActivity, String str, String str2, Runnable runnable, Handler handler) {
        new Thread(new a(monitoredActivity, runnable, ProgressDialog.show(monitoredActivity, str, str2, true, false), handler)).start();
    }
}
