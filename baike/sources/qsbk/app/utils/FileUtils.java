package qsbk.app.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.RefreshTipView;

public class FileUtils {

    public interface CallBack {
        void onResult(boolean z);
    }

    public interface Filter {
        boolean filter(File file);
    }

    public static void cacheCotent(String str, String str2, String str3) {
        new m("qbk-FileUtl1", str, str3, str2).start();
    }

    public static String saveDrawable(Bitmap bitmap, String str, String str2, Handler handler, boolean z) {
        IOException e;
        Throwable th;
        String str3 = "";
        if (!(bitmap == null || bitmap.isRecycled())) {
            Message message = new Message();
            File file = new File(str2);
            File file2 = new File(str2, str);
            FileOutputStream fileOutputStream = null;
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream2;
            if (file2.exists()) {
                if (z) {
                    file2.delete();
                    try {
                        file2.createNewFile();
                        fileOutputStream2 = new FileOutputStream(file2);
                        try {
                            if (bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream2)) {
                                fileOutputStream2.flush();
                            }
                            if (handler != null) {
                                message.what = 1;
                                message.obj = file2.getPath();
                                handler.sendMessage(message);
                            }
                            str3 = file2.getPath();
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e2) {
                                }
                            }
                        } catch (IOException e3) {
                            e = e3;
                            fileOutputStream = fileOutputStream2;
                            if (handler != null) {
                                try {
                                    message.what = 0;
                                    message.obj = "图片保存失败！";
                                    handler.sendMessage(message);
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileOutputStream2 = fileOutputStream;
                                    if (fileOutputStream2 != null) {
                                        try {
                                            fileOutputStream2.close();
                                        } catch (IOException e4) {
                                        }
                                    }
                                    throw th;
                                }
                            }
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                }
                            }
                            if (handler != null) {
                                message.what = 1;
                                message.obj = file2.getPath();
                                handler.sendMessage(message);
                            }
                            return str3;
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw th;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        if (handler != null) {
                            message.what = 0;
                            message.obj = "图片保存失败！";
                            handler.sendMessage(message);
                        }
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (handler != null) {
                            message.what = 1;
                            message.obj = file2.getPath();
                            handler.sendMessage(message);
                        }
                        return str3;
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream2 = null;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                }
                if (handler != null) {
                    message.what = 1;
                    message.obj = file2.getPath();
                    handler.sendMessage(message);
                }
            } else {
                try {
                    file2.createNewFile();
                    fileOutputStream2 = new FileOutputStream(file2);
                    try {
                        if (bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream2)) {
                            fileOutputStream2.flush();
                        }
                        if (handler != null) {
                            message.what = 1;
                            message.obj = file2.getPath();
                            handler.sendMessage(message);
                        }
                        str3 = file2.getPath();
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e7) {
                            }
                        }
                    } catch (IOException e8) {
                        e = e8;
                        try {
                            Log.e("FileUtils", e.toString() + "--头像保存时出现异常！");
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e9) {
                                }
                            }
                            return str3;
                        } catch (Throwable th5) {
                            th = th5;
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e10) {
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e11) {
                    e = e11;
                    fileOutputStream2 = null;
                    Log.e("FileUtils", e.toString() + "--头像保存时出现异常！");
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    return str3;
                } catch (Throwable th6) {
                    th = th6;
                    fileOutputStream2 = null;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            }
        }
        return str3;
    }

    public static boolean copyFile(File file, File file2) {
        InputStream fileInputStream;
        OutputStream fileOutputStream;
        IOException e;
        InputStream inputStream;
        Throwable th;
        OutputStream outputStream = null;
        File parentFile = file2.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = null;
                inputStream = fileInputStream;
                try {
                    e.printStackTrace();
                    StorageUtils.closeQuietly(inputStream);
                    StorageUtils.closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = inputStream;
                    outputStream = fileOutputStream;
                    StorageUtils.closeQuietly(fileInputStream);
                    StorageUtils.closeQuietly(outputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                StorageUtils.closeQuietly(fileInputStream);
                StorageUtils.closeQuietly(outputStream);
                throw th;
            }
            try {
                StorageUtils.copy(fileInputStream, fileOutputStream);
                StorageUtils.closeQuietly(fileInputStream);
                StorageUtils.closeQuietly(fileOutputStream);
                return true;
            } catch (IOException e3) {
                e = e3;
                inputStream = fileInputStream;
                e.printStackTrace();
                StorageUtils.closeQuietly(inputStream);
                StorageUtils.closeQuietly(fileOutputStream);
                return false;
            } catch (Throwable th4) {
                th = th4;
                outputStream = fileOutputStream;
                StorageUtils.closeQuietly(fileInputStream);
                StorageUtils.closeQuietly(outputStream);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            fileOutputStream = null;
            e.printStackTrace();
            StorageUtils.closeQuietly(inputStream);
            StorageUtils.closeQuietly(fileOutputStream);
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            StorageUtils.closeQuietly(fileInputStream);
            StorageUtils.closeQuietly(outputStream);
            throw th;
        }
    }

    public static File getSaveDrawableFile(String str, String str2) {
        File file = new File(DeviceUtils.getSDPath() + File.separator + str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.getPath().equals("")) {
            return null;
        }
        return new File(file.getPath() + File.separator + str);
    }

    public static String saveDrawable(Bitmap bitmap, String str, String str2) {
        Object e;
        Throwable th;
        String sDPath = DeviceUtils.getSDPath();
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        File file = new File(sDPath + File.separator + str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        File saveDrawableFile = getSaveDrawableFile(str, str2);
        if (saveDrawableFile != null) {
            try {
                File file2 = new File(saveDrawableFile.getAbsolutePath() + System.currentTimeMillis());
                saveDrawableFile.renameTo(file2);
                file2.delete();
            } catch (Exception e2) {
                Logger.getInstance().debug("FileUtils", "saveDrawable", e2 + "");
            }
            sDPath = saveDrawableFile.getPath();
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(saveDrawableFile, false);
                try {
                    if (bitmap.compress(CompressFormat.JPEG, 85, fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                    if (fileOutputStream == null) {
                        return sDPath;
                    }
                    try {
                        fileOutputStream.close();
                        return sDPath;
                    } catch (IOException e3) {
                        return sDPath;
                    }
                } catch (IOException e4) {
                    e = e4;
                    try {
                        Logger.getInstance().debug("FileUtils", "saveDrawable", e + "");
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                return null;
                            } catch (IOException e5) {
                                return null;
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e7) {
                e = e7;
                fileOutputStream = null;
                Logger.getInstance().debug("FileUtils", "saveDrawable", e + "");
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    return null;
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return null;
    }

    public static void saveContent(String str, String str2) {
        if (str2 != null && !str2.equals("") && !str.equals("")) {
            File file = new File(str);
            if (file.exists()) {
                long lastModified = file.lastModified();
                DebugUtil.debug("上次缓存时间：" + lastModified);
                DebugUtil.debug("缓存时间间隔：" + (System.currentTimeMillis() - lastModified));
                if (System.currentTimeMillis() - lastModified > RefreshTipView.SECOND_REFRESH_INTERVAL) {
                    file.delete();
                    a(file, str2);
                    return;
                }
                return;
            }
            a(file, str2);
        }
    }

    private static void a(File file, String str) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            Log.e("FileUtils", "文件缓存出错 path:" + file.getPath());
            e.printStackTrace();
        }
    }

    public static String getContent(String str) throws IOException {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        String str2 = "";
        File file = new File(str);
        byte[] bArr = new byte[1024];
        InputStream bufferedInputStream;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                while (true) {
                    try {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream2.write(bArr, 0, read);
                        } else {
                            String str3 = new String(byteArrayOutputStream2.toByteArray());
                            bufferedInputStream.close();
                            byteArrayOutputStream2.close();
                            return str3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream = byteArrayOutputStream2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                byteArrayOutputStream = byteArrayOutputStream2;
                bufferedInputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            bufferedInputStream.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public static long getFileSize(File file, List<File> list) {
        long j = 0;
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (list != null) {
                    list.add(listFiles[i]);
                }
                if (listFiles[i].isDirectory()) {
                    j += getFileSize(listFiles[i], list);
                } else {
                    j += listFiles[i].length();
                }
            }
        }
        return j;
    }

    public static int removeOldFiles(File file, FilenameFilter filenameFilter, long j) {
        int i = 0;
        if (file.isDirectory()) {
            Object[] listFiles;
            if (filenameFilter != null) {
                listFiles = file.listFiles(filenameFilter);
            } else {
                listFiles = file.listFiles();
            }
            long fileSize = getFileSize(file, null);
            if (DebugUtil.DEBUG) {
                Log.d(FileUtils.class.getName(), "total size is " + fileSize);
            }
            if (fileSize <= j) {
                return 0;
            }
            Arrays.sort(listFiles);
            int length = listFiles.length;
            int i2 = 0;
            while (i < length / 2) {
                if (listFiles[i].delete()) {
                    i2++;
                }
                i++;
            }
            return i2;
        }
        throw new IllegalArgumentException("the first param is not directory");
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        Cursor query;
        Cursor cursor;
        Throwable th;
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            try {
                query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                    if (query.moveToFirst()) {
                        String string = query.getString(columnIndexOrThrow);
                        if (query == null) {
                            return string;
                        }
                        query.close();
                        return string;
                    } else if (query != null) {
                        query.close();
                    }
                } catch (Exception e) {
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static Bitmap getImage(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        if (httpURLConnection.getResponseCode() == 200) {
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        }
        return null;
    }

    public static String md5Encryption(String str) {
        return null;
    }

    public static void trimDirAsync(File file, int i, Filter filter) {
        if (file.exists() && file.isDirectory()) {
            new n("trimDiskCache", file, filter, i).start();
        }
    }

    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File deleteFile : file.listFiles()) {
                deleteFile(deleteFile);
            }
            return;
        }
        file.delete();
    }

    public static void copyFileAsync(File file, File file2, CallBack callBack) {
        copyFileAsync(file, file2, callBack, false);
    }

    public static void copyFileAsync(File file, File file2, CallBack callBack, boolean z) {
        if (file.exists()) {
            File parentFile = file2.getParentFile();
            if (file2.exists()) {
                file2.delete();
            }
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!z || !file.renameTo(file2)) {
                new q(file, file2, callBack).execute(new Void[0]);
            } else if (callBack != null) {
                callBack.onResult(true);
            }
        } else if (callBack != null) {
            callBack.onResult(false);
        }
    }

    public static File getExternalDCIMDir(Context context) {
        File file;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            if (externalStoragePublicDirectory != null) {
                file = new File(externalStoragePublicDirectory, LogUtils.DEFAULT_TAG);
                if (!file.exists()) {
                    file.mkdir();
                }
                return file;
            }
        }
        file = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static void notifyFileChanged(Context context, File file) {
        try {
            if (VERSION.SDK_INT >= 19) {
                MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, new r());
                return;
            }
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean makeSureCanCreateFile(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        if (parentFile.exists() || !makeSureCanCreateFile(parentFile) || parentFile.mkdir()) {
            return true;
        }
        return false;
    }
}
