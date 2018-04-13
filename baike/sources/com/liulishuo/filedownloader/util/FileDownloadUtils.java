package com.liulishuo.filedownloader.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.liulishuo.filedownloader.BuildConfig;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDownloadUtils {
    private static int a = 65536;
    private static long b = 2000;
    private static String c;
    private static Boolean d;
    private static Boolean e = null;
    private static final Pattern f = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");

    public static void setMinProgressStep(int i) throws IllegalAccessException {
        if (isDownloaderProcess(FileDownloadHelper.getAppContext())) {
            a = i;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-step'.");
    }

    public static void setMinProgressTime(long j) throws IllegalAccessException {
        if (isDownloaderProcess(FileDownloadHelper.getAppContext())) {
            b = j;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-time'.");
    }

    public static int getMinProgressStep() {
        return a;
    }

    public static long getMinProgressTime() {
        return b;
    }

    public static boolean isFilenameValid(String str) {
        return true;
    }

    public static String getDefaultSaveRootPath() {
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        if (FileDownloadHelper.getAppContext().getExternalCacheDir() == null) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath();
        }
        return FileDownloadHelper.getAppContext().getExternalCacheDir().getAbsolutePath();
    }

    public static String getDefaultSaveFilePath(String str) {
        return generateFilePath(getDefaultSaveRootPath(), generateFileName(str));
    }

    public static String generateFileName(String str) {
        return a(str);
    }

    public static String generateFilePath(String str, String str2) {
        if (str2 == null) {
            throw new IllegalStateException("can't generate real path, the file name is null");
        } else if (str == null) {
            throw new IllegalStateException("can't generate real path, the directory is null");
        } else {
            return formatString("%s%s%s", str, File.separator, str2);
        }
    }

    public static void setDefaultSaveRootPath(String str) {
        c = str;
    }

    public static String getTempPath(String str) {
        return formatString("%s.temp", str);
    }

    public static int generateId(String str, String str2) {
        return generateId(str, str2, false);
    }

    public static int generateId(String str, String str2, boolean z) {
        if (z) {
            return a(formatString("%sp%s@dir", str, str2)).hashCode();
        }
        return a(formatString("%sp%s", str, str2)).hashCode();
    }

    private static String a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & 255) < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(b & 255));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (Throwable e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }

    public static String getStack() {
        return getStack(true);
    }

    public static String getStack(boolean z) {
        return getStack(new Throwable().getStackTrace(), z);
    }

    public static String getStack(StackTraceElement[] stackTraceElementArr, boolean z) {
        if (stackTraceElementArr == null || stackTraceElementArr.length < 4) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            if (stackTraceElementArr[i].getClassName().contains(BuildConfig.APPLICATION_ID)) {
                stringBuilder.append("[");
                stringBuilder.append(stackTraceElementArr[i].getClassName().substring(BuildConfig.APPLICATION_ID.length()));
                stringBuilder.append(Config.TRACE_TODAY_VISIT_SPLIT);
                stringBuilder.append(stackTraceElementArr[i].getMethodName());
                if (z) {
                    stringBuilder.append("(").append(stackTraceElementArr[i].getLineNumber()).append(")]");
                } else {
                    stringBuilder.append("]");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isDownloaderProcess(Context context) {
        if (d != null) {
            return d.booleanValue();
        }
        boolean z;
        if (FileDownloadProperties.getImpl().PROCESS_NON_SEPARATE) {
            z = true;
        } else {
            int myPid = Process.myPid();
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
                FileDownloadLog.w(FileDownloadUtils.class, "The running app process info list from ActivityManager is null or empty, maybe current App is not running.", new Object[0]);
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    z = runningAppProcessInfo.processName.endsWith(":filedownloader");
                    break;
                }
            }
            z = false;
        }
        d = Boolean.valueOf(z);
        return d.booleanValue();
    }

    public static String[] convertHeaderString(String str) {
        String[] split = str.split("\n");
        String[] strArr = new String[(split.length * 2)];
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split(": ");
            strArr[i * 2] = split2[0];
            strArr[(i * 2) + 1] = split2[1];
        }
        return strArr;
    }

    public static long getFreeSpaceBytes(String str) {
        StatFs statFs = new StatFs(str);
        if (VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBytes();
        }
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }

    public static String formatString(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    public static void markConverted(Context context) {
        File convertedMarkedFile = getConvertedMarkedFile(context);
        try {
            convertedMarkedFile.getParentFile().mkdirs();
            convertedMarkedFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFilenameConverted(Context context) {
        if (e == null) {
            e = Boolean.valueOf(getConvertedMarkedFile(context).exists());
        }
        return e.booleanValue();
    }

    public static File getConvertedMarkedFile(Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + File.separator + a.TABLE_NAME, ".old_file_converted");
    }

    public static String parseContentDisposition(String str) {
        String str2 = null;
        if (str != null) {
            try {
                Matcher matcher = f.matcher(str);
                if (matcher.find()) {
                    str2 = matcher.group(1);
                }
            } catch (IllegalStateException e) {
            }
        }
        return str2;
    }

    public static String getTargetFilePath(String str, boolean z, String str2) {
        if (str == null) {
            return null;
        }
        if (!z) {
            return str;
        }
        if (str2 == null) {
            return null;
        }
        return generateFilePath(str, str2);
    }

    public static String getParent(String str) {
        int i = 2;
        int length = str.length();
        int i2;
        if (File.separatorChar == TokenParser.ESCAPE && length > 2 && str.charAt(1) == ':') {
            i2 = 2;
        } else {
            i2 = 0;
        }
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        if (lastIndexOf != -1 || r0 <= 0) {
            i = lastIndexOf;
        }
        if (i == -1 || str.charAt(length - 1) == File.separatorChar) {
            return null;
        }
        if (str.indexOf(File.separatorChar) == i && str.charAt(r0) == File.separatorChar) {
            return str.substring(0, i + 1);
        }
        return str.substring(0, i);
    }

    public static String getThreadPoolName(String str) {
        return "FileDownloader-" + str;
    }

    public static boolean isNetworkOnWifiType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) FileDownloadHelper.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static boolean checkPermission(String str) {
        return FileDownloadHelper.getAppContext().checkCallingOrSelfPermission(str) == 0;
    }

    public static long convertContentLengthString(String str) {
        long j = -1;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }
}
