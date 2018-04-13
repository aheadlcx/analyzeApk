package com.tencent.tinker.loader.shareutil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SharePatchFileUtil {
    private static final String TAG = "Tinker.PatchFileUtil";
    private static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static File getPatchDirectory(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        return new File(applicationInfo.dataDir, ShareConstants.PATCH_DIRECTORY_NAME);
    }

    public static File getPatchTempDirectory(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        return new File(applicationInfo.dataDir, ShareConstants.PATCH_TEMP_DIRECTORY_NAME);
    }

    public static File getPatchLastCrashFile(Context context) {
        File patchTempDirectory = getPatchTempDirectory(context);
        if (patchTempDirectory == null) {
            return null;
        }
        return new File(patchTempDirectory, ShareConstants.PATCH_TEMP_LAST_CRASH_NAME);
    }

    public static File getPatchInfoFile(String str) {
        return new File(str + "/" + ShareConstants.PATCH_INFO_NAME);
    }

    public static File getPatchInfoLockFile(String str) {
        return new File(str + "/" + ShareConstants.PATCH_INFO_LOCK_NAME);
    }

    public static String getPatchVersionDirectory(String str) {
        if (str == null || str.length() != 32) {
            return null;
        }
        return ShareConstants.PATCH_BASE_NAME + str.substring(0, 8);
    }

    public static String getPatchVersionFile(String str) {
        if (str == null || str.length() != 32) {
            return null;
        }
        return getPatchVersionDirectory(str) + ShareConstants.PATCH_SUFFIX;
    }

    public static boolean checkIfMd5Valid(String str) {
        if (str == null || str.length() != 32) {
            return false;
        }
        return true;
    }

    public static String checkTinkerLastUncaughtCrash(Context context) {
        Closeable bufferedReader;
        Object e;
        Throwable th;
        File patchLastCrashFile = getPatchLastCrashFile(context);
        if (!isLegalFile(patchLastCrashFile)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(patchLastCrashFile)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuffer.append(readLine);
                        stringBuffer.append("\n");
                    } else {
                        closeQuietly(bufferedReader);
                        return stringBuffer.toString();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception e3) {
            e = e3;
            bufferedReader = null;
            try {
                Log.e(TAG, "checkTinkerLastUncaughtCrash exception: " + e);
                closeQuietly(bufferedReader);
                return null;
            } catch (Throwable th2) {
                th = th2;
                closeQuietly(bufferedReader);
                throw th;
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            closeQuietly(bufferedReader);
            throw th;
        }
    }

    public static final boolean isLegalFile(File file) {
        return file != null && file.exists() && file.canRead() && file.isFile() && file.length() > 0;
    }

    public static long getFileOrDirectorySize(File file) {
        long j = 0;
        if (file == null || !file.exists()) {
            return 0;
        }
        if (file.isFile()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j += getFileOrDirectorySize(file2);
            } else {
                j += file2.length();
            }
        }
        return j;
    }

    public static final boolean safeDeleteFile(File file) {
        boolean z = true;
        if (file != null && file.exists()) {
            Log.i(TAG, "safeDeleteFile, try to delete path: " + file.getPath());
            z = file.delete();
            if (!z) {
                Log.e(TAG, "Failed to delete file, try to delete when exit. path: " + file.getPath());
                file.deleteOnExit();
            }
        }
        return z;
    }

    public static final boolean deleteDir(String str) {
        if (str == null) {
            return false;
        }
        return deleteDir(new File(str));
    }

    public static final boolean deleteDir(File file) {
        int i = 0;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            safeDeleteFile(file);
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int length = listFiles.length;
                while (i < length) {
                    deleteDir(listFiles[i]);
                    i++;
                }
                safeDeleteFile(file);
            }
        }
        return true;
    }

    public static boolean verifyFileMd5(File file, String str) {
        if (str == null) {
            return false;
        }
        String md5 = getMD5(file);
        if (md5 != null) {
            return str.equals(md5);
        }
        return false;
    }

    public static boolean isRawDexFile(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(ShareConstants.DEX_SUFFIX);
    }

    public static boolean verifyDexFileMd5(File file, String str) {
        return verifyDexFileMd5(file, "classes.dex", str);
    }

    public static boolean verifyDexFileMd5(File file, String str, String str2) {
        Throwable th;
        Throwable th2;
        boolean z = false;
        if (file == null || str2 == null || str == null) {
            return z;
        }
        Object md5;
        if (isRawDexFile(file.getName())) {
            md5 = getMD5(file);
        } else {
            ZipFile zipFile;
            try {
                zipFile = new ZipFile(file);
                try {
                    ZipEntry entry = zipFile.getEntry(str);
                    if (entry == null) {
                        Log.e(TAG, "There's no entry named: classes.dex in " + file.getAbsolutePath());
                        if (zipFile == null) {
                            return z;
                        }
                        try {
                            zipFile.close();
                            return z;
                        } catch (Throwable th3) {
                            return z;
                        }
                    }
                    md5 = getMD5(zipFile.getInputStream(entry));
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (Throwable th4) {
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    try {
                        Log.e(TAG, "Bad dex jar file: " + file.getAbsolutePath(), th);
                        if (zipFile != null) {
                            return z;
                        }
                        try {
                            zipFile.close();
                            return z;
                        } catch (Throwable th6) {
                            return z;
                        }
                    } catch (Throwable th7) {
                        th2 = th7;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (Throwable th8) {
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th9) {
                th2 = th9;
                zipFile = null;
                if (zipFile != null) {
                    zipFile.close();
                }
                throw th2;
            }
        }
        return str2.equals(md5);
    }

    public static void copyFileUsingStream(File file, File file2) throws IOException {
        Closeable fileOutputStream;
        Throwable th;
        Closeable closeable = null;
        if (isLegalFile(file) && file2 != null && !file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            File parentFile = file2.getParentFile();
            if (!(parentFile == null || parentFile.exists())) {
                parentFile.mkdirs();
            }
            try {
                Closeable fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2, false);
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                    closeable = fileInputStream;
                    closeQuietly(closeable);
                    closeQuietly(fileOutputStream);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            closeQuietly(fileInputStream);
                            closeQuietly(fileOutputStream);
                            return;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    closeable = fileInputStream;
                    closeQuietly(closeable);
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                closeQuietly(closeable);
                closeQuietly(fileOutputStream);
                throw th;
            }
        }
    }

    public static String loadDigestes(JarFile jarFile, JarEntry jarEntry) throws Exception {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        Closeable bufferedInputStream;
        try {
            byte[] bArr = new byte[16384];
            bufferedInputStream = new BufferedInputStream(jarFile.getInputStream(jarEntry));
            while (true) {
                try {
                    int read = bufferedInputStream.read(bArr);
                    if (read > 0) {
                        stringBuilder.append(new String(bArr, 0, read));
                    } else {
                        closeQuietly(bufferedInputStream);
                        return stringBuilder.toString();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            closeQuietly(bufferedInputStream);
            throw th;
        }
    }

    public static final String getMD5(InputStream inputStream) {
        String str = null;
        if (inputStream != null) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                MessageDigest instance = MessageDigest.getInstance("MD5");
                StringBuilder stringBuilder = new StringBuilder(32);
                byte[] bArr = new byte[ShareConstants.MD5_FILE_BUF_LENGTH];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                byte[] digest = instance.digest();
                for (byte b : digest) {
                    stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
                }
                str = stringBuilder.toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static String getMD5(byte[] bArr) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr = new char[(length * 2)];
            int i2 = 0;
            while (i < length) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr[i2] = hexDigits[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr[i3] = hexDigits[b & 15];
                i++;
            }
            return new String(cArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMD5(File file) {
        Closeable fileInputStream;
        Throwable th;
        String str = null;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    str = getMD5((InputStream) fileInputStream);
                    closeQuietly(fileInputStream);
                } catch (Exception e) {
                    closeQuietly(fileInputStream);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                fileInputStream = str;
                closeQuietly(fileInputStream);
                return str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = str;
                th = th4;
                closeQuietly(fileInputStream);
                throw th;
            }
        }
        return str;
    }

    public static String optimizedPathFor(File file, File file2) {
        String name;
        if (ShareTinkerInternals.isAfterAndroidO()) {
            try {
                String currentInstructionSet = ShareTinkerInternals.getCurrentInstructionSet();
                File parentFile = file.getParentFile();
                name = file.getName();
                int lastIndexOf = name.lastIndexOf(46);
                if (lastIndexOf > 0) {
                    name = name.substring(0, lastIndexOf);
                }
                return parentFile.getAbsolutePath() + "/oat/" + currentInstructionSet + "/" + name + ShareConstants.ODEX_SUFFIX;
            } catch (Throwable e) {
                throw new TinkerRuntimeException("getCurrentInstructionSet fail:", e);
            }
        }
        name = file.getName();
        if (!name.endsWith(ShareConstants.DEX_SUFFIX)) {
            int lastIndexOf2 = name.lastIndexOf(".");
            if (lastIndexOf2 < 0) {
                name = name + ShareConstants.DEX_SUFFIX;
            } else {
                StringBuilder stringBuilder = new StringBuilder(lastIndexOf2 + 4);
                stringBuilder.append(name, 0, lastIndexOf2);
                stringBuilder.append(ShareConstants.DEX_SUFFIX);
                name = stringBuilder.toString();
            }
        }
        return new File(file2, name).getPath();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                Log.w(TAG, "Failed to close resource", e);
            }
        }
    }

    public static void closeZip(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (Throwable e) {
                Log.w(TAG, "Failed to close resource", e);
            }
        }
    }

    public static boolean checkResourceArscMd5(File file, String str) {
        Throwable th;
        Throwable th2;
        ZipFile zipFile = null;
        try {
            ZipFile zipFile2 = new ZipFile(file);
            try {
                ZipEntry entry = zipFile2.getEntry(ShareConstants.RES_ARSC);
                if (entry == null) {
                    Log.i(TAG, "checkResourceArscMd5 resources.arsc not found");
                    closeZip(zipFile2);
                    return false;
                }
                InputStream inputStream = zipFile2.getInputStream(entry);
                String md5 = getMD5(inputStream);
                if (md5 == null || !md5.equals(str)) {
                    closeQuietly(inputStream);
                    closeZip(zipFile2);
                    return false;
                }
                closeQuietly(inputStream);
                closeZip(zipFile2);
                return true;
            } catch (Throwable th3) {
                th2 = th3;
                zipFile = zipFile2;
                closeZip(zipFile);
                throw th2;
            }
        } catch (Throwable th4) {
            th = th4;
            Log.i(TAG, "checkResourceArscMd5 throwable:" + th.getMessage());
            closeZip(zipFile);
            return false;
        }
    }

    public static void ensureFileDirectory(File file) {
        if (file != null) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
    }
}
