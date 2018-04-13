package qsbk.app.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.widget.RefreshTipView;

public class FileUtils {
    public static void cacheCotent(String str, String str2, String str3) {
        new j("qbk-FileUtl1", str, str3, str2).start();
    }

    public static String saveDrawable(Bitmap bitmap, String str, String str2, Handler handler, boolean z) {
        String str3 = "";
        Message message = new Message();
        File file = new File(str2);
        File file2 = new File(str2, str);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
        OutputStream fileOutputStream;
        if (file2.exists()) {
            String path;
            if (z) {
                file2.delete();
                try {
                    file2.createNewFile();
                    fileOutputStream = new FileOutputStream(file2);
                    if (bitmap != null && bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                    if (handler != null) {
                        message.what = 1;
                        message.obj = file2.getPath();
                        handler.sendMessage(message);
                    }
                    path = file2.getPath();
                } catch (IOException e) {
                    if (handler != null) {
                        message.what = 0;
                        message.obj = "图片保存失败！";
                        handler.sendMessage(message);
                    }
                    e.printStackTrace();
                }
                if (handler != null) {
                    return path;
                }
                message.what = 1;
                message.obj = file2.getPath();
                handler.sendMessage(message);
                return path;
            }
            path = str3;
            if (handler != null) {
                return path;
            }
            message.what = 1;
            message.obj = file2.getPath();
            handler.sendMessage(message);
            return path;
        }
        try {
            file2.createNewFile();
            fileOutputStream = new FileOutputStream(file2);
            if (bitmap != null && bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream)) {
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            if (handler != null) {
                message.what = 1;
                message.obj = file2.getPath();
                handler.sendMessage(message);
            }
            return file2.getPath();
        } catch (IOException e2) {
            Log.e("FileUtils", e2.toString() + "--头像保存时出现异常！");
            return str3;
        }
    }

    public static String saveDrawable(Bitmap bitmap, String str, String str2) {
        File file = new File(DeviceUtils.getSDPath() + File.separator + str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (bitmap == null || file.getPath().equals("")) {
            return null;
        }
        File file2 = new File(file.getPath() + File.separator + str);
        try {
            File file3 = new File(file2.getAbsolutePath() + System.currentTimeMillis());
            file2.renameTo(file3);
            file3.delete();
        } catch (Exception e) {
            Logger.getInstance().debug("FileUtils", "saveDrawable", e + "");
        }
        String path = file2.getPath();
        try {
            OutputStream fileOutputStream = new FileOutputStream(file2, false);
            if (bitmap.compress(CompressFormat.JPEG, 85, fileOutputStream)) {
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            return path;
        } catch (IOException e2) {
            Logger.getInstance().debug("FileUtils", "saveDrawable", e2 + "");
            return null;
        }
    }

    public static void saveContent(String str, String str2) {
        if (str2 != null && !str2.equals("") && !str.equals("")) {
            File file = new File(str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() > RefreshTipView.SECOND_REFRESH_INTERVAL) {
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
        return getContent(new File(str));
    }

    public static String getContent(File file) throws IOException {
        String str = "";
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                bArr = byteArrayOutputStream.toByteArray();
                bufferedInputStream.close();
                byteArrayOutputStream.close();
                return new String(bArr);
            }
        }
    }

    public static long getFileSize(File file, List<File> list) {
        long j = 0;
        File[] listFiles = file.listFiles();
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
            if (getFileSize(file, null) <= j) {
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
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            try {
                Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
                int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                if (query.moveToFirst()) {
                    return query.getString(columnIndexOrThrow);
                }
            } catch (Exception e) {
            }
        } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean copyDir(File file, File file2) {
        int i = 0;
        if (!file.exists() || !file.isDirectory() || !file.canRead()) {
            return false;
        }
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File[] listFiles = file.listFiles();
        while (i < listFiles.length) {
            File file3 = listFiles[i];
            File file4 = new File(file2.getPath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + file3.getName());
            if (file3.isDirectory()) {
                copyDir(file3, file4);
            } else {
                copyfile(file3, file4, true);
            }
            i++;
        }
        return true;
    }

    public static boolean copyfile(File file, File file2, boolean z) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        FileInputStream fileInputStream = null;
        boolean z2 = false;
        if (file.exists() && file.isFile() && file.canRead() && file.length() != 0) {
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (file2.exists() && z) {
                file2.delete();
            }
            FileInputStream fileInputStream2;
            try {
                fileInputStream2 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = fileInputStream2.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        z2 = true;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        fileInputStream = fileInputStream2;
                        try {
                            Log.e("readfile", e.getMessage());
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e2222) {
                                    e2222.printStackTrace();
                                }
                            }
                            return z2;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream2 = fileInputStream;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e22222) {
                                    e22222.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e222222) {
                                    e222222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    Log.e("readfile", e.getMessage());
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return z2;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                Log.e("readfile", e.getMessage());
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return z2;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                fileInputStream2 = null;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return z2;
    }

    public static boolean mergeFile(File file, int i, File file2, boolean z) {
        FileChannel fileChannel;
        Error error;
        Exception exception;
        Throwable th;
        if (!file.exists() || !file.isDirectory() || !file.canRead() || file.length() == 0) {
            return false;
        }
        try {
            if (file2.exists() && z) {
                file2.delete();
            }
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean z2 = false;
        FileChannel fileChannel2 = null;
        FileChannel fileChannel3 = null;
        try {
            fileChannel3 = new FileOutputStream(file2).getChannel();
            byte[] bArr = new byte[1024];
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length <= i) {
                    i = listFiles.length;
                }
                int i2 = 0;
                fileChannel = null;
                while (i2 < i) {
                    try {
                        File file3 = listFiles[i2];
                        if (file3 != null && file3.exists() && file3.canRead() && file3.length() > 0) {
                            fileChannel = new FileInputStream(file3).getChannel();
                            fileChannel.transferTo(0, fileChannel.size(), fileChannel3);
                            try {
                                fileChannel.close();
                                fileChannel = null;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        i2++;
                    } catch (Error e3) {
                        Error error2 = e3;
                        fileChannel2 = fileChannel;
                        error = error2;
                    } catch (Exception e4) {
                        Exception exception2 = e4;
                        fileChannel2 = fileChannel;
                        exception = exception2;
                    } catch (Throwable th2) {
                        th = th2;
                        fileChannel2 = fileChannel;
                    }
                }
                z2 = true;
            } else {
                fileChannel = null;
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            if (fileChannel3 == null) {
                return z2;
            }
            try {
                fileChannel3.close();
                return z2;
            } catch (IOException e52) {
                e52.printStackTrace();
                return z2;
            }
        } catch (Error e6) {
            error = e6;
            try {
                Log.e("readfile", error.getMessage());
                if (file2.exists() && z) {
                    file2.delete();
                }
                if (fileChannel2 != null) {
                    try {
                        fileChannel2.close();
                    } catch (IOException e522) {
                        e522.printStackTrace();
                    }
                }
                if (fileChannel3 == null) {
                    return false;
                }
                try {
                    fileChannel3.close();
                    return false;
                } catch (IOException e5222) {
                    e5222.printStackTrace();
                    return false;
                }
            } catch (Throwable th3) {
                th = th3;
                if (fileChannel2 != null) {
                    try {
                        fileChannel2.close();
                    } catch (IOException e52222) {
                        e52222.printStackTrace();
                    }
                }
                if (fileChannel3 != null) {
                    try {
                        fileChannel3.close();
                    } catch (IOException e522222) {
                        e522222.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e7) {
            exception = e7;
            Log.e("readfile", exception.getMessage());
            if (file2.exists() && z) {
                file2.delete();
            }
            if (fileChannel2 != null) {
                try {
                    fileChannel2.close();
                } catch (IOException e5222222) {
                    e5222222.printStackTrace();
                }
            }
            if (fileChannel3 == null) {
                return false;
            }
            try {
                fileChannel3.close();
                return false;
            } catch (IOException e52222222) {
                e52222222.printStackTrace();
                return false;
            }
        }
    }

    public static String getCacheDataPath() {
        return AppUtils.getInstance().getAppContext().getCacheDir().getAbsolutePath();
    }

    public static boolean deletCacheDataFile(String str) {
        return new File(getCacheDataPath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str).delete();
    }

    public static synchronized void deleteDir(String str) {
        synchronized (FileUtils.class) {
            deleteDir(str, true);
        }
    }

    public static synchronized void deleteDir(String str, boolean z) {
        synchronized (FileUtils.class) {
            if (!TextUtils.isEmpty(str) && SdcardUtils.isSDCardMounted()) {
                a(str, z);
            }
        }
    }

    private static void a(String str, boolean z) {
        File file = new File(str);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2 != null && file2.exists()) {
                        if (file2.isDirectory()) {
                            a(file2.getAbsolutePath(), true);
                        } else if (file2.isFile()) {
                            file2.delete();
                        }
                    }
                }
            }
            if (z) {
                file.delete();
            }
        }
    }

    public static void clearOldCache(String str, int i) {
        File file = new File(str);
        if (file.isDirectory()) {
            List asList = Arrays.asList(file.listFiles());
            int size = asList.size();
            if (size > i) {
                Collections.sort(asList, new k());
                for (int i2 = 0; i2 < size - i; i2++) {
                    file = (File) asList.get(i2);
                    if (!file.isDirectory() && file.exists()) {
                        file.delete();
                    }
                }
            }
        }
    }

    public static boolean isFileExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    public static boolean deleteFileIfExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static long getPathLength(String str) {
        long j = 0;
        TimeDelta timeDelta = new TimeDelta();
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.isFile()) {
                if (file.exists()) {
                    j = file.length();
                } else {
                    j = a(file.getParentFile());
                }
            } else if (str.contains("%d.ts&0-")) {
                j = a(file.getParentFile());
            } else {
                j = a(file);
            }
        }
        LogUtils.d("read file length cost: " + timeDelta.getDelta());
        return j;
    }

    private static long a(File file) {
        long j = 0;
        if (file != null && file.isDirectory() && file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File length : listFiles) {
                    j += length.length();
                }
            }
        }
        return j;
    }

    public static String getStringFromAssetsFile(String str) {
        InputStreamReader inputStreamReader;
        IOException e;
        Throwable th;
        String str2 = null;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            inputStreamReader = new InputStreamReader(AppUtils.getInstance().getAppContext().getAssets().open(str));
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                str2 = stringBuffer.toString();
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e222 = e4;
            Object obj = str2;
            e222.printStackTrace();
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            return str2;
        } catch (Throwable th3) {
            inputStreamReader = str2;
            th = th3;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        return str2;
    }

    public static String readStream(InputStream inputStream, String str) throws Exception {
        String str2 = "";
        if (inputStream != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                str2 = stringBuffer.toString();
                if (str2.startsWith("<html>")) {
                    str2 = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    @SuppressLint({"NewApi"})
    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    public static boolean hasExternalCacheDir() {
        return VERSION.SDK_INT >= 8;
    }

    public static void deleteFiles(List<File> list) {
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    public static byte[] fileToBytes(String str) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        byte[] bArr = null;
        try {
            fileInputStream = new FileInputStream(new File(str));
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                        } catch (Throwable th2) {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e5) {
                                }
                            }
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                } catch (FileNotFoundException e7) {
                    e2 = e7;
                    try {
                        e2.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e8) {
                                    }
                                }
                            } catch (Throwable th3) {
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e9) {
                                    }
                                }
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e10) {
                            }
                        }
                        return bArr;
                    } catch (Throwable th4) {
                        th = th4;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e11) {
                                    }
                                }
                            } catch (Throwable th5) {
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e12) {
                                    }
                                }
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e13) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e14) {
                    e322 = e14;
                    e322.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e15) {
                                }
                            }
                        } catch (Throwable th6) {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e16) {
                                }
                            }
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e17) {
                        }
                    }
                    return bArr;
                }
            } catch (FileNotFoundException e18) {
                e2 = e18;
                byteArrayOutputStream = bArr;
                e2.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            } catch (IOException e19) {
                e3222 = e19;
                byteArrayOutputStream = bArr;
                e3222.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            } catch (Throwable th7) {
                byteArrayOutputStream = bArr;
                th = th7;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e20) {
            e2 = e20;
            byteArrayOutputStream = bArr;
            fileInputStream = bArr;
            e2.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return bArr;
        } catch (IOException e21) {
            e3222 = e21;
            byteArrayOutputStream = bArr;
            fileInputStream = bArr;
            e3222.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return bArr;
        } catch (Throwable th72) {
            byteArrayOutputStream = bArr;
            fileInputStream = bArr;
            th = th72;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return bArr;
    }
}
