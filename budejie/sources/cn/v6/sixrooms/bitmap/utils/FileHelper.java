package cn.v6.sixrooms.bitmap.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.text.TextUtils;
import cn.v6.sixrooms.utils.FileUtil;
import cn.v6.sixrooms.utils.LogUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.facebook.common.util.UriUtil;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileHelper {
    private static final String a = FileHelper.class.getSimpleName();

    public static boolean fileIsExist(String str) {
        if (str == null || str.length() <= 0) {
            LogUtils.e(a, "param invalid, filePath: " + str);
            return false;
        } else if (new File(str).exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static InputStream readFile(String str) {
        if (str == null) {
            LogUtils.e(a, "Invalid param. filePath: " + str);
            return null;
        }
        try {
            if (fileIsExist(str)) {
                return new FileInputStream(new File(str));
            }
            return null;
        } catch (Exception e) {
            LogUtils.e(a, "Exception, ex: " + e.toString());
            return null;
        }
    }

    public static boolean createDirectory(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        return false;
    }

    public static boolean deleteDirectory(String str) {
        int i = 0;
        if (str == null) {
            LogUtils.e(a, "Invalid param. filePath: " + str);
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            while (i < listFiles.length) {
                LogUtils.d(a, "delete filePath: " + listFiles[i].getAbsolutePath());
                if (listFiles[i].isDirectory()) {
                    deleteDirectory(listFiles[i].getAbsolutePath());
                } else {
                    listFiles[i].delete();
                }
                i++;
            }
        }
        LogUtils.d(a, "delete filePath: " + file.getAbsolutePath());
        file.delete();
        return true;
    }

    public static boolean writeFile(String str, InputStream inputStream) {
        if (str == null || str.length() <= 0) {
            LogUtils.e(a, "Invalid param. filePath: " + str);
            return false;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                deleteDirectory(str);
            }
            String substring = str.substring(0, str.lastIndexOf("/"));
            boolean createDirectory = createDirectory(substring);
            if (createDirectory) {
                file.createNewFile();
                if (createDirectory) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    int read = inputStream.read(bArr);
                    while (-1 != read) {
                        fileOutputStream.write(bArr, 0, read);
                        read = inputStream.read(bArr);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
                LogUtils.e(a, "createNewFile fail filePath = " + str);
                return false;
            }
            LogUtils.e(a, "createDirectory fail path = " + substring);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeFile(String str, String str2) {
        return writeFile(str, str2, false);
    }

    public static boolean writeFile(String str, String str2, boolean z) {
        if (str == null || str2 == null || str.length() <= 0 || str2.length() <= 0) {
            LogUtils.e(a, "Invalid param. filePath: " + str + ", fileContent: " + str2);
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            LogUtils.e(a, "writeFile ioe: " + e.toString());
            return false;
        }
    }

    public static long getFileSize(String str) {
        if (str == null) {
            LogUtils.e(a, "Invalid param. filePath: " + str);
            return 0;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }

    public static long getFileModifyTime(String str) {
        if (str == null) {
            LogUtils.e(a, "Invalid param. filePath: " + str);
            return 0;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.lastModified();
        }
        return 0;
    }

    public static boolean setFileModifyTime(String str, long j) {
        if (str == null) {
            LogUtils.e(a, "Invalid param. filePath: " + str);
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.setLastModified(j)) {
            return true;
        }
        return false;
    }

    public static boolean copyFile(ContentResolver contentResolver, String str, String str2) {
        InputStream fileInputStream;
        Exception e;
        InputStream inputStream;
        Throwable th;
        OutputStream outputStream = null;
        if (contentResolver == null || str == null || str.length() <= 0 || str2 == null || str2.length() <= 0) {
            LogUtils.e(a, "copyFile Invalid param. cr=" + contentResolver + ", fromPath=" + str + ", destUri=" + str2);
            return false;
        }
        try {
            fileInputStream = new FileInputStream(str);
            try {
                Uri parse;
                OutputStream fileOutputStream;
                String toLowerCase = str2.toLowerCase();
                if (toLowerCase.startsWith("content://")) {
                    parse = Uri.parse(str2);
                    str2 = null;
                } else if (toLowerCase.startsWith("file://")) {
                    parse = Uri.parse(str2);
                    str2 = parse.getPath();
                } else {
                    parse = null;
                }
                if (str2 != null) {
                    File file = new File(str2);
                    String substring = str2.substring(0, str2.lastIndexOf("/"));
                    File file2 = new File(substring);
                    if (file2.exists() && !file2.isDirectory()) {
                        file2.delete();
                    }
                    file2 = new File(substring + File.separator);
                    if (!(file2.exists() || file2.mkdirs())) {
                        LogUtils.e(a, "Can't make dirs, path=" + substring);
                    }
                    File file3 = new File(str2);
                    if (file3.exists()) {
                        if (file3.isDirectory()) {
                            deleteDirectory(str2);
                        } else {
                            file3.delete();
                        }
                    }
                    fileOutputStream = new FileOutputStream(str2);
                    try {
                        file.setLastModified(System.currentTimeMillis());
                    } catch (Exception e2) {
                        e = e2;
                        outputStream = fileOutputStream;
                        inputStream = fileInputStream;
                        try {
                            LogUtils.e(a, "Exception, ex: " + e.toString());
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (outputStream == null) {
                                return false;
                            }
                            try {
                                outputStream.close();
                                return false;
                            } catch (Exception e4) {
                                return false;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = inputStream;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e5) {
                                }
                            }
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception e6) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = fileOutputStream;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        throw th;
                    }
                }
                fileOutputStream = new AutoCloseOutputStream(contentResolver.openFileDescriptor(parse, IXAdRequestInfo.WIDTH));
                try {
                    byte[] bArr = new byte[1024];
                    for (int read = fileInputStream.read(bArr); -1 != read; read = fileInputStream.read(bArr)) {
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileInputStream.close();
                } catch (Exception e7) {
                    e = e7;
                    outputStream = fileOutputStream;
                    inputStream = fileInputStream;
                    LogUtils.e(a, "Exception, ex: " + e.toString());
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream == null) {
                        return false;
                    }
                    outputStream.close();
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    outputStream = fileOutputStream;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                } catch (Exception e8) {
                    e = e8;
                    OutputStream outputStream2 = fileOutputStream;
                    inputStream = null;
                    outputStream = outputStream2;
                    LogUtils.e(a, "Exception, ex: " + e.toString());
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream == null) {
                        return false;
                    }
                    outputStream.close();
                    return false;
                } catch (Throwable th5) {
                    th = th5;
                    fileInputStream = null;
                    outputStream = fileOutputStream;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e9) {
                e = e9;
                inputStream = fileInputStream;
                LogUtils.e(a, "Exception, ex: " + e.toString());
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream == null) {
                    return false;
                }
                outputStream.close();
                return false;
            } catch (Throwable th6) {
                th = th6;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            inputStream = null;
            LogUtils.e(a, "Exception, ex: " + e.toString());
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream == null) {
                return false;
            }
            outputStream.close();
            return false;
        } catch (Throwable th7) {
            th = th7;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    public static byte[] readAll(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        int read = inputStream.read(bArr);
        while (-1 != read) {
            byteArrayOutputStream.write(bArr, 0, read);
            read = inputStream.read(bArr);
        }
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readFileToBytes(java.lang.String r4) throws java.io.FileNotFoundException {
        /*
        r3 = 0;
        r0 = new java.io.File;	 Catch:{ IOException -> 0x001a, all -> 0x0029 }
        r0.<init>(r4);	 Catch:{ IOException -> 0x001a, all -> 0x0029 }
        r0 = r0.length();	 Catch:{ IOException -> 0x001a, all -> 0x0029 }
        r1 = (int) r0;	 Catch:{ IOException -> 0x001a, all -> 0x0029 }
        r0 = new byte[r1];	 Catch:{ IOException -> 0x001a, all -> 0x0029 }
        r2 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0035, all -> 0x0029 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x0035, all -> 0x0029 }
        r3 = 0;
        r2.read(r0, r3, r1);	 Catch:{ IOException -> 0x0038 }
        r2.close();	 Catch:{ IOException -> 0x0038 }
    L_0x0019:
        return r0;
    L_0x001a:
        r0 = move-exception;
        r1 = r0;
        r2 = r3;
        r0 = r3;
    L_0x001e:
        r1.printStackTrace();	 Catch:{ all -> 0x0032 }
        if (r2 == 0) goto L_0x0019;
    L_0x0023:
        r2.close();	 Catch:{ Exception -> 0x0027 }
        goto L_0x0019;
    L_0x0027:
        r1 = move-exception;
        goto L_0x0019;
    L_0x0029:
        r0 = move-exception;
    L_0x002a:
        if (r3 == 0) goto L_0x002f;
    L_0x002c:
        r3.close();	 Catch:{ Exception -> 0x0030 }
    L_0x002f:
        throw r0;
    L_0x0030:
        r1 = move-exception;
        goto L_0x002f;
    L_0x0032:
        r0 = move-exception;
        r3 = r2;
        goto L_0x002a;
    L_0x0035:
        r1 = move-exception;
        r2 = r3;
        goto L_0x001e;
    L_0x0038:
        r1 = move-exception;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.bitmap.utils.FileHelper.readFileToBytes(java.lang.String):byte[]");
    }

    public static String getFileForNet(String str) {
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        NullPointerException e3;
        String str2 = FileManager.getAudioPlayPath() + System.currentTimeMillis() + ".flv";
        File file = new File(str2);
        File parentFile = file.getParentFile();
        if (!(parentFile == null || parentFile.exists())) {
            parentFile.mkdirs();
        }
        file.delete();
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(getFileByte(str));
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e4) {
                e = e4;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e22 = e6;
                e22.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                return str2;
            } catch (NullPointerException e7) {
                e3 = e7;
                e3.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                return str2;
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return str2;
        } catch (IOException e9) {
            e2222 = e9;
            fileOutputStream = null;
            e2222.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return str2;
        } catch (NullPointerException e10) {
            e3 = e10;
            fileOutputStream = null;
            e3.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return str2;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
        return str2;
    }

    public static byte[] getFileByte(String str) {
        InputStream inputStream;
        Exception exception;
        byte[] bArr;
        Exception exception2;
        Throwable th;
        InputStream inputStream2 = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                byte[] readAll;
                inputStream = httpURLConnection.getInputStream();
                try {
                    readAll = readAll(inputStream);
                } catch (Exception e) {
                    exception = e;
                    bArr = inputStream2;
                    exception2 = exception;
                    try {
                        LogUtils.e("-", "下载文件失败，原因是：" + exception2.toString());
                        exception2.printStackTrace();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                try {
                    inputStream.close();
                    bArr = readAll;
                } catch (Exception e3) {
                    exception = e3;
                    bArr = readAll;
                    exception2 = exception;
                    LogUtils.e("-", "下载文件失败，原因是：" + exception2.toString());
                    exception2.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return bArr;
                }
            }
            LogUtils.e("-", "下载文件失败，状态码是：" + httpURLConnection.getResponseCode());
            bArr = inputStream2;
            inputStream = inputStream2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
            }
        } catch (Exception e32) {
            inputStream = inputStream2;
            InputStream inputStream3 = inputStream2;
            exception2 = e32;
            bArr = inputStream3;
            LogUtils.e("-", "下载文件失败，原因是：" + exception2.toString());
            exception2.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
            return bArr;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            throw th;
        }
        return bArr;
    }

    public static byte[] readFile(Context context, Uri uri) {
        InputStream readFile;
        if (context == null || uri == null) {
            LogUtils.e(a, "Invalid param. ctx: " + context + ", uri: " + uri);
            return null;
        }
        if (uri.getScheme().toLowerCase().equals(UriUtil.LOCAL_FILE_SCHEME)) {
            readFile = readFile(uri.getPath());
        } else {
            readFile = null;
        }
        try {
            readFile = context.getContentResolver().openInputStream(uri);
            if (readFile != null) {
                byte[] readAll = readAll(readFile);
                readFile.close();
                return readAll;
            } else if (readFile == null) {
                return null;
            } else {
                try {
                    readFile.close();
                    return null;
                } catch (Exception e) {
                    return null;
                }
            }
        } catch (FileNotFoundException e2) {
            LogUtils.e(a, "FilNotFoundException, ex: " + e2.toString());
            if (readFile == null) {
                return null;
            }
            try {
                readFile.close();
                return null;
            } catch (Exception e3) {
                return null;
            }
        } catch (Exception e4) {
            LogUtils.e(a, "Exception, ex: " + e4.toString());
            if (readFile == null) {
                return null;
            }
            try {
                readFile.close();
                return null;
            } catch (Exception e5) {
                return null;
            }
        } catch (Throwable th) {
            if (readFile != null) {
                try {
                    readFile.close();
                } catch (Exception e6) {
                }
            }
        }
    }

    public static boolean writeFile(String str, byte[] bArr) {
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        if (str == null || bArr == null) {
            LogUtils.e(a, "Invalid param. filePath: " + str + ", content: " + bArr);
            return false;
        }
        try {
            String substring = str.substring(0, str.lastIndexOf("/"));
            File file = new File(substring);
            if (file.exists() && !file.isDirectory()) {
                file.delete();
            }
            file = new File(str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    deleteDirectory(str);
                } else {
                    file.delete();
                }
            }
            File file2 = new File(substring + File.separator);
            if (!(file2.exists() || file2.mkdirs())) {
                LogUtils.e(a, "Can't make dirs, path=" + substring);
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(str);
            try {
                fileOutputStream2.write(bArr);
                fileOutputStream2.flush();
                fileOutputStream2.close();
                file2.setLastModified(System.currentTimeMillis());
                return true;
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    LogUtils.e(a, "Exception, ex: " + e.toString());
                    if (fileOutputStream != null) {
                        return false;
                    }
                    try {
                        fileOutputStream.close();
                        return false;
                    } catch (Exception e3) {
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            LogUtils.e(a, "Exception, ex: " + e.toString());
            if (fileOutputStream != null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        }
    }

    public static boolean readZipFile(String str, StringBuffer stringBuffer) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    stringBuffer.append(nextEntry.getCrc() + ", size: " + nextEntry.getSize());
                } else {
                    zipInputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            LogUtils.e(a, "Exception: " + e.toString());
            return false;
        }
    }

    public static byte[] readGZipFile(String str) {
        if (fileIsExist(str)) {
            LogUtils.i(a, "zipFileName: " + str);
            try {
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] bArr = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = fileInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (Exception e) {
                LogUtils.i(a, "read zipRecorder file error");
            }
        }
        return null;
    }

    public static boolean zipFile(String str, String str2, String str3) throws IOException {
        boolean z = false;
        if (!(str == null || "".equals(str))) {
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                String absolutePath = file.getAbsolutePath();
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(str3)));
                File file2 = new File(file, str2);
                if (file2.isFile()) {
                    z = a(absolutePath, file2, zipOutputStream);
                } else {
                    z = b(absolutePath, file2, zipOutputStream);
                }
                zipOutputStream.close();
            }
        }
        return z;
    }

    public static boolean unZipFile(String str, String str2) throws Exception {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        ZipFile zipFile = new ZipFile(str);
        Enumeration entries = zipFile.entries();
        byte[] bArr = new byte[51200];
        LogUtils.i(a, "unZipDir: " + str2);
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.isDirectory()) {
                File file2 = new File(str2 + "/" + zipEntry.getName());
                LogUtils.i(a, "entry.isDirectory XXX " + file2.getPath());
                if (!file2.exists()) {
                    file2.mkdirs();
                }
            } else {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                File file3 = new File(str2 + "/" + zipEntry.getName());
                if (file3.exists()) {
                    file3.delete();
                }
                file3.createNewFile();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
                int i = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 51200);
                    if (read == -1) {
                        break;
                    }
                    try {
                        randomAccessFile.seek((long) i);
                    } catch (Exception e) {
                        LogUtils.e(a, "exception, ex: " + e.toString());
                    }
                    randomAccessFile.write(bArr, 0, read);
                    i += read;
                }
                file3.delete();
                randomAccessFile.close();
                bufferedInputStream.close();
            }
        }
        return true;
    }

    private static boolean a(String str, File file, ZipOutputStream zipOutputStream) throws IOException {
        FileInputStream fileInputStream;
        IOException e;
        Throwable th;
        byte[] bArr = new byte[51200];
        try {
            fileInputStream = new FileInputStream(file);
            try {
                zipOutputStream.putNextEntry(new ZipEntry(a(str, file)));
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
                zipOutputStream.closeEntry();
                fileInputStream.close();
                if (zipOutputStream != null) {
                    zipOutputStream.closeEntry();
                }
                fileInputStream.close();
                return true;
            } catch (IOException e2) {
                e = e2;
                try {
                    LogUtils.e(a, "Exception, ex: " + e.toString());
                    if (zipOutputStream != null) {
                        zipOutputStream.closeEntry();
                    }
                    if (fileInputStream != null) {
                        return false;
                    }
                    fileInputStream.close();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (zipOutputStream != null) {
                        zipOutputStream.closeEntry();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
            LogUtils.e(a, "Exception, ex: " + e.toString());
            if (zipOutputStream != null) {
                zipOutputStream.closeEntry();
            }
            if (fileInputStream != null) {
                return false;
            }
            fileInputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (zipOutputStream != null) {
                zipOutputStream.closeEntry();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    private static boolean b(String str, File file, ZipOutputStream zipOutputStream) throws IOException {
        int i = 0;
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles.length == 0) {
            try {
                zipOutputStream.putNextEntry(new ZipEntry(a(str, file)));
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                LogUtils.e(a, "Exception, ex: " + e.toString());
            }
        }
        while (i < listFiles.length) {
            if (listFiles[i].isFile()) {
                a(str, listFiles[i], zipOutputStream);
            } else {
                b(str, listFiles[i], zipOutputStream);
            }
            i++;
        }
        return true;
    }

    private static String a(String str, File file) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        String absolutePath = file.getAbsolutePath();
        if (file.isDirectory()) {
            absolutePath = absolutePath + "/";
        }
        return absolutePath.substring(absolutePath.indexOf(str) + str.length());
    }

    public static byte[] pathToByte(String str, double d, double d2, double d3) throws IllegalArgumentException {
        OutOfMemoryError e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int readPictureDegree = FileUtil.readPictureDegree(str);
        if (readPictureDegree == -1) {
            return null;
        }
        int pow;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        float f = (float) options.outWidth;
        float f2 = (float) options.outHeight;
        if (readPictureDegree == 90 || readPictureDegree == 270) {
            f = (float) options.outHeight;
            f2 = (float) options.outWidth;
        }
        LogUtils.i(a, "原始bitmapWidth=" + f);
        LogUtils.i(a, "原始bitmapHeight=" + f2);
        Bitmap decodeFile2;
        if (f > f2) {
            LogUtils.i(a, "宽大于高");
            if (((double) f) > d || ((double) f2) > d2) {
                if (((double) f) / d > ((double) f2) / d2) {
                    pow = (int) Math.pow(2.0d, Math.ceil(Math.log10(((double) f) / d) / Math.log10(2.0d)));
                } else {
                    pow = (int) Math.pow(2.0d, Math.ceil(Math.log10(((double) f2) / d2) / Math.log10(2.0d)));
                }
                LogUtils.i(a, "rate=" + pow);
                options.inSampleSize = pow;
            }
            options.inJustDecodeBounds = false;
            try {
                decodeFile2 = BitmapFactory.decodeFile(str, options);
                if (decodeFile2 == null) {
                    try {
                        throw new IllegalArgumentException();
                    } catch (OutOfMemoryError e2) {
                        e = e2;
                        if (decodeFile2 != null) {
                            decodeFile2.recycle();
                        }
                        LogUtils.i(a, "图片太大内存溢出");
                        e.printStackTrace();
                        return null;
                    }
                }
                decodeFile2 = FileUtil.rotaingImageView(readPictureDegree, decodeFile2);
                if (decodeFile2 == null) {
                    throw new IllegalArgumentException();
                }
                f2 = (float) decodeFile2.getWidth();
                decodeFile2.getHeight();
                LogUtils.i(a, "bitmapWidth=" + f2);
            } catch (OutOfMemoryError e3) {
                e = e3;
                decodeFile2 = decodeFile;
                if (decodeFile2 != null) {
                    decodeFile2.recycle();
                }
                LogUtils.i(a, "图片太大内存溢出");
                e.printStackTrace();
                return null;
            }
        }
        LogUtils.i(a, "高大于宽");
        if (((double) f2) > d || ((double) f) > d2) {
            if (((double) f2) / d > ((double) f) / d2) {
                pow = (int) Math.pow(2.0d, Math.ceil(Math.log10(((double) f2) / d) / Math.log10(2.0d)));
            } else {
                pow = (int) Math.pow(2.0d, Math.ceil(Math.log10(((double) f) / d2) / Math.log10(2.0d)));
            }
            LogUtils.i(a, "rate=" + pow);
            options.inSampleSize = pow;
        }
        options.inJustDecodeBounds = false;
        try {
            decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile == null) {
                throw new IllegalArgumentException();
            }
            decodeFile = FileUtil.rotaingImageView(readPictureDegree, decodeFile);
            if (decodeFile == null) {
                throw new IllegalArgumentException();
            }
            decodeFile.getWidth();
            f2 = (float) decodeFile.getHeight();
            LogUtils.i(a, "opts.outHeight=" + options.outHeight);
            LogUtils.i(a, "bitmapHeight=" + f2);
            decodeFile2 = decodeFile;
        } catch (OutOfMemoryError e4) {
            if (decodeFile != null) {
                decodeFile.recycle();
            }
            LogUtils.i(a, "图片太大内存溢出");
            e4.printStackTrace();
            return null;
        }
        try {
            decodeFile2.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            LogUtils.i(a, "bos.size()=" + byteArrayOutputStream.size());
            if (((double) byteArrayOutputStream.size()) > d3) {
                pow = (int) ((d3 / ((double) byteArrayOutputStream.size())) * 100.0d);
                byteArrayOutputStream.reset();
                LogUtils.i(a, "bos.size()=" + byteArrayOutputStream.size());
                decodeFile2.compress(CompressFormat.JPEG, pow, byteArrayOutputStream);
                LogUtils.i(a, "压缩");
                LogUtils.i(a, "bos.size()=" + byteArrayOutputStream.size());
            }
            LogUtils.i(a, "bitmap.getHeight()=" + decodeFile2.getHeight());
            LogUtils.i(a, "bitmap.getWidth()=" + decodeFile2.getWidth());
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            if (decodeFile2 != null) {
                decodeFile2.recycle();
            }
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (IOException e5) {
                e5.printStackTrace();
                return null;
            }
        } catch (OutOfMemoryError e6) {
            LogUtils.i(a, "图片太大内存溢出");
            return null;
        }
    }

    public static byte[] bitmapToByte(Bitmap bitmap, double d) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        LogUtils.i(a, "bos.size()=" + byteArrayOutputStream.size());
        if (((double) byteArrayOutputStream.size()) > d) {
            int size = (int) ((d / ((double) byteArrayOutputStream.size())) * 100.0d);
            byteArrayOutputStream.reset();
            LogUtils.i(a, "bos.size()=" + byteArrayOutputStream.size());
            bitmap.compress(CompressFormat.JPEG, size, byteArrayOutputStream);
            LogUtils.i(a, "压缩");
            LogUtils.i(a, "bos.size()=" + byteArrayOutputStream.size());
        }
        LogUtils.i(a, "bitmap.getHeight()=" + bitmap.getHeight());
        LogUtils.i(a, "bitmap.getWidth()=" + bitmap.getWidth());
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        if (bitmap != null) {
            bitmap.recycle();
        }
        try {
            byteArrayOutputStream.close();
            return toByteArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
