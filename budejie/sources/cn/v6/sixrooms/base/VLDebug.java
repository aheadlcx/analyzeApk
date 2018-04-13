package cn.v6.sixrooms.base;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import com.alipay.sdk.util.h;
import com.facebook.common.time.Clock;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class VLDebug {
    private static VLLogLevel a = VLLogLevel.None;
    private static String[] b = new String[]{"N", "E", "W", "I", "D", "V"};
    private static String c = "VLDebug";
    private static long d = Clock.MAX_TIME;
    private static long e = Clock.MAX_TIME;
    private static SimpleDateFormat f = new SimpleDateFormat("MMdd_kkmmss.SSS", Locale.getDefault());
    private static FileWriter g = null;
    private static File h = null;

    public enum VLLogLevel {
        None,
        Error,
        Warning,
        Info,
        Debug,
        Verbose
    }

    public static final synchronized void configDebug(Context context, VLLogLevel vLLogLevel, long j, long j2) {
        synchronized (VLDebug.class) {
            c = context.getPackageName();
            a = vLLogLevel;
            if (j <= 0) {
                j = Clock.MAX_TIME;
            }
            d = j;
            if (j2 <= 0) {
                j2 = Clock.MAX_TIME;
            }
            e = j2;
            if (Environment.getExternalStorageState().equalsIgnoreCase("mounted")) {
                File externalFilesDir = context.getExternalFilesDir(null);
                if (!(externalFilesDir == null || externalFilesDir.isFile())) {
                    externalFilesDir = externalFilesDir.getParentFile();
                    if (!(externalFilesDir == null || externalFilesDir.isFile())) {
                        File file = new File(externalFilesDir.getAbsolutePath() + File.separator + "logs");
                        if (!file.isFile() && (file.exists() || file.mkdirs())) {
                            h = file;
                            for (File file2 : file.listFiles()) {
                                if (System.currentTimeMillis() - file2.lastModified() > e) {
                                    file2.delete();
                                }
                            }
                            String packageName = context.getPackageName();
                            if (packageName.lastIndexOf(46) >= 0) {
                                packageName = packageName.substring(packageName.lastIndexOf(46) + 1);
                            }
                            String str = file.getAbsolutePath() + File.separator + packageName + "_log.txt";
                            try {
                                externalFilesDir = new File(str);
                                if (!externalFilesDir.exists()) {
                                    externalFilesDir.createNewFile();
                                } else if (externalFilesDir.length() > d) {
                                    externalFilesDir.renameTo(new File(str + "." + f.format(new Date()) + ".bak"));
                                    externalFilesDir = new File(str);
                                    if (!externalFilesDir.exists()) {
                                        externalFilesDir.createNewFile();
                                    }
                                }
                                g = new FileWriter(externalFilesDir, true);
                            } catch (Exception e) {
                                g = null;
                            }
                        }
                    }
                }
            }
        }
    }

    public static final String toDesc() {
        return "{logLevel=" + a + ",logRotateBytes=" + d + ",logPreserveHours=" + (((e / 1000) / 60) / 60) + h.d;
    }

    public static final void logE(String str, Object... objArr) {
        if (VLLogLevel.Error.ordinal() <= a.ordinal()) {
            a(true, VLLogLevel.Error, str, objArr);
        }
    }

    public static final void logW(String str, Object... objArr) {
        if (VLLogLevel.Warning.ordinal() <= a.ordinal()) {
            a(true, VLLogLevel.Warning, str, objArr);
        }
    }

    public static final void logI(String str, Object... objArr) {
        if (VLLogLevel.Info.ordinal() <= a.ordinal()) {
            a(true, VLLogLevel.Info, str, objArr);
        }
    }

    public static final void logD(String str, Object... objArr) {
        if (VLLogLevel.Debug.ordinal() <= a.ordinal()) {
            a(true, VLLogLevel.Debug, str, objArr);
        }
    }

    public static final void logV(String str, Object... objArr) {
        if (VLLogLevel.Verbose.ordinal() <= a.ordinal()) {
            a(true, VLLogLevel.Verbose, str, objArr);
        }
    }

    public static final void screenE(String str, Object... objArr) {
        if (VLLogLevel.Error.ordinal() <= a.ordinal()) {
            a(false, VLLogLevel.Error, str, objArr);
        }
    }

    public static final void screenW(String str, Object... objArr) {
        if (VLLogLevel.Warning.ordinal() <= a.ordinal()) {
            a(false, VLLogLevel.Warning, str, objArr);
        }
    }

    public static final void screenI(String str, Object... objArr) {
        if (VLLogLevel.Info.ordinal() <= a.ordinal()) {
            a(false, VLLogLevel.Info, str, objArr);
        }
    }

    public static final void screenD(String str, Object... objArr) {
        if (VLLogLevel.Debug.ordinal() <= a.ordinal()) {
            a(false, VLLogLevel.Debug, str, objArr);
        }
    }

    public static final void screenV(String str, Object... objArr) {
        if (VLLogLevel.Verbose.ordinal() <= a.ordinal()) {
            a(false, VLLogLevel.Verbose, str, objArr);
        }
    }

    public static final void traceE() {
        logE("traceE", new Object[0]);
    }

    public static final void traceW() {
        logW("traceW", new Object[0]);
    }

    public static final void traceI() {
        logI("traceI", new Object[0]);
    }

    public static final void traceD() {
        logD("traceD", new Object[0]);
    }

    public static final void traceV() {
        logD("traceV", new Object[0]);
    }

    private static final void a(boolean z, VLLogLevel vLLogLevel, String str, Object... objArr) {
        String substring;
        String fileName = Thread.currentThread().getStackTrace()[4].getFileName();
        if (fileName.length() > 5) {
            substring = fileName.substring(0, fileName.length() - 5);
        } else {
            substring = fileName;
        }
        try {
            fileName = substring + ": " + String.format(str, objArr);
        } catch (Exception e) {
            fileName = substring + ": " + e.getMessage();
        }
        a(vLLogLevel, c, fileName);
        if (z && g != null) {
            a(vLLogLevel, fileName);
        }
    }

    private static final void a(VLLogLevel vLLogLevel, String str, String str2) {
        switch (e.a[vLLogLevel.ordinal()]) {
            case 2:
                Log.e(str, str2);
                return;
            case 3:
                Log.w(str, str2);
                return;
            case 4:
                Log.i(str, str2);
                return;
            case 5:
                Log.d(str, str2);
                return;
            case 6:
                Log.v(str, str2);
                return;
            default:
                return;
        }
    }

    private static final void a(VLLogLevel vLLogLevel, String str) {
        String format = f.format(new Date());
        try {
            g.write(format + " " + b[vLLogLevel.ordinal()] + "/" + str + "[" + dumpStackTrace(getStackTraceLevel(2)) + "]\r\n");
            g.flush();
        } catch (IOException e) {
            g = null;
        }
    }

    public static final void logEx(Thread thread, Throwable th) {
        if (VLLogLevel.Error.ordinal() <= a.ordinal()) {
            String dumpExceptionStackTrace = dumpExceptionStackTrace(thread, th);
            a(VLLogLevel.Error, c, dumpExceptionStackTrace);
            a(VLLogLevel.Error, dumpExceptionStackTrace);
            if (SixRoomsUtils.externalStorageExist()) {
                String format = f.format(new Date());
                if (h != null) {
                    format = h.getAbsolutePath() + File.separator + SixRoomsUtils.appName(V6Coop.getInstance().getContext()) + "_" + format + ".txt";
                    File file = new File(format);
                    try {
                        file.createNewFile();
                        FileWriter fileWriter = new FileWriter(file, true);
                        fileWriter.write(dumpExceptionStackTrace);
                        fileWriter.flush();
                        fileWriter.close();
                        long currentTimeMillis = System.currentTimeMillis();
                        for (File file2 : h.listFiles()) {
                            if (file2.getName().endsWith(".txt") && currentTimeMillis - file2.lastModified() > e) {
                                file2.delete();
                            }
                        }
                    } catch (Throwable e) {
                        Log.e(c, format, e);
                    }
                }
            }
        }
    }

    public static final StackTraceElement getCurrentStackTrace() {
        return Thread.currentThread().getStackTrace()[3];
    }

    public static final StackTraceElement getParentStackTrace() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static final StackTraceElement getStackTraceLevel(int i) {
        return Thread.currentThread().getStackTrace()[i + 4];
    }

    public static final String dumpStackTrace(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        int lastIndexOf = className.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            className = className.substring(lastIndexOf + 1);
        }
        return className + "::" + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public static final String dumpExceptionStackTrace(Thread thread, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder("Unhandled Exception In Thread :");
        stringBuilder.append("id=").append(thread.getId()).append(",");
        stringBuilder.append("name=").append(thread.getName()).append("\n");
        stringBuilder.append("exception=").append(th.getMessage()).append("\n");
        stringBuilder.append("Exception stacktaces : \n");
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuilder.append(stringWriter.toString());
        return stringBuilder.toString();
    }

    public static final boolean Assert(boolean z) {
        if (!z) {
            logE(c + "Assert failed! " + dumpStackTrace(getParentStackTrace()), new Object[0]);
            if (SixRoomsUtils.appIsDebug()) {
                throw new RuntimeException();
            }
        }
        return z;
    }

    public static final boolean Assert(boolean z, Throwable th) {
        if (!z) {
            String dumpExceptionStackTrace = dumpExceptionStackTrace(Thread.currentThread(), th);
            logE(c + " Assert failed!", new Object[0]);
            logE(c + " " + dumpExceptionStackTrace, new Object[0]);
        }
        return z;
    }
}
